package org.p122a.p123a.p167i.p169b;

import android.util.Log;
import java.net.URI;
import java.util.Locale;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.client.CircularRedirectException;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p152c.C2977e;
import org.p122a.p123a.p152c.p153a.C2925c;
import org.p122a.p123a.p152c.p156c.C2951i;
import org.p122a.p123a.p152c.p156c.C2952j;
import org.p122a.p123a.p152c.p156c.C2961t;
import org.p122a.p123a.p152c.p160e.C2968a;
import org.p122a.p123a.p152c.p161f.C2987j;
import org.p122a.p123a.p152c.p161f.C2988k;
import org.p122a.p123a.p180o.C3234a;
import org.p122a.p123a.p180o.C3235b;
import org.p122a.p123a.p180o.C3239f;

@C2912b
/* renamed from: org.a.a.i.b.m */
public class C3076m implements C2977e {
    @Deprecated
    public static final String f15238a = "http.protocol.redirect-locations";
    public static final C3076m f15239b;
    private static final String f15240c = "HttpClient";
    private static final String[] f15241d;

    static {
        f15239b = new C3076m();
        f15241d = new String[]{C2951i.f14860a, C2952j.f14861a};
    }

    protected URI m17316a(String str) {
        try {
            C2987j c2987j = new C2987j(new URI(str).normalize());
            String h = c2987j.m16962h();
            if (h != null) {
                c2987j.m16952c(h.toLowerCase(Locale.ENGLISH));
            }
            if (C3239f.m17910a(c2987j.m16964j())) {
                c2987j.m16954d("/");
            }
            return c2987j.m16941a();
        } catch (Throwable e) {
            throw new ProtocolException("Invalid redirect URI: " + str, e);
        }
    }

    public boolean m17317a(HttpRequest httpRequest, HttpResponse httpResponse, HttpContext httpContext) {
        C3234a.m17886a((Object) httpRequest, "HTTP request");
        C3234a.m17886a((Object) httpResponse, "HTTP response");
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        String method = httpRequest.getRequestLine().getMethod();
        Header firstHeader = httpResponse.getFirstHeader("location");
        switch (statusCode) {
            case 301:
            case 307:
                return m17319b(method);
            case 302:
                return m17319b(method) && firstHeader != null;
            case 303:
                return true;
            default:
                return false;
        }
    }

    public HttpUriRequest m17318b(HttpRequest httpRequest, HttpResponse httpResponse, HttpContext httpContext) {
        URI c = m17320c(httpRequest, httpResponse, httpContext);
        String method = httpRequest.getRequestLine().getMethod();
        return method.equalsIgnoreCase(C2952j.f14861a) ? new C2952j(c) : method.equalsIgnoreCase(C2951i.f14860a) ? new C2951i(c) : httpResponse.getStatusLine().getStatusCode() == 307 ? C2961t.m16836a(httpRequest).m16845a(c).m16867n() : new C2951i(c);
    }

    protected boolean m17319b(String str) {
        for (String equalsIgnoreCase : f15241d) {
            if (equalsIgnoreCase.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    public URI m17320c(HttpRequest httpRequest, HttpResponse httpResponse, HttpContext httpContext) {
        C3234a.m17886a((Object) httpRequest, "HTTP request");
        C3234a.m17886a((Object) httpResponse, "HTTP response");
        C3234a.m17886a((Object) httpContext, "HTTP context");
        C2968a a = C2968a.m16884a(httpContext);
        Header firstHeader = httpResponse.getFirstHeader("location");
        if (firstHeader == null) {
            throw new ProtocolException("Received redirect response " + httpResponse.getStatusLine() + " but no location header");
        }
        String value = firstHeader.getValue();
        if (Log.isLoggable(f15240c, 3)) {
            Log.d(f15240c, "Redirect requested to location '" + value + "'");
        }
        C2925c n = a.m16906n();
        URI a2 = m17316a(value);
        try {
            URI uri;
            if (a2.isAbsolute()) {
                uri = a2;
            } else if (n.m16747g()) {
                HttpHost t = a.m16882t();
                C3235b.m17894a((Object) t, "Target host");
                uri = C2988k.m16970a(C2988k.m16973a(new URI(httpRequest.getRequestLine().getUri()), t, false), a2);
            } else {
                throw new ProtocolException("Relative redirect location '" + a2 + "' not allowed");
            }
            ah ahVar = (ah) a.getAttribute(f15238a);
            if (ahVar == null) {
                ahVar = new ah();
                httpContext.setAttribute(f15238a, ahVar);
            }
            if (n.m16748h() || !ahVar.m17350a(uri)) {
                ahVar.m17352b(uri);
                return uri;
            }
            throw new CircularRedirectException("Circular redirect to '" + uri + "'");
        } catch (Throwable e) {
            throw new ProtocolException(e.getMessage(), e);
        }
    }
}
