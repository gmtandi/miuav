package com.fimi.soul.biz.update;

import com.fimi.soul.biz.p098j.C1330i;
import com.fimi.soul.entity.PlaneMsg;
import java.util.List;

/* renamed from: com.fimi.soul.biz.update.v */
class C1424v implements C1330i {
    final /* synthetic */ ad f6415a;
    final /* synthetic */ C1423u f6416b;

    C1424v(C1423u c1423u, ad adVar) {
        this.f6416b = c1423u;
        this.f6415a = adVar;
    }

    public void m9509a(PlaneMsg planeMsg) {
        if (planeMsg.isSuccess()) {
            this.f6415a.m9418a((List) planeMsg.getData());
        }
    }
}
