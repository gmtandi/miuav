package org.p122a.p123a.p167i.p170c;

import java.io.InterruptedIOException;
import java.net.Socket;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.p124f.C3040h;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p162e.C2998d;
import org.p122a.p123a.p167i.C3116c;
import org.p122a.p123a.p173j.C3114b;
import org.p122a.p123a.p173j.C3190c;

@C2913c
/* renamed from: org.a.a.i.c.i */
public class C3117i extends C3116c implements C3040h, HttpContext {
    private final String f15419a;
    private final Map<String, Object> f15420b;
    private volatile boolean f15421c;

    public C3117i(String str, int i) {
        this(str, i, i, null, null, null, null, null, null, null);
    }

    public C3117i(String str, int i, int i2, CharsetDecoder charsetDecoder, CharsetEncoder charsetEncoder, C2998d c2998d, ContentLengthStrategy contentLengthStrategy, ContentLengthStrategy contentLengthStrategy2, C3190c<HttpRequest> c3190c, C3114b<HttpResponse> c3114b) {
        super(i, i2, charsetDecoder, charsetEncoder, c2998d, contentLengthStrategy, contentLengthStrategy2, c3190c, c3114b);
        this.f15419a = str;
        this.f15420b = new ConcurrentHashMap();
    }

    public String m17576a() {
        return this.f15419a;
    }

    public void m17577a(Socket socket) {
        if (this.f15421c) {
            socket.close();
            throw new InterruptedIOException("Connection already shutdown");
        } else {
            super.m17573a(socket);
        }
    }

    public Socket m17578b() {
        return super.m17300b();
    }

    public SSLSession m17579c() {
        Socket b = super.m17300b();
        return b instanceof SSLSocket ? ((SSLSocket) b).getSession() : null;
    }

    public Object getAttribute(String str) {
        return this.f15420b.get(str);
    }

    public Object removeAttribute(String str) {
        return this.f15420b.remove(str);
    }

    public void setAttribute(String str, Object obj) {
        this.f15420b.put(str, obj);
    }

    public void shutdown() {
        this.f15421c = true;
        super.shutdown();
    }
}
