package com.huankong.fictionalfiction.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huankong.fictionalfiction.bean.BookRequestBody;
import com.huankong.fictionalfiction.bean.chapter.BookChapter;
import com.huankong.fictionalfiction.bean.content.BookContent;
import com.huankong.fictionalfiction.service.BookChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class BookChapterController {
    @Autowired
    BookChapterService bookChapterService;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    private BookChapter bookChapter;

    @GetMapping(value = "/fictionalfiction/bookchapter")
    public BookChapter bookChapter(@RequestBody BookRequestBody bookRequestBody) {
        String responseString = stringRedisTemplate.opsForValue().get(bookRequestBody.getKey());
        if (responseString != null && responseString.length() > 0) {
            return new Gson().fromJson(responseString, new TypeToken<BookChapter>() {
            }.getType());
        } else {
            bookChapter = bookChapterService.bookChapter(bookRequestBody.getKey());
            stringRedisTemplate.opsForValue().set(bookRequestBody.getKey(), new Gson().toJson(bookChapter), 5 * 60, TimeUnit.SECONDS);
            return bookChapter;
        }
    }
}
