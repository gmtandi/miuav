package com.fimi.soul.drone.p110d;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.p109a.ap;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.fimi.soul.drone.d.l */
public class C1485l {
    public static final ap f7048a;
    public static final C1465c f7049b;

    static {
        f7048a = new ap();
        f7048a.a = Opcodes.LOR;
        f7049b = f7048a.m9667a();
    }

    public static ap m9876a() {
        return f7048a;
    }

    public static void m9877a(C1433a c1433a) {
        f7048a.f6642d = (byte) 17;
        c1433a.m9569P().m9993a(f7048a.m9667a());
    }
}
