package com.fimi.soul.biz.update;

import com.fimi.kernel.p076b.p079c.C1134b;
import java.io.File;

/* renamed from: com.fimi.soul.biz.update.g */
class C1410g implements C1134b {
    final /* synthetic */ ah f6355a;
    final /* synthetic */ C1404a f6356b;

    C1410g(C1404a c1404a, ah ahVar) {
        this.f6356b = c1404a;
        this.f6355a = ahVar;
    }

    public void m9453a(boolean z, String str) {
        if (z) {
            File file = new File(this.f6356b.f6325l);
            this.f6356b.f6326m = file.length();
            this.f6356b.f6327n = 0;
            this.f6356b.f6323i.m7709b(file, new C1411h(this));
            return;
        }
        this.f6355a.m9419a(true, 0, 0, 0);
    }
}
