package com.fimi.soul.module.calibcompass;

import com.fimi.soul.module.p091a.C1658a;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.fimi.soul.module.calibcompass.q */
public class C1683q {
    private static C1683q f7941a;
    private volatile ConcurrentHashMap<String, C1658a> f7942b;

    public C1683q() {
        this.f7942b = new ConcurrentHashMap();
    }

    public static C1683q m10886a() {
        if (f7941a == null) {
            f7941a = new C1683q();
        }
        return f7941a;
    }

    public void m10887a(String str) {
        C1658a c1658a = (C1658a) this.f7942b.get(str);
        if (c1658a != null) {
            c1658a.m10805b();
        }
        this.f7942b.remove(str);
    }

    public void m10888a(String str, C1658a c1658a) {
        if (c1658a != null) {
            this.f7942b.put(str, c1658a);
        }
    }
}
