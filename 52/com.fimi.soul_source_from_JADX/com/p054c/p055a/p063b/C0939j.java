package com.p054c.p055a.p063b;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import com.p054c.p055a.p056a.p057a.C0864a;
import com.p054c.p055a.p056a.p061b.C0875c;
import com.p054c.p055a.p063b.p064a.C0900f;
import com.p054c.p055a.p063b.p064a.C0902h;
import com.p054c.p055a.p063b.p066b.C0906d;
import com.p054c.p055a.p063b.p068d.C0920c;
import com.p054c.p055a.p063b.p071g.C0935a;
import com.p054c.p055a.p072c.C0958f;
import java.util.concurrent.Executor;

/* renamed from: com.c.a.b.j */
public final class C0939j {
    final Resources f4915a;
    final int f4916b;
    final int f4917c;
    final int f4918d;
    final int f4919e;
    final C0935a f4920f;
    final Executor f4921g;
    final Executor f4922h;
    final boolean f4923i;
    final boolean f4924j;
    final int f4925k;
    final int f4926l;
    final C0902h f4927m;
    final C0875c f4928n;
    final C0864a f4929o;
    final C0920c f4930p;
    final C0906d f4931q;
    final C0924d f4932r;
    final C0920c f4933s;
    final C0920c f4934t;

    private C0939j(C0941l c0941l) {
        this.f4915a = c0941l.f4948h.getResources();
        this.f4916b = c0941l.f4949i;
        this.f4917c = c0941l.f4950j;
        this.f4918d = c0941l.f4951k;
        this.f4919e = c0941l.f4952l;
        this.f4920f = c0941l.f4953m;
        this.f4921g = c0941l.f4954n;
        this.f4922h = c0941l.f4955o;
        this.f4925k = c0941l.f4958r;
        this.f4926l = c0941l.f4959s;
        this.f4927m = c0941l.f4961u;
        this.f4929o = c0941l.f4966z;
        this.f4928n = c0941l.f4965y;
        this.f4932r = c0941l.f4946D;
        this.f4930p = c0941l.f4944B;
        this.f4931q = c0941l.f4945C;
        this.f4923i = c0941l.f4956p;
        this.f4924j = c0941l.f4957q;
        this.f4933s = new C0942m(this.f4930p);
        this.f4934t = new C0943n(this.f4930p);
        C0958f.m7557a(c0941l.f4947E);
    }

    public static C0939j m7446a(Context context) {
        return new C0941l(context).m7486c();
    }

    C0900f m7447a() {
        DisplayMetrics displayMetrics = this.f4915a.getDisplayMetrics();
        int i = this.f4916b;
        if (i <= 0) {
            i = displayMetrics.widthPixels;
        }
        int i2 = this.f4917c;
        if (i2 <= 0) {
            i2 = displayMetrics.heightPixels;
        }
        return new C0900f(i, i2);
    }
}
