package org.p122a.p123a.p167i.p169b;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.p157d.C2993c;

/* renamed from: org.a.a.i.b.x */
class C3098x<V> implements Callable<V> {
    private final HttpUriRequest f15343a;
    private final HttpClient f15344b;
    private final AtomicBoolean f15345c;
    private final long f15346d;
    private long f15347e;
    private long f15348f;
    private final HttpContext f15349g;
    private final ResponseHandler<V> f15350h;
    private final C2993c<V> f15351i;
    private final C3092r f15352j;

    C3098x(HttpClient httpClient, HttpUriRequest httpUriRequest, HttpContext httpContext, ResponseHandler<V> responseHandler, C2993c<V> c2993c, C3092r c3092r) {
        this.f15345c = new AtomicBoolean(false);
        this.f15346d = System.currentTimeMillis();
        this.f15347e = -1;
        this.f15348f = -1;
        this.f15344b = httpClient;
        this.f15350h = responseHandler;
        this.f15343a = httpUriRequest;
        this.f15349g = httpContext;
        this.f15351i = c2993c;
        this.f15352j = c3092r;
    }

    public long m17464a() {
        return this.f15346d;
    }

    public long m17465b() {
        return this.f15347e;
    }

    public long m17466c() {
        return this.f15348f;
    }

    public V call() {
        if (this.f15345c.get()) {
            throw new IllegalStateException("call has been cancelled for request " + this.f15343a.getURI());
        }
        try {
            this.f15352j.m17385a().incrementAndGet();
            this.f15347e = System.currentTimeMillis();
            this.f15352j.m17386b().decrementAndGet();
            Object execute = this.f15344b.execute(this.f15343a, this.f15350h, this.f15349g);
            this.f15348f = System.currentTimeMillis();
            this.f15352j.m17387c().m17402a(this.f15347e);
            if (this.f15351i != null) {
                this.f15351i.m17005a(execute);
            }
            this.f15352j.m17389e().m17402a(this.f15347e);
            this.f15352j.m17390f().m17402a(this.f15347e);
            this.f15352j.m17385a().decrementAndGet();
            return execute;
        } catch (Exception e) {
            this.f15352j.m17388d().m17402a(this.f15347e);
            this.f15348f = System.currentTimeMillis();
            if (this.f15351i != null) {
                this.f15351i.m17004a(e);
            }
            throw e;
        } catch (Throwable th) {
            this.f15352j.m17389e().m17402a(this.f15347e);
            this.f15352j.m17390f().m17402a(this.f15347e);
            this.f15352j.m17385a().decrementAndGet();
        }
    }

    public void m17467d() {
        this.f15345c.set(true);
        if (this.f15351i != null) {
            this.f15351i.m17003a();
        }
    }
}
