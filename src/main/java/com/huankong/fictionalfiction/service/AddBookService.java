package com.huankong.fictionalfiction.service;

import com.huankong.fictionalfiction.bean.BookRequestBody;
import com.huankong.fictionalfiction.bean.User;
import com.huankong.fictionalfiction.bean.UserBook;
import com.huankong.fictionalfiction.bean.details.BookDetails;
import com.huankong.fictionalfiction.mapper.UserBookMapper;
import com.huankong.fictionalfiction.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AddBookService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserBookMapper userBookMapper;
    @Autowired
    private BookDetailsService bookDetailsService;

    public Map<String, String> addBook(BookRequestBody bookRequestBody) {
        String username = bookRequestBody.getName();
        String bookid = bookRequestBody.getUrl();

        Map<String, String> map = new HashMap<>();

        User user = userMapper.getUserByUserName(username);

        UserBook userBook = new UserBook();
        // 查看详情，获取更多信息
        BookDetails bookDetails = bookDetailsService.bookDetails(bookRequestBody);

        userBook.setUserid(user.getId());
        userBook.setBookid(bookid);
        userBook.setName(bookDetails.getData().getName());
        userBook.setAuthor(bookDetails.getData().getAuthor());
        userBook.setcName(bookDetails.getData().getcName());
        try {
            userBookMapper.insertUserBook(userBook);

            map.put("info", "success");
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }

        map.put("info", "error");
        return map;
    }
}
