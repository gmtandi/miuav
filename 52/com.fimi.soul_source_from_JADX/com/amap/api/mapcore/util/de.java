package com.amap.api.mapcore.util;

public class de {
    private df f2414a;
    private dj f2415b;

    /* renamed from: com.amap.api.mapcore.util.de.a */
    public interface C0336a {
        void m3366a(Throwable th);

        void m3367a(byte[] bArr, long j);

        void m3368d();

        void m3369e();
    }

    public de(dj djVar) {
        this(djVar, 0, -1);
    }

    public de(dj djVar, long j, long j2) {
        this.f2415b = djVar;
        this.f2414a = new df(this.f2415b.f2114g, this.f2415b.f2115h, djVar.f2116i == null ? null : djVar.f2116i);
        this.f2414a.m4020b(j2);
        this.f2414a.m4018a(j);
    }

    public void m4008a() {
        this.f2414a.m4017a();
    }

    public void m4009a(C0336a c0336a) {
        this.f2414a.m4019a(this.f2415b.m3431a(), this.f2415b.m3436c(), this.f2415b.m3434b(), c0336a);
    }
}
