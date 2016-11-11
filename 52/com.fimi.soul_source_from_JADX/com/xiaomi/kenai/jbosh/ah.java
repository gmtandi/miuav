package com.xiaomi.kenai.jbosh;

final class ah {
    private final C2514o f12642a;
    private final C2516q f12643b;
    private final C2515p f12644c;
    private final C2512m f12645d;
    private final C2509j f12646e;
    private final C2513n f12647f;
    private final C2508i f12648g;
    private final C2505f f12649h;
    private final C2510k f12650i;
    private final C2506g f12651j;
    private final C2507h f12652k;
    private final boolean f12653l;

    private ah(C2514o c2514o, C2516q c2516q, C2515p c2515p, C2512m c2512m, C2509j c2509j, C2513n c2513n, C2508i c2508i, C2505f c2505f, C2510k c2510k, C2506g c2506g, C2507h c2507h, boolean z) {
        this.f12642a = c2514o;
        this.f12643b = c2516q;
        this.f12644c = c2515p;
        this.f12645d = c2512m;
        this.f12646e = c2509j;
        this.f12647f = c2513n;
        this.f12648g = c2508i;
        this.f12649h = c2505f;
        this.f12650i = c2510k;
        this.f12651j = c2506g;
        this.f12652k = c2507h;
        this.f12653l = z;
    }

    static ah m14321a(C2501b c2501b, C2501b c2501b2) {
        C2506g a = C2506g.m14385a(c2501b2.m14336a(C2517r.f12713c));
        boolean z = a != null && ((String) a.m14304a()).equals(c2501b.m14336a(C2517r.f12727q));
        return new ah(C2514o.m14395a(m14322a(c2501b2, C2517r.f12730t)), C2516q.m14398a(m14322a(c2501b2, C2517r.f12736z)), C2515p.m14396a(c2501b2.m14336a(C2517r.f12735y)), C2512m.m14392a(c2501b2.m14336a(C2517r.f12724n)), C2509j.m14388a(c2501b2.m14336a(C2517r.f12719i)), C2513n.m14394a(c2501b2.m14336a(C2517r.f12726p)), C2508i.m14387a(c2501b2.m14336a(C2517r.f12718h)), C2505f.m14384a(c2501b2.m14336a(C2517r.f12711a)), C2510k.m14389a(c2501b2.m14336a(C2517r.f12721k)), a, C2507h.m14386a(c2501b2.m14336a(C2517r.f12714d)), z);
    }

    private static String m14322a(C2501b c2501b, ag agVar) {
        String a = c2501b.m14336a(agVar);
        if (a != null) {
            return a;
        }
        throw new aa("Connection Manager session creation response did not include required '" + agVar.m14319b() + "' attribute");
    }

    C2514o m14323a() {
        return this.f12642a;
    }

    C2516q m14324b() {
        return this.f12643b;
    }

    C2515p m14325c() {
        return this.f12644c;
    }

    C2512m m14326d() {
        return this.f12645d;
    }

    C2510k m14327e() {
        return this.f12650i;
    }

    boolean m14328f() {
        return this.f12653l;
    }
}
