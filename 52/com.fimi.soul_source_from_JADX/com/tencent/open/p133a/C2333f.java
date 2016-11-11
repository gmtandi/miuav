package com.tencent.open.p133a;

import android.os.Environment;
import android.text.TextUtils;
import com.tencent.open.p133a.C2331d.C2326a;
import com.tencent.open.p133a.C2331d.C2328c;
import com.tencent.open.p133a.C2331d.C2329d;
import com.tencent.open.utils.Global;
import java.io.File;

/* renamed from: com.tencent.open.a.f */
public class C2333f {
    public static C2333f f12012a;
    protected static final C2324b f12013c;
    public static final String f12014d;
    protected C2320a f12015b;

    static {
        f12012a = null;
        f12014d = C2325c.f11988a;
        f12013c = new C2324b(C2333f.m13756c(), C2325c.f12006s, C2325c.f12000m, C2325c.f12001n, C2325c.f11995h, (long) C2325c.f12002o, 10, C2325c.f11998k, C2325c.f12007t);
    }

    private C2333f() {
        this.f12015b = new C2320a(f12013c);
    }

    public static C2333f m13750a() {
        if (f12012a == null) {
            synchronized (C2333f.class) {
                if (f12012a == null) {
                    f12012a = new C2333f();
                }
            }
        }
        return f12012a;
    }

    public static final void m13751a(String str, String str2) {
        C2333f.m13750a().m13760a(1, str, str2, null);
    }

    public static final void m13752a(String str, String str2, Throwable th) {
        C2333f.m13750a().m13760a(2, str, str2, th);
    }

    public static void m13753b() {
        synchronized (C2333f.class) {
            C2333f.m13750a().m13761d();
            if (f12012a != null) {
                f12012a = null;
            }
        }
    }

    public static final void m13754b(String str, String str2) {
        C2333f.m13750a().m13760a(2, str, str2, null);
    }

    public static final void m13755b(String str, String str2, Throwable th) {
        C2333f.m13750a().m13760a(16, str, str2, th);
    }

    protected static File m13756c() {
        String packageName = Global.getPackageName();
        if (TextUtils.isEmpty(packageName)) {
            packageName = "default";
        }
        String str = C2325c.f11996i + File.separator + packageName;
        C2329d b = C2328c.m13740b();
        Object obj = (b == null || b.m13747c() <= C2325c.f11999l) ? null : 1;
        return obj != null ? new File(Environment.getExternalStorageDirectory(), str) : new File(Global.getFilesDir(), str);
    }

    public static final void m13757c(String str, String str2) {
        C2333f.m13750a().m13760a(4, str, str2, null);
    }

    public static final void m13758d(String str, String str2) {
        C2333f.m13750a().m13760a(8, str, str2, null);
    }

    public static final void m13759e(String str, String str2) {
        C2333f.m13750a().m13760a(16, str, str2, null);
    }

    protected void m13760a(int i, String str, String str2, Throwable th) {
        C2332e.f12011a.m13696b(i, Thread.currentThread(), System.currentTimeMillis(), str, str2, th);
        if (C2326a.m13737a(C2325c.f11990c, i) && this.f12015b != null) {
            this.f12015b.m13696b(i, Thread.currentThread(), System.currentTimeMillis(), str, str2, th);
        }
    }

    protected void m13761d() {
        if (this.f12015b != null) {
            this.f12015b.m13703a();
            this.f12015b.m13707b();
            this.f12015b = null;
        }
    }
}
