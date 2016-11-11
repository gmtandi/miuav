package com.xiaomi.push.service;

import com.fimi.soul.module.setting.newhand.ae;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import com.xiaomi.push.service.XMPushService.C2632c;

public class aa {
    private static int f13097e;
    private XMPushService f13098a;
    private int f13099b;
    private long f13100c;
    private int f13101d;

    static {
        f13097e = ae.f9482j;
    }

    public aa(XMPushService xMPushService) {
        this.f13101d = 0;
        this.f13098a = xMPushService;
        this.f13099b = 10;
        this.f13100c = 0;
    }

    private int m14970b() {
        int i = 40;
        if (this.f13101d > 13) {
            return ae.f9482j;
        }
        if (this.f13101d > 7) {
            return 60;
        }
        if (this.f13101d >= 1) {
            return 10;
        }
        if (this.f13100c == 0) {
            return 0;
        }
        long currentTimeMillis = System.currentTimeMillis() - this.f13100c;
        if (currentTimeMillis < MiStatInterface.MIN_UPLOAD_INTERVAL) {
            if (this.f13099b >= f13097e) {
                return this.f13099b;
            }
            i = this.f13099b;
            this.f13099b = (int) (((double) this.f13099b) * 1.5d);
            return i;
        } else if (currentTimeMillis < 900000) {
            if (this.f13099b < 40) {
                i = this.f13099b;
            }
            this.f13099b = i;
            return this.f13099b;
        } else if (currentTimeMillis < 1800000) {
            this.f13099b = this.f13099b < 20 ? this.f13099b : 20;
            return this.f13099b;
        } else {
            this.f13099b = 10;
            return this.f13099b;
        }
    }

    public void m14971a() {
        this.f13100c = System.currentTimeMillis();
        this.f13098a.m14943a(1);
        this.f13101d = 0;
    }

    public void m14972a(boolean z) {
        if (!this.f13098a.m14961c()) {
            C2463b.m14126b("should not reconnect as no client or network.");
        } else if (z) {
            this.f13098a.m14943a(1);
            XMPushService xMPushService = this.f13098a;
            XMPushService xMPushService2 = this.f13098a;
            xMPushService2.getClass();
            xMPushService.m14945a(new C2632c(xMPushService2));
            this.f13101d++;
        } else if (!this.f13098a.m14960b(1)) {
            int b = m14970b();
            C2463b.m14123a("schedule reconnect in " + b + "s");
            XMPushService xMPushService3 = this.f13098a;
            XMPushService xMPushService4 = this.f13098a;
            xMPushService4.getClass();
            xMPushService3.m14946a(new C2632c(xMPushService4), (long) (b * XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER));
            this.f13101d++;
        }
    }
}
