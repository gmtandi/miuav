package org.p122a.p123a.p167i.p174d;

import java.util.Locale;
import java.util.StringTokenizer;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p166g.C3044a;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.i.d.v */
public class C3157v extends C3142f {
    private static boolean m17686a(String str) {
        String toUpperCase = str.toUpperCase(Locale.ENGLISH);
        return toUpperCase.endsWith(".COM") || toUpperCase.endsWith(".EDU") || toUpperCase.endsWith(".NET") || toUpperCase.endsWith(".GOV") || toUpperCase.endsWith(".MIL") || toUpperCase.endsWith(".ORG") || toUpperCase.endsWith(".INT");
    }

    public boolean match(Cookie cookie, CookieOrigin cookieOrigin) {
        C3234a.m17886a((Object) cookie, "Cookie");
        C3234a.m17886a((Object) cookieOrigin, "Cookie origin");
        String host = cookieOrigin.getHost();
        String domain = cookie.getDomain();
        return domain == null ? false : host.endsWith(domain);
    }

    public void validate(Cookie cookie, CookieOrigin cookieOrigin) {
        super.validate(cookie, cookieOrigin);
        String host = cookieOrigin.getHost();
        String domain = cookie.getDomain();
        if (host.contains(".")) {
            int countTokens = new StringTokenizer(domain, ".").countTokens();
            if (C3157v.m17686a(domain)) {
                if (countTokens < 2) {
                    throw new C3044a("Domain attribute \"" + domain + "\" violates the Netscape cookie specification for " + "special domains");
                }
            } else if (countTokens < 3) {
                throw new C3044a("Domain attribute \"" + domain + "\" violates the Netscape cookie specification");
            }
        }
    }
}
