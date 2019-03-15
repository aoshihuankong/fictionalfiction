package com.huankong.fictionalfiction.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huankong.fictionalfiction.bean.BookRequestBody;
import com.huankong.fictionalfiction.bean.details.BookDetails;
import com.huankong.fictionalfiction.bean.search.BookSearch;
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
public class BookDetailsController {
    @Autowired
    BookDetailsService bookDetailsService;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    private BookDetails bookDetails;

    @GetMapping(value = "/fictionalfiction/bookdetails")
    public BookDetails bookDetails(@RequestBody BookRequestBody bookRequestBody) {
        String responseString = stringRedisTemplate.opsForValue().get(bookRequestBody.getKey());
        if (responseString != null && responseString.length() > 0) {
            return new Gson().fromJson(responseString, new TypeToken<BookDetails>() {
            }.getType());
        } else {
            bookDetails = bookDetailsService.bookDetails(bookRequestBody.getKey());
            stringRedisTemplate.opsForValue().set(bookRequestBody.getKey(), new Gson().toJson(bookDetails), 5 * 60, TimeUnit.SECONDS);
            return bookDetails;
        }
    }
}
