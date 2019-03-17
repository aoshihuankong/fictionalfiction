package com.huankong.fictionalfiction.controller;

import com.huankong.fictionalfiction.bean.BookRequestBody;
import com.huankong.fictionalfiction.bean.details.BookDetails;
import com.huankong.fictionalfiction.service.BookDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookDetailsController {
    @Autowired
    private BookDetailsService bookDetailsService;

    @GetMapping(value = "/fictionalfiction/bookdetails")
    public BookDetails bookDetails(@RequestBody BookRequestBody bookRequestBody) {
        return bookDetailsService.bookDetails(bookRequestBody);
    }
}
