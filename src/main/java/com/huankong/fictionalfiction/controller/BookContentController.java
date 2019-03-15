package com.huankong.fictionalfiction.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huankong.fictionalfiction.bean.content.BookContent;
import com.huankong.fictionalfiction.bean.details.BookDetails;
import com.huankong.fictionalfiction.service.BookContentService;
import com.huankong.fictionalfiction.service.BookDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BookContentController {
    @Autowired
    BookContentService bookContentService;

    @GetMapping(value = "/fictionalfiction/bookcontent")
    public BookContent bookContent(@RequestBody String requestString) {
        Map<String, String> map = new Gson().fromJson(requestString, new TypeToken<HashMap>(){}.getType());
        return bookContentService.bookContent(map.get("url"));
    }
}
