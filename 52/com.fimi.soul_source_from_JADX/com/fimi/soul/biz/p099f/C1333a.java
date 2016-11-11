package com.fimi.soul.biz.p099f;

import android.content.Context;
import android.content.SharedPreferences;
import com.fimi.soul.utils.ay;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.OAuth;

/* renamed from: com.fimi.soul.biz.f.a */
public class C1333a {
    public static String m8954a(Context context, int i) {
        return C1333a.m8960d(context, i).getString("macAlgorithm", null);
    }

    public static void m8955a(Context context, int i, String str) {
        C1333a.m8960d(context, i).edit().putString("macAlgorithm", str).commit();
    }

    public static String m8956b(Context context, int i) {
        return C1333a.m8960d(context, i).getString("mackey", null);
    }

    public static void m8957b(Context context, int i, String str) {
        C1333a.m8960d(context, i).edit().putString("mackey", str).commit();
    }

    public static String m8958c(Context context, int i) {
        return C1333a.m8960d(context, i).getString(OAuth.ACCESS_TOKEN, null);
    }

    public static void m8959c(Context context, int i, String str) {
        C1333a.m8960d(context, i).edit().putString(OAuth.ACCESS_TOKEN, str).commit();
    }

    private static SharedPreferences m8960d(Context context, int i) {
        return ay.m12293a(context);
    }
}
