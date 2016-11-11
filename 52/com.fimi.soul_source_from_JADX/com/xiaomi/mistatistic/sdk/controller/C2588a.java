package com.xiaomi.mistatistic.sdk.controller;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.xiaomi.mistatistic.sdk.controller.a */
public abstract class C2588a {
    private static Context f12938a;
    private static String f12939b;
    private static String f12940c;
    private static String f12941d;

    public static Context m14708a() {
        return f12938a;
    }

    public static void m14709a(Context context, String str, String str2, String str3) {
        f12938a = context.getApplicationContext();
        f12939b = str;
        f12940c = str2;
        f12941d = str3;
    }

    public static String m14710b() {
        return f12939b;
    }

    public static String m14711c() {
        return f12940c;
    }

    public static String m14712d() {
        return f12941d;
    }

    public static String m14713e() {
        try {
            PackageInfo packageInfo = f12938a.getPackageManager().getPackageInfo(f12938a.getPackageName(), Opcodes.ACC_ENUM);
            if (packageInfo != null) {
                return packageInfo.versionName;
            }
        } catch (NameNotFoundException e) {
        }
        return null;
    }
}
