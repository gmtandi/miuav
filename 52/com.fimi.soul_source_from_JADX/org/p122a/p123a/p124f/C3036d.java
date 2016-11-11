package org.p122a.p123a.p124f;

import java.util.concurrent.TimeUnit;
import org.apache.http.HttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.protocol.HttpContext;

/* renamed from: org.a.a.f.d */
public interface C3036d {
    C3013a m17135a(HttpRoute httpRoute, Object obj);

    void m17136a();

    void m17137a(long j, TimeUnit timeUnit);

    void m17138a(HttpClientConnection httpClientConnection, Object obj, long j, TimeUnit timeUnit);

    void m17139a(HttpClientConnection httpClientConnection, HttpRoute httpRoute, int i, HttpContext httpContext);

    void m17140a(HttpClientConnection httpClientConnection, HttpRoute httpRoute, HttpContext httpContext);

    void m17141b();

    void m17142b(HttpClientConnection httpClientConnection, HttpRoute httpRoute, HttpContext httpContext);
}
