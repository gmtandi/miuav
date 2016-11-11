package org.p122a.p123a.p167i.p169b;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieIdentityComparator;
import org.p122a.p123a.p150a.C2911a;
import org.p122a.p123a.p150a.C2914d;

@C2914d
/* renamed from: org.a.a.i.b.d */
public class C3082d implements Serializable, CookieStore {
    private static final long serialVersionUID = -7581093305228232025L;
    @C2911a(a = "this")
    private final TreeSet<Cookie> f15276a;

    public C3082d() {
        this.f15276a = new TreeSet(new CookieIdentityComparator());
    }

    public synchronized void m17374a(Cookie[] cookieArr) {
        if (cookieArr != null) {
            for (Cookie addCookie : cookieArr) {
                addCookie(addCookie);
            }
        }
    }

    public synchronized void addCookie(Cookie cookie) {
        if (cookie != null) {
            this.f15276a.remove(cookie);
            if (!cookie.isExpired(new Date())) {
                this.f15276a.add(cookie);
            }
        }
    }

    public synchronized void clear() {
        this.f15276a.clear();
    }

    public synchronized boolean clearExpired(Date date) {
        boolean z = false;
        synchronized (this) {
            if (date != null) {
                Iterator it = this.f15276a.iterator();
                boolean z2 = false;
                while (it.hasNext()) {
                    if (((Cookie) it.next()).isExpired(date)) {
                        it.remove();
                        z2 = true;
                    }
                }
                z = z2;
            }
        }
        return z;
    }

    public synchronized List<Cookie> getCookies() {
        return new ArrayList(this.f15276a);
    }

    public synchronized String toString() {
        return this.f15276a.toString();
    }
}
