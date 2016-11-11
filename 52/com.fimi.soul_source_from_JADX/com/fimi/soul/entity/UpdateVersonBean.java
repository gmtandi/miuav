package com.fimi.soul.entity;

import java.io.Serializable;

public class UpdateVersonBean implements Serializable {
    public static final String SP_CAMERA_VERSION = "sp_camera_version";
    public static final String SP_NEED_DOWN_FIRMWWARES = "sp_need_down_firmwares";
    public static final String SP_RELAY_VERSION = "sp_relay_version";
    public static final int SYSID_AP_CONTROL = 5;
    public static final int SYSID_CAMERRA = 4;
    public static final int SYSID_CLOUD_CONTROL = 3;
    public static final int SYSID_FLY_CONTROL = 0;
    public static final int SYSID_HAND_CONTROL = 1;
    public static final int SYSID_RECEIVER_CONTROL = 2;
    public static final int SYSID_RELAY = 11;
    public static final int SYS_CLOUD_X = 6;
    public static final int SYS_LIGHT_STREAM = 9;
    public static final int SYS_SAFETY_ZONE = 10;
    public static final String UPGRADE_FORCE = "1";
    public static final String UPGRADE_UNFORCE = "0";
    private int adminid;
    private String binKey;
    private String fieldNames;
    private String fileEncode;
    private String forceSign;
    private int id;
    private String modelName;
    private String newVersion;
    private int pk;
    private String preversion;
    private String size;
    private String status;
    private int sysid;
    private String sysname;
    private String updcontents;
    private String uploadTime;
    private String url;

    public int getAdminid() {
        return this.adminid;
    }

    public String getBinKey() {
        return this.binKey;
    }

    public String getFieldNames() {
        return this.fieldNames;
    }

    public String getFileEncode() {
        return this.fileEncode;
    }

    public String getForceSign() {
        return this.forceSign;
    }

    public int getId() {
        return this.id;
    }

    public String getModelName() {
        return this.modelName;
    }

    public String getNewVersion() {
        return this.newVersion;
    }

    public int getPk() {
        return this.pk;
    }

    public String getPreversion() {
        return this.preversion;
    }

    public String getSize() {
        return this.size;
    }

    public String getStatus() {
        return this.status;
    }

    public int getSysid() {
        return this.sysid;
    }

    public String getSysname() {
        return this.sysname;
    }

    public String getUpdcontents() {
        return this.updcontents;
    }

    public String getUploadTime() {
        return this.uploadTime;
    }

    public String getUrl() {
        return this.url;
    }

    public void setAdminid(int i) {
        this.adminid = i;
    }

    public void setBinKey(String str) {
        this.binKey = str;
    }

    public void setFieldNames(String str) {
        this.fieldNames = str;
    }

    public void setFileEncode(String str) {
        this.fileEncode = str;
    }

    public void setForceSign(String str) {
        this.forceSign = str;
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setModelName(String str) {
        this.modelName = str;
    }

    public void setNewVersion(String str) {
        this.newVersion = str;
    }

    public void setPk(int i) {
        this.pk = i;
    }

    public void setPreversion(String str) {
        this.preversion = str;
    }

    public void setSize(String str) {
        this.size = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setSysid(int i) {
        this.sysid = i;
    }

    public void setSysname(String str) {
        this.sysname = str;
    }

    public void setUpdcontents(String str) {
        this.updcontents = str;
    }

    public void setUploadTime(String str) {
        this.uploadTime = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
