package com.huankong.fictionalfiction.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huankong.fictionalfiction.bean.BookRequestBody;
import com.huankong.fictionalfiction.bean.biquege.details.BiQueGeDetails;
import com.huankong.fictionalfiction.bean.details.BookDetails;
import com.huankong.fictionalfiction.bean.details.DetailsData;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Service
public class BookDetailsService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public BookDetails bookDetails(BookRequestBody bookRequestBody) {
        String url = bookRequestBody.getUrl();

        String responseString = stringRedisTemplate.opsForValue().get(url);
        if ("".equals(responseString)) {
            return new Gson().fromJson(responseString, new TypeToken<BookDetails>() {
            }.getType());
        }

        // 查询该key是否有对应的小说
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        BookDetails bookDetails = new BookDetails();

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
                BiQueGeDetails biQueGeDetails = new Gson().fromJson(responseString, new TypeToken<BiQueGeDetails>() {
                }.getType());

                bookDetails.setSource(1);
                bookDetails.setInfo(biQueGeDetails.getInfo());
                bookDetails.setData(new DetailsData());
                bookDetails.getData().setId("https://quapp.1122dh.com/book/" + biQueGeDetails.getData().getId() + "/");
                bookDetails.getData().setName(biQueGeDetails.getData().getName());
                bookDetails.getData().setAuthor(biQueGeDetails.getData().getAuthor());
                bookDetails.getData().setImg("https://imgapi.jiaston.com/BookFiles/BookImages/" + biQueGeDetails.getData().getImg());
                bookDetails.getData().setDesc(biQueGeDetails.getData().getDesc());
                bookDetails.getData().setFirstChapterLink("https://quapp.1122dh.com/book/" + biQueGeDetails.getData().getId() + "/" + biQueGeDetails.getData().getFirstChapterId() + ".html");
                bookDetails.getData().setLastChapterLink("https://quapp.1122dh.com/book/" + biQueGeDetails.getData().getId() + "/" + biQueGeDetails.getData().getLastChapterId() + ".html");
                bookDetails.getData().setLastChapter(biQueGeDetails.getData().getLastChapter());
                bookDetails.getData().setLastTime(new SimpleDateFormat("yyyy-MM-dd H:mm:ss").format(new SimpleDateFormat("MM/d/yyyy K:m:s a", Locale.ENGLISH).parse(biQueGeDetails.getData().getLastTime()).getTime()));
                bookDetails.getData().setcName(biQueGeDetails.getData().getCName());

                // 先将数据保存在缓存中
                stringRedisTemplate.opsForValue().set(url, new Gson().toJson(bookDetails), 30 * 60, TimeUnit.SECONDS);
                // 最后返回数据
                return bookDetails;
            } else {
                bookDetails.setData(new DetailsData());
                bookDetails.setSource(1);
                bookDetails.setInfo("error");

                return bookDetails;
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
            bookDetails.setData(new DetailsData());
            bookDetails.setSource(1);
            bookDetails.setInfo("error");

            return bookDetails;
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
