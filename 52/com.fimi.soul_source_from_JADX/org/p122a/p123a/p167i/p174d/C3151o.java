package org.p122a.p123a.p167i.p174d;

import com.xiaomi.market.sdk.C2537j;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.http.FormattedHeader;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.message.BasicHeaderElement;
import org.apache.http.message.BufferedHeader;
import org.apache.http.message.ParserCursor;
import org.apache.http.util.CharArrayBuffer;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p152c.p153a.C2924b;
import org.p122a.p123a.p152c.p161f.C2979b;
import org.p122a.p123a.p178k.C3209a;
import org.p122a.p123a.p180o.C3234a;

@C2913c
/* renamed from: org.a.a.i.d.o */
public class C3151o extends C3138s {
    private static final String[] f15497a;
    private final String[] f15498b;

    static {
        f15497a = new String[]{C2979b.f14907a, C2979b.f14908b, C2979b.f14909c, "EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z", "EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z", "EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z", "EEE dd MMM yy HH:mm:ss z", "EEE,dd-MMM-yy HH:mm:ss z", "EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z"};
    }

    public C3151o() {
        this(null, C3150n.SECURITYLEVEL_DEFAULT);
    }

    public C3151o(String[] strArr) {
        this(strArr, C3150n.SECURITYLEVEL_DEFAULT);
    }

    public C3151o(String[] strArr, C3150n c3150n) {
        if (strArr != null) {
            this.f15498b = (String[]) strArr.clone();
        } else {
            this.f15498b = f15497a;
        }
        switch (C3153q.f15500a[c3150n.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                m17661a("path", new C3145i());
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                m17661a("path", new C3152p(this));
                break;
            default:
                throw new RuntimeException("Unknown security level");
        }
        m17661a("domain", new C3142f());
        m17661a("max-age", new C3144h());
        m17661a("secure", new C3146j());
        m17661a("comment", new C3141e());
        m17661a("expires", new C3143g(this.f15498b));
        m17661a(C2537j.aq, new C3154r());
    }

    private static boolean m17684c(String str) {
        return str != null && str.startsWith("\"") && str.endsWith("\"");
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
            String name = cookie.getName();
            String value = cookie.getValue();
            if (cookie.getVersion() <= 0 || C3151o.m17684c(value)) {
                charArrayBuffer.append(name);
                charArrayBuffer.append("=");
                if (value != null) {
                    charArrayBuffer.append(value);
                }
            } else {
                C3209a.f15655b.formatHeaderElement(charArrayBuffer, new BasicHeaderElement(name, value), false);
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
            HeaderElement[] headerElementArr;
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
            if (i != 0 || r0 == 0) {
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
                headerElementArr = new HeaderElement[]{c3158w.m17688a(buffer, parserCursor)};
            } else {
                headerElementArr = elements;
            }
            return m17665a(headerElementArr, cookieOrigin);
        }
        throw new MalformedCookieException("Unrecognized cookie header '" + header.toString() + "'");
    }

    public String toString() {
        return C2924b.f14789a;
    }
}
