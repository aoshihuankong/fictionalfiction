package com.huankong.fictionalfiction.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huankong.fictionalfiction.bean.BookRequestBody;
import com.huankong.fictionalfiction.bean.content.BookContent;
import com.huankong.fictionalfiction.bean.details.BookDetails;
import com.huankong.fictionalfiction.service.BookContentService;
import com.huankong.fictionalfiction.service.BookDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class BookContentController {
    @Autowired
    BookContentService bookContentService;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    private BookContent bookContent;

    @GetMapping(value = "/fictionalfiction/bookcontent")
    public BookContent bookContent(@RequestBody BookRequestBody bookRequestBody) {
        String responseString = stringRedisTemplate.opsForValue().get(bookRequestBody.getKey());
        if (responseString != null && responseString.length() > 0) {
            return new Gson().fromJson(responseString, new TypeToken<BookContent>() {
            }.getType());
        } else {
            bookContent = bookContentService.bookContent(bookRequestBody.getKey());
            stringRedisTemplate.opsForValue().set(bookRequestBody.getKey(), new Gson().toJson(bookContent), 5 * 60, TimeUnit.SECONDS);
            return bookContent;
        }
    }
}
