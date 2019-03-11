package com.huankong.fictionalfiction.ipproxy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IPProxyScheduleTest {
    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Test
    public void ipProxy() {
        /*IPProxySchedule ipProxySchedule = new IPProxySchedule();
        ipProxySchedule.ipProxyInit();*/
        System.out.println(stringRedisTemplate.opsForValue().get("ipProxy"));
    }
}