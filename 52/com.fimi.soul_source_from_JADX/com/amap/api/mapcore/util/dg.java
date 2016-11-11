package com.amap.api.mapcore.util;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

class dg implements HostnameVerifier {
    final /* synthetic */ df f2426a;

    dg(df dfVar) {
        this.f2426a = dfVar;
    }

    public boolean verify(String str, SSLSession sSLSession) {
        return HttpsURLConnection.getDefaultHostnameVerifier().verify("*.amap.com", sSLSession);
    }
}
