package com.fimi.soul.module.update;

import com.fimi.soul.biz.camera.p094c.C1287j;
import com.fimi.soul.biz.update.C1420r;
import com.fimi.soul.biz.update.ak;

/* renamed from: com.fimi.soul.module.update.v */
class C1928v implements Runnable {
    final /* synthetic */ UpgradingActivity f9871a;

    C1928v(UpgradingActivity upgradingActivity) {
        this.f9871a = upgradingActivity;
    }

    public void run() {
        ak.m9434c("request camera upgrade");
        C1287j s = this.f9871a.f9784z.m8874s();
        this.f9871a.f9757A;
        s.m8786h(C1420r.m9468b());
    }
}
