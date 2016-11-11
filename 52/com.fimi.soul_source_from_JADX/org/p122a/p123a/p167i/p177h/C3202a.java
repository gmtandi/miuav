package org.p122a.p123a.p167i.p177h;

import com.facebook.common.util.UriUtil;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpHost;
import org.apache.http.params.HttpParams;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.p122a.p123a.C2994d;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p162e.C2995a;
import org.p122a.p123a.p162e.C3002h;
import org.p122a.p123a.p167i.C3116c;
import org.p122a.p123a.p167i.C3162d;
import org.p122a.p123a.p171m.C3130f;
import org.p122a.p123a.p179l.C3213a;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.i.h.a */
public class C3202a implements C3130f<HttpHost, HttpClientConnection> {
    private final SocketFactory f15639a;
    private final SSLSocketFactory f15640b;
    private final int f15641c;
    private final C3002h f15642d;
    private final C2994d<? extends HttpClientConnection> f15643e;

    public C3202a() {
        this(null, null, 0, C3002h.f14978a, C2995a.f14958a);
    }

    public C3202a(int i, C3002h c3002h, C2995a c2995a) {
        this(null, null, i, c3002h, c2995a);
    }

    public C3202a(SocketFactory socketFactory, SSLSocketFactory sSLSocketFactory, int i, C3002h c3002h, C2995a c2995a) {
        this.f15639a = socketFactory;
        this.f15640b = sSLSocketFactory;
        this.f15641c = i;
        if (c3002h == null) {
            c3002h = C3002h.f14978a;
        }
        this.f15642d = c3002h;
        if (c2995a == null) {
            c2995a = C2995a.f14958a;
        }
        this.f15643e = new C3162d(c2995a);
    }

    @Deprecated
    public C3202a(SSLSocketFactory sSLSocketFactory, HttpParams httpParams) {
        C3234a.m17886a((Object) httpParams, "HTTP params");
        this.f15639a = null;
        this.f15640b = sSLSocketFactory;
        this.f15641c = httpParams.getIntParameter("http.connection.timeout", 0);
        this.f15642d = C3213a.m17825a(httpParams);
        this.f15643e = new C3162d(C3213a.m17827c(httpParams));
    }

    public C3202a(C3002h c3002h, C2995a c2995a) {
        this(null, null, 0, c3002h, c2995a);
    }

    @Deprecated
    public C3202a(HttpParams httpParams) {
        this(null, httpParams);
    }

    @Deprecated
    protected HttpClientConnection m17780a(Socket socket, HttpParams httpParams) {
        HttpClientConnection c3116c = new C3116c(httpParams.getIntParameter("http.socket.buffer-size", Opcodes.ACC_ANNOTATION));
        c3116c.m17573a(socket);
        return c3116c;
    }

    public HttpClientConnection m17781a(HttpHost httpHost) {
        Socket createSocket;
        String schemeName = httpHost.getSchemeName();
        Socket socket = null;
        if (UriUtil.HTTP_SCHEME.equalsIgnoreCase(schemeName)) {
            socket = this.f15639a != null ? this.f15639a.createSocket() : new Socket();
        }
        if (UriUtil.HTTPS_SCHEME.equalsIgnoreCase(schemeName)) {
            createSocket = (this.f15640b != null ? this.f15640b : SSLSocketFactory.getDefault()).createSocket();
        } else {
            createSocket = socket;
        }
        if (createSocket == null) {
            throw new IOException(schemeName + " scheme is not supported");
        }
        schemeName = httpHost.getHostName();
        int port = httpHost.getPort();
        if (port == -1) {
            if (httpHost.getSchemeName().equalsIgnoreCase(UriUtil.HTTP_SCHEME)) {
                port = 80;
            } else if (httpHost.getSchemeName().equalsIgnoreCase(UriUtil.HTTPS_SCHEME)) {
                port = 443;
            }
        }
        createSocket.setSoTimeout(this.f15642d.m17039a());
        createSocket.connect(new InetSocketAddress(schemeName, port), this.f15641c);
        createSocket.setTcpNoDelay(this.f15642d.m17043e());
        int c = this.f15642d.m17041c();
        if (c >= 0) {
            createSocket.setSoLinger(c > 0, c);
        }
        createSocket.setKeepAlive(this.f15642d.m17042d());
        return (HttpClientConnection) this.f15643e.m17006a(createSocket);
    }
}
