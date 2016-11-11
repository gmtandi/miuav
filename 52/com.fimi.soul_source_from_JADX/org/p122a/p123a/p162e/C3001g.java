package org.p122a.p123a.p162e;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p180o.C3234a;

@C2913c
/* renamed from: org.a.a.e.g */
public final class C3001g<I> {
    private final Map<String, I> f14977a;

    C3001g() {
        this.f14977a = new HashMap();
    }

    public static <I> C3001g<I> m17034a() {
        return new C3001g();
    }

    public C3001g<I> m17035a(String str, I i) {
        C3234a.m17885a((CharSequence) str, "ID");
        C3234a.m17886a((Object) i, "Item");
        this.f14977a.put(str.toLowerCase(Locale.US), i);
        return this;
    }

    public C3000f<I> m17036b() {
        return new C3000f(this.f14977a);
    }

    public String toString() {
        return this.f14977a.toString();
    }
}
