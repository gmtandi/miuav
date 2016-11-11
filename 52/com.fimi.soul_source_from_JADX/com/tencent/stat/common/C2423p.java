package com.tencent.stat.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import org.p122a.p123a.C2915a;

/* renamed from: com.tencent.stat.common.p */
public class C2423p {
    private static SharedPreferences f12362a;

    static {
        f12362a = null;
    }

    public static int m14059a(Context context, String str, int i) {
        return C2423p.m14061a(context).getInt(C2418k.m14020b(context, C2915a.f14760f + str), i);
    }

    public static long m14060a(Context context, String str, long j) {
        return C2423p.m14061a(context).getLong(C2418k.m14020b(context, C2915a.f14760f + str), j);
    }

    static synchronized SharedPreferences m14061a(Context context) {
        SharedPreferences sharedPreferences;
        synchronized (C2423p.class) {
            if (f12362a == null) {
                f12362a = PreferenceManager.getDefaultSharedPreferences(context);
            }
            sharedPreferences = f12362a;
        }
        return sharedPreferences;
    }

    public static String m14062a(Context context, String str, String str2) {
        return C2423p.m14061a(context).getString(C2418k.m14020b(context, C2915a.f14760f + str), str2);
    }

    public static void m14063b(Context context, String str, int i) {
        String b = C2418k.m14020b(context, C2915a.f14760f + str);
        Editor edit = C2423p.m14061a(context).edit();
        edit.putInt(b, i);
        edit.commit();
    }

    public static void m14064b(Context context, String str, long j) {
        String b = C2418k.m14020b(context, C2915a.f14760f + str);
        Editor edit = C2423p.m14061a(context).edit();
        edit.putLong(b, j);
        edit.commit();
    }

    public static void m14065b(Context context, String str, String str2) {
        String b = C2418k.m14020b(context, C2915a.f14760f + str);
        Editor edit = C2423p.m14061a(context).edit();
        edit.putString(b, str2);
        edit.commit();
    }
}
