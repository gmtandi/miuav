package com.xiaomi.mistatistic.sdk.controller;

import android.content.Context;
import android.content.SharedPreferences.Editor;

/* renamed from: com.xiaomi.mistatistic.sdk.controller.s */
public class C2605s {
    public static int m14779a(Context context, String str, int i) {
        return context.getSharedPreferences("mistat", 0).getInt(str, i);
    }

    public static long m14780a(Context context, String str, long j) {
        return context.getSharedPreferences("mistat", 0).getLong(str, j);
    }

    public static String m14781a(Context context, String str, String str2) {
        return context.getSharedPreferences("mistat", 0).getString(str, str2);
    }

    public static boolean m14782a(Context context, String str) {
        return context.getSharedPreferences("mistat", 0).contains(str);
    }

    public static void m14783b(Context context, String str, int i) {
        Editor edit = context.getSharedPreferences("mistat", 0).edit();
        edit.putInt(str, i);
        edit.commit();
    }

    public static void m14784b(Context context, String str, long j) {
        Editor edit = context.getSharedPreferences("mistat", 0).edit();
        edit.putLong(str, j);
        edit.commit();
    }

    public static void m14785b(Context context, String str, String str2) {
        Editor edit = context.getSharedPreferences("mistat", 0).edit();
        edit.putString(str, str2);
        edit.commit();
    }
}
