package com.huankong.fictionalfiction.bean.chapter;

import java.util.List;

public class ChapterData {
    private long id;
    private String name;
    private List<ChapterChapters> chapters;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChapterChapters> getChapters() {
        return chapters;
    }

    public void setChapters(List<ChapterChapters> chapters) {
        this.chapters = chapters;
    }
}
