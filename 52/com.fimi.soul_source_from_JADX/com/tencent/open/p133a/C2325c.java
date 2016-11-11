package com.tencent.open.p133a;

import android.support.v4.view.accessibility.AccessibilityEventCompat;
import com.fimi.soul.module.setting.newhand.C1873o;
import java.io.File;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.tencent.open.a.c */
public class C2325c {
    public static String f11988a;
    public static int f11989b;
    public static int f11990c;
    public static boolean f11991d;
    public static boolean f11992e;
    public static boolean f11993f;
    public static String f11994g;
    public static String f11995h;
    public static String f11996i;
    public static String f11997j;
    public static String f11998k;
    public static long f11999l;
    public static int f12000m;
    public static int f12001n;
    public static int f12002o;
    public static String f12003p;
    public static String f12004q;
    public static String f12005r;
    public static int f12006s;
    public static long f12007t;

    static {
        f11988a = "openSDK_LOG";
        f11989b = 60;
        f11990c = 32;
        f11991d = false;
        f11992e = false;
        f11993f = false;
        f11994g = "OpenSDK.File.Tracer";
        f11995h = "OpenSDK.Client.File.Tracer";
        f11996i = "Tencent" + File.separator + "OpenSDK" + File.separator + "Logs";
        f11997j = ".OpenSDK.log";
        f11998k = ".app.log";
        f11999l = 8388608;
        f12000m = AccessibilityEventCompat.TYPE_GESTURE_DETECTION_START;
        f12001n = Opcodes.ACC_ANNOTATION;
        f12002o = C1873o.ak;
        f12003p = "debug.file.blockcount";
        f12004q = "debug.file.keepperiod";
        f12005r = "debug.file.tracelevel";
        f12006s = 24;
        f12007t = 604800000;
    }
}
