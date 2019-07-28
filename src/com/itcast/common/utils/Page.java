package com.itcast.common.utils;

import java.util.List;

public class Page<T> {

    private int total;
    private int page;
    private int size;
    private List<T> rows;//结果集

    public Page(int total, Integer page, Integer rows, List<T> list) {
        this.setTotal(total);
        this.setPage(page);
        this.setRows(list);
        this.setSize(rows);


    }


    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public List<T> getRows() {
        return rows;
    }
    public void setRows(List<T> rows) {
        this.rows = rows;
    }



}
