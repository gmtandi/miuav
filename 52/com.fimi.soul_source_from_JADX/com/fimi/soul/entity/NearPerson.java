package com.fimi.soul.entity;

import java.io.Serializable;

public class NearPerson implements Serializable {
    private String curLatitude;
    private String curLongitude;
    private String distance;
    private String nickName;
    private String timeDifference;
    private String userID;
    private String userImgUrl;

    public String getCurLatitude() {
        return this.curLatitude;
    }

    public String getCurLongitude() {
        return this.curLongitude;
    }

    public String getDistance() {
        return this.distance;
    }

    public String getNickName() {
        return this.nickName;
    }

    public String getTimeDifference() {
        return this.timeDifference;
    }

    public String getUserID() {
        return this.userID;
    }

    public String getUserImgUrl() {
        return this.userImgUrl;
    }

    public void setCurLatitude(String str) {
        this.curLatitude = str;
    }

    public void setCurLongitude(String str) {
        this.curLongitude = str;
    }

    public void setDistance(String str) {
        this.distance = str;
    }

    public void setNickName(String str) {
        this.nickName = str;
    }

    public void setTimeDifference(String str) {
        this.timeDifference = str;
    }

    public void setUserID(String str) {
        this.userID = str;
    }

    public void setUserImgUrl(String str) {
        this.userImgUrl = str;
    }
}
