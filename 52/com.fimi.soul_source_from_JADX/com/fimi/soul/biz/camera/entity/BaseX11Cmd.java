package com.fimi.soul.biz.camera.entity;

import java.io.Serializable;

public class BaseX11Cmd implements Serializable {
    private int fetch_size;
    private String md5sum;
    private int msg_id;
    private int offset;
    private String[] options;
    private String param;
    private String rem_size;
    private int rval;
    private long sent_size;
    private long size;
    private int token;
    private String type;

    public BaseX11Cmd() {
        this.token = 0;
        this.msg_id = 0;
        this.size = 0;
        this.sent_size = 0;
        this.rval = 0;
    }

    public int getFetch_size() {
        return this.fetch_size;
    }

    public String getMd5sum() {
        return this.md5sum;
    }

    public int getMsg_id() {
        return this.msg_id;
    }

    public int getOffset() {
        return this.offset;
    }

    public String[] getOptions() {
        return this.options;
    }

    public String getParam() {
        return this.param;
    }

    public String getRem_size() {
        return this.rem_size;
    }

    public int getRval() {
        return this.rval;
    }

    public long getSent_size() {
        return this.sent_size;
    }

    public long getSize() {
        return this.size;
    }

    public int getToken() {
        return this.token;
    }

    public String getType() {
        return this.type;
    }

    public void setFetch_size(int i) {
        this.fetch_size = i;
    }

    public void setMd5sum(String str) {
        this.md5sum = str;
    }

    public void setMsg_id(int i) {
        this.msg_id = i;
    }

    public void setOffset(int i) {
        this.offset = i;
    }

    public void setOptions(String[] strArr) {
        this.options = strArr;
    }

    public void setParam(String str) {
        this.param = str;
    }

    public void setRem_size(String str) {
        this.rem_size = str;
    }

    public void setRval(int i) {
        this.rval = i;
    }

    public void setSent_size(long j) {
        this.sent_size = j;
    }

    public void setSize(long j) {
        this.size = j;
    }

    public void setToken(int i) {
        this.token = i;
    }

    public void setType(String str) {
        this.type = str;
    }
}
