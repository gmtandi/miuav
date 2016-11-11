package com.xiaomi.mistatistic.sdk;

public class BuildSetting {
    private static boolean f12897a;

    static {
        f12897a = false;
    }

    public static boolean isTest() {
        return f12897a;
    }

    public static void setTest(boolean z) {
        f12897a = z;
    }
}
