package com.fimi.soul.module.dronemanage;

class ah implements Runnable {
    final /* synthetic */ af f8408a;

    ah(af afVar) {
        this.f8408a = afVar;
    }

    public void run() {
        af.f8400d++;
        af.f8398b.m9569P().m9993a(af.f8399c);
        if (af.f8400d >= 5) {
            this.f8408a.f8406h.sendEmptyMessage(2);
        }
    }
}
