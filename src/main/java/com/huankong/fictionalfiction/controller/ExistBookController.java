package com.huankong.fictionalfiction.controller;

import com.huankong.fictionalfiction.bean.BookRequestBody;
import com.huankong.fictionalfiction.service.ExistBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ExistBookController {
    @Autowired
    ExistBookService existBookService;

    @PostMapping(value = "/fictionalfiction/existbook")
    public Map<String, Boolean> addBook(@RequestBody BookRequestBody bookRequestBody) {
        return existBookService.existBook(bookRequestBody);
    }
}
