package org.p122a.p123a.p167i.p170c;

import android.util.Log;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentLengthStrategy;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p162e.C2998d;
import org.p122a.p123a.p173j.C3114b;
import org.p122a.p123a.p173j.C3190c;

@C2913c
/* renamed from: org.a.a.i.c.p */
class C3124p extends C3117i {
    private static final String f15434a = "HttpClient";
    private static final String f15435b = "Headers";
    private final C3135z f15436c;

    public C3124p(String str, int i, int i2, CharsetDecoder charsetDecoder, CharsetEncoder charsetEncoder, C2998d c2998d, ContentLengthStrategy contentLengthStrategy, ContentLengthStrategy contentLengthStrategy2, C3190c<HttpRequest> c3190c, C3114b<HttpResponse> c3114b) {
        super(str, i, i2, charsetDecoder, charsetEncoder, c2998d, contentLengthStrategy, contentLengthStrategy2, c3190c, c3114b);
        this.f15436c = new C3135z(str);
    }

    protected void m17589a(HttpRequest httpRequest) {
        if (httpRequest != null && Log.isLoggable(f15435b, 3)) {
            Log.d(f15435b, m17576a() + " >> " + httpRequest.getRequestLine().toString());
            for (Object obj : httpRequest.getAllHeaders()) {
                Log.d(f15435b, m17576a() + " >> " + obj.toString());
            }
        }
    }

    protected void m17590a(HttpResponse httpResponse) {
        if (httpResponse != null && Log.isLoggable(f15435b, 3)) {
            Log.d(f15435b, m17576a() + " << " + httpResponse.getStatusLine().toString());
            for (Object obj : httpResponse.getAllHeaders()) {
                Log.d(f15435b, m17576a() + " << " + obj.toString());
            }
        }
    }

    protected InputStream m17591b(Socket socket) {
        InputStream b = super.m17299b(socket);
        return this.f15436c.m17650a() ? new C3123o(b, this.f15436c) : b;
    }

    protected OutputStream m17592c(Socket socket) {
        OutputStream c = super.m17302c(socket);
        return this.f15436c.m17650a() ? new C3125q(c, this.f15436c) : c;
    }

    public void close() {
        if (Log.isLoggable(f15434a, 3)) {
            Log.d(f15434a, m17576a() + ": Close connection");
        }
        super.close();
    }

    public void shutdown() {
        if (Log.isLoggable(f15434a, 3)) {
            Log.d(f15434a, m17576a() + ": Shutdown connection");
        }
        super.shutdown();
    }
}
