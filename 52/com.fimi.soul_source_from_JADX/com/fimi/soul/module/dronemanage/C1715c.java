package com.fimi.soul.module.dronemanage;

/* renamed from: com.fimi.soul.module.dronemanage.c */
class C1715c implements Runnable {
    final /* synthetic */ C1713a f8425a;

    C1715c(C1713a c1713a) {
        this.f8425a = c1713a;
    }

    public void run() {
        C1713a c1713a = this.f8425a;
        c1713a.f8388d++;
        this.f8425a.f8386b.m9569P().m9993a(this.f8425a.f8387c);
        if (this.f8425a.f8388d >= 5) {
            this.f8425a.f8389e.sendEmptyMessage(1);
        }
    }
}
