package org.p122a.p123a.p167i.p170c;

import com.facebook.common.util.UriUtil;
import java.net.InetAddress;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.ProtocolException;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.p124f.C3041i;
import org.p122a.p123a.p124f.C3042j;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p152c.p153a.C2925c;
import org.p122a.p123a.p152c.p160e.C2968a;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.i.c.k */
public class C3118k implements HttpRoutePlanner {
    private final C3041i f15422a;

    public C3118k(C3041i c3041i) {
        if (c3041i == null) {
            c3041i = C3120l.f15424a;
        }
        this.f15422a = c3041i;
    }

    protected HttpHost m17580a(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) {
        return null;
    }

    public HttpRoute determineRoute(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) {
        C3234a.m17886a((Object) httpRequest, "Request");
        if (httpHost == null) {
            throw new ProtocolException("Target host is not specified");
        }
        C2925c n = C2968a.m16884a(httpContext).m16906n();
        InetAddress c = n.m16743c();
        HttpHost b = n.m16742b();
        if (b == null) {
            b = m17580a(httpHost, httpRequest, httpContext);
        }
        if (httpHost.getPort() <= 0) {
            try {
                httpHost = new HttpHost(httpHost.getHostName(), this.f15422a.m17152a(httpHost), httpHost.getSchemeName());
            } catch (C3042j e) {
                throw new HttpException(e.getMessage());
            }
        }
        boolean equalsIgnoreCase = httpHost.getSchemeName().equalsIgnoreCase(UriUtil.HTTPS_SCHEME);
        return b == null ? new HttpRoute(httpHost, c, equalsIgnoreCase) : new HttpRoute(httpHost, c, b, equalsIgnoreCase);
    }
}
