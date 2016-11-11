package org.p122a.p123a.p167i.p170c;

import java.net.InetAddress;
import java.net.Socket;
import javax.net.ssl.SSLSession;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpConnectionMetrics;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.p124f.C3040h;
import org.p122a.p123a.p150a.C2913c;

@C2913c
/* renamed from: org.a.a.i.c.e */
class C3110e implements C3040h, HttpContext {
    private volatile C3109d f15402a;

    C3110e(C3109d c3109d) {
        this.f15402a = c3109d;
    }

    public static C3109d m17553a(HttpClientConnection httpClientConnection) {
        C3109d d = C3110e.m17556c(httpClientConnection).m17561d();
        if (d != null) {
            return d;
        }
        throw new C3111f();
    }

    public static HttpClientConnection m17554a(C3109d c3109d) {
        return new C3110e(c3109d);
    }

    public static C3109d m17555b(HttpClientConnection httpClientConnection) {
        return C3110e.m17556c(httpClientConnection).m17562e();
    }

    private static C3110e m17556c(HttpClientConnection httpClientConnection) {
        if (C3110e.class.isInstance(httpClientConnection)) {
            return (C3110e) C3110e.class.cast(httpClientConnection);
        }
        throw new IllegalStateException("Unexpected connection proxy class: " + httpClientConnection.getClass());
    }

    public String m17557a() {
        return m17564g().m17148a();
    }

    public void m17558a(Socket socket) {
        m17564g().m17149a(socket);
    }

    public Socket m17559b() {
        return m17564g().m17150b();
    }

    public SSLSession m17560c() {
        return m17564g().m17151c();
    }

    public void close() {
        C3109d c3109d = this.f15402a;
        if (c3109d != null) {
            c3109d.m17549c();
        }
    }

    C3109d m17561d() {
        return this.f15402a;
    }

    C3109d m17562e() {
        C3109d c3109d = this.f15402a;
        this.f15402a = null;
        return c3109d;
    }

    C3040h m17563f() {
        C3109d c3109d = this.f15402a;
        return c3109d == null ? null : (C3040h) c3109d.m17540i();
    }

    public void flush() {
        m17564g().flush();
    }

    C3040h m17564g() {
        C3040h f = m17563f();
        if (f != null) {
            return f;
        }
        throw new C3111f();
    }

    public Object getAttribute(String str) {
        C3040h g = m17564g();
        return g instanceof HttpContext ? ((HttpContext) g).getAttribute(str) : null;
    }

    public InetAddress getLocalAddress() {
        return m17564g().getLocalAddress();
    }

    public int getLocalPort() {
        return m17564g().getLocalPort();
    }

    public HttpConnectionMetrics getMetrics() {
        return m17564g().getMetrics();
    }

    public InetAddress getRemoteAddress() {
        return m17564g().getRemoteAddress();
    }

    public int getRemotePort() {
        return m17564g().getRemotePort();
    }

    public int getSocketTimeout() {
        return m17564g().getSocketTimeout();
    }

    public boolean isOpen() {
        C3109d c3109d = this.f15402a;
        return (c3109d == null || c3109d.m17551e()) ? false : true;
    }

    public boolean isResponseAvailable(int i) {
        return m17564g().isResponseAvailable(i);
    }

    public boolean isStale() {
        HttpClientConnection f = m17563f();
        return f != null ? f.isStale() : true;
    }

    public void receiveResponseEntity(HttpResponse httpResponse) {
        m17564g().receiveResponseEntity(httpResponse);
    }

    public HttpResponse receiveResponseHeader() {
        return m17564g().receiveResponseHeader();
    }

    public Object removeAttribute(String str) {
        C3040h g = m17564g();
        return g instanceof HttpContext ? ((HttpContext) g).removeAttribute(str) : null;
    }

    public void sendRequestEntity(HttpEntityEnclosingRequest httpEntityEnclosingRequest) {
        m17564g().sendRequestEntity(httpEntityEnclosingRequest);
    }

    public void sendRequestHeader(HttpRequest httpRequest) {
        m17564g().sendRequestHeader(httpRequest);
    }

    public void setAttribute(String str, Object obj) {
        C3040h g = m17564g();
        if (g instanceof HttpContext) {
            ((HttpContext) g).setAttribute(str, obj);
        }
    }

    public void setSocketTimeout(int i) {
        m17564g().setSocketTimeout(i);
    }

    public void shutdown() {
        C3109d c3109d = this.f15402a;
        if (c3109d != null) {
            c3109d.m17550d();
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("CPoolProxy{");
        C3040h f = m17563f();
        if (f != null) {
            stringBuilder.append(f);
        } else {
            stringBuilder.append("detached");
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
