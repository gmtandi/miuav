package org.p122a.p123a.p167i.p172g;

import org.apache.http.HttpMessage;
import org.apache.http.HttpRequest;
import org.apache.http.io.SessionOutputBuffer;
import org.apache.http.message.LineFormatter;
import org.p122a.p123a.p150a.C2913c;

@C2913c
/* renamed from: org.a.a.i.g.i */
public class C3189i extends C3182b<HttpRequest> {
    public C3189i(SessionOutputBuffer sessionOutputBuffer) {
        this(sessionOutputBuffer, null);
    }

    public C3189i(SessionOutputBuffer sessionOutputBuffer, LineFormatter lineFormatter) {
        super(sessionOutputBuffer, lineFormatter);
    }

    protected void m17742a(HttpMessage httpMessage) {
        this.c.formatRequestLine(this.b, ((HttpRequest) httpMessage).getRequestLine());
        this.a.writeLine(this.b);
    }
}
