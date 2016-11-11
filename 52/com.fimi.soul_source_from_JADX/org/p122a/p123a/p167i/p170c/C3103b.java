package org.p122a.p123a.p167i.p170c;

import java.util.concurrent.TimeUnit;
import org.apache.http.HttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.p122a.p123a.p124f.C3013a;

/* renamed from: org.a.a.i.c.b */
class C3103b implements C3013a {
    final /* synthetic */ HttpRoute f15376a;
    final /* synthetic */ Object f15377b;
    final /* synthetic */ C3102a f15378c;

    C3103b(C3102a c3102a, HttpRoute httpRoute, Object obj) {
        this.f15378c = c3102a;
        this.f15376a = httpRoute;
        this.f15377b = obj;
    }

    public HttpClientConnection m17493a(long j, TimeUnit timeUnit) {
        return this.f15378c.m17486b(this.f15376a, this.f15377b);
    }

    public boolean m17494a() {
        return false;
    }
}
