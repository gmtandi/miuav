package com.fimi.soul.biz.update;

import com.fimi.kernel.C1189f;
import com.fimi.soul.biz.p098j.C1330i;
import com.fimi.soul.entity.PlaneMsg;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.biz.update.x */
class C1426x implements C1330i {
    final /* synthetic */ C1423u f6418a;

    C1426x(C1423u c1423u) {
        this.f6418a = c1423u;
    }

    public void m9510a(PlaneMsg planeMsg) {
        if (planeMsg.isSuccess()) {
            C1189f.m8333c().m7930a("upgradeResultInfo", C2915a.f14760f);
        }
    }
}
