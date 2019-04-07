package com.huankong.fictionalfiction.mapper;

import com.huankong.fictionalfiction.bean.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM t_user WHERE id = #{id}")
    public User getUserById(Integer id);

    @Select("SELECT * FROM t_user WHERE username = #{username}")
    public User getUserByUserName(String username);

    @Update("UPDATE t_user SET username = #{username}, password = #{password}, email = #{email} WHERE id = #{d}")
    public void updateUser(User user);

    @Delete("DELETE FROM t_user WHERE id = #{id}")
    public void deleteUserById(Integer id);

    @Insert("INSERT INTO t_user(username, password, email) VALUES (#{username}, #{password}, #{email})")
    public void insertUser(User user);
}
