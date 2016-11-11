package org.p122a.p123a.p124f.p163a;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.params.HttpParams;

@Deprecated
/* renamed from: org.a.a.f.a.g */
class C3010g implements C3005f {
    private final SocketFactory f15043a;

    C3010g(SocketFactory socketFactory) {
        this.f15043a = socketFactory;
    }

    public Socket m17057a(Socket socket, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, HttpParams httpParams) {
        String hostName = inetSocketAddress.getHostName();
        int port = inetSocketAddress.getPort();
        InetAddress inetAddress = null;
        int i = 0;
        if (inetSocketAddress2 != null) {
            inetAddress = inetSocketAddress2.getAddress();
            i = inetSocketAddress2.getPort();
        }
        return this.f15043a.connectSocket(socket, hostName, port, inetAddress, i, httpParams);
    }

    public Socket m17058a(HttpParams httpParams) {
        return this.f15043a.createSocket();
    }

    public SocketFactory m17059a() {
        return this.f15043a;
    }

    public boolean m17060a(Socket socket) {
        return this.f15043a.isSecure(socket);
    }

    public boolean equals(Object obj) {
        return obj == null ? false : this == obj ? true : obj instanceof C3010g ? this.f15043a.equals(((C3010g) obj).f15043a) : this.f15043a.equals(obj);
    }

    public int hashCode() {
        return this.f15043a.hashCode();
    }
}
