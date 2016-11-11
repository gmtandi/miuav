package com.xiaomi.smack;

import com.xiaomi.push.service.XMPushService.C2629e;

/* renamed from: com.xiaomi.smack.u */
class C2712u extends C2629e {
    final /* synthetic */ long f13417a;
    final /* synthetic */ C2711t f13418b;

    C2712u(C2711t c2711t, int i, long j) {
        this.f13418b = c2711t;
        this.f13417a = j;
        super(i);
    }

    public void m15338a() {
        if (this.f13418b.m15172j() && !this.f13418b.m15160a(this.f13417a)) {
            this.f13418b.f13411u.m14956b(22, null);
        }
    }

    public String m15339b() {
        return "check the ping-pong.";
    }
}
