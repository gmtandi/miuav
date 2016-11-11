package com.xiaomi.infra.galaxy.fds.android.auth;

import org.apache.http.client.methods.HttpRequestBase;

public interface GalaxyFDSCredential {
    void addHeader(HttpRequestBase httpRequestBase);

    String addParam(String str);
}
