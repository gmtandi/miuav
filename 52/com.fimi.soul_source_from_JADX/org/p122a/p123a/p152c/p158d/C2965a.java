package org.p122a.p123a.p152c.p158d;

import java.lang.reflect.Field;
import java.net.InetAddress;
import java.util.Map;
import org.apache.http.HttpHost;
import org.apache.http.params.HttpParams;
import org.p122a.p123a.p152c.p153a.C2925c;

@Deprecated
/* renamed from: org.a.a.c.d.a */
public final class C2965a {
    private C2965a() {
    }

    public static C2925c m16870a(HttpParams httpParams) {
        if (httpParams == null) {
            return null;
        }
        try {
            Field declaredField = httpParams.getClass().getDeclaredField("parameters");
            declaredField.setAccessible(true);
            Map map = (Map) declaredField.get(httpParams);
            if (map == null || map.isEmpty()) {
                return null;
            }
        } catch (Exception e) {
        }
        return C2925c.m16740q().m16769d(httpParams.getIntParameter("http.socket.timeout", 0)).m16766b(httpParams.getBooleanParameter("http.connection.stalecheck", true)).m16767c(httpParams.getIntParameter("http.connection.timeout", 0)).m16763a(httpParams.getBooleanParameter("http.protocol.expect-continue", false)).m16762a((HttpHost) httpParams.getParameter("http.route.default-proxy")).m16760a((InetAddress) httpParams.getParameter("http.route.local-address")).m16772f(httpParams.getBooleanParameter("http.protocol.handle-authentication", true)).m16771e(httpParams.getBooleanParameter("http.protocol.allow-circular-redirects", false)).m16759a((String) httpParams.getParameter("http.protocol.cookie-policy")).m16758a(httpParams.getIntParameter("http.protocol.max-redirects", 50)).m16768c(httpParams.getBooleanParameter("http.protocol.handle-redirects", true)).m16770d(!httpParams.getBooleanParameter("http.protocol.reject-relative-redirect", false)).m16757a();
    }
}
