package com.fimi.soul.biz.camera.entity;

import com.fimi.soul.biz.camera.C1314u;
import java.util.HashMap;
import java.util.Map;

public class X11SystemConfig {
    private String SDState;
    private String appStatus;
    private int batteryLevel;
    private String cameraTimeLock;
    private String captureMode;
    private int currentZoomInfo;
    private String dvVersion;
    private long freeKBSpace;
    private boolean isSaveLowResolution;
    private boolean isTimelapsePhoto;
    private boolean isTimelapseVideo;
    private boolean isVideoStamp;
    private int maxZoomInfo;
    private int photoFileNumbers;
    private String photoQuality;
    private String photoSize;
    private String photoStamp;
    private String powerSourceType;
    private int remainPhotoTakeNumbers;
    private int remainVideoRecordMinutes;
    private String streamOutType;
    private Map<String, X11SystemConfigOption> systemConfigOptions;
    private int totalFileNumbers;
    private long totalKBSpace;
    private int videoFileNumbers;
    private String videoLoopBack;
    private String videoMode;
    private String videoQuality;
    private String videoResolution;
    private String videoSrt;
    private String videoStamp;
    private String videoStandard;
    private X11DeviceInfo x11DeviceInfo;

    public X11SystemConfig() {
        this.freeKBSpace = 0;
        this.totalKBSpace = 0;
        this.currentZoomInfo = 0;
        this.isVideoStamp = false;
        this.dvVersion = null;
        this.systemConfigOptions = new HashMap();
        this.x11DeviceInfo = new X11DeviceInfo();
    }

    public String getAppStatus() {
        return this.appStatus;
    }

    public int getBatteryLevel() {
        return this.batteryLevel;
    }

    public String getCameraTimeLock() {
        return this.cameraTimeLock;
    }

    public String getCaptureMode() {
        return this.captureMode;
    }

    public int getCurrentZoomInfo() {
        return this.currentZoomInfo;
    }

    public String getDvVersion() {
        return this.dvVersion;
    }

    public long getFreeKBSpace() {
        return this.freeKBSpace;
    }

    public int getMaxZoomInfo() {
        return this.maxZoomInfo;
    }

    public int getPhotoFileNumbers() {
        return this.photoFileNumbers;
    }

    public String getPhotoQuality() {
        return this.photoQuality;
    }

    public String getPhotoSize() {
        return this.photoSize;
    }

    public String getPhotoStamp() {
        return this.photoStamp;
    }

    public String getPowerSourceType() {
        return this.powerSourceType;
    }

    public int getRemainPhotoTakeNumbers() {
        return this.remainPhotoTakeNumbers;
    }

    public int getRemainVideoRecordMinutes() {
        return this.remainVideoRecordMinutes;
    }

    public String getSDState() {
        return this.SDState;
    }

    public String getStreamOutType() {
        return this.streamOutType;
    }

    public Map<String, X11SystemConfigOption> getSystemConfigOptions() {
        return this.systemConfigOptions;
    }

    public int getTotalFileNumbers() {
        return this.totalFileNumbers;
    }

    public long getTotalKBSpace() {
        return this.totalKBSpace;
    }

    public int getVideoFileNumbers() {
        return this.videoFileNumbers;
    }

    public String getVideoLoopBack() {
        return this.videoLoopBack;
    }

    public String getVideoMode() {
        return this.videoMode;
    }

    public String getVideoQuality() {
        return this.videoQuality;
    }

    public String getVideoResolution() {
        return this.videoResolution;
    }

    public String getVideoSrt() {
        return this.videoSrt;
    }

    public String getVideoStamp() {
        return this.videoStamp;
    }

    public String getVideoStandard() {
        return this.videoStandard;
    }

    public X11DeviceInfo getX11DeviceInfo() {
        return this.x11DeviceInfo;
    }

    public String getX11DeviceName() {
        return this.x11DeviceInfo != null ? this.x11DeviceInfo.getChip() : C1314u.f5881h;
    }

    public boolean isContinueCaptureMode() {
        return this.captureMode != null && this.captureMode.contains(C1314u.cf);
    }

    public boolean isPrecisePhotoMode() {
        return this.captureMode != null && this.captureMode.contains(C1314u.cg);
    }

    public boolean isSaveLowResolution() {
        return this.isSaveLowResolution;
    }

    public boolean isTimelapsePhoto() {
        return this.isTimelapsePhoto;
    }

    public boolean isTimelapseVideo() {
        return this.isTimelapseVideo;
    }

    public boolean isVideoStamp() {
        return this.isVideoStamp;
    }

    public void setAppStatus(String str) {
        this.appStatus = str;
    }

    public void setBatteryLevel(int i) {
        this.batteryLevel = i;
    }

    public void setCameraTimeLock(String str) {
        this.cameraTimeLock = str;
    }

    public void setCaptureMode(String str) {
        this.captureMode = str;
    }

    public void setCurrentZoomInfo(int i) {
        this.currentZoomInfo = i;
    }

    public void setDvVersion(String str) {
        this.dvVersion = str;
    }

    public void setFreeKBSpace(long j) {
        this.freeKBSpace = j;
    }

    public void setMaxZoomInfo(int i) {
        this.maxZoomInfo = i;
    }

    public void setPhotoFileNumbers(int i) {
        this.photoFileNumbers = i;
    }

    public void setPhotoQuality(String str) {
        this.photoQuality = str;
    }

    public void setPhotoSize(String str) {
        this.photoSize = str;
    }

    public void setPhotoStamp(String str) {
        this.photoStamp = str;
    }

    public void setPowerSourceType(String str) {
        this.powerSourceType = str;
    }

    public void setRemainPhotoTakeNumbers(int i) {
        this.remainPhotoTakeNumbers = i;
    }

    public void setRemainVideoRecordMinutes(int i) {
        this.remainVideoRecordMinutes = i;
    }

    public void setSDState(String str) {
        this.SDState = str;
    }

    public void setSaveLowResolution(boolean z) {
        this.isSaveLowResolution = z;
    }

    public void setStreamOutType(String str) {
        this.streamOutType = str;
    }

    public void setSystemConfigOptions(Map<String, X11SystemConfigOption> map) {
        this.systemConfigOptions = map;
    }

    public void setTimelapsePhoto(boolean z) {
        this.isTimelapsePhoto = z;
    }

    public void setTimelapseVideo(boolean z) {
        this.isTimelapseVideo = z;
    }

    public void setTotalFileNumbers(int i) {
        this.totalFileNumbers = i;
    }

    public void setTotalKBSpace(long j) {
        this.totalKBSpace = j;
    }

    public void setVideoFileNumbers(int i) {
        this.videoFileNumbers = i;
    }

    public void setVideoLoopBack(String str) {
        this.videoLoopBack = str;
    }

    public void setVideoMode(String str) {
        this.videoMode = str;
    }

    public void setVideoQuality(String str) {
        this.videoQuality = str;
    }

    public void setVideoResolution(String str) {
        this.videoResolution = str;
    }

    public void setVideoSrt(String str) {
        this.videoSrt = str;
    }

    public void setVideoStamp(String str) {
        this.videoStamp = str;
    }

    public void setVideoStamp(boolean z) {
        this.isVideoStamp = z;
    }

    public void setVideoStandard(String str) {
        this.videoStandard = str;
    }

    public void setX11DeviceInfo(X11DeviceInfo x11DeviceInfo) {
        this.x11DeviceInfo = x11DeviceInfo;
    }
}
