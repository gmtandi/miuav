package org.p122a.p123a.p167i.p169b;

import java.util.concurrent.atomic.AtomicLong;

/* renamed from: org.a.a.i.b.s */
class C3093s {
    private final AtomicLong f15292a;
    private final AtomicLong f15293b;

    C3093s() {
        this.f15292a = new AtomicLong(0);
        this.f15293b = new AtomicLong(0);
    }

    public long m17401a() {
        return this.f15292a.get();
    }

    public void m17402a(long j) {
        this.f15292a.incrementAndGet();
        this.f15293b.addAndGet(System.currentTimeMillis() - j);
    }

    public long m17403b() {
        long j = this.f15292a.get();
        return j > 0 ? this.f15293b.get() / j : 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[count=").append(m17401a()).append(", averageDuration=").append(m17403b()).append("]");
        return stringBuilder.toString();
    }
}
