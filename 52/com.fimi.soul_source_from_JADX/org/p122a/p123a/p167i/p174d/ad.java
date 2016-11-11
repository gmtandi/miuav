package org.p122a.p123a.p167i.p174d;

import com.tencent.mm.sdk.contact.RContact;
import com.xiaomi.market.sdk.C2537j;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookiePathComparator;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.message.BufferedHeader;
import org.apache.http.util.CharArrayBuffer;
import org.p122a.p123a.p124f.p125c.C3022o;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p152c.p161f.C2979b;
import org.p122a.p123a.p166g.C3044a;
import org.p122a.p123a.p180o.C3234a;

@C2913c
/* renamed from: org.a.a.i.d.ad */
public class ad extends C3138s {
    private static final CookiePathComparator f15466a;
    private static final String[] f15467b;
    private final String[] f15468c;
    private final boolean f15469d;

    static {
        f15466a = new CookiePathComparator();
        f15467b = new String[]{C2979b.f14907a, C2979b.f14908b, C2979b.f14909c};
    }

    public ad() {
        this(null, false);
    }

    public ad(String[] strArr, boolean z) {
        if (strArr != null) {
            this.f15468c = (String[]) strArr.clone();
        } else {
            this.f15468c = f15467b;
        }
        this.f15469d = z;
        m17661a(C2537j.aq, new ae());
        m17661a("path", new C3145i());
        m17661a("domain", new ab());
        m17661a("max-age", new C3144h());
        m17661a("secure", new C3146j());
        m17661a("comment", new C3141e());
        m17661a("expires", new C3143g(this.f15468c));
    }

    private List<Header> m17666a(List<Cookie> list) {
        int i = Integer.MAX_VALUE;
        for (Cookie cookie : list) {
            i = cookie.getVersion() < i ? cookie.getVersion() : i;
        }
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(list.size() * 40);
        charArrayBuffer.append("Cookie");
        charArrayBuffer.append(": ");
        charArrayBuffer.append("$Version=");
        charArrayBuffer.append(Integer.toString(i));
        for (Cookie cookie2 : list) {
            charArrayBuffer.append("; ");
            m17669a(charArrayBuffer, cookie2, i);
        }
        List<Header> arrayList = new ArrayList(1);
        arrayList.add(new BufferedHeader(charArrayBuffer));
        return arrayList;
    }

    private List<Header> m17667b(List<Cookie> list) {
        List<Header> arrayList = new ArrayList(list.size());
        for (Cookie cookie : list) {
            int version = cookie.getVersion();
            CharArrayBuffer charArrayBuffer = new CharArrayBuffer(40);
            charArrayBuffer.append("Cookie: ");
            charArrayBuffer.append("$Version=");
            charArrayBuffer.append(Integer.toString(version));
            charArrayBuffer.append("; ");
            m17669a(charArrayBuffer, cookie, version);
            arrayList.add(new BufferedHeader(charArrayBuffer));
        }
        return arrayList;
    }

    protected void m17668a(CharArrayBuffer charArrayBuffer, String str, String str2, int i) {
        charArrayBuffer.append(str);
        charArrayBuffer.append("=");
        if (str2 == null) {
            return;
        }
        if (i > 0) {
            charArrayBuffer.append(C3022o.f15057e);
            charArrayBuffer.append(str2);
            charArrayBuffer.append(C3022o.f15057e);
            return;
        }
        charArrayBuffer.append(str2);
    }

    protected void m17669a(CharArrayBuffer charArrayBuffer, Cookie cookie, int i) {
        m17668a(charArrayBuffer, cookie.getName(), cookie.getValue(), i);
        if (cookie.getPath() != null && (cookie instanceof ClientCookie) && ((ClientCookie) cookie).containsAttribute("path")) {
            charArrayBuffer.append("; ");
            m17668a(charArrayBuffer, "$Path", cookie.getPath(), i);
        }
        if (cookie.getDomain() != null && (cookie instanceof ClientCookie) && ((ClientCookie) cookie).containsAttribute("domain")) {
            charArrayBuffer.append("; ");
            m17668a(charArrayBuffer, "$Domain", cookie.getDomain(), i);
        }
    }

    public List<Header> formatCookies(List<Cookie> list) {
        List list2;
        C3234a.m17887a((Collection) list, "List of cookies");
        if (list.size() > 1) {
            List arrayList = new ArrayList(list);
            Collections.sort(arrayList, f15466a);
            list2 = arrayList;
        }
        return this.f15469d ? m17666a(list2) : m17667b(list2);
    }

    public int getVersion() {
        return 1;
    }

    public Header getVersionHeader() {
        return null;
    }

    public List<Cookie> parse(Header header, CookieOrigin cookieOrigin) {
        C3234a.m17886a((Object) header, "Header");
        C3234a.m17886a((Object) cookieOrigin, "Cookie origin");
        if (header.getName().equalsIgnoreCase("Set-Cookie")) {
            return m17665a(header.getElements(), cookieOrigin);
        }
        throw new MalformedCookieException("Unrecognized cookie header '" + header.toString() + "'");
    }

    public String toString() {
        return "rfc2109";
    }

    public void validate(Cookie cookie, CookieOrigin cookieOrigin) {
        C3234a.m17886a((Object) cookie, "Cookie");
        String name = cookie.getName();
        if (name.indexOf(32) != -1) {
            throw new C3044a("Cookie name may not contain blanks");
        } else if (name.startsWith(RContact.FAVOUR_CONTACT_SHOW_HEAD_CHAR)) {
            throw new C3044a("Cookie name may not start with $");
        } else {
            super.validate(cookie, cookieOrigin);
        }
    }
}
