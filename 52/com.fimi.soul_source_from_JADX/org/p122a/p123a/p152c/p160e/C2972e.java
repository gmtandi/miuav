package org.p122a.p123a.p152c.p160e;

import android.util.Log;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.conn.routing.RouteInfo;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.c.e.e */
public class C2972e implements HttpRequestInterceptor {
    private static final String f14902a = "HttpClient";
    private static final String f14903b = "Proxy-Connection";

    public void process(HttpRequest httpRequest, HttpContext httpContext) {
        C3234a.m17886a((Object) httpRequest, "HTTP request");
        if (httpRequest.getRequestLine().getMethod().equalsIgnoreCase("CONNECT")) {
            httpRequest.setHeader(f14903b, "Keep-Alive");
            return;
        }
        RouteInfo b = C2968a.m16884a(httpContext).m16893b();
        if (b != null) {
            if ((b.getHopCount() == 1 || b.isTunnelled()) && !httpRequest.containsHeader(C3004e.f15024j)) {
                httpRequest.addHeader(C3004e.f15024j, "Keep-Alive");
            }
            if (b.getHopCount() == 2 && !b.isTunnelled() && !httpRequest.containsHeader(f14903b)) {
                httpRequest.addHeader(f14903b, "Keep-Alive");
            }
        } else if (Log.isLoggable(f14902a, 3)) {
            Log.d(f14902a, "Connection route not set in the context");
        }
    }
}
