package com.facebook.imagepipeline.producers;

import android.os.SystemClock;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.imagepipeline.image.EncodedImage;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;

public class JobScheduler {
    static final String QUEUE_TIME_KEY = "queueTime";
    private final Runnable mDoJobRunnable;
    @GuardedBy("this")
    @VisibleForTesting
    EncodedImage mEncodedImage;
    private final Executor mExecutor;
    @GuardedBy("this")
    @VisibleForTesting
    boolean mIsLast;
    private final JobRunnable mJobRunnable;
    @GuardedBy("this")
    @VisibleForTesting
    long mJobStartTime;
    @GuardedBy("this")
    @VisibleForTesting
    JobState mJobState;
    @GuardedBy("this")
    @VisibleForTesting
    long mJobSubmitTime;
    private final int mMinimumJobIntervalMs;
    private final Runnable mSubmitJobRunnable;

    public interface JobRunnable {
        void run(EncodedImage encodedImage, boolean z);
    }

    /* renamed from: com.facebook.imagepipeline.producers.JobScheduler.1 */
    class C10501 implements Runnable {
        C10501() {
        }

        public void run() {
            JobScheduler.this.doJob();
        }
    }

    /* renamed from: com.facebook.imagepipeline.producers.JobScheduler.2 */
    class C10512 implements Runnable {
        C10512() {
        }

        public void run() {
            JobScheduler.this.submitJob();
        }
    }

    /* renamed from: com.facebook.imagepipeline.producers.JobScheduler.3 */
    /* synthetic */ class C10523 {
        static final /* synthetic */ int[] f5056xca5c4655;

        static {
            f5056xca5c4655 = new int[JobState.values().length];
            try {
                f5056xca5c4655[JobState.IDLE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f5056xca5c4655[JobState.QUEUED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f5056xca5c4655[JobState.RUNNING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f5056xca5c4655[JobState.RUNNING_AND_PENDING.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    @VisibleForTesting
    class JobStartExecutorSupplier {
        private static ScheduledExecutorService sJobStarterExecutor;

        JobStartExecutorSupplier() {
        }

        static ScheduledExecutorService get() {
            if (sJobStarterExecutor == null) {
                sJobStarterExecutor = Executors.newSingleThreadScheduledExecutor();
            }
            return sJobStarterExecutor;
        }
    }

    @VisibleForTesting
    enum JobState {
        IDLE,
        QUEUED,
        RUNNING,
        RUNNING_AND_PENDING
    }

    public JobScheduler(Executor executor, JobRunnable jobRunnable, int i) {
        this.mExecutor = executor;
        this.mJobRunnable = jobRunnable;
        this.mMinimumJobIntervalMs = i;
        this.mDoJobRunnable = new C10501();
        this.mSubmitJobRunnable = new C10512();
        this.mEncodedImage = null;
        this.mIsLast = false;
        this.mJobState = JobState.IDLE;
        this.mJobSubmitTime = 0;
        this.mJobStartTime = 0;
    }

    private void doJob() {
        long uptimeMillis = SystemClock.uptimeMillis();
        synchronized (this) {
            EncodedImage encodedImage = this.mEncodedImage;
            boolean z = this.mIsLast;
            this.mEncodedImage = null;
            this.mIsLast = false;
            this.mJobState = JobState.RUNNING;
            this.mJobStartTime = uptimeMillis;
        }
        try {
            if (shouldProcess(encodedImage, z)) {
                this.mJobRunnable.run(encodedImage, z);
            }
            EncodedImage.closeSafely(encodedImage);
            onJobFinished();
        } catch (Throwable th) {
            EncodedImage.closeSafely(encodedImage);
            onJobFinished();
        }
    }

    private void enqueueJob(long j) {
        if (j > 0) {
            JobStartExecutorSupplier.get().schedule(this.mSubmitJobRunnable, j, TimeUnit.MILLISECONDS);
        } else {
            this.mSubmitJobRunnable.run();
        }
    }

    private void onJobFinished() {
        long uptimeMillis = SystemClock.uptimeMillis();
        long j = 0;
        Object obj = null;
        synchronized (this) {
            if (this.mJobState == JobState.RUNNING_AND_PENDING) {
                j = Math.max(this.mJobStartTime + ((long) this.mMinimumJobIntervalMs), uptimeMillis);
                obj = 1;
                this.mJobSubmitTime = uptimeMillis;
                this.mJobState = JobState.QUEUED;
            } else {
                this.mJobState = JobState.IDLE;
            }
        }
        if (obj != null) {
            enqueueJob(j - uptimeMillis);
        }
    }

    private static boolean shouldProcess(EncodedImage encodedImage, boolean z) {
        return z || EncodedImage.isValid(encodedImage);
    }

    private void submitJob() {
        this.mExecutor.execute(this.mDoJobRunnable);
    }

    public void clearJob() {
        EncodedImage encodedImage;
        synchronized (this) {
            encodedImage = this.mEncodedImage;
            this.mEncodedImage = null;
            this.mIsLast = false;
        }
        EncodedImage.closeSafely(encodedImage);
    }

    public synchronized long getQueuedTime() {
        return this.mJobStartTime - this.mJobSubmitTime;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean scheduleJob() {
        /*
        r8 = this;
        r1 = 1;
        r0 = 0;
        r4 = android.os.SystemClock.uptimeMillis();
        r2 = 0;
        monitor-enter(r8);
        r6 = r8.mEncodedImage;	 Catch:{ all -> 0x0042 }
        r7 = r8.mIsLast;	 Catch:{ all -> 0x0042 }
        r6 = shouldProcess(r6, r7);	 Catch:{ all -> 0x0042 }
        if (r6 != 0) goto L_0x0015;
    L_0x0013:
        monitor-exit(r8);	 Catch:{ all -> 0x0042 }
    L_0x0014:
        return r0;
    L_0x0015:
        r6 = com.facebook.imagepipeline.producers.JobScheduler.C10523.f5056xca5c4655;	 Catch:{ all -> 0x0042 }
        r7 = r8.mJobState;	 Catch:{ all -> 0x0042 }
        r7 = r7.ordinal();	 Catch:{ all -> 0x0042 }
        r6 = r6[r7];	 Catch:{ all -> 0x0042 }
        switch(r6) {
            case 1: goto L_0x002b;
            case 2: goto L_0x0022;
            case 3: goto L_0x003d;
            default: goto L_0x0022;
        };	 Catch:{ all -> 0x0042 }
    L_0x0022:
        monitor-exit(r8);	 Catch:{ all -> 0x0042 }
        if (r0 == 0) goto L_0x0029;
    L_0x0025:
        r2 = r2 - r4;
        r8.enqueueJob(r2);
    L_0x0029:
        r0 = r1;
        goto L_0x0014;
    L_0x002b:
        r2 = r8.mJobStartTime;	 Catch:{ all -> 0x0042 }
        r0 = r8.mMinimumJobIntervalMs;	 Catch:{ all -> 0x0042 }
        r6 = (long) r0;	 Catch:{ all -> 0x0042 }
        r2 = r2 + r6;
        r2 = java.lang.Math.max(r2, r4);	 Catch:{ all -> 0x0042 }
        r8.mJobSubmitTime = r4;	 Catch:{ all -> 0x0042 }
        r0 = com.facebook.imagepipeline.producers.JobScheduler.JobState.QUEUED;	 Catch:{ all -> 0x0042 }
        r8.mJobState = r0;	 Catch:{ all -> 0x0042 }
        r0 = r1;
        goto L_0x0022;
    L_0x003d:
        r6 = com.facebook.imagepipeline.producers.JobScheduler.JobState.RUNNING_AND_PENDING;	 Catch:{ all -> 0x0042 }
        r8.mJobState = r6;	 Catch:{ all -> 0x0042 }
        goto L_0x0022;
    L_0x0042:
        r0 = move-exception;
        monitor-exit(r8);	 Catch:{ all -> 0x0042 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.JobScheduler.scheduleJob():boolean");
    }

    public boolean updateJob(EncodedImage encodedImage, boolean z) {
        if (!shouldProcess(encodedImage, z)) {
            return false;
        }
        EncodedImage encodedImage2;
        synchronized (this) {
            encodedImage2 = this.mEncodedImage;
            this.mEncodedImage = EncodedImage.cloneOrNull(encodedImage);
            this.mIsLast = z;
        }
        EncodedImage.closeSafely(encodedImage2);
        return true;
    }
}
