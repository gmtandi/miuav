package com.fimi.soul.module.dronemanage;

/* renamed from: com.fimi.soul.module.dronemanage.g */
public class C1720g {
    private static C1720g f8467a;
    private C1721h f8468b;

    static {
        f8467a = null;
    }

    private C1720g() {
        this.f8468b = C1721h.NONEXECUTE;
    }

    public static C1720g m11225a() {
        if (f8467a == null) {
            f8467a = new C1720g();
        }
        return f8467a;
    }

    public void m11226a(C1721h c1721h) {
        this.f8468b = c1721h;
    }

    public C1721h m11227b() {
        return this.f8468b;
    }
}
