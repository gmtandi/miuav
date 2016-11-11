package com.xiaomi.mistatistic.sdk.controller;

import com.xiaomi.mistatistic.sdk.data.C2614a;
import com.xiaomi.mistatistic.sdk.data.C2616b;
import com.xiaomi.mistatistic.sdk.data.C2617c;
import com.xiaomi.mistatistic.sdk.data.C2619f;
import com.xiaomi.mistatistic.sdk.data.C2620g;
import com.xiaomi.mistatistic.sdk.data.C2621h;

/* renamed from: com.xiaomi.mistatistic.sdk.controller.n */
class C2600n implements C2582e {
    private C2614a f12973a;

    public C2600n(C2614a c2614a) {
        this.f12973a = c2614a;
    }

    public void m14767a() {
        C2621h a = this.f12973a.m14810a();
        C2595i c2595i = new C2595i();
        if ((this.f12973a instanceof C2619f) || (this.f12973a instanceof C2620g) || (this.f12973a instanceof C2617c) || (this.f12973a instanceof C2616b)) {
            c2595i.m14748a(a);
        } else {
            String str = a.f13004c;
            String str2 = a.f13002a;
            C2621h a2 = c2595i.m14746a(str2, str);
            if (a2 == null || !a.f13005d.equals(a2.f13005d)) {
                c2595i.m14748a(a);
            } else {
                c2595i.m14749a(str, str2, a.f13006e);
            }
        }
        if (ab.m14718a().m14723d()) {
            new C2606t().m14795a();
        }
    }
}
