package p147m.framework.p148a;

import java.net.Socket;
import java.security.KeyStore;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.p122a.p123a.p124f.p125c.C3026h;

/* renamed from: m.framework.a.i */
public class C2854i extends SSLSocketFactory {
    SSLContext f14594a;

    public C2854i(KeyStore keyStore) {
        super(keyStore);
        this.f14594a = SSLContext.getInstance(C3026h.f15062a);
        C2855j c2855j = new C2855j(this);
        this.f14594a.init(null, new TrustManager[]{c2855j}, null);
    }

    public Socket createSocket() {
        return this.f14594a.getSocketFactory().createSocket();
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) {
        return this.f14594a.getSocketFactory().createSocket(socket, str, i, z);
    }
}
