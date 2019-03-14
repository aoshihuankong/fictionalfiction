package com.huankong.fictionalfiction.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huankong.fictionalfiction.bean.BookChapter;
import com.huankong.fictionalfiction.bean.BookDetails;
import com.huankong.fictionalfiction.service.BookChapterService;
import com.huankong.fictionalfiction.service.BookDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BookChapterController {
    @Autowired
    BookChapterService bookChapterService;

    @GetMapping(value = "/fictionalfiction/bookchapter")
    public BookChapter bookChapter(@RequestBody String requestString) {
        Map<String, String> map = new Gson().fromJson(requestString, new TypeToken<HashMap>(){}.getType());
        return bookChapterService.bookChapter(map.get("id"));
    }
}
