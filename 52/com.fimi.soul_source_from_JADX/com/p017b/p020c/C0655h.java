package com.p017b.p020c;

import java.util.ArrayList;

/* renamed from: com.b.c.h */
class C0655h {
    int f3975a;
    ArrayList<C0654g> f3976b;

    C0655h(int i, ArrayList<C0654g> arrayList) {
        this.f3975a = i;
        this.f3976b = arrayList;
    }

    boolean m5874a(int i) {
        if (!((this.f3975a & i) == 0 || this.f3976b == null)) {
            int size = this.f3976b.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (((C0654g) this.f3976b.get(i2)).f3972a == i) {
                    this.f3976b.remove(i2);
                    this.f3975a &= i ^ -1;
                    return true;
                }
            }
        }
        return false;
    }
}
