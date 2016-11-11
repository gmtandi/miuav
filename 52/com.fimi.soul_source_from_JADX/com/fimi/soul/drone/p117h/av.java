package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.utils.be;

/* renamed from: com.fimi.soul.drone.h.av */
public class av extends C1495k {
    private byte f7433b;
    private byte f7434c;
    private byte f7435d;
    private byte f7436e;
    private byte f7437f;
    private byte f7438g;

    public av(C1433a c1433a) {
        super(c1433a);
    }

    public byte m10408a() {
        return this.f7433b;
    }

    public void m10409a(byte b) {
        this.f7433b = b;
    }

    public void m10410a(byte b, byte b2, byte b3, byte b4, byte b5, byte b6) {
        this.f7433b = b;
        this.f7434c = b2;
        this.f7435d = b3;
        this.f7436e = b4;
        this.f7437f = b5;
        this.f7438g = b6;
        this.a.m9589a(C1584h.Remotecontrol);
    }

    public byte m10411b() {
        return this.f7434c;
    }

    public void m10412b(byte b) {
        this.f7438g = b;
    }

    public byte m10413c() {
        return this.f7435d;
    }

    public byte m10414d() {
        return this.f7436e;
    }

    public byte m10415e() {
        return this.f7437f;
    }

    public byte m10416f() {
        return this.f7438g;
    }

    public boolean m10417g() {
        return be.m12349a(this.f7437f, Math.abs(0)) > 0;
    }

    public boolean m10418h() {
        return be.m12349a(this.f7437f, Math.abs(1)) > 0;
    }
}
