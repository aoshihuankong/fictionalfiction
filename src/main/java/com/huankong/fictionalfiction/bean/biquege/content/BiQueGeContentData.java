package com.huankong.fictionalfiction.bean.biquege.content;

public class BiQueGeContentData {
    private long id;
    private String name;
    private long cid;
    private String cname;
    private long pid;
    private long nid;
    private String content;
    private int hasContent;

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

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public long getNid() {
        return nid;
    }

    public void setNid(long nid) {
        this.nid = nid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getHasContent() {
        return hasContent;
    }

    public void setHasContent(int hasContent) {
        this.hasContent = hasContent;
    }
}
