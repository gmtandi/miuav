package com.p017b.p020c;

import java.util.ArrayList;

/* renamed from: com.b.c.o */
class C0662o {
    int f4011a;
    ArrayList<C0661n> f4012b;

    C0662o(int i, ArrayList<C0661n> arrayList) {
        this.f4011a = i;
        this.f4012b = arrayList;
    }

    boolean m5947a(int i) {
        if (!((this.f4011a & i) == 0 || this.f4012b == null)) {
            int size = this.f4012b.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (((C0661n) this.f4012b.get(i2)).f4008a == i) {
                    this.f4012b.remove(i2);
                    this.f4011a &= i ^ -1;
                    return true;
                }
            }
        }
        return false;
    }
}
