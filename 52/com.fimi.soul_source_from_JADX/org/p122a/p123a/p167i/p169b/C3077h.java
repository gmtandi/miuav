package org.p122a.p123a.p167i.p169b;

import android.util.Log;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;
import java.net.URI;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.p150a.C2914d;
import org.p122a.p123a.p152c.p156c.C2946d;
import org.p122a.p123a.p152c.p161f.C2988k;
import org.p122a.p123a.p180o.C3234a;
import org.p122a.p123a.p180o.C3237d;

@C2914d
/* renamed from: org.a.a.i.b.h */
public abstract class C3077h implements Closeable, HttpClient {
    private static final String f15243a = "HttpClient";

    private static HttpHost m17322b(HttpUriRequest httpUriRequest) {
        HttpHost httpHost = null;
        URI uri = httpUriRequest.getURI();
        if (uri.isAbsolute()) {
            httpHost = C2988k.m16975b(uri);
            if (httpHost == null) {
                throw new ClientProtocolException("URI does not specify a valid host name: " + uri);
            }
        }
        return httpHost;
    }

    public C2946d m17323a(HttpHost httpHost, HttpRequest httpRequest) {
        return m17324a(httpHost, httpRequest, (HttpContext) null);
    }

    protected abstract C2946d m17324a(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext);

    public C2946d m17325a(HttpUriRequest httpUriRequest) {
        return m17326a(httpUriRequest, (HttpContext) null);
    }

    public C2946d m17326a(HttpUriRequest httpUriRequest, HttpContext httpContext) {
        C3234a.m17886a((Object) httpUriRequest, "HTTP request");
        return m17324a(C3077h.m17322b(httpUriRequest), httpUriRequest, httpContext);
    }

    public C2946d m17327b(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) {
        return m17324a(httpHost, httpRequest, httpContext);
    }

    public <T> T execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler) {
        return execute(httpHost, httpRequest, responseHandler, null);
    }

    public <T> T execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) {
        C3234a.m17886a((Object) responseHandler, "Response handler");
        HttpResponse b = m17327b(httpHost, httpRequest, httpContext);
        try {
            T handleResponse = responseHandler.handleResponse(b);
            C3237d.m17904b(b.getEntity());
            return handleResponse;
        } catch (Throwable e) {
            try {
                C3237d.m17904b(b.getEntity());
            } catch (Throwable e2) {
                if (Log.isLoggable(f15243a, 5)) {
                    Log.w(f15243a, "Error consuming content after an exception.", e2);
                }
            }
            if (e instanceof RuntimeException) {
                throw ((RuntimeException) e);
            } else if (e instanceof IOException) {
                throw ((IOException) e);
            } else {
                throw new UndeclaredThrowableException(e);
            }
        }
    }

    public <T> T execute(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler) {
        return execute(httpUriRequest, (ResponseHandler) responseHandler, null);
    }

    public <T> T execute(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) {
        return execute(C3077h.m17322b(httpUriRequest), httpUriRequest, responseHandler, httpContext);
    }

    public /* synthetic */ HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest) {
        return m17323a(httpHost, httpRequest);
    }

    public /* synthetic */ HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) {
        return m17327b(httpHost, httpRequest, httpContext);
    }

    public /* synthetic */ HttpResponse execute(HttpUriRequest httpUriRequest) {
        return m17325a(httpUriRequest);
    }

    public /* synthetic */ HttpResponse execute(HttpUriRequest httpUriRequest, HttpContext httpContext) {
        return m17326a(httpUriRequest, httpContext);
    }
}
