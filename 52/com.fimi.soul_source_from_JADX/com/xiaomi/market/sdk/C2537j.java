package com.xiaomi.market.sdk;

/* renamed from: com.xiaomi.market.sdk.j */
public class C2537j {
    public static final String HOST = "host";
    public static final String f12836T = "http://api.developer.xiaomi.com/autoupdate/";
    public static final String f12837U = "http://dev.staging.api.developer.n.xiaomi.com/autoupdate/";
    public static String f12838V = null;
    public static final String f12839W = "packageName";
    public static final String f12840X = "versionCode";
    public static final String f12841Y = "apkHash";
    public static final String f12842Z = "signature";
    public static final String aA = "apkSize";
    public static final String aB = "diffFile";
    public static final String aC = "diffFileHash";
    public static final String aD = "diffFileSize";
    public static final String aa = "clientId";
    public static final String ab = "sdk";
    public static final String ac = "os";
    public static final String ad = "la";
    public static final String ae = "co";
    public static final String af = "xiaomiSDKVersion";
    public static final String ag = "info";
    public static final String ah = "screenSize";
    public static final String ai = "resolution";
    public static final String aj = "density";
    public static final String ak = "touchScreen";
    public static final String al = "glEsVersion";
    public static final String am = "feature";
    public static final String an = "library";
    public static final String ao = "glExtension";
    public static final String ap = "sdk";
    public static final String aq = "version";
    public static final String ar = "release";
    public static final String as = "deviceId";
    public static final String at = "fitness";
    public static final String au = "source";
    public static final String av = "updateLog";
    public static final String aw = "versionCode";
    public static final String ax = "versionName";
    public static final String ay = "apk";
    public static final String az = "apkHash";

    static {
        f12838V = "http://api.developer.xiaomi.com/autoupdate/updateself";
    }

    public static void m14521k() {
        if (C2546s.bb) {
            f12838V = "http://dev.staging.api.developer.n.xiaomi.com/autoupdate/updateself";
        } else {
            f12838V = "http://api.developer.xiaomi.com/autoupdate/updateself";
        }
    }
}
