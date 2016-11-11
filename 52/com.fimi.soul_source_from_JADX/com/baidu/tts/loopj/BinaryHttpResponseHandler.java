package com.baidu.tts.loopj;

import android.os.Looper;
import java.util.regex.Pattern;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.p122a.p123a.C3004e;

public abstract class BinaryHttpResponseHandler extends AsyncHttpResponseHandler {
    private static final String LOG_TAG = "BinaryHttpRH";
    private String[] mAllowedContentTypes;

    public BinaryHttpResponseHandler() {
        this.mAllowedContentTypes = new String[]{RequestParams.APPLICATION_OCTET_STREAM, "image/jpeg", "image/png", "image/gif"};
    }

    public BinaryHttpResponseHandler(String[] strArr) {
        this.mAllowedContentTypes = new String[]{RequestParams.APPLICATION_OCTET_STREAM, "image/jpeg", "image/png", "image/gif"};
        if (strArr != null) {
            this.mAllowedContentTypes = strArr;
        } else {
            AsyncHttpClient.log.m6890e(LOG_TAG, "Constructor passed allowedContentTypes was null !");
        }
    }

    public BinaryHttpResponseHandler(String[] strArr, Looper looper) {
        super(looper);
        this.mAllowedContentTypes = new String[]{RequestParams.APPLICATION_OCTET_STREAM, "image/jpeg", "image/png", "image/gif"};
        if (strArr != null) {
            this.mAllowedContentTypes = strArr;
        } else {
            AsyncHttpClient.log.m6890e(LOG_TAG, "Constructor passed allowedContentTypes was null !");
        }
    }

    public String[] getAllowedContentTypes() {
        return this.mAllowedContentTypes;
    }

    public abstract void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th);

    public abstract void onSuccess(int i, Header[] headerArr, byte[] bArr);

    public final void sendResponseMessage(HttpResponse httpResponse) {
        int i = 0;
        StatusLine statusLine = httpResponse.getStatusLine();
        Header[] headers = httpResponse.getHeaders(C3004e.f15031q);
        if (headers.length != 1) {
            sendFailureMessage(statusLine.getStatusCode(), httpResponse.getAllHeaders(), null, new HttpResponseException(statusLine.getStatusCode(), "None, or more than one, Content-Type Header found!"));
            return;
        }
        Header header = headers[0];
        for (String str : getAllowedContentTypes()) {
            try {
                if (Pattern.matches(str, header.getValue())) {
                    i = 1;
                }
            } catch (Throwable e) {
                AsyncHttpClient.log.m6891e(LOG_TAG, "Given pattern is not valid: " + str, e);
            }
        }
        if (i == 0) {
            sendFailureMessage(statusLine.getStatusCode(), httpResponse.getAllHeaders(), null, new HttpResponseException(statusLine.getStatusCode(), "Content-Type (" + header.getValue() + ") not allowed!"));
        } else {
            super.sendResponseMessage(httpResponse);
        }
    }
}
