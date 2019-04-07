package com.huankong.fictionalfiction.bean;

import java.util.List;

public class UserBookSearch {
    private int total;
    private List<UserBook> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<UserBook> getRows() {
        return rows;
    }

    public void setRows(List<UserBook> rows) {
        this.rows = rows;
    }
}
