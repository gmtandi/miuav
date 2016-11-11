package org.p122a.p123a.p167i.p168a;

import android.util.Base64;
import java.nio.charset.Charset;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.auth.Credentials;
import org.apache.http.message.BufferedHeader;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EncodingUtils;
import org.p122a.p123a.C2922b;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p151b.C2920e;
import org.p122a.p123a.p159n.C3223a;
import org.p122a.p123a.p180o.C3234a;

@C2913c
/* renamed from: org.a.a.i.a.c */
public class C3057c extends C3056t {
    private boolean f15127a;

    public C3057c() {
        this(C2922b.f14782f);
    }

    public C3057c(Charset charset) {
        super(charset);
        this.f15127a = false;
    }

    @Deprecated
    public C3057c(C2920e c2920e) {
        super(c2920e);
    }

    @Deprecated
    public static Header m17179a(Credentials credentials, String str, boolean z) {
        C3234a.m17886a((Object) credentials, "Credentials");
        C3234a.m17886a((Object) str, "charset");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(credentials.getUserPrincipal().getName());
        stringBuilder.append(":");
        stringBuilder.append(credentials.getPassword() == null ? "null" : credentials.getPassword());
        byte[] encode = Base64.encode(EncodingUtils.getBytes(stringBuilder.toString(), str), 2);
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(32);
        if (z) {
            charArrayBuffer.append(C3004e.f15002N);
        } else {
            charArrayBuffer.append(C3004e.f15022h);
        }
        charArrayBuffer.append(": Basic ");
        charArrayBuffer.append(encode, 0, encode.length);
        return new BufferedHeader(charArrayBuffer);
    }

    public Header m17180a(Credentials credentials, HttpRequest httpRequest, HttpContext httpContext) {
        C3234a.m17886a((Object) credentials, "Credentials");
        C3234a.m17886a((Object) httpRequest, "HTTP request");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(credentials.getUserPrincipal().getName());
        stringBuilder.append(":");
        stringBuilder.append(credentials.getPassword() == null ? "null" : credentials.getPassword());
        byte[] encode = Base64.encode(EncodingUtils.getBytes(stringBuilder.toString(), m17175a(httpRequest)), 2);
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(32);
        if (m17172a()) {
            charArrayBuffer.append(C3004e.f15002N);
        } else {
            charArrayBuffer.append(C3004e.f15022h);
        }
        charArrayBuffer.append(": Basic ");
        charArrayBuffer.append(encode, 0, encode.length);
        return new BufferedHeader(charArrayBuffer);
    }

    @Deprecated
    public Header authenticate(Credentials credentials, HttpRequest httpRequest) {
        return m17180a(credentials, httpRequest, new C3223a());
    }

    public String getSchemeName() {
        return "basic";
    }

    public boolean isComplete() {
        return this.f15127a;
    }

    public boolean isConnectionBased() {
        return false;
    }

    public void processChallenge(Header header) {
        super.processChallenge(header);
        this.f15127a = true;
    }
}
