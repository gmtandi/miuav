package com.facebook.datasource;

import android.util.Pair;
import com.facebook.common.internal.Preconditions;
import com.fimi.soul.view.photodraweeview.C2020f;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public abstract class AbstractDataSource<T> implements DataSource<T> {
    @GuardedBy("this")
    private DataSourceStatus mDataSourceStatus;
    @GuardedBy("this")
    private Throwable mFailureThrowable;
    @GuardedBy("this")
    private boolean mIsClosed;
    @GuardedBy("this")
    private float mProgress;
    @GuardedBy("this")
    @Nullable
    private T mResult;
    private final ConcurrentLinkedQueue<Pair<DataSubscriber<T>, Executor>> mSubscribers;

    /* renamed from: com.facebook.datasource.AbstractDataSource.1 */
    class C09761 implements Runnable {
        final /* synthetic */ DataSubscriber val$dataSubscriber;
        final /* synthetic */ boolean val$isCancellation;
        final /* synthetic */ boolean val$isFailure;

        C09761(boolean z, DataSubscriber dataSubscriber, boolean z2) {
            this.val$isFailure = z;
            this.val$dataSubscriber = dataSubscriber;
            this.val$isCancellation = z2;
        }

        public void run() {
            if (this.val$isFailure) {
                this.val$dataSubscriber.onFailure(AbstractDataSource.this);
            } else if (this.val$isCancellation) {
                this.val$dataSubscriber.onCancellation(AbstractDataSource.this);
            } else {
                this.val$dataSubscriber.onNewResult(AbstractDataSource.this);
            }
        }
    }

    /* renamed from: com.facebook.datasource.AbstractDataSource.2 */
    class C09772 implements Runnable {
        final /* synthetic */ DataSubscriber val$subscriber;

        C09772(DataSubscriber dataSubscriber) {
            this.val$subscriber = dataSubscriber;
        }

        public void run() {
            this.val$subscriber.onProgressUpdate(AbstractDataSource.this);
        }
    }

    enum DataSourceStatus {
        IN_PROGRESS,
        SUCCESS,
        FAILURE
    }

    protected AbstractDataSource() {
        this.mResult = null;
        this.mFailureThrowable = null;
        this.mProgress = 0.0f;
        this.mIsClosed = false;
        this.mDataSourceStatus = DataSourceStatus.IN_PROGRESS;
        this.mSubscribers = new ConcurrentLinkedQueue();
    }

    private void notifyDataSubscriber(DataSubscriber<T> dataSubscriber, Executor executor, boolean z, boolean z2) {
        executor.execute(new C09761(z, dataSubscriber, z2));
    }

    private void notifyDataSubscribers() {
        boolean hasFailed = hasFailed();
        boolean wasCancelled = wasCancelled();
        Iterator it = this.mSubscribers.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            notifyDataSubscriber((DataSubscriber) pair.first, (Executor) pair.second, hasFailed, wasCancelled);
        }
    }

    private synchronized boolean setFailureInternal(Throwable th) {
        boolean z;
        if (this.mIsClosed || this.mDataSourceStatus != DataSourceStatus.IN_PROGRESS) {
            z = false;
        } else {
            this.mDataSourceStatus = DataSourceStatus.FAILURE;
            this.mFailureThrowable = th;
            z = true;
        }
        return z;
    }

    private synchronized boolean setProgressInternal(float f) {
        boolean z = false;
        synchronized (this) {
            if (!this.mIsClosed && this.mDataSourceStatus == DataSourceStatus.IN_PROGRESS) {
                if (f >= this.mProgress) {
                    this.mProgress = f;
                    z = true;
                }
            }
        }
        return z;
    }

    private boolean setResultInternal(@Nullable T t, boolean z) {
        Throwable th;
        Object obj;
        try {
            synchronized (this) {
                try {
                    boolean z2;
                    if (this.mIsClosed || this.mDataSourceStatus != DataSourceStatus.IN_PROGRESS) {
                        z2 = false;
                        try {
                            if (t != null) {
                                closeResult(t);
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            try {
                                throw th;
                            } catch (Throwable th3) {
                                th = th3;
                            }
                        }
                    } else {
                        if (z) {
                            this.mDataSourceStatus = DataSourceStatus.SUCCESS;
                            this.mProgress = C2020f.f10933c;
                        }
                        if (this.mResult != t) {
                            Object obj2 = this.mResult;
                            try {
                                this.mResult = t;
                                obj = obj2;
                            } catch (Throwable th4) {
                                th = th4;
                                obj = obj2;
                                throw th;
                            }
                        } else {
                            obj = null;
                        }
                        z2 = true;
                        if (obj != null) {
                            closeResult(obj);
                        }
                    }
                    return z2;
                } catch (Throwable th5) {
                    th = th5;
                    obj = null;
                    throw th;
                }
            }
        } catch (Throwable th6) {
            th = th6;
            obj = null;
            if (obj != null) {
                closeResult(obj);
            }
            throw th;
        }
    }

    private synchronized boolean wasCancelled() {
        boolean z;
        z = isClosed() && !isFinished();
        return z;
    }

    public boolean close() {
        boolean z = true;
        synchronized (this) {
            if (this.mIsClosed) {
                z = false;
            } else {
                this.mIsClosed = true;
                Object obj = this.mResult;
                this.mResult = null;
                if (obj != null) {
                    closeResult(obj);
                }
                if (!isFinished()) {
                    notifyDataSubscribers();
                }
                synchronized (this) {
                    this.mSubscribers.clear();
                }
            }
        }
        return z;
    }

    protected void closeResult(@Nullable T t) {
    }

    @Nullable
    public synchronized Throwable getFailureCause() {
        return this.mFailureThrowable;
    }

    public synchronized float getProgress() {
        return this.mProgress;
    }

    @Nullable
    public synchronized T getResult() {
        return this.mResult;
    }

    public synchronized boolean hasFailed() {
        return this.mDataSourceStatus == DataSourceStatus.FAILURE;
    }

    public synchronized boolean hasResult() {
        return this.mResult != null;
    }

    public synchronized boolean isClosed() {
        return this.mIsClosed;
    }

    public synchronized boolean isFinished() {
        return this.mDataSourceStatus != DataSourceStatus.IN_PROGRESS;
    }

    protected void notifyProgressUpdate() {
        Iterator it = this.mSubscribers.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            ((Executor) pair.second).execute(new C09772((DataSubscriber) pair.first));
        }
    }

    protected boolean setFailure(Throwable th) {
        boolean failureInternal = setFailureInternal(th);
        if (failureInternal) {
            notifyDataSubscribers();
        }
        return failureInternal;
    }

    protected boolean setProgress(float f) {
        boolean progressInternal = setProgressInternal(f);
        if (progressInternal) {
            notifyProgressUpdate();
        }
        return progressInternal;
    }

    protected boolean setResult(@Nullable T t, boolean z) {
        boolean resultInternal = setResultInternal(t, z);
        if (resultInternal) {
            notifyDataSubscribers();
        }
        return resultInternal;
    }

    public void subscribe(DataSubscriber<T> dataSubscriber, Executor executor) {
        Preconditions.checkNotNull(dataSubscriber);
        Preconditions.checkNotNull(executor);
        synchronized (this) {
            if (this.mIsClosed) {
                return;
            }
            if (this.mDataSourceStatus == DataSourceStatus.IN_PROGRESS) {
                this.mSubscribers.add(Pair.create(dataSubscriber, executor));
            }
            Object obj = (hasResult() || isFinished() || wasCancelled()) ? 1 : null;
            if (obj != null) {
                notifyDataSubscriber(dataSubscriber, executor, hasFailed(), wasCancelled());
            }
        }
    }
}
