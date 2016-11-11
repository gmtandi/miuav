package org.p122a.p123a.p167i.p169b;

import java.util.HashMap;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScheme;
import org.p122a.p123a.p124f.C3041i;
import org.p122a.p123a.p124f.C3042j;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p152c.C2927a;
import org.p122a.p123a.p167i.p170c.C3120l;
import org.p122a.p123a.p180o.C3234a;

@C2913c
/* renamed from: org.a.a.i.b.c */
public class C3081c implements C2927a {
    private final HashMap<HttpHost, AuthScheme> f15274a;
    private final C3041i f15275b;

    public C3081c() {
        this(null);
    }

    public C3081c(C3041i c3041i) {
        this.f15274a = new HashMap();
        if (c3041i == null) {
            c3041i = C3120l.f15424a;
        }
        this.f15275b = c3041i;
    }

    public AuthScheme m17369a(HttpHost httpHost) {
        C3234a.m17886a((Object) httpHost, "HTTP host");
        return (AuthScheme) this.f15274a.get(m17373c(httpHost));
    }

    public void m17370a() {
        this.f15274a.clear();
    }

    public void m17371a(HttpHost httpHost, AuthScheme authScheme) {
        C3234a.m17886a((Object) httpHost, "HTTP host");
        this.f15274a.put(m17373c(httpHost), authScheme);
    }

    public void m17372b(HttpHost httpHost) {
        C3234a.m17886a((Object) httpHost, "HTTP host");
        this.f15274a.remove(m17373c(httpHost));
    }

    protected HttpHost m17373c(HttpHost httpHost) {
        if (httpHost.getPort() > 0) {
            return httpHost;
        }
        try {
            return new HttpHost(httpHost.getHostName(), this.f15275b.m17152a(httpHost), httpHost.getSchemeName());
        } catch (C3042j e) {
            return httpHost;
        }
    }

    public String toString() {
        return this.f15274a.toString();
    }
}
