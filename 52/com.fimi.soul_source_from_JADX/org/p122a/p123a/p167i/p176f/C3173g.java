package org.p122a.p123a.p167i.p176f;

import android.util.Log;
import java.io.IOException;
import java.net.URI;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.ProtocolException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.protocol.HttpProcessor;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p152c.p156c.C2941h;
import org.p122a.p123a.p152c.p156c.C2946d;
import org.p122a.p123a.p152c.p156c.C2957p;
import org.p122a.p123a.p152c.p160e.C2968a;
import org.p122a.p123a.p152c.p161f.C2988k;
import org.p122a.p123a.p159n.C2967c;
import org.p122a.p123a.p167i.p169b.C3083e;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.i.f.g */
public class C3173g implements C3167b {
    private static final String f15551a = "HttpClient";
    private final C3167b f15552b;
    private final HttpProcessor f15553c;

    public C3173g(C3167b c3167b, HttpProcessor httpProcessor) {
        C3234a.m17886a((Object) c3167b, "HTTP client request executor");
        C3234a.m17886a((Object) httpProcessor, "HTTP protocol processor");
        this.f15552b = c3167b;
        this.f15553c = httpProcessor;
    }

    public C2946d m17714a(HttpRoute httpRoute, C2957p c2957p, C2968a c2968a, C2941h c2941h) {
        URI uri;
        C3234a.m17886a((Object) httpRoute, "HTTP route");
        C3234a.m17886a((Object) c2957p, "HTTP request");
        C3234a.m17886a((Object) c2968a, "HTTP context");
        HttpRequest a = c2957p.m16831a();
        if (a instanceof HttpUriRequest) {
            uri = ((HttpUriRequest) a).getURI();
        } else {
            String uri2 = a.getRequestLine().getUri();
            try {
                uri = URI.create(uri2);
            } catch (Throwable e) {
                if (Log.isLoggable(f15551a, 3)) {
                    Log.d(f15551a, "Unable to parse '" + uri2 + "' as a valid URI; " + "request URI and Host header may be inconsistent", e);
                }
                uri = null;
            }
        }
        c2957p.m16832a(uri);
        m17715a(c2957p, httpRoute);
        HttpHost httpHost = (HttpHost) c2957p.getParams().getParameter("http.virtual-host");
        if (httpHost != null && httpHost.getPort() == -1) {
            int port = httpRoute.getTargetHost().getPort();
            if (port != -1) {
                httpHost = new HttpHost(httpHost.getHostName(), port, httpHost.getSchemeName());
            }
            if (Log.isLoggable(f15551a, 3)) {
                Log.d(f15551a, "Using virtual host" + httpHost);
            }
        }
        if (httpHost == null) {
            httpHost = (uri == null || !uri.isAbsolute() || uri.getHost() == null) ? null : new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());
        }
        HttpHost targetHost = httpHost == null ? httpRoute.getTargetHost() : httpHost;
        if (uri != null) {
            String userInfo = uri.getUserInfo();
            if (userInfo != null) {
                CredentialsProvider i = c2968a.m16901i();
                if (i == null) {
                    i = new C3083e();
                    c2968a.m16892a(i);
                }
                i.setCredentials(new AuthScope(targetHost.getHostName(), targetHost.getPort()), new UsernamePasswordCredentials(userInfo));
            }
        }
        c2968a.setAttribute(C2967c.f14884q, targetHost);
        c2968a.setAttribute(C2968a.f14887a, httpRoute);
        c2968a.setAttribute(C2967c.f14882o, c2957p);
        this.f15553c.process(c2957p, c2968a);
        C2946d a2 = this.f15552b.m17698a(httpRoute, c2957p, c2968a, c2941h);
        try {
            c2968a.setAttribute(C2967c.f14883p, a2);
            this.f15553c.process(a2, c2968a);
            return a2;
        } catch (RuntimeException e2) {
            a2.close();
            throw e2;
        } catch (IOException e3) {
            a2.close();
            throw e3;
        } catch (HttpException e4) {
            a2.close();
            throw e4;
        }
    }

    void m17715a(C2957p c2957p, HttpRoute httpRoute) {
        try {
            URI uri = c2957p.getURI();
            if (uri != null) {
                uri = (httpRoute.getProxyHost() == null || httpRoute.isTunnelled()) ? uri.isAbsolute() ? C2988k.m16973a(uri, null, true) : C2988k.m16968a(uri) : !uri.isAbsolute() ? C2988k.m16973a(uri, httpRoute.getTargetHost(), true) : C2988k.m16968a(uri);
                c2957p.m16832a(uri);
            }
        } catch (Throwable e) {
            throw new ProtocolException("Invalid URI: " + c2957p.getRequestLine().getUri(), e);
        }
    }
}
