package com.p017b.p018a;

import java.util.ArrayList;

/* renamed from: com.b.a.f */
class C0624f extends C0622d {
    boolean f3861a;
    final /* synthetic */ ArrayList f3862b;
    final /* synthetic */ C0623e f3863c;

    C0624f(C0623e c0623e, ArrayList arrayList) {
        this.f3863c = c0623e;
        this.f3862b = arrayList;
        this.f3861a = false;
    }

    public void m5605b(C0616a c0616a) {
        if (!this.f3861a) {
            int size = this.f3862b.size();
            for (int i = 0; i < size; i++) {
                C0629k c0629k = (C0629k) this.f3862b.get(i);
                c0629k.f3875a.m5376a();
                this.f3863c.f3851c.add(c0629k.f3875a);
            }
        }
    }

    public void m5606c(C0616a c0616a) {
        this.f3861a = true;
    }
}
