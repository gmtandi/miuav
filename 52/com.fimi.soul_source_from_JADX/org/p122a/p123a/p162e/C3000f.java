package org.p122a.p123a.p162e;

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.p122a.p123a.p150a.C2914d;

@C2914d
/* renamed from: org.a.a.e.f */
public final class C3000f<I> implements C2997c<I> {
    private final Map<String, I> f14976a;

    C3000f(Map<String, I> map) {
        this.f14976a = new ConcurrentHashMap(map);
    }

    public I m17033a(String str) {
        return str == null ? null : this.f14976a.get(str.toLowerCase(Locale.US));
    }

    public String toString() {
        return this.f14976a.toString();
    }
}
