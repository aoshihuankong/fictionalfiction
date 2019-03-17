package com.huankong.fictionalfiction.controller;

import com.huankong.fictionalfiction.bean.BookRequestBody;
import com.huankong.fictionalfiction.bean.search.BookSearch;
import com.huankong.fictionalfiction.service.BookSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookSearchController {
    @Autowired
    private BookSearchService bookSearchService;

    @GetMapping(value = "/fictionalfiction/booksearch")
    public BookSearch bookSearch(@RequestBody BookRequestBody bookRequestBody) {
        return bookSearchService.bookSearch(bookRequestBody);
    }
}
