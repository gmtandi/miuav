package com.fimi.soul.entity;

import java.io.Serializable;

public class AppVersion implements Serializable {
    private String apkUrl;
    private String newVersion;
    private String updcontents;
    private String uploadTime;

    public String getApkUrl() {
        return this.apkUrl;
    }

    public String getNewVersion() {
        return this.newVersion;
    }

    public String getUpdcontents() {
        return this.updcontents;
    }

    public String getUploadTime() {
        return this.uploadTime;
    }

    public void setApkUrl(String str) {
        this.apkUrl = str;
    }

    public void setNewVersion(String str) {
        this.newVersion = str;
    }

    public void setUpdcontents(String str) {
        this.updcontents = str;
    }

    public void setUploadTime(String str) {
        this.uploadTime = str;
    }
}
