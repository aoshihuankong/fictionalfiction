package com.huankong.fictionalfiction.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huankong.fictionalfiction.bean.BookSearch;
import com.huankong.fictionalfiction.service.BookSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BookSearchController {
    @Autowired
    BookSearchService bookSearchService;

    @GetMapping(value = "/fictionalfiction/booksearch")
    public BookSearch bookSearch(@RequestBody String requestString) {
        Map<String, String> map = new Gson().fromJson(requestString, new TypeToken<HashMap>(){}.getType());
        return bookSearchService.bookSearch(map.get("name"));
    }
}
