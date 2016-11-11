package org.p122a.p123a.p167i.p174d;

import java.util.Date;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p152c.p161f.C2979b;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.i.d.g */
public class C3143g extends C3136a {
    private final String[] f15484a;

    public C3143g(String[] strArr) {
        C3234a.m17886a((Object) strArr, "Array of date patterns");
        this.f15484a = strArr;
    }

    public void parse(SetCookie setCookie, String str) {
        C3234a.m17886a((Object) setCookie, "Cookie");
        if (str == null) {
            throw new MalformedCookieException("Missing value for expires attribute");
        }
        Date a = C2979b.m16917a(str, this.f15484a);
        if (a == null) {
            throw new MalformedCookieException("Unable to parse expires attribute: " + str);
        }
        setCookie.setExpiryDate(a);
    }
}
