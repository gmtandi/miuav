package org.p122a.p123a.p167i.p170c;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.HttpHost;
import org.p122a.p123a.p162e.C2995a;
import org.p122a.p123a.p162e.C3002h;

/* renamed from: org.a.a.i.c.u */
class C3129u {
    private final Map<HttpHost, C3002h> f15450a;
    private final Map<HttpHost, C2995a> f15451b;
    private volatile C3002h f15452c;
    private volatile C2995a f15453d;

    C3129u() {
        this.f15450a = new ConcurrentHashMap();
        this.f15451b = new ConcurrentHashMap();
    }

    public C3002h m17629a() {
        return this.f15452c;
    }

    public C3002h m17630a(HttpHost httpHost) {
        return (C3002h) this.f15450a.get(httpHost);
    }

    public void m17631a(C2995a c2995a) {
        this.f15453d = c2995a;
    }

    public void m17632a(C3002h c3002h) {
        this.f15452c = c3002h;
    }

    public void m17633a(HttpHost httpHost, C2995a c2995a) {
        this.f15451b.put(httpHost, c2995a);
    }

    public void m17634a(HttpHost httpHost, C3002h c3002h) {
        this.f15450a.put(httpHost, c3002h);
    }

    public C2995a m17635b() {
        return this.f15453d;
    }

    public C2995a m17636b(HttpHost httpHost) {
        return (C2995a) this.f15451b.get(httpHost);
    }
}
