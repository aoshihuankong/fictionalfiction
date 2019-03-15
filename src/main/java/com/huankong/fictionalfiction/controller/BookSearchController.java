package com.huankong.fictionalfiction.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huankong.fictionalfiction.bean.BookRequestBody;
import com.huankong.fictionalfiction.bean.search.BookSearch;
import com.huankong.fictionalfiction.service.BookSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class BookSearchController {
    @Autowired
    BookSearchService bookSearchService;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    private BookSearch bookSearch;

    @GetMapping(value = "/fictionalfiction/booksearch")
    public BookSearch bookSearch(@RequestBody BookRequestBody bookRequestBody) {
        String responseString = stringRedisTemplate.opsForValue().get(bookRequestBody.getKey());
        if (responseString != null && responseString.length() > 0) {
            return new Gson().fromJson(responseString, new TypeToken<BookSearch>() {
            }.getType());
        } else {
            bookSearch = bookSearchService.bookSearch(bookRequestBody.getKey());
            stringRedisTemplate.opsForValue().set(bookRequestBody.getKey(), new Gson().toJson(bookSearch), 5 * 60, TimeUnit.SECONDS);
            return bookSearch;
        }
    }
}
