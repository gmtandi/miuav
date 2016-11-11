package com.loopj.android.http;

import android.os.Message;
import com.fimi.soul.module.setting.newhand.ae;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C3004e;

public class BinaryHttpResponseHandler extends AsyncHttpResponseHandler {
    private static String[] mAllowedContentTypes;

    static {
        mAllowedContentTypes = new String[]{"image/jpeg", "image/png"};
    }

    public BinaryHttpResponseHandler(String[] strArr) {
        this();
        mAllowedContentTypes = strArr;
    }

    protected void handleFailureMessage(Throwable th, byte[] bArr) {
        onFailure(th, bArr);
    }

    protected void handleMessage(Message message) {
        Object[] objArr;
        switch (message.what) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                objArr = (Object[]) message.obj;
                handleSuccessMessage(((Integer) objArr[0]).intValue(), (byte[]) objArr[1]);
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                objArr = (Object[]) message.obj;
                handleFailureMessage((Throwable) objArr[0], (byte[]) objArr[1]);
            default:
                super.handleMessage(message);
        }
    }

    protected void handleSuccessMessage(int i, byte[] bArr) {
        onSuccess(i, bArr);
    }

    public void onFailure(Throwable th, byte[] bArr) {
        onFailure(th);
    }

    public void onSuccess(int i, byte[] bArr) {
        onSuccess(bArr);
    }

    public void onSuccess(byte[] bArr) {
    }

    protected void sendFailureMessage(Throwable th, byte[] bArr) {
        sendMessage(obtainMessage(1, new Object[]{th, bArr}));
    }

    void sendResponseMessage(HttpResponse httpResponse) {
        byte[] bArr = null;
        int i = 0;
        StatusLine statusLine = httpResponse.getStatusLine();
        Header[] headers = httpResponse.getHeaders(C3004e.f15031q);
        if (headers.length != 1) {
            sendFailureMessage(new HttpResponseException(statusLine.getStatusCode(), "None, or more than one, Content-Type Header found!"), null);
            return;
        }
        Header header = headers[0];
        for (String equals : mAllowedContentTypes) {
            if (equals.equals(header.getValue())) {
                i = 1;
            }
        }
        if (i == 0) {
            sendFailureMessage(new HttpResponseException(statusLine.getStatusCode(), "Content-Type not allowed!"), null);
            return;
        }
        try {
            HttpEntity entity = httpResponse.getEntity();
            bArr = EntityUtils.toByteArray(entity != null ? new BufferedHttpEntity(entity) : null);
        } catch (Throwable e) {
            sendFailureMessage(e, null);
        }
        if (statusLine.getStatusCode() >= ae.f9482j) {
            sendFailureMessage(new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase()), bArr);
        } else {
            sendSuccessMessage(statusLine.getStatusCode(), bArr);
        }
    }

    protected void sendSuccessMessage(int i, byte[] bArr) {
        sendMessage(obtainMessage(0, new Object[]{Integer.valueOf(i), bArr}));
    }
}
