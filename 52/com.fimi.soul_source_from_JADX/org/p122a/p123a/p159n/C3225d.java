package org.p122a.p123a.p159n;

import java.util.List;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.protocol.HttpProcessor;

/* renamed from: org.a.a.n.d */
public class C3225d {
    private C3224b<HttpRequestInterceptor> f15695a;
    private C3224b<HttpResponseInterceptor> f15696b;

    C3225d() {
    }

    public static C3225d m17866a() {
        return new C3225d();
    }

    private C3224b<HttpRequestInterceptor> m17867c() {
        if (this.f15695a == null) {
            this.f15695a = new C3224b();
        }
        return this.f15695a;
    }

    private C3224b<HttpResponseInterceptor> m17868d() {
        if (this.f15696b == null) {
            this.f15696b = new C3224b();
        }
        return this.f15696b;
    }

    public C3225d m17869a(HttpRequestInterceptor httpRequestInterceptor) {
        if (httpRequestInterceptor != null) {
            m17867c().m17860a((Object) httpRequestInterceptor);
        }
        return this;
    }

    public C3225d m17870a(HttpResponseInterceptor httpResponseInterceptor) {
        if (httpResponseInterceptor != null) {
            m17868d().m17860a((Object) httpResponseInterceptor);
        }
        return this;
    }

    public C3225d m17871a(HttpRequestInterceptor... httpRequestInterceptorArr) {
        if (httpRequestInterceptorArr != null) {
            m17867c().m17862a((Object[]) httpRequestInterceptorArr);
        }
        return this;
    }

    public C3225d m17872a(HttpResponseInterceptor... httpResponseInterceptorArr) {
        if (httpResponseInterceptorArr != null) {
            m17868d().m17862a((Object[]) httpResponseInterceptorArr);
        }
        return this;
    }

    public C3225d m17873b(HttpRequestInterceptor httpRequestInterceptor) {
        if (httpRequestInterceptor != null) {
            m17867c().m17863b((Object) httpRequestInterceptor);
        }
        return this;
    }

    public C3225d m17874b(HttpResponseInterceptor httpResponseInterceptor) {
        if (httpResponseInterceptor != null) {
            m17868d().m17863b((Object) httpResponseInterceptor);
        }
        return this;
    }

    public C3225d m17875b(HttpRequestInterceptor... httpRequestInterceptorArr) {
        if (httpRequestInterceptorArr != null) {
            m17867c().m17865b((Object[]) httpRequestInterceptorArr);
        }
        return this;
    }

    public C3225d m17876b(HttpResponseInterceptor... httpResponseInterceptorArr) {
        if (httpResponseInterceptorArr != null) {
            m17868d().m17865b((Object[]) httpResponseInterceptorArr);
        }
        return this;
    }

    public HttpProcessor m17877b() {
        List list = null;
        List a = this.f15695a != null ? this.f15695a.m17859a() : null;
        if (this.f15696b != null) {
            list = this.f15696b.m17859a();
        }
        return new C3227f(a, list);
    }

    public C3225d m17878c(HttpRequestInterceptor httpRequestInterceptor) {
        return m17873b(httpRequestInterceptor);
    }

    public C3225d m17879c(HttpResponseInterceptor httpResponseInterceptor) {
        return m17874b(httpResponseInterceptor);
    }

    public C3225d m17880c(HttpRequestInterceptor... httpRequestInterceptorArr) {
        return m17875b(httpRequestInterceptorArr);
    }

    public C3225d m17881c(HttpResponseInterceptor... httpResponseInterceptorArr) {
        return m17876b(httpResponseInterceptorArr);
    }
}
