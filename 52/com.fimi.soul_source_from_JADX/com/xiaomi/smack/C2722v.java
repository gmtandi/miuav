package com.xiaomi.smack;

import com.xiaomi.push.service.XMPushService.C2629e;

/* renamed from: com.xiaomi.smack.v */
class C2722v extends C2629e {
    final /* synthetic */ int f13444a;
    final /* synthetic */ Exception f13445b;
    final /* synthetic */ C2711t f13446c;

    C2722v(C2711t c2711t, int i, int i2, Exception exception) {
        this.f13446c = c2711t;
        this.f13444a = i2;
        this.f13445b = exception;
        super(i);
    }

    public void m15377a() {
        this.f13446c.f13411u.m14956b(this.f13444a, this.f13445b);
    }

    public String m15378b() {
        return "shutdown the connection. " + this.f13444a + ", " + this.f13445b;
    }
}
