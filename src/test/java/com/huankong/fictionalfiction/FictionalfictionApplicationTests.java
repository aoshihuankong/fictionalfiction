package com.huankong.fictionalfiction;

import com.huankong.fictionalfiction.bean.User;
import com.huankong.fictionalfiction.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FictionalfictionApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Test
    public void contextLoads() {
        User user = userMapper.getUserById(1);
        System.out.println(user);
    }

}
