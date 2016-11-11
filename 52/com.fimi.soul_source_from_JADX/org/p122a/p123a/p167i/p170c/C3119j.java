package org.p122a.p123a.p167i.p170c;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.p124f.C3041i;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.i.c.j */
public class C3119j extends C3118k {
    private final HttpHost f15423a;

    public C3119j(HttpHost httpHost) {
        this(httpHost, null);
    }

    public C3119j(HttpHost httpHost, C3041i c3041i) {
        super(c3041i);
        this.f15423a = (HttpHost) C3234a.m17886a((Object) httpHost, "Proxy host");
    }

    protected HttpHost m17581a(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) {
        return this.f15423a;
    }
}
