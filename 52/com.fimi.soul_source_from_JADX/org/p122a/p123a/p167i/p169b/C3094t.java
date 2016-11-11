package org.p122a.p123a.p167i.p169b;

import java.io.Closeable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.p150a.C2914d;
import org.p122a.p123a.p157d.C2993c;

@C2914d
/* renamed from: org.a.a.i.b.t */
public class C3094t implements Closeable {
    private final HttpClient f15294a;
    private final ExecutorService f15295b;
    private final C3092r f15296c;
    private final AtomicBoolean f15297d;

    public C3094t(HttpClient httpClient, ExecutorService executorService) {
        this.f15296c = new C3092r();
        this.f15297d = new AtomicBoolean(false);
        this.f15294a = httpClient;
        this.f15295b = executorService;
    }

    public C3092r m17404a() {
        return this.f15296c;
    }

    public <T> C3097w<T> m17405a(HttpUriRequest httpUriRequest, HttpContext httpContext, ResponseHandler<T> responseHandler) {
        return m17406a(httpUriRequest, httpContext, responseHandler, null);
    }

    public <T> C3097w<T> m17406a(HttpUriRequest httpUriRequest, HttpContext httpContext, ResponseHandler<T> responseHandler, C2993c<T> c2993c) {
        if (this.f15297d.get()) {
            throw new IllegalStateException("Close has been called on this httpclient instance.");
        }
        this.f15296c.m17386b().incrementAndGet();
        Object c3097w = new C3097w(httpUriRequest, new C3098x(this.f15294a, httpUriRequest, httpContext, responseHandler, c2993c, this.f15296c));
        this.f15295b.execute(c3097w);
        return c3097w;
    }

    public void close() {
        this.f15297d.set(true);
        this.f15295b.shutdownNow();
        if (this.f15294a instanceof Closeable) {
            ((Closeable) this.f15294a).close();
        }
    }
}
