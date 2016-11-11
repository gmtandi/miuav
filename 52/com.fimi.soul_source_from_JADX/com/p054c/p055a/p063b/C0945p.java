package com.p054c.p055a.p063b;

import java.io.File;

/* renamed from: com.c.a.b.p */
class C0945p implements Runnable {
    final /* synthetic */ C0948r f4979a;
    final /* synthetic */ C0944o f4980b;

    C0945p(C0944o c0944o, C0948r c0948r) {
        this.f4980b = c0944o;
        this.f4979a = c0948r;
    }

    public void run() {
        File a = this.f4980b.f4969a.f4929o.m7028a(this.f4979a.m7538a());
        Object obj = (a == null || !a.exists()) ? null : 1;
        this.f4980b.m7498h();
        if (obj != null) {
            this.f4980b.f4971c.execute(this.f4979a);
        } else {
            this.f4980b.f4970b.execute(this.f4979a);
        }
    }
}
