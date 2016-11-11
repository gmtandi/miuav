package org.p122a.p123a.p167i.p172g;

import org.apache.http.HttpResponse;
import org.apache.http.io.HttpMessageWriter;
import org.apache.http.io.SessionOutputBuffer;
import org.apache.http.message.LineFormatter;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p173j.C3190c;
import org.p122a.p123a.p178k.C3211c;

@C2912b
/* renamed from: org.a.a.i.g.n */
public class C3195n implements C3190c<HttpResponse> {
    public static final C3195n f15615a;
    private final LineFormatter f15616b;

    static {
        f15615a = new C3195n();
    }

    public C3195n() {
        this(null);
    }

    public C3195n(LineFormatter lineFormatter) {
        if (lineFormatter == null) {
            lineFormatter = C3211c.f15664b;
        }
        this.f15616b = lineFormatter;
    }

    public HttpMessageWriter m17749a(SessionOutputBuffer sessionOutputBuffer) {
        return new C3194m(sessionOutputBuffer, this.f15616b);
    }
}
