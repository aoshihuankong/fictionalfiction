package com.huankong.fictionalfiction.controller;

import com.huankong.fictionalfiction.bean.BookRequestBody;
import com.huankong.fictionalfiction.service.ForgetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForgetPasswordController {
    @Autowired
    private ForgetPasswordService forgetPasswordService;

    @PostMapping(value = "/fictionalfiction/forgetpassword")
    public void addBook(@RequestBody BookRequestBody bookRequestBody) {
        forgetPasswordService.forgetPassword(bookRequestBody);
    }
}
