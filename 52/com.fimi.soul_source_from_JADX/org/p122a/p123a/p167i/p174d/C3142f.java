package org.p122a.p123a.p167i.p174d;

import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieAttributeHandler;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p166g.C3044a;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.i.d.f */
public class C3142f implements CookieAttributeHandler {
    public boolean match(Cookie cookie, CookieOrigin cookieOrigin) {
        C3234a.m17886a((Object) cookie, "Cookie");
        C3234a.m17886a((Object) cookieOrigin, "Cookie origin");
        String host = cookieOrigin.getHost();
        String domain = cookie.getDomain();
        if (domain == null) {
            return false;
        }
        if (host.equals(domain)) {
            return true;
        }
        if (!domain.startsWith(".")) {
            domain = '.' + domain;
        }
        boolean z = host.endsWith(domain) || host.equals(domain.substring(1));
        return z;
    }

    public void parse(SetCookie setCookie, String str) {
        C3234a.m17886a((Object) setCookie, "Cookie");
        if (str == null) {
            throw new MalformedCookieException("Missing value for domain attribute");
        } else if (str.trim().length() == 0) {
            throw new MalformedCookieException("Blank value for domain attribute");
        } else {
            setCookie.setDomain(str);
        }
    }

    public void validate(Cookie cookie, CookieOrigin cookieOrigin) {
        C3234a.m17886a((Object) cookie, "Cookie");
        C3234a.m17886a((Object) cookieOrigin, "Cookie origin");
        String host = cookieOrigin.getHost();
        String domain = cookie.getDomain();
        if (domain == null) {
            throw new C3044a("Cookie domain may not be null");
        } else if (host.contains(".")) {
            if (!host.endsWith(domain)) {
                if (domain.startsWith(".")) {
                    domain = domain.substring(1, domain.length());
                }
                if (!host.equals(domain)) {
                    throw new C3044a("Illegal domain attribute \"" + domain + "\". Domain of origin: \"" + host + "\"");
                }
            }
        } else if (!host.equals(domain)) {
            throw new C3044a("Illegal domain attribute \"" + domain + "\". Domain of origin: \"" + host + "\"");
        }
    }
}
