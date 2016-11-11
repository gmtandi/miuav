package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;
import java.io.Serializable;

/* renamed from: com.fimi.soul.drone.h.ai */
public class ai extends C1495k implements Serializable {
    private double f7340b;
    private double f7341c;
    private byte f7342d;
    private byte f7343e;
    private int f7344f;
    private byte f7345g;
    private byte f7346h;
    private double f7347i;
    private double f7348j;
    private double f7349k;
    private double f7350l;
    private double f7351m;
    private double f7352n;
    private double f7353o;

    public ai(C1433a c1433a) {
        super(c1433a);
    }

    public double m10272a() {
        return this.f7340b;
    }

    public void m10273a(byte b) {
        this.f7342d = b;
    }

    public void m10274a(double d) {
        this.f7340b = d;
    }

    public void m10275a(double d, double d2, double d3, double d4, double d5, double d6, double d7, byte b, byte b2, int i, byte b3, byte b4, double d8, double d9) {
        this.f7347i = d2;
        this.f7352n = d;
        this.f7348j = d3;
        this.f7349k = d4;
        this.f7350l = d5;
        this.f7351m = d6;
        this.f7353o = d7;
        this.f7340b = d8;
        this.f7341c = d9;
        this.f7342d = b;
        this.f7343e = b2;
        this.f7344f = i;
        this.f7345g = b3;
        this.f7346h = b4;
        this.a.m9589a(C1584h.HEARDATA);
    }

    public void m10276a(int i) {
        this.f7344f = i;
    }

    public double m10277b() {
        return this.f7341c;
    }

    public void m10278b(byte b) {
        this.f7343e = b;
    }

    public void m10279b(double d) {
        this.f7341c = d;
    }

    public byte m10280c() {
        return this.f7342d;
    }

    public void m10281c(double d) {
        this.f7347i = d;
    }

    public byte m10282d() {
        return this.f7343e;
    }

    public void m10283d(double d) {
        this.f7348j = d;
    }

    public int m10284e() {
        return this.f7344f;
    }

    public void m10285e(double d) {
        this.f7349k = d;
    }

    public byte m10286f() {
        return this.f7345g;
    }

    public void m10287f(double d) {
        this.f7350l = d;
    }

    public byte m10288g() {
        return this.f7346h;
    }

    public void m10289g(double d) {
        this.f7351m = d;
    }

    public double m10290h() {
        return this.f7347i;
    }

    public void m10291h(double d) {
        this.f7352n = d;
    }

    public double m10292i() {
        return this.f7348j;
    }

    public void m10293i(double d) {
        this.f7353o = d;
    }

    public double m10294j() {
        return this.f7349k;
    }

    public double m10295k() {
        return this.f7350l;
    }

    public double m10296l() {
        return this.f7351m;
    }

    public double m10297m() {
        return this.f7352n;
    }

    public double m10298n() {
        return this.f7353o;
    }

    public String toString() {
        return "HeardData{GroundSpeed=" + this.f7340b + ", DownVelocity=" + this.f7341c + ", Heartbeat=" + this.f7342d + ", Disarm_count=" + this.f7343e + ", FlightMode=" + this.f7344f + ", rcReceiver=" + this.f7345g + ", takeoffTag=" + this.f7346h + ", ThrottleStick=" + this.f7347i + ", rollangle=" + this.f7348j + ", pitchangle=" + this.f7349k + ", Headingangle=" + this.f7350l + ", YawStick=" + this.f7351m + ", PitchStick=" + this.f7352n + ", RollStick=" + this.f7353o + '}';
    }
}
