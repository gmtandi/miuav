package com.amap.api.mapcore;

import android.content.Context;
import android.view.ViewConfiguration;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import com.amap.api.maps.model.WeightedLatLng;
import com.fimi.soul.view.photodraweeview.C2020f;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.amap.api.mapcore.o */
public class C0323o {
    private static float f1966J;
    private static final float[] f1967K;
    private static final float[] f1968L;
    private static float f1969Q;
    private static float f1970R;
    private float f1971A;
    private float f1972B;
    private boolean f1973C;
    private Interpolator f1974D;
    private boolean f1975E;
    private float f1976F;
    private float f1977G;
    private int f1978H;
    private float f1979I;
    private float f1980M;
    private final float f1981N;
    private float f1982O;
    private boolean f1983P;
    private int f1984a;
    private int f1985b;
    private int f1986c;
    private float f1987d;
    private float f1988e;
    private float f1989f;
    private int f1990g;
    private int f1991h;
    private float f1992i;
    private float f1993j;
    private float f1994k;
    private int f1995l;
    private int f1996m;
    private int f1997n;
    private int f1998o;
    private int f1999p;
    private int f2000q;
    private float f2001r;
    private float f2002s;
    private float f2003t;
    private long f2004u;
    private long f2005v;
    private float f2006w;
    private float f2007x;
    private float f2008y;
    private float f2009z;

    static {
        float f = 0.0f;
        f1966J = (float) (Math.log(0.78d) / Math.log(0.9d));
        f1967K = new float[Opcodes.LSUB];
        f1968L = new float[Opcodes.LSUB];
        int i = 0;
        float f2 = 0.0f;
        while (i < 100) {
            float f3;
            float f4 = ((float) i) / 100.0f;
            float f5 = C2020f.f10933c;
            float f6 = f2;
            while (true) {
                f2 = ((f5 - f6) / 2.0f) + f6;
                f3 = (C2020f.f10931a * f2) * (C2020f.f10933c - f2);
                float f7 = ((((C2020f.f10933c - f2) * 0.175f) + (0.35000002f * f2)) * f3) + ((f2 * f2) * f2);
                if (((double) Math.abs(f7 - f4)) < 1.0E-5d) {
                    break;
                } else if (f7 > f4) {
                    f5 = f2;
                } else {
                    f6 = f2;
                }
            }
            f1967K[i] = (f2 * (f2 * f2)) + (f3 * (((C2020f.f10933c - f2) * 0.5f) + f2));
            f5 = C2020f.f10933c;
            while (true) {
                f2 = ((f5 - f) / 2.0f) + f;
                f3 = (C2020f.f10931a * f2) * (C2020f.f10933c - f2);
                f7 = ((((C2020f.f10933c - f2) * 0.5f) + f2) * f3) + ((f2 * f2) * f2);
                if (((double) Math.abs(f7 - f4)) < 1.0E-5d) {
                    break;
                } else if (f7 > f4) {
                    f5 = f2;
                } else {
                    f = f2;
                }
            }
            f1968L[i] = (f2 * (f2 * f2)) + ((((C2020f.f10933c - f2) * 0.175f) + (0.35000002f * f2)) * f3);
            i++;
            f2 = f6;
        }
        float[] fArr = f1967K;
        f1968L[100] = C2020f.f10933c;
        fArr[100] = C2020f.f10933c;
        f1969Q = 8.0f;
        f1970R = C2020f.f10933c;
        f1970R = C2020f.f10933c / C0323o.m3270a((float) C2020f.f10933c);
    }

    public C0323o(Context context) {
        this(context, null);
    }

    public C0323o(Context context, Interpolator interpolator) {
        this(context, interpolator, context.getApplicationInfo().targetSdkVersion >= 11);
    }

    public C0323o(Context context, Interpolator interpolator, boolean z) {
        this.f1979I = ViewConfiguration.getScrollFriction();
        this.f1973C = true;
        this.f1974D = interpolator;
        this.f1981N = context.getResources().getDisplayMetrics().density * 160.0f;
        this.f1980M = m3271b(ViewConfiguration.getScrollFriction());
        this.f1975E = z;
        this.f1982O = m3271b(0.84f);
    }

    static float m3270a(float f) {
        float f2 = f1969Q * f;
        return (f2 < C2020f.f10933c ? f2 - (C2020f.f10933c - ((float) Math.exp((double) (-f2)))) : ((C2020f.f10933c - ((float) Math.exp((double) (C2020f.f10933c - f2)))) * (C2020f.f10933c - 0.36787945f)) + 0.36787945f) * f1970R;
    }

    private float m3271b(float f) {
        return (386.0878f * this.f1981N) * f;
    }

    private double m3272c(float f) {
        return Math.log((double) ((0.35f * Math.abs(f)) / (this.f1979I * this.f1982O)));
    }

    private int m3273d(float f) {
        return (int) (Math.exp(m3272c(f) / (((double) f1966J) - WeightedLatLng.DEFAULT_INTENSITY)) * 1000.0d);
    }

    private double m3274e(float f) {
        return Math.exp(m3272c(f) * (((double) f1966J) / (((double) f1966J) - WeightedLatLng.DEFAULT_INTENSITY))) * ((double) (this.f1979I * this.f1982O));
    }

    public void m3275a(int i, int i2, float f, float f2, float f3, int i3, int i4, float f4, float f5, float f6, long j) {
        this.f1984a = 2;
        this.f1973C = false;
        this.f2005v = j;
        this.f2004u = AnimationUtils.currentAnimationTimeMillis();
        this.f1985b = i;
        this.f1986c = i2;
        this.f1987d = f;
        this.f1988e = f2;
        this.f1989f = f3;
        this.f1990g = i + i3;
        this.f1991h = i2 + i4;
        this.f1992i = f + f4;
        this.f1993j = f2 + f5;
        this.f1994k = f3 + f6;
        this.f2007x = (float) i3;
        this.f2008y = (float) i4;
        this.f2009z = f4;
        this.f1971A = f5;
        this.f1972B = f6;
        this.f2006w = C2020f.f10933c / ((float) this.f2005v);
    }

    public void m3276a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        float g;
        float f;
        float f2 = C2020f.f10933c;
        if (this.f1975E && !this.f1973C) {
            g = m3286g();
            f = (float) (this.f1990g - this.f1985b);
            float f3 = (float) (this.f1991h - this.f1986c);
            float sqrt = (float) Math.sqrt((double) ((f * f) + (f3 * f3)));
            f = (f / sqrt) * g;
            g *= f3 / sqrt;
            if (Math.signum((float) i3) == Math.signum(f) && Math.signum((float) i4) == Math.signum(g)) {
                i3 = (int) (f + ((float) i3));
                i4 = (int) (g + ((float) i4));
            }
        }
        this.f1984a = 1;
        this.f1973C = false;
        f = (float) Math.sqrt((double) ((i3 * i3) + (i4 * i4)));
        this.f1976F = f;
        this.f2005v = (long) m3273d(f);
        this.f2004u = AnimationUtils.currentAnimationTimeMillis();
        this.f1985b = i;
        this.f1986c = i2;
        g = f == 0.0f ? C2020f.f10933c : ((float) i3) / f;
        if (f != 0.0f) {
            f2 = ((float) i4) / f;
        }
        double e = m3274e(f);
        this.f1978H = (int) (((double) Math.signum(f)) * e);
        this.f1995l = i5;
        this.f1996m = i6;
        this.f1997n = i7;
        this.f1998o = i8;
        this.f1990g = ((int) Math.round(((double) g) * e)) + i;
        this.f1990g = Math.min(this.f1990g, this.f1996m);
        this.f1990g = Math.max(this.f1990g, this.f1995l);
        this.f1991h = ((int) Math.round(((double) f2) * e)) + i2;
        this.f1991h = Math.min(this.f1991h, this.f1998o);
        this.f1991h = Math.max(this.f1991h, this.f1997n);
    }

    public void m3277a(Interpolator interpolator) {
        this.f1974D = interpolator;
    }

    public final void m3278a(boolean z) {
        this.f1973C = z;
    }

    public final boolean m3279a() {
        return this.f1973C;
    }

    public final int m3280b() {
        return this.f1999p;
    }

    public void m3281b(boolean z) {
        this.f1983P = z;
    }

    public final int m3282c() {
        return this.f2000q;
    }

    public final float m3283d() {
        return this.f2001r;
    }

    public final float m3284e() {
        return this.f2002s;
    }

    public final float m3285f() {
        return this.f2003t;
    }

    public float m3286g() {
        return this.f1984a == 1 ? this.f1977G : this.f1976F - ((this.f1980M * ((float) m3288i())) / 2000.0f);
    }

    public boolean m3287h() {
        if (this.f1973C) {
            return false;
        }
        int currentAnimationTimeMillis = (int) (AnimationUtils.currentAnimationTimeMillis() - this.f2004u);
        if (((long) currentAnimationTimeMillis) < this.f2005v) {
            float f;
            switch (this.f1984a) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    float f2 = ((float) currentAnimationTimeMillis) / ((float) this.f2005v);
                    int i = (int) (100.0f * f2);
                    float f3 = C2020f.f10933c;
                    f = 0.0f;
                    if (i < 100) {
                        f3 = ((float) i) / 100.0f;
                        f = ((float) (i + 1)) / 100.0f;
                        float f4 = f1967K[i];
                        f = (f1967K[i + 1] - f4) / (f - f3);
                        f3 = ((f2 - f3) * f) + f4;
                    }
                    this.f1977G = ((f * ((float) this.f1978H)) / ((float) this.f2005v)) * 1000.0f;
                    this.f1999p = this.f1985b + Math.round(((float) (this.f1990g - this.f1985b)) * f3);
                    this.f1999p = Math.min(this.f1999p, this.f1996m);
                    this.f1999p = Math.max(this.f1999p, this.f1995l);
                    this.f2000q = this.f1986c + Math.round(f3 * ((float) (this.f1991h - this.f1986c)));
                    this.f2000q = Math.min(this.f2000q, this.f1998o);
                    this.f2000q = Math.max(this.f2000q, this.f1997n);
                    if (this.f1999p == this.f1990g && this.f2000q == this.f1991h) {
                        this.f1973C = true;
                        break;
                    }
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    f = ((float) currentAnimationTimeMillis) * this.f2006w;
                    f = this.f1974D == null ? C0323o.m3270a(f) : this.f1974D.getInterpolation(f);
                    this.f1999p = this.f1985b + Math.round(this.f2007x * f);
                    this.f2000q = this.f1986c + Math.round(this.f2008y * f);
                    this.f2001r = this.f1987d + (this.f2009z * f);
                    this.f2002s = this.f1988e + (this.f1971A * f);
                    this.f2003t = (f * this.f1972B) + this.f1989f;
                    break;
            }
        }
        this.f1999p = this.f1990g;
        this.f2000q = this.f1991h;
        this.f2001r = this.f1992i;
        this.f2002s = this.f1993j;
        this.f2003t = this.f1994k;
        this.f1973C = true;
        return true;
    }

    public int m3288i() {
        return (int) (AnimationUtils.currentAnimationTimeMillis() - this.f2004u);
    }

    public final int m3289j() {
        return this.f1984a;
    }

    public boolean m3290k() {
        return this.f1983P;
    }
}
