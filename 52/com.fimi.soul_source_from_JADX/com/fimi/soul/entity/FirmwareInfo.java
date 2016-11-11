package com.fimi.soul.entity;

import java.io.Serializable;

public class FirmwareInfo implements Serializable {
    public static final int SC_DOWN_UPGRADE = 1;
    private String fileEncode;
    private String fileName;
    private String inputTime;
    private int isLowVersion;
    private Boolean isUpgradeSuccess;
    private int model;
    private int sort;
    private int source;
    private String status;
    private int sysId;
    private String sysName;
    private int updateTime;
    private String updcontents;
    private String version;

    public FirmwareInfo() {
        this.isUpgradeSuccess = Boolean.valueOf(false);
    }

    public FirmwareInfo(int i, int i2, String str, String str2, String str3, String str4, int i3, int i4, String str5) {
        this.isUpgradeSuccess = Boolean.valueOf(false);
        this.sysId = i;
        this.model = i2;
        this.sysName = str;
        this.status = str2;
        this.inputTime = str3;
        this.version = str4;
        this.isLowVersion = i3;
        this.sort = i4;
        this.fileName = str5;
    }

    public String getFileEncode() {
        return this.fileEncode;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getInputTime() {
        return this.inputTime;
    }

    public int getIsLowVersion() {
        return this.isLowVersion;
    }

    public int getModel() {
        return this.model;
    }

    public int getSort() {
        return this.sort;
    }

    public int getSource() {
        return this.source;
    }

    public String getStatus() {
        return this.status;
    }

    public int getSysId() {
        return this.sysId;
    }

    public String getSysName() {
        return this.sysName;
    }

    public int getUpdateTime() {
        return this.updateTime;
    }

    public String getUpdcontents() {
        return this.updcontents;
    }

    public String getVersion() {
        return this.version;
    }

    public Boolean isUpgradeSuccess() {
        return this.isUpgradeSuccess;
    }

    public void setFileEncode(String str) {
        this.fileEncode = str;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public void setInputTime(String str) {
        this.inputTime = str;
    }

    public void setIsLowVersion(int i) {
        this.isLowVersion = i;
    }

    public void setIsUpgradeSuccess(Boolean bool) {
        this.isUpgradeSuccess = bool;
    }

    public void setModel(int i) {
        this.model = i;
    }

    public void setSort(int i) {
        this.sort = i;
    }

    public void setSource(int i) {
        this.source = i;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setSysId(int i) {
        this.sysId = i;
    }

    public void setSysName(String str) {
        this.sysName = str;
    }

    public void setUpdateTime(int i) {
        this.updateTime = i;
    }

    public void setUpdcontents(String str) {
        this.updcontents = str;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String toString() {
        return "FirmwareInfo{sysId=" + this.sysId + ", model=" + this.model + ", sysName='" + this.sysName + '\'' + ", status='" + this.status + '\'' + ", inputTime='" + this.inputTime + '\'' + ", version='" + this.version + '\'' + ", isLowVersion=" + this.isLowVersion + ", sort=" + this.sort + ", fileName='" + this.fileName + '\'' + '}';
    }
}
