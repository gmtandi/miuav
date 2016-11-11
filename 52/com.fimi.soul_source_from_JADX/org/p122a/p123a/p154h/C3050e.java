package org.p122a.p123a.p154h;

import com.baidu.tts.loopj.RequestParams;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Locale;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.message.ParserCursor;
import org.apache.http.util.CharArrayBuffer;
import org.p122a.p123a.C2922b;
import org.p122a.p123a.p124f.p125c.C3022o;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p152c.p161f.C2989l;
import org.p122a.p123a.p178k.C3209a;
import org.p122a.p123a.p178k.C3210b;
import org.p122a.p123a.p180o.C3234a;
import org.p122a.p123a.p180o.C3239f;

@C2912b
/* renamed from: org.a.a.h.e */
public final class C3050e implements Serializable {
    public static final C3050e f15101a;
    public static final C3050e f15102b;
    public static final C3050e f15103c;
    public static final C3050e f15104d;
    public static final C3050e f15105e;
    public static final C3050e f15106f;
    public static final C3050e f15107g;
    public static final C3050e f15108h;
    public static final C3050e f15109i;
    public static final C3050e f15110j;
    public static final C3050e f15111k;
    public static final C3050e f15112l;
    public static final C3050e f15113m;
    public static final C3050e f15114n;
    private static final long serialVersionUID = -7768694718232371896L;
    private final String f15115o;
    private final Charset f15116p;
    private final NameValuePair[] f15117q;

    static {
        f15101a = C3050e.m17157a("application/atom+xml", C2922b.f14783g);
        f15102b = C3050e.m17157a(C2989l.f14939a, C2922b.f14783g);
        f15103c = C3050e.m17157a(RequestParams.APPLICATION_JSON, C2922b.f14781e);
        f15104d = C3050e.m17157a(RequestParams.APPLICATION_OCTET_STREAM, (Charset) null);
        f15105e = C3050e.m17157a("application/svg+xml", C2922b.f14783g);
        f15106f = C3050e.m17157a("application/xhtml+xml", C2922b.f14783g);
        f15107g = C3050e.m17157a("application/xml", C2922b.f14783g);
        f15108h = C3050e.m17157a("multipart/form-data", C2922b.f14783g);
        f15109i = C3050e.m17157a("text/html", C2922b.f14783g);
        f15110j = C3050e.m17157a("text/plain", C2922b.f14783g);
        f15111k = C3050e.m17157a("text/xml", C2922b.f14783g);
        f15112l = C3050e.m17157a("*/*", (Charset) null);
        f15113m = f15110j;
        f15114n = f15104d;
    }

    C3050e(String str, Charset charset) {
        this.f15115o = str;
        this.f15116p = charset;
        this.f15117q = null;
    }

    C3050e(String str, NameValuePair[] nameValuePairArr) {
        this.f15115o = str;
        this.f15117q = nameValuePairArr;
        Object a = m17165a("charset");
        this.f15116p = !C3239f.m17911b(a) ? Charset.forName(a) : null;
    }

    public static C3050e m17156a(String str, String str2) {
        return C3050e.m17157a(str, !C3239f.m17911b(str2) ? Charset.forName(str2) : null);
    }

    public static C3050e m17157a(String str, Charset charset) {
        String toLowerCase = ((String) C3234a.m17892b((CharSequence) str, "MIME type")).toLowerCase(Locale.US);
        C3234a.m17888a(C3050e.m17163e(toLowerCase), "MIME type may not contain reserved characters");
        return new C3050e(toLowerCase, charset);
    }

    private static C3050e m17158a(HeaderElement headerElement) {
        String name = headerElement.getName();
        NameValuePair[] parameters = headerElement.getParameters();
        if (parameters == null || parameters.length <= 0) {
            parameters = null;
        }
        return new C3050e(name, parameters);
    }

    public static C3050e m17159a(HttpEntity httpEntity) {
        if (httpEntity == null) {
            return null;
        }
        Header contentType = httpEntity.getContentType();
        if (contentType == null) {
            return null;
        }
        HeaderElement[] elements = contentType.getElements();
        return elements.length > 0 ? C3050e.m17158a(elements[0]) : null;
    }

    public static C3050e m17160b(String str) {
        return new C3050e(str, (Charset) null);
    }

    public static C3050e m17161b(HttpEntity httpEntity) {
        C3050e a = C3050e.m17159a(httpEntity);
        return a != null ? a : f15113m;
    }

    public static C3050e m17162c(String str) {
        C3234a.m17886a((Object) str, "Content type");
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(str.length());
        charArrayBuffer.append(str);
        HeaderElement[] parseElements = C3210b.f15659b.parseElements(charArrayBuffer, new ParserCursor(0, str.length()));
        if (parseElements.length > 0) {
            return C3050e.m17158a(parseElements[0]);
        }
        throw new ParseException("Invalid content type: " + str);
    }

    private static boolean m17163e(String str) {
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == C3022o.f15057e || charAt == ',' || charAt == ';') {
                return false;
            }
        }
        return true;
    }

    public String m17164a() {
        return this.f15115o;
    }

    public String m17165a(String str) {
        C3234a.m17885a((CharSequence) str, "Parameter name");
        if (this.f15117q == null) {
            return null;
        }
        for (NameValuePair nameValuePair : this.f15117q) {
            if (nameValuePair.getName().equalsIgnoreCase(str)) {
                return nameValuePair.getValue();
            }
        }
        return null;
    }

    public C3050e m17166a(Charset charset) {
        return C3050e.m17157a(m17164a(), charset);
    }

    public Charset m17167b() {
        return this.f15116p;
    }

    public C3050e m17168d(String str) {
        return C3050e.m17156a(m17164a(), str);
    }

    public String toString() {
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(64);
        charArrayBuffer.append(this.f15115o);
        if (this.f15117q != null) {
            charArrayBuffer.append("; ");
            C3209a.f15655b.formatParameters(charArrayBuffer, this.f15117q, false);
        } else if (this.f15116p != null) {
            charArrayBuffer.append("; charset=");
            charArrayBuffer.append(this.f15116p.name());
        }
        return charArrayBuffer.toString();
    }
}
