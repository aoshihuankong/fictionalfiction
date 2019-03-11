package com.huankong.fictionalfiction.ipproxy;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huankong.fictionalfiction.bean.IPProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Component
@EnableScheduling
public class IPProxySchedule implements ApplicationRunner {
    @Resource
    StringRedisTemplate stringRedisTemplate;

    // 每隔一分钟执行一次
    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional
    public void ipProxyInit() {
        /*获取redis中的代理ip，并对其进行筛选*/
        String ipProxys = stringRedisTemplate.opsForValue().get("ipProxy");
        Set<IPProxy> ipProxySet;
        if (ipProxys != null && ipProxys.length() > 0 ) {
            ipProxySet = new Gson().fromJson(ipProxys, new TypeToken<HashSet<IPProxy>>(){}.getType());
        } else {
            ipProxySet = new HashSet<>();
        }

        //首先使用本机ip爬取代理网第一页,并和redis中的代理ip整合
        Set<IPProxy> ipProxyMessage = URLFecter.urlParse(ipProxySet);

        //对得到的IP进行筛选，将IP速度在两秒以内的留下，其余删除
        ipProxyMessage = IPFilter.filter(ipProxyMessage);

        //对拿到的ip进行质量检测，将质量不合格的ip在List里进行删除
        ipProxyMessage = IPFilter.ipIsAble(ipProxyMessage);

        //将爬取下来的ip信息写进Redis数据库中
        ipProxys = new Gson().toJson(ipProxyMessage);
        stringRedisTemplate.opsForValue().set("ipProxy", ipProxys);
        System.out.println("IP代理更新完成！");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ipProxyInit();
    }
}
