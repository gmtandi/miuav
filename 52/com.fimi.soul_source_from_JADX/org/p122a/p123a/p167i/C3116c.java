package org.p122a.p123a.p167i;

import it.p074a.p075a.C2799f;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpMessage;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.io.HttpMessageParser;
import org.apache.http.io.HttpMessageWriter;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p162e.C2998d;
import org.p122a.p123a.p167i.p172g.C3191j;
import org.p122a.p123a.p167i.p172g.C3193l;
import org.p122a.p123a.p173j.C3114b;
import org.p122a.p123a.p173j.C3190c;
import org.p122a.p123a.p180o.C3234a;

@C2913c
/* renamed from: org.a.a.i.c */
public class C3116c extends C3074a implements HttpClientConnection {
    private final HttpMessageParser f15417a;
    private final HttpMessageWriter f15418b;

    public C3116c(int i) {
        this(i, i, null, null, null, null, null, null, null);
    }

    public C3116c(int i, int i2, CharsetDecoder charsetDecoder, CharsetEncoder charsetEncoder, C2998d c2998d, ContentLengthStrategy contentLengthStrategy, ContentLengthStrategy contentLengthStrategy2, C3190c<HttpRequest> c3190c, C3114b<HttpResponse> c3114b) {
        C3190c c3190c2;
        C3114b c3114b2;
        super(i, i2, charsetDecoder, charsetEncoder, c2998d, contentLengthStrategy, contentLengthStrategy2);
        if (c3190c == null) {
            c3190c2 = C3191j.f15608a;
        }
        this.f15418b = c3190c2.m17743a(m17305f());
        if (c3114b == null) {
            c3114b2 = C3193l.f15612a;
        }
        this.f15417a = c3114b2.m17571a(m17304e(), c2998d);
    }

    public C3116c(int i, CharsetDecoder charsetDecoder, CharsetEncoder charsetEncoder, C2998d c2998d) {
        this(i, i, charsetDecoder, charsetEncoder, c2998d, null, null, null, null);
    }

    public void m17573a(Socket socket) {
        super.m17297a(socket);
    }

    protected void m17574a(HttpRequest httpRequest) {
    }

    protected void m17575a(HttpResponse httpResponse) {
    }

    public void flush() {
        m17303d();
        m17306g();
    }

    public boolean isResponseAvailable(int i) {
        m17303d();
        try {
            return m17298a(i);
        } catch (SocketTimeoutException e) {
            return false;
        }
    }

    public void receiveResponseEntity(HttpResponse httpResponse) {
        C3234a.m17886a((Object) httpResponse, "HTTP response");
        m17303d();
        httpResponse.setEntity(m17301b((HttpMessage) httpResponse));
    }

    public HttpResponse receiveResponseHeader() {
        m17303d();
        HttpResponse httpResponse = (HttpResponse) this.f15417a.parse();
        m17575a(httpResponse);
        if (httpResponse.getStatusLine().getStatusCode() >= C2799f.f14282t) {
            m17308i();
        }
        return httpResponse;
    }

    public void sendRequestEntity(HttpEntityEnclosingRequest httpEntityEnclosingRequest) {
        C3234a.m17886a((Object) httpEntityEnclosingRequest, "HTTP request");
        m17303d();
        HttpEntity entity = httpEntityEnclosingRequest.getEntity();
        if (entity != null) {
            OutputStream a = m17296a((HttpMessage) httpEntityEnclosingRequest);
            entity.writeTo(a);
            a.close();
        }
    }

    public void sendRequestHeader(HttpRequest httpRequest) {
        C3234a.m17886a((Object) httpRequest, "HTTP request");
        m17303d();
        this.f15418b.write(httpRequest);
        m17574a(httpRequest);
        m17307h();
    }
}
