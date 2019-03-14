package com.huankong.fictionalfiction.bean.biquege.details;

class BiQueGeDetailsSameUserBooks{
    private int Id;
    private String Name;
    private String Author;
    private String Img;
    private long LastChapterId;
    private String LastChapter;
    private double Score;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public long getLastChapterId() {
        return LastChapterId;
    }

    public void setLastChapterId(long lastChapterId) {
        LastChapterId = lastChapterId;
    }

    public String getLastChapter() {
        return LastChapter;
    }

    public void setLastChapter(String lastChapter) {
        LastChapter = lastChapter;
    }

    public double getScore() {
        return Score;
    }

    public void setScore(double score) {
        Score = score;
    }
}
