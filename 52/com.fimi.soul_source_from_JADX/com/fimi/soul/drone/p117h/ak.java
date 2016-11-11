package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.h.ak */
public class ak extends C1495k {
    public static final int f7360b = 0;
    public static final int f7361c = 1;
    public static final int f7362d = 2;
    public static final int f7363e = 3;
    public byte f7364f;
    public byte f7365g;
    public byte f7366h;
    public byte f7367i;
    public int f7368j;
    public byte f7369k;
    public byte f7370l;
    private boolean f7371m;
    private boolean f7372n;

    public ak(C1433a c1433a) {
        super(c1433a);
        this.f7372n = true;
        this.f7370l = (byte) 0;
    }

    public byte m10307a() {
        return this.f7370l;
    }

    public void m10308a(byte b) {
        this.f7370l = b;
    }

    public void m10309a(byte b, byte b2, byte b3, byte b4, int i, byte b5) {
        this.f7364f = b;
        this.f7365g = b2;
        this.f7366h = b3;
        this.f7367i = b4;
        this.f7368j = i;
        this.f7369k = b5;
        this.a.m9589a(C1584h.NEW_HAND_OPERATE);
    }

    public void m10310a(C1465c c1465c) {
        C1466d c1466d = c1465c.f7001d;
        c1466d.m9831c();
        this.f7364f = c1466d.m9833d();
        this.f7365g = c1466d.m9833d();
        c1466d.m9833d();
        c1466d.m9833d();
        c1466d.m9833d();
        c1466d.m9833d();
        this.f7369k = c1466d.m9833d();
        this.a.m9589a(C1584h.NEW_HAND_OPERATE);
    }

    public void m10311a(boolean z) {
        this.f7372n = z;
    }

    public void m10312b(C1465c c1465c) {
        C1466d c1466d = c1465c.f7001d;
        c1466d.m9831c();
        this.f7364f = c1466d.m9833d();
        this.f7365g = c1466d.m9833d();
        this.f7366h = c1466d.m9833d();
        this.f7367i = c1466d.m9833d();
        this.f7368j = c1466d.m9835f();
        this.f7369k = c1466d.m9833d();
        if (this.f7364f == 34) {
            if (this.f7366h == (byte) 1 || this.f7366h == (byte) 3) {
                if ((this.f7368j & f7361c) == f7361c) {
                    this.f7371m = false;
                }
                if ((this.f7368j & f7362d) == f7362d) {
                    this.f7371m = false;
                }
                if ((this.f7368j & 4) == 4) {
                    this.f7371m = true;
                }
            }
        } else if (this.f7364f == 51 && this.f7366h == (byte) 3 && this.f7367i == (byte) 4) {
            if (this.f7368j == f7361c) {
                this.f7371m = true;
            } else {
                this.f7371m = false;
            }
        }
        this.a.m9589a(C1584h.NEW_HAND_OPERATE);
    }

    public void m10313b(boolean z) {
        this.f7371m = z;
    }

    public boolean m10314b() {
        return this.f7372n;
    }

    public byte m10315c() {
        return this.f7364f;
    }

    public byte m10316d() {
        return this.f7365g;
    }

    public byte m10317e() {
        return this.f7366h;
    }

    public byte m10318f() {
        return this.f7367i;
    }

    public int m10319g() {
        return this.f7368j;
    }

    public byte m10320h() {
        return this.f7369k;
    }

    public boolean m10321i() {
        return this.f7371m;
    }

    public String toString() {
        return "NewHandDroneVariable{report=" + this.f7369k + ", valueToBeOperate=" + this.f7368j + ", byteCode=" + this.f7367i + ", targetByteID=" + this.f7366h + ", pilotMode=" + this.f7365g + ", operation=" + this.f7364f + '}';
    }
}
