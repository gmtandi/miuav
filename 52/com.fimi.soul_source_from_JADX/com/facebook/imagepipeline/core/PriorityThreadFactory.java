package com.facebook.imagepipeline.core;

import android.os.Process;
import java.util.concurrent.ThreadFactory;

public class PriorityThreadFactory implements ThreadFactory {
    private final int mThreadPriority;

    /* renamed from: com.facebook.imagepipeline.core.PriorityThreadFactory.1 */
    class C10301 implements Runnable {
        final /* synthetic */ Runnable val$runnable;

        C10301(Runnable runnable) {
            this.val$runnable = runnable;
        }

        public void run() {
            try {
                Process.setThreadPriority(PriorityThreadFactory.this.mThreadPriority);
            } catch (Throwable th) {
            }
            this.val$runnable.run();
        }
    }

    public PriorityThreadFactory(int i) {
        this.mThreadPriority = i;
    }

    public Thread newThread(Runnable runnable) {
        return new Thread(new C10301(runnable));
    }
}
