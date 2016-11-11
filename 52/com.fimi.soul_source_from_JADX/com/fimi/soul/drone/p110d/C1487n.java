package com.fimi.soul.drone.p110d;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.p107c.p108a.p109a.ax;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.fimi.soul.drone.d.n */
public class C1487n {
    static int f7052a;
    public static final ax f7053b;

    static {
        f7052a = 1;
        f7053b = new ax();
        f7053b.a = Opcodes.I2C;
    }

    public static void m9879a(C1433a c1433a) {
        ax axVar = f7053b;
        int i = f7052a;
        f7052a = i + 1;
        axVar.f6694d = (short) i;
        c1433a.m9569P().m9993a(f7053b.m9683a());
    }
}
