package org.p122a.p123a.p152c.p156c;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpResponse;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p180o.C3234a;

@C2913c
/* renamed from: org.a.a.c.c.k */
public class C2953k extends C2948o {
    public static final String f14862a = "OPTIONS";

    public C2953k(String str) {
        m16824a(URI.create(str));
    }

    public C2953k(URI uri) {
        m16824a(uri);
    }

    public Set<String> m16829a(HttpResponse httpResponse) {
        C3234a.m17886a((Object) httpResponse, "HTTP response");
        HeaderIterator headerIterator = httpResponse.headerIterator(C3004e.f15021g);
        Set<String> hashSet = new HashSet();
        while (headerIterator.hasNext()) {
            for (HeaderElement name : headerIterator.nextHeader().getElements()) {
                hashSet.add(name.getName());
            }
        }
        return hashSet;
    }

    public String getMethod() {
        return f14862a;
    }
}
