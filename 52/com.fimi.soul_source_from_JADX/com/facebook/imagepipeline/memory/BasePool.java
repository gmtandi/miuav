package com.facebook.imagepipeline.memory;

import android.annotation.SuppressLint;
import android.util.SparseArray;
import android.util.SparseIntArray;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Sets;
import com.facebook.common.internal.Throwables;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.NotThreadSafe;

public abstract class BasePool<V> implements Pool<V> {
    private final Class<?> TAG;
    private boolean mAllowNewBuckets;
    @VisibleForTesting
    final SparseArray<Bucket<V>> mBuckets;
    @GuardedBy("this")
    @VisibleForTesting
    final Counter mFree;
    @VisibleForTesting
    final Set<V> mInUseValues;
    final MemoryTrimmableRegistry mMemoryTrimmableRegistry;
    final PoolParams mPoolParams;
    private final PoolStatsTracker mPoolStatsTracker;
    @GuardedBy("this")
    @VisibleForTesting
    final Counter mUsed;

    @VisibleForTesting
    @NotThreadSafe
    class Counter {
        private static final String TAG = "com.facebook.imagepipeline.common.BasePool.Counter";
        int mCount;
        int mNumBytes;

        Counter() {
        }

        public void decrement(int i) {
            if (this.mNumBytes < i || this.mCount <= 0) {
                FLog.wtf(TAG, "Unexpected decrement of %d. Current numBytes = %d, count = %d", Integer.valueOf(i), Integer.valueOf(this.mNumBytes), Integer.valueOf(this.mCount));
                return;
            }
            this.mCount--;
            this.mNumBytes -= i;
        }

        public void increment(int i) {
            this.mCount++;
            this.mNumBytes += i;
        }

        public void reset() {
            this.mCount = 0;
            this.mNumBytes = 0;
        }
    }

    public class InvalidSizeException extends RuntimeException {
        public InvalidSizeException(Object obj) {
            super("Invalid size: " + obj.toString());
        }
    }

    public class InvalidValueException extends RuntimeException {
        public InvalidValueException(Object obj) {
            super("Invalid value: " + obj.toString());
        }
    }

    public class PoolSizeViolationException extends RuntimeException {
        public PoolSizeViolationException(int i, int i2, int i3, int i4) {
            super("Pool hard cap violation? Hard cap = " + i + " Used size = " + i2 + " Free size = " + i3 + " Request size = " + i4);
        }
    }

    public class SizeTooLargeException extends InvalidSizeException {
        public SizeTooLargeException(Object obj) {
            super(obj);
        }
    }

    public BasePool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams, PoolStatsTracker poolStatsTracker) {
        this.TAG = getClass();
        this.mMemoryTrimmableRegistry = (MemoryTrimmableRegistry) Preconditions.checkNotNull(memoryTrimmableRegistry);
        this.mPoolParams = (PoolParams) Preconditions.checkNotNull(poolParams);
        this.mPoolStatsTracker = (PoolStatsTracker) Preconditions.checkNotNull(poolStatsTracker);
        this.mBuckets = new SparseArray();
        initBuckets(new SparseIntArray(0));
        this.mInUseValues = Sets.newIdentityHashSet();
        this.mFree = new Counter();
        this.mUsed = new Counter();
    }

    private synchronized void ensurePoolSizeInvariant() {
        boolean z = !isMaxSizeSoftCapExceeded() || this.mFree.mNumBytes == 0;
        Preconditions.checkState(z);
    }

    private synchronized void initBuckets(SparseIntArray sparseIntArray) {
        synchronized (this) {
            Preconditions.checkNotNull(sparseIntArray);
            this.mBuckets.clear();
            SparseIntArray sparseIntArray2 = this.mPoolParams.bucketSizes;
            if (sparseIntArray2 != null) {
                for (int i = 0; i < sparseIntArray2.size(); i++) {
                    int keyAt = sparseIntArray2.keyAt(i);
                    this.mBuckets.put(keyAt, new Bucket(getSizeInBytes(keyAt), sparseIntArray2.valueAt(i), sparseIntArray.get(keyAt, 0)));
                }
                this.mAllowNewBuckets = false;
            } else {
                this.mAllowNewBuckets = true;
            }
        }
    }

    @SuppressLint({"InvalidAccessToGuardedField"})
    private void logStats() {
        if (FLog.isLoggable(2)) {
            FLog.m7621v(this.TAG, "Used = (%d, %d); Free = (%d, %d)", Integer.valueOf(this.mUsed.mCount), Integer.valueOf(this.mUsed.mNumBytes), Integer.valueOf(this.mFree.mCount), Integer.valueOf(this.mFree.mNumBytes));
        }
    }

    protected abstract V alloc(int i);

    @VisibleForTesting
    synchronized boolean canAllocate(int i) {
        boolean z = false;
        synchronized (this) {
            int i2 = this.mPoolParams.maxSizeHardCap;
            if (i > i2 - this.mUsed.mNumBytes) {
                this.mPoolStatsTracker.onHardCapReached();
            } else {
                int i3 = this.mPoolParams.maxSizeSoftCap;
                if (i > i3 - (this.mUsed.mNumBytes + this.mFree.mNumBytes)) {
                    trimToSize(i3 - i);
                }
                if (i > i2 - (this.mUsed.mNumBytes + this.mFree.mNumBytes)) {
                    this.mPoolStatsTracker.onHardCapReached();
                } else {
                    z = true;
                }
            }
        }
        return z;
    }

    @VisibleForTesting
    protected abstract void free(V v);

    public V get(int i) {
        V v;
        ensurePoolSizeInvariant();
        int bucketedSize = getBucketedSize(i);
        synchronized (this) {
            Bucket bucket = getBucket(bucketedSize);
            if (bucket != null) {
                v = bucket.get();
                if (v != null) {
                    Preconditions.checkState(this.mInUseValues.add(v));
                    bucketedSize = getBucketedSizeForValue(v);
                    int sizeInBytes = getSizeInBytes(bucketedSize);
                    this.mUsed.increment(sizeInBytes);
                    this.mFree.decrement(sizeInBytes);
                    this.mPoolStatsTracker.onValueReuse(sizeInBytes);
                    logStats();
                    if (FLog.isLoggable(2)) {
                        FLog.m7619v(this.TAG, "get (reuse) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(v)), Integer.valueOf(bucketedSize));
                    }
                }
            }
            int sizeInBytes2 = getSizeInBytes(bucketedSize);
            if (canAllocate(sizeInBytes2)) {
                this.mUsed.increment(sizeInBytes2);
                if (bucket != null) {
                    bucket.incrementInUseCount();
                }
                v = null;
                try {
                    v = alloc(bucketedSize);
                } catch (Throwable th) {
                    synchronized (this) {
                        this.mUsed.decrement(sizeInBytes2);
                        Bucket bucket2 = getBucket(bucketedSize);
                        if (bucket2 != null) {
                            bucket2.decrementInUseCount();
                        }
                        Throwables.propagateIfPossible(th);
                    }
                    return v;
                }
                synchronized (this) {
                    Preconditions.checkState(this.mInUseValues.add(v));
                    trimToSoftCap();
                    this.mPoolStatsTracker.onAlloc(sizeInBytes2);
                    logStats();
                    if (FLog.isLoggable(2)) {
                        FLog.m7619v(this.TAG, "get (alloc) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(v)), Integer.valueOf(bucketedSize));
                    }
                }
            } else {
                throw new PoolSizeViolationException(this.mPoolParams.maxSizeHardCap, this.mUsed.mNumBytes, this.mFree.mNumBytes, sizeInBytes2);
            }
        }
        return v;
    }

    @VisibleForTesting
    synchronized Bucket<V> getBucket(int i) {
        Bucket<V> bucket;
        bucket = (Bucket) this.mBuckets.get(i);
        if (bucket == null && this.mAllowNewBuckets) {
            if (FLog.isLoggable(2)) {
                FLog.m7618v(this.TAG, "creating new bucket %s", Integer.valueOf(i));
            }
            bucket = newBucket(i);
            this.mBuckets.put(i, bucket);
        }
        return bucket;
    }

    protected abstract int getBucketedSize(int i);

    protected abstract int getBucketedSizeForValue(V v);

    protected abstract int getSizeInBytes(int i);

    public synchronized Map<String, Integer> getStats() {
        Map<String, Integer> hashMap;
        hashMap = new HashMap();
        for (int i = 0; i < this.mBuckets.size(); i++) {
            hashMap.put(PoolStatsTracker.BUCKETS_USED_PREFIX + getSizeInBytes(this.mBuckets.keyAt(i)), Integer.valueOf(((Bucket) this.mBuckets.valueAt(i)).getInUseCount()));
        }
        hashMap.put(PoolStatsTracker.SOFT_CAP, Integer.valueOf(this.mPoolParams.maxSizeSoftCap));
        hashMap.put(PoolStatsTracker.HARD_CAP, Integer.valueOf(this.mPoolParams.maxSizeHardCap));
        hashMap.put(PoolStatsTracker.USED_COUNT, Integer.valueOf(this.mUsed.mCount));
        hashMap.put(PoolStatsTracker.USED_BYTES, Integer.valueOf(this.mUsed.mNumBytes));
        hashMap.put(PoolStatsTracker.FREE_COUNT, Integer.valueOf(this.mFree.mCount));
        hashMap.put(PoolStatsTracker.FREE_BYTES, Integer.valueOf(this.mFree.mNumBytes));
        return hashMap;
    }

    protected void initialize() {
        this.mMemoryTrimmableRegistry.registerMemoryTrimmable(this);
        this.mPoolStatsTracker.setBasePool(this);
    }

    @VisibleForTesting
    synchronized boolean isMaxSizeSoftCapExceeded() {
        boolean z;
        z = this.mUsed.mNumBytes + this.mFree.mNumBytes > this.mPoolParams.maxSizeSoftCap;
        if (z) {
            this.mPoolStatsTracker.onSoftCapReached();
        }
        return z;
    }

    protected boolean isReusable(V v) {
        Preconditions.checkNotNull(v);
        return true;
    }

    Bucket<V> newBucket(int i) {
        return new Bucket(getSizeInBytes(i), Integer.MAX_VALUE, 0);
    }

    protected void onParamsChanged() {
    }

    public void release(V v) {
        Preconditions.checkNotNull(v);
        int bucketedSizeForValue = getBucketedSizeForValue(v);
        int sizeInBytes = getSizeInBytes(bucketedSizeForValue);
        synchronized (this) {
            Bucket bucket = getBucket(bucketedSizeForValue);
            if (!this.mInUseValues.remove(v)) {
                FLog.m7595e(this.TAG, "release (free, value unrecognized) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(v)), Integer.valueOf(bucketedSizeForValue));
                free(v);
                this.mPoolStatsTracker.onFree(sizeInBytes);
            } else if (bucket == null || bucket.isMaxLengthExceeded() || isMaxSizeSoftCapExceeded() || !isReusable(v)) {
                if (bucket != null) {
                    bucket.decrementInUseCount();
                }
                if (FLog.isLoggable(2)) {
                    FLog.m7619v(this.TAG, "release (free) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(v)), Integer.valueOf(bucketedSizeForValue));
                }
                free(v);
                this.mUsed.decrement(sizeInBytes);
                this.mPoolStatsTracker.onFree(sizeInBytes);
            } else {
                bucket.release(v);
                this.mFree.increment(sizeInBytes);
                this.mUsed.decrement(sizeInBytes);
                this.mPoolStatsTracker.onValueRelease(sizeInBytes);
                if (FLog.isLoggable(2)) {
                    FLog.m7619v(this.TAG, "release (reuse) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(v)), Integer.valueOf(bucketedSizeForValue));
                }
            }
            logStats();
        }
    }

    public void trim(MemoryTrimType memoryTrimType) {
        trimToNothing();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @com.facebook.common.internal.VisibleForTesting
    void trimToNothing() {
        /*
        r6 = this;
        r1 = 0;
        r3 = new java.util.ArrayList;
        r0 = r6.mBuckets;
        r0 = r0.size();
        r3.<init>(r0);
        r4 = new android.util.SparseIntArray;
        r4.<init>();
        monitor-enter(r6);
        r2 = r1;
    L_0x0013:
        r0 = r6.mBuckets;	 Catch:{ all -> 0x0062 }
        r0 = r0.size();	 Catch:{ all -> 0x0062 }
        if (r2 >= r0) goto L_0x003d;
    L_0x001b:
        r0 = r6.mBuckets;	 Catch:{ all -> 0x0062 }
        r0 = r0.valueAt(r2);	 Catch:{ all -> 0x0062 }
        r0 = (com.facebook.imagepipeline.memory.Bucket) r0;	 Catch:{ all -> 0x0062 }
        r5 = r0.getFreeListSize();	 Catch:{ all -> 0x0062 }
        if (r5 <= 0) goto L_0x002c;
    L_0x0029:
        r3.add(r0);	 Catch:{ all -> 0x0062 }
    L_0x002c:
        r5 = r6.mBuckets;	 Catch:{ all -> 0x0062 }
        r5 = r5.keyAt(r2);	 Catch:{ all -> 0x0062 }
        r0 = r0.getInUseCount();	 Catch:{ all -> 0x0062 }
        r4.put(r5, r0);	 Catch:{ all -> 0x0062 }
        r0 = r2 + 1;
        r2 = r0;
        goto L_0x0013;
    L_0x003d:
        r6.initBuckets(r4);	 Catch:{ all -> 0x0062 }
        r0 = r6.mFree;	 Catch:{ all -> 0x0062 }
        r0.reset();	 Catch:{ all -> 0x0062 }
        r6.logStats();	 Catch:{ all -> 0x0062 }
        monitor-exit(r6);	 Catch:{ all -> 0x0062 }
        r6.onParamsChanged();
    L_0x004c:
        r0 = r3.size();
        if (r1 >= r0) goto L_0x0069;
    L_0x0052:
        r0 = r3.get(r1);
        r0 = (com.facebook.imagepipeline.memory.Bucket) r0;
    L_0x0058:
        r2 = r0.pop();
        if (r2 != 0) goto L_0x0065;
    L_0x005e:
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x004c;
    L_0x0062:
        r0 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x0062 }
        throw r0;
    L_0x0065:
        r6.free(r2);
        goto L_0x0058;
    L_0x0069:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.memory.BasePool.trimToNothing():void");
    }

    @VisibleForTesting
    synchronized void trimToSize(int i) {
        int min = Math.min((this.mUsed.mNumBytes + this.mFree.mNumBytes) - i, this.mFree.mNumBytes);
        if (min > 0) {
            if (FLog.isLoggable(2)) {
                FLog.m7620v(this.TAG, "trimToSize: TargetSize = %d; Initial Size = %d; Bytes to free = %d", Integer.valueOf(i), Integer.valueOf(this.mUsed.mNumBytes + this.mFree.mNumBytes), Integer.valueOf(min));
            }
            logStats();
            for (int i2 = 0; i2 < this.mBuckets.size() && r1 > 0; i2++) {
                Bucket bucket = (Bucket) this.mBuckets.valueAt(i2);
                while (min > 0) {
                    Object pop = bucket.pop();
                    if (pop == null) {
                        break;
                    }
                    free(pop);
                    min -= bucket.mItemSize;
                    this.mFree.decrement(bucket.mItemSize);
                }
            }
            logStats();
            if (FLog.isLoggable(2)) {
                FLog.m7619v(this.TAG, "trimToSize: TargetSize = %d; Final Size = %d", Integer.valueOf(i), Integer.valueOf(this.mUsed.mNumBytes + this.mFree.mNumBytes));
            }
        }
    }

    @VisibleForTesting
    synchronized void trimToSoftCap() {
        if (isMaxSizeSoftCapExceeded()) {
            trimToSize(this.mPoolParams.maxSizeSoftCap);
        }
    }
}
