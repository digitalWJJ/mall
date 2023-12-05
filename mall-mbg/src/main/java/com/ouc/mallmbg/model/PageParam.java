package com.ouc.mallmbg.model;

public class PageParam {
    /**
     * 查询的页数
     * */
    private int pageIndex;

    /**
     * 每一页的个数
     * */
    private int pageSize;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
