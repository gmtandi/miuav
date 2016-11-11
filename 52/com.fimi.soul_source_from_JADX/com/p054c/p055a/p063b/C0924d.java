package com.p054c.p055a.p063b;

import android.content.res.Resources;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import com.p054c.p055a.p063b.p064a.C0899e;
import com.p054c.p055a.p063b.p067c.C0912a;
import com.p054c.p055a.p063b.p071g.C0935a;

/* renamed from: com.c.a.b.d */
public final class C0924d {
    private final int f4853a;
    private final int f4854b;
    private final int f4855c;
    private final Drawable f4856d;
    private final Drawable f4857e;
    private final Drawable f4858f;
    private final boolean f4859g;
    private final boolean f4860h;
    private final boolean f4861i;
    private final C0899e f4862j;
    private final Options f4863k;
    private final int f4864l;
    private final boolean f4865m;
    private final Object f4866n;
    private final C0935a f4867o;
    private final C0935a f4868p;
    private final C0912a f4869q;
    private final Handler f4870r;
    private final boolean f4871s;

    private C0924d(C0934f c0934f) {
        this.f4853a = c0934f.f4883a;
        this.f4854b = c0934f.f4884b;
        this.f4855c = c0934f.f4885c;
        this.f4856d = c0934f.f4886d;
        this.f4857e = c0934f.f4887e;
        this.f4858f = c0934f.f4888f;
        this.f4859g = c0934f.f4889g;
        this.f4860h = c0934f.f4890h;
        this.f4861i = c0934f.f4891i;
        this.f4862j = c0934f.f4892j;
        this.f4863k = c0934f.f4893k;
        this.f4864l = c0934f.f4894l;
        this.f4865m = c0934f.f4895m;
        this.f4866n = c0934f.f4896n;
        this.f4867o = c0934f.f4897o;
        this.f4868p = c0934f.f4898p;
        this.f4869q = c0934f.f4899q;
        this.f4870r = c0934f.f4900r;
        this.f4871s = c0934f.f4901s;
    }

    public static C0924d m7290t() {
        return new C0934f().m7396d();
    }

    public Drawable m7291a(Resources resources) {
        return this.f4853a != 0 ? resources.getDrawable(this.f4853a) : this.f4856d;
    }

    public boolean m7292a() {
        return (this.f4856d == null && this.f4853a == 0) ? false : true;
    }

    public Drawable m7293b(Resources resources) {
        return this.f4854b != 0 ? resources.getDrawable(this.f4854b) : this.f4857e;
    }

    public boolean m7294b() {
        return (this.f4857e == null && this.f4854b == 0) ? false : true;
    }

    public Drawable m7295c(Resources resources) {
        return this.f4855c != 0 ? resources.getDrawable(this.f4855c) : this.f4858f;
    }

    public boolean m7296c() {
        return (this.f4858f == null && this.f4855c == 0) ? false : true;
    }

    public boolean m7297d() {
        return this.f4867o != null;
    }

    public boolean m7298e() {
        return this.f4868p != null;
    }

    public boolean m7299f() {
        return this.f4864l > 0;
    }

    public boolean m7300g() {
        return this.f4859g;
    }

    public boolean m7301h() {
        return this.f4860h;
    }

    public boolean m7302i() {
        return this.f4861i;
    }

    public C0899e m7303j() {
        return this.f4862j;
    }

    public Options m7304k() {
        return this.f4863k;
    }

    public int m7305l() {
        return this.f4864l;
    }

    public boolean m7306m() {
        return this.f4865m;
    }

    public Object m7307n() {
        return this.f4866n;
    }

    public C0935a m7308o() {
        return this.f4867o;
    }

    public C0935a m7309p() {
        return this.f4868p;
    }

    public C0912a m7310q() {
        return this.f4869q;
    }

    public Handler m7311r() {
        return this.f4870r;
    }

    boolean m7312s() {
        return this.f4871s;
    }
}
