package com.fimi.soul.entity;

public class FdsMsg {
    private String bucketName;
    private String filePath;
    private String objectName;
    private long total;
    private long transferred;
    private String url;

    public String getBucketName() {
        return this.bucketName;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public String getObjectName() {
        return this.objectName;
    }

    public long getTotal() {
        return this.total;
    }

    public long getTransferred() {
        return this.transferred;
    }

    public String getUrl() {
        return this.url;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public void setFilePath(String str) {
        this.filePath = str;
    }

    public void setObjectName(String str) {
        this.objectName = str;
    }

    public void setTotal(long j) {
        this.total = j;
    }

    public void setTransferred(long j) {
        this.transferred = j;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
