package com.amap.api.mapcore;

import android.support.v4.view.ViewCompat;
import android.util.Log;
import com.amap.api.mapcore.util.bj;
import com.amap.api.mapcore.util.ce;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.WeightedLatLng;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapProjection;
import com.tencent.open.yyb.TitleBar;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.amap.api.mapcore.q */
class C0326q implements ad {
    private static float f2042m;
    private static int f2043n;
    private static int f2044o;
    private LatLng f2045a;
    private double f2046b;
    private float f2047c;
    private int f2048d;
    private int f2049e;
    private float f2050f;
    private boolean f2051g;
    private String f2052h;
    private ab f2053i;
    private FloatBuffer f2054j;
    private int f2055k;
    private boolean f2056l;

    static {
        f2042m = 4.0075016E7f;
        f2043n = Opcodes.ACC_NATIVE;
        f2044o = 20;
    }

    public C0326q(ab abVar) {
        this.f2045a = null;
        this.f2046b = 0.0d;
        this.f2047c = TitleBar.SHAREBTN_RIGHT_MARGIN;
        this.f2048d = ViewCompat.MEASURED_STATE_MASK;
        this.f2049e = 0;
        this.f2050f = 0.0f;
        this.f2051g = true;
        this.f2055k = 0;
        this.f2056l = false;
        this.f2053i = abVar;
        try {
            this.f2052h = m3323c();
        } catch (Throwable e) {
            ce.m3829a(e, "CircleDelegateImp", "create");
            e.printStackTrace();
        }
    }

    private float m3309b(double d) {
        return (float) ((Math.cos((3.141592653589793d * d) / 180.0d) * ((double) f2042m)) / ((double) (f2043n << f2044o)));
    }

    private double m3310c(double d) {
        return WeightedLatLng.DEFAULT_INTENSITY / ((double) m3309b(d));
    }

    public void m3311a(double d) {
        this.f2046b = d;
        m3328h();
    }

    public void m3312a(float f) {
        this.f2050f = f;
        this.f2053i.m2223M();
        this.f2053i.m2299f(false);
    }

    public void m3313a(int i) {
        this.f2048d = i;
    }

    public void m3314a(LatLng latLng) {
        this.f2045a = latLng;
        m3328h();
    }

    public void m3315a(GL10 gl10) {
        if (this.f2045a != null && this.f2046b > 0.0d && this.f2051g) {
            if (this.f2054j == null || this.f2055k == 0) {
                m3327g();
            }
            if (this.f2054j != null && this.f2055k > 0) {
                C0333u.m3343b(gl10, this.f2049e, this.f2048d, this.f2054j, this.f2047c, this.f2055k);
            }
            this.f2056l = true;
        }
    }

    public void m3316a(boolean z) {
        this.f2051g = z;
        this.f2053i.m2299f(false);
    }

    public boolean m3317a() {
        return true;
    }

    public boolean m3318a(aj ajVar) {
        return equals(ajVar) || ajVar.m2574c().equals(m3323c());
    }

    public void m3319b() {
        this.f2053i.m2279a(m3323c());
        this.f2053i.m2299f(false);
    }

    public void m3320b(float f) {
        this.f2047c = f;
        this.f2053i.m2299f(false);
    }

    public void m3321b(int i) {
        this.f2049e = i;
        this.f2053i.m2299f(false);
    }

    public boolean m3322b(LatLng latLng) {
        return this.f2046b >= ((double) AMapUtils.calculateLineDistance(this.f2045a, latLng));
    }

    public String m3323c() {
        if (this.f2052h == null) {
            this.f2052h = C0410w.m4243a("Circle");
        }
        return this.f2052h;
    }

    public float m3324d() {
        return this.f2050f;
    }

    public boolean m3325e() {
        return this.f2051g;
    }

    public int m3326f() {
        return 0;
    }

    public void m3327g() {
        int i = 0;
        this.f2056l = false;
        LatLng latLng = this.f2045a;
        if (latLng != null) {
            FPoint[] fPointArr = new FPoint[360];
            float[] fArr = new float[(fPointArr.length * 3)];
            double c = m3310c(this.f2045a.latitude) * this.f2046b;
            IPoint iPoint = new IPoint();
            MapProjection c2 = this.f2053i.m2289c();
            MapProjection.lonlat2Geo(latLng.longitude, latLng.latitude, iPoint);
            while (i < 360) {
                double d = (((double) i) * 3.141592653589793d) / 180.0d;
                double sin = Math.sin(d) * c;
                int i2 = (int) (sin + ((double) iPoint.f3714x));
                int cos = (int) ((Math.cos(d) * c) + ((double) iPoint.f3715y));
                FPoint fPoint = new FPoint();
                c2.geo2Map(i2, cos, fPoint);
                fPointArr[i] = fPoint;
                fArr[i * 3] = fPointArr[i].f3693x;
                fArr[(i * 3) + 1] = fPointArr[i].f3694y;
                fArr[(i * 3) + 2] = 0.0f;
                i++;
            }
            this.f2055k = fPointArr.length;
            this.f2054j = bj.m3620a(fArr);
        }
    }

    void m3328h() {
        this.f2055k = 0;
        if (this.f2054j != null) {
            this.f2054j.clear();
        }
        this.f2053i.m2299f(false);
    }

    public LatLng m3329i() {
        return this.f2045a;
    }

    public void m3330j() {
        try {
            this.f2045a = null;
            if (this.f2054j != null) {
                this.f2054j.clear();
                this.f2054j = null;
            }
        } catch (Throwable th) {
            ce.m3829a(th, "CircleDelegateImp", "destroy");
            th.printStackTrace();
            Log.d("destroy erro", "CircleDelegateImp destroy");
        }
    }

    public boolean m3331k() {
        return this.f2056l;
    }

    public double m3332l() {
        return this.f2046b;
    }

    public float m3333m() {
        return this.f2047c;
    }

    public int m3334n() {
        return this.f2048d;
    }

    public int m3335o() {
        return this.f2049e;
    }
}
