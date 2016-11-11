package com.p016a;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/* renamed from: com.a.br */
class br implements HostnameVerifier {
    final /* synthetic */ bn f646a;

    br(bn bnVar) {
        this.f646a = bnVar;
    }

    public boolean verify(String str, SSLSession sSLSession) {
        return HttpsURLConnection.getDefaultHostnameVerifier().verify("*.amap.com", sSLSession);
    }
}
