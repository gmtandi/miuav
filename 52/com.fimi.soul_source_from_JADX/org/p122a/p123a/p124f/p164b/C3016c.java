package org.p122a.p123a.p124f.p164b;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import org.apache.http.HttpHost;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.p150a.C2912b;

@C2912b
/* renamed from: org.a.a.f.b.c */
public class C3016c implements C3014a {
    public static final C3016c f15046a;

    static {
        f15046a = new C3016c();
    }

    public static C3016c m17070a() {
        return f15046a;
    }

    public Socket m17071a(int i, Socket socket, HttpHost httpHost, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, HttpContext httpContext) {
        if (socket == null) {
            socket = m17072a(httpContext);
        }
        if (inetSocketAddress2 != null) {
            socket.bind(inetSocketAddress2);
        }
        try {
            socket.connect(inetSocketAddress, i);
            return socket;
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException e2) {
            }
            throw e;
        }
    }

    public Socket m17072a(HttpContext httpContext) {
        return new Socket();
    }
}
