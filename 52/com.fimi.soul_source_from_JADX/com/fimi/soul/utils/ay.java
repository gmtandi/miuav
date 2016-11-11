package com.fimi.soul.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.fimi.soul.base.C1236a;

public class ay {
    private static SharedPreferences f10057a;

    static {
        f10057a = null;
    }

    public ay(Context context) {
        m12293a(context);
    }

    public static SharedPreferences m12293a(Context context) {
        if (f10057a == null) {
            f10057a = context.getSharedPreferences(C1236a.f5579C, 0);
        }
        return f10057a;
    }

    public int m12294a(String str) {
        return f10057a.getInt(str, 0);
    }

    public void m12295a(long j) {
        f10057a.edit().putLong("play_index", j).commit();
    }

    public void m12296a(String str, int i) {
        Editor edit = f10057a.edit();
        edit.putInt(str, i);
        edit.commit();
    }

    public void m12297a(boolean z) {
        f10057a.edit().putBoolean("isNewGray", z).commit();
    }

    public boolean m12298a() {
        return f10057a.getBoolean("isNewGray", true);
    }

    public void m12299b(boolean z) {
        f10057a.edit().putBoolean("finish", z).commit();
    }

    public boolean m12300b() {
        return f10057a.getBoolean("finish", false);
    }

    public long m12301c() {
        return f10057a.getLong("play_index", 0);
    }
}
