package com.fimi.soul.entity;

public class User extends BaseModel {
    public static final String FN_NAME = "name";
    private String country;
    private String curLatitude;
    private String curLongitude;
    private String device;
    private String name;
    private String nickName;
    private String objectName;
    private String phone;
    private String sex;
    private String signature;
    private String userID;
    private String userIDs;
    private String userImgUrl;
    private String xiaomiID;

    public User(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        this.nickName = str;
        this.sex = str2;
        this.country = str3;
        this.signature = str4;
        this.xiaomiID = str5;
        this.curLongitude = str6;
        this.curLatitude = str7;
        this.device = str8;
        this.name = str9;
        this.userIDs = str10;
        this.phone = str11;
    }

    public String getCountry() {
        return this.country;
    }

    public String getCurLatitude() {
        return this.curLatitude;
    }

    public String getCurLongitude() {
        return this.curLongitude;
    }

    public String getDevice() {
        return this.device;
    }

    public String getName() {
        return this.name;
    }

    public String getNickName() {
        return this.nickName;
    }

    public String getObjectName() {
        return this.objectName;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getSex() {
        return this.sex;
    }

    public String getSignature() {
        return this.signature;
    }

    public String getUserID() {
        return this.userID;
    }

    public String getUserIDs() {
        return this.userIDs;
    }

    public String getUserImgUrl() {
        return this.userImgUrl;
    }

    public String getXiaomiID() {
        return this.xiaomiID;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public void setCurLatitude(String str) {
        this.curLatitude = str;
    }

    public void setCurLongitude(String str) {
        this.curLongitude = str;
    }

    public void setDevice(String str) {
        this.device = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setNickName(String str) {
        this.nickName = str;
    }

    public void setObjectName(String str) {
        this.objectName = str;
    }

    public void setPhone(String str) {
        this.phone = str;
    }

    public void setSex(String str) {
        this.sex = str;
    }

    public void setSignature(String str) {
        this.signature = str;
    }

    public void setUserID(String str) {
        this.userID = str;
    }

    public void setUserIDs(String str) {
        this.userIDs = str;
    }

    public void setUserImgUrl(String str) {
        this.userImgUrl = str;
    }

    public void setXiaomiID(String str) {
        this.xiaomiID = str;
    }
}
