package com.example.service1.model;

import java.io.Serializable;
import java.util.List;

public class BlogPO implements Serializable {

    private static final long serialVersionUID = 2036263491950433686L;

    private String guid;
    private String deputyGuid;
    private String title;
    private String createTime;
    private String md;
    private List<String> md_splits;
    private String html;
    private List<String> html_splits;

    public List<String> getMd_splits() {
        return md_splits;
    }

    public void setMd_splits(List<String> md_splits) {
        this.md_splits = md_splits;
    }

    public List<String> getHtml_splits() {
        return html_splits;
    }

    public void setHtml_splits(List<String> html_splits) {
        this.html_splits = html_splits;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getDeputyGuid() {
        return deputyGuid;
    }

    public void setDeputyGuid(String deputyGuid) {
        this.deputyGuid = deputyGuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMd() {
        return md;
    }

    public void setMd(String md) {
        this.md = md;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
}
