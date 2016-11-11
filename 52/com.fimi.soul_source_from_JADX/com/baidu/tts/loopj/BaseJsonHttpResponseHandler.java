package com.baidu.tts.loopj;

import com.fimi.kernel.p076b.p080d.C1142e;
import com.fimi.soul.drone.p107c.p108a.p109a.C1458u;
import org.apache.http.Header;

public abstract class BaseJsonHttpResponseHandler<JSON_TYPE> extends TextHttpResponseHandler {
    private static final String LOG_TAG = "BaseJsonHttpRH";

    /* renamed from: com.baidu.tts.loopj.BaseJsonHttpResponseHandler.1 */
    class C08381 implements Runnable {
        final /* synthetic */ Header[] val$headers;
        final /* synthetic */ String val$responseString;
        final /* synthetic */ int val$statusCode;

        /* renamed from: com.baidu.tts.loopj.BaseJsonHttpResponseHandler.1.1 */
        class C08361 implements Runnable {
            final /* synthetic */ Object val$jsonResponse;

            C08361(Object obj) {
                this.val$jsonResponse = obj;
            }

            public void run() {
                BaseJsonHttpResponseHandler.this.onSuccess(C08381.this.val$statusCode, C08381.this.val$headers, C08381.this.val$responseString, this.val$jsonResponse);
            }
        }

        /* renamed from: com.baidu.tts.loopj.BaseJsonHttpResponseHandler.1.2 */
        class C08372 implements Runnable {
            final /* synthetic */ Throwable val$t;

            C08372(Throwable th) {
                this.val$t = th;
            }

            public void run() {
                BaseJsonHttpResponseHandler.this.onFailure(C08381.this.val$statusCode, C08381.this.val$headers, this.val$t, C08381.this.val$responseString, null);
            }
        }

        C08381(String str, int i, Header[] headerArr) {
            this.val$responseString = str;
            this.val$statusCode = i;
            this.val$headers = headerArr;
        }

        public void run() {
            try {
                BaseJsonHttpResponseHandler.this.postRunnable(new C08361(BaseJsonHttpResponseHandler.this.parseResponse(this.val$responseString, false)));
            } catch (Throwable th) {
                AsyncHttpClient.log.m6889d(BaseJsonHttpResponseHandler.LOG_TAG, "parseResponse thrown an problem", th);
                BaseJsonHttpResponseHandler.this.postRunnable(new C08372(th));
            }
        }
    }

    /* renamed from: com.baidu.tts.loopj.BaseJsonHttpResponseHandler.2 */
    class C08412 implements Runnable {
        final /* synthetic */ Header[] val$headers;
        final /* synthetic */ String val$responseString;
        final /* synthetic */ int val$statusCode;
        final /* synthetic */ Throwable val$throwable;

        /* renamed from: com.baidu.tts.loopj.BaseJsonHttpResponseHandler.2.1 */
        class C08391 implements Runnable {
            final /* synthetic */ Object val$jsonResponse;

            C08391(Object obj) {
                this.val$jsonResponse = obj;
            }

            public void run() {
                BaseJsonHttpResponseHandler.this.onFailure(C08412.this.val$statusCode, C08412.this.val$headers, C08412.this.val$throwable, C08412.this.val$responseString, this.val$jsonResponse);
            }
        }

        /* renamed from: com.baidu.tts.loopj.BaseJsonHttpResponseHandler.2.2 */
        class C08402 implements Runnable {
            C08402() {
            }

            public void run() {
                BaseJsonHttpResponseHandler.this.onFailure(C08412.this.val$statusCode, C08412.this.val$headers, C08412.this.val$throwable, C08412.this.val$responseString, null);
            }
        }

        C08412(String str, int i, Header[] headerArr, Throwable th) {
            this.val$responseString = str;
            this.val$statusCode = i;
            this.val$headers = headerArr;
            this.val$throwable = th;
        }

        public void run() {
            try {
                BaseJsonHttpResponseHandler.this.postRunnable(new C08391(BaseJsonHttpResponseHandler.this.parseResponse(this.val$responseString, true)));
            } catch (Throwable th) {
                AsyncHttpClient.log.m6889d(BaseJsonHttpResponseHandler.LOG_TAG, "parseResponse thrown an problem", th);
                BaseJsonHttpResponseHandler.this.postRunnable(new C08402());
            }
        }
    }

    public BaseJsonHttpResponseHandler() {
        this(C1142e.f5201a);
    }

    public BaseJsonHttpResponseHandler(String str) {
        super(str);
    }

    public final void onFailure(int i, Header[] headerArr, String str, Throwable th) {
        if (str != null) {
            Runnable c08412 = new C08412(str, i, headerArr, th);
            if (getUseSynchronousMode() || getUsePoolThread()) {
                c08412.run();
                return;
            } else {
                new Thread(c08412).start();
                return;
            }
        }
        onFailure(i, headerArr, th, null, null);
    }

    public abstract void onFailure(int i, Header[] headerArr, Throwable th, String str, JSON_TYPE json_type);

    public final void onSuccess(int i, Header[] headerArr, String str) {
        if (i != C1458u.f6934b) {
            Runnable c08381 = new C08381(str, i, headerArr);
            if (getUseSynchronousMode() || getUsePoolThread()) {
                c08381.run();
                return;
            } else {
                new Thread(c08381).start();
                return;
            }
        }
        onSuccess(i, headerArr, null, null);
    }

    public abstract void onSuccess(int i, Header[] headerArr, String str, JSON_TYPE json_type);

    protected abstract JSON_TYPE parseResponse(String str, boolean z);
}
