package org.p122a.p123a.p167i.p174d;

import java.util.Collection;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecFactory;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p166g.C3045b;

@C2912b
/* renamed from: org.a.a.i.d.ac */
public class ac implements C3045b, CookieSpecFactory {
    private final String[] f15463a;
    private final boolean f15464b;

    public ac() {
        this(null, false);
    }

    public ac(String[] strArr, boolean z) {
        this.f15463a = strArr;
        this.f15464b = z;
    }

    public CookieSpec m17658a(HttpContext httpContext) {
        return new ad(this.f15463a, this.f15464b);
    }

    public CookieSpec newInstance(HttpParams httpParams) {
        if (httpParams == null) {
            return new ad();
        }
        Collection collection = (Collection) httpParams.getParameter("http.protocol.cookie-datepatterns");
        return new ad(collection != null ? (String[]) collection.toArray(new String[collection.size()]) : null, httpParams.getBooleanParameter("http.protocol.single-cookie-header", false));
    }
}
