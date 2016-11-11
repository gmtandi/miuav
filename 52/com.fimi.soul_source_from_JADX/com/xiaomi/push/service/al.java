package com.xiaomi.push.service;

import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.push.service.C2669v.C2667b.C2644a;
import com.xiaomi.push.service.C2669v.C2668c;

class al implements C2644a {
    final /* synthetic */ XMPushService f13123a;

    al(XMPushService xMPushService) {
        this.f13123a = xMPushService;
    }

    public void m14996a(C2668c c2668c, C2668c c2668c2, int i) {
        if (c2668c2 == C2668c.binded) {
            C2654j.m15039a(this.f13123a);
            C2654j.m15041b(this.f13123a);
        } else if (c2668c2 == C2668c.unbind) {
            C2654j.m15037a(this.f13123a, ErrorCode.ERROR_SERVICE_UNAVAILABLE, " the push is not connected.");
        }
    }
}
