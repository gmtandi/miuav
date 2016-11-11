package com.fimi.kernel.p076b.p077a;

import it.p074a.p075a.C1096l;

/* renamed from: com.fimi.kernel.b.a.a */
public abstract class C1097a implements C1096l {
    public void m7679a() {
        m7681a(C1098b.Aborted, 0);
    }

    public void m7680a(int i) {
        m7681a(C1098b.Transferred, i);
    }

    public abstract void m7681a(C1098b c1098b, int i);

    public void m7682b() {
        m7681a(C1098b.Completed, 0);
    }

    public void m7683c() {
        m7681a(C1098b.Failed, 0);
    }

    public void m7684d() {
        m7681a(C1098b.Started, 0);
    }
}
