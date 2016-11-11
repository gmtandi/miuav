package com.xiaomi.push.service;

import com.tencent.connect.common.Constants;
import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.push.service.XMPushService.C2629e;
import com.xiaomi.xmpush.thrift.C2748k;

/* renamed from: com.xiaomi.push.service.n */
final class C2658n extends C2629e {
    final /* synthetic */ XMPushService f13157a;
    final /* synthetic */ C2748k f13158b;

    C2658n(int i, XMPushService xMPushService, C2748k c2748k) {
        this.f13157a = xMPushService;
        this.f13158b = c2748k;
        super(i);
    }

    public void m15063a() {
        try {
            C2748k a = C2655k.m15056e(this.f13157a, this.f13158b);
            a.m15587m().m15426a("message_obsleted", Constants.VIA_TO_TYPE_QQ_GROUP);
            this.f13157a.m14959b(a);
        } catch (Throwable e) {
            C2463b.m14125a(e);
            this.f13157a.m14956b(10, e);
        }
    }

    public String m15064b() {
        return "send ack message for obsleted message.";
    }
}
