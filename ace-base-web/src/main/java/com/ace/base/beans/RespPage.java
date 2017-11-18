package com.ace.base.beans;

import java.util.List;

public class RespPage<T>  {
    private int pageNo;
    private int pageSize;
    private long rowCount;
    private long pageCount;
    private List<T> data;


    public RespPage(int pageNo, int pageSize, long rowCount,List<T> data) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.rowCount = rowCount;
        this.pageCount = (rowCount+pageSize-1)/pageSize;
        this.data = data;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getRowCount() {
        return rowCount;
    }

    public void setRowCount(long rowCount) {
        this.rowCount = rowCount;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
