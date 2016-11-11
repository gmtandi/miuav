package com.fimi.soul.module.update;

import java.util.TimerTask;

/* renamed from: com.fimi.soul.module.update.p */
class C1923p extends TimerTask {
    final /* synthetic */ C1922o f9866a;

    C1923p(C1922o c1922o) {
        this.f9866a = c1922o;
    }

    public void run() {
        this.f9866a.f9865d = this.f9866a.f9865d + 1;
        if (this.f9866a.f9865d == 40) {
            this.f9866a.m12086b();
            this.f9866a.m12088d();
            this.f9866a.f9863b.m11961a(true);
        }
        System.out.println(" counter=" + this.f9866a.f9865d);
    }
}
