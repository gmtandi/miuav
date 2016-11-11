package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.drone.h.w */
public class C1580w extends C1495k {
    private String f7618b;

    public C1580w(C1433a c1433a) {
        super(c1433a);
        this.f7618b = C2915a.f14760f;
    }

    public String m10613a() {
        return this.f7618b;
    }

    public void m10614a(String str) {
        this.f7618b = str;
        this.a.m9589a(C1584h.DRONEERRORACTIONCODE);
    }
}
