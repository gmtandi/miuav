package org.p122a.p123a.p167i.p170c;

import android.util.Log;
import com.facebook.common.util.UriUtil;
import java.io.Closeable;
import java.net.InetSocketAddress;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpHost;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.protocol.HttpContext;
import org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.p122a.p123a.p124f.C3013a;
import org.p122a.p123a.p124f.C3017b;
import org.p122a.p123a.p124f.C3036d;
import org.p122a.p123a.p124f.C3037e;
import org.p122a.p123a.p124f.C3040h;
import org.p122a.p123a.p124f.C3041i;
import org.p122a.p123a.p124f.p125c.C3026h;
import org.p122a.p123a.p124f.p164b.C3014a;
import org.p122a.p123a.p124f.p164b.C3016c;
import org.p122a.p123a.p150a.C2914d;
import org.p122a.p123a.p162e.C2995a;
import org.p122a.p123a.p162e.C2997c;
import org.p122a.p123a.p162e.C3000f;
import org.p122a.p123a.p162e.C3001g;
import org.p122a.p123a.p162e.C3002h;
import org.p122a.p123a.p171m.C3105h;
import org.p122a.p123a.p171m.C3108i;
import org.p122a.p123a.p171m.C3222l;
import org.p122a.p123a.p180o.C3234a;
import org.p122a.p123a.p180o.C3235b;

@C2914d
/* renamed from: org.a.a.i.c.s */
public class C3127s implements Closeable, C3036d, C3105h<HttpRoute> {
    private static final String f15443a = "HttpClient";
    private final C3129u f15444b;
    private final C3107c f15445c;
    private final C3121m f15446d;
    private final AtomicBoolean f15447e;

    public C3127s() {
        this(C3127s.m17598h());
    }

    public C3127s(long j, TimeUnit timeUnit) {
        this(C3127s.m17598h(), null, null, null, j, timeUnit);
    }

    public C3127s(C3000f<C3014a> c3000f) {
        this(c3000f, null, null);
    }

    public C3127s(C3000f<C3014a> c3000f, C3017b c3017b) {
        this(c3000f, null, c3017b);
    }

    public C3127s(C3000f<C3014a> c3000f, C3037e<HttpRoute, C3040h> c3037e) {
        this(c3000f, c3037e, null);
    }

    public C3127s(C3000f<C3014a> c3000f, C3037e<HttpRoute, C3040h> c3037e, C3017b c3017b) {
        this(c3000f, c3037e, null, c3017b, -1, TimeUnit.MILLISECONDS);
    }

    public C3127s(C3000f<C3014a> c3000f, C3037e<HttpRoute, C3040h> c3037e, C3041i c3041i, C3017b c3017b, long j, TimeUnit timeUnit) {
        this.f15444b = new C3129u();
        this.f15445c = new C3107c(new C3131v(this.f15444b, c3037e), 2, 20, j, timeUnit);
        this.f15446d = new C3121m(c3000f, c3041i, c3017b);
        this.f15447e = new AtomicBoolean(false);
    }

    public C3127s(C3037e<HttpRoute, C3040h> c3037e) {
        this(C3127s.m17598h(), c3037e, null);
    }

    C3127s(C3107c c3107c, C2997c<C3014a> c2997c, C3041i c3041i, C3017b c3017b) {
        this.f15444b = new C3129u();
        this.f15445c = c3107c;
        this.f15446d = new C3121m(c2997c, c3041i, c3017b);
        this.f15447e = new AtomicBoolean(false);
    }

    private String m17595a(C3109d c3109d) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[id: ").append(c3109d.m17538g()).append("]");
        stringBuilder.append("[route: ").append(c3109d.m17539h()).append("]");
        Object l = c3109d.m17543l();
        if (l != null) {
            stringBuilder.append("[state: ").append(l).append("]");
        }
        return stringBuilder.toString();
    }

    private String m17596b(HttpRoute httpRoute, Object obj) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[route: ").append(httpRoute).append("]");
        if (obj != null) {
            stringBuilder.append("[state: ").append(obj).append("]");
        }
        return stringBuilder.toString();
    }

    private String m17597c(HttpRoute httpRoute) {
        StringBuilder stringBuilder = new StringBuilder();
        C3222l e = this.f15445c.m17529e();
        C3222l a = this.f15445c.m17512a((Object) httpRoute);
        stringBuilder.append("[total kept alive: ").append(e.m17855c()).append("; ");
        stringBuilder.append("route allocated: ").append(a.m17853a() + a.m17855c());
        stringBuilder.append(" of ").append(a.m17856d()).append("; ");
        stringBuilder.append("total allocated: ").append(e.m17853a() + e.m17855c());
        stringBuilder.append(" of ").append(e.m17856d()).append("]");
        return stringBuilder.toString();
    }

    private static C3000f<C3014a> m17598h() {
        return C3001g.m17034a().m17035a(UriUtil.HTTP_SCHEME, C3016c.m17070a()).m17035a(UriUtil.HTTPS_SCHEME, C3026h.m17102a()).m17036b();
    }

    public int m17599a(HttpRoute httpRoute) {
        return this.f15445c.m17521b((Object) httpRoute);
    }

    public C3002h m17600a(HttpHost httpHost) {
        return this.f15444b.m17630a(httpHost);
    }

    public C3013a m17601a(HttpRoute httpRoute, Object obj) {
        C3234a.m17886a((Object) httpRoute, "HTTP route");
        if (Log.isLoggable(f15443a, 3)) {
            Log.d(f15443a, "Connection request: " + m17596b(httpRoute, obj) + m17597c(httpRoute));
        }
        return new C3128t(this, this.f15445c.m17510a(httpRoute, obj, null));
    }

    public /* synthetic */ C3222l m17602a(Object obj) {
        return m17618b((HttpRoute) obj);
    }

    protected HttpClientConnection m17603a(Future<C3109d> future, long j, TimeUnit timeUnit) {
        try {
            C3109d c3109d = (C3109d) future.get(j, timeUnit);
            if (c3109d == null || future.isCancelled()) {
                throw new InterruptedException();
            }
            C3235b.m17895a(c3109d.m17540i() != null, "Pool entry with no connection");
            if (Log.isLoggable(f15443a, 3)) {
                Log.d(f15443a, "Connection leased: " + m17595a(c3109d) + m17597c((HttpRoute) c3109d.m17539h()));
            }
            return C3110e.m17554a(c3109d);
        } catch (TimeoutException e) {
            throw new ConnectionPoolTimeoutException("Timeout waiting for connection from pool");
        }
    }

    public void m17604a() {
        if (Log.isLoggable(f15443a, 3)) {
            Log.d(f15443a, "Closing expired connections");
        }
        this.f15445c.m17530f();
    }

    public void m17605a(int i) {
        this.f15445c.m17513a(i);
    }

    public void m17606a(long j, TimeUnit timeUnit) {
        if (Log.isLoggable(f15443a, 3)) {
            Log.d(f15443a, "Closing connections idle longer than " + j + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + timeUnit);
        }
        this.f15445c.m17514a(j, timeUnit);
    }

    public void m17608a(C2995a c2995a) {
        this.f15444b.m17631a(c2995a);
    }

    public void m17609a(C3002h c3002h) {
        this.f15444b.m17632a(c3002h);
    }

    public void m17610a(HttpClientConnection httpClientConnection, Object obj, long j, TimeUnit timeUnit) {
        boolean z = true;
        C3234a.m17886a((Object) httpClientConnection, "Managed connection");
        synchronized (httpClientConnection) {
            C3109d b = C3110e.m17555b(httpClientConnection);
            if (b == null) {
                return;
            }
            C3040h c3040h = (C3040h) b.m17540i();
            try {
                if (c3040h.isOpen()) {
                    b.m17534a(obj);
                    if (timeUnit == null) {
                        timeUnit = TimeUnit.MILLISECONDS;
                    }
                    b.m17533a(j, timeUnit);
                    if (Log.isLoggable(f15443a, 3)) {
                        Log.d(f15443a, "Connection " + m17595a(b) + " can be kept alive " + (j > 0 ? "for " + (((double) j) / 1000.0d) + " seconds" : "indefinitely"));
                    }
                }
                C3107c c3107c = this.f15445c;
                if (!(c3040h.isOpen() && b.m17548b())) {
                    z = false;
                }
                c3107c.m17518a((C3108i) b, z);
                if (Log.isLoggable(f15443a, 3)) {
                    Log.d(f15443a, "Connection released: " + m17595a(b) + m17597c((HttpRoute) b.m17539h()));
                }
            } catch (Throwable th) {
                C3107c c3107c2 = this.f15445c;
                boolean z2 = c3040h.isOpen() && b.m17548b();
                c3107c2.m17518a((C3108i) b, z2);
                if (Log.isLoggable(f15443a, 3)) {
                    Log.d(f15443a, "Connection released: " + m17595a(b) + m17597c((HttpRoute) b.m17539h()));
                }
            }
        }
    }

    public void m17611a(HttpClientConnection httpClientConnection, HttpRoute httpRoute, int i, HttpContext httpContext) {
        C3040h c3040h;
        C3234a.m17886a((Object) httpClientConnection, "Managed Connection");
        C3234a.m17886a((Object) httpRoute, "HTTP route");
        synchronized (httpClientConnection) {
            c3040h = (C3040h) C3110e.m17553a(httpClientConnection).m17540i();
        }
        HttpHost proxyHost = httpRoute.getProxyHost() != null ? httpRoute.getProxyHost() : httpRoute.getTargetHost();
        InetSocketAddress inetSocketAddress = httpRoute.getLocalAddress() != null ? new InetSocketAddress(httpRoute.getLocalAddress(), 0) : null;
        C3002h a = this.f15444b.m17630a(proxyHost);
        if (a == null) {
            a = this.f15444b.m17629a();
        }
        if (a == null) {
            a = C3002h.f14978a;
        }
        this.f15446d.m17585a(c3040h, proxyHost, inetSocketAddress, i, a, httpContext);
    }

    public void m17612a(HttpClientConnection httpClientConnection, HttpRoute httpRoute, HttpContext httpContext) {
        C3040h c3040h;
        C3234a.m17886a((Object) httpClientConnection, "Managed Connection");
        C3234a.m17886a((Object) httpRoute, "HTTP route");
        synchronized (httpClientConnection) {
            c3040h = (C3040h) C3110e.m17553a(httpClientConnection).m17540i();
        }
        this.f15446d.m17586a(c3040h, httpRoute.getTargetHost(), httpContext);
    }

    public void m17613a(HttpHost httpHost, C2995a c2995a) {
        this.f15444b.m17633a(httpHost, c2995a);
    }

    public void m17614a(HttpHost httpHost, C3002h c3002h) {
        this.f15444b.m17634a(httpHost, c3002h);
    }

    public void m17615a(HttpRoute httpRoute, int i) {
        this.f15445c.m17515a((Object) httpRoute, i);
    }

    public /* synthetic */ int m17616b(Object obj) {
        return m17599a((HttpRoute) obj);
    }

    public C2995a m17617b(HttpHost httpHost) {
        return this.f15444b.m17636b(httpHost);
    }

    public C3222l m17618b(HttpRoute httpRoute) {
        return this.f15445c.m17512a((Object) httpRoute);
    }

    public void m17619b() {
        if (this.f15447e.compareAndSet(false, true)) {
            if (Log.isLoggable(f15443a, 3)) {
                Log.d(f15443a, "Connection manager is shutting down");
            }
            try {
                this.f15445c.m17523b();
            } catch (Throwable e) {
                Log.d(f15443a, "I/O exception shutting down connection manager", e);
            }
            if (Log.isLoggable(f15443a, 3)) {
                Log.d(f15443a, "Connection manager shut down");
            }
        }
    }

    public void m17620b(int i) {
        this.f15445c.m17524b(i);
    }

    public void m17621b(HttpClientConnection httpClientConnection, HttpRoute httpRoute, HttpContext httpContext) {
        C3234a.m17886a((Object) httpClientConnection, "Managed Connection");
        C3234a.m17886a((Object) httpRoute, "HTTP route");
        synchronized (httpClientConnection) {
            C3110e.m17553a(httpClientConnection).m17546a();
        }
    }

    public int m17622c() {
        return this.f15445c.m17527c();
    }

    public void close() {
        m17619b();
    }

    public int m17623d() {
        return this.f15445c.m17528d();
    }

    public C3222l m17624e() {
        return this.f15445c.m17529e();
    }

    public C3002h m17625f() {
        return this.f15444b.m17629a();
    }

    protected void finalize() {
        try {
            m17619b();
        } finally {
            super.finalize();
        }
    }

    public C2995a m17626g() {
        return this.f15444b.m17635b();
    }
}
