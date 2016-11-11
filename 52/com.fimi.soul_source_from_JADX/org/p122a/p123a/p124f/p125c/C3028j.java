package org.p122a.p123a.p124f.p125c;

import java.net.Socket;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.X509KeyManager;

/* renamed from: org.a.a.f.c.j */
class C3028j implements X509KeyManager {
    private final X509KeyManager f15079a;
    private final C3025g f15080b;

    C3028j(X509KeyManager x509KeyManager, C3025g c3025g) {
        this.f15079a = x509KeyManager;
        this.f15080b = c3025g;
    }

    public String chooseClientAlias(String[] strArr, Principal[] principalArr, Socket socket) {
        Map hashMap = new HashMap();
        for (String str : strArr) {
            String[] clientAliases = this.f15079a.getClientAliases(str, principalArr);
            if (clientAliases != null) {
                for (String str2 : clientAliases) {
                    hashMap.put(str2, new C3024f(str, this.f15079a.getCertificateChain(str2)));
                }
            }
        }
        return this.f15080b.m17101a(hashMap, socket);
    }

    public String chooseServerAlias(String str, Principal[] principalArr, Socket socket) {
        Map hashMap = new HashMap();
        String[] serverAliases = this.f15079a.getServerAliases(str, principalArr);
        if (serverAliases != null) {
            for (String str2 : serverAliases) {
                hashMap.put(str2, new C3024f(str, this.f15079a.getCertificateChain(str2)));
            }
        }
        return this.f15080b.m17101a(hashMap, socket);
    }

    public X509Certificate[] getCertificateChain(String str) {
        return this.f15079a.getCertificateChain(str);
    }

    public String[] getClientAliases(String str, Principal[] principalArr) {
        return this.f15079a.getClientAliases(str, principalArr);
    }

    public PrivateKey getPrivateKey(String str) {
        return this.f15079a.getPrivateKey(str);
    }

    public String[] getServerAliases(String str, Principal[] principalArr) {
        return this.f15079a.getServerAliases(str, principalArr);
    }
}
