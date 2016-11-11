package org.p122a.p123a.p167i.p174d;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.http.HeaderElement;
import org.apache.http.NameValuePair;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieAttributeHandler;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p180o.C3234a;

@C2913c
/* renamed from: org.a.a.i.d.s */
public abstract class C3138s extends C3137b {
    protected static String m17663a(CookieOrigin cookieOrigin) {
        String path = cookieOrigin.getPath();
        int lastIndexOf = path.lastIndexOf(47);
        if (lastIndexOf < 0) {
            return path;
        }
        if (lastIndexOf == 0) {
            lastIndexOf = 1;
        }
        return path.substring(0, lastIndexOf);
    }

    protected static String m17664b(CookieOrigin cookieOrigin) {
        return cookieOrigin.getHost();
    }

    protected List<Cookie> m17665a(HeaderElement[] headerElementArr, CookieOrigin cookieOrigin) {
        List<Cookie> arrayList = new ArrayList(headerElementArr.length);
        for (HeaderElement headerElement : headerElementArr) {
            String name = headerElement.getName();
            String value = headerElement.getValue();
            if (name == null || name.length() == 0) {
                throw new MalformedCookieException("Cookie name may not be empty");
            }
            SetCookie c3139d = new C3139d(name, value);
            c3139d.setPath(C3138s.m17663a(cookieOrigin));
            c3139d.setDomain(C3138s.m17664b(cookieOrigin));
            NameValuePair[] parameters = headerElement.getParameters();
            for (int length = parameters.length - 1; length >= 0; length--) {
                NameValuePair nameValuePair = parameters[length];
                String toLowerCase = nameValuePair.getName().toLowerCase(Locale.ENGLISH);
                c3139d.m17678a(toLowerCase, nameValuePair.getValue());
                CookieAttributeHandler a = m17660a(toLowerCase);
                if (a != null) {
                    a.parse(c3139d, nameValuePair.getValue());
                }
            }
            arrayList.add(c3139d);
        }
        return arrayList;
    }

    public boolean match(Cookie cookie, CookieOrigin cookieOrigin) {
        C3234a.m17886a((Object) cookie, "Cookie");
        C3234a.m17886a((Object) cookieOrigin, "Cookie origin");
        for (CookieAttributeHandler match : m17659a()) {
            if (!match.match(cookie, cookieOrigin)) {
                return false;
            }
        }
        return true;
    }

    public void validate(Cookie cookie, CookieOrigin cookieOrigin) {
        C3234a.m17886a((Object) cookie, "Cookie");
        C3234a.m17886a((Object) cookieOrigin, "Cookie origin");
        for (CookieAttributeHandler validate : m17659a()) {
            validate.validate(cookie, cookieOrigin);
        }
    }
}
