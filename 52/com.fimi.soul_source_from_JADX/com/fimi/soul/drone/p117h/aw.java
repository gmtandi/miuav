package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;

/* renamed from: com.fimi.soul.drone.h.aw */
public class aw extends C1495k {
    public int f7439b;

    public aw(C1433a c1433a) {
        super(c1433a);
    }

    public int m10419a() {
        return this.f7439b;
    }

    public void m10420a(int i) {
        this.f7439b = i;
        this.a.m9589a(C1584h.RESETRC);
    }

    public String toString() {
        return "RestVariable{targetID=" + this.f7439b + '}';
    }
}
