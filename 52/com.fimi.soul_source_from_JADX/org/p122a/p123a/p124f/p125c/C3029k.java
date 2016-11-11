package org.p122a.p123a.p124f.p125c;

import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

/* renamed from: org.a.a.f.c.k */
class C3029k implements X509TrustManager {
    private final X509TrustManager f15081a;
    private final C1961q f15082b;

    C3029k(X509TrustManager x509TrustManager, C1961q c1961q) {
        this.f15081a = x509TrustManager;
        this.f15082b = c1961q;
    }

    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        this.f15081a.checkClientTrusted(x509CertificateArr, str);
    }

    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        if (!this.f15082b.m12291a(x509CertificateArr, str)) {
            this.f15081a.checkServerTrusted(x509CertificateArr, str);
        }
    }

    public X509Certificate[] getAcceptedIssuers() {
        return this.f15081a.getAcceptedIssuers();
    }
}
