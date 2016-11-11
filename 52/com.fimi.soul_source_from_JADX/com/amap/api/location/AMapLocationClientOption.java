package com.amap.api.location;

import com.p016a.ev;

public class AMapLocationClientOption implements Cloneable {
    private long f1377a;
    private long f1378b;
    private boolean f1379c;
    private boolean f1380d;
    private boolean f1381e;
    private boolean f1382f;
    private AMapLocationMode f1383g;
    private boolean f1384h;
    private boolean f1385i;
    private boolean f1386j;

    public enum AMapLocationMode {
        Battery_Saving,
        Device_Sensors,
        Hight_Accuracy
    }

    public AMapLocationClientOption() {
        this.f1377a = 2000;
        this.f1378b = (long) ev.f1151j;
        this.f1379c = false;
        this.f1380d = false;
        this.f1381e = true;
        this.f1382f = true;
        this.f1383g = AMapLocationMode.Hight_Accuracy;
        this.f1384h = false;
        this.f1385i = false;
        this.f1386j = true;
    }

    private AMapLocationClientOption m2072a(AMapLocationClientOption aMapLocationClientOption) {
        this.f1377a = aMapLocationClientOption.f1377a;
        this.f1379c = aMapLocationClientOption.f1379c;
        this.f1383g = aMapLocationClientOption.f1383g;
        this.f1380d = aMapLocationClientOption.f1380d;
        this.f1384h = aMapLocationClientOption.f1384h;
        this.f1385i = aMapLocationClientOption.f1385i;
        this.f1381e = aMapLocationClientOption.f1381e;
        this.f1382f = aMapLocationClientOption.f1382f;
        this.f1378b = aMapLocationClientOption.f1378b;
        return this;
    }

    public AMapLocationClientOption clone() {
        return new AMapLocationClientOption().m2072a(this);
    }

    public long getHttpTimeOut() {
        return this.f1378b;
    }

    public long getInterval() {
        return this.f1377a;
    }

    public AMapLocationMode getLocationMode() {
        return this.f1383g;
    }

    public boolean isGpsFirst() {
        return this.f1385i;
    }

    public boolean isKillProcess() {
        return this.f1384h;
    }

    public boolean isMockEnable() {
        return this.f1380d;
    }

    public boolean isNeedAddress() {
        return this.f1381e;
    }

    public boolean isOffset() {
        return this.f1386j;
    }

    public boolean isOnceLocation() {
        return this.f1379c;
    }

    public boolean isWifiActiveScan() {
        return this.f1382f;
    }

    public AMapLocationClientOption setGpsFirst(boolean z) {
        this.f1385i = z;
        return this;
    }

    public void setHttpTimeOut(long j) {
        this.f1378b = j;
    }

    public AMapLocationClientOption setInterval(long j) {
        if (j < 1000) {
            j = 1000;
        }
        this.f1377a = j;
        return this;
    }

    public AMapLocationClientOption setKillProcess(boolean z) {
        this.f1384h = z;
        return this;
    }

    public AMapLocationClientOption setLocationMode(AMapLocationMode aMapLocationMode) {
        this.f1383g = aMapLocationMode;
        return this;
    }

    public void setMockEnable(boolean z) {
        this.f1380d = z;
    }

    public AMapLocationClientOption setNeedAddress(boolean z) {
        this.f1381e = z;
        return this;
    }

    public AMapLocationClientOption setOffset(boolean z) {
        this.f1386j = z;
        return this;
    }

    public AMapLocationClientOption setOnceLocation(boolean z) {
        this.f1379c = z;
        return this;
    }

    public void setWifiActiveScan(boolean z) {
        this.f1382f = z;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("interval:").append(String.valueOf(this.f1377a)).append("#");
        stringBuilder.append("isOnceLocation:").append(String.valueOf(this.f1379c)).append("#");
        stringBuilder.append("locationMode:").append(String.valueOf(this.f1383g)).append("#");
        stringBuilder.append("isMockEnable:").append(String.valueOf(this.f1380d)).append("#");
        stringBuilder.append("isKillProcess:").append(String.valueOf(this.f1384h)).append("#");
        stringBuilder.append("isGpsFirst:").append(String.valueOf(this.f1385i)).append("#");
        stringBuilder.append("isNeedAddress:").append(String.valueOf(this.f1381e)).append("#");
        stringBuilder.append("isWifiActiveScan:").append(String.valueOf(this.f1382f)).append("#");
        stringBuilder.append("httpTimeOut:").append(String.valueOf(this.f1378b)).append("#");
        stringBuilder.append("isOffset:").append(String.valueOf(this.f1386j));
        return stringBuilder.toString();
    }
}
