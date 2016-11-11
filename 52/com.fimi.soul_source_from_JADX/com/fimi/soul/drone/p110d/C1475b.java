package com.fimi.soul.drone.p110d;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.p107c.p108a.p109a.af;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.fimi.soul.drone.d.b */
public class C1475b {
    static int f7029a;
    public static final af f7030b;

    static {
        f7029a = 1;
        f7030b = new af();
        f7030b.a = Opcodes.D2F;
    }

    public static void m9856a(C1433a c1433a) {
        af afVar = f7030b;
        int i = f7029a;
        f7029a = i + 1;
        afVar.f6571d = (short) i;
        c1433a.m9569P().m9993a(f7030b.m9647a());
    }
}
