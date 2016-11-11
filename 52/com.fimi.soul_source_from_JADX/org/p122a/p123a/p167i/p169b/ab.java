package org.p122a.p123a.p167i.p169b;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestExecutor;
import org.p122a.p123a.p124f.C3036d;
import org.p122a.p123a.p150a.C2914d;
import org.p122a.p123a.p152c.p153a.C2925c;
import org.p122a.p123a.p152c.p156c.C2941h;
import org.p122a.p123a.p152c.p156c.C2946d;
import org.p122a.p123a.p152c.p156c.C2947e;
import org.p122a.p123a.p152c.p156c.C2957p;
import org.p122a.p123a.p152c.p160e.C2968a;
import org.p122a.p123a.p159n.C3223a;
import org.p122a.p123a.p167i.C3201g;
import org.p122a.p123a.p167i.p176f.C3172f;
import org.p122a.p123a.p180o.C3234a;

@C2914d
/* renamed from: org.a.a.i.b.ab */
class ab extends C3077h {
    private final C3036d f15244a;
    private final C3172f f15245b;
    private final HttpParams f15246c;

    public ab(C3036d c3036d) {
        this.f15244a = (C3036d) C3234a.m17886a((Object) c3036d, "HTTP connection manager");
        this.f15245b = new C3172f(new HttpRequestExecutor(), c3036d, C3201g.f15638a, C3087k.f15279a);
        this.f15246c = new BasicHttpParams();
    }

    protected C2946d m17329a(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) {
        C3234a.m17886a((Object) httpHost, "Target host");
        C3234a.m17886a((Object) httpRequest, "HTTP request");
        C2941h c2941h = httpRequest instanceof C2941h ? (C2941h) httpRequest : null;
        try {
            C2957p a = C2957p.m16830a(httpRequest);
            if (httpContext == null) {
                httpContext = new C3223a();
            }
            C2968a a2 = C2968a.m16884a(httpContext);
            HttpRoute httpRoute = new HttpRoute(httpHost);
            C2925c g_ = httpRequest instanceof C2947e ? ((C2947e) httpRequest).g_() : null;
            if (g_ != null) {
                a2.m16888a(g_);
            }
            return this.f15245b.m17713a(httpRoute, a, a2, c2941h);
        } catch (Throwable e) {
            throw new ClientProtocolException(e);
        }
    }

    public void close() {
        this.f15244a.m17141b();
    }

    public ClientConnectionManager getConnectionManager() {
        return new ac(this);
    }

    public HttpParams getParams() {
        return this.f15246c;
    }
}
