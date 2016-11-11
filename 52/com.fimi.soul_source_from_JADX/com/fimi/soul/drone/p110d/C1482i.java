package com.fimi.soul.drone.p110d;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.p109a.C1438a;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.fimi.soul.drone.d.i */
public class C1482i {
    public static final C1438a f7043a;
    public static final C1465c f7044b;

    static {
        f7043a = new C1438a();
        f7043a.a = Opcodes.DCMPG;
        f7044b = f7043a.m9633a();
    }

    public static void m9873a(C1433a c1433a) {
        f7043a.f6536d = (short) 1;
        c1433a.m9569P().m9993a(f7043a.m9633a());
    }
}
