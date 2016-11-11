package org.p122a.p123a.p159n;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.p150a.C2914d;
import org.p122a.p123a.p180o.C3234a;

@C2914d
/* renamed from: org.a.a.n.a */
public class C3223a implements HttpContext {
    private final HttpContext f15691a;
    private final Map<String, Object> f15692b;

    public C3223a() {
        this(null);
    }

    public C3223a(HttpContext httpContext) {
        this.f15692b = new ConcurrentHashMap();
        this.f15691a = httpContext;
    }

    public void m17857a() {
        this.f15692b.clear();
    }

    public Object getAttribute(String str) {
        C3234a.m17886a((Object) str, "Id");
        Object obj = this.f15692b.get(str);
        return (obj != null || this.f15691a == null) ? obj : this.f15691a.getAttribute(str);
    }

    public Object removeAttribute(String str) {
        C3234a.m17886a((Object) str, "Id");
        return this.f15692b.remove(str);
    }

    public void setAttribute(String str, Object obj) {
        C3234a.m17886a((Object) str, "Id");
        if (obj != null) {
            this.f15692b.put(str, obj);
        } else {
            this.f15692b.remove(str);
        }
    }

    public String toString() {
        return this.f15692b.toString();
    }
}
