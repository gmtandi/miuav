package com.fimi.soul.biz.update;

import com.fimi.kernel.C1189f;
import com.fimi.soul.biz.p098j.C1330i;
import com.fimi.soul.entity.PlaneMsg;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.biz.update.z */
class C1428z implements C1330i {
    final /* synthetic */ C1427y f6420a;

    C1428z(C1427y c1427y) {
        this.f6420a = c1427y;
    }

    public void m9511a(PlaneMsg planeMsg) {
        if (planeMsg.isSuccess()) {
            C1189f.m8333c().m7930a("upgradeResultInfo", C2915a.f14760f);
        }
    }
}
