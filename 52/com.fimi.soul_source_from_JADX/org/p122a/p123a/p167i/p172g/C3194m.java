package org.p122a.p123a.p167i.p172g;

import org.apache.http.HttpMessage;
import org.apache.http.HttpResponse;
import org.apache.http.io.SessionOutputBuffer;
import org.apache.http.message.LineFormatter;
import org.p122a.p123a.p150a.C2913c;

@C2913c
/* renamed from: org.a.a.i.g.m */
public class C3194m extends C3182b<HttpResponse> {
    public C3194m(SessionOutputBuffer sessionOutputBuffer) {
        super(sessionOutputBuffer, null);
    }

    public C3194m(SessionOutputBuffer sessionOutputBuffer, LineFormatter lineFormatter) {
        super(sessionOutputBuffer, lineFormatter);
    }

    protected void m17748a(HttpMessage httpMessage) {
        this.c.formatStatusLine(this.b, ((HttpResponse) httpMessage).getStatusLine());
        this.a.writeLine(this.b);
    }
}
