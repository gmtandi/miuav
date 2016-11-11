package org.p122a.p123a.p167i.p172g;

import org.apache.http.HeaderIterator;
import org.apache.http.HttpMessage;
import org.apache.http.io.HttpMessageWriter;
import org.apache.http.io.SessionOutputBuffer;
import org.apache.http.message.LineFormatter;
import org.apache.http.params.HttpParams;
import org.apache.http.util.CharArrayBuffer;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p178k.C3211c;
import org.p122a.p123a.p180o.C3234a;

@C2913c
/* renamed from: org.a.a.i.g.b */
public abstract class C3182b<T extends HttpMessage> implements HttpMessageWriter {
    protected final SessionOutputBuffer f15574a;
    protected final CharArrayBuffer f15575b;
    protected final LineFormatter f15576c;

    public C3182b(SessionOutputBuffer sessionOutputBuffer, LineFormatter lineFormatter) {
        this.f15574a = (SessionOutputBuffer) C3234a.m17886a((Object) sessionOutputBuffer, "Session input buffer");
        if (lineFormatter == null) {
            lineFormatter = C3211c.f15664b;
        }
        this.f15576c = lineFormatter;
        this.f15575b = new CharArrayBuffer(SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
    }

    @Deprecated
    public C3182b(SessionOutputBuffer sessionOutputBuffer, LineFormatter lineFormatter, HttpParams httpParams) {
        C3234a.m17886a((Object) sessionOutputBuffer, "Session input buffer");
        this.f15574a = sessionOutputBuffer;
        this.f15575b = new CharArrayBuffer(SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
        if (lineFormatter == null) {
            lineFormatter = C3211c.f15664b;
        }
        this.f15576c = lineFormatter;
    }

    protected abstract void m17730a(HttpMessage httpMessage);

    public void write(HttpMessage httpMessage) {
        C3234a.m17886a((Object) httpMessage, "HTTP message");
        m17730a(httpMessage);
        HeaderIterator headerIterator = httpMessage.headerIterator();
        while (headerIterator.hasNext()) {
            this.f15574a.writeLine(this.f15576c.formatHeader(this.f15575b, headerIterator.nextHeader()));
        }
        this.f15575b.clear();
        this.f15574a.writeLine(this.f15575b);
    }
}
