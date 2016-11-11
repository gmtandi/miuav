package it.sephiroth.android.library.widget;

import android.content.Context;
import android.util.Log;
import android.view.ViewConfiguration;
import android.view.animation.AnimationUtils;
import com.amap.api.maps.model.WeightedLatLng;
import com.fimi.soul.view.photodraweeview.C2020f;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;

class at {
    private static final int f14501A = 1;
    private static final int f14502B = 2;
    private static final float f14503o = 2000.0f;
    private static float f14504q = 0.0f;
    private static final float f14505r = 0.35f;
    private static final float f14506s = 0.5f;
    private static final float f14507t = 1.0f;
    private static final float f14508u = 0.175f;
    private static final float f14509v = 0.35000002f;
    private static final int f14510w = 100;
    private static final float[] f14511x;
    private static final float[] f14512y;
    private static final int f14513z = 0;
    private int f14514a;
    private int f14515b;
    private int f14516c;
    private int f14517d;
    private float f14518e;
    private float f14519f;
    private long f14520g;
    private int f14521h;
    private int f14522i;
    private int f14523j;
    private boolean f14524k;
    private int f14525l;
    private float f14526m;
    private int f14527n;
    private float f14528p;

    static {
        float f = 0.0f;
        f14504q = (float) (Math.log(0.78d) / Math.log(0.9d));
        f14511x = new float[Opcodes.LSUB];
        f14512y = new float[Opcodes.LSUB];
        int i = 0;
        float f2 = 0.0f;
        while (i < f14510w) {
            float f3;
            float f4 = ((float) i) / 100.0f;
            float f5 = f14507t;
            float f6 = f2;
            while (true) {
                f2 = ((f5 - f6) / 2.0f) + f6;
                f3 = (C2020f.f10931a * f2) * (f14507t - f2);
                float f7 = ((((f14507t - f2) * f14508u) + (f14509v * f2)) * f3) + ((f2 * f2) * f2);
                if (((double) Math.abs(f7 - f4)) < 1.0E-5d) {
                    break;
                } else if (f7 > f4) {
                    f5 = f2;
                } else {
                    f6 = f2;
                }
            }
            f14511x[i] = (f2 * (f2 * f2)) + (f3 * (((f14507t - f2) * f14506s) + f2));
            f5 = f14507t;
            while (true) {
                f2 = ((f5 - f) / 2.0f) + f;
                f3 = (C2020f.f10931a * f2) * (f14507t - f2);
                f7 = ((((f14507t - f2) * f14506s) + f2) * f3) + ((f2 * f2) * f2);
                if (((double) Math.abs(f7 - f4)) < 1.0E-5d) {
                    break;
                } else if (f7 > f4) {
                    f5 = f2;
                } else {
                    f = f2;
                }
            }
            f14512y[i] = (f2 * (f2 * f2)) + ((((f14507t - f2) * f14508u) + (f14509v * f2)) * f3);
            i += f14501A;
            f2 = f6;
        }
        float[] fArr = f14511x;
        f14512y[f14510w] = f14507t;
        fArr[f14510w] = f14507t;
    }

    at(Context context) {
        this.f14526m = ViewConfiguration.getScrollFriction();
        this.f14527n = 0;
        this.f14524k = true;
        this.f14528p = ((context.getResources().getDisplayMetrics().density * 160.0f) * 386.0878f) * 0.84f;
    }

    private void m16340a(int i, int i2, int i3, int i4) {
        boolean z = true;
        if (i <= i2 || i >= i3) {
            boolean z2 = i > i3;
            int i5 = z2 ? i3 : i2;
            int i6 = i - i5;
            if (i6 * i4 < 0) {
                z = false;
            }
            if (z) {
                m16357g(i, i5, i4);
                return;
            } else if (m16350e(i4) > ((double) Math.abs(i6))) {
                m16363a(i, i4, z2 ? i2 : i, z2 ? i : i3, this.f14525l);
                return;
            } else {
                m16352e(i, i5, i4);
                return;
            }
        }
        Log.e("OverScroller", "startAfterEdge called from a valid position");
        this.f14524k = true;
    }

    private static float m16344c(int i) {
        return i > 0 ? -2000.0f : f14503o;
    }

    private double m16346d(int i) {
        return Math.log((double) ((f14505r * ((float) Math.abs(i))) / (this.f14526m * this.f14528p)));
    }

    private void m16348d() {
        float abs = ((float) (this.f14517d * this.f14517d)) / (Math.abs(this.f14519f) * 2.0f);
        float signum = Math.signum((float) this.f14517d);
        if (abs > ((float) this.f14525l)) {
            this.f14519f = (((-signum) * ((float) this.f14517d)) * ((float) this.f14517d)) / (((float) this.f14525l) * 2.0f);
            abs = (float) this.f14525l;
        }
        this.f14525l = (int) abs;
        this.f14527n = f14502B;
        int i = this.f14514a;
        if (this.f14517d <= 0) {
            abs = -abs;
        }
        this.f14516c = ((int) abs) + i;
        this.f14521h = -((int) ((1000.0f * ((float) this.f14517d)) / this.f14519f));
    }

    private void m16349d(int i, int i2, int i3) {
        float abs = Math.abs(((float) (i3 - i)) / ((float) (i2 - i)));
        int i4 = (int) (100.0f * abs);
        if (i4 < f14510w) {
            float f = ((float) i4) / 100.0f;
            float f2 = ((float) (i4 + f14501A)) / 100.0f;
            float f3 = f14512y[i4];
            this.f14521h = (int) (((((abs - f) / (f2 - f)) * (f14512y[i4 + f14501A] - f3)) + f3) * ((float) this.f14521h));
        }
    }

    private double m16350e(int i) {
        return Math.exp(m16346d(i) * (((double) f14504q) / (((double) f14504q) - WeightedLatLng.DEFAULT_INTENSITY))) * ((double) (this.f14526m * this.f14528p));
    }

    private void m16352e(int i, int i2, int i3) {
        this.f14524k = false;
        this.f14527n = f14501A;
        this.f14514a = i;
        this.f14516c = i2;
        int i4 = i - i2;
        this.f14519f = m16344c(i4);
        this.f14517d = -i4;
        this.f14525l = Math.abs(i4);
        this.f14521h = (int) (Math.sqrt((((double) i4) * -2.0d) / ((double) this.f14519f)) * 1000.0d);
    }

    private int m16353f(int i) {
        return (int) (Math.exp(m16346d(i) / (((double) f14504q) - WeightedLatLng.DEFAULT_INTENSITY)) * 1000.0d);
    }

    private void m16355f(int i, int i2, int i3) {
        float sqrt = (float) Math.sqrt((((double) (((((float) (i3 * i3)) / 2.0f) / Math.abs(this.f14519f)) + ((float) Math.abs(i2 - i)))) * 2.0d) / ((double) Math.abs(this.f14519f)));
        this.f14520g -= (long) ((int) ((sqrt - (((float) (-i3)) / this.f14519f)) * 1000.0f));
        this.f14514a = i2;
        this.f14517d = (int) ((-this.f14519f) * sqrt);
    }

    private void m16357g(int i, int i2, int i3) {
        this.f14519f = m16344c(i3 == 0 ? i - i2 : i3);
        m16355f(i, i2, i3);
        m16348d();
    }

    void m16359a() {
        this.f14515b = this.f14516c;
        this.f14524k = true;
    }

    void m16360a(float f) {
        this.f14526m = f;
    }

    void m16361a(int i) {
        this.f14516c = i;
        this.f14524k = false;
    }

    void m16362a(int i, int i2, int i3) {
        this.f14524k = false;
        this.f14514a = i;
        this.f14516c = i + i2;
        this.f14520g = AnimationUtils.currentAnimationTimeMillis();
        this.f14521h = i3;
        this.f14519f = 0.0f;
        this.f14517d = 0;
    }

    void m16363a(int i, int i2, int i3, int i4, int i5) {
        this.f14525l = i5;
        this.f14524k = false;
        this.f14517d = i2;
        this.f14518e = (float) i2;
        this.f14522i = 0;
        this.f14521h = 0;
        this.f14520g = AnimationUtils.currentAnimationTimeMillis();
        this.f14514a = i;
        this.f14515b = i;
        if (i > i4 || i < i3) {
            m16340a(i, i3, i4, i2);
            return;
        }
        this.f14527n = 0;
        double d = 0.0d;
        if (i2 != 0) {
            int f = m16353f(i2);
            this.f14522i = f;
            this.f14521h = f;
            d = m16350e(i2);
        }
        this.f14523j = (int) (d * ((double) Math.signum((float) i2)));
        this.f14516c = this.f14523j + i;
        if (this.f14516c < i3) {
            m16349d(this.f14514a, this.f14516c, i3);
            this.f14516c = i3;
        }
        if (this.f14516c > i4) {
            m16349d(this.f14514a, this.f14516c, i4);
            this.f14516c = i4;
        }
    }

    void m16364b(float f) {
        this.f14515b = this.f14514a + Math.round(((float) (this.f14516c - this.f14514a)) * f);
    }

    void m16365b(int i) {
        this.f14521h = ((int) (AnimationUtils.currentAnimationTimeMillis() - this.f14520g)) + i;
        this.f14524k = false;
    }

    boolean m16366b() {
        switch (this.f14527n) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                if (this.f14521h < this.f14522i) {
                    this.f14514a = this.f14516c;
                    this.f14517d = (int) this.f14518e;
                    this.f14519f = m16344c(this.f14517d);
                    this.f14520g += (long) this.f14521h;
                    m16348d();
                    break;
                }
                return false;
            case f14501A /*1*/:
                return false;
            case f14502B /*2*/:
                this.f14520g += (long) this.f14521h;
                m16352e(this.f14516c, this.f14514a, 0);
                break;
        }
        m16369c();
        return true;
    }

    boolean m16367b(int i, int i2, int i3) {
        this.f14524k = true;
        this.f14516c = i;
        this.f14514a = i;
        this.f14517d = 0;
        this.f14520g = AnimationUtils.currentAnimationTimeMillis();
        this.f14521h = 0;
        if (i < i2) {
            m16352e(i, i2, 0);
        } else if (i > i3) {
            m16352e(i, i3, 0);
        }
        return !this.f14524k;
    }

    void m16368c(int i, int i2, int i3) {
        if (this.f14527n == 0) {
            this.f14525l = i3;
            this.f14520g = AnimationUtils.currentAnimationTimeMillis();
            m16340a(i, i2, i2, (int) this.f14518e);
        }
    }

    boolean m16369c() {
        long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis() - this.f14520g;
        if (currentAnimationTimeMillis > ((long) this.f14521h)) {
            return false;
        }
        double d = 0.0d;
        float f;
        float f2;
        float f3;
        switch (this.f14527n) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                f = ((float) currentAnimationTimeMillis) / ((float) this.f14522i);
                int i = (int) (100.0f * f);
                float f4 = f14507t;
                f2 = 0.0f;
                if (i < f14510w) {
                    f4 = ((float) i) / 100.0f;
                    f2 = ((float) (i + f14501A)) / 100.0f;
                    f3 = f14511x[i];
                    f2 = (f14511x[i + f14501A] - f3) / (f2 - f4);
                    f4 = ((f - f4) * f2) + f3;
                }
                double d2 = (double) (f4 * ((float) this.f14523j));
                this.f14518e = ((f2 * ((float) this.f14523j)) / ((float) this.f14522i)) * 1000.0f;
                d = d2;
                break;
            case f14501A /*1*/:
                f = ((float) currentAnimationTimeMillis) / ((float) this.f14521h);
                float f5 = f * f;
                f3 = Math.signum((float) this.f14517d);
                d = (double) ((((float) this.f14525l) * f3) * ((C2020f.f10931a * f5) - ((2.0f * f) * f5)));
                this.f14518e = ((-f) + f5) * ((f3 * ((float) this.f14525l)) * 6.0f);
                break;
            case f14502B /*2*/:
                f2 = ((float) currentAnimationTimeMillis) / 1000.0f;
                this.f14518e = ((float) this.f14517d) + (this.f14519f * f2);
                d = (double) (((f2 * (this.f14519f * f2)) / 2.0f) + (((float) this.f14517d) * f2));
                break;
        }
        this.f14515b = ((int) Math.round(d)) + this.f14514a;
        return true;
    }
}
