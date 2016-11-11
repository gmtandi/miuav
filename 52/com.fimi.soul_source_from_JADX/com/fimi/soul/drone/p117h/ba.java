package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;

/* renamed from: com.fimi.soul.drone.h.ba */
public class ba extends C1495k {
    public byte f7453b;
    public byte f7454c;
    public byte f7455d;
    public byte f7456e;
    public byte f7457f;
    public byte f7458g;
    public byte f7459h;
    public byte f7460i;
    public byte f7461j;
    public byte f7462k;
    public byte f7463l;

    public ba(C1433a c1433a) {
        super(c1433a);
    }

    public byte m10448a() {
        return this.f7453b;
    }

    public void m10449a(byte b) {
        this.f7453b = b;
    }

    public void m10450a(byte b, byte b2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, byte b9, byte b10, byte b11) {
        this.f7453b = b;
        this.f7454c = b2;
        this.f7455d = b3;
        this.f7456e = b4;
        this.f7457f = b5;
        this.f7458g = b6;
        this.f7459h = b7;
        this.f7460i = b8;
        this.f7461j = b9;
        this.f7462k = b10;
        this.f7463l = b11;
        this.a.m9589a(C1584h.SIMULATORINFO);
    }

    public byte m10451b() {
        return this.f7454c;
    }

    public void m10452b(byte b) {
        this.f7454c = b;
    }

    public byte m10453c() {
        return this.f7455d;
    }

    public void m10454c(byte b) {
        this.f7455d = b;
    }

    public byte m10455d() {
        return this.f7456e;
    }

    public void m10456d(byte b) {
        this.f7456e = b;
    }

    public byte m10457e() {
        return this.f7457f;
    }

    public void m10458e(byte b) {
        this.f7457f = b;
    }

    public byte m10459f() {
        return this.f7458g;
    }

    public void m10460f(byte b) {
        this.f7458g = b;
    }

    public byte m10461g() {
        return this.f7459h;
    }

    public void m10462g(byte b) {
        this.f7459h = b;
    }

    public byte m10463h() {
        return this.f7460i;
    }

    public void m10464h(byte b) {
        this.f7460i = b;
    }

    public byte m10465i() {
        return this.f7461j;
    }

    public void m10466i(byte b) {
        this.f7461j = b;
    }

    public byte m10467j() {
        return this.f7462k;
    }

    public void m10468j(byte b) {
        this.f7462k = b;
    }

    public byte m10469k() {
        return this.f7463l;
    }

    public void m10470k(byte b) {
        this.f7463l = b;
    }

    public String toString() {
        return "SimulatorInfo [ADC0=" + this.f7453b + ", ADC1=" + this.f7454c + ", ADC2=" + this.f7455d + ", ADC3=" + this.f7456e + ", ADC4=" + this.f7457f + ", ADC5=" + this.f7458g + ", battery=" + this.f7459h + ", switch1=" + this.f7460i + ", switch2=" + this.f7461j + ", left3=" + this.f7462k + ", right3=" + this.f7463l + "]";
    }
}
