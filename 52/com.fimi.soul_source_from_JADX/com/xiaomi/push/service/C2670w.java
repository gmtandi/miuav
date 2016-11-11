package com.xiaomi.push.service;

import com.tencent.mm.sdk.platformtools.Util;
import com.xiaomi.push.service.C2669v.C2667b;
import com.xiaomi.push.service.C2669v.C2667b.C2644a;
import com.xiaomi.push.service.C2669v.C2668c;

/* renamed from: com.xiaomi.push.service.w */
class C2670w implements C2644a {
    final /* synthetic */ C2667b f13201a;

    C2670w(C2667b c2667b) {
        this.f13201a = c2667b;
    }

    public void m15121a(C2668c c2668c, C2668c c2668c2, int i) {
        if (c2668c2 == C2668c.binding) {
            this.f13201a.f13192p.m14946a(this.f13201a.f13193q, (long) Util.MILLSECONDS_OF_MINUTE);
        } else {
            this.f13201a.f13192p.m14957b(this.f13201a.f13193q);
        }
    }
}
