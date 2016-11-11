package com.fimi.soul.entity;

import java.io.Serializable;

public class UpdatePlannerBackdata implements Serializable {
    private String endDate;
    private String fieldNames;
    private String flyFileUrl;
    private double flyJourney;
    private double flyTime;
    private double flyTimeSum;
    private double maxHight;
    private int month;
    private int pk;
    private String planeID;
    private int planeactionID;
    private String startDate;
    private int userID;
    private int year;

    public String getEndDate() {
        return this.endDate;
    }

    public String getFieldNames() {
        return this.fieldNames;
    }

    public String getFlyFileUrl() {
        return this.flyFileUrl;
    }

    public double getFlyJourney() {
        return this.flyJourney;
    }

    public double getFlyTime() {
        return this.flyTime;
    }

    public double getFlyTimeSum() {
        return this.flyTimeSum;
    }

    public double getMaxHight() {
        return this.maxHight;
    }

    public int getMonth() {
        return this.month;
    }

    public int getPk() {
        return this.pk;
    }

    public String getPlaneID() {
        return this.planeID;
    }

    public int getPlaneactionID() {
        return this.planeactionID;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public int getUserID() {
        return this.userID;
    }

    public int getYear() {
        return this.year;
    }

    public void setEndDate(String str) {
        this.endDate = str;
    }

    public void setFieldNames(String str) {
        this.fieldNames = str;
    }

    public void setFlyFileUrl(String str) {
        this.flyFileUrl = str;
    }

    public void setFlyJourney(double d) {
        this.flyJourney = d;
    }

    public void setFlyTime(double d) {
        this.flyTime = d;
    }

    public void setFlyTimeSum(double d) {
        this.flyTimeSum = d;
    }

    public void setMaxHight(double d) {
        this.maxHight = d;
    }

    public void setMonth(int i) {
        this.month = i;
    }

    public void setPk(int i) {
        this.pk = i;
    }

    public void setPlaneID(String str) {
        this.planeID = str;
    }

    public void setPlaneactionID(int i) {
        this.planeactionID = i;
    }

    public void setStartDate(String str) {
        this.startDate = str;
    }

    public void setUserID(int i) {
        this.userID = i;
    }

    public void setYear(int i) {
        this.year = i;
    }
}
