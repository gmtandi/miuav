package org.p122a.p123a.p167i.p170c;

import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseFactory;
import org.apache.http.io.HttpMessageParser;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.message.LineParser;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p162e.C2998d;
import org.p122a.p123a.p167i.C3206i;
import org.p122a.p123a.p173j.C3114b;
import org.p122a.p123a.p178k.C3212d;

@C2912b
/* renamed from: org.a.a.i.c.h */
public class C3115h implements C3114b<HttpResponse> {
    public static final C3115h f15414a;
    private final LineParser f15415b;
    private final HttpResponseFactory f15416c;

    static {
        f15414a = new C3115h();
    }

    public C3115h() {
        this(null, null);
    }

    public C3115h(HttpResponseFactory httpResponseFactory) {
        this(null, httpResponseFactory);
    }

    public C3115h(LineParser lineParser, HttpResponseFactory httpResponseFactory) {
        if (lineParser == null) {
            lineParser = C3212d.f15666b;
        }
        this.f15415b = lineParser;
        if (httpResponseFactory == null) {
            httpResponseFactory = C3206i.f15649a;
        }
        this.f15416c = httpResponseFactory;
    }

    public HttpMessageParser m17572a(SessionInputBuffer sessionInputBuffer, C2998d c2998d) {
        return new C3113g(sessionInputBuffer, this.f15415b, this.f15416c, c2998d);
    }
}
