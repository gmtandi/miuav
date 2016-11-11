package org.p122a.p123a.p124f.p163a;

import java.net.Socket;
import org.apache.http.conn.scheme.LayeredSocketFactory;

@Deprecated
/* renamed from: org.a.a.f.a.b */
class C3008b extends C3007h implements LayeredSocketFactory {
    private final C3006a f15042a;

    C3008b(C3006a c3006a) {
        super(c3006a);
        this.f15042a = c3006a;
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) {
        return this.f15042a.m17054a(socket, str, i, z);
    }
}
