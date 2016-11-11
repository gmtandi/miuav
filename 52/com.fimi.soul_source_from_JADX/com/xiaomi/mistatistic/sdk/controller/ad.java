package com.xiaomi.mistatistic.sdk.controller;

class ad implements C2582e {
    final /* synthetic */ int f12948a;
    final /* synthetic */ long f12949b;
    final /* synthetic */ ab f12950c;

    ad(ab abVar, int i, long j) {
        this.f12950c = abVar;
        this.f12948a = i;
        this.f12949b = j;
    }

    public void m14726a() {
        this.f12950c.f12944b = this.f12948a;
        if (this.f12950c.f12944b == 4) {
            this.f12950c.f12945c = this.f12949b;
        } else {
            this.f12950c.f12945c = -1;
        }
        C2605s.m14783b(C2588a.m14708a(), "upload_policy", this.f12950c.f12944b);
        if (this.f12950c.f12944b == 4) {
            C2605s.m14784b(C2588a.m14708a(), "upload_interval", this.f12950c.f12945c);
            C2589b.m14731a().m14735a(new ae(this), this.f12950c.f12945c);
        }
    }
}
