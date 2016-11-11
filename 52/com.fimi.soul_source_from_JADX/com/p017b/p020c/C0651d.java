package com.p017b.p020c;

import android.view.View;
import android.view.animation.Interpolator;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.p017b.p018a.C0616a;
import com.p017b.p018a.C0620b;
import com.p017b.p018a.as;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: com.b.c.d */
class C0651d extends C0650c {
    private static final int f3946k = 0;
    private static final int f3947l = 1;
    private static final int f3948m = 2;
    private static final int f3949n = 4;
    private static final int f3950o = 8;
    private static final int f3951p = 16;
    private static final int f3952q = 32;
    private static final int f3953r = 64;
    private static final int f3954s = 128;
    private static final int f3955t = 256;
    private static final int f3956u = 512;
    private static final int f3957v = 511;
    ArrayList<C0654g> f3958a;
    private final WeakReference<View> f3959b;
    private long f3960c;
    private boolean f3961d;
    private long f3962e;
    private boolean f3963f;
    private Interpolator f3964g;
    private boolean f3965h;
    private C0620b f3966i;
    private C0653f f3967j;
    private Runnable f3968w;
    private HashMap<C0616a, C0655h> f3969x;

    C0651d(View view) {
        this.f3961d = false;
        this.f3962e = 0;
        this.f3963f = false;
        this.f3965h = false;
        this.f3966i = null;
        this.f3967j = new C0653f();
        this.f3958a = new ArrayList();
        this.f3968w = new C0652e(this);
        this.f3969x = new HashMap();
        this.f3959b = new WeakReference(view);
    }

    private float m5830a(int i) {
        View view = (View) this.f3959b.get();
        if (view != null) {
            switch (i) {
                case f3947l /*1*/:
                    return view.getTranslationX();
                case f3948m /*2*/:
                    return view.getTranslationY();
                case f3949n /*4*/:
                    return view.getScaleX();
                case f3950o /*8*/:
                    return view.getScaleY();
                case f3951p /*16*/:
                    return view.getRotation();
                case f3952q /*32*/:
                    return view.getRotationX();
                case f3953r /*64*/:
                    return view.getRotationY();
                case f3954s /*128*/:
                    return view.getX();
                case f3955t /*256*/:
                    return view.getY();
                case f3956u /*512*/:
                    return view.getAlpha();
            }
        }
        return 0.0f;
    }

    private void m5832a(int i, float f) {
        float a = m5830a(i);
        m5833a(i, a, f - a);
    }

    private void m5833a(int i, float f, float f2) {
        if (this.f3969x.size() > 0) {
            for (C0616a c0616a : this.f3969x.keySet()) {
                C0655h c0655h = (C0655h) this.f3969x.get(c0616a);
                if (c0655h.m5874a(i) && c0655h.f3975a == 0) {
                    break;
                }
            }
            C0616a c0616a2 = null;
            if (c0616a2 != null) {
                c0616a2.m5382b();
            }
        }
        this.f3958a.add(new C0654g(i, f, f2));
        View view = (View) this.f3959b.get();
        if (view != null) {
            view.removeCallbacks(this.f3968w);
            view.post(this.f3968w);
        }
    }

    private void m5837b(int i, float f) {
        m5833a(i, m5830a(i), f);
    }

    private void m5839c(int i, float f) {
        View view = (View) this.f3959b.get();
        if (view != null) {
            switch (i) {
                case f3947l /*1*/:
                    view.setTranslationX(f);
                case f3948m /*2*/:
                    view.setTranslationY(f);
                case f3949n /*4*/:
                    view.setScaleX(f);
                case f3950o /*8*/:
                    view.setScaleY(f);
                case f3951p /*16*/:
                    view.setRotation(f);
                case f3952q /*32*/:
                    view.setRotationX(f);
                case f3953r /*64*/:
                    view.setRotationY(f);
                case f3954s /*128*/:
                    view.setX(f);
                case f3955t /*256*/:
                    view.setY(f);
                case f3956u /*512*/:
                    view.setAlpha(f);
                default:
            }
        }
    }

    private void m5841e() {
        float[] fArr = new float[f3947l];
        fArr[f3946k] = C2020f.f10933c;
        as b = as.m5508b(fArr);
        ArrayList arrayList = (ArrayList) this.f3958a.clone();
        this.f3958a.clear();
        int i = f3946k;
        for (int i2 = f3946k; i2 < arrayList.size(); i2 += f3947l) {
            i |= ((C0654g) arrayList.get(i2)).f3972a;
        }
        this.f3969x.put(b, new C0655h(i, arrayList));
        b.m5524a(this.f3967j);
        b.m5379a(this.f3967j);
        if (this.f3963f) {
            b.m5521a(this.f3962e);
        }
        if (this.f3961d) {
            b.m5537d(this.f3960c);
        }
        if (this.f3965h) {
            b.m5522a(this.f3964g);
        }
        b.m5518a();
    }

    public long m5842a() {
        return this.f3961d ? this.f3960c : new as().m5538e();
    }

    public C0650c m5843a(float f) {
        m5832a((int) f3954s, f);
        return this;
    }

    public C0650c m5844a(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("Animators cannot have negative duration: " + j);
        }
        this.f3961d = true;
        this.f3960c = j;
        return this;
    }

    public C0650c m5845a(Interpolator interpolator) {
        this.f3965h = true;
        this.f3964g = interpolator;
        return this;
    }

    public C0650c m5846a(C0620b c0620b) {
        this.f3966i = c0620b;
        return this;
    }

    public long m5847b() {
        return this.f3963f ? this.f3962e : 0;
    }

    public C0650c m5848b(float f) {
        m5837b(f3954s, f);
        return this;
    }

    public C0650c m5849b(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("Animators cannot have negative duration: " + j);
        }
        this.f3963f = true;
        this.f3962e = j;
        return this;
    }

    public C0650c m5850c(float f) {
        m5832a((int) f3955t, f);
        return this;
    }

    public void m5851c() {
        m5841e();
    }

    public C0650c m5852d(float f) {
        m5837b(f3955t, f);
        return this;
    }

    public void m5853d() {
        if (this.f3969x.size() > 0) {
            for (C0616a b : ((HashMap) this.f3969x.clone()).keySet()) {
                b.m5382b();
            }
        }
        this.f3958a.clear();
        View view = (View) this.f3959b.get();
        if (view != null) {
            view.removeCallbacks(this.f3968w);
        }
    }

    public C0650c m5854e(float f) {
        m5832a((int) f3951p, f);
        return this;
    }

    public C0650c m5855f(float f) {
        m5837b(f3951p, f);
        return this;
    }

    public C0650c m5856g(float f) {
        m5832a((int) f3952q, f);
        return this;
    }

    public C0650c m5857h(float f) {
        m5837b(f3952q, f);
        return this;
    }

    public C0650c m5858i(float f) {
        m5832a((int) f3953r, f);
        return this;
    }

    public C0650c m5859j(float f) {
        m5837b(f3953r, f);
        return this;
    }

    public C0650c m5860k(float f) {
        m5832a((int) f3947l, f);
        return this;
    }

    public C0650c m5861l(float f) {
        m5837b(f3947l, f);
        return this;
    }

    public C0650c m5862m(float f) {
        m5832a((int) f3948m, f);
        return this;
    }

    public C0650c m5863n(float f) {
        m5837b(f3948m, f);
        return this;
    }

    public C0650c m5864o(float f) {
        m5832a((int) f3949n, f);
        return this;
    }

    public C0650c m5865p(float f) {
        m5837b(f3949n, f);
        return this;
    }

    public C0650c m5866q(float f) {
        m5832a((int) f3950o, f);
        return this;
    }

    public C0650c m5867r(float f) {
        m5837b(f3950o, f);
        return this;
    }

    public C0650c m5868s(float f) {
        m5832a((int) f3956u, f);
        return this;
    }

    public C0650c m5869t(float f) {
        m5837b(f3956u, f);
        return this;
    }
}
