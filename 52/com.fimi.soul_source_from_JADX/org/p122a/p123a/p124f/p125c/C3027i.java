package org.p122a.p123a.p124f.p125c;

import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509KeyManager;
import javax.net.ssl.X509TrustManager;
import org.p122a.p123a.p150a.C2913c;

@C2913c
/* renamed from: org.a.a.f.c.i */
public class C3027i {
    static final String f15073a = "TLS";
    static final String f15074b = "SSL";
    private String f15075c;
    private Set<KeyManager> f15076d;
    private Set<TrustManager> f15077e;
    private SecureRandom f15078f;

    public C3027i() {
        this.f15076d = new HashSet();
        this.f15077e = new HashSet();
    }

    public C3027i m17111a() {
        this.f15075c = f15073a;
        return this;
    }

    public C3027i m17112a(String str) {
        this.f15075c = str;
        return this;
    }

    public C3027i m17113a(KeyStore keyStore) {
        return m17114a(keyStore, null);
    }

    public C3027i m17114a(KeyStore keyStore, C1961q c1961q) {
        int i = 0;
        TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        instance.init(keyStore);
        TrustManager[] trustManagers = instance.getTrustManagers();
        if (trustManagers != null) {
            if (c1961q != null) {
                for (int i2 = 0; i2 < trustManagers.length; i2++) {
                    TrustManager trustManager = trustManagers[i2];
                    if (trustManager instanceof X509TrustManager) {
                        trustManagers[i2] = new C3029k((X509TrustManager) trustManager, c1961q);
                    }
                }
            }
            int length = trustManagers.length;
            while (i < length) {
                this.f15077e.add(trustManagers[i]);
                i++;
            }
        }
        return this;
    }

    public C3027i m17115a(KeyStore keyStore, char[] cArr) {
        m17116a(keyStore, cArr, null);
        return this;
    }

    public C3027i m17116a(KeyStore keyStore, char[] cArr, C3025g c3025g) {
        int i = 0;
        KeyManagerFactory instance = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        instance.init(keyStore, cArr);
        KeyManager[] keyManagers = instance.getKeyManagers();
        if (keyManagers != null) {
            if (c3025g != null) {
                for (int i2 = 0; i2 < keyManagers.length; i2++) {
                    KeyManager keyManager = keyManagers[i2];
                    if (keyManager instanceof X509KeyManager) {
                        keyManagers[i2] = new C3028j((X509KeyManager) keyManager, c3025g);
                    }
                }
            }
            int length = keyManagers.length;
            while (i < length) {
                this.f15076d.add(keyManagers[i]);
                i++;
            }
        }
        return this;
    }

    public C3027i m17117a(SecureRandom secureRandom) {
        this.f15078f = secureRandom;
        return this;
    }

    public C3027i m17118b() {
        this.f15075c = f15074b;
        return this;
    }

    public SSLContext m17119c() {
        SSLContext instance = SSLContext.getInstance(this.f15075c != null ? this.f15075c : f15073a);
        instance.init(!this.f15076d.isEmpty() ? (KeyManager[]) this.f15076d.toArray(new KeyManager[this.f15076d.size()]) : null, !this.f15077e.isEmpty() ? (TrustManager[]) this.f15077e.toArray(new TrustManager[this.f15077e.size()]) : null, this.f15078f);
        return instance;
    }
}
