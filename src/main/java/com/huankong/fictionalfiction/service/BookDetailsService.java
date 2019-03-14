package com.huankong.fictionalfiction.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huankong.fictionalfiction.bean.*;
import com.huankong.fictionalfiction.bean.biquege.details.BiQueGeDetails;
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
public class BookDetailsService {
    public BookDetails bookDetails(String url) {
        // 查询该key是否有对应的小说
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        String responseString = null;
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
                bookDetails.getData().setLastTime(biQueGeDetails.getData().getLastTime());
                bookDetails.getData().setcName(biQueGeDetails.getData().getCName());
                return bookDetails;
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

        bookDetails = new BookDetails();
        bookDetails.setData(new DetailsData());
        bookDetails.setSource(1);
        bookDetails.setInfo(response.toString());

        return bookDetails;
    }
}
