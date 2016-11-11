package com.p016a;

import android.content.Context;
import android.text.TextUtils;
import java.lang.Thread.UncaughtExceptionHandler;

/* renamed from: com.a.as */
public class as implements UncaughtExceptionHandler {
    private static as f569a;
    private UncaughtExceptionHandler f570b;
    private Context f571c;
    private gd f572d;

    private as(Context context, gd gdVar) {
        this.f571c = context.getApplicationContext();
        this.f572d = gdVar;
        this.f570b = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    static synchronized as m1075a(Context context, gd gdVar) {
        as asVar;
        synchronized (as.class) {
            if (f569a == null) {
                f569a = new as(context, gdVar);
            }
            asVar = f569a;
        }
        return asVar;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        String a = gf.m1957a(th);
        try {
            if (!TextUtils.isEmpty(a) && a.contains("amapdynamic") && a.contains("com.amap.api")) {
                an.m1052a(new C0261v(this.f571c, at.m1080c()), this.f571c, this.f572d);
            }
        } catch (Throwable th2) {
            C0247g.m1917a(th2, "DynamicExceptionHandler", "uncaughtException");
        }
        if (this.f570b != null) {
            this.f570b.uncaughtException(thread, th);
        }
    }
}
