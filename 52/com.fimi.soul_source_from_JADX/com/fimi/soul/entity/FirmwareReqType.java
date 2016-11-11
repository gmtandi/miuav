package com.fimi.soul.entity;

import java.io.Serializable;

public class FirmwareReqType implements Serializable {
    private String deviceID;
    private int sysID;
    private String type;
    private String version;

    public String getDeviceID() {
        return this.deviceID;
    }

    public int getSysID() {
        return this.sysID;
    }

    public String getType() {
        return this.type;
    }

    public String getVersion() {
        return this.version;
    }

    public void setDeviceID(String str) {
        this.deviceID = str;
    }

    public void setSysID(int i) {
        this.sysID = i;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setVersion(String str) {
        this.version = str;
    }
}
