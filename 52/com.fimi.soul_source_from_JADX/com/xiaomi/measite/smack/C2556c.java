package com.xiaomi.measite.smack;

import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.smack.util.C2555j;
import java.util.Date;

/* renamed from: com.xiaomi.measite.smack.c */
class C2556c implements C2555j {
    final /* synthetic */ C2552a f12853a;

    C2556c(C2552a c2552a) {
        this.f12853a = c2552a;
    }

    public void m14581a(String str) {
        C2463b.m14126b("SMACK " + this.f12853a.f12844b.format(new Date()) + " SENT (" + this.f12853a.f12845c.hashCode() + "): " + str);
    }
}
