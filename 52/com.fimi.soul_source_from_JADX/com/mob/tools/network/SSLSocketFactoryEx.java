package com.mob.tools.network;

import java.net.Socket;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.p122a.p123a.p124f.p125c.C3026h;

public class SSLSocketFactoryEx extends SSLSocketFactory {
    SSLContext sslContext;

    /* renamed from: com.mob.tools.network.SSLSocketFactoryEx.1 */
    class C21761 implements X509TrustManager {
        C21761() {
        }

        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
            try {
                x509CertificateArr[0].checkValidity();
            } catch (Exception e) {
                throw new CertificateException("Certificate not valid or trusted.");
            }
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }

    public SSLSocketFactoryEx(KeyStore keyStore) {
        super(keyStore);
        this.sslContext = SSLContext.getInstance(C3026h.f15062a);
        C21761 c21761 = new C21761();
        this.sslContext.init(null, new TrustManager[]{c21761}, null);
    }

    public void allowAllHostnameVerifier() {
        setHostnameVerifier(ALLOW_ALL_HOSTNAME_VERIFIER);
    }

    public Socket createSocket() {
        return this.sslContext.getSocketFactory().createSocket();
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) {
        return this.sslContext.getSocketFactory().createSocket(socket, str, i, z);
    }
}
