package com.tencent.open.p133a;

import com.tencent.open.p133a.C2331d.C2326a;

/* renamed from: com.tencent.open.a.i */
public abstract class C2319i {
    private volatile int f11959a;
    private volatile boolean f11960b;
    private C2335h f11961c;

    public C2319i() {
        this(C2325c.f11989b, true, C2335h.f12018a);
    }

    public C2319i(int i, boolean z, C2335h c2335h) {
        this.f11959a = C2325c.f11989b;
        this.f11960b = true;
        this.f11961c = C2335h.f12018a;
        m13692a(i);
        m13695a(z);
        m13694a(c2335h);
    }

    public void m13692a(int i) {
        this.f11959a = i;
    }

    protected abstract void m13693a(int i, Thread thread, long j, String str, String str2, Throwable th);

    public void m13694a(C2335h c2335h) {
        this.f11961c = c2335h;
    }

    public void m13695a(boolean z) {
        this.f11960b = z;
    }

    public void m13696b(int i, Thread thread, long j, String str, String str2, Throwable th) {
        if (m13697d() && C2326a.m13737a(this.f11959a, i)) {
            m13693a(i, thread, j, str, str2, th);
        }
    }

    public boolean m13697d() {
        return this.f11960b;
    }

    public C2335h m13698e() {
        return this.f11961c;
    }
}
