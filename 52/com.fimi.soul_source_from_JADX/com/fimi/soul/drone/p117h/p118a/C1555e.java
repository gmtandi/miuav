package com.fimi.soul.drone.p117h.p118a;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.entity.DroneModelType;

/* renamed from: com.fimi.soul.drone.h.a.e */
public class C1555e extends C1495k {
    private byte f7285b;
    private byte f7286c;
    private byte f7287d;
    private byte f7288e;
    private byte f7289f;
    private byte f7290g;
    private byte f7291h;
    private DroneModelType f7292i;

    public C1555e(C1433a c1433a) {
        super(c1433a);
        this.f7286c = (byte) 50;
        this.f7288e = (byte) 90;
        this.f7292i = new DroneModelType();
    }

    public byte m10165a() {
        return this.f7288e;
    }

    public void m10166a(byte b, byte b2, byte b3, byte b4, byte b5, byte b6, byte b7) {
        this.f7285b = b;
        this.f7286c = b2;
        this.f7292i.setCtrlType(b);
        this.f7292i.setCtrlMode(b2);
        this.f7288e = b4;
        this.f7289f = b5;
        this.f7290g = b6;
        this.f7287d = b3;
        this.f7291h = b7;
        this.a.m9589a(C1584h.NEWFRONEMODEL);
    }

    public byte m10167b() {
        return this.f7290g;
    }

    public byte m10168c() {
        return this.f7289f;
    }

    public byte m10169d() {
        return this.f7285b;
    }

    public byte m10170e() {
        return this.f7286c;
    }

    public byte m10171f() {
        return this.f7287d;
    }

    public DroneModelType m10172g() {
        return this.f7292i;
    }

    public String toString() {
        return "NewDroneModel{CtrlType=" + this.f7285b + ", CtrlMode=" + this.f7286c + ", WP_STA=" + this.f7287d + '}';
    }
}
