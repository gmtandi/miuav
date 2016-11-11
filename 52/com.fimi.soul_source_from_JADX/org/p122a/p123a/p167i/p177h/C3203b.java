package org.p122a.p123a.p167i.p177h;

import java.util.concurrent.atomic.AtomicLong;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpHost;
import org.apache.http.params.HttpParams;
import org.p122a.p123a.p150a.C2914d;
import org.p122a.p123a.p162e.C2995a;
import org.p122a.p123a.p162e.C3002h;
import org.p122a.p123a.p171m.C3106a;
import org.p122a.p123a.p171m.C3130f;

@C2914d
/* renamed from: org.a.a.i.h.b */
public class C3203b extends C3106a<HttpHost, HttpClientConnection, C3204c> {
    private static final AtomicLong f15644a;

    static {
        f15644a = new AtomicLong();
    }

    public C3203b() {
        super(new C3202a(C3002h.f14978a, C2995a.f14958a), 2, 20);
    }

    public C3203b(C3002h c3002h, C2995a c2995a) {
        super(new C3202a(c3002h, c2995a), 2, 20);
    }

    public C3203b(C3130f<HttpHost, HttpClientConnection> c3130f) {
        super(c3130f, 2, 20);
    }

    @Deprecated
    public C3203b(HttpParams httpParams) {
        super(new C3202a(httpParams), 2, 20);
    }

    protected C3204c m17782a(HttpHost httpHost, HttpClientConnection httpClientConnection) {
        return new C3204c(Long.toString(f15644a.getAndIncrement()), httpHost, httpClientConnection);
    }
}
