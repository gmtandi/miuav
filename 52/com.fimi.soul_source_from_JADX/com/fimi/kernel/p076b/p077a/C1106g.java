package com.fimi.kernel.p076b.p077a;

import android.util.Log;

/* renamed from: com.fimi.kernel.b.a.g */
class C1106g implements Runnable {
    final /* synthetic */ C1102c f5112a;

    C1106g(C1102c c1102c) {
        this.f5112a = c1102c;
    }

    public void run() {
        try {
            if (this.f5112a.f5108i.isFile()) {
                this.f5112a.f5102c.m15899a(this.f5112a.f5108i, new C1107h(this));
            }
        } catch (Exception e) {
            Log.d(this.f5112a.f5101a, e.toString());
        }
    }
}
