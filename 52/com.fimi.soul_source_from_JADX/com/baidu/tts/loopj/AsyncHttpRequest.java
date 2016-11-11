package com.baidu.tts.loopj;

import com.tencent.open.SocialConstants;
import java.net.MalformedURLException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.protocol.HttpContext;

public class AsyncHttpRequest implements Runnable {
    private boolean cancelIsNotified;
    private final AbstractHttpClient client;
    private final HttpContext context;
    private int executionCount;
    private final AtomicBoolean isCancelled;
    private volatile boolean isFinished;
    private boolean isRequestPreProcessed;
    private final HttpUriRequest request;
    private final ResponseHandlerInterface responseHandler;

    public AsyncHttpRequest(AbstractHttpClient abstractHttpClient, HttpContext httpContext, HttpUriRequest httpUriRequest, ResponseHandlerInterface responseHandlerInterface) {
        this.isCancelled = new AtomicBoolean();
        this.client = (AbstractHttpClient) Utils.notNull(abstractHttpClient, "client");
        this.context = (HttpContext) Utils.notNull(httpContext, "context");
        this.request = (HttpUriRequest) Utils.notNull(httpUriRequest, SocialConstants.TYPE_REQUEST);
        this.responseHandler = (ResponseHandlerInterface) Utils.notNull(responseHandlerInterface, "responseHandler");
    }

    private void makeRequest() {
        if (!isCancelled()) {
            if (this.request.getURI().getScheme() == null) {
                throw new MalformedURLException("No valid URI scheme was provided");
            }
            if (this.responseHandler instanceof RangeFileAsyncHttpResponseHandler) {
                ((RangeFileAsyncHttpResponseHandler) this.responseHandler).updateRequestHeaders(this.request);
            }
            HttpResponse execute = this.client.execute(this.request, this.context);
            if (!isCancelled()) {
                this.responseHandler.onPreProcessResponse(this.responseHandler, execute);
                if (!isCancelled()) {
                    this.responseHandler.sendResponseMessage(execute);
                    if (!isCancelled()) {
                        this.responseHandler.onPostProcessResponse(this.responseHandler, execute);
                    }
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void makeRequestWithRetries() {
        /*
        r7 = this;
        r1 = 1;
        r0 = 0;
        r2 = r7.client;
        r3 = r2.getHttpRequestRetryHandler();
        r2 = r1;
    L_0x0009:
        if (r2 == 0) goto L_0x0073;
    L_0x000b:
        r7.makeRequest();	 Catch:{ UnknownHostException -> 0x000f, NullPointerException -> 0x0076, IOException -> 0x00a0 }
    L_0x000e:
        return;
    L_0x000f:
        r0 = move-exception;
        r2 = new java.io.IOException;	 Catch:{ Exception -> 0x004c }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x004c }
        r4.<init>();	 Catch:{ Exception -> 0x004c }
        r5 = "UnknownHostException exception: ";
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x004c }
        r5 = r0.getMessage();	 Catch:{ Exception -> 0x004c }
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x004c }
        r4 = r4.toString();	 Catch:{ Exception -> 0x004c }
        r2.<init>(r4);	 Catch:{ Exception -> 0x004c }
        r4 = r7.executionCount;	 Catch:{ Exception -> 0x004c }
        if (r4 < 0) goto L_0x0074;
    L_0x0030:
        r4 = r7.executionCount;	 Catch:{ Exception -> 0x004c }
        r4 = r4 + 1;
        r7.executionCount = r4;	 Catch:{ Exception -> 0x004c }
        r5 = r7.context;	 Catch:{ Exception -> 0x004c }
        r0 = r3.retryRequest(r0, r4, r5);	 Catch:{ Exception -> 0x004c }
        if (r0 == 0) goto L_0x0074;
    L_0x003e:
        r0 = r1;
    L_0x003f:
        r6 = r2;
        r2 = r0;
        r0 = r6;
    L_0x0042:
        if (r2 == 0) goto L_0x0009;
    L_0x0044:
        r4 = r7.responseHandler;	 Catch:{ Exception -> 0x004c }
        r5 = r7.executionCount;	 Catch:{ Exception -> 0x004c }
        r4.sendRetryMessage(r5);	 Catch:{ Exception -> 0x004c }
        goto L_0x0009;
    L_0x004c:
        r0 = move-exception;
        r1 = r0;
        r0 = com.baidu.tts.loopj.AsyncHttpClient.log;
        r2 = "AsyncHttpRequest";
        r3 = "Unhandled exception origin cause";
        r0.m6891e(r2, r3, r1);
        r0 = new java.io.IOException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Unhandled exception: ";
        r2 = r2.append(r3);
        r1 = r1.getMessage();
        r1 = r2.append(r1);
        r1 = r1.toString();
        r0.<init>(r1);
    L_0x0073:
        throw r0;
    L_0x0074:
        r0 = 0;
        goto L_0x003f;
    L_0x0076:
        r2 = move-exception;
        r0 = new java.io.IOException;	 Catch:{ Exception -> 0x004c }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x004c }
        r4.<init>();	 Catch:{ Exception -> 0x004c }
        r5 = "NPE in HttpClient: ";
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x004c }
        r2 = r2.getMessage();	 Catch:{ Exception -> 0x004c }
        r2 = r4.append(r2);	 Catch:{ Exception -> 0x004c }
        r2 = r2.toString();	 Catch:{ Exception -> 0x004c }
        r0.<init>(r2);	 Catch:{ Exception -> 0x004c }
        r2 = r7.executionCount;	 Catch:{ Exception -> 0x004c }
        r2 = r2 + 1;
        r7.executionCount = r2;	 Catch:{ Exception -> 0x004c }
        r4 = r7.context;	 Catch:{ Exception -> 0x004c }
        r2 = r3.retryRequest(r0, r2, r4);	 Catch:{ Exception -> 0x004c }
        goto L_0x0042;
    L_0x00a0:
        r0 = move-exception;
        r2 = r7.isCancelled();	 Catch:{ Exception -> 0x004c }
        if (r2 != 0) goto L_0x000e;
    L_0x00a7:
        r2 = r7.executionCount;	 Catch:{ Exception -> 0x004c }
        r2 = r2 + 1;
        r7.executionCount = r2;	 Catch:{ Exception -> 0x004c }
        r4 = r7.context;	 Catch:{ Exception -> 0x004c }
        r2 = r3.retryRequest(r0, r2, r4);	 Catch:{ Exception -> 0x004c }
        goto L_0x0042;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.tts.loopj.AsyncHttpRequest.makeRequestWithRetries():void");
    }

    private synchronized void sendCancelNotification() {
        if (!(this.isFinished || !this.isCancelled.get() || this.cancelIsNotified)) {
            this.cancelIsNotified = true;
            this.responseHandler.sendCancelMessage();
        }
    }

    public boolean cancel(boolean z) {
        this.isCancelled.set(true);
        this.request.abort();
        return isCancelled();
    }

    public Object getTag() {
        return this.responseHandler.getTag();
    }

    public boolean isCancelled() {
        boolean z = this.isCancelled.get();
        if (z) {
            sendCancelNotification();
        }
        return z;
    }

    public boolean isDone() {
        return isCancelled() || this.isFinished;
    }

    public void onPostProcessRequest(AsyncHttpRequest asyncHttpRequest) {
    }

    public void onPreProcessRequest(AsyncHttpRequest asyncHttpRequest) {
    }

    public void run() {
        if (!isCancelled()) {
            if (!this.isRequestPreProcessed) {
                this.isRequestPreProcessed = true;
                onPreProcessRequest(this);
            }
            if (!isCancelled()) {
                this.responseHandler.sendStartMessage();
                if (!isCancelled()) {
                    try {
                        makeRequestWithRetries();
                    } catch (Throwable e) {
                        if (isCancelled()) {
                            AsyncHttpClient.log.m6891e("AsyncHttpRequest", "makeRequestWithRetries returned error", e);
                        } else {
                            this.responseHandler.sendFailureMessage(0, null, null, e);
                        }
                    }
                    if (!isCancelled()) {
                        this.responseHandler.sendFinishMessage();
                        if (!isCancelled()) {
                            onPostProcessRequest(this);
                            this.isFinished = true;
                        }
                    }
                }
            }
        }
    }

    public AsyncHttpRequest setRequestTag(Object obj) {
        this.responseHandler.setTag(obj);
        return this;
    }
}
