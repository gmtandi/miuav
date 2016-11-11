package org.p122a.p123a.p167i.p168a;

import java.util.Locale;
import org.apache.http.FormattedHeader;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.CharArrayBuffer;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p151b.C2920e;
import org.p122a.p123a.p151b.C2921f;
import org.p122a.p123a.p180o.C3234a;

@C2913c
/* renamed from: org.a.a.i.a.a */
public abstract class C3054a implements C2921f {
    private C2920e f15123a;

    @Deprecated
    public C3054a(C2920e c2920e) {
        this.f15123a = c2920e;
    }

    public Header m17170a(Credentials credentials, HttpRequest httpRequest, HttpContext httpContext) {
        return authenticate(credentials, httpRequest);
    }

    protected abstract void m17171a(CharArrayBuffer charArrayBuffer, int i, int i2);

    public boolean m17172a() {
        return this.f15123a != null && this.f15123a == C2920e.PROXY;
    }

    public C2920e m17173b() {
        return this.f15123a;
    }

    public void processChallenge(Header header) {
        CharArrayBuffer buffer;
        int valuePos;
        C3234a.m17886a((Object) header, "Header");
        String name = header.getName();
        if (name.equalsIgnoreCase(C3004e.ac)) {
            this.f15123a = C2920e.TARGET;
        } else if (name.equalsIgnoreCase(C3004e.f15001M)) {
            this.f15123a = C2920e.PROXY;
        } else {
            throw new MalformedChallengeException("Unexpected header name: " + name);
        }
        if (header instanceof FormattedHeader) {
            buffer = ((FormattedHeader) header).getBuffer();
            valuePos = ((FormattedHeader) header).getValuePos();
        } else {
            name = header.getValue();
            if (name == null) {
                throw new MalformedChallengeException("Header value is null");
            }
            buffer = new CharArrayBuffer(name.length());
            buffer.append(name);
            valuePos = 0;
        }
        while (valuePos < buffer.length() && HTTP.isWhitespace(buffer.charAt(valuePos))) {
            valuePos++;
        }
        int i = valuePos;
        while (i < buffer.length() && !HTTP.isWhitespace(buffer.charAt(i))) {
            i++;
        }
        name = buffer.substring(valuePos, i);
        if (name.equalsIgnoreCase(getSchemeName())) {
            m17171a(buffer, i, buffer.length());
            return;
        }
        throw new MalformedChallengeException("Invalid scheme identifier: " + name);
    }

    public String toString() {
        String schemeName = getSchemeName();
        return schemeName != null ? schemeName.toUpperCase(Locale.ENGLISH) : super.toString();
    }
}
