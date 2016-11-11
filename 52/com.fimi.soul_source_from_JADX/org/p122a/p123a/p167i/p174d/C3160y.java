package org.p122a.p123a.p167i.p174d;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.http.FormattedHeader;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.message.BufferedHeader;
import org.apache.http.message.ParserCursor;
import org.apache.http.util.CharArrayBuffer;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p152c.p153a.C2924b;
import org.p122a.p123a.p180o.C3234a;

@C2913c
/* renamed from: org.a.a.i.d.y */
public class C3160y extends C3138s {
    protected static final String f15503a = "EEE, dd-MMM-yy HH:mm:ss z";
    private final String[] f15504b;

    public C3160y() {
        this(null);
    }

    public C3160y(String[] strArr) {
        if (strArr != null) {
            this.f15504b = (String[]) strArr.clone();
        } else {
            this.f15504b = new String[]{f15503a};
        }
        m17661a("path", new C3145i());
        m17661a("domain", new C3157v());
        m17661a("max-age", new C3144h());
        m17661a("secure", new C3146j());
        m17661a("comment", new C3141e());
        m17661a("expires", new C3143g(this.f15504b));
    }

    public List<Header> formatCookies(List<Cookie> list) {
        C3234a.m17887a((Collection) list, "List of cookies");
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(list.size() * 20);
        charArrayBuffer.append("Cookie");
        charArrayBuffer.append(": ");
        for (int i = 0; i < list.size(); i++) {
            Cookie cookie = (Cookie) list.get(i);
            if (i > 0) {
                charArrayBuffer.append("; ");
            }
            charArrayBuffer.append(cookie.getName());
            String value = cookie.getValue();
            if (value != null) {
                charArrayBuffer.append("=");
                charArrayBuffer.append(value);
            }
        }
        List<Header> arrayList = new ArrayList(1);
        arrayList.add(new BufferedHeader(charArrayBuffer));
        return arrayList;
    }

    public int getVersion() {
        return 0;
    }

    public Header getVersionHeader() {
        return null;
    }

    public List<Cookie> parse(Header header, CookieOrigin cookieOrigin) {
        C3234a.m17886a((Object) header, "Header");
        C3234a.m17886a((Object) cookieOrigin, "Cookie origin");
        if (header.getName().equalsIgnoreCase("Set-Cookie")) {
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
            return m17665a(new HeaderElement[]{c3158w.m17688a(buffer, parserCursor)}, cookieOrigin);
        }
        throw new MalformedCookieException("Unrecognized cookie header '" + header.toString() + "'");
    }

    public String toString() {
        return C2924b.f14790b;
    }
}
