package org.p122a.p123a.p124f.p163a;

import java.net.InetSocketAddress;
import java.net.Socket;
import org.apache.http.params.HttpParams;

@Deprecated
/* renamed from: org.a.a.f.a.e */
class C3012e implements C3009c {
    private final C3006a f15045a;

    C3012e(C3006a c3006a) {
        this.f15045a = c3006a;
    }

    public Socket m17062a(Socket socket, String str, int i, HttpParams httpParams) {
        return this.f15045a.m17054a(socket, str, i, true);
    }

    public Socket m17063a(Socket socket, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, HttpParams httpParams) {
        return this.f15045a.m17051a(socket, inetSocketAddress, inetSocketAddress2, httpParams);
    }

    public Socket m17064a(HttpParams httpParams) {
        return this.f15045a.m17052a(httpParams);
    }

    public boolean m17065a(Socket socket) {
        return this.f15045a.m17053a(socket);
    }
}
