package com.fimi.soul.entity;

public class DroneInfoBean {
    private String appSoftVersion;
    private String camerId;
    private int camerVersion;
    private int cloudSoftVersion;
    private int cloudXVersion;
    private String cloudXid;
    private String coulidId;
    private int flySoftVersion;
    private int lightStreamVersion;
    private int nosafeZoneVersion;
    private String phoneDevice;
    private String receiverId;
    private int receiverSoftVersion;
    private String relayId;
    private int relayVersion;
    private String remoteId;
    private int remoteSoftVersion;
    private String telePhoneId;

    public DroneInfoBean(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.flySoftVersion = i;
        this.remoteSoftVersion = i2;
        this.cloudSoftVersion = i3;
        this.camerVersion = i4;
        this.relayVersion = i5;
        this.cloudXVersion = i6;
        this.nosafeZoneVersion = i7;
    }

    public String getAppSoftVersion() {
        return this.appSoftVersion;
    }

    public String getCamerId() {
        return this.camerId;
    }

    public int getCamerVersion() {
        return this.camerVersion;
    }

    public int getCloudSoftVersion() {
        return this.cloudSoftVersion;
    }

    public int getCloudXVersion() {
        return this.cloudXVersion;
    }

    public String getCoulidId() {
        return this.coulidId;
    }

    public int getFlySoftVersion() {
        return this.flySoftVersion;
    }

    public int getLightStreamVersion() {
        return this.lightStreamVersion;
    }

    public int getNosafeZoneVersion() {
        return this.nosafeZoneVersion;
    }

    public String getPhoneDevice() {
        return this.phoneDevice;
    }

    public String getReceiverId() {
        return this.receiverId;
    }

    public int getReceiverSoftVersion() {
        return this.receiverSoftVersion;
    }

    public String getRelayId() {
        return this.relayId;
    }

    public int getRelayVersion() {
        return this.relayVersion;
    }

    public String getRemoteId() {
        return this.remoteId;
    }

    public int getRemoteSoftVersion() {
        return this.remoteSoftVersion;
    }

    public String getTelePhoneId() {
        return this.telePhoneId;
    }

    public boolean isNeedVersionAgain() {
        return this.flySoftVersion <= 0 || this.remoteSoftVersion <= 0 || this.cloudSoftVersion <= 0 || this.cloudXVersion <= 0 || this.nosafeZoneVersion <= 0 || this.lightStreamVersion <= 0;
    }

    public void setAppSoftVersion(String str) {
        this.appSoftVersion = str;
    }

    public void setCamerId(String str) {
        this.camerId = str;
    }

    public void setCamerVersion(int i) {
        this.camerVersion = i;
    }

    public void setCloudSoftVersion(int i) {
        this.cloudSoftVersion = i;
    }

    public void setCloudXVersion(int i) {
        this.cloudXVersion = i;
    }

    public void setCoulidId(String str) {
        this.coulidId = str;
    }

    public void setFlySoftVersion(int i) {
        this.flySoftVersion = i;
    }

    public void setLightStreamVersion(int i) {
        this.lightStreamVersion = i;
    }

    public void setNosafeZoneVersion(int i) {
        this.nosafeZoneVersion = i;
    }

    public void setPhoneDevice(String str) {
        this.phoneDevice = str;
    }

    public void setReceiverId(String str) {
        this.receiverId = str;
    }

    public void setReceiverSoftVersion(int i) {
        this.receiverSoftVersion = i;
    }

    public void setRelayId(String str) {
        this.relayId = str;
    }

    public void setRelayVersion(int i) {
        this.relayVersion = i;
    }

    public void setRemoteId(String str) {
        this.remoteId = str;
    }

    public void setRemoteSoftVersion(int i) {
        this.remoteSoftVersion = i;
    }

    public void setTelePhoneId(String str) {
        this.telePhoneId = str;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        if (this.remoteSoftVersion > 0) {
            stringBuffer.append("\u9065\u63a7\u5668\u903b\u8f91\u7248\u672c\uff1a" + this.remoteSoftVersion + "\n");
        } else {
            stringBuffer.append("\u9065\u63a7\u5668\u903b\u8f91\u7248\u672c\uff1a\u672a\u83b7\u53d6\u5230\u3002\n");
        }
        if (this.flySoftVersion > 0) {
            stringBuffer.append("\u98de\u63a7\u903b\u8f91\u7248\u672c\uff1a" + this.flySoftVersion + "\n");
        } else {
            stringBuffer.append("\u98de\u63a7\u903b\u8f91\u7248\u672c\uff1a\u672a\u83b7\u53d6\u5230\u3002\n");
        }
        if (this.cloudSoftVersion > 0) {
            stringBuffer.append("\u4e91\u53f0\u903b\u8f91\u7248\u672c\uff1a" + this.cloudSoftVersion + "\n");
        } else {
            stringBuffer.append("\u4e91\u53f0\u903b\u8f91\u7248\u672c\uff1a\u672a\u83b7\u53d6\u5230\u3002\n");
        }
        if (this.lightStreamVersion > 0) {
            stringBuffer.append("\u5149\u6d41\u7684\u903b\u8f91\u7248\u672c\uff1a" + this.lightStreamVersion + "\n");
        } else {
            stringBuffer.append("\u5149\u6d41\u903b\u8f91\u7248\u672c\uff1a\u672a\u83b7\u53d6\u5230\u3002\n");
        }
        if (this.nosafeZoneVersion > 0) {
            stringBuffer.append("\u7981\u98de\u533a\u903b\u8f91\u7248\u672c\uff1a" + this.nosafeZoneVersion + "\n");
        } else {
            stringBuffer.append("\u7981\u98de\u533a\u903b\u8f91\u7248\u672c\uff1a\u672a\u83b7\u53d6\u5230\n");
        }
        if (this.cloudXVersion > 0) {
            stringBuffer.append("\u4f3a\u670d\u903b\u8f91\u7248\u672c\uff1a" + this.cloudXVersion + "\n");
        } else {
            stringBuffer.append("\u4f3a\u670d\u903b\u8f91\u7248\u672c\uff1a\u672a\u83b7\u53d6\u5230\u3002\n");
        }
        if (this.relayVersion > 0) {
            stringBuffer.append("\u4e2d\u7ee7\u903b\u8f91\u7248\u672c\uff1a" + this.relayVersion + "\n");
        } else {
            stringBuffer.append("\u4e2d\u7ee7\u903b\u8f91\u7248\u672c:\u672a\u83b7\u53d6\u5230\u3002\n");
        }
        if (this.camerVersion > 0) {
            stringBuffer.append("\u76f8\u673a\u903b\u8f91\u7248\u672c\uff1a" + this.camerVersion + "\n");
        } else {
            stringBuffer.append("\u76f8\u673a\u903b\u8f91\u7248\u672c\uff1a\u672a\u83b7\u53d6\u5230\u3002\n");
        }
        return stringBuffer.toString();
    }
}
