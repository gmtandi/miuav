package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.C1469a;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;
import com.fimi.soul.drone.p117h.C1564g;
import com.fimi.soul.drone.p117h.C1575r;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.fimi.soul.drone.c.a.a.i */
public final class C1446i {
    public static C1445h m9762a(int i, C1465c c1465c) {
        C1469a c1575r;
        switch (i) {
            case Opcodes.V1_5 /*49*/:
                c1575r = new C1575r();
                break;
            case Opcodes.V1_6 /*50*/:
                c1575r = new C1564g();
                break;
            case Opcodes.V1_7 /*51*/:
                c1575r = new C1575r();
                break;
            default:
                c1575r = null;
                break;
        }
        if (c1575r == null) {
            return null;
        }
        c1575r.m10174a(i);
        C1445h c1445h = new C1445h(c1575r);
        c1445h.a = i;
        c1445h.m9759a(c1465c.f7001d);
        return c1445h;
    }

    public static C1465c m9763a(int i, int i2, C1466d c1466d) {
        C1465c c1465c = new C1465c(c1466d);
        c1465c.f6999b = i;
        c1465c.f7000c = i2;
        return c1465c;
    }

    public static C1465c m9764a(int i, byte[] bArr) {
        C1465c c1465c = new C1465c(C1446i.m9765a(bArr));
        c1465c.f6999b = bArr.length;
        c1465c.f7000c = i;
        return c1465c;
    }

    public static C1466d m9765a(byte[] bArr) {
        C1466d c1466d = new C1466d();
        for (byte b : bArr) {
            c1466d.m9828b(b);
        }
        return c1466d;
    }
}
