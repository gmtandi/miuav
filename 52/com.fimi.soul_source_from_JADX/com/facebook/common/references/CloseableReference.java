package com.facebook.common.references;

import com.facebook.common.internal.Closeables;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public final class CloseableReference<T> implements Closeable, Cloneable {
    private static final ResourceReleaser<Closeable> DEFAULT_CLOSEABLE_RELEASER;
    private static Class<CloseableReference> TAG;
    @GuardedBy("this")
    private boolean mIsClosed;
    private final SharedReference<T> mSharedReference;

    /* renamed from: com.facebook.common.references.CloseableReference.1 */
    final class C09721 implements ResourceReleaser<Closeable> {
        C09721() {
        }

        public void release(Closeable closeable) {
            try {
                Closeables.close(closeable, true);
            } catch (IOException e) {
            }
        }
    }

    static {
        TAG = CloseableReference.class;
        DEFAULT_CLOSEABLE_RELEASER = new C09721();
    }

    private CloseableReference(SharedReference<T> sharedReference) {
        this.mIsClosed = false;
        this.mSharedReference = (SharedReference) Preconditions.checkNotNull(sharedReference);
        sharedReference.addReference();
    }

    private CloseableReference(T t, ResourceReleaser<T> resourceReleaser) {
        this.mIsClosed = false;
        this.mSharedReference = new SharedReference(t, resourceReleaser);
    }

    @Nullable
    public static <T> CloseableReference<T> cloneOrNull(@Nullable CloseableReference<T> closeableReference) {
        return closeableReference != null ? closeableReference.cloneOrNull() : null;
    }

    public static <T> List<CloseableReference<T>> cloneOrNull(Collection<CloseableReference<T>> collection) {
        if (collection == null) {
            return null;
        }
        List<CloseableReference<T>> arrayList = new ArrayList(collection.size());
        for (CloseableReference cloneOrNull : collection) {
            arrayList.add(cloneOrNull(cloneOrNull));
        }
        return arrayList;
    }

    public static void closeSafely(@Nullable CloseableReference<?> closeableReference) {
        if (closeableReference != null) {
            closeableReference.close();
        }
    }

    public static void closeSafely(@Nullable Iterable<? extends CloseableReference<?>> iterable) {
        if (iterable != null) {
            for (CloseableReference closeSafely : iterable) {
                closeSafely(closeSafely);
            }
        }
    }

    public static boolean isValid(@Nullable CloseableReference<?> closeableReference) {
        return closeableReference != null && closeableReference.isValid();
    }

    @Nullable
    public static <T extends Closeable> CloseableReference<T> of(@Nullable T t) {
        return t == null ? null : new CloseableReference(t, DEFAULT_CLOSEABLE_RELEASER);
    }

    @Nullable
    public static <T> CloseableReference<T> of(@Nullable T t, ResourceReleaser<T> resourceReleaser) {
        return t == null ? null : new CloseableReference(t, resourceReleaser);
    }

    public synchronized CloseableReference<T> clone() {
        Preconditions.checkState(isValid());
        return new CloseableReference(this.mSharedReference);
    }

    public synchronized CloseableReference<T> cloneOrNull() {
        return isValid() ? new CloseableReference(this.mSharedReference) : null;
    }

    public void close() {
        synchronized (this) {
            if (this.mIsClosed) {
                return;
            }
            this.mIsClosed = true;
            this.mSharedReference.deleteReference();
        }
    }

    protected void finalize() {
        try {
            synchronized (this) {
                if (this.mIsClosed) {
                    return;
                }
                FLog.m7635w(TAG, "Finalized without closing: %x %x (type = %s)", Integer.valueOf(System.identityHashCode(this)), Integer.valueOf(System.identityHashCode(this.mSharedReference)), this.mSharedReference.get().getClass().getSimpleName());
                close();
                super.finalize();
            }
        } finally {
            super.finalize();
        }
    }

    public synchronized T get() {
        Preconditions.checkState(!this.mIsClosed);
        return this.mSharedReference.get();
    }

    @VisibleForTesting
    public synchronized SharedReference<T> getUnderlyingReferenceTestOnly() {
        return this.mSharedReference;
    }

    public synchronized int getValueHash() {
        return isValid() ? System.identityHashCode(this.mSharedReference.get()) : 0;
    }

    public synchronized boolean isValid() {
        return !this.mIsClosed;
    }
}
