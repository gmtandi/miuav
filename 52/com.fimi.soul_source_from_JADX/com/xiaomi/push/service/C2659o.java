package com.xiaomi.push.service;

import com.tencent.connect.common.Constants;
import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.push.service.XMPushService.C2629e;
import com.xiaomi.xmpush.thrift.C2748k;

/* renamed from: com.xiaomi.push.service.o */
final class C2659o extends C2629e {
    final /* synthetic */ XMPushService f13159a;
    final /* synthetic */ C2748k f13160b;

    C2659o(int i, XMPushService xMPushService, C2748k c2748k) {
        this.f13159a = xMPushService;
        this.f13160b = c2748k;
        super(i);
    }

    public void m15065a() {
        try {
            C2748k a = C2655k.m15056e(this.f13159a, this.f13160b);
            a.m15587m().m15426a("miui_message_unrecognized", Constants.VIA_TO_TYPE_QQ_GROUP);
            this.f13159a.m14959b(a);
        } catch (Throwable e) {
            C2463b.m14125a(e);
            this.f13159a.m14956b(10, e);
        }
    }

    public String m15066b() {
        return "send ack message for unrecognized new miui message.";
    }
}
