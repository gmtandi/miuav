package com.fimi.soul.module.dronemanage;

import com.fimi.soul.drone.p110d.C1486m;

class aa implements Runnable {
    final /* synthetic */ C1738z f8390a;

    aa(C1738z c1738z) {
        this.f8390a = c1738z;
    }

    public void run() {
        if (this.f8390a.f8539l.m9608k().f7414b != this.f8390a.f8538i) {
            C1486m.m9878a(this.f8390a.f8539l);
        }
        C1738z.m11286l();
        if (C1738z.f8529m >= 10) {
            this.f8390a.m11207i().sendEmptyMessage(2);
        }
    }
}
