package com.xiaomi.push.service;

import com.tencent.connect.common.Constants;
import com.xiaomi.channel.commonutils.misc.C2464a;
import com.xiaomi.push.service.XMPushService.C2629e;
import com.xiaomi.smack.C2674k;

class ai extends C2629e {
    final /* synthetic */ int f13114a;
    final /* synthetic */ byte[] f13115b;
    final /* synthetic */ String f13116c;
    final /* synthetic */ XMPushService f13117d;

    ai(XMPushService xMPushService, int i, int i2, byte[] bArr, String str) {
        this.f13117d = xMPushService;
        this.f13114a = i2;
        this.f13115b = bArr;
        this.f13116c = str;
        super(i);
    }

    public void m14989a() {
        C2651g.m15029f(this.f13117d);
        C2669v.m15106a().m15112a(Constants.VIA_SHARE_TYPE_TEXT);
        C2464a.m14129a(this.f13114a);
        this.f13117d.f13083b.m15138c(C2674k.m15134d());
        this.f13117d.m14953a(this.f13115b, this.f13116c);
    }

    public String m14990b() {
        return "clear account cache.";
    }
}
