package org.p122a.p123a.p167i.p176f;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.URI;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestExecutor;
import org.p122a.p123a.p124f.C3036d;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p152c.p153a.C2925c;
import org.p122a.p123a.p152c.p156c.C2941h;
import org.p122a.p123a.p152c.p156c.C2946d;
import org.p122a.p123a.p152c.p156c.C2957p;
import org.p122a.p123a.p152c.p160e.C2968a;
import org.p122a.p123a.p152c.p160e.C2972e;
import org.p122a.p123a.p152c.p161f.C2988k;
import org.p122a.p123a.p159n.C2967c;
import org.p122a.p123a.p159n.C3227f;
import org.p122a.p123a.p159n.C3229h;
import org.p122a.p123a.p159n.C3231j;
import org.p122a.p123a.p159n.C3232k;
import org.p122a.p123a.p180o.C3234a;
import org.p122a.p123a.p180o.C3240g;

@C2912b
/* renamed from: org.a.a.i.f.f */
public class C3172f implements C3167b {
    private final HttpRequestExecutor f15546a;
    private final C3036d f15547b;
    private final ConnectionReuseStrategy f15548c;
    private final ConnectionKeepAliveStrategy f15549d;
    private final HttpProcessor f15550e;

    public C3172f(HttpRequestExecutor httpRequestExecutor, C3036d c3036d, ConnectionReuseStrategy connectionReuseStrategy, ConnectionKeepAliveStrategy connectionKeepAliveStrategy) {
        C3234a.m17886a((Object) httpRequestExecutor, "HTTP request executor");
        C3234a.m17886a((Object) c3036d, "Client connection manager");
        C3234a.m17886a((Object) connectionReuseStrategy, "Connection reuse strategy");
        C3234a.m17886a((Object) connectionKeepAliveStrategy, "Connection keep alive strategy");
        this.f15550e = new C3227f(new C3229h(), new C3231j(), new C2972e(), new C3232k(C3240g.m17912a("Apache-HttpClient", "org.apache.http.client", getClass())));
        this.f15546a = httpRequestExecutor;
        this.f15547b = c3036d;
        this.f15548c = connectionReuseStrategy;
        this.f15549d = connectionKeepAliveStrategy;
    }

    static void m17712a(C2957p c2957p, HttpRoute httpRoute) {
        try {
            URI uri = c2957p.getURI();
            if (uri != null) {
                c2957p.m16832a(uri.isAbsolute() ? C2988k.m16973a(uri, null, true) : C2988k.m16968a(uri));
            }
        } catch (Throwable e) {
            throw new ProtocolException("Invalid URI: " + c2957p.getRequestLine().getUri(), e);
        }
    }

    public C2946d m17713a(HttpRoute httpRoute, C2957p c2957p, C2968a c2968a, C2941h c2941h) {
        Throwable e;
        C3234a.m17886a((Object) httpRoute, "HTTP route");
        C3234a.m17886a((Object) c2957p, "HTTP request");
        C3234a.m17886a((Object) c2968a, "HTTP context");
        C3172f.m17712a(c2957p, httpRoute);
        Object a = this.f15547b.m17135a(httpRoute, null);
        if (c2941h != null) {
            if (c2941h.isAborted()) {
                a.m16821a();
                throw new C3175i("Request aborted");
            }
            c2941h.m16817a(a);
        }
        C2925c n = c2968a.m16906n();
        try {
            int n2;
            HttpResponse execute;
            HttpEntity entity;
            int m = n.m16753m();
            HttpClientConnection a2 = a.m17066a(m > 0 ? (long) m : 0, TimeUnit.MILLISECONDS);
            C3169c c3169c = new C3169c(this.f15547b, a2);
            if (c2941h != null) {
                try {
                    if (c2941h.isAborted()) {
                        c3169c.close();
                        throw new C3175i("Request aborted");
                    }
                    c2941h.m16817a(c3169c);
                } catch (Throwable e2) {
                    InterruptedIOException interruptedIOException = new InterruptedIOException("Connection has been shut down");
                    interruptedIOException.initCause(e2);
                    throw interruptedIOException;
                } catch (HttpException e3) {
                    c3169c.abortConnection();
                    throw e3;
                } catch (IOException e4) {
                    c3169c.abortConnection();
                    throw e4;
                } catch (RuntimeException e5) {
                    c3169c.abortConnection();
                    throw e5;
                }
            }
            if (!a2.isOpen()) {
                n2 = n.m16754n();
                C3036d c3036d = this.f15547b;
                if (n2 <= 0) {
                    n2 = 0;
                }
                c3036d.m17139a(a2, httpRoute, n2, (HttpContext) c2968a);
                this.f15547b.m17142b(a2, httpRoute, c2968a);
            }
            n2 = n.m16755o();
            if (n2 >= 0) {
                a2.setSocketTimeout(n2);
            }
            HttpRequest a3 = c2957p.m16831a();
            if (a3 instanceof HttpUriRequest) {
                URI uri = ((HttpUriRequest) a3).getURI();
                if (uri.isAbsolute()) {
                    a = new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());
                    if (a == null) {
                        a = httpRoute.getTargetHost();
                    }
                    c2968a.setAttribute(C2967c.f14884q, a);
                    c2968a.setAttribute(C2967c.f14882o, c2957p);
                    c2968a.setAttribute(C2967c.f14881n, a2);
                    c2968a.setAttribute(C2968a.f14887a, httpRoute);
                    this.f15550e.process(c2957p, c2968a);
                    execute = this.f15546a.execute(c2957p, a2, c2968a);
                    this.f15550e.process(execute, c2968a);
                    if (this.f15548c.keepAlive(execute, c2968a)) {
                        c3169c.m17705d();
                    } else {
                        c3169c.m17700a(this.f15549d.getKeepAliveDuration(execute, c2968a), TimeUnit.MILLISECONDS);
                        c3169c.m17704c();
                    }
                    entity = execute.getEntity();
                    if (entity == null && entity.isStreaming()) {
                        return new C3170d(execute, c3169c);
                    }
                    c3169c.releaseConnection();
                    return new C3170d(execute, null);
                }
            }
            a = null;
            if (a == null) {
                a = httpRoute.getTargetHost();
            }
            c2968a.setAttribute(C2967c.f14884q, a);
            c2968a.setAttribute(C2967c.f14882o, c2957p);
            c2968a.setAttribute(C2967c.f14881n, a2);
            c2968a.setAttribute(C2968a.f14887a, httpRoute);
            this.f15550e.process(c2957p, c2968a);
            execute = this.f15546a.execute(c2957p, a2, c2968a);
            this.f15550e.process(execute, c2968a);
            if (this.f15548c.keepAlive(execute, c2968a)) {
                c3169c.m17705d();
            } else {
                c3169c.m17700a(this.f15549d.getKeepAliveDuration(execute, c2968a), TimeUnit.MILLISECONDS);
                c3169c.m17704c();
            }
            entity = execute.getEntity();
            if (entity == null) {
            }
            c3169c.releaseConnection();
            return new C3170d(execute, null);
        } catch (Throwable e22) {
            Thread.currentThread().interrupt();
            throw new C3175i("Request aborted", e22);
        } catch (ExecutionException e6) {
            e22 = e6;
            Throwable cause = e22.getCause();
            if (cause != null) {
                e22 = cause;
            }
            throw new C3175i("Request execution failed", e22);
        }
    }
}
