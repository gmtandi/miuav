package com.fimi.soul.module.droneui;

/* renamed from: com.fimi.soul.module.droneui.h */
class C1747h implements Runnable {
    final /* synthetic */ C1746g f8612a;

    C1747h(C1746g c1746g) {
        this.f8612a = c1746g;
    }

    public void run() {
        this.f8612a.f8611i = this.f8612a.f8611i - 1;
        if (this.f8612a.f8611i >= 0 || this.f8612a.f8610h == null) {
            this.f8612a.f8604b.postDelayed(this.f8612a.f8603a, 1000);
        } else {
            this.f8612a.f8610h.m11023d();
        }
    }
}
