package org.p122a.p123a.p152c.p160e;

import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p150a.C2912b;

@C2912b
/* renamed from: org.a.a.c.e.b */
public class C2969b implements HttpRequestInterceptor {
    public void process(HttpRequest httpRequest, HttpContext httpContext) {
        if (!httpRequest.containsHeader(C3004e.f15017c)) {
            httpRequest.addHeader(C3004e.f15017c, "gzip,deflate");
        }
    }
}
