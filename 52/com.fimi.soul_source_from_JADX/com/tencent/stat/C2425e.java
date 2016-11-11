package com.tencent.stat;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.protocol.HttpContext;

/* renamed from: com.tencent.stat.e */
class C2425e extends DefaultConnectionKeepAliveStrategy {
    final /* synthetic */ C2424d f12369a;

    C2425e(C2424d c2424d) {
        this.f12369a = c2424d;
    }

    public long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext) {
        long keepAliveDuration = super.getKeepAliveDuration(httpResponse, httpContext);
        return keepAliveDuration == -1 ? 20000 : keepAliveDuration;
    }
}
