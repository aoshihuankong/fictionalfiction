package com.huankong.fictionalfiction.ipproxy;

import com.huankong.fictionalfiction.bean.IPProxy;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.*;

import static java.lang.System.out;

public class IPFilter {
    //对IP进行过滤
    public static Set<IPProxy> filter(Set<IPProxy> ipProxys) {
        Set<IPProxy> newipProxys = new HashSet<>();

        for (IPProxy ipProxy : ipProxys
             ) {
            String ipSpeed = ipProxy.getIPSpeed();

            ipSpeed = ipSpeed.substring(0, ipSpeed.indexOf('秒'));
            double Speed = Double.parseDouble(ipSpeed);

            if (Speed <= 3.0) {
                newipProxys.add(ipProxy);
            }
        }

        return newipProxys;
    }
    // 对ip进行测试
    public static Set<IPProxy> ipIsAble(Set<IPProxy> ipProxys) {
        Set<IPProxy> ipProxySet = new HashSet<>();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        for (IPProxy ipProxy : ipProxys
             ) {
            String ip = ipProxy.getIPAddress();
            String port = ipProxy.getIPPort();

            HttpHost proxy = new HttpHost(ip, Integer.parseInt(port));
            RequestConfig config = RequestConfig.custom().setProxy(proxy).setConnectTimeout(5000).
                    setSocketTimeout(5000).build();
            HttpGet httpGet = new HttpGet("https://sou.jiaston.com/search.aspx");
            httpGet.setConfig(config);

            httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            httpGet.setHeader("Accept-Encoding", "gzip, deflate, br");
            httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");

            try {
                response = httpClient.execute(httpGet);
                ipProxySet.add(ipProxy);
            } catch (IOException e) {
                out.println("不可用代理已删除" + ipProxy.getIPAddress() + ":" + ipProxy.getIPPort());
            }
        }

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

        return ipProxySet;
    }
}
