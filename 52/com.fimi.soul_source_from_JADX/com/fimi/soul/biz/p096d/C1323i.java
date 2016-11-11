package com.fimi.soul.biz.p096d;

import com.fimi.kernel.p076b.C1153f;

/* renamed from: com.fimi.soul.biz.d.i */
class C1323i implements C1153f<String> {
    final /* synthetic */ C1153f f5934a;
    final /* synthetic */ C1316b f5935b;

    C1323i(C1316b c1316b, C1153f c1153f) {
        this.f5935b = c1316b;
        this.f5934a = c1153f;
    }

    public void m8924a(String str) {
        if (str != null) {
            this.f5934a.m7924a(Boolean.valueOf(true));
        } else {
            this.f5934a.m7924a(Boolean.valueOf(false));
        }
    }

    public void m8926b(String str) {
        if (str == null) {
            this.f5934a.m7924a(Boolean.valueOf(true));
        } else {
            this.f5934a.m7924a(Boolean.valueOf(false));
        }
    }
}
