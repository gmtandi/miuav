package com.baidu.tts.loopj;

import com.fimi.kernel.p076b.p080d.C1142e;
import com.fimi.soul.drone.p107c.p108a.p109a.C1458u;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonHttpResponseHandler extends TextHttpResponseHandler {
    private static final String LOG_TAG = "JsonHttpRH";
    private boolean useRFC5179CompatibilityMode;

    /* renamed from: com.baidu.tts.loopj.JsonHttpResponseHandler.1 */
    class C08441 implements Runnable {
        final /* synthetic */ Header[] val$headers;
        final /* synthetic */ byte[] val$responseBytes;
        final /* synthetic */ int val$statusCode;

        /* renamed from: com.baidu.tts.loopj.JsonHttpResponseHandler.1.1 */
        class C08421 implements Runnable {
            final /* synthetic */ Object val$jsonResponse;

            C08421(Object obj) {
                this.val$jsonResponse = obj;
            }

            public void run() {
                if (!JsonHttpResponseHandler.this.useRFC5179CompatibilityMode && this.val$jsonResponse == null) {
                    JsonHttpResponseHandler.this.onSuccess(C08441.this.val$statusCode, C08441.this.val$headers, (String) null);
                } else if (this.val$jsonResponse instanceof JSONObject) {
                    JsonHttpResponseHandler.this.onSuccess(C08441.this.val$statusCode, C08441.this.val$headers, (JSONObject) this.val$jsonResponse);
                } else if (this.val$jsonResponse instanceof JSONArray) {
                    JsonHttpResponseHandler.this.onSuccess(C08441.this.val$statusCode, C08441.this.val$headers, (JSONArray) this.val$jsonResponse);
                } else if (!(this.val$jsonResponse instanceof String)) {
                    JsonHttpResponseHandler.this.onFailure(C08441.this.val$statusCode, C08441.this.val$headers, new JSONException("Unexpected response type " + this.val$jsonResponse.getClass().getName()), (JSONObject) null);
                } else if (JsonHttpResponseHandler.this.useRFC5179CompatibilityMode) {
                    JsonHttpResponseHandler.this.onFailure(C08441.this.val$statusCode, C08441.this.val$headers, (String) this.val$jsonResponse, new JSONException("Response cannot be parsed as JSON data"));
                } else {
                    JsonHttpResponseHandler.this.onSuccess(C08441.this.val$statusCode, C08441.this.val$headers, (String) this.val$jsonResponse);
                }
            }
        }

        /* renamed from: com.baidu.tts.loopj.JsonHttpResponseHandler.1.2 */
        class C08432 implements Runnable {
            final /* synthetic */ JSONException val$ex;

            C08432(JSONException jSONException) {
                this.val$ex = jSONException;
            }

            public void run() {
                JsonHttpResponseHandler.this.onFailure(C08441.this.val$statusCode, C08441.this.val$headers, this.val$ex, (JSONObject) null);
            }
        }

        C08441(byte[] bArr, int i, Header[] headerArr) {
            this.val$responseBytes = bArr;
            this.val$statusCode = i;
            this.val$headers = headerArr;
        }

        public void run() {
            try {
                JsonHttpResponseHandler.this.postRunnable(new C08421(JsonHttpResponseHandler.this.parseResponse(this.val$responseBytes)));
            } catch (JSONException e) {
                JsonHttpResponseHandler.this.postRunnable(new C08432(e));
            }
        }
    }

    /* renamed from: com.baidu.tts.loopj.JsonHttpResponseHandler.2 */
    class C08472 implements Runnable {
        final /* synthetic */ Header[] val$headers;
        final /* synthetic */ byte[] val$responseBytes;
        final /* synthetic */ int val$statusCode;
        final /* synthetic */ Throwable val$throwable;

        /* renamed from: com.baidu.tts.loopj.JsonHttpResponseHandler.2.1 */
        class C08451 implements Runnable {
            final /* synthetic */ Object val$jsonResponse;

            C08451(Object obj) {
                this.val$jsonResponse = obj;
            }

            public void run() {
                if (!JsonHttpResponseHandler.this.useRFC5179CompatibilityMode && this.val$jsonResponse == null) {
                    JsonHttpResponseHandler.this.onFailure(C08472.this.val$statusCode, C08472.this.val$headers, (String) null, C08472.this.val$throwable);
                } else if (this.val$jsonResponse instanceof JSONObject) {
                    JsonHttpResponseHandler.this.onFailure(C08472.this.val$statusCode, C08472.this.val$headers, C08472.this.val$throwable, (JSONObject) this.val$jsonResponse);
                } else if (this.val$jsonResponse instanceof JSONArray) {
                    JsonHttpResponseHandler.this.onFailure(C08472.this.val$statusCode, C08472.this.val$headers, C08472.this.val$throwable, (JSONArray) this.val$jsonResponse);
                } else if (this.val$jsonResponse instanceof String) {
                    JsonHttpResponseHandler.this.onFailure(C08472.this.val$statusCode, C08472.this.val$headers, (String) this.val$jsonResponse, C08472.this.val$throwable);
                } else {
                    JsonHttpResponseHandler.this.onFailure(C08472.this.val$statusCode, C08472.this.val$headers, new JSONException("Unexpected response type " + this.val$jsonResponse.getClass().getName()), (JSONObject) null);
                }
            }
        }

        /* renamed from: com.baidu.tts.loopj.JsonHttpResponseHandler.2.2 */
        class C08462 implements Runnable {
            final /* synthetic */ JSONException val$ex;

            C08462(JSONException jSONException) {
                this.val$ex = jSONException;
            }

            public void run() {
                JsonHttpResponseHandler.this.onFailure(C08472.this.val$statusCode, C08472.this.val$headers, this.val$ex, (JSONObject) null);
            }
        }

        C08472(byte[] bArr, int i, Header[] headerArr, Throwable th) {
            this.val$responseBytes = bArr;
            this.val$statusCode = i;
            this.val$headers = headerArr;
            this.val$throwable = th;
        }

        public void run() {
            try {
                JsonHttpResponseHandler.this.postRunnable(new C08451(JsonHttpResponseHandler.this.parseResponse(this.val$responseBytes)));
            } catch (JSONException e) {
                JsonHttpResponseHandler.this.postRunnable(new C08462(e));
            }
        }
    }

    public JsonHttpResponseHandler() {
        super(C1142e.f5201a);
        this.useRFC5179CompatibilityMode = true;
    }

    public JsonHttpResponseHandler(String str) {
        super(str);
        this.useRFC5179CompatibilityMode = true;
    }

    public JsonHttpResponseHandler(String str, boolean z) {
        super(str);
        this.useRFC5179CompatibilityMode = true;
        this.useRFC5179CompatibilityMode = z;
    }

    public JsonHttpResponseHandler(boolean z) {
        super(C1142e.f5201a);
        this.useRFC5179CompatibilityMode = true;
        this.useRFC5179CompatibilityMode = z;
    }

    public boolean isUseRFC5179CompatibilityMode() {
        return this.useRFC5179CompatibilityMode;
    }

    public void onFailure(int i, Header[] headerArr, String str, Throwable th) {
        AsyncHttpClient.log.m6897w(LOG_TAG, "onFailure(int, Header[], String, Throwable) was not overriden, but callback was received", th);
    }

    public void onFailure(int i, Header[] headerArr, Throwable th, JSONArray jSONArray) {
        AsyncHttpClient.log.m6897w(LOG_TAG, "onFailure(int, Header[], Throwable, JSONArray) was not overriden, but callback was received", th);
    }

    public void onFailure(int i, Header[] headerArr, Throwable th, JSONObject jSONObject) {
        AsyncHttpClient.log.m6897w(LOG_TAG, "onFailure(int, Header[], Throwable, JSONObject) was not overriden, but callback was received", th);
    }

    public final void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
        if (bArr != null) {
            Runnable c08472 = new C08472(bArr, i, headerArr, th);
            if (getUseSynchronousMode() || getUsePoolThread()) {
                c08472.run();
                return;
            } else {
                new Thread(c08472).start();
                return;
            }
        }
        AsyncHttpClient.log.m6894v(LOG_TAG, "response body is null, calling onFailure(Throwable, JSONObject)");
        onFailure(i, headerArr, th, (JSONObject) null);
    }

    public void onSuccess(int i, Header[] headerArr, String str) {
        AsyncHttpClient.log.m6896w(LOG_TAG, "onSuccess(int, Header[], String) was not overriden, but callback was received");
    }

    public void onSuccess(int i, Header[] headerArr, JSONArray jSONArray) {
        AsyncHttpClient.log.m6896w(LOG_TAG, "onSuccess(int, Header[], JSONArray) was not overriden, but callback was received");
    }

    public void onSuccess(int i, Header[] headerArr, JSONObject jSONObject) {
        AsyncHttpClient.log.m6896w(LOG_TAG, "onSuccess(int, Header[], JSONObject) was not overriden, but callback was received");
    }

    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        if (i != C1458u.f6934b) {
            Runnable c08441 = new C08441(bArr, i, headerArr);
            if (getUseSynchronousMode() || getUsePoolThread()) {
                c08441.run();
                return;
            } else {
                new Thread(c08441).start();
                return;
            }
        }
        onSuccess(i, headerArr, new JSONObject());
    }

    protected Object parseResponse(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        String str;
        Object nextValue;
        Object obj;
        String str2;
        String responseString = TextHttpResponseHandler.getResponseString(bArr, getCharset());
        if (responseString != null) {
            responseString = responseString.trim();
            if (this.useRFC5179CompatibilityMode) {
                if (responseString.startsWith("{") || responseString.startsWith("[")) {
                    str = responseString;
                    nextValue = new JSONTokener(responseString).nextValue();
                    obj = str;
                    return nextValue == null ? nextValue : obj;
                }
            } else if ((responseString.startsWith("{") && responseString.endsWith("}")) || (responseString.startsWith("[") && responseString.endsWith("]"))) {
                str = responseString;
                nextValue = new JSONTokener(responseString).nextValue();
                str2 = str;
                if (nextValue == null) {
                }
            } else if (responseString.startsWith("\"") && responseString.endsWith("\"")) {
                str = responseString;
                responseString = responseString.substring(1, responseString.length() - 1);
                str2 = str;
                if (nextValue == null) {
                }
            }
        }
        str = responseString;
        nextValue = null;
        str2 = str;
        if (nextValue == null) {
        }
    }

    public void setUseRFC5179CompatibilityMode(boolean z) {
        this.useRFC5179CompatibilityMode = z;
    }
}
