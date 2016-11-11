package org.p122a.p123a.p167i.p174d;

import java.util.Locale;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieAttributeHandler;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p166g.C3044a;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.i.d.ab */
public class ab implements CookieAttributeHandler {
    public boolean match(Cookie cookie, CookieOrigin cookieOrigin) {
        C3234a.m17886a((Object) cookie, "Cookie");
        C3234a.m17886a((Object) cookieOrigin, "Cookie origin");
        String host = cookieOrigin.getHost();
        String domain = cookie.getDomain();
        return domain == null ? false : host.equals(domain) || (domain.startsWith(".") && host.endsWith(domain));
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
        } else if (!domain.equals(host)) {
            if (domain.indexOf(46) == -1) {
                throw new C3044a("Domain attribute \"" + domain + "\" does not match the host \"" + host + "\"");
            } else if (domain.startsWith(".")) {
                int indexOf = domain.indexOf(46, 1);
                if (indexOf < 0 || indexOf == domain.length() - 1) {
                    throw new C3044a("Domain attribute \"" + domain + "\" violates RFC 2109: domain must contain an embedded dot");
                }
                host = host.toLowerCase(Locale.ENGLISH);
                if (!host.endsWith(domain)) {
                    throw new C3044a("Illegal domain attribute \"" + domain + "\". Domain of origin: \"" + host + "\"");
                } else if (host.substring(0, host.length() - domain.length()).indexOf(46) != -1) {
                    throw new C3044a("Domain attribute \"" + domain + "\" violates RFC 2109: host minus domain may not contain any dots");
                }
            } else {
                throw new C3044a("Domain attribute \"" + domain + "\" violates RFC 2109: domain must start with a dot");
            }
        }
    }
}
