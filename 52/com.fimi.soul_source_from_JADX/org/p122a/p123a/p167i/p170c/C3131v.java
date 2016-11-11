package org.p122a.p123a.p167i.p170c;

import org.apache.http.conn.routing.HttpRoute;
import org.p122a.p123a.p124f.C3037e;
import org.p122a.p123a.p124f.C3040h;
import org.p122a.p123a.p162e.C2995a;
import org.p122a.p123a.p171m.C3130f;

/* renamed from: org.a.a.i.c.v */
class C3131v implements C3130f<HttpRoute, C3040h> {
    private final C3129u f15454a;
    private final C3037e<HttpRoute, C3040h> f15455b;

    C3131v(C3129u c3129u, C3037e<HttpRoute, C3040h> c3037e) {
        C3037e c3037e2;
        if (c3129u == null) {
            c3129u = new C3129u();
        }
        this.f15454a = c3129u;
        if (c3037e == null) {
            c3037e2 = C3126r.f15439a;
        }
        this.f15455b = c3037e2;
    }

    public C3040h m17639a(HttpRoute httpRoute) {
        C2995a c2995a = null;
        if (httpRoute.getProxyHost() != null) {
            c2995a = this.f15454a.m17636b(httpRoute.getProxyHost());
        }
        if (c2995a == null) {
            c2995a = this.f15454a.m17636b(httpRoute.getTargetHost());
        }
        if (c2995a == null) {
            c2995a = this.f15454a.m17635b();
        }
        if (c2995a == null) {
            c2995a = C2995a.f14958a;
        }
        return (C3040h) this.f15455b.m17143a(httpRoute, c2995a);
    }
}
