package com.fimi.soul.drone.p110d;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.p109a.C1459v;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.fimi.soul.drone.d.g */
public class C1480g {
    public static final C1459v f7039a;
    public static final C1465c f7040b;

    static {
        f7039a = new C1459v();
        f7039a.a = Opcodes.FMUL;
        f7040b = f7039a.m9796a();
    }

    public static C1459v m9870a() {
        return f7039a;
    }

    public static void m9871a(C1433a c1433a, byte b) {
        f7039a.f6940d = b;
        c1433a.m9569P().m9993a(f7039a.m9796a());
    }
}
