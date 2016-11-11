package com.xiaomi.push.service;

import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.push.service.XMPushService.C2629e;
import com.xiaomi.xmpush.thrift.C2748k;

/* renamed from: com.xiaomi.push.service.q */
final class C2661q extends C2629e {
    final /* synthetic */ XMPushService f13164a;
    final /* synthetic */ C2748k f13165b;
    final /* synthetic */ String f13166c;
    final /* synthetic */ String f13167d;

    C2661q(int i, XMPushService xMPushService, C2748k c2748k, String str, String str2) {
        this.f13164a = xMPushService;
        this.f13165b = c2748k;
        this.f13166c = str;
        this.f13167d = str2;
        super(i);
    }

    public void m15069a() {
        try {
            C2748k a = C2655k.m15056e(this.f13164a, this.f13165b);
            a.f13747h.m15426a(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2, this.f13166c);
            a.f13747h.m15426a("reason", this.f13167d);
            this.f13164a.m14959b(a);
        } catch (Throwable e) {
            C2463b.m14125a(e);
            this.f13164a.m14956b(10, e);
        }
    }

    public String m15070b() {
        return "send wrong message ack for message.";
    }
}
