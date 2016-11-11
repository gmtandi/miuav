package com.fimi.soul.entity;

public class BatteryOverDischange {
    public String appType;
    public String batteryCurrent;
    public String batteryFour;
    public String batteryFull;
    public String batteryId;
    public String batteryLevel;
    public String batteryOne;
    public String batteryRecyle;
    public String batteryThree;
    public String batteryTwo;
    public String temperature;
    public String userId;
    public String version;
    public String voltage;

    public String getAppType() {
        return this.appType;
    }

    public String getBatteryCurrent() {
        return this.batteryCurrent;
    }

    public String getBatteryFour() {
        return this.batteryFour;
    }

    public String getBatteryFull() {
        return this.batteryFull;
    }

    public String getBatteryId() {
        return this.batteryId;
    }

    public String getBatteryLevel() {
        return this.batteryLevel;
    }

    public String getBatteryOne() {
        return this.batteryOne;
    }

    public String getBatteryRecyle() {
        return this.batteryRecyle;
    }

    public String getBatteryThree() {
        return this.batteryThree;
    }

    public String getBatteryTwo() {
        return this.batteryTwo;
    }

    public String getTemperature() {
        return this.temperature;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getVersion() {
        return this.version;
    }

    public String getVoltage() {
        return this.voltage;
    }

    public void setAppType(String str) {
        this.appType = str;
    }

    public void setBatteryCurrent(String str) {
        this.batteryCurrent = str;
    }

    public void setBatteryFour(String str) {
        this.batteryFour = str;
    }

    public void setBatteryFull(String str) {
        this.batteryFull = str;
    }

    public void setBatteryId(String str) {
        this.batteryId = str;
    }

    public void setBatteryLevel(String str) {
        this.batteryLevel = str;
    }

    public void setBatteryOne(String str) {
        this.batteryOne = str;
    }

    public void setBatteryOverDischange(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14) {
        this.userId = str;
        this.batteryId = str2;
        this.batteryLevel = str3;
        this.voltage = str4;
        this.batteryCurrent = str5;
        this.temperature = str6;
        this.batteryFull = str7;
        this.batteryOne = str8;
        this.batteryTwo = str9;
        this.batteryThree = str10;
        this.batteryFour = str11;
        this.batteryRecyle = str12;
        this.version = str13;
        this.appType = str14;
    }

    public void setBatteryRecyle(String str) {
        this.batteryRecyle = str;
    }

    public void setBatteryThree(String str) {
        this.batteryThree = str;
    }

    public void setBatteryTwo(String str) {
        this.batteryTwo = str;
    }

    public void setTemperature(String str) {
        this.temperature = str;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public void setVoltage(String str) {
        this.voltage = str;
    }
}
