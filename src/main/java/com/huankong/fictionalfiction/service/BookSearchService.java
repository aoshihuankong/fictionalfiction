package com.huankong.fictionalfiction.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huankong.fictionalfiction.bean.*;
import com.huankong.fictionalfiction.bean.biquege.search.BiQueGeSearch;
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

@Service
public class BookSearchService {
    public BookSearch bookSearch(String key) {
        // 查询该key是否有对应的小说
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        String responseString = null;
        BookSearch bookSearch = new BookSearch();

        RequestConfig config = RequestConfig.custom().setConnectTimeout(3000).
                setSocketTimeout(3000).build();
        HttpGet httpGet = new HttpGet("https://sou.jiaston.com/search.aspx?key="+ key +"&page=1&siteid=app2");
        httpGet.setConfig(config);

        httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate, br");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");

        try {
            response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                responseString = EntityUtils.toString(response.getEntity(),"utf-8");

                // 兼容笔趣阁报文
                BiQueGeSearch biQueGeSearch = new Gson().fromJson(responseString, new TypeToken<BiQueGeSearch>(){}.getType());
                bookSearch.setData(new ArrayList<>());
                bookSearch.setTatol(biQueGeSearch.getData().size());
                bookSearch.setSource(1);
                bookSearch.setInfo(biQueGeSearch.getInfo());
                for (int i = 0; i < biQueGeSearch.getData().size(); i++) {
                    bookSearch.getData().add(new SearchData());
                    bookSearch.getData().get(i).setId("https://quapp.1122dh.com/info/"+ biQueGeSearch.getData().get(i).getId() +".html");
                    bookSearch.getData().get(i).setName(biQueGeSearch.getData().get(i).getName());
                    bookSearch.getData().get(i).setAuthor(biQueGeSearch.getData().get(i).getAuthor());
                    bookSearch.getData().get(i).setImg(biQueGeSearch.getData().get(i).getImg());
                    bookSearch.getData().get(i).setDesc(biQueGeSearch.getData().get(i).getDesc());
                    bookSearch.getData().get(i).setLastChapterLink("https://quapp.1122dh.com/book/"+ biQueGeSearch.getData().get(i).getId() +"/"+ biQueGeSearch.getData().get(i).getLastChapterId() +".html");
                    bookSearch.getData().get(i).setLastChapter(biQueGeSearch.getData().get(i).getLastChapter());
                    bookSearch.getData().get(i).setLastTime(biQueGeSearch.getData().get(i).getUpdateTime());
                    bookSearch.getData().get(i).setcName(biQueGeSearch.getData().get(i).getCName());
                }
                return bookSearch;
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

        bookSearch = new BookSearch();
        bookSearch.setData(new ArrayList<>());
        bookSearch.setTatol(0);
        bookSearch.setSource(1);
        bookSearch.setInfo(response.toString());

        return bookSearch;
    }
}
