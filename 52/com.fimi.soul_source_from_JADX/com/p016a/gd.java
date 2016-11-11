package com.p016a;

import org.p122a.p123a.p152c.p153a.C2924b;

/* renamed from: com.a.gd */
public class gd {
    String f1262a;
    String f1263b;
    String f1264c;
    private boolean f1265d;
    private String f1266e;
    private String[] f1267f;

    private gd(ge geVar) {
        this.f1265d = true;
        this.f1266e = C2924b.f14791c;
        this.f1267f = null;
        this.f1262a = geVar.f1268a;
        this.f1264c = geVar.f1269b;
        this.f1263b = geVar.f1270c;
        this.f1265d = geVar.f1271d;
        this.f1266e = geVar.f1272e;
        this.f1267f = geVar.f1273f;
    }

    public String m1938a() {
        return this.f1264c;
    }

    public void m1939a(boolean z) {
        this.f1265d = z;
    }

    public String m1940b() {
        return this.f1262a;
    }

    public String m1941c() {
        return this.f1263b;
    }

    public String m1942d() {
        return this.f1266e;
    }

    public boolean m1943e() {
        return this.f1265d;
    }

    public String[] m1944f() {
        return (String[]) this.f1267f.clone();
    }
}
