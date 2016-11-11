package com.fimi.soul.entity;

import java.io.Serializable;

public class Page implements Serializable {
    private static final long serialVersionUID = 1;
    private int curpage;
    private int rows;
    private int total;

    public Page() {
        this.rows = 20;
    }

    public int getCurpage() {
        return this.curpage;
    }

    public int getRows() {
        return this.rows;
    }

    public int getTotal() {
        return this.total;
    }

    public void setCurpage(int i) {
        this.curpage = i;
    }

    public void setRows(int i) {
        this.rows = i;
    }

    public void setTotal(int i) {
        this.total = i;
    }
}
