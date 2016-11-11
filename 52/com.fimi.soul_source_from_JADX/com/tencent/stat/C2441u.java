package com.tencent.stat;

import java.util.List;

/* renamed from: com.tencent.stat.u */
class C2441u implements C2407c {
    final /* synthetic */ List f12402a;
    final /* synthetic */ int f12403b;
    final /* synthetic */ C2434n f12404c;

    C2441u(C2434n c2434n, List list, int i) {
        this.f12404c = c2434n;
        this.f12402a = list;
        this.f12403b = i;
    }

    public void m14108a() {
        this.f12404c.m14104a(this.f12402a);
    }

    public void m14109b() {
        this.f12404c.m14105a(this.f12402a, 1);
        C2434n c2434n = this.f12404c;
        c2434n.f12386b += this.f12403b;
    }
}
