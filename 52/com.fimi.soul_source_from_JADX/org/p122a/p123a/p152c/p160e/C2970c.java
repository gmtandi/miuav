package org.p122a.p123a.p152c.p160e;

import android.util.Log;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.routing.RouteInfo;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.SetCookie2;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p152c.p153a.C2924b;
import org.p122a.p123a.p162e.C2997c;
import org.p122a.p123a.p166g.C3045b;
import org.p122a.p123a.p180o.C3234a;
import org.p122a.p123a.p180o.C3239f;

@C2912b
/* renamed from: org.a.a.c.e.c */
public class C2970c implements HttpRequestInterceptor {
    private static final String f14900a = "HttpClient";

    public void process(HttpRequest httpRequest, HttpContext httpContext) {
        Object obj = null;
        C3234a.m17886a((Object) httpRequest, "HTTP request");
        C3234a.m17886a((Object) httpContext, "HTTP context");
        if (!httpRequest.getRequestLine().getMethod().equalsIgnoreCase("CONNECT")) {
            HttpContext a = C2968a.m16884a(httpContext);
            CookieStore d = a.m16896d();
            if (d != null) {
                C2997c g = a.m16899g();
                if (g != null) {
                    HttpHost t = a.m16882t();
                    if (t != null) {
                        RouteInfo b = a.m16893b();
                        if (b != null) {
                            URI uri;
                            String e = a.m16906n().m16745e();
                            String str = e == null ? C2924b.f14792d : e;
                            if (Log.isLoggable(f14900a, 3)) {
                                Log.d(f14900a, "CookieSpec selected: " + str);
                            }
                            if (httpRequest instanceof HttpUriRequest) {
                                uri = ((HttpUriRequest) httpRequest).getURI();
                            } else {
                                try {
                                    uri = new URI(httpRequest.getRequestLine().getUri());
                                } catch (URISyntaxException e2) {
                                    uri = null;
                                }
                            }
                            e = uri != null ? uri.getPath() : null;
                            String hostName = t.getHostName();
                            int port = t.getPort();
                            if (port < 0) {
                                port = b.getTargetHost().getPort();
                            }
                            if (port < 0) {
                                port = 0;
                            }
                            if (C3239f.m17910a(e)) {
                                e = "/";
                            }
                            CookieOrigin cookieOrigin = new CookieOrigin(hostName, port, e, b.isSecure());
                            C3045b c3045b = (C3045b) g.m17023a(str);
                            if (c3045b == null) {
                                throw new HttpException("Unsupported cookie policy: " + str);
                            }
                            Header versionHeader;
                            CookieSpec a2 = c3045b.m17153a(a);
                            List<Cookie> arrayList = new ArrayList(d.getCookies());
                            List<Cookie> arrayList2 = new ArrayList();
                            Date date = new Date();
                            for (Cookie cookie : arrayList) {
                                if (cookie.isExpired(date)) {
                                    if (Log.isLoggable(f14900a, 3)) {
                                        Log.d(f14900a, "Cookie " + cookie + " expired");
                                    }
                                } else if (a2.match(cookie, cookieOrigin)) {
                                    if (Log.isLoggable(f14900a, 3)) {
                                        Log.d(f14900a, "Cookie " + cookie + " match " + cookieOrigin);
                                    }
                                    arrayList2.add(cookie);
                                }
                            }
                            if (!arrayList2.isEmpty()) {
                                for (Header versionHeader2 : a2.formatCookies(arrayList2)) {
                                    httpRequest.addHeader(versionHeader2);
                                }
                            }
                            int version = a2.getVersion();
                            if (version > 0) {
                                for (Cookie cookie2 : arrayList2) {
                                    if (version != cookie2.getVersion() || !(cookie2 instanceof SetCookie2)) {
                                        obj = 1;
                                    }
                                }
                                if (obj != null) {
                                    versionHeader2 = a2.getVersionHeader();
                                    if (versionHeader2 != null) {
                                        httpRequest.addHeader(versionHeader2);
                                    }
                                }
                            }
                            httpContext.setAttribute(C2968a.f14890d, a2);
                            httpContext.setAttribute(C2968a.f14891e, cookieOrigin);
                        } else if (Log.isLoggable(f14900a, 3)) {
                            Log.d(f14900a, "Connection route not set in the context");
                        }
                    } else if (Log.isLoggable(f14900a, 3)) {
                        Log.d(f14900a, "Target host not set in the context");
                    }
                } else if (Log.isLoggable(f14900a, 3)) {
                    Log.d(f14900a, "CookieSpec registry not specified in HTTP context");
                }
            } else if (Log.isLoggable(f14900a, 3)) {
                Log.d(f14900a, "Cookie store not specified in HTTP context");
            }
        }
    }
}
