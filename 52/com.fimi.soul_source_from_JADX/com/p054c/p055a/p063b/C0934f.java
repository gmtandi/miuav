package com.p054c.p055a.p063b;

import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import com.p054c.p055a.p063b.p064a.C0899e;
import com.p054c.p055a.p063b.p067c.C0912a;
import com.p054c.p055a.p063b.p071g.C0935a;

/* renamed from: com.c.a.b.f */
public class C0934f {
    private int f4883a;
    private int f4884b;
    private int f4885c;
    private Drawable f4886d;
    private Drawable f4887e;
    private Drawable f4888f;
    private boolean f4889g;
    private boolean f4890h;
    private boolean f4891i;
    private C0899e f4892j;
    private Options f4893k;
    private int f4894l;
    private boolean f4895m;
    private Object f4896n;
    private C0935a f4897o;
    private C0935a f4898p;
    private C0912a f4899q;
    private Handler f4900r;
    private boolean f4901s;

    public C0934f() {
        this.f4883a = 0;
        this.f4884b = 0;
        this.f4885c = 0;
        this.f4886d = null;
        this.f4887e = null;
        this.f4888f = null;
        this.f4889g = false;
        this.f4890h = false;
        this.f4891i = false;
        this.f4892j = C0899e.IN_SAMPLE_POWER_OF_2;
        this.f4893k = new Options();
        this.f4894l = 0;
        this.f4895m = false;
        this.f4896n = null;
        this.f4897o = null;
        this.f4898p = null;
        this.f4899q = C0905a.m7221c();
        this.f4900r = null;
        this.f4901s = false;
        this.f4893k.inPurgeable = true;
        this.f4893k.inInputShareable = true;
    }

    public C0934f m7375a() {
        this.f4889g = true;
        return this;
    }

    @Deprecated
    public C0934f m7376a(int i) {
        this.f4883a = i;
        return this;
    }

    public C0934f m7377a(Config config) {
        if (config == null) {
            throw new IllegalArgumentException("bitmapConfig can't be null");
        }
        this.f4893k.inPreferredConfig = config;
        return this;
    }

    public C0934f m7378a(Options options) {
        if (options == null) {
            throw new IllegalArgumentException("decodingOptions can't be null");
        }
        this.f4893k = options;
        return this;
    }

    public C0934f m7379a(Drawable drawable) {
        this.f4886d = drawable;
        return this;
    }

    public C0934f m7380a(Handler handler) {
        this.f4900r = handler;
        return this;
    }

    public C0934f m7381a(C0899e c0899e) {
        this.f4892j = c0899e;
        return this;
    }

    public C0934f m7382a(C0912a c0912a) {
        if (c0912a == null) {
            throw new IllegalArgumentException("displayer can't be null");
        }
        this.f4899q = c0912a;
        return this;
    }

    public C0934f m7383a(C0924d c0924d) {
        this.f4883a = c0924d.f4853a;
        this.f4884b = c0924d.f4854b;
        this.f4885c = c0924d.f4855c;
        this.f4886d = c0924d.f4856d;
        this.f4887e = c0924d.f4857e;
        this.f4888f = c0924d.f4858f;
        this.f4889g = c0924d.f4859g;
        this.f4890h = c0924d.f4860h;
        this.f4891i = c0924d.f4861i;
        this.f4892j = c0924d.f4862j;
        this.f4893k = c0924d.f4863k;
        this.f4894l = c0924d.f4864l;
        this.f4895m = c0924d.f4865m;
        this.f4896n = c0924d.f4866n;
        this.f4897o = c0924d.f4867o;
        this.f4898p = c0924d.f4868p;
        this.f4899q = c0924d.f4869q;
        this.f4900r = c0924d.f4870r;
        this.f4901s = c0924d.f4871s;
        return this;
    }

    public C0934f m7384a(C0935a c0935a) {
        this.f4897o = c0935a;
        return this;
    }

    public C0934f m7385a(Object obj) {
        this.f4896n = obj;
        return this;
    }

    public C0934f m7386a(boolean z) {
        this.f4889g = z;
        return this;
    }

    @Deprecated
    public C0934f m7387b() {
        this.f4890h = true;
        return this;
    }

    public C0934f m7388b(int i) {
        this.f4883a = i;
        return this;
    }

    public C0934f m7389b(Drawable drawable) {
        this.f4887e = drawable;
        return this;
    }

    public C0934f m7390b(C0935a c0935a) {
        this.f4898p = c0935a;
        return this;
    }

    public C0934f m7391b(boolean z) {
        this.f4890h = z;
        return this;
    }

    @Deprecated
    public C0934f m7392c() {
        return m7398d(true);
    }

    public C0934f m7393c(int i) {
        this.f4884b = i;
        return this;
    }

    public C0934f m7394c(Drawable drawable) {
        this.f4888f = drawable;
        return this;
    }

    @Deprecated
    public C0934f m7395c(boolean z) {
        return m7398d(z);
    }

    public C0924d m7396d() {
        return new C0924d();
    }

    public C0934f m7397d(int i) {
        this.f4885c = i;
        return this;
    }

    public C0934f m7398d(boolean z) {
        this.f4891i = z;
        return this;
    }

    public C0934f m7399e(int i) {
        this.f4894l = i;
        return this;
    }

    public C0934f m7400e(boolean z) {
        this.f4895m = z;
        return this;
    }

    C0934f m7401f(boolean z) {
        this.f4901s = z;
        return this;
    }
}
