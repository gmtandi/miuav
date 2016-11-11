package com.fimi.soul.drone.p110d;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.p109a.C1451n;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.fimi.soul.drone.d.h */
public class C1481h {
    public static final C1451n f7041a;
    public static final C1465c f7042b;

    static {
        f7041a = new C1451n();
        f7041a.a = Opcodes.I2L;
        f7042b = f7041a.m9778a();
    }

    public static void m9872a(C1433a c1433a) {
        f7041a.f6872d = (short) 1;
        f7041a.f6876h = (byte) 0;
        f7041a.f6877i = (byte) 0;
        f7041a.f6878j = (byte) 0;
        f7041a.f6879k = (byte) 0;
        c1433a.m9569P().m9993a(f7041a.m9778a());
    }
}
