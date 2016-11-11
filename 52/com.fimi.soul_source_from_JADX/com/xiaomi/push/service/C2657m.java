package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.push.service.XMPushService.C2629e;
import com.xiaomi.xmpush.thrift.C2748k;

/* renamed from: com.xiaomi.push.service.m */
final class C2657m extends C2629e {
    final /* synthetic */ XMPushService f13155a;
    final /* synthetic */ C2748k f13156b;

    C2657m(int i, XMPushService xMPushService, C2748k c2748k) {
        this.f13155a = xMPushService;
        this.f13156b = c2748k;
        super(i);
    }

    public void m15061a() {
        try {
            this.f13155a.m14959b(C2655k.m15056e(this.f13155a, this.f13156b));
        } catch (Throwable e) {
            C2463b.m14125a(e);
            this.f13155a.m14956b(10, e);
        }
    }

    public String m15062b() {
        return "send ack message for message.";
    }
}
