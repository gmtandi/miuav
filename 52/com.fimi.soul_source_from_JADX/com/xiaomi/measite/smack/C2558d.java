package com.xiaomi.measite.smack;

import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.smack.C2557n;
import com.xiaomi.smack.packet.C2694d;
import java.util.Date;

/* renamed from: com.xiaomi.measite.smack.d */
class C2558d implements C2557n {
    final /* synthetic */ C2552a f12854a;

    C2558d(C2552a c2552a) {
        this.f12854a = c2552a;
    }

    public void m14583a(C2694d c2694d) {
        if (C2552a.f12843a) {
            C2463b.m14126b("SMACK " + this.f12854a.f12844b.format(new Date()) + " RCV PKT (" + this.f12854a.f12845c.hashCode() + "): " + c2694d.m15228a());
        }
    }
}
