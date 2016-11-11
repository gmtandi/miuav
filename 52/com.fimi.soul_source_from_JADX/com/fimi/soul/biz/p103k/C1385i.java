package com.fimi.soul.biz.p103k;

import com.fimi.kernel.p076b.C1153f;
import com.fimi.kernel.p084e.ak;
import com.fimi.soul.C1205R;

/* renamed from: com.fimi.soul.biz.k.i */
class C1385i implements C1153f<String> {
    final /* synthetic */ C1384h f6221a;

    C1385i(C1384h c1384h) {
        this.f6221a = c1384h;
    }

    public void m9310a(String str) {
        if (str != null && str.length() > 0) {
            ak.m8082a(this.f6221a.f6219b, (int) C1205R.string.commit_crash_msg);
        }
    }

    public void m9312b(String str) {
    }
}
