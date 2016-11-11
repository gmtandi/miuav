package com.xiaomi.measite.smack;

import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.smack.C2559l;
import java.util.Date;

/* renamed from: com.xiaomi.measite.smack.e */
class C2560e implements C2559l {
    final /* synthetic */ C2552a f12855a;

    C2560e(C2552a c2552a) {
        this.f12855a = c2552a;
    }

    public void m14588a() {
        C2463b.m14126b("SMACK " + this.f12855a.f12844b.format(new Date()) + " Connection reconnected (" + this.f12855a.f12845c.hashCode() + ")");
    }

    public void m14589a(int i, Exception exception) {
        C2463b.m14126b("SMACK " + this.f12855a.f12844b.format(new Date()) + " Connection closed (" + this.f12855a.f12845c.hashCode() + ")");
    }

    public void m14590a(Exception exception) {
        C2463b.m14126b("SMACK " + this.f12855a.f12844b.format(new Date()) + " Reconnection failed due to an exception (" + this.f12855a.f12845c.hashCode() + ")");
        exception.printStackTrace();
    }

    public void m14591b() {
        C2463b.m14126b("SMACK " + this.f12855a.f12844b.format(new Date()) + " Connection started (" + this.f12855a.f12845c.hashCode() + ")");
    }
}
