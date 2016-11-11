package org.p122a.p123a.p124f.p163a;

import java.net.Socket;
import org.apache.http.conn.scheme.LayeredSocketFactory;
import org.apache.http.params.HttpParams;

@Deprecated
/* renamed from: org.a.a.f.a.d */
class C3011d extends C3010g implements C3009c {
    private final LayeredSocketFactory f15044a;

    C3011d(LayeredSocketFactory layeredSocketFactory) {
        super(layeredSocketFactory);
        this.f15044a = layeredSocketFactory;
    }

    public Socket m17061a(Socket socket, String str, int i, HttpParams httpParams) {
        return this.f15044a.createSocket(socket, str, i, true);
    }
}
