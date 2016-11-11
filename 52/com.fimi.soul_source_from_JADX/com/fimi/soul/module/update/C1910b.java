package com.fimi.soul.module.update;

import android.os.Looper;

/* renamed from: com.fimi.soul.module.update.b */
class C1910b implements Runnable {
    final /* synthetic */ CheckFirmwareActvity f9819a;

    C1910b(CheckFirmwareActvity checkFirmwareActvity) {
        this.f9819a = checkFirmwareActvity;
    }

    public void run() {
        Looper.prepare();
        for (int i = 0; i < 5; i++) {
            this.f9819a.f9654c.m12072a();
        }
    }
}
