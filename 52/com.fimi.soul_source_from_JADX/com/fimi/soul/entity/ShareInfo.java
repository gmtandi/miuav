package com.fimi.soul.entity;

import android.graphics.Bitmap;
import java.io.Serializable;

public class ShareInfo implements Serializable {
    private static final long serialVersionUID = 1;
    private String appName;
    private String description;
    private Bitmap thumbData;
    private String title;
    private String url;
    private String webpageUrl;

    public String getAppName() {
        return this.appName;
    }

    public String getDescription() {
        return this.description;
    }

    public Bitmap getThumbData() {
        return this.thumbData;
    }

    public String getTitle() {
        return this.title;
    }

    public String getUrl() {
        return this.url;
    }

    public String getWebpageUrl() {
        return this.webpageUrl;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setThumbData(Bitmap bitmap) {
        this.thumbData = bitmap;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void setWebpageUrl(String str) {
        this.webpageUrl = str;
    }
}
