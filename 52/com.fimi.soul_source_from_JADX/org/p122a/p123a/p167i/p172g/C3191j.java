package org.p122a.p123a.p167i.p172g;

import org.apache.http.HttpRequest;
import org.apache.http.io.HttpMessageWriter;
import org.apache.http.io.SessionOutputBuffer;
import org.apache.http.message.LineFormatter;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p173j.C3190c;
import org.p122a.p123a.p178k.C3211c;

@C2912b
/* renamed from: org.a.a.i.g.j */
public class C3191j implements C3190c<HttpRequest> {
    public static final C3191j f15608a;
    private final LineFormatter f15609b;

    static {
        f15608a = new C3191j();
    }

    public C3191j() {
        this(null);
    }

    public C3191j(LineFormatter lineFormatter) {
        if (lineFormatter == null) {
            lineFormatter = C3211c.f15664b;
        }
        this.f15609b = lineFormatter;
    }

    public HttpMessageWriter m17744a(SessionOutputBuffer sessionOutputBuffer) {
        return new C3189i(sessionOutputBuffer, this.f15609b);
    }
}
