package com.baidu.tts.p027b.p028a.p032b;

import com.baidu.tts.loopj.AsyncHttpResponseHandler;
import com.baidu.tts.loopj.RequestParams;
import com.fimi.soul.module.setting.newhand.ae;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;

/* renamed from: com.baidu.tts.b.a.b.g */
public abstract class C0728g extends AsyncHttpResponseHandler {
    private String f4162a;
    private HttpEntity f4163b;

    String m6385a(HttpEntity httpEntity) {
        Header contentType = httpEntity.getContentType();
        if (contentType != null) {
            if (RequestParams.APPLICATION_JSON.equals(contentType.getValue())) {
                return RequestParams.APPLICATION_JSON;
            }
        }
        return null;
    }

    public abstract void m6386a(int i, Header[] headerArr, String str, HttpEntity httpEntity);

    public abstract void m6387a(int i, Header[] headerArr, String str, HttpEntity httpEntity, Throwable th);

    public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
        m6387a(i, headerArr, this.f4162a, this.f4163b, th);
    }

    public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        m6386a(i, headerArr, this.f4162a, this.f4163b);
    }

    public void sendResponseMessage(HttpResponse httpResponse) {
        if (!Thread.currentThread().isInterrupted()) {
            StatusLine statusLine = httpResponse.getStatusLine();
            this.f4163b = httpResponse.getEntity();
            this.f4162a = m6385a(this.f4163b);
            if (!Thread.currentThread().isInterrupted()) {
                if (statusLine.getStatusCode() >= ae.f9482j) {
                    sendFailureMessage(statusLine.getStatusCode(), httpResponse.getAllHeaders(), null, new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase()));
                } else {
                    sendSuccessMessage(statusLine.getStatusCode(), httpResponse.getAllHeaders(), null);
                }
            }
        }
    }
}
