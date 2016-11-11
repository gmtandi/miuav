package com.fimi.kernel;

import android.app.Activity;
import android.content.Context;
import com.fimi.kernel.p076b.C1137c;
import com.fimi.kernel.p076b.C1152e;
import com.fimi.kernel.p082c.C1156a;
import com.fimi.kernel.p082c.C1157c;
import com.fimi.kernel.p084e.ad;
import com.fimi.kernel.view.C1192b;
import com.fimi.kernel.view.C1197c;

/* renamed from: com.fimi.kernel.f */
public final class C1189f {
    private static Context f5355a;
    private static Activity f5356b;
    private static String f5357c;

    static {
        f5355a = null;
        f5356b = null;
        f5357c = null;
    }

    private C1189f() {
    }

    public static Context m8327a() {
        return f5355a;
    }

    public static <T> T m8328a(C1152e c1152e) {
        return C1137c.m7862a(c1152e);
    }

    public static void m8329a(Activity activity) {
        f5356b = null;
        f5356b = activity;
        f5357c = f5356b.getClass().getName();
    }

    public static void m8330a(Context context) {
        f5355a = context;
    }

    public static C1192b m8331b() {
        return C1197c.m8374a();
    }

    public static void m8332b(Activity activity) {
        if (activity.getClass().getName().equals(f5357c)) {
            f5356b = null;
            f5357c = null;
        }
    }

    public static C1156a m8333c() {
        return C1157c.m7938a();
    }

    public static Activity m8334d() {
        return f5356b;
    }

    public static ad m8335e() {
        return new ad(C1189f.m8327a());
    }
}
