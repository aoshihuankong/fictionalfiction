package com.huankong.fictionalfiction.bean.biquege.chapter;

import java.util.List;

public class BiQueGeChapterData {
    private long id;
    private String name;
    private List<BiQueGeChapterDataList> list;

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

    public List<BiQueGeChapterDataList> getList() {
        return list;
    }

    public void setList(List<BiQueGeChapterDataList> list) {
        this.list = list;
    }
}
