package com.fimi.soul.media.player.pragma;

import android.util.Log;
import java.util.Locale;

public class DebugLog {
    public static final boolean ENABLE_DEBUG = true;
    public static final boolean ENABLE_ERROR = true;
    public static final boolean ENABLE_INFO = true;
    public static final boolean ENABLE_VERBOSE = true;
    public static final boolean ENABLE_WARN = true;

    public static int m10782d(String str, String str2) {
        return Log.d(str, str2);
    }

    public static int m10783d(String str, String str2, Throwable th) {
        return Log.d(str, str2, th);
    }

    public static int dfmt(String str, String str2, Object... objArr) {
        return Log.d(str, String.format(Locale.US, str2, objArr));
    }

    public static int m10784e(String str, String str2) {
        return Log.e(str, str2);
    }

    public static int m10785e(String str, String str2, Throwable th) {
        return Log.e(str, str2, th);
    }

    public static int efmt(String str, String str2, Object... objArr) {
        return Log.e(str, String.format(Locale.US, str2, objArr));
    }

    public static int m10786i(String str, String str2) {
        return Log.i(str, str2);
    }

    public static int m10787i(String str, String str2, Throwable th) {
        return Log.i(str, str2, th);
    }

    public static int ifmt(String str, String str2, Object... objArr) {
        return Log.i(str, String.format(Locale.US, str2, objArr));
    }

    public static void printCause(Throwable th) {
        Throwable cause = th.getCause();
        if (cause != null) {
            th = cause;
        }
        printStackTrace(th);
    }

    public static void printStackTrace(Throwable th) {
        th.printStackTrace();
    }

    public static int m10788v(String str, String str2) {
        return Log.v(str, str2);
    }

    public static int m10789v(String str, String str2, Throwable th) {
        return Log.v(str, str2, th);
    }

    public static int vfmt(String str, String str2, Object... objArr) {
        return Log.v(str, String.format(Locale.US, str2, objArr));
    }

    public static int m10790w(String str, String str2) {
        return Log.w(str, str2);
    }

    public static int m10791w(String str, String str2, Throwable th) {
        return Log.w(str, str2, th);
    }

    public static int wfmt(String str, String str2, Object... objArr) {
        return Log.w(str, String.format(Locale.US, str2, objArr));
    }
}
