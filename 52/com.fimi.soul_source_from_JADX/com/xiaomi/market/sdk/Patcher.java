package com.xiaomi.market.sdk;

import android.util.Log;

public class Patcher {
    private static final String TAG = "MarketPatcher";
    private static final String aX = "sdk_patcher_jni";

    public static int m14460a(String str, String str2, String str3) {
        return new Patcher().applyPatch(str, str2, str3);
    }

    public static boolean m14461o() {
        try {
            System.loadLibrary(aX);
            return true;
        } catch (Throwable th) {
            Log.e(TAG, "load patcher library failed : " + th.toString());
            return false;
        }
    }

    public native int applyPatch(String str, String str2, String str3);
}
