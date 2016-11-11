package org.p122a.p123a.p124f.p125c;

import java.security.cert.X509Certificate;
import java.util.Arrays;
import org.p122a.p123a.p180o.C3234a;

/* renamed from: org.a.a.f.c.f */
public final class C3024f {
    private final String f15060a;
    private final X509Certificate[] f15061b;

    public C3024f(String str, X509Certificate[] x509CertificateArr) {
        this.f15060a = (String) C3234a.m17886a((Object) str, "Private key type");
        this.f15061b = x509CertificateArr;
    }

    public String m17099a() {
        return this.f15060a;
    }

    public X509Certificate[] m17100b() {
        return this.f15061b;
    }

    public String toString() {
        return this.f15060a + ':' + Arrays.toString(this.f15061b);
    }
}
