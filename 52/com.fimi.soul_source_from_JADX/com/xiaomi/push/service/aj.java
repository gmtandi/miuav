package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.push.service.XMPushService.C2629e;

class aj extends C2629e {
    final /* synthetic */ String f13118a;
    final /* synthetic */ byte[] f13119b;
    final /* synthetic */ XMPushService f13120c;

    aj(XMPushService xMPushService, int i, String str, byte[] bArr) {
        this.f13120c = xMPushService;
        this.f13118a = str;
        this.f13119b = bArr;
        super(i);
    }

    public void m14991a() {
        try {
            this.f13120c.m14951a(this.f13118a, this.f13119b);
        } catch (Throwable e) {
            C2463b.m14125a(e);
            this.f13120c.m14956b(10, e);
        }
    }

    public String m14992b() {
        return "send mi push message";
    }
}
