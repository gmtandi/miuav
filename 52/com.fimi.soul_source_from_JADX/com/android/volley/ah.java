package com.android.volley;

import android.util.Log;
import java.util.Locale;

public class ah {
    public static String f3497a;
    public static boolean f3498b;

    static {
        f3497a = "Volley";
        f3498b = Log.isLoggable(f3497a, 2);
    }

    public static void m5055a(String str) {
        m5058b("Changing log tag to %s", str);
        f3497a = str;
        f3498b = Log.isLoggable(f3497a, 2);
    }

    public static void m5056a(String str, Object... objArr) {
        if (f3498b) {
            Log.v(f3497a, m5062e(str, objArr));
        }
    }

    public static void m5057a(Throwable th, String str, Object... objArr) {
        Log.e(f3497a, m5062e(str, objArr), th);
    }

    public static void m5058b(String str, Object... objArr) {
        Log.d(f3497a, m5062e(str, objArr));
    }

    public static void m5059b(Throwable th, String str, Object... objArr) {
        Log.wtf(f3497a, m5062e(str, objArr), th);
    }

    public static void m5060c(String str, Object... objArr) {
        Log.e(f3497a, m5062e(str, objArr));
    }

    public static void m5061d(String str, Object... objArr) {
        Log.wtf(f3497a, m5062e(str, objArr));
    }

    private static String m5062e(String str, Object... objArr) {
        String str2;
        if (objArr != null) {
            str = String.format(Locale.US, str, objArr);
        }
        StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        String str3 = "<unknown>";
        for (int i = 2; i < stackTrace.length; i++) {
            if (!stackTrace[i].getClass().equals(ah.class)) {
                str3 = stackTrace[i].getClassName();
                str3 = str3.substring(str3.lastIndexOf(46) + 1);
                str2 = str3.substring(str3.lastIndexOf(36) + 1) + "." + stackTrace[i].getMethodName();
                break;
            }
        }
        str2 = str3;
        return String.format(Locale.US, "[%d] %s: %s", new Object[]{Long.valueOf(Thread.currentThread().getId()), str2, str});
    }
}
