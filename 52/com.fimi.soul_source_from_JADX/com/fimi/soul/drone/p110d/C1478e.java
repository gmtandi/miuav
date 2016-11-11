package com.fimi.soul.drone.p110d;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.p109a.av;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.fimi.soul.drone.d.e */
public class C1478e {
    public static final av f7035a;
    public static C1465c f7036b;

    static {
        f7035a = new av();
        f7035a.a = Opcodes.IFNULL;
        f7036b = f7035a.m9679a();
    }

    public static void m9859a(C1433a c1433a) {
        f7035a.f6680d = (byte) 100;
        f7035a.f6681e = (byte) 1;
        c1433a.m9569P().m9993a(f7035a.m9679a());
    }
}
