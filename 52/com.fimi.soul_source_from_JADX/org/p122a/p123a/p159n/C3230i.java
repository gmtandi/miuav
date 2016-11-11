package org.p122a.p123a.p159n;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpDateGenerator;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p150a.C2914d;
import org.p122a.p123a.p180o.C3234a;

@C2914d
/* renamed from: org.a.a.n.i */
public class C3230i implements HttpRequestInterceptor {
    private static final HttpDateGenerator f15700a;

    static {
        f15700a = new HttpDateGenerator();
    }

    public void process(HttpRequest httpRequest, HttpContext httpContext) {
        C3234a.m17886a((Object) httpRequest, "HTTP request");
        if ((httpRequest instanceof HttpEntityEnclosingRequest) && !httpRequest.containsHeader(C3004e.f15032r)) {
            httpRequest.setHeader(C3004e.f15032r, f15700a.getCurrentDate());
        }
    }
}
