package com.huankong.fictionalfiction.ipproxy;

import com.huankong.fictionalfiction.bean.IPProxy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.Set;

public class URLFecter {
    //使用本机IP爬取xici代理网站的第一页
    public static Set<IPProxy> urlParse(Set<IPProxy> ipProxys) {
        String url = "https://www.kuaidaili.com/free/inha/1/";
        String html = MyHttpResponse.getHtml(url);

        //将html解析成DOM结构
        Document document = Jsoup.parse(html);

        //提取所需要的数据
        Elements trs = document.select("table").select("tbody").select("tr");

        for (int i = 0; i < trs.size(); i++) {
            IPProxy ipProxy = new IPProxy();
            String ipAddress = trs.get(i).select("td").get(0).text();
            String ipPort = trs.get(i).select("td").get(1).text();
            String ipType = trs.get(i).select("td").get(3).text();
            String ipSpeed = trs.get(i).select("td").get(5).text();

            ipProxy.setIPAddress(ipAddress);
            ipProxy.setIPPort(ipPort);
            ipProxy.setIPType(ipType);
            ipProxy.setIPSpeed(ipSpeed);

            ipProxys.add(ipProxy);
        }

        return ipProxys;
    }
}
