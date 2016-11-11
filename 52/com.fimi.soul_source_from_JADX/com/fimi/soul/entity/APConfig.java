package com.fimi.soul.entity;

public class APConfig {
    private String apPwd;
    private String conCameraStatus;
    private String countryCode;
    private String ipAddr;
    private String power;
    private String primaryChannel;
    private int signalInfo;
    private String ssid;
    private String version;

    public String getApPwd() {
        return this.apPwd;
    }

    public String getConCameraStatus() {
        return this.conCameraStatus;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getIpAddr() {
        return this.ipAddr;
    }

    public String getPower() {
        return this.power;
    }

    public String getPrimaryChannel() {
        return this.primaryChannel;
    }

    public int getSignalInfo() {
        return this.signalInfo;
    }

    public String getSsid() {
        return this.ssid;
    }

    public String getVersion() {
        return this.version;
    }

    public void setApPwd(String str) {
        this.apPwd = str;
    }

    public void setConCameraStatus(String str) {
        this.conCameraStatus = str;
    }

    public void setCountryCode(String str) {
        this.countryCode = str;
    }

    public void setIpAddr(String str) {
        this.ipAddr = str;
    }

    public void setPower(String str) {
        this.power = str;
    }

    public void setPrimaryChannel(String str) {
        this.primaryChannel = str;
    }

    public void setSignalInfo(int i) {
        this.signalInfo = i;
    }

    public void setSsid(String str) {
        this.ssid = str;
    }

    public void setVersion(String str) {
        this.version = str;
    }
}
