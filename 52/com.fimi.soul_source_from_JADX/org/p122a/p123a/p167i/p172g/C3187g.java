package org.p122a.p123a.p167i.p172g;

import org.apache.http.ConnectionClosedException;
import org.apache.http.HttpMessage;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestFactory;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.message.LineParser;
import org.apache.http.message.ParserCursor;
import org.apache.http.params.HttpParams;
import org.apache.http.util.CharArrayBuffer;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p162e.C2998d;
import org.p122a.p123a.p167i.C3205h;
import org.p122a.p123a.p180o.C3234a;

@C2913c
/* renamed from: org.a.a.i.g.g */
public class C3187g extends C3112a<HttpRequest> {
    private final HttpRequestFactory f15603b;
    private final CharArrayBuffer f15604c;

    public C3187g(SessionInputBuffer sessionInputBuffer) {
        this(sessionInputBuffer, null, null, C2998d.f14971a);
    }

    public C3187g(SessionInputBuffer sessionInputBuffer, C2998d c2998d) {
        this(sessionInputBuffer, null, null, c2998d);
    }

    public C3187g(SessionInputBuffer sessionInputBuffer, LineParser lineParser, HttpRequestFactory httpRequestFactory, C2998d c2998d) {
        super(sessionInputBuffer, lineParser, c2998d);
        if (httpRequestFactory == null) {
            httpRequestFactory = C3205h.f15645a;
        }
        this.f15603b = httpRequestFactory;
        this.f15604c = new CharArrayBuffer(SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
    }

    @Deprecated
    public C3187g(SessionInputBuffer sessionInputBuffer, LineParser lineParser, HttpRequestFactory httpRequestFactory, HttpParams httpParams) {
        super(sessionInputBuffer, lineParser, httpParams);
        this.f15603b = (HttpRequestFactory) C3234a.m17886a((Object) httpRequestFactory, "Request factory");
        this.f15604c = new CharArrayBuffer(SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
    }

    protected HttpRequest m17739a(SessionInputBuffer sessionInputBuffer) {
        this.f15604c.clear();
        if (sessionInputBuffer.readLine(this.f15604c) == -1) {
            throw new ConnectionClosedException("Client closed connection");
        }
        return this.f15603b.newHttpRequest(this.a.parseRequestLine(this.f15604c, new ParserCursor(0, this.f15604c.length())));
    }

    protected /* synthetic */ HttpMessage m17740b(SessionInputBuffer sessionInputBuffer) {
        return m17739a(sessionInputBuffer);
    }
}
