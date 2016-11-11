package org.p122a.p123a.p152c.p160e;

import java.util.Collection;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.c.e.f */
public class C2973f implements HttpRequestInterceptor {
    private final Collection<? extends Header> f14904a;

    public C2973f() {
        this(null);
    }

    public C2973f(Collection<? extends Header> collection) {
        this.f14904a = collection;
    }

    public void process(HttpRequest httpRequest, HttpContext httpContext) {
        C3234a.m17886a((Object) httpRequest, "HTTP request");
        if (!httpRequest.getRequestLine().getMethod().equalsIgnoreCase("CONNECT")) {
            Collection collection = (Collection) httpRequest.getParams().getParameter("http.default-headers");
            if (collection == null) {
                collection = this.f14904a;
            }
            if (r0 != null) {
                for (Header addHeader : r0) {
                    httpRequest.addHeader(addHeader);
                }
            }
        }
    }
}
