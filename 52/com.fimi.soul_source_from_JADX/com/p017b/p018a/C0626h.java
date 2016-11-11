package com.p017b.p018a;

import com.fimi.soul.view.photodraweeview.C2020f;

/* renamed from: com.b.a.h */
public class C0626h {
    final /* synthetic */ C0623e f3866a;
    private C0629k f3867b;

    C0626h(C0623e c0623e, C0616a c0616a) {
        this.f3866a = c0623e;
        this.f3867b = (C0629k) c0623e.f3852d.get(c0616a);
        if (this.f3867b == null) {
            this.f3867b = new C0629k(c0616a);
            c0623e.f3852d.put(c0616a, this.f3867b);
            c0623e.f3853e.add(this.f3867b);
        }
    }

    public C0626h m5611a(long j) {
        C0616a b = as.m5508b(0.0f, C2020f.f10933c);
        b.m5537d(j);
        m5614c(b);
        return this;
    }

    public C0626h m5612a(C0616a c0616a) {
        C0629k c0629k = (C0629k) this.f3866a.f3852d.get(c0616a);
        if (c0629k == null) {
            c0629k = new C0629k(c0616a);
            this.f3866a.f3852d.put(c0616a, c0629k);
            this.f3866a.f3853e.add(c0629k);
        }
        c0629k.m5621a(new C0627i(this.f3867b, 0));
        return this;
    }

    public C0626h m5613b(C0616a c0616a) {
        C0629k c0629k = (C0629k) this.f3866a.f3852d.get(c0616a);
        if (c0629k == null) {
            c0629k = new C0629k(c0616a);
            this.f3866a.f3852d.put(c0616a, c0629k);
            this.f3866a.f3853e.add(c0629k);
        }
        c0629k.m5621a(new C0627i(this.f3867b, 1));
        return this;
    }

    public C0626h m5614c(C0616a c0616a) {
        C0629k c0629k = (C0629k) this.f3866a.f3852d.get(c0616a);
        if (c0629k == null) {
            c0629k = new C0629k(c0616a);
            this.f3866a.f3852d.put(c0616a, c0629k);
            this.f3866a.f3853e.add(c0629k);
        }
        this.f3867b.m5621a(new C0627i(c0629k, 1));
        return this;
    }
}
