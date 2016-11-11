package com.xiaomi.mistatistic.sdk.controller;

import com.xiaomi.mistatistic.sdk.controller.p139a.C2584b;
import com.xiaomi.mistatistic.sdk.controller.p139a.C2586d;

/* renamed from: com.xiaomi.mistatistic.sdk.controller.t */
public class C2606t {
    private static boolean f12977b;
    private long f12978a;

    static {
        f12977b = false;
    }

    private void m14789a(String str) {
        C2589b.m14733b().m14734a(new C2586d(str, new C2608v(this)));
    }

    public static boolean m14792b() {
        return f12977b;
    }

    private void m14793c() {
        C2589b.m14731a().m14734a(new C2584b(ab.m14718a().m14724e(), new C2607u(this)));
    }

    private void m14794d() {
        C2589b.m14731a().m14734a(new C2609w(this));
    }

    public void m14795a() {
        f12977b = true;
        m14793c();
        ab.m14718a().m14722c();
    }
}
