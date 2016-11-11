package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.push.service.XMPushService.C2629e;
import com.xiaomi.xmpush.thrift.C2748k;

/* renamed from: com.xiaomi.push.service.p */
final class C2660p extends C2629e {
    final /* synthetic */ XMPushService f13161a;
    final /* synthetic */ C2748k f13162b;
    final /* synthetic */ String f13163c;

    C2660p(int i, XMPushService xMPushService, C2748k c2748k, String str) {
        this.f13161a = xMPushService;
        this.f13162b = c2748k;
        this.f13163c = str;
        super(i);
    }

    public void m15067a() {
        try {
            C2748k a = C2655k.m15056e(this.f13161a, this.f13162b);
            a.m15587m().m15426a("absent_target_package", this.f13163c);
            this.f13161a.m14959b(a);
        } catch (Throwable e) {
            C2463b.m14125a(e);
            this.f13161a.m14956b(10, e);
        }
    }

    public String m15068b() {
        return "send app absent ack message for message.";
    }
}
