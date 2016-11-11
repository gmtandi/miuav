package com.p054c.p055a.p072c;

import android.util.Log;
import com.p054c.p055a.p063b.C0936g;

/* renamed from: com.c.a.c.f */
public final class C0958f {
    private static final String f5046a = "%1$s\n%2$s";
    private static volatile boolean f5047b;
    private static volatile boolean f5048c;

    static {
        f5047b = false;
        f5048c = true;
    }

    private C0958f() {
    }

    @Deprecated
    public static void m7552a() {
        C0958f.m7560b(true);
    }

    private static void m7553a(int i, Throwable th, String str, Object... objArr) {
        if (f5048c) {
            String format = objArr.length > 0 ? String.format(str, objArr) : str;
            if (th != null) {
                if (format == null) {
                    format = th.getMessage();
                }
                String stackTraceString = Log.getStackTraceString(th);
                format = String.format(f5046a, new Object[]{format, stackTraceString});
            }
            Log.println(i, C0936g.f4902a, format);
        }
    }

    public static void m7554a(String str, Object... objArr) {
        if (f5047b) {
            C0958f.m7553a(3, null, str, objArr);
        }
    }

    public static void m7555a(Throwable th) {
        C0958f.m7553a(6, th, null, new Object[0]);
    }

    public static void m7556a(Throwable th, String str, Object... objArr) {
        C0958f.m7553a(6, th, str, objArr);
    }

    public static void m7557a(boolean z) {
        f5047b = z;
    }

    @Deprecated
    public static void m7558b() {
        C0958f.m7560b(false);
    }

    public static void m7559b(String str, Object... objArr) {
        C0958f.m7553a(4, null, str, objArr);
    }

    public static void m7560b(boolean z) {
        f5048c = z;
    }

    public static void m7561c(String str, Object... objArr) {
        C0958f.m7553a(5, null, str, objArr);
    }

    public static void m7562d(String str, Object... objArr) {
        C0958f.m7553a(6, null, str, objArr);
    }
}
