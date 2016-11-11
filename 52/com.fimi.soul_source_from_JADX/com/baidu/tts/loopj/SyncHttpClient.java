package com.baidu.tts.loopj;

import android.content.Context;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.C3004e;

public class SyncHttpClient extends AsyncHttpClient {
    private RequestHandle mRequestHandle;

    public SyncHttpClient() {
        super(false, 80, 443);
    }

    public SyncHttpClient(int i) {
        super(false, i, 443);
    }

    public SyncHttpClient(int i, int i2) {
        super(false, i, i2);
    }

    public SyncHttpClient(SchemeRegistry schemeRegistry) {
        super(schemeRegistry);
    }

    public SyncHttpClient(boolean z, int i, int i2) {
        super(z, i, i2);
    }

    protected RequestHandle sendRequest(DefaultHttpClient defaultHttpClient, HttpContext httpContext, HttpUriRequest httpUriRequest, String str, ResponseHandlerInterface responseHandlerInterface, Context context) {
        if (str != null) {
            httpUriRequest.addHeader(C3004e.f15031q, str);
        }
        responseHandlerInterface.setUseSynchronousMode(true);
        AsyncHttpRequest newAsyncHttpRequest = newAsyncHttpRequest(defaultHttpClient, httpContext, httpUriRequest, str, responseHandlerInterface, context);
        this.mRequestHandle = new RequestHandle(newAsyncHttpRequest);
        newAsyncHttpRequest.run();
        return null;
    }

    public void stop() {
        if (this.mRequestHandle != null) {
            this.mRequestHandle.cancel(true);
            this.mRequestHandle = null;
        }
    }
}
