package com.fimi.soul.drone.p116g;

/* renamed from: com.fimi.soul.drone.g.h */
class C1548h implements Runnable {
    final /* synthetic */ C1547g f7262a;

    C1548h(C1547g c1547g) {
        this.f7262a = c1547g;
    }

    public void run() {
        if (this.f7262a.f7260e != null) {
            this.f7262a.f7260e.run();
            this.f7262a.f7259d.postDelayed(this.f7262a.f7261f, (long) this.f7262a.f7258c);
        }
    }
}
