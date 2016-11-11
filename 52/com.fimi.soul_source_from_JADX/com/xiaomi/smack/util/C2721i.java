package com.xiaomi.smack.util;

import com.xiaomi.channel.commonutils.misc.C2467b.C2466b;
import com.xiaomi.push.service.XMPushService;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.xiaomi.smack.util.i */
final class C2721i extends C2466b {
    final /* synthetic */ XMPushService f13443a;

    C2721i(XMPushService xMPushService) {
        this.f13443a = xMPushService;
    }

    public void m15376b() {
        List arrayList;
        synchronized (C2720h.f13439c) {
            arrayList = new ArrayList(C2720h.f13440d);
            C2720h.f13440d.clear();
        }
        C2720h.m15373b(this.f13443a, arrayList);
    }
}
