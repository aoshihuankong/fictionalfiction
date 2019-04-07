package com.huankong.fictionalfiction.service;

import com.huankong.fictionalfiction.bean.BookRequestBody;
import com.huankong.fictionalfiction.bean.User;
import com.huankong.fictionalfiction.mapper.UserBookMapper;
import com.huankong.fictionalfiction.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ExistBookService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserBookMapper userBookMapper;

    public Map<String, Boolean> existBook(BookRequestBody bookRequestBody) {
        String username = bookRequestBody.getName();
        String bookid = bookRequestBody.getUrl();

        User user = userMapper.getUserByUserName(username);
        Integer userid = user.getId();

        Map<String, Boolean> map = new HashMap<>();
        if (userBookMapper.getUserBookByUserIdAndBookId(userid, bookid) == null) {
            // 返回不存在
            map.put("exist", false);
            return map;
        }
        // 如果已添加到书架，返回存在
        map.put("exist", true);
        return map;
    }
}
