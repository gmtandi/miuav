package org.p122a.p123a.p152c.p156c;

import java.io.IOException;
import org.apache.http.conn.ConnectionReleaseTrigger;
import org.p122a.p123a.p157d.C2943b;

/* renamed from: org.a.a.c.c.c */
class C2945c implements C2943b {
    final /* synthetic */ ConnectionReleaseTrigger f14853a;
    final /* synthetic */ C2942a f14854b;

    C2945c(C2942a c2942a, ConnectionReleaseTrigger connectionReleaseTrigger) {
        this.f14854b = c2942a;
        this.f14853a = connectionReleaseTrigger;
    }

    public boolean m16823a() {
        try {
            this.f14853a.abortConnection();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
