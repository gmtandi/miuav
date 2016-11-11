package org.p122a.p123a.p167i;

import it.p074a.p075a.C2799f;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpMessage;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpServerConnection;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.io.HttpMessageParser;
import org.apache.http.io.HttpMessageWriter;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p162e.C2998d;
import org.p122a.p123a.p167i.p172g.C3188h;
import org.p122a.p123a.p167i.p172g.C3195n;
import org.p122a.p123a.p167i.p175e.C3163a;
import org.p122a.p123a.p173j.C3114b;
import org.p122a.p123a.p173j.C3190c;
import org.p122a.p123a.p180o.C3234a;

@C2913c
/* renamed from: org.a.a.i.e */
public class C3166e extends C3074a implements HttpServerConnection {
    private final HttpMessageParser f15520a;
    private final HttpMessageWriter f15521b;

    public C3166e(int i) {
        this(i, i, null, null, null, null, null, null, null);
    }

    public C3166e(int i, int i2, CharsetDecoder charsetDecoder, CharsetEncoder charsetEncoder, C2998d c2998d, ContentLengthStrategy contentLengthStrategy, ContentLengthStrategy contentLengthStrategy2, C3114b<HttpRequest> c3114b, C3190c<HttpResponse> c3190c) {
        super(i, i2, charsetDecoder, charsetEncoder, c2998d, contentLengthStrategy != null ? contentLengthStrategy : C3163a.f15514a, contentLengthStrategy2);
        if (c3114b == null) {
            c3114b = C3188h.f15605a;
        }
        this.f15520a = c3114b.m17571a(m17304e(), c2998d);
        if (c3190c == null) {
            c3190c = C3195n.f15615a;
        }
        this.f15521b = c3190c.m17743a(m17305f());
    }

    public C3166e(int i, CharsetDecoder charsetDecoder, CharsetEncoder charsetEncoder, C2998d c2998d) {
        this(i, i, charsetDecoder, charsetEncoder, c2998d, null, null, null, null);
    }

    public void m17695a(Socket socket) {
        super.m17297a(socket);
    }

    protected void m17696a(HttpRequest httpRequest) {
    }

    protected void m17697a(HttpResponse httpResponse) {
    }

    public void flush() {
        m17303d();
        m17306g();
    }

    public void receiveRequestEntity(HttpEntityEnclosingRequest httpEntityEnclosingRequest) {
        C3234a.m17886a((Object) httpEntityEnclosingRequest, "HTTP request");
        m17303d();
        httpEntityEnclosingRequest.setEntity(m17301b((HttpMessage) httpEntityEnclosingRequest));
    }

    public HttpRequest receiveRequestHeader() {
        m17303d();
        HttpRequest httpRequest = (HttpRequest) this.f15520a.parse();
        m17696a(httpRequest);
        m17307h();
        return httpRequest;
    }

    public void sendResponseEntity(HttpResponse httpResponse) {
        C3234a.m17886a((Object) httpResponse, "HTTP response");
        m17303d();
        HttpEntity entity = httpResponse.getEntity();
        if (entity != null) {
            OutputStream a = m17296a((HttpMessage) httpResponse);
            entity.writeTo(a);
            a.close();
        }
    }

    public void sendResponseHeader(HttpResponse httpResponse) {
        C3234a.m17886a((Object) httpResponse, "HTTP response");
        m17303d();
        this.f15521b.write(httpResponse);
        m17697a(httpResponse);
        if (httpResponse.getStatusLine().getStatusCode() >= C2799f.f14282t) {
            m17308i();
        }
    }
}
