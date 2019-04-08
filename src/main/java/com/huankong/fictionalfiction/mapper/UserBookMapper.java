package com.huankong.fictionalfiction.mapper;

import com.huankong.fictionalfiction.bean.UserBook;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserBookMapper {
    @Select("SELECT * FROM t_userbook WHERE userid = #{userid} AND bookid = #{bookid}")
    public UserBook getUserBookByUserIdAndBookId(@Param("userid") Integer userid, @Param("bookid") String bookid);

    @Select("SELECT * FROM t_userbook WHERE userid = #{userid} order by progressTime desc")
    public List<UserBook> getUserBooksByUserId(Integer userid);

    @Insert("INSERT INTO t_userbook(userid, bookid, name, author, cName) VALUES (#{userid}, #{bookid}, #{name}, #{author}, #{cName})")
    public void insertUserBook(UserBook userBook);

    @Update("UPDATE t_userbook SET progress = #{progress}, progressLink = #{progressLink}, progressTime = #{progressTime} WHERE userid = #{userid} AND bookid = #{bookid}")
    public void updateUserBook(UserBook userBook);

    @Delete("DELETE FROM t_userbook WHERE userid = #{userid} And bookid = #{bookid}")
    public void deleteUserBookByUserIdAndBookId(UserBook userBook);
}
