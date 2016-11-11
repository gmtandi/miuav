package com.facebook.imagepipeline.listener;

import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

public class ForwardingRequestListener implements RequestListener {
    private static final String TAG = "ForwardingRequestListener";
    private final List<RequestListener> mRequestListeners;

    public ForwardingRequestListener(Set<RequestListener> set) {
        this.mRequestListeners = new ArrayList(set.size());
        for (RequestListener add : set) {
            this.mRequestListeners.add(add);
        }
    }

    private void onException(String str, Throwable th) {
        FLog.m7598e(TAG, str, th);
    }

    public void onProducerEvent(String str, String str2, String str3) {
        int size = this.mRequestListeners.size();
        for (int i = 0; i < size; i++) {
            try {
                ((RequestListener) this.mRequestListeners.get(i)).onProducerEvent(str, str2, str3);
            } catch (Throwable e) {
                onException("InternalListener exception in onIntermediateChunkStart", e);
            }
        }
    }

    public void onProducerFinishWithCancellation(String str, String str2, @Nullable Map<String, String> map) {
        int size = this.mRequestListeners.size();
        for (int i = 0; i < size; i++) {
            try {
                ((RequestListener) this.mRequestListeners.get(i)).onProducerFinishWithCancellation(str, str2, map);
            } catch (Throwable e) {
                onException("InternalListener exception in onProducerFinishWithCancellation", e);
            }
        }
    }

    public void onProducerFinishWithFailure(String str, String str2, Throwable th, @Nullable Map<String, String> map) {
        int size = this.mRequestListeners.size();
        for (int i = 0; i < size; i++) {
            try {
                ((RequestListener) this.mRequestListeners.get(i)).onProducerFinishWithFailure(str, str2, th, map);
            } catch (Throwable e) {
                onException("InternalListener exception in onProducerFinishWithFailure", e);
            }
        }
    }

    public void onProducerFinishWithSuccess(String str, String str2, @Nullable Map<String, String> map) {
        int size = this.mRequestListeners.size();
        for (int i = 0; i < size; i++) {
            try {
                ((RequestListener) this.mRequestListeners.get(i)).onProducerFinishWithSuccess(str, str2, map);
            } catch (Throwable e) {
                onException("InternalListener exception in onProducerFinishWithSuccess", e);
            }
        }
    }

    public void onProducerStart(String str, String str2) {
        int size = this.mRequestListeners.size();
        for (int i = 0; i < size; i++) {
            try {
                ((RequestListener) this.mRequestListeners.get(i)).onProducerStart(str, str2);
            } catch (Throwable e) {
                onException("InternalListener exception in onProducerStart", e);
            }
        }
    }

    public void onRequestCancellation(String str) {
        int size = this.mRequestListeners.size();
        for (int i = 0; i < size; i++) {
            try {
                ((RequestListener) this.mRequestListeners.get(i)).onRequestCancellation(str);
            } catch (Throwable e) {
                onException("InternalListener exception in onRequestCancellation", e);
            }
        }
    }

    public void onRequestFailure(ImageRequest imageRequest, String str, Throwable th, boolean z) {
        int size = this.mRequestListeners.size();
        for (int i = 0; i < size; i++) {
            try {
                ((RequestListener) this.mRequestListeners.get(i)).onRequestFailure(imageRequest, str, th, z);
            } catch (Throwable e) {
                onException("InternalListener exception in onRequestFailure", e);
            }
        }
    }

    public void onRequestStart(ImageRequest imageRequest, Object obj, String str, boolean z) {
        int size = this.mRequestListeners.size();
        for (int i = 0; i < size; i++) {
            try {
                ((RequestListener) this.mRequestListeners.get(i)).onRequestStart(imageRequest, obj, str, z);
            } catch (Throwable e) {
                onException("InternalListener exception in onRequestStart", e);
            }
        }
    }

    public void onRequestSuccess(ImageRequest imageRequest, String str, boolean z) {
        int size = this.mRequestListeners.size();
        for (int i = 0; i < size; i++) {
            try {
                ((RequestListener) this.mRequestListeners.get(i)).onRequestSuccess(imageRequest, str, z);
            } catch (Throwable e) {
                onException("InternalListener exception in onRequestSuccess", e);
            }
        }
    }

    public boolean requiresExtraMap(String str) {
        int size = this.mRequestListeners.size();
        for (int i = 0; i < size; i++) {
            if (((RequestListener) this.mRequestListeners.get(i)).requiresExtraMap(str)) {
                return true;
            }
        }
        return false;
    }
}
