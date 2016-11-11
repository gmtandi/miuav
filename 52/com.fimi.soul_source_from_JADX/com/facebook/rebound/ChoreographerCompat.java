package com.facebook.rebound;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;

public class ChoreographerCompat {
    private static final boolean IS_JELLYBEAN_OR_HIGHER;
    private static final long ONE_FRAME_MILLIS = 17;
    private static ChoreographerCompat __instance;
    private Choreographer mChoreographer;
    private Handler mHandler;

    public abstract class FrameCallback {
        private android.view.Choreographer.FrameCallback mFrameCallback;
        private Runnable mRunnable;

        /* renamed from: com.facebook.rebound.ChoreographerCompat.FrameCallback.1 */
        class C10831 implements android.view.Choreographer.FrameCallback {
            C10831() {
            }

            public void doFrame(long j) {
                FrameCallback.this.doFrame(j);
            }
        }

        /* renamed from: com.facebook.rebound.ChoreographerCompat.FrameCallback.2 */
        class C10842 implements Runnable {
            C10842() {
            }

            public void run() {
                FrameCallback.this.doFrame(System.nanoTime());
            }
        }

        public abstract void doFrame(long j);

        @TargetApi(16)
        android.view.Choreographer.FrameCallback getFrameCallback() {
            if (this.mFrameCallback == null) {
                this.mFrameCallback = new C10831();
            }
            return this.mFrameCallback;
        }

        Runnable getRunnable() {
            if (this.mRunnable == null) {
                this.mRunnable = new C10842();
            }
            return this.mRunnable;
        }
    }

    static {
        IS_JELLYBEAN_OR_HIGHER = VERSION.SDK_INT >= 16 ? true : IS_JELLYBEAN_OR_HIGHER;
        __instance = new ChoreographerCompat();
    }

    private ChoreographerCompat() {
        if (IS_JELLYBEAN_OR_HIGHER) {
            this.mChoreographer = getChoreographer();
        } else {
            this.mHandler = new Handler(Looper.getMainLooper());
        }
    }

    @TargetApi(16)
    private void choreographerPostFrameCallback(android.view.Choreographer.FrameCallback frameCallback) {
        this.mChoreographer.postFrameCallback(frameCallback);
    }

    @TargetApi(16)
    private void choreographerPostFrameCallbackDelayed(android.view.Choreographer.FrameCallback frameCallback, long j) {
        this.mChoreographer.postFrameCallbackDelayed(frameCallback, j);
    }

    @TargetApi(16)
    private void choreographerRemoveFrameCallback(android.view.Choreographer.FrameCallback frameCallback) {
        this.mChoreographer.removeFrameCallback(frameCallback);
    }

    @TargetApi(16)
    private Choreographer getChoreographer() {
        return Choreographer.getInstance();
    }

    public static ChoreographerCompat getInstance() {
        return __instance;
    }

    public void postFrameCallback(FrameCallback frameCallback) {
        if (IS_JELLYBEAN_OR_HIGHER) {
            choreographerPostFrameCallback(frameCallback.getFrameCallback());
        } else {
            this.mHandler.postDelayed(frameCallback.getRunnable(), 0);
        }
    }

    public void postFrameCallbackDelayed(FrameCallback frameCallback, long j) {
        if (IS_JELLYBEAN_OR_HIGHER) {
            choreographerPostFrameCallbackDelayed(frameCallback.getFrameCallback(), j);
        } else {
            this.mHandler.postDelayed(frameCallback.getRunnable(), ONE_FRAME_MILLIS + j);
        }
    }

    public void removeFrameCallback(FrameCallback frameCallback) {
        if (IS_JELLYBEAN_OR_HIGHER) {
            choreographerRemoveFrameCallback(frameCallback.getFrameCallback());
        } else {
            this.mHandler.removeCallbacks(frameCallback.getRunnable());
        }
    }
}
