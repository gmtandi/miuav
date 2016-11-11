package com.fimi.soul.biz.p097e;

import com.fimi.soul.biz.p098j.C1330i;
import com.fimi.soul.entity.BatteryOverDischange;
import com.fimi.soul.entity.PlaneMsg;

/* renamed from: com.fimi.soul.biz.e.d */
class C1332d implements C1330i {
    final /* synthetic */ BatteryOverDischange f5954a;
    final /* synthetic */ C1328a f5955b;

    C1332d(C1328a c1328a, BatteryOverDischange batteryOverDischange) {
        this.f5955b = c1328a;
        this.f5954a = batteryOverDischange;
    }

    public void m8953a(PlaneMsg planeMsg) {
        if (!planeMsg.isSuccess()) {
            this.f5955b.f5949a.m12457a(this.f5954a);
        }
    }
}
