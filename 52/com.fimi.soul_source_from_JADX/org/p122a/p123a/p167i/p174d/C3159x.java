package org.p122a.p123a.p167i.p174d;

import java.util.Collection;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecFactory;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p166g.C3045b;

@C2912b
/* renamed from: org.a.a.i.d.x */
public class C3159x implements C3045b, CookieSpecFactory {
    private final String[] f15502a;

    public C3159x() {
        this(null);
    }

    public C3159x(String[] strArr) {
        this.f15502a = strArr;
    }

    public CookieSpec m17689a(HttpContext httpContext) {
        return new C3160y(this.f15502a);
    }

    public CookieSpec newInstance(HttpParams httpParams) {
        if (httpParams == null) {
            return new C3160y();
        }
        Collection collection = (Collection) httpParams.getParameter("http.protocol.cookie-datepatterns");
        return new C3160y(collection != null ? (String[]) collection.toArray(new String[collection.size()]) : null);
    }
}
