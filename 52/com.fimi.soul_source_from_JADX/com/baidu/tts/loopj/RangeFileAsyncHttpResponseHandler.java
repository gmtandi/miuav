package com.baidu.tts.loopj;

import com.fimi.soul.module.setting.newhand.ae;
import java.io.File;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpUriRequest;
import org.p122a.p123a.C3004e;

public abstract class RangeFileAsyncHttpResponseHandler extends FileAsyncHttpResponseHandler {
    private static final String LOG_TAG = "RangeFileAsyncHttpRH";
    private boolean append;
    private long current;

    public RangeFileAsyncHttpResponseHandler(File file) {
        super(file);
        this.current = 0;
        this.append = false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected byte[] getResponseData(org.apache.http.HttpEntity r11) {
        /*
        r10 = this;
        if (r11 == 0) goto L_0x0059;
    L_0x0002:
        r1 = r11.getContent();
        r2 = r11.getContentLength();
        r4 = r10.current;
        r2 = r2 + r4;
        r4 = new java.io.FileOutputStream;
        r0 = r10.getTargetFile();
        r5 = r10.append;
        r4.<init>(r0, r5);
        if (r1 == 0) goto L_0x0059;
    L_0x001a:
        r0 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r0 = new byte[r0];	 Catch:{ all -> 0x0045 }
    L_0x001e:
        r6 = r10.current;	 Catch:{ all -> 0x0045 }
        r5 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1));
        if (r5 >= 0) goto L_0x0050;
    L_0x0024:
        r5 = r1.read(r0);	 Catch:{ all -> 0x0045 }
        r6 = -1;
        if (r5 == r6) goto L_0x0050;
    L_0x002b:
        r6 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x0045 }
        r6 = r6.isInterrupted();	 Catch:{ all -> 0x0045 }
        if (r6 != 0) goto L_0x0050;
    L_0x0035:
        r6 = r10.current;	 Catch:{ all -> 0x0045 }
        r8 = (long) r5;	 Catch:{ all -> 0x0045 }
        r6 = r6 + r8;
        r10.current = r6;	 Catch:{ all -> 0x0045 }
        r6 = 0;
        r4.write(r0, r6, r5);	 Catch:{ all -> 0x0045 }
        r6 = r10.current;	 Catch:{ all -> 0x0045 }
        r10.sendProgressMessage(r6, r2);	 Catch:{ all -> 0x0045 }
        goto L_0x001e;
    L_0x0045:
        r0 = move-exception;
        r1.close();
        r4.flush();
        r4.close();
        throw r0;
    L_0x0050:
        r1.close();
        r4.flush();
        r4.close();
    L_0x0059:
        r0 = 0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.tts.loopj.RangeFileAsyncHttpResponseHandler.getResponseData(org.apache.http.HttpEntity):byte[]");
    }

    public void sendResponseMessage(HttpResponse httpResponse) {
        if (!Thread.currentThread().isInterrupted()) {
            StatusLine statusLine = httpResponse.getStatusLine();
            if (statusLine.getStatusCode() == 416) {
                if (!Thread.currentThread().isInterrupted()) {
                    sendSuccessMessage(statusLine.getStatusCode(), httpResponse.getAllHeaders(), null);
                }
            } else if (statusLine.getStatusCode() >= ae.f9482j) {
                if (!Thread.currentThread().isInterrupted()) {
                    sendFailureMessage(statusLine.getStatusCode(), httpResponse.getAllHeaders(), null, new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase()));
                }
            } else if (!Thread.currentThread().isInterrupted()) {
                Header firstHeader = httpResponse.getFirstHeader(C3004e.f15030p);
                if (firstHeader == null) {
                    this.append = false;
                    this.current = 0;
                } else {
                    AsyncHttpClient.log.m6894v(LOG_TAG, "Content-Range: " + firstHeader.getValue());
                }
                sendSuccessMessage(statusLine.getStatusCode(), httpResponse.getAllHeaders(), getResponseData(httpResponse.getEntity()));
            }
        }
    }

    public void updateRequestHeaders(HttpUriRequest httpUriRequest) {
        if (this.file.exists() && this.file.canWrite()) {
            this.current = this.file.length();
        }
        if (this.current > 0) {
            this.append = true;
            httpUriRequest.setHeader(C3004e.f15003O, "bytes=" + this.current + "-");
        }
    }
}
