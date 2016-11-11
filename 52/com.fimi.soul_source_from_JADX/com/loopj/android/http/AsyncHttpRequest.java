package com.loopj.android.http;

import java.io.IOException;
import java.net.ConnectException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.protocol.HttpContext;

class AsyncHttpRequest implements Runnable {
    private final AbstractHttpClient client;
    private final HttpContext context;
    private int executionCount;
    private boolean isBinaryRequest;
    private final HttpUriRequest request;
    private final AsyncHttpResponseHandler responseHandler;

    public AsyncHttpRequest(AbstractHttpClient abstractHttpClient, HttpContext httpContext, HttpUriRequest httpUriRequest, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        this.client = abstractHttpClient;
        this.context = httpContext;
        this.request = httpUriRequest;
        this.responseHandler = asyncHttpResponseHandler;
        if (asyncHttpResponseHandler instanceof BinaryHttpResponseHandler) {
            this.isBinaryRequest = true;
        }
    }

    private void makeRequest() {
        if (!Thread.currentThread().isInterrupted()) {
            HttpResponse execute = this.client.execute(this.request, this.context);
            if (!Thread.currentThread().isInterrupted() && this.responseHandler != null) {
                this.responseHandler.sendResponseMessage(execute);
            }
        }
    }

    private void makeRequestWithRetries() {
        int i;
        boolean z = true;
        Throwable th = null;
        HttpRequestRetryHandler httpRequestRetryHandler = this.client.getHttpRequestRetryHandler();
        while (z) {
            try {
                makeRequest();
                return;
            } catch (Throwable th2) {
                if (this.responseHandler != null) {
                    this.responseHandler.sendFailureMessage(th2, "can't resolve host");
                    return;
                }
                return;
            } catch (Throwable th22) {
                if (this.responseHandler != null) {
                    this.responseHandler.sendFailureMessage(th22, "can't resolve host");
                    return;
                }
                return;
            } catch (Throwable th222) {
                if (this.responseHandler != null) {
                    this.responseHandler.sendFailureMessage(th222, "socket time out");
                    return;
                }
                return;
            } catch (IOException e) {
                th222 = e;
                i = this.executionCount + 1;
                this.executionCount = i;
                z = httpRequestRetryHandler.retryRequest(th222, i, this.context);
            } catch (NullPointerException e2) {
                th222 = new IOException("NPE in HttpClient" + e2.getMessage());
                i = this.executionCount + 1;
                this.executionCount = i;
                z = httpRequestRetryHandler.retryRequest(th222, i, this.context);
            }
        }
        ConnectException connectException = new ConnectException();
        connectException.initCause(th222);
        throw connectException;
    }

    public void run() {
        try {
            if (this.responseHandler != null) {
                this.responseHandler.sendStartMessage();
            }
            makeRequestWithRetries();
            if (this.responseHandler != null) {
                this.responseHandler.sendFinishMessage();
            }
        } catch (Throwable e) {
            if (this.responseHandler != null) {
                this.responseHandler.sendFinishMessage();
                if (this.isBinaryRequest) {
                    this.responseHandler.sendFailureMessage(e, (byte[]) null);
                } else {
                    this.responseHandler.sendFailureMessage(e, (String) null);
                }
            }
        }
    }
}
