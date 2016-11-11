package com.xiaomi.mistatistic.sdk.controller;

import com.xiaomi.mistatistic.sdk.MiStatInterface;

class ac implements C2582e {
    final /* synthetic */ ab f12947a;

    ac(ab abVar) {
        this.f12947a = abVar;
    }

    public void m14725a() {
        this.f12947a.f12944b = C2605s.m14779a(C2588a.m14708a(), "upload_policy", 3);
        if (this.f12947a.f12944b == 4) {
            this.f12947a.f12945c = C2605s.m14780a(C2588a.m14708a(), "upload_interval", (long) MiStatInterface.MAX_UPLOAD_INTERVAL);
        } else {
            this.f12947a.f12945c = -1;
        }
        if (this.f12947a.f12944b == 3) {
            new C2606t().m14795a();
        }
    }
}
