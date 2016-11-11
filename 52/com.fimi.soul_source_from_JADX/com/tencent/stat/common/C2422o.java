package com.tencent.stat.common;

import java.io.File;

/* renamed from: com.tencent.stat.common.o */
class C2422o {
    private static int f12361a;

    static {
        f12361a = -1;
    }

    public static boolean m14058a() {
        if (f12361a == 1) {
            return true;
        }
        if (f12361a == 0) {
            return false;
        }
        String[] strArr = new String[]{"/bin", "/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        int i = 0;
        while (i < strArr.length) {
            try {
                File file = new File(strArr[i] + "su");
                if (file == null || !file.exists()) {
                    i++;
                } else {
                    f12361a = 1;
                    return true;
                }
            } catch (Exception e) {
            }
        }
        f12361a = 0;
        return false;
    }
}
