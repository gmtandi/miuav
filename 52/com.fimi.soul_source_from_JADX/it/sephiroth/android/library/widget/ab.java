package it.sephiroth.android.library.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.AutoScrollHelper;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.fimi.soul.view.photodraweeview.C2020f;
import it.sephiroth.android.library.C2811R;

public class ab {
    private static final int f14408L = 0;
    private static final int f14409M = 1;
    private static final int f14410N = 2;
    private static final int f14411O = 3;
    private static final int f14412P = 4;
    private static final int f14413Q = 7;
    private static final int f14414R = 7;
    private static final float f14415S = 1.1f;
    private static final int f14416T = 8;
    private static final int f14417U = 16;
    public static final int f14418a = 0;
    public static final int f14419b = 1;
    private static final String f14420c = "EdgeEffect";
    private static final int f14421d = 1000;
    private static final int f14422e = 167;
    private static final int f14423f = 1000;
    private static final float f14424g = 1.0f;
    private static final float f14425h = 0.5f;
    private static final float f14426i = 4.0f;
    private static final float f14427j = 1.0f;
    private static final float f14428k = 0.6f;
    private static final int f14429l = 100;
    private static final float f14430m = 0.001f;
    private static final int f14431u = 300;
    private float f14432A;
    private float f14433B;
    private float f14434C;
    private float f14435D;
    private float f14436E;
    private float f14437F;
    private float f14438G;
    private float f14439H;
    private long f14440I;
    private float f14441J;
    private final Interpolator f14442K;
    private int f14443V;
    private float f14444W;
    private final Rect f14445X;
    private final int f14446Y;
    private final int f14447Z;
    private final int aa;
    private final int ab;
    private final int f14448n;
    private final Drawable f14449o;
    private final Drawable f14450p;
    private int f14451q;
    private int f14452r;
    private int f14453s;
    private int f14454t;
    private final int f14455v;
    private float f14456w;
    private float f14457x;
    private float f14458y;
    private float f14459z;

    public ab(Context context, int i) {
        this.f14443V = f14418a;
        this.f14445X = new Rect();
        Resources resources = context.getResources();
        this.f14449o = resources.getDrawable(C2811R.drawable.hlv_overscroll_edge);
        this.f14450p = resources.getDrawable(C2811R.drawable.hlv_overscroll_glow);
        this.f14448n = i;
        this.f14446Y = this.f14449o.getIntrinsicHeight();
        this.f14447Z = this.f14450p.getIntrinsicHeight();
        this.aa = this.f14450p.getIntrinsicWidth();
        this.ab = (int) (Math.min((((((float) this.f14447Z) * f14426i) * ((float) this.f14447Z)) / ((float) this.aa)) * f14428k, ((float) this.f14447Z) * f14426i) + f14425h);
        this.f14455v = (int) ((resources.getDisplayMetrics().density * BitmapDescriptorFactory.HUE_MAGENTA) + f14425h);
        this.f14442K = new DecelerateInterpolator();
    }

    private void m16272d() {
        float min = Math.min(((float) (AnimationUtils.currentAnimationTimeMillis() - this.f14440I)) / this.f14441J, f14427j);
        float interpolation = this.f14442K.getInterpolation(min);
        this.f14456w = this.f14432A + ((this.f14433B - this.f14432A) * interpolation);
        this.f14457x = this.f14434C + ((this.f14435D - this.f14434C) * interpolation);
        this.f14458y = this.f14436E + ((this.f14437F - this.f14436E) * interpolation);
        this.f14459z = this.f14438G + ((this.f14439H - this.f14438G) * interpolation);
        if (min >= 0.999f) {
            switch (this.f14443V) {
                case f14419b /*1*/:
                    this.f14443V = f14412P;
                    this.f14440I = AnimationUtils.currentAnimationTimeMillis();
                    this.f14441J = 1000.0f;
                    this.f14432A = this.f14456w;
                    this.f14434C = this.f14457x;
                    this.f14436E = this.f14458y;
                    this.f14438G = this.f14459z;
                    this.f14433B = 0.0f;
                    this.f14435D = 0.0f;
                    this.f14437F = 0.0f;
                    this.f14439H = 0.0f;
                case f14410N /*2*/:
                    this.f14443V = f14411O;
                    this.f14440I = AnimationUtils.currentAnimationTimeMillis();
                    this.f14441J = 1000.0f;
                    this.f14432A = this.f14456w;
                    this.f14434C = this.f14457x;
                    this.f14436E = this.f14458y;
                    this.f14438G = this.f14459z;
                    this.f14433B = 0.0f;
                    this.f14435D = 0.0f;
                    this.f14437F = 0.0f;
                    this.f14439H = 0.0f;
                case f14411O /*3*/:
                    this.f14443V = f14418a;
                case f14412P /*4*/:
                    this.f14457x = ((this.f14439H != 0.0f ? f14427j / (this.f14439H * this.f14439H) : AutoScrollHelper.NO_MAX) * (interpolation * (this.f14435D - this.f14434C))) + this.f14434C;
                    this.f14443V = f14411O;
                default:
            }
        }
    }

    public Rect m16273a(boolean z) {
        int i = f14418a;
        this.f14445X.set(f14418a, f14418a, this.f14451q, this.ab);
        Rect rect = this.f14445X;
        int i2 = this.f14453s;
        int i3 = this.f14454t;
        if (z) {
            i = this.ab;
        }
        rect.offset(i2, i3 - i);
        return this.f14445X;
    }

    public void m16274a(float f) {
        long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        if (this.f14443V != f14412P || ((float) (currentAnimationTimeMillis - this.f14440I)) >= this.f14441J) {
            if (this.f14443V != f14419b) {
                this.f14459z = f14427j;
            }
            this.f14443V = f14419b;
            this.f14440I = currentAnimationTimeMillis;
            this.f14441J = 167.0f;
            this.f14444W += f;
            float abs = Math.abs(this.f14444W);
            float max = Math.max(f14428k, Math.min(abs, f14427j));
            this.f14432A = max;
            this.f14456w = max;
            abs = Math.max(f14425h, Math.min(abs * 7.0f, f14427j));
            this.f14434C = abs;
            this.f14457x = abs;
            abs = Math.min(f14427j, this.f14458y + (Math.abs(f) * f14415S));
            this.f14436E = abs;
            this.f14458y = abs;
            abs = Math.abs(f);
            if (f > 0.0f && this.f14444W < 0.0f) {
                abs = -abs;
            }
            if (this.f14444W == 0.0f) {
                this.f14459z = 0.0f;
            }
            abs = Math.min(f14426i, Math.max(0.0f, (abs * 7.0f) + this.f14459z));
            this.f14438G = abs;
            this.f14459z = abs;
            this.f14433B = this.f14456w;
            this.f14435D = this.f14457x;
            this.f14437F = this.f14458y;
            this.f14439H = this.f14459z;
        }
    }

    public void m16275a(int i) {
        this.f14443V = f14410N;
        int max = Math.max(f14429l, Math.abs(i));
        this.f14440I = AnimationUtils.currentAnimationTimeMillis();
        this.f14441J = 0.1f + (((float) max) * 0.03f);
        this.f14432A = 0.0f;
        this.f14434C = 0.0f;
        this.f14457x = 0.0f;
        this.f14436E = f14425h;
        this.f14438G = 0.0f;
        this.f14433B = (float) Math.max(f14418a, Math.min(max * f14416T, f14419b));
        this.f14435D = Math.max(f14425h, Math.min((float) (max * f14416T), f14427j));
        this.f14439H = Math.min(0.025f + (((float) ((max / f14429l) * max)) * 1.5E-4f), C2020f.f10932b);
        this.f14437F = Math.max(this.f14436E, Math.min(((float) (max * f14417U)) * 1.0E-5f, f14427j));
    }

    public void m16276a(int i, int i2) {
        this.f14451q = i;
        this.f14452r = i2;
    }

    public boolean m16277a() {
        return this.f14443V == 0;
    }

    public boolean m16278a(Canvas canvas) {
        m16272d();
        this.f14450p.setAlpha((int) (Math.max(0.0f, Math.min(this.f14458y, f14427j)) * 255.0f));
        int min = (int) Math.min((((((float) this.f14447Z) * this.f14459z) * ((float) this.f14447Z)) / ((float) this.aa)) * f14428k, ((float) this.f14447Z) * f14426i);
        if (this.f14448n == 0) {
            this.f14450p.setBounds(f14418a, f14418a, this.f14451q, min);
        } else {
            this.f14450p.setBounds(f14418a, f14418a, this.f14451q, min);
        }
        this.f14450p.draw(canvas);
        this.f14449o.setAlpha((int) (Math.max(0.0f, Math.min(this.f14456w, f14427j)) * 255.0f));
        int i = (int) (((float) this.f14446Y) * this.f14457x);
        if (this.f14448n == 0) {
            this.f14449o.setBounds(f14418a, f14418a, this.f14451q, i);
        } else {
            this.f14449o.setBounds(f14418a, f14418a, this.f14451q, i);
        }
        this.f14449o.draw(canvas);
        if (this.f14443V == f14411O && min == 0 && i == 0) {
            this.f14443V = f14418a;
        }
        return this.f14443V != 0;
    }

    public void m16279b() {
        this.f14443V = f14418a;
    }

    public void m16280b(int i, int i2) {
        this.f14453s = i;
        this.f14454t = i2;
    }

    public void m16281c() {
        this.f14444W = 0.0f;
        if (this.f14443V == f14419b || this.f14443V == f14412P) {
            this.f14443V = f14411O;
            this.f14432A = this.f14456w;
            this.f14434C = this.f14457x;
            this.f14436E = this.f14458y;
            this.f14438G = this.f14459z;
            this.f14433B = 0.0f;
            this.f14435D = 0.0f;
            this.f14437F = 0.0f;
            this.f14439H = 0.0f;
            this.f14440I = AnimationUtils.currentAnimationTimeMillis();
            this.f14441J = 1000.0f;
        }
    }
}
