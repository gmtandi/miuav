package com.fimi.soul.biz.update;

import com.fimi.kernel.p076b.p077a.C1097a;
import com.fimi.kernel.p076b.p077a.C1098b;

/* renamed from: com.fimi.soul.biz.update.h */
class C1411h extends C1097a {
    final /* synthetic */ C1410g f6357a;

    C1411h(C1410g c1410g) {
        this.f6357a = c1410g;
    }

    public void m9454a(C1098b c1098b, int i) {
        C1404a.m9399c(this.f6357a.f6356b, (long) i);
        boolean z = c1098b == C1098b.Completed || c1098b == C1098b.Aborted;
        this.f6357a.f6355a.m9419a(z, this.f6357a.f6356b.f6327n, this.f6357a.f6356b.f6326m, 0);
    }
}
