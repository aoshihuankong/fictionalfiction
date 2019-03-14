package com.huankong.fictionalfiction.bean.biquege.search;

import java.util.List;

public class BiQueGeSearch {
    private int status;
    private String info;
    private List<BiQueGeSearchData> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<BiQueGeSearchData> getData() {
        return data;
    }

    public void setData(List<BiQueGeSearchData> data) {
        this.data = data;
    }
}
