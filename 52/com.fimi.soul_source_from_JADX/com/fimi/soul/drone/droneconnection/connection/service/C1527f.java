package com.fimi.soul.drone.droneconnection.connection.service;

import com.fimi.soul.drone.p107c.p108a.C1437b;

/* renamed from: com.fimi.soul.drone.droneconnection.connection.service.f */
class C1527f implements Runnable {
    final /* synthetic */ C1437b f7163a;
    final /* synthetic */ C1523b f7164b;

    C1527f(C1523b c1523b, C1437b c1437b) {
        this.f7164b = c1523b;
        this.f7163a = c1437b;
    }

    public void run() {
        this.f7164b.f7156a.f7146b = true;
        this.f7164b.f7156a.f7152h.m8509a(this.f7163a);
    }
}
