package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;
import java.io.Serializable;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.fimi.soul.drone.c.a.a.p */
public class C1453p extends C1437b implements Serializable {
    static final byte f6886b = (byte) 10;
    static final byte f6887c = (byte) 108;
    static final byte f6888d = (byte) 1;
    static final byte f6889e = (byte) 11;
    static final byte f6890f = (byte) 1;
    static final byte f6891g = (byte) 8;
    byte f6892h;
    public byte f6893i;
    public byte f6894j;
    public byte f6895k;
    public byte f6896l;
    public byte f6897m;

    public C1453p() {
        this.f6892h = f6889e;
        this.a = Opcodes.IDIV;
    }

    public C1465c m9782a() {
        C1465c c1465c = new C1465c();
        c1465c.f7000c = Opcodes.IDIV;
        c1465c.f6999b = 10;
        c1465c.f7001d.m9828b(this.f6892h);
        c1465c.f7001d.m9828b((byte) f6890f);
        c1465c.f7001d.m9828b((byte) f6891g);
        c1465c.f7001d.m9828b((byte) 0);
        c1465c.f7001d.m9828b((byte) 0);
        c1465c.f7001d.m9828b((byte) 0);
        c1465c.f7001d.m9828b((byte) 0);
        c1465c.f7001d.m9828b((byte) 0);
        c1465c.f7001d.m9828b((byte) 0);
        c1465c.f7001d.m9828b((byte) 0);
        return c1465c;
    }

    public void m9783a(C1465c c1465c) {
        m9784a(c1465c.f7001d);
    }

    public void m9784a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6894j = c1466d.m9833d();
        this.f6895k = c1466d.m9833d();
        this.f6896l = c1466d.m9833d();
        this.f6897m = c1466d.m9833d();
    }

    public void m9785a(boolean z) {
        if (z) {
            this.f6892h = f6889e;
        } else {
            this.f6892h = f6890f;
        }
    }
}
