package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.push.service.XMPushService.C2629e;
import com.xiaomi.xmpush.thrift.C2748k;

/* renamed from: com.xiaomi.push.service.l */
final class C2656l extends C2629e {
    final /* synthetic */ XMPushService f13153a;
    final /* synthetic */ C2748k f13154b;

    C2656l(int i, XMPushService xMPushService, C2748k c2748k) {
        this.f13153a = xMPushService;
        this.f13154b = c2748k;
        super(i);
    }

    public void m15059a() {
        try {
            this.f13153a.m14959b(this.f13153a.m14940a(this.f13154b.m15584j(), this.f13154b.m15582h()));
        } catch (Throwable e) {
            C2463b.m14125a(e);
            this.f13153a.m14956b(10, e);
        }
    }

    public String m15060b() {
        return "send app absent message.";
    }
}
