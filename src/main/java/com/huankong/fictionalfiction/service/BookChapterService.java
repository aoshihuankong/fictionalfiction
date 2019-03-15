package com.huankong.fictionalfiction.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huankong.fictionalfiction.bean.biquege.chapter.BiQueGeChapter;
import com.huankong.fictionalfiction.bean.chapter.BookChapter;
import com.huankong.fictionalfiction.bean.chapter.ChapterChapters;
import com.huankong.fictionalfiction.bean.chapter.ChapterData;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

@Service
public class BookChapterService {
    public BookChapter bookChapter(String url) {
        // 查询该key是否有对应的小说
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        String responseString = null;
        BookChapter bookChapter = new BookChapter();

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
                BiQueGeChapter biQueGeChapter = new Gson().fromJson(responseString, new TypeToken<BiQueGeChapter>() {
                }.getType());

                bookChapter.setSource(1);
                bookChapter.setInfo(biQueGeChapter.getInfo());
                bookChapter.setData(new ChapterData());
                bookChapter.getData().setId(biQueGeChapter.getData().getId());
                bookChapter.getData().setName(biQueGeChapter.getData().getName());
                bookChapter.getData().setChapters(new ArrayList<>());
                // 删除list中数据为null的元素
                biQueGeChapter.getData().getList().removeIf(Objects::isNull);
                for (int i = 0; i < biQueGeChapter.getData().getList().size(); i++) {
                    biQueGeChapter.getData().getList().get(i).getList().removeIf(Objects::isNull);
                    for (int j = 0; j < biQueGeChapter.getData().getList().get(i).getList().size(); j++) {
                        ChapterChapters chapterChapters = new ChapterChapters();
                        chapterChapters.setId("https://quapp.1122dh.com/book/" + biQueGeChapter.getData().getId() + "/" + biQueGeChapter.getData().getList().get(i).getList().get(j).getId() + ".html");
                        chapterChapters.setName(biQueGeChapter.getData().getList().get(i).getList().get(j).getName());
                        bookChapter.getData().getChapters().add(chapterChapters);
                    }
                }
                return bookChapter;
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

        bookChapter = new BookChapter();
        bookChapter.setData(new ChapterData());
        bookChapter.setSource(1);
        bookChapter.setInfo(response.toString());

        return bookChapter;
    }
}
