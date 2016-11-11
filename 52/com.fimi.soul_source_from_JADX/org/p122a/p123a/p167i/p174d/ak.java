package org.p122a.p123a.p167i.p174d;

import com.xiaomi.market.sdk.C2537j;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.NameValuePair;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieAttributeHandler;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;
import org.apache.http.message.BufferedHeader;
import org.apache.http.util.CharArrayBuffer;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p180o.C3234a;

@C2913c
/* renamed from: org.a.a.i.d.ak */
public class ak extends ad {
    public ak() {
        this(null, false);
    }

    public ak(String[] strArr, boolean z) {
        super(strArr, z);
        m17661a("domain", new ah());
        m17661a("port", new ai());
        m17661a("commenturl", new af());
        m17661a("discard", new ag());
        m17661a(C2537j.aq, new al());
    }

    private List<Cookie> m17674b(HeaderElement[] headerElementArr, CookieOrigin cookieOrigin) {
        List<Cookie> arrayList = new ArrayList(headerElementArr.length);
        for (HeaderElement headerElement : headerElementArr) {
            String name = headerElement.getName();
            String value = headerElement.getValue();
            if (name == null || name.length() == 0) {
                throw new MalformedCookieException("Cookie name may not be empty");
            }
            SetCookie c3140c = new C3140c(name, value);
            c3140c.setPath(C3138s.m17663a(cookieOrigin));
            c3140c.setDomain(C3138s.m17664b(cookieOrigin));
            c3140c.setPorts(new int[]{cookieOrigin.getPort()});
            NameValuePair[] parameters = headerElement.getParameters();
            Map hashMap = new HashMap(parameters.length);
            for (int length = parameters.length - 1; length >= 0; length--) {
                NameValuePair nameValuePair = parameters[length];
                hashMap.put(nameValuePair.getName().toLowerCase(Locale.ENGLISH), nameValuePair);
            }
            for (Entry value2 : hashMap.entrySet()) {
                NameValuePair nameValuePair2 = (NameValuePair) value2.getValue();
                value = nameValuePair2.getName().toLowerCase(Locale.ENGLISH);
                c3140c.m17678a(value, nameValuePair2.getValue());
                CookieAttributeHandler a = m17660a(value);
                if (a != null) {
                    a.parse(c3140c, nameValuePair2.getValue());
                }
            }
            arrayList.add(c3140c);
        }
        return arrayList;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static org.apache.http.cookie.CookieOrigin m17675c(org.apache.http.cookie.CookieOrigin r6) {
        /*
        r1 = 0;
        r3 = r6.getHost();
        r2 = 1;
        r0 = r1;
    L_0x0007:
        r4 = r3.length();
        if (r0 >= r4) goto L_0x0044;
    L_0x000d:
        r4 = r3.charAt(r0);
        r5 = 46;
        if (r4 == r5) goto L_0x0019;
    L_0x0015:
        r5 = 58;
        if (r4 != r5) goto L_0x0041;
    L_0x0019:
        if (r1 == 0) goto L_0x0040;
    L_0x001b:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r0 = r0.append(r3);
        r1 = ".local";
        r0 = r0.append(r1);
        r1 = r0.toString();
        r0 = new org.apache.http.cookie.CookieOrigin;
        r2 = r6.getPort();
        r3 = r6.getPath();
        r4 = r6.isSecure();
        r0.<init>(r1, r2, r3, r4);
        r6 = r0;
    L_0x0040:
        return r6;
    L_0x0041:
        r0 = r0 + 1;
        goto L_0x0007;
    L_0x0044:
        r1 = r2;
        goto L_0x0019;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.a.a.i.d.ak.c(org.apache.http.cookie.CookieOrigin):org.apache.http.cookie.CookieOrigin");
    }

    protected List<Cookie> m17676a(HeaderElement[] headerElementArr, CookieOrigin cookieOrigin) {
        return m17674b(headerElementArr, ak.m17675c(cookieOrigin));
    }

    protected void m17677a(CharArrayBuffer charArrayBuffer, Cookie cookie, int i) {
        super.m17669a(charArrayBuffer, cookie, i);
        if (cookie instanceof ClientCookie) {
            String attribute = ((ClientCookie) cookie).getAttribute("port");
            if (attribute != null) {
                charArrayBuffer.append("; $Port");
                charArrayBuffer.append("=\"");
                if (attribute.trim().length() > 0) {
                    int[] ports = cookie.getPorts();
                    if (ports != null) {
                        int length = ports.length;
                        for (int i2 = 0; i2 < length; i2++) {
                            if (i2 > 0) {
                                charArrayBuffer.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
                            }
                            charArrayBuffer.append(Integer.toString(ports[i2]));
                        }
                    }
                }
                charArrayBuffer.append("\"");
            }
        }
    }

    public int getVersion() {
        return 1;
    }

    public Header getVersionHeader() {
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(40);
        charArrayBuffer.append("Cookie2");
        charArrayBuffer.append(": ");
        charArrayBuffer.append("$Version=");
        charArrayBuffer.append(Integer.toString(getVersion()));
        return new BufferedHeader(charArrayBuffer);
    }

    public boolean match(Cookie cookie, CookieOrigin cookieOrigin) {
        C3234a.m17886a((Object) cookie, "Cookie");
        C3234a.m17886a((Object) cookieOrigin, "Cookie origin");
        return super.match(cookie, ak.m17675c(cookieOrigin));
    }

    public List<Cookie> parse(Header header, CookieOrigin cookieOrigin) {
        C3234a.m17886a((Object) header, "Header");
        C3234a.m17886a((Object) cookieOrigin, "Cookie origin");
        if (header.getName().equalsIgnoreCase("Set-Cookie2")) {
            return m17674b(header.getElements(), ak.m17675c(cookieOrigin));
        }
        throw new MalformedCookieException("Unrecognized cookie header '" + header.toString() + "'");
    }

    public String toString() {
        return "rfc2965";
    }

    public void validate(Cookie cookie, CookieOrigin cookieOrigin) {
        C3234a.m17886a((Object) cookie, "Cookie");
        C3234a.m17886a((Object) cookieOrigin, "Cookie origin");
        super.validate(cookie, ak.m17675c(cookieOrigin));
    }
}
