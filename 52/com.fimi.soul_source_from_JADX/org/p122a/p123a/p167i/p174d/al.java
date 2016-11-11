package org.p122a.p123a.p167i.p174d;

import com.xiaomi.market.sdk.C2537j;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieAttributeHandler;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;
import org.apache.http.cookie.SetCookie2;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p166g.C3044a;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.i.d.al */
public class al implements CookieAttributeHandler {
    public boolean match(Cookie cookie, CookieOrigin cookieOrigin) {
        return true;
    }

    public void parse(SetCookie setCookie, String str) {
        C3234a.m17886a((Object) setCookie, "Cookie");
        if (str == null) {
            throw new MalformedCookieException("Missing value for version attribute");
        }
        int parseInt;
        try {
            parseInt = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            parseInt = -1;
        }
        if (parseInt < 0) {
            throw new MalformedCookieException("Invalid cookie version.");
        }
        setCookie.setVersion(parseInt);
    }

    public void validate(Cookie cookie, CookieOrigin cookieOrigin) {
        C3234a.m17886a((Object) cookie, "Cookie");
        if ((cookie instanceof SetCookie2) && (cookie instanceof ClientCookie) && !((ClientCookie) cookie).containsAttribute(C2537j.aq)) {
            throw new C3044a("Violates RFC 2965. Version attribute is required.");
        }
    }
}
