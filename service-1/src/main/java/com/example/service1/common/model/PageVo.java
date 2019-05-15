package com.example.service1.common.model;

public class PageVo {
    private int pageSize = 10;
    private int curPage = 1;
    private int startIndex = 0;
    private int endIndex = 0;

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public int getStartIndex() {
        return startIndex == 0 ? (curPage-1) * pageSize : startIndex;
    }

    public int getEndIndex() {
        return endIndex == 0 ? curPage * pageSize : endIndex;
    }
}
