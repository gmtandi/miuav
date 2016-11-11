package org.p122a.p123a.p167i.p174d;

import java.util.Collection;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecFactory;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p166g.C3045b;

@C2912b
/* renamed from: org.a.a.i.d.m */
public class C3149m implements C3045b, CookieSpecFactory {
    private final String[] f15492a;
    private final C3150n f15493b;

    public C3149m() {
        this(null, C3150n.SECURITYLEVEL_DEFAULT);
    }

    public C3149m(String[] strArr) {
        this(null, C3150n.SECURITYLEVEL_DEFAULT);
    }

    public C3149m(String[] strArr, C3150n c3150n) {
        this.f15492a = strArr;
        this.f15493b = c3150n;
    }

    public CookieSpec m17683a(HttpContext httpContext) {
        return new C3151o(this.f15492a);
    }

    public CookieSpec newInstance(HttpParams httpParams) {
        if (httpParams == null) {
            return new C3151o(null, this.f15493b);
        }
        Collection collection = (Collection) httpParams.getParameter("http.protocol.cookie-datepatterns");
        return new C3151o(collection != null ? (String[]) collection.toArray(new String[collection.size()]) : null, this.f15493b);
    }
}
