package com.p016a;

/* renamed from: com.a.bl */
public class bl {
    private bn f632a;
    private bp f633b;

    public bl(bp bpVar) {
        this(bpVar, 0, -1);
    }

    public bl(bp bpVar, long j, long j2) {
        this.f633b = bpVar;
        this.f632a = new bn(this.f633b.f550a, this.f633b.f551b, bpVar.f552c == null ? null : bpVar.f552c);
        this.f632a.m1184b(j2);
        this.f632a.m1182a(j);
    }

    public void m1175a(bm bmVar) {
        this.f632a.m1183a(this.f633b.m1038c(), this.f633b.m1033a(), this.f633b.m1036b(), bmVar);
    }
}
