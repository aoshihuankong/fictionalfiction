package com.huankong.fictionalfiction.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huankong.fictionalfiction.bean.BookRequestBody;
import com.huankong.fictionalfiction.bean.UserBook;
import com.huankong.fictionalfiction.bean.biquege.content.BiQueGeContent;
import com.huankong.fictionalfiction.bean.content.BookContent;
import com.huankong.fictionalfiction.bean.content.ContentData;
import com.huankong.fictionalfiction.mapper.UserBookMapper;
import com.huankong.fictionalfiction.mapper.UserMapper;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class BookContentService {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    private JestClient jestClient;
    @Autowired
    private UserBookMapper userBookMapper;
    @Autowired
    private UserMapper userMapper;

    public BookContent bookContent(BookRequestBody bookRequestBody) {
        String username = bookRequestBody.getUsername();
        String name = bookRequestBody.getName();
        String url = bookRequestBody.getUrl();

        BookContent bookContent = new BookContent();

        // 先尝试从缓存中获取数据
        String responseString = stringRedisTemplate.opsForValue().get(url);
        if (!"".equals(responseString) && responseString != null) {
            // 如果缓存中存在则直接返回
            bookContent = new Gson().fromJson(responseString, new TypeToken<BookContent>() {
            }.getType());
            // 更新阅读进度
            String bookid = bookContent.getData().getId();
            Integer userid = userMapper.getUserByUserName(username).getId();
            UserBook userBook = new UserBook();
            userBook.setUserid(userid);
            userBook.setBookid(bookid);
            userBook.setProgress(bookContent.getData().getCname());
            userBook.setProgressLink(bookContent.getData().getCid());
            userBookMapper.updateUserBook(userBook);
            return bookContent;
        }

        // 再尝试从es中获取数据
        String json = "{\"query\":{\"bool\":{\"filter\":{\"bool\":{\"must\":[{\"term\":{\"data.cid.keyword\":\"" + url + "\"}}]}}}}}";
        Search search = new Search.Builder(json).addIndex("bookcontent").addType(name).build();
        try {
            SearchResult searchResult = jestClient.execute(search);

            if (searchResult.getResponseCode() == 200 && searchResult.getFirstHit(BookContent.class) != null) {
                // 如果ES中存在本地数据，则直接返回
                bookContent = searchResult.getFirstHit(BookContent.class).source;
                // 先将数据保存在缓存中
                stringRedisTemplate.opsForValue().set(bookRequestBody.getUrl(), new Gson().toJson(bookContent), 30 * 60, TimeUnit.SECONDS);
                // 更新阅读进度
                String bookid = bookContent.getData().getId();
                Integer userid = userMapper.getUserByUserName(username).getId();
                UserBook userBook = new UserBook();
                userBook.setUserid(userid);
                userBook.setBookid(bookid);
                userBook.setProgress(bookContent.getData().getCname());
                userBook.setProgressLink(bookContent.getData().getCid());
                userBookMapper.updateUserBook(userBook);
                // 再返回数据
                return bookContent;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 如果缓存和ES中都没有数据，则调用第三方api
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        bookContent = new BookContent();

        RequestConfig config = RequestConfig.custom().setConnectTimeout(3000).
                setSocketTimeout(3000).build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(config);

        httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate, br");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");

        try {
            response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                responseString = EntityUtils.toString(response.getEntity(), "utf-8");

                // 兼容笔趣阁报文
                BiQueGeContent biQueGeContent = new Gson().fromJson(responseString, new TypeToken<BiQueGeContent>() {
                }.getType());

                bookContent.setSource(1);
                bookContent.setInfo(biQueGeContent.getInfo());
                bookContent.setData(new ContentData());
                bookContent.getData().setId("https://quapp.1122dh.com/info/" + biQueGeContent.getData().getId() + ".html");
                bookContent.getData().setName(biQueGeContent.getData().getName());
                bookContent.getData().setCid("https://quapp.1122dh.com/book/" + biQueGeContent.getData().getId() + "/" + biQueGeContent.getData().getCid() + ".html");
                bookContent.getData().setCname(biQueGeContent.getData().getCname());
                bookContent.getData().setPid(biQueGeContent.getData().getPid() != -1 ? "https://quapp.1122dh.com/book/" + biQueGeContent.getData().getId() + "/" + biQueGeContent.getData().getPid() + ".html" : "");
                bookContent.getData().setNid(biQueGeContent.getData().getNid() != -1 ? "https://quapp.1122dh.com/book/" + biQueGeContent.getData().getId() + "/" + biQueGeContent.getData().getNid() + ".html" : "");
                bookContent.getData().setContent(biQueGeContent.getData().getContent());

                // 先将数据保存在缓存中
                stringRedisTemplate.opsForValue().set(url, new Gson().toJson(bookContent), 30 * 60, TimeUnit.SECONDS);
                // 再将数据保存在ES中（最新章节不保存）
                if (bookContent.getData().getNid().length() > 0) {
                    Index index = new Index.Builder(bookContent).index("bookcontent").type(bookContent.getData().getName()).build();
                    jestClient.execute(index);
                }
                // 更新阅读进度
                String bookid = bookContent.getData().getId();
                Integer userid = userMapper.getUserByUserName(username).getId();
                UserBook userBook = new UserBook();
                userBook.setUserid(userid);
                userBook.setBookid(bookid);
                userBook.setProgress(bookContent.getData().getCname());
                userBook.setProgressLink(bookContent.getData().getCid());
                userBookMapper.updateUserBook(userBook);
                // 最后返回数据
                return bookContent;
            } else {
                bookContent.setData(new ContentData());
                bookContent.setSource(1);
                bookContent.setInfo("error");

                return bookContent;
            }
        } catch (IOException e) {
            e.printStackTrace();
            bookContent.setData(new ContentData());
            bookContent.setSource(1);
            bookContent.setInfo("error");

            return bookContent;
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
