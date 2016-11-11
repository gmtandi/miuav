package org.p122a.p123a.p167i.p172g;

import org.apache.http.HttpMessage;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseFactory;
import org.apache.http.NoHttpResponseException;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.message.LineParser;
import org.apache.http.message.ParserCursor;
import org.apache.http.params.HttpParams;
import org.apache.http.util.CharArrayBuffer;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p162e.C2998d;
import org.p122a.p123a.p167i.C3206i;
import org.p122a.p123a.p180o.C3234a;

@C2913c
/* renamed from: org.a.a.i.g.k */
public class C3192k extends C3112a<HttpResponse> {
    private final HttpResponseFactory f15610b;
    private final CharArrayBuffer f15611c;

    public C3192k(SessionInputBuffer sessionInputBuffer) {
        this(sessionInputBuffer, null, null, C2998d.f14971a);
    }

    public C3192k(SessionInputBuffer sessionInputBuffer, C2998d c2998d) {
        this(sessionInputBuffer, null, null, c2998d);
    }

    public C3192k(SessionInputBuffer sessionInputBuffer, LineParser lineParser, HttpResponseFactory httpResponseFactory, C2998d c2998d) {
        super(sessionInputBuffer, lineParser, c2998d);
        if (httpResponseFactory == null) {
            httpResponseFactory = C3206i.f15649a;
        }
        this.f15610b = httpResponseFactory;
        this.f15611c = new CharArrayBuffer(SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
    }

    @Deprecated
    public C3192k(SessionInputBuffer sessionInputBuffer, LineParser lineParser, HttpResponseFactory httpResponseFactory, HttpParams httpParams) {
        super(sessionInputBuffer, lineParser, httpParams);
        this.f15610b = (HttpResponseFactory) C3234a.m17886a((Object) httpResponseFactory, "Response factory");
        this.f15611c = new CharArrayBuffer(SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
    }

    protected HttpResponse m17745a(SessionInputBuffer sessionInputBuffer) {
        this.f15611c.clear();
        if (sessionInputBuffer.readLine(this.f15611c) == -1) {
            throw new NoHttpResponseException("The target server failed to respond");
        }
        return this.f15610b.newHttpResponse(this.a.parseStatusLine(this.f15611c, new ParserCursor(0, this.f15611c.length())), null);
    }

    protected /* synthetic */ HttpMessage m17746b(SessionInputBuffer sessionInputBuffer) {
        return m17745a(sessionInputBuffer);
    }
}
