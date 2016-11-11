package org.p122a.p123a.p167i.p172g;

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
/* renamed from: org.a.a.i.g.l */
public class C3193l implements C3114b<HttpResponse> {
    public static final C3193l f15612a;
    private final LineParser f15613b;
    private final HttpResponseFactory f15614c;

    static {
        f15612a = new C3193l();
    }

    public C3193l() {
        this(null, null);
    }

    public C3193l(LineParser lineParser, HttpResponseFactory httpResponseFactory) {
        if (lineParser == null) {
            lineParser = C3212d.f15666b;
        }
        this.f15613b = lineParser;
        if (httpResponseFactory == null) {
            httpResponseFactory = C3206i.f15649a;
        }
        this.f15614c = httpResponseFactory;
    }

    public HttpMessageParser m17747a(SessionInputBuffer sessionInputBuffer, C2998d c2998d) {
        return new C3192k(sessionInputBuffer, this.f15613b, this.f15614c, c2998d);
    }
}
