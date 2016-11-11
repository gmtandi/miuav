package org.p122a.p123a.p167i.p170c;

import org.apache.http.HttpMessage;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseFactory;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.message.LineParser;
import org.apache.http.params.HttpParams;
import org.apache.http.util.CharArrayBuffer;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p162e.C2998d;
import org.p122a.p123a.p167i.C3206i;
import org.p122a.p123a.p167i.p172g.C3112a;
import org.p122a.p123a.p180o.C3234a;

@C2913c
/* renamed from: org.a.a.i.c.g */
public class C3113g extends C3112a<HttpResponse> {
    private static final String f15411b = "HttpClient";
    private final HttpResponseFactory f15412c;
    private final CharArrayBuffer f15413d;

    public C3113g(SessionInputBuffer sessionInputBuffer) {
        this(sessionInputBuffer, null, null, C2998d.f14971a);
    }

    public C3113g(SessionInputBuffer sessionInputBuffer, C2998d c2998d) {
        this(sessionInputBuffer, null, null, c2998d);
    }

    public C3113g(SessionInputBuffer sessionInputBuffer, LineParser lineParser, HttpResponseFactory httpResponseFactory, C2998d c2998d) {
        super(sessionInputBuffer, lineParser, c2998d);
        if (httpResponseFactory == null) {
            httpResponseFactory = C3206i.f15649a;
        }
        this.f15412c = httpResponseFactory;
        this.f15413d = new CharArrayBuffer(SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
    }

    @Deprecated
    public C3113g(SessionInputBuffer sessionInputBuffer, LineParser lineParser, HttpResponseFactory httpResponseFactory, HttpParams httpParams) {
        super(sessionInputBuffer, lineParser, httpParams);
        C3234a.m17886a((Object) httpResponseFactory, "Response factory");
        this.f15412c = httpResponseFactory;
        this.f15413d = new CharArrayBuffer(SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected org.apache.http.HttpResponse m17568a(org.apache.http.io.SessionInputBuffer r8) {
        /*
        r7 = this;
        r1 = 0;
        r6 = -1;
        r0 = r1;
    L_0x0003:
        r2 = r7.f15413d;
        r2.clear();
        r2 = r7.f15413d;
        r2 = r8.readLine(r2);
        if (r2 != r6) goto L_0x001a;
    L_0x0010:
        if (r0 != 0) goto L_0x001a;
    L_0x0012:
        r0 = new org.apache.http.NoHttpResponseException;
        r1 = "The target server failed to respond";
        r0.<init>(r1);
        throw r0;
    L_0x001a:
        r3 = new org.apache.http.message.ParserCursor;
        r4 = r7.f15413d;
        r4 = r4.length();
        r3.<init>(r1, r4);
        r4 = r7.a;
        r5 = r7.f15413d;
        r4 = r4.hasProtocolVersion(r5, r3);
        if (r4 == 0) goto L_0x003f;
    L_0x002f:
        r0 = r7.a;
        r1 = r7.f15413d;
        r0 = r0.parseStatusLine(r1, r3);
        r1 = r7.f15412c;
        r2 = 0;
        r0 = r1.newHttpResponse(r0, r2);
        return r0;
    L_0x003f:
        if (r2 == r6) goto L_0x0049;
    L_0x0041:
        r2 = r7.f15413d;
        r2 = r7.m17569a(r2, r0);
        if (r2 == 0) goto L_0x0051;
    L_0x0049:
        r0 = new org.apache.http.ProtocolException;
        r1 = "The server failed to respond with a valid HTTP response";
        r0.<init>(r1);
        throw r0;
    L_0x0051:
        r2 = "HttpClient";
        r3 = 3;
        r2 = android.util.Log.isLoggable(r2, r3);
        if (r2 == 0) goto L_0x0078;
    L_0x005a:
        r2 = "HttpClient";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "Garbage in response: ";
        r3 = r3.append(r4);
        r4 = r7.f15413d;
        r4 = r4.toString();
        r3 = r3.append(r4);
        r3 = r3.toString();
        android.util.Log.d(r2, r3);
    L_0x0078:
        r0 = r0 + 1;
        goto L_0x0003;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.a.a.i.c.g.a(org.apache.http.io.SessionInputBuffer):org.apache.http.HttpResponse");
    }

    protected boolean m17569a(CharArrayBuffer charArrayBuffer, int i) {
        return false;
    }

    protected /* synthetic */ HttpMessage m17570b(SessionInputBuffer sessionInputBuffer) {
        return m17568a(sessionInputBuffer);
    }
}
