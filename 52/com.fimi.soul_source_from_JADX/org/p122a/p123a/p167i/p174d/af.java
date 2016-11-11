package org.p122a.p123a.p167i.p174d;

import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieAttributeHandler;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.SetCookie;
import org.apache.http.cookie.SetCookie2;
import org.p122a.p123a.p150a.C2912b;

@C2912b
/* renamed from: org.a.a.i.d.af */
public class af implements CookieAttributeHandler {
    public boolean match(Cookie cookie, CookieOrigin cookieOrigin) {
        return true;
    }

    public void parse(SetCookie setCookie, String str) {
        if (setCookie instanceof SetCookie2) {
            ((SetCookie2) setCookie).setCommentURL(str);
        }
    }

    public void validate(Cookie cookie, CookieOrigin cookieOrigin) {
    }
}
