package com.fimi.soul.biz.camera.entity;

import com.fimi.soul.biz.camera.C1314u;
import com.fimi.soul.biz.p096d.C1316b;
import com.fimi.soul.utils.ba;
import java.util.Map;

public class X11ApWifiConfig {
    private int ApChannel;
    private String ApESSID;
    private String ApIP;
    private String ApSSID;

    public X11ApWifiConfig() {
        this.ApSSID = "AMBA";
        this.ApIP = C1314u.f5874a;
        this.ApESSID = "amba_TEST";
        this.ApChannel = 4;
    }

    public static X11ApWifiConfig build(String str) {
        X11ApWifiConfig x11ApWifiConfig = new X11ApWifiConfig();
        try {
            Map a = ba.m12314a(str);
            x11ApWifiConfig.setApSSID((String) a.get(C1316b.f5907b));
            x11ApWifiConfig.setApIP((String) a.get("LOCAL_IP"));
            x11ApWifiConfig.setApChannel(Integer.parseInt((String) a.get("AP_CHANNEL")));
        } catch (Exception e) {
        }
        return x11ApWifiConfig;
    }

    public int getApChannel() {
        return this.ApChannel;
    }

    public String getApESSID() {
        return this.ApESSID;
    }

    public String getApIP() {
        return this.ApIP;
    }

    public String getApSSID() {
        return this.ApSSID;
    }

    public void setApChannel(int i) {
        this.ApChannel = i;
    }

    public void setApESSID(String str) {
        this.ApESSID = str;
    }

    public void setApIP(String str) {
        this.ApIP = str;
    }

    public void setApSSID(String str) {
        this.ApSSID = str;
    }
}
