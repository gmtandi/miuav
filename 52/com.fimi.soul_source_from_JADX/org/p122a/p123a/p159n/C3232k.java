package org.p122a.p123a.p159n;

import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.n.k */
public class C3232k implements HttpRequestInterceptor {
    private final String f15701a;

    public C3232k() {
        this(null);
    }

    public C3232k(String str) {
        this.f15701a = str;
    }

    public void process(HttpRequest httpRequest, HttpContext httpContext) {
        C3234a.m17886a((Object) httpRequest, "HTTP request");
        if (!httpRequest.containsHeader(C3004e.f15013Y)) {
            String str = null;
            HttpParams params = httpRequest.getParams();
            if (params != null) {
                str = (String) params.getParameter("http.useragent");
            }
            if (str == null) {
                str = this.f15701a;
            }
            if (str != null) {
                httpRequest.addHeader(C3004e.f15013Y, str);
            }
        }
    }
}
