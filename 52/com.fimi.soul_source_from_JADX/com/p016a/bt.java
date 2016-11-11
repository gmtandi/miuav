package com.p016a;

import java.util.TimerTask;

/* renamed from: com.a.bt */
class bt extends TimerTask {
    final /* synthetic */ int f699a;
    final /* synthetic */ bs f700b;

    bt(bs bsVar, int i) {
        this.f700b = bsVar;
        this.f699a = i;
    }

    public void run() {
        Thread.currentThread().setPriority(1);
        if (dn.m1519b() - this.f700b.f698z >= 10000) {
            if (this.f700b.m1186A()) {
                this.f700b.m1218b(this.f699a);
            } else {
                this.f700b.m1249y();
            }
        }
    }
}
