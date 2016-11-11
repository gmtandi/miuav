package org.p122a.p123a.p124f;

import java.net.Socket;
import javax.net.ssl.SSLSession;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpInetConnection;

/* renamed from: org.a.a.f.h */
public interface C3040h extends HttpClientConnection, HttpInetConnection {
    String m17148a();

    void m17149a(Socket socket);

    Socket m17150b();

    SSLSession m17151c();
}
