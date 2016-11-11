package org.p122a.p123a.p167i.p170c;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.apache.http.HttpClientConnection;
import org.p122a.p123a.p124f.C3013a;

/* renamed from: org.a.a.i.c.t */
class C3128t implements C3013a {
    final /* synthetic */ Future f15448a;
    final /* synthetic */ C3127s f15449b;

    C3128t(C3127s c3127s, Future future) {
        this.f15449b = c3127s;
        this.f15448a = future;
    }

    public HttpClientConnection m17627a(long j, TimeUnit timeUnit) {
        return this.f15449b.m17603a(this.f15448a, j, timeUnit);
    }

    public boolean m17628a() {
        return this.f15448a.cancel(true);
    }
}
