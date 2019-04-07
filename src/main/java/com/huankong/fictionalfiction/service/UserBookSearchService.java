package com.huankong.fictionalfiction.service;

import com.huankong.fictionalfiction.bean.BookRequestBody;
import com.huankong.fictionalfiction.bean.User;
import com.huankong.fictionalfiction.bean.UserBook;
import com.huankong.fictionalfiction.bean.UserBookSearch;
import com.huankong.fictionalfiction.bean.details.BookDetails;
import com.huankong.fictionalfiction.mapper.UserBookMapper;
import com.huankong.fictionalfiction.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserBookSearchService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserBookMapper userBookMapper;
    @Autowired
    private BookDetailsService bookDetailsService;

    public UserBookSearch userBookSeach(BookRequestBody bookRequestBody) {
        String username = bookRequestBody.getName();

        UserBookSearch userBookSearch = new UserBookSearch();
        User user = userMapper.getUserByUserName(username);
        Integer userid = user.getId();

        List<UserBook> userBooks = userBookMapper.getUserBooksByUserId(userid);

        // 添加最新章节和更新时间
        BookDetails bookDetails = new BookDetails();
        List<UserBook> userBookscopy = new ArrayList<>();
        for (UserBook userBook : userBooks
             ) {
            bookRequestBody.setUrl(userBook.getBookid());
            bookDetails = bookDetailsService.bookDetails(bookRequestBody);
            userBook.setLastChapterLink(bookDetails.getData().getLastChapterLink());
            userBook.setLastChapter(bookDetails.getData().getLastChapter());
            userBook.setLastTime(bookDetails.getData().getLastTime());
            userBookscopy.add(userBook);
        }

        userBookSearch.setTotal(userBookscopy.size());
        userBookSearch.setRows(userBookscopy);

        return userBookSearch;
    }
}
