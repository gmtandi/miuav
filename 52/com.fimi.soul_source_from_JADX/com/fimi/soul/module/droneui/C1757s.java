package com.fimi.soul.module.droneui;

/* renamed from: com.fimi.soul.module.droneui.s */
public class C1757s {
    static double f8627a;
    static double f8628b;
    double f8629c;
    double f8630d;
    double f8631e;
    double f8632f;
    double f8633g;
    double f8634h;
    double f8635i;
    double f8636j;
    double f8637k;
    double f8638l;
    double f8639m;
    double f8640n;

    static {
        f8627a = 6378137.0d;
        f8628b = 6356725.0d;
    }

    public C1757s(double d, double d2) {
        this.f8629c = (double) ((int) d);
        this.f8630d = (double) ((int) ((d - this.f8629c) * 60.0d));
        this.f8631e = ((d - this.f8629c) - (this.f8630d / 60.0d)) * 3600.0d;
        this.f8632f = (double) ((int) d2);
        this.f8633g = (double) ((int) ((d2 - this.f8632f) * 60.0d));
        this.f8634h = ((d2 - this.f8632f) - (this.f8633g / 60.0d)) * 3600.0d;
        this.f8635i = d;
        this.f8636j = d2;
        this.f8637k = (3.141592653589793d * d) / 180.0d;
        this.f8638l = (3.141592653589793d * d2) / 180.0d;
        this.f8639m = f8628b + (((f8627a - f8628b) * (90.0d - this.f8636j)) / 90.0d);
        this.f8640n = this.f8639m * Math.cos(this.f8638l);
    }

    public static double m11362a(C1757s c1757s, C1757s c1757s2) {
        double atan = (Math.atan(Math.abs(((c1757s2.f8637k - c1757s.f8637k) * c1757s.f8640n) / ((c1757s2.f8638l - c1757s.f8638l) * c1757s.f8639m))) * 180.0d) / 3.141592653589793d;
        double d = c1757s2.f8635i - c1757s.f8635i;
        double d2 = c1757s2.f8636j - c1757s.f8636j;
        return (d <= 0.0d || d2 > 0.0d) ? (d > 0.0d || d2 >= 0.0d) ? (d >= 0.0d || d2 < 0.0d) ? atan : (90.0d - atan) + 270.0d : atan + 180.0d : (90.0d - atan) + 90.0d;
    }

    public static C1757s m11363a(double d, double d2, double d3, double d4) {
        return C1757s.m11364a(new C1757s(d, d2), d3, d4);
    }

    public static C1757s m11364a(C1757s c1757s, double d, double d2) {
        return new C1757s((((((d * 1000.0d) * Math.sin((d2 * 3.141592653589793d) / 180.0d)) / c1757s.f8640n) + c1757s.f8637k) * 180.0d) / 3.141592653589793d, (((((d * 1000.0d) * Math.cos((d2 * 3.141592653589793d) / 180.0d)) / c1757s.f8639m) + c1757s.f8638l) * 180.0d) / 3.141592653589793d);
    }
}
