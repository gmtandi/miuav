package com.fimi.soul.module.droneFragment;

/* renamed from: com.fimi.soul.module.droneFragment.f */
class C1692f implements Runnable {
    final /* synthetic */ C1691e f8281a;

    C1692f(C1691e c1691e) {
        this.f8281a = c1691e;
    }

    public void run() {
        if (C1691e.f8275f != null) {
            this.f8281a.f8280j = true;
            C1691e.f8273c.set(1);
            C1691e.f8275f.destroy();
            C1691e.f8275f = null;
        }
    }
}
