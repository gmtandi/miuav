package com.p016a;

import android.content.Context;

/* renamed from: com.a.j */
class C0250j implements bo {
    private Context f1285a;

    C0250j(Context context) {
        this.f1285a = context;
    }

    public void m1985a() {
        try {
            C0255n.m2034b(this.f1285a);
        } catch (Throwable th) {
            C0247g.m1917a(th, "LogNetListener", "onNetCompleted");
        }
    }
}
