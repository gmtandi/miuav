package org.p122a.p123a.p167i.p174d;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieAttributeHandler;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.SetCookie;
import org.p122a.p123a.p152c.p161f.C2985h;

/* renamed from: org.a.a.i.d.z */
public class C3161z implements CookieAttributeHandler {
    private final CookieAttributeHandler f15505a;
    private Set<String> f15506b;
    private Set<String> f15507c;

    public C3161z(CookieAttributeHandler cookieAttributeHandler) {
        this.f15505a = cookieAttributeHandler;
    }

    private boolean m17690a(Cookie cookie) {
        String domain = cookie.getDomain();
        if (domain.startsWith(".")) {
            domain = domain.substring(1);
        }
        domain = C2985h.m16928a(domain);
        if (this.f15506b != null && this.f15506b.contains(domain)) {
            return false;
        }
        if (this.f15507c == null) {
            return false;
        }
        while (!this.f15507c.contains(domain)) {
            if (domain.startsWith("*.")) {
                domain = domain.substring(2);
            }
            int indexOf = domain.indexOf(46);
            if (indexOf != -1) {
                domain = "*" + domain.substring(indexOf);
                if (domain.length() <= 0) {
                }
            }
            return false;
        }
        return true;
    }

    public void m17691a(Collection<String> collection) {
        this.f15507c = new HashSet(collection);
    }

    public void m17692b(Collection<String> collection) {
        this.f15506b = new HashSet(collection);
    }

    public boolean match(Cookie cookie, CookieOrigin cookieOrigin) {
        return m17690a(cookie) ? false : this.f15505a.match(cookie, cookieOrigin);
    }

    public void parse(SetCookie setCookie, String str) {
        this.f15505a.parse(setCookie, str);
    }

    public void validate(Cookie cookie, CookieOrigin cookieOrigin) {
        this.f15505a.validate(cookie, cookieOrigin);
    }
}
