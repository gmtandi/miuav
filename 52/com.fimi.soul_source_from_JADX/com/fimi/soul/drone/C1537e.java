package com.fimi.soul.drone;

import java.util.Iterator;

/* renamed from: com.fimi.soul.drone.e */
class C1537e implements Runnable {
    final /* synthetic */ C1496d f7197a;

    C1537e(C1496d c1496d) {
        this.f7197a = c1496d;
    }

    public void run() {
        do {
            this.f7197a.f7064c.removeCallbacks(this);
            C1584h c1584h = (C1584h) this.f7197a.f7063b.poll();
            if (!(c1584h == null || this.f7197a.f7066e.isEmpty())) {
                Iterator it = this.f7197a.f7066e.iterator();
                while (it.hasNext()) {
                    ((C1234i) it.next()).onDroneEvent(c1584h, this.f7197a.a);
                }
            }
        } while (!this.f7197a.f7063b.isEmpty());
    }
}
