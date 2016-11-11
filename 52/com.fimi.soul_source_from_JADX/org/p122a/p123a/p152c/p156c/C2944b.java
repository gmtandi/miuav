package org.p122a.p123a.p152c.p156c;

import org.apache.http.conn.ClientConnectionRequest;
import org.p122a.p123a.p157d.C2943b;

/* renamed from: org.a.a.c.c.b */
class C2944b implements C2943b {
    final /* synthetic */ ClientConnectionRequest f14851a;
    final /* synthetic */ C2942a f14852b;

    C2944b(C2942a c2942a, ClientConnectionRequest clientConnectionRequest) {
        this.f14852b = c2942a;
        this.f14851a = clientConnectionRequest;
    }

    public boolean m16822a() {
        this.f14851a.abortRequest();
        return true;
    }
}
