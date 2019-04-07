package com.huankong.fictionalfiction.service;

import com.huankong.fictionalfiction.bean.BookRequestBody;
import com.huankong.fictionalfiction.bean.User;
import com.huankong.fictionalfiction.bean.UserBook;
import com.huankong.fictionalfiction.mapper.UserBookMapper;
import com.huankong.fictionalfiction.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DelectBookService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserBookMapper userBookMapper;

    public Map<String, String> delectBook(BookRequestBody bookRequestBody) {
        String username = bookRequestBody.getName();
        List<String> bookids = bookRequestBody.getUrls();

        Map<String, String> map = new HashMap<>();

        User user = userMapper.getUserByUserName(username);

        UserBook userBook = new UserBook();
        userBook.setUserid(user.getId());
        try {
            for (String bookid : bookids
                    ) {
                userBook.setBookid(bookid);
                userBookMapper.deleteUserBookByUserIdAndBookId(userBook);
            }
            map.put("info", "success");
            return map;
        } catch (Exception e) {
            e.printStackTrace();

            map.put("info", "error");
            return map;
        }
    }
}
