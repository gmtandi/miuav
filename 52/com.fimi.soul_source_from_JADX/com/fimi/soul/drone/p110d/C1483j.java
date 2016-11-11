package com.fimi.soul.drone.p110d;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.p107c.p108a.p109a.ai;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.fimi.soul.drone.d.j */
public class C1483j {
    static int f7045a;
    public static final ai f7046b;

    static {
        f7045a = 1;
        f7046b = new ai();
        f7046b.a = Opcodes.I2S;
    }

    public static void m9874a(C1433a c1433a) {
        ai aiVar = f7046b;
        int i = f7045a;
        f7045a = i + 1;
        aiVar.f6596d = (short) i;
        c1433a.m9569P().m9993a(f7046b.m9653a());
    }
}
