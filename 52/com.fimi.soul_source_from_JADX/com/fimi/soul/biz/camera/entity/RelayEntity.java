package com.fimi.soul.biz.camera.entity;

public class RelayEntity {
    private String device_type;
    private int firmupg_finished;
    private String hw_version;
    private boolean isConnectCamera;
    private int quality;
    private String sw_version;

    public String getDevice_type() {
        return this.device_type;
    }

    public int getFirmupg_finished() {
        return this.firmupg_finished;
    }

    public String getHw_version() {
        return this.hw_version;
    }

    public int getQuality() {
        return this.quality;
    }

    public String getSw_version() {
        return this.sw_version;
    }

    public boolean isConnectCamera() {
        return this.isConnectCamera;
    }

    public void setDevice_type(String str) {
        this.device_type = str;
    }

    public void setFirmupg_finished(int i) {
        this.firmupg_finished = i;
    }

    public void setHw_version(String str) {
        this.hw_version = str;
    }

    public void setIsConnectCamera(boolean z) {
        this.isConnectCamera = z;
    }

    public void setQuality(int i) {
        this.quality = i;
    }

    public void setSw_version(String str) {
        this.sw_version = str;
    }
}
