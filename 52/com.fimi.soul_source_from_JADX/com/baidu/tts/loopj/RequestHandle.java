package com.baidu.tts.loopj;

import android.os.Looper;
import java.lang.ref.WeakReference;

public class RequestHandle {
    private final WeakReference<AsyncHttpRequest> request;

    /* renamed from: com.baidu.tts.loopj.RequestHandle.1 */
    class C08491 implements Runnable {
        final /* synthetic */ AsyncHttpRequest val$_request;
        final /* synthetic */ boolean val$mayInterruptIfRunning;

        C08491(AsyncHttpRequest asyncHttpRequest, boolean z) {
            this.val$_request = asyncHttpRequest;
            this.val$mayInterruptIfRunning = z;
        }

        public void run() {
            this.val$_request.cancel(this.val$mayInterruptIfRunning);
        }
    }

    public RequestHandle(AsyncHttpRequest asyncHttpRequest) {
        this.request = new WeakReference(asyncHttpRequest);
    }

    public boolean cancel(boolean z) {
        AsyncHttpRequest asyncHttpRequest = (AsyncHttpRequest) this.request.get();
        if (asyncHttpRequest == null) {
            return false;
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            return asyncHttpRequest.cancel(z);
        }
        new Thread(new C08491(asyncHttpRequest, z)).start();
        return true;
    }

    public Object getTag() {
        AsyncHttpRequest asyncHttpRequest = (AsyncHttpRequest) this.request.get();
        return asyncHttpRequest == null ? null : asyncHttpRequest.getTag();
    }

    public boolean isCancelled() {
        AsyncHttpRequest asyncHttpRequest = (AsyncHttpRequest) this.request.get();
        return asyncHttpRequest == null || asyncHttpRequest.isCancelled();
    }

    public boolean isFinished() {
        AsyncHttpRequest asyncHttpRequest = (AsyncHttpRequest) this.request.get();
        return asyncHttpRequest == null || asyncHttpRequest.isDone();
    }

    public RequestHandle setTag(Object obj) {
        AsyncHttpRequest asyncHttpRequest = (AsyncHttpRequest) this.request.get();
        if (asyncHttpRequest != null) {
            asyncHttpRequest.setRequestTag(obj);
        }
        return this;
    }

    public boolean shouldBeGarbageCollected() {
        boolean z = isCancelled() || isFinished();
        if (z) {
            this.request.clear();
        }
        return z;
    }
}
