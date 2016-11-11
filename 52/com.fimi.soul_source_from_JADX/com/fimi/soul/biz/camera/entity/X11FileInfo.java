package com.fimi.soul.biz.camera.entity;

import com.fimi.soul.biz.camera.C1314u;

public class X11FileInfo {
    public static final String FILE_TYPE_JPG = ".jpg";
    public static final String FILE_TYPE_MP4 = ".MP4";
    private String absolutePath;
    private String createDate;
    private String localPath;
    private String md5;
    private String name;
    private int offset;
    private String remotePath;
    private long size;

    public String getAbsolutePath() {
        return this.absolutePath;
    }

    public String getCreateDate() {
        return this.createDate;
    }

    public String getHttpThmUrl() {
        if (this.remotePath.indexOf(".") > 0) {
            String.format("http://%s/%s", new Object[]{C1314u.f5874a, this.remotePath.substring(0, this.remotePath.indexOf(".")) + "_THM.MP4"});
        }
        return null;
    }

    public String getHttpUrl() {
        return String.format("http://%s/%s", new Object[]{C1314u.f5874a, this.remotePath});
    }

    public String getLocalPath() {
        return this.localPath;
    }

    public String getMd5() {
        return this.md5;
    }

    public String getName() {
        return this.name;
    }

    public int getOffset() {
        return this.offset;
    }

    public String getRemotePath() {
        return this.remotePath;
    }

    public long getSize() {
        return this.size;
    }

    public void setAbsolutePath(String str) {
        this.absolutePath = str;
    }

    public void setCreateDate(String str) {
        this.createDate = str;
    }

    public void setLocalPath(String str) {
        this.localPath = str;
    }

    public void setMd5(String str) {
        this.md5 = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setOffset(int i) {
        this.offset = i;
    }

    public void setRemotePath(String str) {
        this.remotePath = str;
    }

    public void setSize(long j) {
        this.size = j;
    }
}
