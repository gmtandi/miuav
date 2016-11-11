package org.p122a.p123a.p167i;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import org.apache.http.Header;
import org.apache.http.HttpConnection;
import org.apache.http.HttpConnectionMetrics;
import org.apache.http.HttpEntity;
import org.apache.http.HttpInetConnection;
import org.apache.http.HttpMessage;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.impl.HttpConnectionMetricsImpl;
import org.apache.http.impl.io.HttpTransportMetricsImpl;
import org.apache.http.io.HttpTransportMetrics;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.io.SessionOutputBuffer;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p154h.C3047b;
import org.p122a.p123a.p162e.C2998d;
import org.p122a.p123a.p167i.p172g.C3183c;
import org.p122a.p123a.p167i.p172g.C3184d;
import org.p122a.p123a.p167i.p172g.C3185e;
import org.p122a.p123a.p167i.p172g.C3186f;
import org.p122a.p123a.p167i.p172g.C3196o;
import org.p122a.p123a.p167i.p172g.C3197p;
import org.p122a.p123a.p167i.p172g.C3199q;
import org.p122a.p123a.p167i.p172g.C3200r;
import org.p122a.p123a.p167i.p175e.C3164b;
import org.p122a.p123a.p167i.p175e.C3165c;
import org.p122a.p123a.p180o.C3234a;
import org.p122a.p123a.p180o.C3235b;
import org.p122a.p123a.p180o.C3238e;

@C2913c
/* renamed from: org.a.a.i.a */
public class C3074a implements HttpConnection, HttpInetConnection {
    private final C3199q f15224a;
    private final C3200r f15225b;
    private final HttpConnectionMetricsImpl f15226c;
    private final ContentLengthStrategy f15227d;
    private final ContentLengthStrategy f15228e;
    private volatile boolean f15229f;
    private volatile Socket f15230g;

    protected C3074a(int i, int i2, CharsetDecoder charsetDecoder, CharsetEncoder charsetEncoder, C2998d c2998d, ContentLengthStrategy contentLengthStrategy, ContentLengthStrategy contentLengthStrategy2) {
        C3234a.m17883a(i, "Buffer size");
        HttpTransportMetrics httpTransportMetricsImpl = new HttpTransportMetricsImpl();
        HttpTransportMetrics httpTransportMetricsImpl2 = new HttpTransportMetricsImpl();
        this.f15224a = new C3199q(httpTransportMetricsImpl, i, -1, c2998d != null ? c2998d : C2998d.f14971a, charsetDecoder);
        this.f15225b = new C3200r(httpTransportMetricsImpl2, i, i2, charsetEncoder);
        this.f15226c = new HttpConnectionMetricsImpl(httpTransportMetricsImpl, httpTransportMetricsImpl2);
        if (contentLengthStrategy == null) {
            contentLengthStrategy = C3164b.f15516a;
        }
        this.f15227d = contentLengthStrategy;
        if (contentLengthStrategy2 == null) {
            contentLengthStrategy2 = C3165c.f15518a;
        }
        this.f15228e = contentLengthStrategy2;
    }

    private int m17293b(int i) {
        int soTimeout = this.f15230g.getSoTimeout();
        try {
            this.f15230g.setSoTimeout(i);
            int e = this.f15224a.m17764e();
            return e;
        } finally {
            this.f15230g.setSoTimeout(soTimeout);
        }
    }

    protected InputStream m17294a(long j, SessionInputBuffer sessionInputBuffer) {
        return j == -2 ? new C3183c(sessionInputBuffer) : j == -1 ? new C3196o(sessionInputBuffer) : new C3185e(sessionInputBuffer, j);
    }

    protected OutputStream m17295a(long j, SessionOutputBuffer sessionOutputBuffer) {
        return j == -2 ? new C3184d((int) Opcodes.ACC_STRICT, sessionOutputBuffer) : j == -1 ? new C3197p(sessionOutputBuffer) : new C3186f(sessionOutputBuffer, j);
    }

    protected OutputStream m17296a(HttpMessage httpMessage) {
        return m17295a(this.f15228e.determineLength(httpMessage), this.f15225b);
    }

    protected void m17297a(Socket socket) {
        C3234a.m17886a((Object) socket, "Socket");
        this.f15230g = socket;
        this.f15229f = true;
        this.f15224a.m17759a(null);
        this.f15225b.m17772a(null);
    }

    protected boolean m17298a(int i) {
        if (this.f15224a.m17765f()) {
            return true;
        }
        m17293b(i);
        return this.f15224a.m17765f();
    }

    protected InputStream m17299b(Socket socket) {
        return socket.getInputStream();
    }

    protected Socket m17300b() {
        return this.f15230g;
    }

    protected HttpEntity m17301b(HttpMessage httpMessage) {
        C3047b c3047b = new C3047b();
        long determineLength = this.f15227d.determineLength(httpMessage);
        InputStream a = m17294a(determineLength, this.f15224a);
        if (determineLength == -2) {
            c3047b.m16809a(true);
            c3047b.m17154a(-1);
            c3047b.m17155a(a);
        } else if (determineLength == -1) {
            c3047b.m16809a(false);
            c3047b.m17154a(-1);
            c3047b.m17155a(a);
        } else {
            c3047b.m16809a(false);
            c3047b.m17154a(determineLength);
            c3047b.m17155a(a);
        }
        Header firstHeader = httpMessage.getFirstHeader(C3004e.f15031q);
        if (firstHeader != null) {
            c3047b.m16808a(firstHeader);
        }
        firstHeader = httpMessage.getFirstHeader(C3004e.f15025k);
        if (firstHeader != null) {
            c3047b.m16811b(firstHeader);
        }
        return c3047b;
    }

    protected OutputStream m17302c(Socket socket) {
        return socket.getOutputStream();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void close() {
        /*
        r2 = this;
        r0 = r2.f15229f;
        if (r0 != 0) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        r0 = 0;
        r2.f15229f = r0;
        r1 = r2.f15230g;
        r0 = r2.f15224a;	 Catch:{ all -> 0x001e }
        r0.m17766g();	 Catch:{ all -> 0x001e }
        r0 = r2.f15225b;	 Catch:{ all -> 0x001e }
        r0.flush();	 Catch:{ all -> 0x001e }
        r1.shutdownOutput();	 Catch:{ IOException -> 0x0023, UnsupportedOperationException -> 0x0027 }
    L_0x0017:
        r1.shutdownInput();	 Catch:{ IOException -> 0x0025, UnsupportedOperationException -> 0x0027 }
    L_0x001a:
        r1.close();
        goto L_0x0004;
    L_0x001e:
        r0 = move-exception;
        r1.close();
        throw r0;
    L_0x0023:
        r0 = move-exception;
        goto L_0x0017;
    L_0x0025:
        r0 = move-exception;
        goto L_0x001a;
    L_0x0027:
        r0 = move-exception;
        goto L_0x001a;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.a.a.i.a.close():void");
    }

    protected void m17303d() {
        C3235b.m17895a(this.f15229f, "Connection is not open");
        if (!this.f15224a.m17760a()) {
            this.f15224a.m17759a(m17299b(this.f15230g));
        }
        if (!this.f15225b.m17773a()) {
            this.f15225b.m17772a(m17302c(this.f15230g));
        }
    }

    protected SessionInputBuffer m17304e() {
        return this.f15224a;
    }

    protected SessionOutputBuffer m17305f() {
        return this.f15225b;
    }

    protected void m17306g() {
        this.f15225b.flush();
    }

    public InetAddress getLocalAddress() {
        return this.f15230g != null ? this.f15230g.getLocalAddress() : null;
    }

    public int getLocalPort() {
        return this.f15230g != null ? this.f15230g.getLocalPort() : -1;
    }

    public HttpConnectionMetrics getMetrics() {
        return this.f15226c;
    }

    public InetAddress getRemoteAddress() {
        return this.f15230g != null ? this.f15230g.getInetAddress() : null;
    }

    public int getRemotePort() {
        return this.f15230g != null ? this.f15230g.getPort() : -1;
    }

    public int getSocketTimeout() {
        int i = -1;
        if (this.f15230g != null) {
            try {
                i = this.f15230g.getSoTimeout();
            } catch (SocketException e) {
            }
        }
        return i;
    }

    protected void m17307h() {
        this.f15226c.incrementRequestCount();
    }

    protected void m17308i() {
        this.f15226c.incrementResponseCount();
    }

    public boolean isOpen() {
        return this.f15229f;
    }

    public boolean isStale() {
        if (!isOpen()) {
            return true;
        }
        try {
            return m17293b(1) < 0;
        } catch (SocketTimeoutException e) {
            return false;
        } catch (IOException e2) {
            return true;
        }
    }

    public void setSocketTimeout(int i) {
        if (this.f15230g != null) {
            try {
                this.f15230g.setSoTimeout(i);
            } catch (SocketException e) {
            }
        }
    }

    public void shutdown() {
        this.f15229f = false;
        Socket socket = this.f15230g;
        if (socket != null) {
            socket.close();
        }
    }

    public String toString() {
        if (this.f15230g == null) {
            return "[Not bound]";
        }
        StringBuilder stringBuilder = new StringBuilder();
        SocketAddress remoteSocketAddress = this.f15230g.getRemoteSocketAddress();
        SocketAddress localSocketAddress = this.f15230g.getLocalSocketAddress();
        if (!(remoteSocketAddress == null || localSocketAddress == null)) {
            C3238e.m17909a(stringBuilder, localSocketAddress);
            stringBuilder.append("<->");
            C3238e.m17909a(stringBuilder, remoteSocketAddress);
        }
        return stringBuilder.toString();
    }
}
