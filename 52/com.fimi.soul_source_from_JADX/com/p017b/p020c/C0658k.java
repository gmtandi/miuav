package com.p017b.p020c;

import android.view.View;
import android.view.animation.Interpolator;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.p017b.p018a.C0616a;
import com.p017b.p018a.C0620b;
import com.p017b.p018a.as;
import com.p017b.p020c.p021a.C0647a;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: com.b.c.k */
class C0658k extends C0650c {
    private static final int f3981l = 0;
    private static final int f3982m = 1;
    private static final int f3983n = 2;
    private static final int f3984o = 4;
    private static final int f3985p = 8;
    private static final int f3986q = 16;
    private static final int f3987r = 32;
    private static final int f3988s = 64;
    private static final int f3989t = 128;
    private static final int f3990u = 256;
    private static final int f3991v = 512;
    private static final int f3992w = 511;
    ArrayList<C0661n> f3993a;
    private final C0647a f3994b;
    private final WeakReference<View> f3995c;
    private long f3996d;
    private boolean f3997e;
    private long f3998f;
    private boolean f3999g;
    private Interpolator f4000h;
    private boolean f4001i;
    private C0620b f4002j;
    private C0660m f4003k;
    private Runnable f4004x;
    private HashMap<C0616a, C0662o> f4005y;

    C0658k(View view) {
        this.f3997e = false;
        this.f3998f = 0;
        this.f3999g = false;
        this.f4001i = false;
        this.f4002j = null;
        this.f4003k = new C0660m();
        this.f3993a = new ArrayList();
        this.f4004x = new C0659l(this);
        this.f4005y = new HashMap();
        this.f3995c = new WeakReference(view);
        this.f3994b = C0647a.m5712a(view);
    }

    private float m5903a(int i) {
        switch (i) {
            case f3982m /*1*/:
                return this.f3994b.m5739k();
            case f3983n /*2*/:
                return this.f3994b.m5741l();
            case f3984o /*4*/:
                return this.f3994b.m5731g();
            case f3985p /*8*/:
                return this.f3994b.m5733h();
            case f3986q /*16*/:
                return this.f3994b.m5725d();
            case f3987r /*32*/:
                return this.f3994b.m5727e();
            case f3988s /*64*/:
                return this.f3994b.m5729f();
            case f3989t /*128*/:
                return this.f3994b.m5743m();
            case f3990u /*256*/:
                return this.f3994b.m5744n();
            case f3991v /*512*/:
                return this.f3994b.m5717a();
            default:
                return 0.0f;
        }
    }

    private void m5905a(int i, float f) {
        float a = m5903a(i);
        m5906a(i, a, f - a);
    }

    private void m5906a(int i, float f, float f2) {
        if (this.f4005y.size() > 0) {
            for (C0616a c0616a : this.f4005y.keySet()) {
                C0662o c0662o = (C0662o) this.f4005y.get(c0616a);
                if (c0662o.m5947a(i) && c0662o.f4011a == 0) {
                    break;
                }
            }
            C0616a c0616a2 = null;
            if (c0616a2 != null) {
                c0616a2.m5382b();
            }
        }
        this.f3993a.add(new C0661n(i, f, f2));
        View view = (View) this.f3995c.get();
        if (view != null) {
            view.removeCallbacks(this.f4004x);
            view.post(this.f4004x);
        }
    }

    private void m5910b(int i, float f) {
        m5906a(i, m5903a(i), f);
    }

    private void m5912c(int i, float f) {
        switch (i) {
            case f3982m /*1*/:
                this.f3994b.m5736i(f);
            case f3983n /*2*/:
                this.f3994b.m5738j(f);
            case f3984o /*4*/:
                this.f3994b.m5732g(f);
            case f3985p /*8*/:
                this.f3994b.m5734h(f);
            case f3986q /*16*/:
                this.f3994b.m5726d(f);
            case f3987r /*32*/:
                this.f3994b.m5728e(f);
            case f3988s /*64*/:
                this.f3994b.m5730f(f);
            case f3989t /*128*/:
                this.f3994b.m5740k(f);
            case f3990u /*256*/:
                this.f3994b.m5742l(f);
            case f3991v /*512*/:
                this.f3994b.m5718a(f);
            default:
        }
    }

    private void m5914e() {
        float[] fArr = new float[f3982m];
        fArr[f3981l] = C2020f.f10933c;
        as b = as.m5508b(fArr);
        ArrayList arrayList = (ArrayList) this.f3993a.clone();
        this.f3993a.clear();
        int i = f3981l;
        for (int i2 = f3981l; i2 < arrayList.size(); i2 += f3982m) {
            i |= ((C0661n) arrayList.get(i2)).f4008a;
        }
        this.f4005y.put(b, new C0662o(i, arrayList));
        b.m5524a(this.f4003k);
        b.m5379a(this.f4003k);
        if (this.f3999g) {
            b.m5521a(this.f3998f);
        }
        if (this.f3997e) {
            b.m5537d(this.f3996d);
        }
        if (this.f4001i) {
            b.m5522a(this.f4000h);
        }
        b.m5518a();
    }

    public long m5915a() {
        return this.f3997e ? this.f3996d : new as().m5538e();
    }

    public C0650c m5916a(float f) {
        m5905a((int) f3989t, f);
        return this;
    }

    public C0650c m5917a(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("Animators cannot have negative duration: " + j);
        }
        this.f3997e = true;
        this.f3996d = j;
        return this;
    }

    public C0650c m5918a(Interpolator interpolator) {
        this.f4001i = true;
        this.f4000h = interpolator;
        return this;
    }

    public C0650c m5919a(C0620b c0620b) {
        this.f4002j = c0620b;
        return this;
    }

    public long m5920b() {
        return this.f3999g ? this.f3998f : 0;
    }

    public C0650c m5921b(float f) {
        m5910b(f3989t, f);
        return this;
    }

    public C0650c m5922b(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("Animators cannot have negative duration: " + j);
        }
        this.f3999g = true;
        this.f3998f = j;
        return this;
    }

    public C0650c m5923c(float f) {
        m5905a((int) f3990u, f);
        return this;
    }

    public void m5924c() {
        m5914e();
    }

    public C0650c m5925d(float f) {
        m5910b(f3990u, f);
        return this;
    }

    public void m5926d() {
        if (this.f4005y.size() > 0) {
            for (C0616a b : ((HashMap) this.f4005y.clone()).keySet()) {
                b.m5382b();
            }
        }
        this.f3993a.clear();
        View view = (View) this.f3995c.get();
        if (view != null) {
            view.removeCallbacks(this.f4004x);
        }
    }

    public C0650c m5927e(float f) {
        m5905a((int) f3986q, f);
        return this;
    }

    public C0650c m5928f(float f) {
        m5910b(f3986q, f);
        return this;
    }

    public C0650c m5929g(float f) {
        m5905a((int) f3987r, f);
        return this;
    }

    public C0650c m5930h(float f) {
        m5910b(f3987r, f);
        return this;
    }

    public C0650c m5931i(float f) {
        m5905a((int) f3988s, f);
        return this;
    }

    public C0650c m5932j(float f) {
        m5910b(f3988s, f);
        return this;
    }

    public C0650c m5933k(float f) {
        m5905a((int) f3982m, f);
        return this;
    }

    public C0650c m5934l(float f) {
        m5910b(f3982m, f);
        return this;
    }

    public C0650c m5935m(float f) {
        m5905a((int) f3983n, f);
        return this;
    }

    public C0650c m5936n(float f) {
        m5910b(f3983n, f);
        return this;
    }

    public C0650c m5937o(float f) {
        m5905a((int) f3984o, f);
        return this;
    }

    public C0650c m5938p(float f) {
        m5910b(f3984o, f);
        return this;
    }

    public C0650c m5939q(float f) {
        m5905a((int) f3985p, f);
        return this;
    }

    public C0650c m5940r(float f) {
        m5910b(f3985p, f);
        return this;
    }

    public C0650c m5941s(float f) {
        m5905a((int) f3991v, f);
        return this;
    }

    public C0650c m5942t(float f) {
        m5910b(f3991v, f);
        return this;
    }
}
