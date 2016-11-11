package org.p122a.p123a.p167i.p174d;

import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.i.d.r */
public class C3154r extends C3136a {
    public void parse(SetCookie setCookie, String str) {
        C3234a.m17886a((Object) setCookie, "Cookie");
        if (str == null) {
            throw new MalformedCookieException("Missing value for version attribute");
        }
        int i = 0;
        try {
            i = Integer.parseInt(str);
        } catch (NumberFormatException e) {
        }
        setCookie.setVersion(i);
    }
}
