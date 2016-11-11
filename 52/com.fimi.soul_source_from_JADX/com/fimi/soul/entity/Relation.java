package com.fimi.soul.entity;

import java.io.Serializable;

public class Relation implements Serializable {
    private static final long serialVersionUID = 1;
    private String flyTimes;
    private String nickName;
    private String relationID;
    private String signature;
    private String userID;
    private String userImgUrl;

    public String getFlyTimes() {
        return this.flyTimes;
    }

    public String getNickName() {
        return this.nickName;
    }

    public String getRelationID() {
        return this.relationID;
    }

    public String getSignature() {
        return this.signature;
    }

    public String getUserID() {
        return this.userID;
    }

    public String getUserImgUrl() {
        return this.userImgUrl;
    }

    public void setFlyTimes(String str) {
        this.flyTimes = str;
    }

    public void setNickName(String str) {
        this.nickName = str;
    }

    public void setRelationID(String str) {
        this.relationID = str;
    }

    public void setSignature(String str) {
        this.signature = str;
    }

    public void setUserID(String str) {
        this.userID = str;
    }

    public void setUserImgUrl(String str) {
        this.userImgUrl = str;
    }
}
