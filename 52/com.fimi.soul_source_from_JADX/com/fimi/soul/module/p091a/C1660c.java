package com.fimi.soul.module.p091a;

/* renamed from: com.fimi.soul.module.a.c */
class C1660c implements Runnable {
    final /* synthetic */ C1658a f7848a;

    C1660c(C1658a c1658a) {
        this.f7848a = c1658a;
    }

    public void run() {
        if (this.f7848a.f7845e != null) {
            C1658a.f7839a++;
            this.f7848a.f7844d.m9569P().m9993a(this.f7848a.f7845e);
            if (C1658a.f7839a >= 5) {
                this.f7848a.f7842b.sendEmptyMessage(1);
            }
        }
    }
}
