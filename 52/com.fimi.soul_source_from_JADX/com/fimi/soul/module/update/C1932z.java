package com.fimi.soul.module.update;

import com.fimi.soul.drone.p110d.C1488o;

/* renamed from: com.fimi.soul.module.update.z */
class C1932z implements Runnable {
    final /* synthetic */ UpgradingActivity f9875a;

    C1932z(UpgradingActivity upgradingActivity) {
        this.f9875a = upgradingActivity;
    }

    public void run() {
        C1488o.f7054a.f6936d = (byte) 0;
        C1488o.f7054a.f6937e = (byte) 2;
        this.f9875a.f9775k.m9569P().m9993a(C1488o.f7054a.m9794a());
    }
}
