package org.p122a.p123a.p152c.p160e;

import android.util.Log;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.c.e.i */
public class C2976i implements HttpResponseInterceptor {
    private static final String f14906a = "HttpClient";

    private static String m16908a(Cookie cookie) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(cookie.getName());
        stringBuilder.append("=\"");
        String value = cookie.getValue();
        if (value.length() > 100) {
            value = value.substring(0, 100) + "...";
        }
        stringBuilder.append(value);
        stringBuilder.append("\"");
        stringBuilder.append(", version:");
        stringBuilder.append(Integer.toString(cookie.getVersion()));
        stringBuilder.append(", domain:");
        stringBuilder.append(cookie.getDomain());
        stringBuilder.append(", path:");
        stringBuilder.append(cookie.getPath());
        stringBuilder.append(", expiry:");
        stringBuilder.append(cookie.getExpiryDate());
        return stringBuilder.toString();
    }

    private void m16909a(HeaderIterator headerIterator, CookieSpec cookieSpec, CookieOrigin cookieOrigin, CookieStore cookieStore) {
        while (headerIterator.hasNext()) {
            Header nextHeader = headerIterator.nextHeader();
            try {
                for (Cookie cookie : cookieSpec.parse(nextHeader, cookieOrigin)) {
                    try {
                        cookieSpec.validate(cookie, cookieOrigin);
                        cookieStore.addCookie(cookie);
                        if (Log.isLoggable(f14906a, 3)) {
                            Log.d(f14906a, "Cookie accepted [" + C2976i.m16908a(cookie) + "]");
                        }
                    } catch (MalformedCookieException e) {
                        if (Log.isLoggable(f14906a, 5)) {
                            Log.w(f14906a, "Cookie rejected [" + C2976i.m16908a(cookie) + "] " + e.getMessage());
                        }
                    }
                }
            } catch (MalformedCookieException e2) {
                if (Log.isLoggable(f14906a, 5)) {
                    Log.w(f14906a, "Invalid cookie header: \"" + nextHeader + "\". " + e2.getMessage());
                }
            }
        }
    }

    public void process(HttpResponse httpResponse, HttpContext httpContext) {
        C3234a.m17886a((Object) httpResponse, "HTTP request");
        C3234a.m17886a((Object) httpContext, "HTTP context");
        C2968a a = C2968a.m16884a(httpContext);
        CookieSpec e = a.m16897e();
        if (e != null) {
            CookieStore d = a.m16896d();
            if (d != null) {
                CookieOrigin f = a.m16898f();
                if (f != null) {
                    m16909a(httpResponse.headerIterator("Set-Cookie"), e, f, d);
                    if (e.getVersion() > 0) {
                        m16909a(httpResponse.headerIterator("Set-Cookie2"), e, f, d);
                    }
                } else if (Log.isLoggable(f14906a, 3)) {
                    Log.d(f14906a, "Cookie origin not specified in HTTP context");
                }
            } else if (Log.isLoggable(f14906a, 3)) {
                Log.d(f14906a, "Cookie store not specified in HTTP context");
            }
        } else if (Log.isLoggable(f14906a, 3)) {
            Log.d(f14906a, "Cookie spec not specified in HTTP context");
        }
    }
}
