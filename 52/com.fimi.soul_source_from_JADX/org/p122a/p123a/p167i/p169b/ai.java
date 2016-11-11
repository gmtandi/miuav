package org.p122a.p123a.p167i.p169b;

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.HttpRequest;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p152c.p156c.C2949f;
import org.p122a.p123a.p152c.p156c.C2951i;
import org.p122a.p123a.p152c.p156c.C2952j;
import org.p122a.p123a.p152c.p156c.C2953k;
import org.p122a.p123a.p152c.p156c.C2956n;
import org.p122a.p123a.p152c.p156c.C2960s;

@C2912b
/* renamed from: org.a.a.i.b.ai */
public class ai extends C3079l {
    private final Map<String, Boolean> f15270b;

    public ai() {
        this(3, false);
    }

    public ai(int i, boolean z) {
        super(i, z);
        this.f15270b = new ConcurrentHashMap();
        this.f15270b.put(C2951i.f14860a, Boolean.TRUE);
        this.f15270b.put(C2952j.f14861a, Boolean.TRUE);
        this.f15270b.put(C2956n.f14865a, Boolean.TRUE);
        this.f15270b.put(C2949f.f14858a, Boolean.TRUE);
        this.f15270b.put(C2953k.f14862a, Boolean.TRUE);
        this.f15270b.put(C2960s.f14871a, Boolean.TRUE);
    }

    protected boolean m17358a(HttpRequest httpRequest) {
        Boolean bool = (Boolean) this.f15270b.get(httpRequest.getRequestLine().getMethod().toUpperCase(Locale.ENGLISH));
        return bool != null && bool.booleanValue();
    }
}
