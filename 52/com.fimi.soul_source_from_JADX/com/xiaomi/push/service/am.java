package com.xiaomi.push.service;

import com.xiaomi.push.service.XMPushService.C2629e;
import com.xiaomi.smack.packet.C2699f;
import com.xiaomi.smack.packet.C2699f.C2698b;

class am extends C2629e {
    final /* synthetic */ XMPushService f13124a;

    am(XMPushService xMPushService, int i) {
        this.f13124a = xMPushService;
        super(i);
    }

    public void m14997a() {
        if (this.f13124a.f13089h != null) {
            this.f13124a.f13089h.m15156a(new C2699f(C2698b.unavailable), 15, null);
            this.f13124a.f13089h = null;
        }
    }

    public String m14998b() {
        return "disconnect for service destroy.";
    }
}
