package com.tencent.stat;

import com.tencent.connect.common.Constants;
import com.tencent.stat.common.C2418k;
import com.tencent.stat.common.StatLogger;
import org.json.JSONObject;

public class DeviceInfo {
    public static final int NEW_USER = 0;
    public static final int OLD_USER = 1;
    public static final String TAG_ANDROID_ID = "aid";
    public static final String TAG_FLAG = "__MTA_DEVICE_INFO__";
    public static final String TAG_IMEI = "ui";
    public static final String TAG_MAC = "mc";
    public static final String TAG_MID = "mid";
    public static final String TAG_TIMESTAMPS = "ts";
    public static final String TAG_VERSION = "ver";
    public static final int UPGRADE_USER = 2;
    private static StatLogger f12178h;
    private String f12179a;
    private String f12180b;
    private String f12181c;
    private String f12182d;
    private int f12183e;
    private int f12184f;
    private long f12185g;

    static {
        f12178h = C2418k.m14018b();
    }

    DeviceInfo() {
        this.f12179a = null;
        this.f12180b = null;
        this.f12181c = null;
        this.f12182d = Constants.VIA_RESULT_SUCCESS;
        this.f12184f = NEW_USER;
        this.f12185g = 0;
    }

    DeviceInfo(String str, String str2, int i) {
        this.f12179a = null;
        this.f12180b = null;
        this.f12181c = null;
        this.f12182d = Constants.VIA_RESULT_SUCCESS;
        this.f12184f = NEW_USER;
        this.f12185g = 0;
        this.f12179a = str;
        this.f12180b = str2;
        this.f12183e = i;
    }

    static DeviceInfo m13884a(String str) {
        DeviceInfo deviceInfo = new DeviceInfo();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.isNull(TAG_IMEI)) {
                deviceInfo.m13894d(jSONObject.getString(TAG_IMEI));
            }
            if (!jSONObject.isNull(TAG_MAC)) {
                deviceInfo.m13895e(jSONObject.getString(TAG_MAC));
            }
            if (!jSONObject.isNull(TAG_MID)) {
                deviceInfo.m13893c(jSONObject.getString(TAG_MID));
            }
            if (!jSONObject.isNull(TAG_ANDROID_ID)) {
                deviceInfo.m13891b(jSONObject.getString(TAG_ANDROID_ID));
            }
            if (!jSONObject.isNull(TAG_TIMESTAMPS)) {
                deviceInfo.m13888a(jSONObject.getLong(TAG_TIMESTAMPS));
            }
            if (!jSONObject.isNull(TAG_VERSION)) {
                deviceInfo.m13887a(jSONObject.getInt(TAG_VERSION));
            }
        } catch (Exception e) {
            f12178h.m13977e(e);
        }
        return deviceInfo;
    }

    int m13885a() {
        return this.f12184f;
    }

    int m13886a(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            return OLD_USER;
        }
        String mid = getMid();
        String mid2 = deviceInfo.getMid();
        if (mid != null && mid2 != null && mid.equals(mid2)) {
            return NEW_USER;
        }
        int a = m13885a();
        int a2 = deviceInfo.m13885a();
        if (a > a2) {
            return OLD_USER;
        }
        if (a != a2) {
            return -1;
        }
        long b = m13889b();
        long b2 = deviceInfo.m13889b();
        return b <= b2 ? b == b2 ? NEW_USER : -1 : OLD_USER;
    }

    void m13887a(int i) {
        this.f12184f = i;
    }

    void m13888a(long j) {
        this.f12185g = j;
    }

    long m13889b() {
        return this.f12185g;
    }

    void m13890b(int i) {
        this.f12183e = i;
    }

    void m13891b(String str) {
        this.f12181c = str;
    }

    JSONObject m13892c() {
        JSONObject jSONObject = new JSONObject();
        try {
            C2418k.m14014a(jSONObject, TAG_IMEI, this.f12179a);
            C2418k.m14014a(jSONObject, TAG_MAC, this.f12180b);
            C2418k.m14014a(jSONObject, TAG_MID, this.f12182d);
            C2418k.m14014a(jSONObject, TAG_ANDROID_ID, this.f12181c);
            jSONObject.put(TAG_TIMESTAMPS, this.f12185g);
            jSONObject.put(TAG_VERSION, this.f12184f);
        } catch (Exception e) {
            f12178h.m13977e(e);
        }
        return jSONObject;
    }

    void m13893c(String str) {
        this.f12182d = str;
    }

    void m13894d(String str) {
        this.f12179a = str;
    }

    void m13895e(String str) {
        this.f12180b = str;
    }

    public String getImei() {
        return this.f12179a;
    }

    public String getMac() {
        return this.f12180b;
    }

    public String getMid() {
        return this.f12182d;
    }

    public int getUserType() {
        return this.f12183e;
    }

    public String toString() {
        return m13892c().toString();
    }
}
