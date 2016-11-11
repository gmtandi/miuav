package com.fimi.soul.drone.droneconnection.connection.service;

import com.fimi.soul.drone.droneconnection.connection.C1514k;
import com.fimi.soul.drone.p107c.p108a.C1437b;

/* renamed from: com.fimi.soul.drone.droneconnection.connection.service.b */
class C1523b implements C1514k {
    final /* synthetic */ C1522a f7156a;
    private final Runnable f7157b;
    private final Runnable f7158c;
    private final Runnable f7159d;

    C1523b(C1522a c1522a) {
        this.f7156a = c1522a;
        this.f7157b = new C1524c(this);
        this.f7158c = new C1525d(this);
        this.f7159d = new C1526e(this);
    }

    public void m10034a() {
        this.f7156a.f7147c.post(this.f7157b);
    }

    public void m10035a(C1437b c1437b) {
        this.f7156a.f7147c.post(new C1527f(this, c1437b));
    }

    public void m10036a(String str) {
        this.f7156a.f7148d.set(str);
        this.f7156a.f7147c.post(this.f7159d);
    }

    public void m10037a(boolean z) {
        this.f7156a.f7147c.post(new C1528g(this, z));
    }

    public void m10038b() {
        this.f7156a.f7147c.post(this.f7158c);
    }

    public void m10039b(boolean z) {
        this.f7156a.f7147c.post(new C1529h(this, z));
    }
}
