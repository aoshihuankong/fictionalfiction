package com.huankong.fictionalfiction.controller;

import com.huankong.fictionalfiction.bean.BookRequestBody;
import com.huankong.fictionalfiction.bean.UserBookSearch;
import com.huankong.fictionalfiction.service.UserBookSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserBookSearchController {
    @Autowired
    UserBookSearchService userBookSeachService;

    @PostMapping(value = "/fictionalfiction/userbooksearch")
    public UserBookSearch addBook(@RequestBody BookRequestBody bookRequestBody) {
        return userBookSeachService.userBookSeach(bookRequestBody);
    }
}
