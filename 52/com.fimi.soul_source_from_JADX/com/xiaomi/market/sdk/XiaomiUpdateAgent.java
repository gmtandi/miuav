package com.xiaomi.market.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import java.io.File;

public class XiaomiUpdateAgent {
    private static final String TAG = "MarketUpdateAgent";
    private static C2550w aK;
    private static C2544q aL;
    private static boolean be;
    private static boolean bf;
    private static boolean bg;
    private static XiaomiUpdateListener bh;
    private static Context mContext;

    static {
        be = true;
        bf = false;
        bg = false;
    }

    static Context getContext() {
        return mContext;
    }

    public static int getSDKVersion() {
        return 0;
    }

    static C2544q m14466p(Context context) {
        PackageInfo packageInfo;
        C2544q h = C2544q.m14541h(context.getPackageName());
        PackageManager packageManager = context.getPackageManager();
        try {
            packageInfo = packageManager.getPackageInfo(h.packageName, 64);
        } catch (NameNotFoundException e) {
            Log.e(TAG, "get package info failed");
            packageInfo = null;
        }
        if (packageInfo == null || packageInfo.applicationInfo == null) {
            return null;
        }
        h.aT = packageManager.getApplicationLabel(packageInfo.applicationInfo).toString();
        h.versionCode = packageInfo.versionCode;
        h.versionName = packageInfo.versionName;
        h.aU = C2529b.m14487a(String.valueOf(packageInfo.signatures[0].toChars()));
        h.sourceDir = packageInfo.applicationInfo.sourceDir;
        h.aV = C2529b.m14486a(new File(h.sourceDir));
        return h;
    }

    public static void setCheckUpdateOnlyWifi(boolean z) {
        bf = z;
    }

    public static void setUpdateAutoPopup(boolean z) {
        be = z;
    }

    public static void setUpdateListener(XiaomiUpdateListener xiaomiUpdateListener) {
        bh = xiaomiUpdateListener;
    }

    public static void update(Context context) {
        if (context != null) {
            mContext = context;
            C2537j.m14521k();
            new C2547t().execute(new Void[0]);
        }
    }
}
