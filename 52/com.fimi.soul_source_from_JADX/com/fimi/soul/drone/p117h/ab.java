package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;

/* renamed from: com.fimi.soul.drone.h.ab */
public class ab extends C1495k {
    private short f7310b;
    private byte f7311c;

    public ab(C1433a c1433a) {
        super(c1433a);
        this.f7311c = (byte) 100;
    }

    public byte m10231a() {
        return this.f7311c;
    }

    public void m10232a(short s, byte b) {
        this.f7310b = s;
        this.f7311c = b;
        this.a.m9589a(C1584h.ExecuteWaypoint);
    }

    public short m10233b() {
        return this.f7310b;
    }

    public void m10234c() {
        this.f7310b = (short) 0;
    }
}
