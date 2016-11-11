package com.xiaomi.market.sdk;

/* renamed from: com.xiaomi.market.sdk.l */
public final class C2539l implements C2538k {
    public static final String PACKAGE_NAME = "package_name";
    public static final String aB = "diff_url";
    public static final String aC = "diff_hash";
    public static final String aE = "update_download";
    public static final String aF = "download_id";
    public static final String aG = "apk_path";
    public static final String aH = "CREATE TABLE update_download (_id INTEGER PRIMARY KEY AUTOINCREMENT,package_name TEXT,download_id INTEGER, version_code INTEGER, apk_url TEXT, apk_hash TEXT, diff_url TEXT, diff_hash TEXT, apk_path TEXT, UNIQUE(package_name));";
    public static final String[] aI;
    public static final String aw = "version_code";
    public static final String ay = "apk_url";
    public static final String az = "apk_hash";

    static {
        aI = new String[]{"update_download.package_name", "update_download.download_id", "update_download.version_code", "update_download.apk_url", "update_download.apk_hash", "update_download.diff_url", "update_download.diff_hash", "update_download.apk_path"};
    }
}
