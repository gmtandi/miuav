package org.p122a.p123a.p152c;

import java.util.Map;
import java.util.Queue;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScheme;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.p151b.C2916a;

/* renamed from: org.a.a.c.b */
public interface C2940b {
    Queue<C2916a> m16812a(Map<String, Header> map, HttpHost httpHost, HttpResponse httpResponse, HttpContext httpContext);

    void m16813a(HttpHost httpHost, AuthScheme authScheme, HttpContext httpContext);

    boolean m16814a(HttpHost httpHost, HttpResponse httpResponse, HttpContext httpContext);

    Map<String, Header> m16815b(HttpHost httpHost, HttpResponse httpResponse, HttpContext httpContext);

    void m16816b(HttpHost httpHost, AuthScheme authScheme, HttpContext httpContext);
}
