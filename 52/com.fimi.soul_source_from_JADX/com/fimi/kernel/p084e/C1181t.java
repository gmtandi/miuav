package com.fimi.kernel.p084e;

import android.content.Context;
import android.util.Log;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
import java.util.Calendar;

/* renamed from: com.fimi.kernel.e.t */
public class C1181t {
    public static boolean f5347a;
    public static boolean f5348b;
    public static boolean f5349c;
    public static long f5350d;

    static {
        f5347a = false;
        f5348b = false;
        f5349c = false;
        f5350d = 0;
    }

    public static void m8216a() {
        f5347a = true;
        f5348b = true;
        f5349c = true;
    }

    public static void m8217a(Context context) {
        C1181t.m8223a(context.getClass().getSimpleName());
    }

    public static void m8218a(Context context, String str) {
        C1181t.m8224a(context.getClass().getSimpleName(), str);
    }

    public static void m8219a(Context context, String str, boolean z) {
        C1181t.m8225a(context.getClass().getSimpleName(), str, z);
    }

    public static void m8220a(Class<?> cls) {
        C1181t.m8223a(cls.getSimpleName());
    }

    public static void m8221a(Class<?> cls, String str) {
        C1181t.m8224a(cls.getSimpleName(), str);
    }

    public static void m8222a(Class<?> cls, String str, boolean z) {
        C1181t.m8225a(cls.getSimpleName(), str, z);
    }

    public static void m8223a(String str) {
        f5350d = Calendar.getInstance().getTimeInMillis();
        Log.d(str, "\u65e5\u5fd7\u8ba1\u65f6\u5f00\u59cb\uff1a" + f5350d);
    }

    public static void m8224a(String str, String str2) {
        if (f5347a) {
            Log.d(str, str2);
        }
    }

    public static void m8225a(String str, String str2, boolean z) {
        Log.d(str, str2 + ":" + (Calendar.getInstance().getTimeInMillis() - f5350d) + LocaleUtil.MALAY);
    }

    public static void m8226a(boolean z) {
        f5347a = z;
    }

    public static void m8227a(boolean z, boolean z2, boolean z3) {
        f5347a = z;
        f5348b = z2;
        f5349c = z3;
    }

    public static void m8228b() {
        f5347a = false;
        f5348b = false;
        f5349c = false;
    }

    public static void m8229b(Context context, String str) {
        C1181t.m8231b(context.getClass().getSimpleName(), str);
    }

    public static void m8230b(Class<?> cls, String str) {
        C1181t.m8231b(cls.getSimpleName(), str);
    }

    public static void m8231b(String str, String str2) {
        Log.i(str, str2);
    }

    public static void m8232b(boolean z) {
        f5348b = z;
    }

    public static void m8233c(Context context, String str) {
        C1181t.m8235c(context.getClass().getSimpleName(), str);
    }

    public static void m8234c(Class<?> cls, String str) {
        C1181t.m8235c(cls.getSimpleName(), str);
    }

    public static void m8235c(String str, String str2) {
        Log.e(str, str2);
    }

    public static void m8236c(boolean z) {
        f5349c = z;
    }
}
