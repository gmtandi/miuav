package com.p016a;

import android.content.Context;
import java.lang.Thread.UncaughtExceptionHandler;

/* renamed from: com.a.g */
public class C0247g {
    protected static C0247g f1249a;
    protected UncaughtExceptionHandler f1250b;
    protected boolean f1251c;

    public C0247g() {
        this.f1251c = true;
    }

    public static void m1917a(Throwable th, String str, String str2) {
        th.printStackTrace();
        if (f1249a != null) {
            f1249a.m1920a(th, 1, str, str2);
        }
    }

    protected void m1918a(Context context, gd gdVar, boolean z) {
    }

    protected void m1919a(gd gdVar, String str) {
    }

    protected void m1920a(Throwable th, int i, String str, String str2) {
    }
}
