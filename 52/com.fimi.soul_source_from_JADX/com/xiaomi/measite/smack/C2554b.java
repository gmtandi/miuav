package com.xiaomi.measite.smack;

import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.smack.util.C2553f;
import java.util.Date;

/* renamed from: com.xiaomi.measite.smack.b */
class C2554b implements C2553f {
    final /* synthetic */ C2552a f12852a;

    C2554b(C2552a c2552a) {
        this.f12852a = c2552a;
    }

    public void m14579a(String str) {
        C2463b.m14126b("SMACK " + this.f12852a.f12844b.format(new Date()) + " RCV  (" + this.f12852a.f12845c.hashCode() + "): " + str);
    }
}
