package org.p122a.p123a.p167i.p174d;

import java.util.Collection;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecFactory;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p166g.C3045b;

@C2912b
/* renamed from: org.a.a.i.d.k */
public class C3147k implements C3045b, CookieSpecFactory {
    private final String[] f15485a;
    private final boolean f15486b;

    public C3147k() {
        this(null, false);
    }

    public C3147k(String[] strArr, boolean z) {
        this.f15485a = strArr;
        this.f15486b = z;
    }

    public CookieSpec m17679a(HttpContext httpContext) {
        return new C3148l(this.f15485a, this.f15486b);
    }

    public CookieSpec newInstance(HttpParams httpParams) {
        if (httpParams == null) {
            return new C3148l();
        }
        Collection collection = (Collection) httpParams.getParameter("http.protocol.cookie-datepatterns");
        return new C3148l(collection != null ? (String[]) collection.toArray(new String[collection.size()]) : null, httpParams.getBooleanParameter("http.protocol.single-cookie-header", false));
    }
}
