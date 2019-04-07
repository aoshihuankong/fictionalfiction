package com.huankong.fictionalfiction.controller;

import com.huankong.fictionalfiction.bean.User;
import com.huankong.fictionalfiction.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserRegisterController {
    @Autowired
    UserRegisterService userRegisterService;

    @PostMapping(value = "/fictionalfiction/register")
    public Map<String, String> userRegister(@RequestBody User user) {
        return userRegisterService.userRegister(user);
    }
}
