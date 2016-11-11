package org.p122a.p123a.p167i.p168a;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.http.HeaderElement;
import org.apache.http.HttpRequest;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.message.ParserCursor;
import org.apache.http.util.CharArrayBuffer;
import org.p122a.p123a.C2922b;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p151b.C2920e;
import org.p122a.p123a.p178k.C3210b;

@C2913c
/* renamed from: org.a.a.i.a.t */
public abstract class C3056t extends C3054a {
    private final Map<String, String> f15125a;
    private final Charset f15126b;

    public C3056t() {
        this(C2922b.f14782f);
    }

    public C3056t(Charset charset) {
        this.f15125a = new HashMap();
        if (charset == null) {
            charset = C2922b.f14782f;
        }
        this.f15126b = charset;
    }

    @Deprecated
    public C3056t(C2920e c2920e) {
        super(c2920e);
        this.f15125a = new HashMap();
        this.f15126b = C2922b.f14782f;
    }

    String m17175a(HttpRequest httpRequest) {
        String str = (String) httpRequest.getParams().getParameter("http.auth.credential-charset");
        return str == null ? m17177g().name() : str;
    }

    protected void m17176a(CharArrayBuffer charArrayBuffer, int i, int i2) {
        HeaderElement[] parseElements = C3210b.f15659b.parseElements(charArrayBuffer, new ParserCursor(i, charArrayBuffer.length()));
        if (parseElements.length == 0) {
            throw new MalformedChallengeException("Authentication challenge is empty");
        }
        this.f15125a.clear();
        for (HeaderElement headerElement : parseElements) {
            this.f15125a.put(headerElement.getName().toLowerCase(Locale.ENGLISH), headerElement.getValue());
        }
    }

    public Charset m17177g() {
        return this.f15126b;
    }

    public String getParameter(String str) {
        return str == null ? null : (String) this.f15125a.get(str.toLowerCase(Locale.ENGLISH));
    }

    public String getRealm() {
        return getParameter("realm");
    }

    protected Map<String, String> m17178h() {
        return this.f15125a;
    }
}
