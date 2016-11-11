package org.p122a.p123a.p167i.p170c;

import android.util.Log;
import com.facebook.common.util.UriUtil;
import com.tencent.mm.sdk.platformtools.MAlarmHandler;
import java.io.Closeable;
import java.net.InetSocketAddress;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.http.HttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.LangUtils;
import org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p124f.C3013a;
import org.p122a.p123a.p124f.C3017b;
import org.p122a.p123a.p124f.C3036d;
import org.p122a.p123a.p124f.C3037e;
import org.p122a.p123a.p124f.C3040h;
import org.p122a.p123a.p124f.C3041i;
import org.p122a.p123a.p124f.p125c.C3026h;
import org.p122a.p123a.p124f.p164b.C3014a;
import org.p122a.p123a.p124f.p164b.C3016c;
import org.p122a.p123a.p150a.C2911a;
import org.p122a.p123a.p150a.C2914d;
import org.p122a.p123a.p162e.C2995a;
import org.p122a.p123a.p162e.C2997c;
import org.p122a.p123a.p162e.C3000f;
import org.p122a.p123a.p162e.C3001g;
import org.p122a.p123a.p162e.C3002h;
import org.p122a.p123a.p180o.C3234a;
import org.p122a.p123a.p180o.C3235b;

@C2914d
/* renamed from: org.a.a.i.c.a */
public class C3102a implements Closeable, C3036d {
    private static final String f15364a = "HttpClient";
    private final C3121m f15365b;
    private final C3037e<HttpRoute, C3040h> f15366c;
    @C2911a(a = "this")
    private C3040h f15367d;
    @C2911a(a = "this")
    private HttpRoute f15368e;
    @C2911a(a = "this")
    private Object f15369f;
    @C2911a(a = "this")
    private long f15370g;
    @C2911a(a = "this")
    private long f15371h;
    @C2911a(a = "this")
    private boolean f15372i;
    @C2911a(a = "this")
    private C3002h f15373j;
    @C2911a(a = "this")
    private C2995a f15374k;
    private final AtomicBoolean f15375l;

    public C3102a() {
        this(C3102a.m17474g(), null, null, null);
    }

    public C3102a(C2997c<C3014a> c2997c) {
        this(c2997c, null, null, null);
    }

    public C3102a(C2997c<C3014a> c2997c, C3037e<HttpRoute, C3040h> c3037e) {
        this(c2997c, c3037e, null, null);
    }

    public C3102a(C2997c<C3014a> c2997c, C3037e<HttpRoute, C3040h> c3037e, C3041i c3041i, C3017b c3017b) {
        C3037e c3037e2;
        this.f15365b = new C3121m(c2997c, c3041i, c3017b);
        if (c3037e == null) {
            c3037e2 = C3126r.f15439a;
        }
        this.f15366c = c3037e2;
        this.f15371h = MAlarmHandler.NEXT_FIRE_INTERVAL;
        this.f15373j = C3002h.f14978a;
        this.f15374k = C2995a.f14958a;
        this.f15375l = new AtomicBoolean(false);
    }

    private static C3000f<C3014a> m17474g() {
        return C3001g.m17034a().m17035a(UriUtil.HTTP_SCHEME, C3016c.m17070a()).m17035a(UriUtil.HTTPS_SCHEME, C3026h.m17102a()).m17036b();
    }

    private void m17475h() {
        if (this.f15367d != null) {
            if (Log.isLoggable(f15364a, 3)) {
                Log.d(f15364a, "Closing connection");
            }
            try {
                this.f15367d.close();
            } catch (Throwable e) {
                if (Log.isLoggable(f15364a, 3)) {
                    Log.d(f15364a, "I/O exception closing connection", e);
                }
            }
            this.f15367d = null;
        }
    }

    private void m17476i() {
        if (this.f15367d != null) {
            if (Log.isLoggable(f15364a, 3)) {
                Log.d(f15364a, "Shutting down connection");
            }
            try {
                this.f15367d.shutdown();
            } catch (Throwable e) {
                if (Log.isLoggable(f15364a, 3)) {
                    Log.d(f15364a, "I/O exception shutting down connection", e);
                }
            }
            this.f15367d = null;
        }
    }

    private void m17477j() {
        if (this.f15367d != null && System.currentTimeMillis() >= this.f15371h) {
            if (Log.isLoggable(f15364a, 3)) {
                Log.d(f15364a, "Connection expired @ " + new Date(this.f15371h));
            }
            m17475h();
        }
    }

    public final C3013a m17478a(HttpRoute httpRoute, Object obj) {
        C3234a.m17886a((Object) httpRoute, "Route");
        return new C3103b(this, httpRoute, obj);
    }

    public synchronized void m17479a() {
        if (!this.f15375l.get()) {
            if (!this.f15372i) {
                m17477j();
            }
        }
    }

    public synchronized void m17480a(long j, TimeUnit timeUnit) {
        long j2 = 0;
        synchronized (this) {
            C3234a.m17886a((Object) timeUnit, "Time unit");
            if (!this.f15375l.get()) {
                if (!this.f15372i) {
                    long toMillis = timeUnit.toMillis(j);
                    if (toMillis >= 0) {
                        j2 = toMillis;
                    }
                    if (this.f15370g <= System.currentTimeMillis() - j2) {
                        m17475h();
                    }
                }
            }
        }
    }

    public synchronized void m17481a(C2995a c2995a) {
        if (c2995a == null) {
            c2995a = C2995a.f14958a;
        }
        this.f15374k = c2995a;
    }

    public synchronized void m17482a(C3002h c3002h) {
        if (c3002h == null) {
            c3002h = C3002h.f14978a;
        }
        this.f15373j = c3002h;
    }

    public synchronized void m17483a(HttpClientConnection httpClientConnection, Object obj, long j, TimeUnit timeUnit) {
        boolean z = false;
        synchronized (this) {
            C3234a.m17886a((Object) httpClientConnection, C3004e.f15024j);
            if (httpClientConnection == this.f15367d) {
                z = true;
            }
            C3235b.m17895a(z, "Connection not obtained from this manager");
            if (Log.isLoggable(f15364a, 3)) {
                Log.d(f15364a, "Releasing connection " + httpClientConnection);
            }
            if (!this.f15375l.get()) {
                try {
                    this.f15370g = System.currentTimeMillis();
                    C3040h isOpen = this.f15367d.isOpen();
                    if (isOpen == null) {
                        this.f15367d = isOpen;
                        this.f15368e = null;
                        this.f15367d = null;
                        this.f15371h = MAlarmHandler.NEXT_FIRE_INTERVAL;
                        this.f15372i = false;
                    } else {
                        this.f15369f = obj;
                        if (Log.isLoggable(f15364a, 3)) {
                            Log.d(f15364a, "Connection can be kept alive " + (j > 0 ? "for " + j + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + timeUnit : "indefinitely"));
                        }
                        if (j > 0) {
                            this.f15371h = this.f15370g + timeUnit.toMillis(j);
                        } else {
                            this.f15371h = MAlarmHandler.NEXT_FIRE_INTERVAL;
                        }
                        this.f15372i = false;
                    }
                } finally {
                    this.f15372i = false;
                }
            }
        }
    }

    public void m17484a(HttpClientConnection httpClientConnection, HttpRoute httpRoute, int i, HttpContext httpContext) {
        C3234a.m17886a((Object) httpClientConnection, C3004e.f15024j);
        C3234a.m17886a((Object) httpRoute, "HTTP route");
        C3235b.m17895a(httpClientConnection == this.f15367d, "Connection not obtained from this manager");
        this.f15365b.m17585a(this.f15367d, httpRoute.getProxyHost() != null ? httpRoute.getProxyHost() : httpRoute.getTargetHost(), httpRoute.getLocalAddress() != null ? new InetSocketAddress(httpRoute.getLocalAddress(), 0) : null, i, this.f15373j, httpContext);
    }

    public void m17485a(HttpClientConnection httpClientConnection, HttpRoute httpRoute, HttpContext httpContext) {
        C3234a.m17886a((Object) httpClientConnection, C3004e.f15024j);
        C3234a.m17886a((Object) httpRoute, "HTTP route");
        C3235b.m17895a(httpClientConnection == this.f15367d, "Connection not obtained from this manager");
        this.f15365b.m17586a(this.f15367d, httpRoute.getTargetHost(), httpContext);
    }

    synchronized HttpClientConnection m17486b(HttpRoute httpRoute, Object obj) {
        HttpClientConnection httpClientConnection;
        boolean z = true;
        synchronized (this) {
            C3235b.m17895a(!this.f15375l.get(), "Connection manager has been shut down");
            if (Log.isLoggable(f15364a, 3)) {
                Log.d(f15364a, "Get connection for route " + httpRoute);
            }
            if (this.f15372i) {
                z = false;
            }
            C3235b.m17895a(z, "Connection is still allocated");
            if (!(LangUtils.equals(this.f15368e, httpRoute) && LangUtils.equals(this.f15369f, obj))) {
                m17475h();
            }
            this.f15368e = httpRoute;
            this.f15369f = obj;
            m17477j();
            if (this.f15367d == null) {
                this.f15367d = (C3040h) this.f15366c.m17143a(httpRoute, this.f15374k);
            }
            this.f15372i = true;
            httpClientConnection = this.f15367d;
        }
        return httpClientConnection;
    }

    public synchronized void m17487b() {
        if (this.f15375l.compareAndSet(false, true)) {
            m17476i();
        }
    }

    public void m17488b(HttpClientConnection httpClientConnection, HttpRoute httpRoute, HttpContext httpContext) {
    }

    HttpRoute m17489c() {
        return this.f15368e;
    }

    public void close() {
        m17487b();
    }

    Object m17490d() {
        return this.f15369f;
    }

    public synchronized C3002h m17491e() {
        return this.f15373j;
    }

    public synchronized C2995a m17492f() {
        return this.f15374k;
    }

    protected void finalize() {
        try {
            m17487b();
        } finally {
            super.finalize();
        }
    }
}
