package com.huankong.fictionalfiction.controller;

import com.huankong.fictionalfiction.bean.BookRequestBody;
import com.huankong.fictionalfiction.service.DelectBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DelectBookController {
    @Autowired
    DelectBookService delectBookService;

    @PostMapping(value = "/fictionalfiction/delectbook")
    public Map<String, String> addBook(@RequestBody BookRequestBody bookRequestBody) {
        return delectBookService.delectBook(bookRequestBody);
    }
}
