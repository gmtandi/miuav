package com.xiaomi.mistatistic.sdk.controller;

import android.os.Handler;
import java.util.ArrayList;

/* renamed from: com.xiaomi.mistatistic.sdk.controller.b */
public class C2589b {
    private static C2589b f12954a;
    private static C2589b f12955b;
    private volatile Handler f12956c;
    private ArrayList f12957d;

    static {
        f12954a = null;
        f12955b = null;
    }

    private C2589b(String str) {
        this.f12957d = new ArrayList();
        new C2592f(this, str).start();
    }

    public static synchronized C2589b m14731a() {
        C2589b c2589b;
        synchronized (C2589b.class) {
            if (f12954a == null) {
                f12954a = new C2589b("local_job_dispatcher");
            }
            c2589b = f12954a;
        }
        return c2589b;
    }

    public static synchronized C2589b m14733b() {
        C2589b c2589b;
        synchronized (C2589b.class) {
            if (f12955b == null) {
                f12955b = new C2589b("remote_job_dispatcher");
            }
            c2589b = f12955b;
        }
        return c2589b;
    }

    public void m14734a(C2582e c2582e) {
        synchronized (this.f12957d) {
            if (this.f12956c == null) {
                this.f12957d.add(c2582e);
            } else {
                this.f12956c.post(new C2590c(this, c2582e));
            }
        }
    }

    public void m14735a(C2582e c2582e, long j) {
        if (this.f12956c != null) {
            this.f12956c.postDelayed(new C2591d(this, c2582e), j);
        }
    }
}
