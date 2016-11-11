package com.xiaomi.mistatistic.sdk.controller;

import android.util.Log;

/* renamed from: com.xiaomi.mistatistic.sdk.controller.o */
public class C2601o {
    private static boolean f12974a;

    static {
        f12974a = false;
    }

    public static void m14768a() {
        f12974a = true;
    }

    public void m14769a(String str) {
        if (f12974a) {
            Log.v("MI_STAT", str);
        }
    }

    public void m14770a(String str, Throwable th) {
        if (f12974a) {
            Log.e("MI_STAT", str, th);
        }
    }
}
