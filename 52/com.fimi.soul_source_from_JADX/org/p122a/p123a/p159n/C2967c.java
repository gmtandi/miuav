package org.p122a.p123a.p159n;

import org.apache.http.HttpConnection;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p180o.C3234a;

@C2913c
/* renamed from: org.a.a.n.c */
public class C2967c implements HttpContext {
    public static final String f14881n = "http.connection";
    public static final String f14882o = "http.request";
    public static final String f14883p = "http.response";
    public static final String f14884q = "http.target_host";
    public static final String f14885r = "http.request_sent";
    private final HttpContext f14886a;

    public C2967c() {
        this.f14886a = new C3223a();
    }

    public C2967c(HttpContext httpContext) {
        this.f14886a = httpContext;
    }

    public static C2967c m16873b(HttpContext httpContext) {
        C3234a.m17886a((Object) httpContext, "HTTP context");
        return httpContext instanceof C2967c ? (C2967c) httpContext : new C2967c(httpContext);
    }

    public static C2967c m16874o() {
        return new C2967c(new C3223a());
    }

    public <T> T m16875a(String str, Class<T> cls) {
        C3234a.m17886a((Object) cls, "Attribute class");
        Object attribute = getAttribute(str);
        return attribute == null ? null : cls.cast(attribute);
    }

    public void m16876a(HttpHost httpHost) {
        setAttribute(f14884q, httpHost);
    }

    public <T extends HttpConnection> T m16877b(Class<T> cls) {
        return (HttpConnection) m16875a(f14881n, cls);
    }

    public Object getAttribute(String str) {
        return this.f14886a.getAttribute(str);
    }

    public HttpConnection m16878p() {
        return (HttpConnection) m16875a(f14881n, HttpConnection.class);
    }

    public HttpRequest m16879q() {
        return (HttpRequest) m16875a(f14882o, HttpRequest.class);
    }

    public boolean m16880r() {
        Boolean bool = (Boolean) m16875a(f14885r, Boolean.class);
        return bool != null && bool.booleanValue();
    }

    public Object removeAttribute(String str) {
        return this.f14886a.removeAttribute(str);
    }

    public HttpResponse m16881s() {
        return (HttpResponse) m16875a(f14883p, HttpResponse.class);
    }

    public void setAttribute(String str, Object obj) {
        this.f14886a.setAttribute(str, obj);
    }

    public HttpHost m16882t() {
        return (HttpHost) m16875a(f14884q, HttpHost.class);
    }
}
