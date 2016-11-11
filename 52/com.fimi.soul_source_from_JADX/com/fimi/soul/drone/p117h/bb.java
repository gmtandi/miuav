package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;

/* renamed from: com.fimi.soul.drone.h.bb */
public class bb extends C1495k {
    private double f7464b;
    private double f7465c;
    private double f7466d;
    private double f7467e;
    private double f7468f;
    private byte f7469g;
    private double f7470h;

    public bb(C1433a c1433a) {
        super(c1433a);
    }

    public byte m10471a() {
        return this.f7469g;
    }

    public void m10472a(double d, double d2, double d3, double d4, double d5, byte b, double d6) {
        this.f7464b = d;
        this.f7465c = d2;
        this.f7466d = d3;
        this.f7467e = d4;
        this.f7468f = d5;
        this.f7469g = b;
        this.f7470h = d6;
        this.a.m9589a(C1584h.GPS_FIX);
    }

    public double m10473b() {
        return this.f7470h;
    }

    public double m10474c() {
        return this.f7464b;
    }

    public double m10475d() {
        return this.f7465c;
    }

    public double m10476e() {
        return this.f7466d;
    }

    public double m10477f() {
        return this.f7467e;
    }

    public double m10478g() {
        return this.f7468f;
    }

    public String toString() {
        return "TelWaypoint [GPSLatitude=" + this.f7464b + ", GPSLongitude=" + this.f7465c + ", GPSAltitude=" + this.f7466d + ", GPSSpeed=" + this.f7467e + ", GPSBearing=" + this.f7468f + ", number=" + this.f7469g + ", accuracy=" + this.f7470h + "]";
    }
}
