package com.huankong.fictionalfiction.bean;

public class UserBook {
    private int id;
    private int userid;
    private String bookid;
    private String name;
    private String author;
    private String cName;
    private String lastChapterLink;
    private String lastChapter;
    private String lastTime;
    private String progress;
    private String progressLink;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getLastChapterLink() {
        return lastChapterLink;
    }

    public void setLastChapterLink(String lastChapterLink) {
        this.lastChapterLink = lastChapterLink;
    }

    public String getLastChapter() {
        return lastChapter;
    }

    public void setLastChapter(String lastChapter) {
        this.lastChapter = lastChapter;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getProgressLink() {
        return progressLink;
    }

    public void setProgressLink(String progressLink) {
        this.progressLink = progressLink;
    }
}
