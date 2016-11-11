package com.fimi.soul.module.setting.newhand;

/* renamed from: com.fimi.soul.module.setting.newhand.k */
class C1869k implements Runnable {
    final /* synthetic */ LoopWidget f9509a;

    C1869k(LoopWidget loopWidget) {
        this.f9509a = loopWidget;
    }

    public void run() {
        if (this.f9509a.f9362f) {
            this.f9509a.f9363g = (this.f9509a.f9363g % (this.f9509a.f9357a + 1)) + 1;
            if (this.f9509a.f9363g == 1) {
                this.f9509a.f9361e.setCurrentItem(this.f9509a.f9363g, false);
                this.f9509a.f9367k.post(this.f9509a.f9369m);
                return;
            }
            this.f9509a.f9361e.setCurrentItem(this.f9509a.f9363g);
            this.f9509a.f9367k.postDelayed(this.f9509a.f9369m, 2000);
            return;
        }
        this.f9509a.f9367k.postDelayed(this.f9509a.f9369m, 5000);
    }
}
