package com.huankong.fictionalfiction.controller;

import com.huankong.fictionalfiction.bean.BookRequestBody;
import com.huankong.fictionalfiction.bean.chapter.BookChapter;
import com.huankong.fictionalfiction.service.BookChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookChapterController {
    @Autowired
    private BookChapterService bookChapterService;

    @PostMapping(value = "/fictionalfiction/bookchapter")
    public BookChapter bookChapter(@RequestBody BookRequestBody bookRequestBody) {
        return bookChapterService.bookChapter(bookRequestBody);
    }
}
