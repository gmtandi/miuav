package com.p054c.p055a.p063b;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.ImageView;
import com.p054c.p055a.p056a.p057a.C0864a;
import com.p054c.p055a.p056a.p061b.C0875c;
import com.p054c.p055a.p063b.p064a.C0900f;
import com.p054c.p055a.p063b.p064a.C0901g;
import com.p054c.p055a.p063b.p064a.C0903i;
import com.p054c.p055a.p063b.p069e.C0925a;
import com.p054c.p055a.p063b.p069e.C0927b;
import com.p054c.p055a.p063b.p069e.C0928c;
import com.p054c.p055a.p063b.p070f.C0930a;
import com.p054c.p055a.p063b.p070f.C0931b;
import com.p054c.p055a.p063b.p070f.C0933d;
import com.p054c.p055a.p072c.C0955b;
import com.p054c.p055a.p072c.C0958f;
import com.p054c.p055a.p072c.C0959g;

/* renamed from: com.c.a.b.g */
public class C0936g {
    public static final String f4902a;
    static final String f4903b = "Initialize ImageLoader with configuration";
    static final String f4904c = "Destroy ImageLoader";
    static final String f4905d = "Load image from memory cache [%s]";
    private static final String f4906e = "Try to initialize ImageLoader which had already been initialized before. To re-init ImageLoader with new configuration call ImageLoader.destroy() at first.";
    private static final String f4907f = "Wrong arguments were passed to displayImage() method (ImageView reference must not be null)";
    private static final String f4908g = "ImageLoader must be init with configuration before using";
    private static final String f4909h = "ImageLoader configuration can not be initialized with null";
    private static volatile C0936g f4910l;
    private C0939j f4911i;
    private C0944o f4912j;
    private C0930a f4913k;

    static {
        f4902a = C0936g.class.getSimpleName();
    }

    protected C0936g() {
        this.f4913k = new C0933d();
    }

    private static Handler m7403a(C0924d c0924d) {
        Handler r = c0924d.m7311r();
        return c0924d.m7312s() ? null : (r == null && Looper.myLooper() == Looper.getMainLooper()) ? new Handler() : r;
    }

    public static C0936g m7404a() {
        if (f4910l == null) {
            synchronized (C0936g.class) {
                if (f4910l == null) {
                    f4910l = new C0936g();
                }
            }
        }
        return f4910l;
    }

    private void m7405m() {
        if (this.f4911i == null) {
            throw new IllegalStateException(f4908g);
        }
    }

    public Bitmap m7406a(String str) {
        return m7408a(str, null, null);
    }

    public Bitmap m7407a(String str, C0900f c0900f) {
        return m7408a(str, c0900f, null);
    }

    public Bitmap m7408a(String str, C0900f c0900f, C0924d c0924d) {
        if (c0924d == null) {
            c0924d = this.f4911i.f4932r;
        }
        C0924d d = new C0934f().m7383a(c0924d).m7401f(true).m7396d();
        C0930a c0938i = new C0938i();
        m7419a(str, c0900f, d, c0938i);
        return c0938i.m7444a();
    }

    public Bitmap m7409a(String str, C0924d c0924d) {
        return m7408a(str, null, c0924d);
    }

    public String m7410a(ImageView imageView) {
        return this.f4912j.m7500a(new C0927b(imageView));
    }

    public String m7411a(C0925a c0925a) {
        return this.f4912j.m7500a(c0925a);
    }

    public void m7412a(C0930a c0930a) {
        if (c0930a == null) {
            c0930a = new C0933d();
        }
        this.f4913k = c0930a;
    }

    public synchronized void m7413a(C0939j c0939j) {
        if (c0939j == null) {
            throw new IllegalArgumentException(f4909h);
        } else if (this.f4911i == null) {
            C0958f.m7554a(f4903b, new Object[0]);
            this.f4912j = new C0944o(c0939j);
            this.f4911i = c0939j;
        } else {
            C0958f.m7561c(f4906e, new Object[0]);
        }
    }

    public void m7414a(String str, ImageView imageView) {
        m7426a(str, new C0927b(imageView), null, null, null);
    }

    public void m7415a(String str, ImageView imageView, C0924d c0924d) {
        m7426a(str, new C0927b(imageView), c0924d, null, null);
    }

    public void m7416a(String str, ImageView imageView, C0924d c0924d, C0930a c0930a) {
        m7417a(str, imageView, c0924d, c0930a, null);
    }

    public void m7417a(String str, ImageView imageView, C0924d c0924d, C0930a c0930a, C0931b c0931b) {
        m7426a(str, new C0927b(imageView), c0924d, c0930a, c0931b);
    }

    public void m7418a(String str, ImageView imageView, C0930a c0930a) {
        m7426a(str, new C0927b(imageView), null, c0930a, null);
    }

    public void m7419a(String str, C0900f c0900f, C0924d c0924d, C0930a c0930a) {
        m7420a(str, c0900f, c0924d, c0930a, null);
    }

    public void m7420a(String str, C0900f c0900f, C0924d c0924d, C0930a c0930a, C0931b c0931b) {
        m7405m();
        if (c0900f == null) {
            c0900f = this.f4911i.m7447a();
        }
        m7426a(str, new C0928c(str, c0900f, C0903i.CROP), c0924d == null ? this.f4911i.f4932r : c0924d, c0930a, c0931b);
    }

    public void m7421a(String str, C0900f c0900f, C0930a c0930a) {
        m7420a(str, c0900f, null, c0930a, null);
    }

    public void m7422a(String str, C0924d c0924d, C0930a c0930a) {
        m7420a(str, null, c0924d, c0930a, null);
    }

    public void m7423a(String str, C0925a c0925a) {
        m7426a(str, c0925a, null, null, null);
    }

    public void m7424a(String str, C0925a c0925a, C0924d c0924d) {
        m7426a(str, c0925a, c0924d, null, null);
    }

    public void m7425a(String str, C0925a c0925a, C0924d c0924d, C0930a c0930a) {
        m7426a(str, c0925a, c0924d, c0930a, null);
    }

    public void m7426a(String str, C0925a c0925a, C0924d c0924d, C0930a c0930a, C0931b c0931b) {
        m7405m();
        if (c0925a == null) {
            throw new IllegalArgumentException(f4907f);
        }
        C0930a c0930a2 = c0930a == null ? this.f4913k : c0930a;
        C0924d c0924d2 = c0924d == null ? this.f4911i.f4932r : c0924d;
        if (TextUtils.isEmpty(str)) {
            this.f4912j.m7509b(c0925a);
            c0930a2.m7347a(str, c0925a.m7318d());
            if (c0924d2.m7294b()) {
                c0925a.m7315a(c0924d2.m7293b(this.f4911i.f4915a));
            } else {
                c0925a.m7315a(null);
            }
            c0930a2.m7348a(str, c0925a.m7318d(), null);
            return;
        }
        C0900f a = C0955b.m7545a(c0925a, this.f4911i.m7447a());
        String a2 = C0959g.m7563a(str, a);
        this.f4912j.m7503a(c0925a, a2);
        c0930a2.m7347a(str, c0925a.m7318d());
        Bitmap a3 = this.f4911i.f4928n.m7073a(a2);
        if (a3 == null || a3.isRecycled()) {
            if (c0924d2.m7292a()) {
                c0925a.m7315a(c0924d2.m7291a(this.f4911i.f4915a));
            } else if (c0924d2.m7300g()) {
                c0925a.m7315a(null);
            }
            C0948r c0948r = new C0948r(this.f4912j, new C0946q(str, c0925a, a, a2, c0924d2, c0930a2, c0931b, this.f4912j.m7501a(str)), C0936g.m7403a(c0924d2));
            if (c0924d2.m7312s()) {
                c0948r.run();
                return;
            } else {
                this.f4912j.m7504a(c0948r);
                return;
            }
        }
        C0958f.m7554a(f4905d, a2);
        if (c0924d2.m7298e()) {
            C0953w c0953w = new C0953w(this.f4912j, a3, new C0946q(str, c0925a, a, a2, c0924d2, c0930a2, c0931b, this.f4912j.m7501a(str)), C0936g.m7403a(c0924d2));
            if (c0924d2.m7312s()) {
                c0953w.run();
                return;
            } else {
                this.f4912j.m7505a(c0953w);
                return;
            }
        }
        c0924d2.m7310q().m7246a(a3, c0925a, C0901g.MEMORY_CACHE);
        c0930a2.m7348a(str, c0925a.m7318d(), a3);
    }

    public void m7427a(String str, C0925a c0925a, C0930a c0930a) {
        m7426a(str, c0925a, null, c0930a, null);
    }

    public void m7428a(String str, C0930a c0930a) {
        m7420a(str, null, null, c0930a, null);
    }

    public void m7429a(boolean z) {
        this.f4912j.m7507a(z);
    }

    public void m7430b(ImageView imageView) {
        this.f4912j.m7509b(new C0927b(imageView));
    }

    public void m7431b(C0925a c0925a) {
        this.f4912j.m7509b(c0925a);
    }

    public void m7432b(boolean z) {
        this.f4912j.m7510b(z);
    }

    public boolean m7433b() {
        return this.f4911i != null;
    }

    public C0875c m7434c() {
        m7405m();
        return this.f4911i.f4928n;
    }

    public void m7435d() {
        m7405m();
        this.f4911i.f4928n.m7077b();
    }

    @Deprecated
    public C0864a m7436e() {
        return m7437f();
    }

    public C0864a m7437f() {
        m7405m();
        return this.f4911i.f4929o;
    }

    @Deprecated
    public void m7438g() {
        m7439h();
    }

    public void m7439h() {
        m7405m();
        this.f4911i.f4929o.m7033c();
    }

    public void m7440i() {
        this.f4912j.m7502a();
    }

    public void m7441j() {
        this.f4912j.m7508b();
    }

    public void m7442k() {
        this.f4912j.m7511c();
    }

    public void m7443l() {
        if (this.f4911i != null) {
            C0958f.m7554a(f4904c, new Object[0]);
        }
        m7442k();
        this.f4911i.f4929o.m7031b();
        this.f4912j = null;
        this.f4911i = null;
    }
}
