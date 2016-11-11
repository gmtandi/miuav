package com.xiaomi.push.service;

import com.tencent.connect.common.Constants;
import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.push.service.C2669v.C2667b;
import com.xiaomi.push.service.C2669v.C2668c;
import com.xiaomi.push.service.XMPushService.C2629e;
import com.xiaomi.push.service.XMPushService.C2630a;
import java.util.Collection;

/* renamed from: com.xiaomi.push.service.i */
public class C2653i extends C2629e {
    private XMPushService f13146a;
    private byte[] f13147b;
    private String f13148c;
    private String f13149d;
    private String f13150f;

    public C2653i(XMPushService xMPushService, String str, String str2, String str3, byte[] bArr) {
        super(9);
        this.f13146a = xMPushService;
        this.f13148c = str;
        this.f13147b = bArr;
        this.f13149d = str2;
        this.f13150f = str3;
    }

    public void m15035a() {
        C2650f a;
        Collection c;
        C2667b c2667b;
        C2650f a2 = C2651g.m15021a(this.f13146a);
        if (a2 == null) {
            try {
                a = C2651g.m15022a(this.f13146a, this.f13148c, this.f13149d, this.f13150f);
            } catch (Throwable e) {
                C2463b.m14125a(e);
                a = a2;
            } catch (Throwable e2) {
                C2463b.m14125a(e2);
            }
            if (a != null) {
                C2463b.m14127c("no account for mipush");
                C2654j.m15037a(this.f13146a, ErrorCode.ERROR_AUTHERICATION_ERROR, "no account.");
            }
            c = C2669v.m15106a().m15118c(Constants.VIA_SHARE_TYPE_TEXT);
            if (c.isEmpty()) {
                c2667b = (C2667b) c.iterator().next();
            } else {
                c2667b = a.m15020a(this.f13146a);
                this.f13146a.m14947a(c2667b);
                C2669v.m15106a().m15111a(c2667b);
            }
            if (this.f13146a.m14964f()) {
                this.f13146a.m14952a(true);
                return;
            }
            try {
                if (c2667b.f13189m == C2668c.binded) {
                    this.f13146a.m14951a(this.f13148c, this.f13147b);
                    return;
                } else if (c2667b.f13189m == C2668c.unbind) {
                    XMPushService xMPushService = this.f13146a;
                    XMPushService xMPushService2 = this.f13146a;
                    xMPushService2.getClass();
                    xMPushService.m14945a(new C2630a(xMPushService2, c2667b));
                    return;
                } else {
                    return;
                }
            } catch (Throwable e22) {
                C2463b.m14125a(e22);
                this.f13146a.m14956b(10, e22);
                return;
            }
        }
        a = a2;
        if (a != null) {
            c = C2669v.m15106a().m15118c(Constants.VIA_SHARE_TYPE_TEXT);
            if (c.isEmpty()) {
                c2667b = (C2667b) c.iterator().next();
            } else {
                c2667b = a.m15020a(this.f13146a);
                this.f13146a.m14947a(c2667b);
                C2669v.m15106a().m15111a(c2667b);
            }
            if (this.f13146a.m14964f()) {
                this.f13146a.m14952a(true);
                return;
            } else if (c2667b.f13189m == C2668c.binded) {
                this.f13146a.m14951a(this.f13148c, this.f13147b);
                return;
            } else if (c2667b.f13189m == C2668c.unbind) {
                XMPushService xMPushService3 = this.f13146a;
                XMPushService xMPushService22 = this.f13146a;
                xMPushService22.getClass();
                xMPushService3.m14945a(new C2630a(xMPushService22, c2667b));
                return;
            } else {
                return;
            }
        }
        C2463b.m14127c("no account for mipush");
        C2654j.m15037a(this.f13146a, ErrorCode.ERROR_AUTHERICATION_ERROR, "no account.");
    }

    public String m15036b() {
        return "register app";
    }
}
