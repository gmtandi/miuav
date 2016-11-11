package com.p016a;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import java.lang.reflect.Method;

/* renamed from: com.a.dm */
public class dm {
    private static Method f908a;

    public static void m1491a(Context context, String str, String str2, int i) {
        try {
            Editor edit = context.getSharedPreferences(str, 0).edit();
            edit.putInt(str2, i);
            dm.m1493a(edit);
        } catch (Throwable th) {
            ev.m1777a(th, "SPUtil", "setPrefsInt");
        }
    }

    public static void m1492a(Context context, String str, String str2, long j) {
        try {
            Editor edit = context.getSharedPreferences(str, 0).edit();
            edit.putLong(str2, j);
            dm.m1493a(edit);
        } catch (Throwable th) {
            ev.m1777a(th, "SPUtil", "setPrefsLong");
        }
    }

    public static void m1493a(Editor editor) {
        if (editor != null) {
            if (VERSION.SDK_INT >= 9) {
                try {
                    if (f908a == null) {
                        f908a = Editor.class.getDeclaredMethod("apply", new Class[0]);
                    }
                    f908a.invoke(editor, new Object[0]);
                    return;
                } catch (Throwable th) {
                    ev.m1777a(th, "SPUtil", "applySharedPreference");
                    editor.commit();
                    return;
                }
            }
            editor.commit();
        }
    }

    public static int m1494b(Context context, String str, String str2, int i) {
        try {
            i = context.getSharedPreferences(str, 0).getInt(str2, i);
        } catch (Throwable th) {
            ev.m1777a(th, "SPUtil", "getPrefsInt");
        }
        return i;
    }

    public static long m1495b(Context context, String str, String str2, long j) {
        try {
            j = context.getSharedPreferences(str, 0).getLong(str2, j);
        } catch (Throwable th) {
            ev.m1777a(th, "SPUtil", "getPrefsLong");
        }
        return j;
    }
}
