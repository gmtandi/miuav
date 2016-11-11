package com.xiaomi.smack;

import com.xiaomi.push.service.XMPushService.C2629e;

/* renamed from: com.xiaomi.smack.h */
class C2685h extends C2629e {
    final /* synthetic */ Exception f13286a;
    final /* synthetic */ C2679b f13287b;

    C2685h(C2679b c2679b, int i, Exception exception) {
        this.f13287b = c2679b;
        this.f13286a = exception;
        super(i);
    }

    public void m15200a() {
        this.f13287b.n.m14956b(0, this.f13286a);
    }

    public String m15201b() {
        return "shutdown the connection. " + this.f13286a;
    }
}
