package com.amap.api.mapcore;

import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import com.amap.api.mapcore.util.ce;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.WeightedLatLng;
import com.autonavi.amap.mapcore.AMapNativeRenderer;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapProjection;
import com.tencent.open.yyb.TitleBar;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.amap.api.mapcore.m */
class C0321m implements ac {
    float f1946a;
    float f1947b;
    float f1948c;
    float f1949d;
    private LatLng f1950e;
    private LatLng f1951f;
    private LatLng f1952g;
    private float f1953h;
    private int f1954i;
    private float f1955j;
    private boolean f1956k;
    private String f1957l;
    private ab f1958m;
    private float[] f1959n;
    private int f1960o;
    private boolean f1961p;
    private double f1962q;
    private double f1963r;
    private double f1964s;

    public C0321m(ab abVar) {
        this.f1953h = TitleBar.SHAREBTN_RIGHT_MARGIN;
        this.f1954i = ViewCompat.MEASURED_STATE_MASK;
        this.f1955j = 0.0f;
        this.f1956k = true;
        this.f1960o = 0;
        this.f1961p = false;
        this.f1962q = 0.0d;
        this.f1963r = 0.0d;
        this.f1964s = 0.0d;
        this.f1958m = abVar;
        try {
            this.f1957l = m3259c();
        } catch (Throwable e) {
            ce.m3829a(e, "ArcDelegateImp", "create");
            e.printStackTrace();
        }
    }

    private double m3245a(double d, double d2, double d3, double d4) {
        double d5 = (d2 - d4) / this.f1962q;
        if (Math.abs(d5) > WeightedLatLng.DEFAULT_INTENSITY) {
            d5 = Math.signum(d5);
        }
        d5 = Math.asin(d5);
        return d5 >= 0.0d ? d3 < d ? 3.141592653589793d - Math.abs(d5) : d5 : d3 < d ? 3.141592653589793d - d5 : d5 + 6.283185307179586d;
    }

    private FPoint m3246a(MapProjection mapProjection, double d, double d2, double d3) {
        int cos = (int) ((Math.cos(d) * this.f1962q) + d2);
        int i = (int) (((-Math.sin(d)) * this.f1962q) + d3);
        FPoint fPoint = new FPoint();
        mapProjection.geo2Map(cos, i, fPoint);
        return fPoint;
    }

    private boolean m3247l() {
        return Math.abs(((this.f1950e.latitude - this.f1951f.latitude) * (this.f1951f.longitude - this.f1952g.longitude)) - ((this.f1950e.longitude - this.f1951f.longitude) * (this.f1951f.latitude - this.f1952g.latitude))) >= 1.0E-6d;
    }

    private DPoint m3248m() {
        IPoint iPoint = new IPoint();
        this.f1958m.m2243a(this.f1950e.latitude, this.f1950e.longitude, iPoint);
        IPoint iPoint2 = new IPoint();
        this.f1958m.m2243a(this.f1951f.latitude, this.f1951f.longitude, iPoint2);
        IPoint iPoint3 = new IPoint();
        this.f1958m.m2243a(this.f1952g.latitude, this.f1952g.longitude, iPoint3);
        double d = (double) iPoint.f3714x;
        double d2 = (double) iPoint.f3715y;
        double d3 = (double) iPoint2.f3714x;
        double d4 = (double) iPoint2.f3715y;
        double d5 = (double) iPoint3.f3714x;
        double d6 = (double) iPoint3.f3715y;
        double d7 = (((d6 - d2) * ((((d4 * d4) - (d2 * d2)) + (d3 * d3)) - (d * d))) + ((d4 - d2) * ((((d2 * d2) - (d6 * d6)) + (d * d)) - (d5 * d5)))) / (((2.0d * (d3 - d)) * (d6 - d2)) - ((2.0d * (d5 - d)) * (d4 - d2)));
        double d8 = (((d5 - d) * ((((d3 * d3) - (d * d)) + (d4 * d4)) - (d2 * d2))) + ((d3 - d) * ((((d * d) - (d5 * d5)) + (d2 * d2)) - (d6 * d6)))) / (((2.0d * (d4 - d2)) * (d5 - d)) - ((2.0d * (d6 - d2)) * (d3 - d)));
        this.f1962q = Math.sqrt(((d - d7) * (d - d7)) + ((d2 - d8) * (d2 - d8)));
        this.f1963r = m3245a(d7, d8, d, d2);
        d = m3245a(d7, d8, d3, d4);
        this.f1964s = m3245a(d7, d8, d5, d6);
        if (this.f1963r < this.f1964s) {
            if (d <= this.f1963r || d >= this.f1964s) {
                this.f1964s -= 6.283185307179586d;
            }
        } else if (d <= this.f1964s || d >= this.f1963r) {
            this.f1964s += 6.283185307179586d;
        }
        return new DPoint(d7, d8);
    }

    public void m3249a(float f) {
        this.f1955j = f;
        this.f1958m.m2223M();
        this.f1958m.m2299f(false);
    }

    public void m3250a(int i) {
        this.f1954i = i;
        this.f1946a = ((float) Color.alpha(i)) / 255.0f;
        this.f1947b = ((float) Color.red(i)) / 255.0f;
        this.f1948c = ((float) Color.green(i)) / 255.0f;
        this.f1949d = ((float) Color.blue(i)) / 255.0f;
        this.f1958m.m2299f(false);
    }

    public void m3251a(LatLng latLng) {
        this.f1950e = latLng;
    }

    public void m3252a(GL10 gl10) {
        if (this.f1950e != null && this.f1951f != null && this.f1952g != null && this.f1956k) {
            if (this.f1959n == null || this.f1960o == 0) {
                m3264g();
            }
            if (this.f1959n != null && this.f1960o > 0) {
                float mapLenWithWin = this.f1958m.m2289c().getMapLenWithWin((int) this.f1953h);
                this.f1958m.m2289c().getMapLenWithWin(1);
                AMapNativeRenderer.nativeDrawLineByTextureID(this.f1959n, this.f1959n.length, mapLenWithWin, this.f1958m.m2280b(), this.f1947b, this.f1948c, this.f1949d, this.f1946a, 0.0f, false, true, false);
            }
            this.f1961p = true;
        }
    }

    public void m3253a(boolean z) {
        this.f1956k = z;
        this.f1958m.m2299f(false);
    }

    public boolean m3254a() {
        return true;
    }

    public boolean m3255a(aj ajVar) {
        return equals(ajVar) || ajVar.m2574c().equals(m3259c());
    }

    public void m3256b() {
        this.f1958m.m2279a(m3259c());
        this.f1958m.m2299f(false);
    }

    public void m3257b(float f) {
        this.f1953h = f;
        this.f1958m.m2299f(false);
    }

    public void m3258b(LatLng latLng) {
        this.f1951f = latLng;
    }

    public String m3259c() {
        if (this.f1957l == null) {
            this.f1957l = C0410w.m4243a("Arc");
        }
        return this.f1957l;
    }

    public void m3260c(LatLng latLng) {
        this.f1952g = latLng;
    }

    public float m3261d() {
        return this.f1955j;
    }

    public boolean m3262e() {
        return this.f1956k;
    }

    public int m3263f() {
        return 0;
    }

    public void m3264g() {
        int i = 0;
        if (this.f1950e != null && this.f1951f != null && this.f1952g != null && this.f1956k) {
            try {
                this.f1961p = false;
                MapProjection c = this.f1958m.m2289c();
                FPoint fPoint;
                if (m3247l()) {
                    DPoint m = m3248m();
                    int abs = (int) ((Math.abs(this.f1964s - this.f1963r) * 180.0d) / 3.141592653589793d);
                    double d = (this.f1964s - this.f1963r) / ((double) abs);
                    FPoint[] fPointArr = new FPoint[(abs + 1)];
                    this.f1959n = new float[(fPointArr.length * 3)];
                    for (int i2 = 0; i2 <= abs; i2++) {
                        MapProjection mapProjection;
                        if (i2 == abs) {
                            fPoint = new FPoint();
                            this.f1958m.m2242a(this.f1952g.latitude, this.f1952g.longitude, fPoint);
                            fPointArr[i2] = fPoint;
                        } else {
                            mapProjection = c;
                            fPointArr[i2] = m3246a(mapProjection, (((double) i2) * d) + this.f1963r, m.f3691x, m.f3692y);
                        }
                        mapProjection = c;
                        fPointArr[i2] = m3246a(mapProjection, (((double) i2) * d) + this.f1963r, m.f3691x, m.f3692y);
                        this.f1959n[i2 * 3] = fPointArr[i2].f3693x;
                        this.f1959n[(i2 * 3) + 1] = fPointArr[i2].f3694y;
                        this.f1959n[(i2 * 3) + 2] = 0.0f;
                    }
                    this.f1960o = fPointArr.length;
                    return;
                }
                FPoint[] fPointArr2 = new FPoint[3];
                this.f1959n = new float[(fPointArr2.length * 3)];
                fPoint = new FPoint();
                this.f1958m.m2242a(this.f1950e.latitude, this.f1950e.longitude, fPoint);
                fPointArr2[0] = fPoint;
                fPoint = new FPoint();
                this.f1958m.m2242a(this.f1951f.latitude, this.f1951f.longitude, fPoint);
                fPointArr2[1] = fPoint;
                fPoint = new FPoint();
                this.f1958m.m2242a(this.f1952g.latitude, this.f1952g.longitude, fPoint);
                fPointArr2[2] = fPoint;
                while (i < 3) {
                    this.f1959n[i * 3] = fPointArr2[i].f3693x;
                    this.f1959n[(i * 3) + 1] = fPointArr2[i].f3694y;
                    this.f1959n[(i * 3) + 2] = 0.0f;
                    i++;
                }
                this.f1960o = fPointArr2.length;
            } catch (Throwable th) {
                ce.m3829a(th, "ArcDelegateImp", "calMapFPoint");
                th.printStackTrace();
            }
        }
    }

    public float m3265h() {
        return this.f1953h;
    }

    public int m3266i() {
        return this.f1954i;
    }

    public void m3267j() {
        try {
            this.f1950e = null;
            this.f1951f = null;
            this.f1952g = null;
        } catch (Throwable th) {
            ce.m3829a(th, "ArcDelegateImp", "destroy");
            th.printStackTrace();
            Log.d("destroy erro", "ArcDelegateImp destroy");
        }
    }

    public boolean m3268k() {
        return this.f1961p;
    }
}
