package com.p017b.p018a;

import java.util.ArrayList;

/* renamed from: com.b.a.g */
class C0625g implements C0620b {
    final /* synthetic */ C0623e f3864a;
    private C0623e f3865b;

    C0625g(C0623e c0623e, C0623e c0623e2) {
        this.f3864a = c0623e;
        this.f3865b = c0623e2;
    }

    public void m5607a(C0616a c0616a) {
    }

    public void m5608b(C0616a c0616a) {
        c0616a.m5383b((C0620b) this);
        this.f3864a.f3851c.remove(c0616a);
        ((C0629k) this.f3865b.f3852d.get(c0616a)).f3880f = true;
        if (!this.f3864a.f3850b) {
            int i;
            boolean z;
            ArrayList c = this.f3865b.f3854f;
            int size = c.size();
            for (i = 0; i < size; i++) {
                if (!((C0629k) c.get(i)).f3880f) {
                    z = false;
                    break;
                }
            }
            z = true;
            if (z) {
                if (this.f3864a.a != null) {
                    ArrayList arrayList = (ArrayList) this.f3864a.a.clone();
                    int size2 = arrayList.size();
                    for (i = 0; i < size2; i++) {
                        ((C0620b) arrayList.get(i)).m5565b(this.f3865b);
                    }
                }
                this.f3865b.f3857i = false;
            }
        }
    }

    public void m5609c(C0616a c0616a) {
        if (!this.f3864a.f3850b && this.f3864a.f3851c.size() == 0 && this.f3864a.a != null) {
            int size = this.f3864a.a.size();
            for (int i = 0; i < size; i++) {
                ((C0620b) this.f3864a.a.get(i)).m5566c(this.f3865b);
            }
        }
    }

    public void m5610d(C0616a c0616a) {
    }
}
