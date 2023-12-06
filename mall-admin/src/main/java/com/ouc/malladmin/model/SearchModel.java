package com.ouc.malladmin.model;

public class SearchModel {
    private int pageIndex;

    /**
     * 每一页的个数
     * */
    private int pageSize;

    private String key;

    private int id;

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

    public String getKey(){
        return key;
    }

    public void setKey(String key){
        this.key = key;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
}
