package com.xiaomi.mistatistic.sdk.controller;

import android.content.Context;
import android.text.TextUtils;
import org.p122a.p123a.C2915a;

/* renamed from: com.xiaomi.mistatistic.sdk.controller.h */
class C2594h implements C2582e {
    private Context f12964a;

    public C2594h(Context context) {
        this.f12964a = context;
    }

    public void m14743a() {
        String a = C2605s.m14781a(this.f12964a, "device_id", C2915a.f14760f);
        if (TextUtils.isEmpty(a)) {
            C2593g.f12963a = C2593g.m14736a(this.f12964a);
            C2605s.m14785b(this.f12964a, "device_id", C2593g.f12963a);
            return;
        }
        C2593g.f12963a = a;
    }
}
