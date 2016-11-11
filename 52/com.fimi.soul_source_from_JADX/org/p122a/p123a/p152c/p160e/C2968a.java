package org.p122a.p123a.p152c.p160e;

import java.net.URI;
import java.util.List;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.RouteInfo;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p151b.C2918c;
import org.p122a.p123a.p151b.C2919d;
import org.p122a.p123a.p152c.C2927a;
import org.p122a.p123a.p152c.p153a.C2925c;
import org.p122a.p123a.p159n.C2967c;
import org.p122a.p123a.p159n.C3223a;
import org.p122a.p123a.p162e.C2997c;
import org.p122a.p123a.p166g.C3045b;

@C2913c
/* renamed from: org.a.a.c.e.a */
public class C2968a extends C2967c {
    public static final String f14887a = "http.route";
    public static final String f14888b = "http.protocol.redirect-locations";
    public static final String f14889c = "http.cookiespec-registry";
    public static final String f14890d = "http.cookie-spec";
    public static final String f14891e = "http.cookie-origin";
    public static final String f14892f = "http.cookie-store";
    public static final String f14893g = "http.auth.credentials-provider";
    public static final String f14894h = "http.auth.auth-cache";
    public static final String f14895i = "http.auth.target-scope";
    public static final String f14896j = "http.auth.proxy-scope";
    public static final String f14897k = "http.user-token";
    public static final String f14898l = "http.authscheme-registry";
    public static final String f14899m = "http.request-config";

    public C2968a(HttpContext httpContext) {
        super(httpContext);
    }

    public static C2968a m16883a() {
        return new C2968a(new C3223a());
    }

    public static C2968a m16884a(HttpContext httpContext) {
        return httpContext instanceof C2968a ? (C2968a) httpContext : new C2968a(httpContext);
    }

    private <T> C2997c<T> m16885b(String str, Class<T> cls) {
        return (C2997c) m16875a(str, C2997c.class);
    }

    public <T> T m16886a(Class<T> cls) {
        return m16875a(f14897k, cls);
    }

    public void m16887a(Object obj) {
        setAttribute(f14897k, obj);
    }

    public void m16888a(C2925c c2925c) {
        setAttribute(f14899m, c2925c);
    }

    public void m16889a(C2927a c2927a) {
        setAttribute(f14894h, c2927a);
    }

    public void m16890a(C2997c<C3045b> c2997c) {
        setAttribute(f14889c, c2997c);
    }

    public void m16891a(CookieStore cookieStore) {
        setAttribute(f14892f, cookieStore);
    }

    public void m16892a(CredentialsProvider credentialsProvider) {
        setAttribute(f14893g, credentialsProvider);
    }

    public RouteInfo m16893b() {
        return (RouteInfo) m16875a(f14887a, HttpRoute.class);
    }

    public void m16894b(C2997c<C2918c> c2997c) {
        setAttribute(f14898l, c2997c);
    }

    public List<URI> m16895c() {
        return (List) m16875a(f14888b, List.class);
    }

    public CookieStore m16896d() {
        return (CookieStore) m16875a(f14892f, CookieStore.class);
    }

    public CookieSpec m16897e() {
        return (CookieSpec) m16875a(f14890d, CookieSpec.class);
    }

    public CookieOrigin m16898f() {
        return (CookieOrigin) m16875a(f14891e, CookieOrigin.class);
    }

    public C2997c<C3045b> m16899g() {
        return m16885b(f14889c, C3045b.class);
    }

    public C2997c<C2918c> m16900h() {
        return m16885b(f14898l, C2918c.class);
    }

    public CredentialsProvider m16901i() {
        return (CredentialsProvider) m16875a(f14893g, CredentialsProvider.class);
    }

    public C2927a m16902j() {
        return (C2927a) m16875a(f14894h, C2927a.class);
    }

    public C2919d m16903k() {
        return (C2919d) m16875a(f14895i, C2919d.class);
    }

    public C2919d m16904l() {
        return (C2919d) m16875a(f14896j, C2919d.class);
    }

    public Object m16905m() {
        return getAttribute(f14897k);
    }

    public C2925c m16906n() {
        C2925c c2925c = (C2925c) m16875a(f14899m, C2925c.class);
        return c2925c != null ? c2925c : C2925c.f14794a;
    }
}
