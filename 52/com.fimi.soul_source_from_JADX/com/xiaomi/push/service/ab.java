package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.push.service.XMPushService.C2629e;
import com.xiaomi.smack.packet.C2694d;

public class ab extends C2629e {
    private XMPushService f13102a;
    private C2694d f13103b;

    public ab(XMPushService xMPushService, C2694d c2694d) {
        super(4);
        this.f13102a = null;
        this.f13102a = xMPushService;
        this.f13103b = c2694d;
    }

    public void m14973a() {
        try {
            this.f13102a.m14948a(this.f13103b);
        } catch (Throwable e) {
            C2463b.m14125a(e);
            this.f13102a.m14956b(10, e);
        }
    }

    public String m14974b() {
        return "send a message.";
    }
}
