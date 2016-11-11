package com.fimi.soul.entity;

import java.io.Serializable;

public class Describehistor implements Serializable {
    private String name;
    private double sumTime;
    private String userID;
    private String userImgUrl;

    public String getName() {
        return this.name;
    }

    public double getSumTime() {
        return this.sumTime;
    }

    public String getUserID() {
        return this.userID;
    }

    public String getUserImgUrl() {
        return this.userImgUrl;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setSumTime(double d) {
        this.sumTime = d;
    }

    public void setUserID(String str) {
        this.userID = str;
    }

    public void setUserImgUrl(String str) {
        this.userImgUrl = str;
    }
}
