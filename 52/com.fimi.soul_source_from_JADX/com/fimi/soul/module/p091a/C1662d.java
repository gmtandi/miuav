package com.fimi.soul.module.p091a;

import java.util.ArrayList;
import java.util.List;

/* renamed from: com.fimi.soul.module.a.d */
public class C1662d implements C1661g {
    private List<C1258f> f7849a;

    public C1662d() {
        this.f7849a = new ArrayList();
    }

    public static C1662d m10808a() {
        return C1663e.f7850a;
    }

    public void m10809a(int i) {
        for (C1258f a : this.f7849a) {
            a.m8625a(i);
        }
    }

    public void m10810a(C1258f c1258f) {
        this.f7849a.add(c1258f);
    }

    public void m10811b(C1258f c1258f) {
        this.f7849a.add(c1258f);
    }
}
