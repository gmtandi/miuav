package com.fimi.soul.drone.p110d;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.p107c.p108a.p109a.aj;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.fimi.soul.drone.d.c */
public class C1476c {
    static int f7031a;
    public static final aj f7032b;

    static {
        f7031a = 1;
        f7032b = new aj();
        f7032b.a = Opcodes.I2B;
    }

    public static void m9857a(C1433a c1433a) {
        aj ajVar = f7032b;
        int i = f7031a;
        f7031a = i + 1;
        ajVar.f6601d = (short) i;
        c1433a.m9569P().m9993a(f7032b.m9655a());
    }
}
