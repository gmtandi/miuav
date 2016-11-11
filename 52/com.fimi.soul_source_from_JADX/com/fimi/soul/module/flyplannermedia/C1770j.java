package com.fimi.soul.module.flyplannermedia;

import android.os.Handler;
import android.os.Message;

/* renamed from: com.fimi.soul.module.flyplannermedia.j */
class C1770j extends Handler {
    final /* synthetic */ DroneLocalFragment f8703a;

    C1770j(DroneLocalFragment droneLocalFragment) {
        this.f8703a = droneLocalFragment;
    }

    public void handleMessage(Message message) {
        this.f8703a.f8663h = false;
        if (this.f8703a.f8660e != null) {
            this.f8703a.m11386b().m8488a(this.f8703a.f8660e);
            this.f8703a.m11397g(false);
            if (this.f8703a.f8660e.size() > 0) {
                this.f8703a.m11391d(false);
            } else {
                this.f8703a.m11391d(true);
            }
            this.f8703a.m11386b().notifyDataSetChanged();
            return;
        }
        this.f8703a.m11397g(false);
        this.f8703a.m11391d(true);
    }
}
