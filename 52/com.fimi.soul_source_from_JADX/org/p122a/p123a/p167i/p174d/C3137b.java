package org.p122a.p123a.p167i.p174d;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.cookie.CookieAttributeHandler;
import org.apache.http.cookie.CookieSpec;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p180o.C3234a;

@C2913c
/* renamed from: org.a.a.i.d.b */
public abstract class C3137b implements CookieSpec {
    private final Map<String, CookieAttributeHandler> f15465a;

    public C3137b() {
        this.f15465a = new HashMap(10);
    }

    protected Collection<CookieAttributeHandler> m17659a() {
        return this.f15465a.values();
    }

    protected CookieAttributeHandler m17660a(String str) {
        return (CookieAttributeHandler) this.f15465a.get(str);
    }

    public void m17661a(String str, CookieAttributeHandler cookieAttributeHandler) {
        C3234a.m17886a((Object) str, "Attribute name");
        C3234a.m17886a((Object) cookieAttributeHandler, "Attribute handler");
        this.f15465a.put(str, cookieAttributeHandler);
    }

    protected CookieAttributeHandler m17662b(String str) {
        CookieAttributeHandler a = m17660a(str);
        if (a != null) {
            return a;
        }
        throw new IllegalStateException("Handler not registered for " + str + " attribute.");
    }
}
