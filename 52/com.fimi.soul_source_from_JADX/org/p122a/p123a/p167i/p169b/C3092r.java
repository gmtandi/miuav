package org.p122a.p123a.p167i.p169b;

import java.util.concurrent.atomic.AtomicLong;

/* renamed from: org.a.a.i.b.r */
public final class C3092r {
    private final AtomicLong f15286a;
    private final AtomicLong f15287b;
    private final C3093s f15288c;
    private final C3093s f15289d;
    private final C3093s f15290e;
    private final C3093s f15291f;

    C3092r() {
        this.f15286a = new AtomicLong();
        this.f15287b = new AtomicLong();
        this.f15288c = new C3093s();
        this.f15289d = new C3093s();
        this.f15290e = new C3093s();
        this.f15291f = new C3093s();
    }

    AtomicLong m17385a() {
        return this.f15286a;
    }

    AtomicLong m17386b() {
        return this.f15287b;
    }

    C3093s m17387c() {
        return this.f15288c;
    }

    C3093s m17388d() {
        return this.f15289d;
    }

    C3093s m17389e() {
        return this.f15290e;
    }

    C3093s m17390f() {
        return this.f15291f;
    }

    public long m17391g() {
        return this.f15286a.get();
    }

    public long m17392h() {
        return this.f15287b.get();
    }

    public long m17393i() {
        return this.f15288c.m17401a();
    }

    public long m17394j() {
        return this.f15288c.m17403b();
    }

    public long m17395k() {
        return this.f15289d.m17401a();
    }

    public long m17396l() {
        return this.f15289d.m17403b();
    }

    public long m17397m() {
        return this.f15290e.m17401a();
    }

    public long m17398n() {
        return this.f15290e.m17403b();
    }

    public long m17399o() {
        return this.f15291f.m17401a();
    }

    public long m17400p() {
        return this.f15291f.m17403b();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[activeConnections=").append(this.f15286a).append(", scheduledConnections=").append(this.f15287b).append(", successfulConnections=").append(this.f15288c).append(", failedConnections=").append(this.f15289d).append(", requests=").append(this.f15290e).append(", tasks=").append(this.f15291f).append("]");
        return stringBuilder.toString();
    }
}
