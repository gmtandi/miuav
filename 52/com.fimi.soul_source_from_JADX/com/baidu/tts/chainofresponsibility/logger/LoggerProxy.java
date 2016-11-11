package com.baidu.tts.chainofresponsibility.logger;

import android.util.Log;
import java.util.List;

public class LoggerProxy {
    private static C0755d f4193a;

    static {
        f4193a = C0755d.m6527a();
    }

    public static void m6514a(String str, String str2) {
        log(7, str, str2);
    }

    public static void addPrintString(String str) {
        f4193a.m6535a(str);
    }

    public static void addPrintStrings(List<String> list) {
        f4193a.m6536a((List) list);
    }

    public static void addUnPrintString(String str) {
        f4193a.m6539b(str);
    }

    public static void clearHandler() {
        f4193a.m6538b();
    }

    public static void clearSpecifyStrings() {
        f4193a.m6540c();
    }

    public static void m6515d(String str, String str2) {
        log(3, str, str2);
    }

    public static void m6516e(String str, String str2) {
        log(6, str, str2);
    }

    public static void m6517i(String str, String str2) {
        log(4, str, str2);
    }

    public static boolean isModeRelease() {
        return f4193a.m6542e();
    }

    public static void log(int i, String str, String str2) {
        try {
            f4193a.m6533a(i, str, str2);
        } catch (Exception e) {
            Log.e("LoggerProxy", "log exception=" + e.toString());
        }
    }

    public static void printable(boolean z) {
        f4193a.m6537a(z);
    }

    public static void setModeRelease() {
        f4193a.m6541d();
    }

    public static void m6518v(String str, String str2) {
        log(2, str, str2);
    }

    public static void m6519w(String str, String str2) {
        log(5, str, str2);
    }
}
