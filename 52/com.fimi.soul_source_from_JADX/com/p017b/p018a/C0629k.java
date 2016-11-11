package com.p017b.p018a;

import java.util.ArrayList;

/* renamed from: com.b.a.k */
class C0629k implements Cloneable {
    public C0616a f3875a;
    public ArrayList<C0627i> f3876b;
    public ArrayList<C0627i> f3877c;
    public ArrayList<C0629k> f3878d;
    public ArrayList<C0629k> f3879e;
    public boolean f3880f;

    public C0629k(C0616a c0616a) {
        this.f3876b = null;
        this.f3877c = null;
        this.f3878d = null;
        this.f3879e = null;
        this.f3880f = false;
        this.f3875a = c0616a;
    }

    public C0629k m5620a() {
        try {
            C0629k c0629k = (C0629k) super.clone();
            c0629k.f3875a = this.f3875a.m5391j();
            return c0629k;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public void m5621a(C0627i c0627i) {
        if (this.f3876b == null) {
            this.f3876b = new ArrayList();
            this.f3878d = new ArrayList();
        }
        this.f3876b.add(c0627i);
        if (!this.f3878d.contains(c0627i.f3870c)) {
            this.f3878d.add(c0627i.f3870c);
        }
        C0629k c0629k = c0627i.f3870c;
        if (c0629k.f3879e == null) {
            c0629k.f3879e = new ArrayList();
        }
        c0629k.f3879e.add(this);
    }

    public /* synthetic */ Object clone() {
        return m5620a();
    }
}
