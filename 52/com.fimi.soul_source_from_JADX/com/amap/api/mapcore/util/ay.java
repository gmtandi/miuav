package com.amap.api.mapcore.util;

import com.autonavi.amap.mapcore.DPoint;

public class ay {
    public final double f2142a;
    public final double f2143b;
    public final double f2144c;
    public final double f2145d;
    public final double f2146e;
    public final double f2147f;

    public ay(double d, double d2, double d3, double d4) {
        this.f2142a = d;
        this.f2143b = d3;
        this.f2144c = d2;
        this.f2145d = d4;
        this.f2146e = (d + d2) / 2.0d;
        this.f2147f = (d3 + d4) / 2.0d;
    }

    public boolean m3489a(double d, double d2) {
        return this.f2142a <= d && d <= this.f2144c && this.f2143b <= d2 && d2 <= this.f2145d;
    }

    public boolean m3490a(double d, double d2, double d3, double d4) {
        return d < this.f2144c && this.f2142a < d2 && d3 < this.f2145d && this.f2143b < d4;
    }

    public boolean m3491a(ay ayVar) {
        return m3490a(ayVar.f2142a, ayVar.f2144c, ayVar.f2143b, ayVar.f2145d);
    }

    public boolean m3492a(DPoint dPoint) {
        return m3489a(dPoint.f3691x, dPoint.f3692y);
    }

    public boolean m3493b(ay ayVar) {
        return ayVar.f2142a >= this.f2142a && ayVar.f2144c <= this.f2144c && ayVar.f2143b >= this.f2143b && ayVar.f2145d <= this.f2145d;
    }
}
