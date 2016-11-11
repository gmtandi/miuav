package com.tencent.open.weiyun;

public class WeiyunFile {
    private String f12150a;
    private String f12151b;
    private String f12152c;
    private long f12153d;

    public WeiyunFile(String str, String str2, String str3, long j) {
        this.f12150a = str;
        this.f12151b = str2;
        this.f12152c = str3;
        this.f12153d = j;
    }

    public String getCreateTime() {
        return this.f12152c;
    }

    public String getFileId() {
        return this.f12150a;
    }

    public String getFileName() {
        return this.f12151b;
    }

    public long getFileSize() {
        return this.f12153d;
    }

    public void setCreateTime(String str) {
        this.f12152c = str;
    }

    public void setFileId(String str) {
        this.f12150a = str;
    }

    public void setFileName(String str) {
        this.f12151b = str;
    }

    public void setFileSize(long j) {
        this.f12153d = j;
    }
}
