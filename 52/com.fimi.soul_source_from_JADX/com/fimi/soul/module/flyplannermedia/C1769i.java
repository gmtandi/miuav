package com.fimi.soul.module.flyplannermedia;

/* renamed from: com.fimi.soul.module.flyplannermedia.i */
class C1769i implements Runnable {
    final /* synthetic */ DroneLocalFragment f8702a;

    C1769i(DroneLocalFragment droneLocalFragment) {
        this.f8702a = droneLocalFragment;
    }

    public void run() {
        this.f8702a.f8660e = this.f8702a.m11413q();
        this.f8702a.f8661f.sendEmptyMessage(100);
        this.f8702a.f8663h = false;
    }
}
