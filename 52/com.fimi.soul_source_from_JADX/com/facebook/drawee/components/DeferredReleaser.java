package com.facebook.drawee.components;

import android.os.Handler;
import android.os.Looper;
import com.facebook.common.internal.Preconditions;
import java.util.HashSet;
import java.util.Set;

public class DeferredReleaser {
    private static DeferredReleaser sInstance;
    private final Set<Releasable> mPendingReleasables;
    private final Handler mUiHandler;
    private final Runnable releaseRunnable;

    public interface Releasable {
        void release();
    }

    /* renamed from: com.facebook.drawee.components.DeferredReleaser.1 */
    class C09821 implements Runnable {
        C09821() {
        }

        public void run() {
            DeferredReleaser.ensureOnUiThread();
            for (Releasable release : DeferredReleaser.this.mPendingReleasables) {
                release.release();
            }
            DeferredReleaser.this.mPendingReleasables.clear();
        }
    }

    static {
        sInstance = null;
    }

    public DeferredReleaser() {
        this.releaseRunnable = new C09821();
        this.mPendingReleasables = new HashSet();
        this.mUiHandler = new Handler(Looper.getMainLooper());
    }

    private static void ensureOnUiThread() {
        Preconditions.checkState(Looper.getMainLooper().getThread() == Thread.currentThread());
    }

    public static synchronized DeferredReleaser getInstance() {
        DeferredReleaser deferredReleaser;
        synchronized (DeferredReleaser.class) {
            if (sInstance == null) {
                sInstance = new DeferredReleaser();
            }
            deferredReleaser = sInstance;
        }
        return deferredReleaser;
    }

    public void cancelDeferredRelease(Releasable releasable) {
        ensureOnUiThread();
        this.mPendingReleasables.remove(releasable);
    }

    public void scheduleDeferredRelease(Releasable releasable) {
        ensureOnUiThread();
        if (this.mPendingReleasables.add(releasable) && this.mPendingReleasables.size() == 1) {
            this.mUiHandler.post(this.releaseRunnable);
        }
    }
}
