package com.fimi.soul.entity;

import java.io.Serializable;

public class PageHomeIndexImage implements Serializable {
    private static final long serialVersionUID = 1;
    private String image;
    private String indeximg_switch;
    private String url;

    public String getImage() {
        return this.image;
    }

    public String getIndeximg_switch() {
        return this.indeximg_switch;
    }

    public String getUrl() {
        return this.url;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public void setIndeximg_switch(String str) {
        this.indeximg_switch = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
