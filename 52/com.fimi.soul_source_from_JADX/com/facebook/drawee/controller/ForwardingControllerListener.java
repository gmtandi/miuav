package com.facebook.drawee.controller;

import android.graphics.drawable.Animatable;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class ForwardingControllerListener<INFO> implements ControllerListener<INFO> {
    private static final String TAG = "FdingControllerListener";
    private final List<ControllerListener<? super INFO>> mListeners;

    public ForwardingControllerListener() {
        this.mListeners = new ArrayList(2);
    }

    public static <INFO> ForwardingControllerListener<INFO> create() {
        return new ForwardingControllerListener();
    }

    public static <INFO> ForwardingControllerListener<INFO> of(ControllerListener<? super INFO> controllerListener) {
        ForwardingControllerListener<INFO> create = create();
        create.addListener(controllerListener);
        return create;
    }

    public static <INFO> ForwardingControllerListener<INFO> of(ControllerListener<? super INFO> controllerListener, ControllerListener<? super INFO> controllerListener2) {
        ForwardingControllerListener<INFO> create = create();
        create.addListener(controllerListener);
        create.addListener(controllerListener2);
        return create;
    }

    private synchronized void onException(String str, Throwable th) {
        Log.e(TAG, str, th);
    }

    public synchronized void addListener(ControllerListener<? super INFO> controllerListener) {
        this.mListeners.add(controllerListener);
    }

    public synchronized void clearListeners() {
        this.mListeners.clear();
    }

    public synchronized void onFailure(String str, Throwable th) {
        int size = this.mListeners.size();
        for (int i = 0; i < size; i++) {
            try {
                ((ControllerListener) this.mListeners.get(i)).onFailure(str, th);
            } catch (Throwable e) {
                onException("InternalListener exception in onFailure", e);
            }
        }
    }

    public synchronized void onFinalImageSet(String str, @Nullable INFO info, @Nullable Animatable animatable) {
        int size = this.mListeners.size();
        for (int i = 0; i < size; i++) {
            try {
                ((ControllerListener) this.mListeners.get(i)).onFinalImageSet(str, info, animatable);
            } catch (Throwable e) {
                onException("InternalListener exception in onFinalImageSet", e);
            }
        }
    }

    public void onIntermediateImageFailed(String str, Throwable th) {
        int size = this.mListeners.size();
        for (int i = 0; i < size; i++) {
            try {
                ((ControllerListener) this.mListeners.get(i)).onIntermediateImageFailed(str, th);
            } catch (Throwable e) {
                onException("InternalListener exception in onIntermediateImageFailed", e);
            }
        }
    }

    public void onIntermediateImageSet(String str, @Nullable INFO info) {
        int size = this.mListeners.size();
        for (int i = 0; i < size; i++) {
            try {
                ((ControllerListener) this.mListeners.get(i)).onIntermediateImageSet(str, info);
            } catch (Throwable e) {
                onException("InternalListener exception in onIntermediateImageSet", e);
            }
        }
    }

    public synchronized void onRelease(String str) {
        int size = this.mListeners.size();
        for (int i = 0; i < size; i++) {
            try {
                ((ControllerListener) this.mListeners.get(i)).onRelease(str);
            } catch (Throwable e) {
                onException("InternalListener exception in onRelease", e);
            }
        }
    }

    public synchronized void onSubmit(String str, Object obj) {
        int size = this.mListeners.size();
        for (int i = 0; i < size; i++) {
            try {
                ((ControllerListener) this.mListeners.get(i)).onSubmit(str, obj);
            } catch (Throwable e) {
                onException("InternalListener exception in onSubmit", e);
            }
        }
    }

    public synchronized void removeListener(ControllerListener<? super INFO> controllerListener) {
        this.mListeners.remove(controllerListener);
    }
}
