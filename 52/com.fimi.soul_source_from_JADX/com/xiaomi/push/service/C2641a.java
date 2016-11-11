package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.push.service.XMPushService.C2629e;
import com.xiaomi.smack.packet.C2696c;

/* renamed from: com.xiaomi.push.service.a */
public class C2641a extends C2629e {
    private XMPushService f13095a;
    private C2696c[] f13096b;

    public C2641a(XMPushService xMPushService, C2696c[] c2696cArr) {
        super(4);
        this.f13095a = null;
        this.f13095a = xMPushService;
        this.f13096b = c2696cArr;
    }

    public void m14968a() {
        try {
            this.f13095a.m14954a(this.f13096b);
        } catch (Throwable e) {
            C2463b.m14125a(e);
            this.f13095a.m14956b(10, e);
        }
    }

    public String m14969b() {
        return "batch send message.";
    }
}
