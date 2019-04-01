package com.huankong.fictionalfiction.controller;

import com.huankong.fictionalfiction.bean.BookRequestBody;
import com.huankong.fictionalfiction.bean.content.BookContent;
import com.huankong.fictionalfiction.service.BookContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookContentController {
    @Autowired
    private BookContentService bookContentService;

    @PostMapping(value = "/fictionalfiction/bookcontent")
    public BookContent bookContent(@RequestBody BookRequestBody bookRequestBody) {
        return bookContentService.bookContent(bookRequestBody);
    }
}
