package org.p122a.p123a.p167i.p174d;

import com.xiaomi.market.sdk.C2537j;
import java.util.List;
import org.apache.http.FormattedHeader;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie2;
import org.apache.http.message.ParserCursor;
import org.apache.http.util.CharArrayBuffer;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p152c.p153a.C2924b;
import org.p122a.p123a.p180o.C3234a;

@C2913c
/* renamed from: org.a.a.i.d.l */
public class C3148l implements CookieSpec {
    private final String[] f15487a;
    private final boolean f15488b;
    private ak f15489c;
    private ad f15490d;
    private C3151o f15491e;

    public C3148l() {
        this(null, false);
    }

    public C3148l(String[] strArr, boolean z) {
        this.f15487a = strArr == null ? null : (String[]) strArr.clone();
        this.f15488b = z;
    }

    private ak m17680a() {
        if (this.f15489c == null) {
            this.f15489c = new ak(this.f15487a, this.f15488b);
        }
        return this.f15489c;
    }

    private ad m17681b() {
        if (this.f15490d == null) {
            this.f15490d = new ad(this.f15487a, this.f15488b);
        }
        return this.f15490d;
    }

    private C3151o m17682c() {
        if (this.f15491e == null) {
            this.f15491e = new C3151o(this.f15487a);
        }
        return this.f15491e;
    }

    public List<Header> formatCookies(List<Cookie> list) {
        C3234a.m17886a((Object) list, "List of cookies");
        int i = Integer.MAX_VALUE;
        Object obj = 1;
        for (Cookie cookie : list) {
            if (!(cookie instanceof SetCookie2)) {
                obj = null;
            }
            i = cookie.getVersion() < i ? cookie.getVersion() : i;
        }
        return i > 0 ? obj != null ? m17680a().formatCookies(list) : m17681b().formatCookies(list) : m17682c().formatCookies(list);
    }

    public int getVersion() {
        return m17680a().getVersion();
    }

    public Header getVersionHeader() {
        return m17680a().getVersionHeader();
    }

    public boolean match(Cookie cookie, CookieOrigin cookieOrigin) {
        C3234a.m17886a((Object) cookie, "Cookie");
        C3234a.m17886a((Object) cookieOrigin, "Cookie origin");
        return cookie.getVersion() > 0 ? cookie instanceof SetCookie2 ? m17680a().match(cookie, cookieOrigin) : m17681b().match(cookie, cookieOrigin) : m17682c().match(cookie, cookieOrigin);
    }

    public List<Cookie> parse(Header header, CookieOrigin cookieOrigin) {
        C3234a.m17886a((Object) header, "Header");
        C3234a.m17886a((Object) cookieOrigin, "Cookie origin");
        HeaderElement[] elements = header.getElements();
        int i = 0;
        int i2 = 0;
        for (HeaderElement headerElement : elements) {
            if (headerElement.getParameterByName(C2537j.aq) != null) {
                i2 = 1;
            }
            if (headerElement.getParameterByName("expires") != null) {
                i = 1;
            }
        }
        if (i == 0 && r0 != 0) {
            return "Set-Cookie2".equals(header.getName()) ? m17680a().m17676a(elements, cookieOrigin) : m17681b().m17665a(elements, cookieOrigin);
        } else {
            CharArrayBuffer buffer;
            ParserCursor parserCursor;
            C3158w c3158w = C3158w.f15501a;
            if (header instanceof FormattedHeader) {
                buffer = ((FormattedHeader) header).getBuffer();
                parserCursor = new ParserCursor(((FormattedHeader) header).getValuePos(), buffer.length());
            } else {
                String value = header.getValue();
                if (value == null) {
                    throw new MalformedCookieException("Header value is null");
                }
                buffer = new CharArrayBuffer(value.length());
                buffer.append(value);
                parserCursor = new ParserCursor(0, buffer.length());
            }
            return m17682c().m17665a(new HeaderElement[]{c3158w.m17688a(buffer, parserCursor)}, cookieOrigin);
        }
    }

    public String toString() {
        return C2924b.f14792d;
    }

    public void validate(Cookie cookie, CookieOrigin cookieOrigin) {
        C3234a.m17886a((Object) cookie, "Cookie");
        C3234a.m17886a((Object) cookieOrigin, "Cookie origin");
        if (cookie.getVersion() <= 0) {
            m17682c().validate(cookie, cookieOrigin);
        } else if (cookie instanceof SetCookie2) {
            m17680a().validate(cookie, cookieOrigin);
        } else {
            m17681b().validate(cookie, cookieOrigin);
        }
    }
}
