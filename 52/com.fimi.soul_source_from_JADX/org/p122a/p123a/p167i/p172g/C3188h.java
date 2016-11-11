package org.p122a.p123a.p167i.p172g;

import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestFactory;
import org.apache.http.io.HttpMessageParser;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.message.LineParser;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p162e.C2998d;
import org.p122a.p123a.p167i.C3205h;
import org.p122a.p123a.p173j.C3114b;
import org.p122a.p123a.p178k.C3212d;

@C2912b
/* renamed from: org.a.a.i.g.h */
public class C3188h implements C3114b<HttpRequest> {
    public static final C3188h f15605a;
    private final LineParser f15606b;
    private final HttpRequestFactory f15607c;

    static {
        f15605a = new C3188h();
    }

    public C3188h() {
        this(null, null);
    }

    public C3188h(LineParser lineParser, HttpRequestFactory httpRequestFactory) {
        if (lineParser == null) {
            lineParser = C3212d.f15666b;
        }
        this.f15606b = lineParser;
        if (httpRequestFactory == null) {
            httpRequestFactory = C3205h.f15645a;
        }
        this.f15607c = httpRequestFactory;
    }

    public HttpMessageParser m17741a(SessionInputBuffer sessionInputBuffer, C2998d c2998d) {
        return new C3187g(sessionInputBuffer, this.f15606b, this.f15607c, c2998d);
    }
}
