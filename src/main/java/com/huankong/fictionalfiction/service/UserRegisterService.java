package com.huankong.fictionalfiction.service;

import com.huankong.fictionalfiction.bean.User;
import com.huankong.fictionalfiction.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserRegisterService {
    @Autowired
    UserMapper userMapper;

    public Map<String, String> userRegister(User user) {
        Map<String, String> map = new HashMap<>();

        if (userMapper.getUserByUserName(user.getUsername()) == null) {
            userMapper.insertUser(user);

            map.put("info", "success");
            return map;
        }
        map.put("info", "error");
        return map;
    }
}
