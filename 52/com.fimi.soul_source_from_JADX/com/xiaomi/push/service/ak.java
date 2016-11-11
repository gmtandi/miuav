package com.xiaomi.push.service;

import android.content.Intent;
import com.xiaomi.push.service.XMPushService.C2629e;

class ak extends C2629e {
    final /* synthetic */ Intent f13121a;
    final /* synthetic */ XMPushService f13122b;

    ak(XMPushService xMPushService, int i, Intent intent) {
        this.f13122b = xMPushService;
        this.f13121a = intent;
        super(i);
    }

    public void m14993a() {
        this.f13122b.m14922a(this.f13121a);
    }

    public String m14994b() {
        return "add Stats message";
    }
}
