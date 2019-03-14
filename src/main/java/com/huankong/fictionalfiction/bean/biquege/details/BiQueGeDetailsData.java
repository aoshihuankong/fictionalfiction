package com.huankong.fictionalfiction.bean.biquege.details;

import java.util.List;

public class BiQueGeDetailsData {
    private String Id;
    private String Name;
    private String Img;
    private String Author;
    private String Desc;
    private int CId;
    private String CName;
    private String LastTime;
    private long FirstChapterId;
    private String LastChapter;
    private long LastChapterId;
    private String BookStatus;
    private List<BiQueGeDetailsSameUserBooks> SameUserBooks;
    private List<BiQueGeDetailsSameCategoryBooks> SameCategoryBooks;
    private BiQueGeDetailsBookVote BookVote;

    public BiQueGeDetailsBookVote getBookVote() {
        return BookVote;
    }

    public void setBookVote(BiQueGeDetailsBookVote bookVote) {
        BookVote = bookVote;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public int getCId() {
        return CId;
    }

    public void setCId(int CId) {
        this.CId = CId;
    }

    public String getCName() {
        return CName;
    }

    public void setCName(String CName) {
        this.CName = CName;
    }

    public String getLastTime() {
        return LastTime;
    }

    public void setLastTime(String lastTime) {
        LastTime = lastTime;
    }

    public long getFirstChapterId() {
        return FirstChapterId;
    }

    public void setFirstChapterId(long firstChapterId) {
        FirstChapterId = firstChapterId;
    }

    public String getLastChapter() {
        return LastChapter;
    }

    public void setLastChapter(String lastChapter) {
        LastChapter = lastChapter;
    }

    public long getLastChapterId() {
        return LastChapterId;
    }

    public void setLastChapterId(long lastChapterId) {
        LastChapterId = lastChapterId;
    }

    public String getBookStatus() {
        return BookStatus;
    }

    public void setBookStatus(String bookStatus) {
        BookStatus = bookStatus;
    }

    public List<BiQueGeDetailsSameUserBooks> getSameUserBooks() {
        return SameUserBooks;
    }

    public void setSameUserBooks(List<BiQueGeDetailsSameUserBooks> sameUserBooks) {
        SameUserBooks = sameUserBooks;
    }
}
