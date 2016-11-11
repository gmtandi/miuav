package com.fimi.soul.entity;

import java.io.Serializable;

public class UpdateAllPlannerHistory implements Serializable {
    private String endDate;
    private String flyFileUrl;
    private double flyJourney;
    private double flyTime;
    private double flyTimeSum;
    private double maxHight;
    private int month;
    private String planeID;
    private String startDate;
    private int userID;
    private int year;

    public String getEndDate() {
        return this.endDate;
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

    public String getPlaneID() {
        return this.planeID;
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

    public void setPlaneID(String str) {
        this.planeID = str;
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
