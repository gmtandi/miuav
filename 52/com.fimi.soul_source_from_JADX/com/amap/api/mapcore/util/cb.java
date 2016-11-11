package com.amap.api.mapcore.util;

import android.content.Context;
import java.lang.Thread.UncaughtExceptionHandler;

public class cb {
    protected static cb f2306a;
    protected UncaughtExceptionHandler f2307b;
    protected boolean f2308c;

    public cb() {
        this.f2308c = true;
    }

    public static void m3809a(Throwable th, String str, String str2) {
        th.printStackTrace();
        if (f2306a != null) {
            f2306a.m3811a(th, 1, str, str2);
        }
    }

    protected void m3810a(Context context, bv bvVar, boolean z) {
    }

    protected void m3811a(Throwable th, int i, String str, String str2) {
    }
}
