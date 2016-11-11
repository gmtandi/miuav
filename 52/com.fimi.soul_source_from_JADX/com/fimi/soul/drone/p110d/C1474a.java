package com.fimi.soul.drone.p110d;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.p107c.p108a.p109a.ab;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.fimi.soul.drone.d.a */
public class C1474a {
    static int f7027a;
    public static final ab f7028b;

    static {
        f7027a = 1;
        f7028b = new ab();
        f7028b.a = Opcodes.LCMP;
    }

    public static void m9854a() {
        f7027a = 1;
    }

    public static void m9855a(C1433a c1433a) {
        ab abVar = f7028b;
        int i = f7027a;
        f7027a = i + 1;
        abVar.f6547d = (short) i;
        c1433a.m9569P().m9993a(f7028b.m9637a());
    }
}
