package com.fimi.soul.entity;

import java.io.Serializable;

public class UpdateDroneItem implements Serializable {
    private double distance;
    private String endddata;
    private String fcType;
    private int flag;
    private double latitude;
    private double longitude;
    private double maxhight;
    private int month;
    private String planeID;
    private String record_time;
    private double sportTime;
    private String uploadurl;
    private long user_id;
    private int year;

    public double getDistance() {
        return this.distance;
    }

    public String getEndddata() {
        return this.endddata;
    }

    public String getFcType() {
        return this.fcType;
    }

    public int getFlag() {
        return this.flag;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public double getMaxhight() {
        return this.maxhight;
    }

    public int getMonth() {
        return this.month;
    }

    public String getPlaneID() {
        return this.planeID;
    }

    public String getRecord_time() {
        return this.record_time;
    }

    public double getSportTime() {
        return this.sportTime;
    }

    public String getUploadurl() {
        return this.uploadurl;
    }

    public long getUser_id() {
        return this.user_id;
    }

    public int getYear() {
        return this.year;
    }

    public void setDistance(double d) {
        this.distance = d;
    }

    public void setEndddata(String str) {
        this.endddata = str;
    }

    public void setFcType(String str) {
        this.fcType = str;
    }

    public void setFlag(int i) {
        this.flag = i;
    }

    public void setLatitude(double d) {
        this.latitude = d;
    }

    public void setLongitude(double d) {
        this.longitude = d;
    }

    public void setMaxhight(double d) {
        this.maxhight = d;
    }

    public void setMonth(int i) {
        this.month = i;
    }

    public void setPlaneID(String str) {
        this.planeID = str;
    }

    public void setRecord_time(String str) {
        this.record_time = str;
    }

    public void setSportTime(double d) {
        this.sportTime = d;
    }

    public void setUploadurl(String str) {
        this.uploadurl = str;
    }

    public void setUser_id(long j) {
        this.user_id = j;
    }

    public void setYear(int i) {
        this.year = i;
    }

    public String toString() {
        return "UpdateDroneItem [planeID=" + this.planeID + ", record_time=" + this.record_time + ", user_id=" + this.user_id + ", year=" + this.year + ", month=" + this.month + ", distance=" + this.distance + ", sportTime=" + this.sportTime + ", maxhight=" + this.maxhight + ", endddata=" + this.endddata + ", uploadurl=" + this.uploadurl + ", flag=" + this.flag + "]";
    }
}
