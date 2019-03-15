package com.huankong.fictionalfiction.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huankong.fictionalfiction.bean.biquege.content.BiQueGeContent;
import com.huankong.fictionalfiction.bean.content.BookContent;
import com.huankong.fictionalfiction.bean.content.ContentData;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BookContentService {
    public BookContent bookContent(String url) {
        // 查询该key是否有对应的小说
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        String responseString = null;
        BookContent bookContent = new BookContent();

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
                bookContent.getData().setId("https://quapp.1122dh.com/book/" + biQueGeContent.getData().getId() + "/");
                bookContent.getData().setName(biQueGeContent.getData().getName());
                bookContent.getData().setCid("https://quapp.1122dh.com/book/" + biQueGeContent.getData().getId() + "/" + biQueGeContent.getData().getCid() + ".html");
                bookContent.getData().setCname(biQueGeContent.getData().getCname());
                bookContent.getData().setPid(biQueGeContent.getData().getPid() != -1 ? "https://quapp.1122dh.com/book/" + biQueGeContent.getData().getId() + "/" + biQueGeContent.getData().getPid() + ".html" : "");
                bookContent.getData().setNid(biQueGeContent.getData().getNid() != -1 ? "https://quapp.1122dh.com/book/" + biQueGeContent.getData().getId() + "/" + biQueGeContent.getData().getNid() + ".html" : "");
                bookContent.getData().setContent(biQueGeContent.getData().getContent());

                return bookContent;
            }
        } catch (IOException e) {
            e.printStackTrace();
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

        bookContent = new BookContent();
        bookContent.setData(new ContentData());
        bookContent.setSource(1);
        bookContent.setInfo(response.toString());

        return bookContent;
    }
}
