package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;
import java.util.Arrays;

/* renamed from: com.fimi.soul.drone.c.a.a.bi */
public class bi extends C1437b {
    public static final int f6774b = 201;
    private static final long serialVersionUID = 201;
    public int f6775c;
    public short f6776d;
    public byte f6777e;
    public byte[] f6778f;

    public bi() {
        this.a = f6774b;
    }

    public bi(C1465c c1465c) {
        this.a = f6774b;
        m9709a(c1465c.f7001d);
    }

    public C1465c m9707a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = this.f6775c;
        c1465c.f7000c = f6774b;
        c1465c.f7001d.m9828b(this.f6777e);
        c1465c.f7001d.m9826a(this.f6776d);
        for (byte b : this.f6778f) {
            c1465c.f7001d.m9828b(b);
        }
        return c1465c;
    }

    public void m9708a(int i) {
        this.f6778f = new byte[i];
    }

    public void m9709a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6776d = c1466d.m9834e();
    }

    public void m9710b(int i) {
        this.f6775c = i;
    }

    public byte[] m9711b() {
        return this.f6778f;
    }

    public int m9712c() {
        return this.f6775c;
    }

    public String toString() {
        return "updatedrone [Packet_sequence=" + this.f6776d + ", data=" + Arrays.toString(this.f6778f) + "]";
    }
}
