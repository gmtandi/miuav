package org.p122a.p123a.p167i.p174d;

import java.util.Locale;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieAttributeHandler;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p166g.C3044a;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.i.d.ah */
public class ah implements CookieAttributeHandler {
    public boolean m17670a(String str, String str2) {
        return str.equals(str2) || (str2.startsWith(".") && str.endsWith(str2));
    }

    public boolean match(Cookie cookie, CookieOrigin cookieOrigin) {
        C3234a.m17886a((Object) cookie, "Cookie");
        C3234a.m17886a((Object) cookieOrigin, "Cookie origin");
        String toLowerCase = cookieOrigin.getHost().toLowerCase(Locale.ENGLISH);
        String domain = cookie.getDomain();
        return m17670a(toLowerCase, domain) && toLowerCase.substring(0, toLowerCase.length() - domain.length()).indexOf(46) == -1;
    }

    public void parse(SetCookie setCookie, String str) {
        C3234a.m17886a((Object) setCookie, "Cookie");
        if (str == null) {
            throw new MalformedCookieException("Missing value for domain attribute");
        } else if (str.trim().length() == 0) {
            throw new MalformedCookieException("Blank value for domain attribute");
        } else {
            String toLowerCase = str.toLowerCase(Locale.ENGLISH);
            if (!str.startsWith(".")) {
                toLowerCase = '.' + toLowerCase;
            }
            setCookie.setDomain(toLowerCase);
        }
    }

    public void validate(Cookie cookie, CookieOrigin cookieOrigin) {
        C3234a.m17886a((Object) cookie, "Cookie");
        C3234a.m17886a((Object) cookieOrigin, "Cookie origin");
        String toLowerCase = cookieOrigin.getHost().toLowerCase(Locale.ENGLISH);
        if (cookie.getDomain() == null) {
            throw new C3044a("Invalid cookie state: domain not specified");
        }
        String toLowerCase2 = cookie.getDomain().toLowerCase(Locale.ENGLISH);
        if ((cookie instanceof ClientCookie) && ((ClientCookie) cookie).containsAttribute("domain")) {
            if (toLowerCase2.startsWith(".")) {
                int indexOf = toLowerCase2.indexOf(46, 1);
                if ((indexOf < 0 || indexOf == toLowerCase2.length() - 1) && !toLowerCase2.equals(".local")) {
                    throw new C3044a("Domain attribute \"" + cookie.getDomain() + "\" violates RFC 2965: the value contains no embedded dots " + "and the value is not .local");
                } else if (!m17670a(toLowerCase, toLowerCase2)) {
                    throw new C3044a("Domain attribute \"" + cookie.getDomain() + "\" violates RFC 2965: effective host name does not " + "domain-match domain attribute.");
                } else if (toLowerCase.substring(0, toLowerCase.length() - toLowerCase2.length()).indexOf(46) != -1) {
                    throw new C3044a("Domain attribute \"" + cookie.getDomain() + "\" violates RFC 2965: " + "effective host minus domain may not contain any dots");
                } else {
                    return;
                }
            }
            throw new C3044a("Domain attribute \"" + cookie.getDomain() + "\" violates RFC 2109: domain must start with a dot");
        } else if (!cookie.getDomain().equals(toLowerCase)) {
            throw new C3044a("Illegal domain attribute: \"" + cookie.getDomain() + "\"." + "Domain of origin: \"" + toLowerCase + "\"");
        }
    }
}
