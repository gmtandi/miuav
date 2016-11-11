package org.p122a.p123a.p167i.p169b;

import android.util.Log;
import java.io.Closeable;
import java.util.List;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.p124f.C3036d;
import org.p122a.p123a.p150a.C2914d;
import org.p122a.p123a.p151b.C2918c;
import org.p122a.p123a.p151b.C2919d;
import org.p122a.p123a.p152c.p153a.C2925c;
import org.p122a.p123a.p152c.p156c.C2941h;
import org.p122a.p123a.p152c.p156c.C2946d;
import org.p122a.p123a.p152c.p156c.C2947e;
import org.p122a.p123a.p152c.p156c.C2957p;
import org.p122a.p123a.p152c.p158d.C2965a;
import org.p122a.p123a.p152c.p160e.C2968a;
import org.p122a.p123a.p159n.C3223a;
import org.p122a.p123a.p162e.C2997c;
import org.p122a.p123a.p166g.C3045b;
import org.p122a.p123a.p167i.p176f.C3167b;
import org.p122a.p123a.p180o.C3234a;

@C2914d
/* renamed from: org.a.a.i.b.y */
class C3099y extends C3077h {
    private static final String f15353a = "HttpClient";
    private final C3167b f15354b;
    private final C3036d f15355c;
    private final HttpRoutePlanner f15356d;
    private final C2997c<C3045b> f15357e;
    private final C2997c<C2918c> f15358f;
    private final CookieStore f15359g;
    private final CredentialsProvider f15360h;
    private final C2925c f15361i;
    private final List<Closeable> f15362j;

    public C3099y(C3167b c3167b, C3036d c3036d, HttpRoutePlanner httpRoutePlanner, C2997c<C3045b> c2997c, C2997c<C2918c> c2997c2, CookieStore cookieStore, CredentialsProvider credentialsProvider, C2925c c2925c, List<Closeable> list) {
        C3234a.m17886a((Object) c3167b, "HTTP client exec chain");
        C3234a.m17886a((Object) c3036d, "HTTP connection manager");
        C3234a.m17886a((Object) httpRoutePlanner, "HTTP route planner");
        this.f15354b = c3167b;
        this.f15355c = c3036d;
        this.f15356d = httpRoutePlanner;
        this.f15357e = c2997c;
        this.f15358f = c2997c2;
        this.f15359g = cookieStore;
        this.f15360h = credentialsProvider;
        this.f15361i = c2925c;
        this.f15362j = list;
    }

    private void m17469a(C2968a c2968a) {
        if (c2968a.getAttribute(C2968a.f14895i) == null) {
            c2968a.setAttribute(C2968a.f14895i, new C2919d());
        }
        if (c2968a.getAttribute(C2968a.f14896j) == null) {
            c2968a.setAttribute(C2968a.f14896j, new C2919d());
        }
        if (c2968a.getAttribute(C2968a.f14898l) == null) {
            c2968a.setAttribute(C2968a.f14898l, this.f15358f);
        }
        if (c2968a.getAttribute(C2968a.f14889c) == null) {
            c2968a.setAttribute(C2968a.f14889c, this.f15357e);
        }
        if (c2968a.getAttribute(C2968a.f14892f) == null) {
            c2968a.setAttribute(C2968a.f14892f, this.f15359g);
        }
        if (c2968a.getAttribute(C2968a.f14893g) == null) {
            c2968a.setAttribute(C2968a.f14893g, this.f15360h);
        }
        if (c2968a.getAttribute(C2968a.f14899m) == null) {
            c2968a.setAttribute(C2968a.f14899m, this.f15361i);
        }
    }

    private HttpRoute m17470c(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) {
        return this.f15356d.determineRoute(httpHost == null ? (HttpHost) httpRequest.getParams().getParameter("http.default-host") : httpHost, httpRequest, httpContext);
    }

    protected C2946d m17471a(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) {
        C3234a.m17886a((Object) httpRequest, "HTTP request");
        C2941h c2941h = httpRequest instanceof C2941h ? (C2941h) httpRequest : null;
        try {
            Object a = C2957p.m16830a(httpRequest);
            if (httpContext == null) {
                httpContext = new C3223a();
            }
            C2968a a2 = C2968a.m16884a(httpContext);
            C2925c g_ = httpRequest instanceof C2947e ? ((C2947e) httpRequest).g_() : null;
            if (g_ == null) {
                g_ = C2965a.m16870a(httpRequest.getParams());
            }
            if (g_ != null) {
                a2.m16888a(g_);
            }
            m17469a(a2);
            return this.f15354b.m17698a(m17470c(httpHost, a, a2), a, a2, c2941h);
        } catch (Throwable e) {
            throw new ClientProtocolException(e);
        }
    }

    public void close() {
        this.f15355c.m17141b();
        if (this.f15362j != null) {
            for (Closeable close : this.f15362j) {
                try {
                    close.close();
                } catch (Throwable e) {
                    Log.e(f15353a, e.getMessage(), e);
                }
            }
        }
    }

    public ClientConnectionManager getConnectionManager() {
        return new C3100z(this);
    }

    public HttpParams getParams() {
        throw new UnsupportedOperationException();
    }
}
