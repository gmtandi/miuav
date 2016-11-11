package com.loopj.android.http;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.fimi.soul.module.setting.newhand.ae;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.smile.SmileConstants;

public class AsyncHttpResponseHandler {
    protected static final int FAILURE_MESSAGE = 1;
    protected static final int FINISH_MESSAGE = 3;
    protected static final int START_MESSAGE = 2;
    protected static final int SUCCESS_MESSAGE = 0;
    private Handler handler;

    /* renamed from: com.loopj.android.http.AsyncHttpResponseHandler.1 */
    class C21121 extends Handler {
        C21121() {
        }

        public void handleMessage(Message message) {
            AsyncHttpResponseHandler.this.handleMessage(message);
        }
    }

    public AsyncHttpResponseHandler() {
        if (Looper.myLooper() != null) {
            this.handler = new C21121();
        }
    }

    protected void handleFailureMessage(Throwable th, String str) {
        onFailure(th, str);
    }

    protected void handleMessage(Message message) {
        Object[] objArr;
        switch (message.what) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                objArr = (Object[]) message.obj;
                handleSuccessMessage(((Integer) objArr[0]).intValue(), (String) objArr[FAILURE_MESSAGE]);
            case FAILURE_MESSAGE /*1*/:
                objArr = (Object[]) message.obj;
                handleFailureMessage((Throwable) objArr[0], (String) objArr[FAILURE_MESSAGE]);
            case START_MESSAGE /*2*/:
                onStart();
            case FINISH_MESSAGE /*3*/:
                onFinish();
            default:
        }
    }

    protected void handleSuccessMessage(int i, String str) {
        onSuccess(i, str);
    }

    protected Message obtainMessage(int i, Object obj) {
        if (this.handler != null) {
            return this.handler.obtainMessage(i, obj);
        }
        Message message = new Message();
        message.what = i;
        message.obj = obj;
        return message;
    }

    public void onFailure(Throwable th) {
    }

    public void onFailure(Throwable th, String str) {
        onFailure(th);
    }

    public void onFinish() {
    }

    public void onStart() {
    }

    public void onSuccess(int i, String str) {
        onSuccess(str);
    }

    public void onSuccess(String str) {
    }

    protected void sendFailureMessage(Throwable th, String str) {
        Object obj = new Object[START_MESSAGE];
        obj[0] = th;
        obj[FAILURE_MESSAGE] = str;
        sendMessage(obtainMessage(FAILURE_MESSAGE, obj));
    }

    protected void sendFailureMessage(Throwable th, byte[] bArr) {
        Object obj = new Object[START_MESSAGE];
        obj[0] = th;
        obj[FAILURE_MESSAGE] = bArr;
        sendMessage(obtainMessage(FAILURE_MESSAGE, obj));
    }

    protected void sendFinishMessage() {
        sendMessage(obtainMessage(FINISH_MESSAGE, null));
    }

    protected void sendMessage(Message message) {
        if (this.handler != null) {
            this.handler.sendMessage(message);
        } else {
            handleMessage(message);
        }
    }

    void sendResponseMessage(HttpResponse httpResponse) {
        String str = null;
        StatusLine statusLine = httpResponse.getStatusLine();
        try {
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                str = EntityUtils.toString(new BufferedHttpEntity(entity), C1142e.f5201a);
            }
        } catch (Throwable e) {
            sendFailureMessage(e, null);
        }
        if (statusLine.getStatusCode() >= ae.f9482j) {
            sendFailureMessage(new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase()), str);
        } else {
            sendSuccessMessage(statusLine.getStatusCode(), str);
        }
    }

    protected void sendStartMessage() {
        sendMessage(obtainMessage(START_MESSAGE, null));
    }

    protected void sendSuccessMessage(int i, String str) {
        Object obj = new Object[START_MESSAGE];
        obj[0] = new Integer(i);
        obj[FAILURE_MESSAGE] = str;
        sendMessage(obtainMessage(0, obj));
    }
}
