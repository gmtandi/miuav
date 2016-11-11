package org.p122a.p123a.p124f.p163a;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

@Deprecated
/* renamed from: org.a.a.f.a.h */
class C3007h implements SocketFactory {
    private final C3005f f15041a;

    C3007h(C3005f c3005f) {
        this.f15041a = c3005f;
    }

    public C3005f m17055a() {
        return this.f15041a;
    }

    public Socket connectSocket(Socket socket, String str, int i, InetAddress inetAddress, int i2, HttpParams httpParams) {
        InetSocketAddress inetSocketAddress = null;
        if (inetAddress != null || i2 > 0) {
            if (i2 <= 0) {
                i2 = 0;
            }
            inetSocketAddress = new InetSocketAddress(inetAddress, i2);
        }
        return this.f15041a.m17051a(socket, new InetSocketAddress(InetAddress.getByName(str), i), inetSocketAddress, httpParams);
    }

    public Socket createSocket() {
        return this.f15041a.m17052a(new BasicHttpParams());
    }

    public boolean equals(Object obj) {
        return obj == null ? false : this == obj ? true : obj instanceof C3007h ? this.f15041a.equals(((C3007h) obj).f15041a) : this.f15041a.equals(obj);
    }

    public int hashCode() {
        return this.f15041a.hashCode();
    }

    public boolean isSecure(Socket socket) {
        return this.f15041a.m17053a(socket);
    }
}
