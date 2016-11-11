package org.p122a.p123a.p167i.p174d;

import java.util.Collection;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecFactory;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p166g.C3045b;

@C2912b
/* renamed from: org.a.a.i.d.aj */
public class aj implements C3045b, CookieSpecFactory {
    private final String[] f15470a;
    private final boolean f15471b;

    public aj() {
        this(null, false);
    }

    public aj(String[] strArr, boolean z) {
        this.f15470a = strArr;
        this.f15471b = z;
    }

    public CookieSpec m17673a(HttpContext httpContext) {
        return new ak(this.f15470a, this.f15471b);
    }

    public CookieSpec newInstance(HttpParams httpParams) {
        if (httpParams == null) {
            return new ak();
        }
        Collection collection = (Collection) httpParams.getParameter("http.protocol.cookie-datepatterns");
        return new ak(collection != null ? (String[]) collection.toArray(new String[collection.size()]) : null, httpParams.getBooleanParameter("http.protocol.single-cookie-header", false));
    }
}
