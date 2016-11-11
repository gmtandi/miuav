package org.p122a.p123a.p167i.p169b;

import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.auth.AuthSchemeRegistry;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestExecutor;
import org.p122a.p123a.p124f.C3037e;
import org.p122a.p123a.p124f.C3040h;
import org.p122a.p123a.p151b.C2919d;
import org.p122a.p123a.p152c.p153a.C2923a;
import org.p122a.p123a.p152c.p153a.C2925c;
import org.p122a.p123a.p152c.p158d.C2965a;
import org.p122a.p123a.p152c.p160e.C2972e;
import org.p122a.p123a.p159n.C3227f;
import org.p122a.p123a.p159n.C3231j;
import org.p122a.p123a.p159n.C3232k;
import org.p122a.p123a.p162e.C2995a;
import org.p122a.p123a.p167i.C3201g;
import org.p122a.p123a.p167i.p168a.C3055b;
import org.p122a.p123a.p167i.p168a.C3058d;
import org.p122a.p123a.p167i.p168a.C3060f;
import org.p122a.p123a.p167i.p168a.C3071q;
import org.p122a.p123a.p167i.p170c.C3126r;
import org.p122a.p123a.p179l.C3213a;

/* renamed from: org.a.a.i.b.ag */
public class ag {
    private final C3037e<HttpRoute, C3040h> f15254a;
    private final C2995a f15255b;
    private final C2925c f15256c;
    private final HttpProcessor f15257d;
    private final HttpRequestExecutor f15258e;
    private final af f15259f;
    private final C3060f f15260g;
    private final C2919d f15261h;
    private final AuthSchemeRegistry f15262i;
    private final ConnectionReuseStrategy f15263j;

    public ag() {
        this(null, null, null);
    }

    public ag(C2925c c2925c) {
        this(null, null, c2925c);
    }

    public ag(C3037e<HttpRoute, C3040h> c3037e, C2995a c2995a, C2925c c2925c) {
        C3037e c3037e2;
        if (c3037e == null) {
            c3037e2 = C3126r.f15439a;
        }
        this.f15254a = c3037e2;
        if (c2995a == null) {
            c2995a = C2995a.f14958a;
        }
        this.f15255b = c2995a;
        if (c2925c == null) {
            c2925c = C2925c.f14794a;
        }
        this.f15256c = c2925c;
        this.f15257d = new C3227f(new C3231j(), new C2972e(), new C3232k());
        this.f15258e = new HttpRequestExecutor();
        this.f15259f = new af();
        this.f15260g = new C3060f();
        this.f15261h = new C2919d();
        this.f15262i = new AuthSchemeRegistry();
        this.f15262i.register(C2923a.f14784a, new C3055b());
        this.f15262i.register(C2923a.f14785b, new C3058d());
        this.f15262i.register(C2923a.f14786c, new C3071q());
        this.f15263j = new C3201g();
    }

    @Deprecated
    public ag(HttpParams httpParams) {
        this(null, C3213a.m17827c(httpParams), C2965a.m16870a(httpParams));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.net.Socket m17345a(org.apache.http.HttpHost r9, org.apache.http.HttpHost r10, org.apache.http.auth.Credentials r11) {
        /*
        r8 = this;
        r0 = "Proxy host";
        org.p122a.p123a.p180o.C3234a.m17886a(r9, r0);
        r0 = "Target host";
        org.p122a.p123a.p180o.C3234a.m17886a(r10, r0);
        r0 = "Credentials";
        org.p122a.p123a.p180o.C3234a.m17886a(r11, r0);
        r0 = r10.getPort();
        if (r0 > 0) goto L_0x015a;
    L_0x0015:
        r1 = new org.apache.http.HttpHost;
        r0 = r10.getHostName();
        r2 = 80;
        r3 = r10.getSchemeName();
        r1.<init>(r0, r2, r3);
    L_0x0024:
        r0 = new org.apache.http.conn.routing.HttpRoute;
        r2 = r8.f15256c;
        r2 = r2.m16743c();
        r4 = 0;
        r5 = org.apache.http.conn.routing.RouteInfo.TunnelType.TUNNELLED;
        r6 = org.apache.http.conn.routing.RouteInfo.LayerType.PLAIN;
        r3 = r9;
        r0.<init>(r1, r2, r3, r4, r5, r6);
        r2 = r8.f15254a;
        r3 = r8.f15255b;
        r2 = r2.m17143a(r0, r3);
        r6 = r2;
        r6 = (org.p122a.p123a.p124f.C3040h) r6;
        r5 = new org.a.a.n.a;
        r5.<init>();
        r7 = new org.apache.http.message.BasicHttpRequest;
        r2 = "CONNECT";
        r1 = r1.toHostString();
        r3 = org.apache.http.HttpVersion.HTTP_1_1;
        r7.<init>(r2, r1, r3);
        r1 = new org.a.a.i.b.e;
        r1.<init>();
        r2 = new org.apache.http.auth.AuthScope;
        r3 = r9.getHostName();
        r4 = r9.getPort();
        r2.<init>(r3, r4);
        r1.setCredentials(r2, r11);
        r2 = "http.target_host";
        r5.setAttribute(r2, r10);
        r2 = "http.connection";
        r5.setAttribute(r2, r6);
        r2 = "http.request";
        r5.setAttribute(r2, r7);
        r2 = "http.route";
        r5.setAttribute(r2, r0);
        r0 = "http.auth.proxy-scope";
        r2 = r8.f15261h;
        r5.setAttribute(r0, r2);
        r0 = "http.auth.credentials-provider";
        r5.setAttribute(r0, r1);
        r0 = "http.authscheme-registry";
        r1 = r8.f15262i;
        r5.setAttribute(r0, r1);
        r0 = "http.request-config";
        r1 = r8.f15256c;
        r5.setAttribute(r0, r1);
        r0 = r8.f15258e;
        r1 = r8.f15257d;
        r0.preProcess(r7, r1, r5);
    L_0x009c:
        r0 = r6.isOpen();
        if (r0 != 0) goto L_0x00b2;
    L_0x00a2:
        r0 = new java.net.Socket;
        r1 = r9.getHostName();
        r2 = r9.getPort();
        r0.<init>(r1, r2);
        r6.m17149a(r0);
    L_0x00b2:
        r0 = r8.f15260g;
        r1 = r8.f15261h;
        r0.m17193a(r7, r1, r5);
        r0 = r8.f15258e;
        r2 = r0.execute(r7, r6, r5);
        r0 = r2.getStatusLine();
        r0 = r0.getStatusCode();
        r1 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r0 >= r1) goto L_0x00e8;
    L_0x00cb:
        r0 = new org.apache.http.HttpException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r3 = "Unexpected response to CONNECT request: ";
        r1 = r1.append(r3);
        r2 = r2.getStatusLine();
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x00e8:
        r0 = r8.f15260g;
        r3 = r8.f15259f;
        r4 = r8.f15261h;
        r1 = r9;
        r0 = r0.m17194a(r1, r2, r3, r4, r5);
        if (r0 == 0) goto L_0x011b;
    L_0x00f5:
        r0 = r8.f15260g;
        r3 = r8.f15259f;
        r4 = r8.f15261h;
        r1 = r9;
        r0 = r0.m17195b(r1, r2, r3, r4, r5);
        if (r0 == 0) goto L_0x011b;
    L_0x0102:
        r0 = r8.f15263j;
        r0 = r0.keepAlive(r2, r5);
        if (r0 == 0) goto L_0x0117;
    L_0x010a:
        r0 = r2.getEntity();
        org.p122a.p123a.p180o.C3237d.m17904b(r0);
    L_0x0111:
        r0 = "Proxy-Authorization";
        r7.removeHeaders(r0);
        goto L_0x009c;
    L_0x0117:
        r6.close();
        goto L_0x0111;
    L_0x011b:
        r0 = r2.getStatusLine();
        r0 = r0.getStatusCode();
        r1 = 299; // 0x12b float:4.19E-43 double:1.477E-321;
        if (r0 <= r1) goto L_0x0155;
    L_0x0127:
        r0 = r2.getEntity();
        if (r0 == 0) goto L_0x0135;
    L_0x012d:
        r1 = new org.a.a.h.c;
        r1.<init>(r0);
        r2.setEntity(r1);
    L_0x0135:
        r6.close();
        r0 = new org.a.a.i.f.n;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r3 = "CONNECT refused by proxy: ";
        r1 = r1.append(r3);
        r3 = r2.getStatusLine();
        r1 = r1.append(r3);
        r1 = r1.toString();
        r0.<init>(r1, r2);
        throw r0;
    L_0x0155:
        r0 = r6.m17150b();
        return r0;
    L_0x015a:
        r1 = r10;
        goto L_0x0024;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.a.a.i.b.ag.a(org.apache.http.HttpHost, org.apache.http.HttpHost, org.apache.http.auth.Credentials):java.net.Socket");
    }

    @Deprecated
    public HttpParams m17346a() {
        return new BasicHttpParams();
    }

    @Deprecated
    public AuthSchemeRegistry m17347b() {
        return this.f15262i;
    }
}
