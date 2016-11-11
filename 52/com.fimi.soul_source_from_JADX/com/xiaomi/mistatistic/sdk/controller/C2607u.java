package com.xiaomi.mistatistic.sdk.controller;

import android.text.TextUtils;
import com.xiaomi.mistatistic.sdk.controller.p139a.C2585c;

/* renamed from: com.xiaomi.mistatistic.sdk.controller.u */
class C2607u implements C2585c {
    final /* synthetic */ C2606t f12979a;

    C2607u(C2606t c2606t) {
        this.f12979a = c2606t;
    }

    public void m14796a(String str, long j) {
        if (TextUtils.isEmpty(str)) {
            C2606t.f12977b = false;
            return;
        }
        this.f12979a.f12978a = j;
        this.f12979a.m14789a(str);
    }
}
