package com.facebook.cache.disk;

import com.amap.api.maps.model.WeightedLatLng;
import com.facebook.binaryresource.BinaryResource;
import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.cache.common.CacheErrorLogger.CacheErrorCategory;
import com.facebook.cache.common.CacheEventListener;
import com.facebook.cache.common.CacheEventListener.EvictionReason;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.WriterCallback;
import com.facebook.cache.disk.DiskStorage.DiskDumpInfo;
import com.facebook.cache.disk.DiskStorage.Entry;
import com.facebook.common.disk.DiskTrimmable;
import com.facebook.common.disk.DiskTrimmableRegistry;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import com.facebook.common.statfs.StatFsHelper;
import com.facebook.common.statfs.StatFsHelper.StorageType;
import com.facebook.common.time.Clock;
import com.facebook.common.time.SystemClock;
import com.facebook.common.util.SecureHashUtil;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class DiskStorageCache implements FileCache, DiskTrimmable {
    private static final long FILECACHE_SIZE_UPDATE_PERIOD_MS;
    private static final long FUTURE_TIMESTAMP_THRESHOLD_MS;
    public static final int START_OF_VERSIONING = 1;
    private static final Class<?> TAG;
    private static final double TRIMMING_LOWER_BOUND = 0.02d;
    private static final long UNINITIALIZED = -1;
    private final CacheErrorLogger mCacheErrorLogger;
    private final CacheEventListener mCacheEventListener;
    @GuardedBy("mLock")
    private long mCacheSizeLastUpdateTime;
    private long mCacheSizeLimit;
    private final long mCacheSizeLimitMinimum;
    private final CacheStats mCacheStats;
    private final Clock mClock;
    private final long mDefaultCacheSizeLimit;
    private final Object mLock;
    private final long mLowDiskSpaceCacheSizeLimit;
    private final StatFsHelper mStatFsHelper;
    private final DiskStorageSupplier mStorageSupplier;

    @VisibleForTesting
    class CacheStats {
        private long mCount;
        private boolean mInitialized;
        private long mSize;

        CacheStats() {
            this.mInitialized = false;
            this.mSize = DiskStorageCache.UNINITIALIZED;
            this.mCount = DiskStorageCache.UNINITIALIZED;
        }

        public synchronized long getCount() {
            return this.mCount;
        }

        public synchronized long getSize() {
            return this.mSize;
        }

        public synchronized void increment(long j, long j2) {
            if (this.mInitialized) {
                this.mSize += j;
                this.mCount += j2;
            }
        }

        public synchronized boolean isInitialized() {
            return this.mInitialized;
        }

        public synchronized void reset() {
            this.mInitialized = false;
            this.mCount = DiskStorageCache.UNINITIALIZED;
            this.mSize = DiskStorageCache.UNINITIALIZED;
        }

        public synchronized void set(long j, long j2) {
            this.mCount = j2;
            this.mSize = j;
            this.mInitialized = true;
        }
    }

    public class Params {
        public final long mCacheSizeLimitMinimum;
        public final long mDefaultCacheSizeLimit;
        public final long mLowDiskSpaceCacheSizeLimit;

        public Params(long j, long j2, long j3) {
            this.mCacheSizeLimitMinimum = j;
            this.mLowDiskSpaceCacheSizeLimit = j2;
            this.mDefaultCacheSizeLimit = j3;
        }
    }

    class TimestampComparator implements Comparator<Entry> {
        private final long threshold;

        public TimestampComparator(long j) {
            this.threshold = j;
        }

        public int compare(Entry entry, Entry entry2) {
            long j = DiskStorageCache.FUTURE_TIMESTAMP_THRESHOLD_MS;
            long timestamp = entry.getTimestamp() <= this.threshold ? entry.getTimestamp() : DiskStorageCache.FUTURE_TIMESTAMP_THRESHOLD_MS;
            if (entry2.getTimestamp() <= this.threshold) {
                j = entry2.getTimestamp();
            }
            return timestamp < j ? -1 : j > timestamp ? DiskStorageCache.START_OF_VERSIONING : 0;
        }
    }

    static {
        TAG = DiskStorageCache.class;
        FUTURE_TIMESTAMP_THRESHOLD_MS = TimeUnit.HOURS.toMillis(2);
        FILECACHE_SIZE_UPDATE_PERIOD_MS = TimeUnit.MINUTES.toMillis(30);
    }

    public DiskStorageCache(DiskStorageSupplier diskStorageSupplier, Params params, CacheEventListener cacheEventListener, CacheErrorLogger cacheErrorLogger, @Nullable DiskTrimmableRegistry diskTrimmableRegistry) {
        this.mLock = new Object();
        this.mLowDiskSpaceCacheSizeLimit = params.mLowDiskSpaceCacheSizeLimit;
        this.mDefaultCacheSizeLimit = params.mDefaultCacheSizeLimit;
        this.mCacheSizeLimit = params.mDefaultCacheSizeLimit;
        this.mStatFsHelper = StatFsHelper.getInstance();
        this.mStorageSupplier = diskStorageSupplier;
        this.mCacheSizeLastUpdateTime = UNINITIALIZED;
        this.mCacheEventListener = cacheEventListener;
        this.mCacheSizeLimitMinimum = params.mCacheSizeLimitMinimum;
        this.mCacheErrorLogger = cacheErrorLogger;
        this.mCacheStats = new CacheStats();
        if (diskTrimmableRegistry != null) {
            diskTrimmableRegistry.registerDiskTrimmable(this);
        }
        this.mClock = SystemClock.get();
    }

    @GuardedBy("mLock")
    private void calcFileCacheSize() {
        Object obj = null;
        int i = 0;
        int i2 = 0;
        long j = UNINITIALIZED;
        long now = this.mClock.now();
        long j2 = now + FUTURE_TIMESTAMP_THRESHOLD_MS;
        try {
            long j3 = FUTURE_TIMESTAMP_THRESHOLD_MS;
            int i3 = 0;
            for (Entry entry : this.mStorageSupplier.get().getEntries()) {
                long max;
                int i4;
                int i5;
                Object obj2;
                int i6 = i3 + START_OF_VERSIONING;
                j3 += entry.getSize();
                if (entry.getTimestamp() > j2) {
                    int i7 = i + START_OF_VERSIONING;
                    i = (int) (((long) i2) + entry.getSize());
                    max = Math.max(entry.getTimestamp() - now, j);
                    i4 = i;
                    i5 = i7;
                    obj2 = START_OF_VERSIONING;
                } else {
                    long j4 = j;
                    i4 = i2;
                    i5 = i;
                    max = j4;
                    obj2 = obj;
                }
                obj = obj2;
                i3 = i6;
                i = i5;
                i2 = i4;
                j = max;
            }
            if (obj != null) {
                this.mCacheErrorLogger.logError(CacheErrorCategory.READ_INVALID_ENTRY, TAG, "Future timestamp found in " + i + " files , with a total size of " + i2 + " bytes, and a maximum time delta of " + j + LocaleUtil.MALAY, null);
            }
            this.mCacheStats.set(j3, (long) i3);
        } catch (Throwable e) {
            this.mCacheErrorLogger.logError(CacheErrorCategory.GENERIC_IO, TAG, "calcFileCacheSize: " + e.getMessage(), e);
        }
    }

    private BinaryResource commitResource(String str, CacheKey cacheKey, BinaryResource binaryResource) {
        BinaryResource commit;
        synchronized (this.mLock) {
            commit = this.mStorageSupplier.get().commit(str, binaryResource, cacheKey);
            this.mCacheStats.increment(commit.size(), 1);
        }
        return commit;
    }

    private BinaryResource createTemporaryResource(String str, CacheKey cacheKey) {
        maybeEvictFilesInCacheDir();
        return this.mStorageSupplier.get().createTemporary(str, cacheKey);
    }

    private void deleteTemporaryResource(BinaryResource binaryResource) {
        if (binaryResource instanceof FileBinaryResource) {
            File file = ((FileBinaryResource) binaryResource).getFile();
            if (file.exists()) {
                Object[] objArr = new Object[START_OF_VERSIONING];
                objArr[0] = file;
                FLog.m7595e(TAG, "Temp file still on disk: %s ", objArr);
                if (!file.delete()) {
                    objArr = new Object[START_OF_VERSIONING];
                    objArr[0] = file;
                    FLog.m7595e(TAG, "Failed to delete temp file: %s", objArr);
                }
            }
        }
    }

    @GuardedBy("mLock")
    private void evictAboveSize(long j, EvictionReason evictionReason) {
        DiskStorage diskStorage = this.mStorageSupplier.get();
        try {
            long size = this.mCacheStats.getSize() - j;
            int i = 0;
            long j2 = 0;
            for (Entry entry : getSortedEntries(diskStorage.getEntries())) {
                if (j2 > size) {
                    break;
                }
                long j3;
                int i2;
                long remove = diskStorage.remove(entry);
                if (remove > FUTURE_TIMESTAMP_THRESHOLD_MS) {
                    j3 = j2 + remove;
                    i2 = i + START_OF_VERSIONING;
                } else {
                    long j4 = j2;
                    i2 = i;
                    j3 = j4;
                }
                i = i2;
                j2 = j3;
            }
            this.mCacheStats.increment(-j2, (long) (-i));
            diskStorage.purgeUnexpectedResources();
            reportEviction(evictionReason, i, j2);
        } catch (Throwable e) {
            this.mCacheErrorLogger.logError(CacheErrorCategory.EVICTION, TAG, "evictAboveSize: " + e.getMessage(), e);
            throw e;
        }
    }

    private Collection<Entry> getSortedEntries(Collection<Entry> collection) {
        Collection arrayList = new ArrayList(collection);
        Collections.sort(arrayList, new TimestampComparator(this.mClock.now() + FUTURE_TIMESTAMP_THRESHOLD_MS));
        return arrayList;
    }

    private void maybeEvictFilesInCacheDir() {
        synchronized (this.mLock) {
            boolean maybeUpdateFileCacheSize = maybeUpdateFileCacheSize();
            updateFileCacheSizeLimit();
            long size = this.mCacheStats.getSize();
            if (size > this.mCacheSizeLimit && !maybeUpdateFileCacheSize) {
                this.mCacheStats.reset();
                maybeUpdateFileCacheSize();
            }
            if (size > this.mCacheSizeLimit) {
                evictAboveSize((this.mCacheSizeLimit * 9) / 10, EvictionReason.CACHE_FULL);
            }
        }
    }

    @GuardedBy("mLock")
    private boolean maybeUpdateFileCacheSize() {
        long elapsedRealtime = android.os.SystemClock.elapsedRealtime();
        if (this.mCacheStats.isInitialized() && this.mCacheSizeLastUpdateTime != UNINITIALIZED && elapsedRealtime - this.mCacheSizeLastUpdateTime <= FILECACHE_SIZE_UPDATE_PERIOD_MS) {
            return false;
        }
        calcFileCacheSize();
        this.mCacheSizeLastUpdateTime = elapsedRealtime;
        return true;
    }

    private void reportEviction(EvictionReason evictionReason, int i, long j) {
        this.mCacheEventListener.onEviction(evictionReason, i, j);
    }

    private void trimBy(double d) {
        synchronized (this.mLock) {
            try {
                this.mCacheStats.reset();
                maybeUpdateFileCacheSize();
                long size = this.mCacheStats.getSize();
                evictAboveSize(size - ((long) (((double) size) * d)), EvictionReason.CACHE_MANAGER_TRIMMED);
            } catch (Throwable e) {
                this.mCacheErrorLogger.logError(CacheErrorCategory.EVICTION, TAG, "trimBy: " + e.getMessage(), e);
            }
        }
    }

    @GuardedBy("mLock")
    private void updateFileCacheSizeLimit() {
        if (this.mStatFsHelper.testLowDiskSpace(StorageType.INTERNAL, this.mDefaultCacheSizeLimit - this.mCacheStats.getSize())) {
            this.mCacheSizeLimit = this.mLowDiskSpaceCacheSizeLimit;
        } else {
            this.mCacheSizeLimit = this.mDefaultCacheSizeLimit;
        }
    }

    public void clearAll() {
        synchronized (this.mLock) {
            try {
                this.mStorageSupplier.get().clearAll();
            } catch (Throwable e) {
                this.mCacheErrorLogger.logError(CacheErrorCategory.EVICTION, TAG, "clearAll: " + e.getMessage(), e);
            }
            this.mCacheStats.reset();
        }
    }

    public long clearOldEntries(long j) {
        long j2 = FUTURE_TIMESTAMP_THRESHOLD_MS;
        synchronized (this.mLock) {
            try {
                long now = this.mClock.now();
                DiskStorage diskStorage = this.mStorageSupplier.get();
                Collection<Entry> entries = diskStorage.getEntries();
                int i = 0;
                long j3 = FUTURE_TIMESTAMP_THRESHOLD_MS;
                for (Entry entry : entries) {
                    long j4;
                    int i2;
                    long max = Math.max(1, Math.abs(now - entry.getTimestamp()));
                    long j5;
                    if (max >= j) {
                        long remove = diskStorage.remove(entry);
                        if (remove > FUTURE_TIMESTAMP_THRESHOLD_MS) {
                            j4 = j3 + remove;
                            i2 = i + START_OF_VERSIONING;
                        } else {
                            j5 = j3;
                            i2 = i;
                            j4 = j5;
                        }
                        max = j2;
                    } else {
                        max = Math.max(j2, max);
                        j5 = j3;
                        i2 = i;
                        j4 = j5;
                    }
                    j2 = max;
                    int i3 = i2;
                    j3 = j4;
                    i = i3;
                }
                diskStorage.purgeUnexpectedResources();
                if (i > 0) {
                    maybeUpdateFileCacheSize();
                    this.mCacheStats.increment(-j3, (long) (-i));
                    reportEviction(EvictionReason.CONTENT_STALE, i, j3);
                }
            } catch (Throwable e) {
                this.mCacheErrorLogger.logError(CacheErrorCategory.EVICTION, TAG, "clearOldEntries: " + e.getMessage(), e);
            }
        }
        return j2;
    }

    public DiskDumpInfo getDumpInfo() {
        return this.mStorageSupplier.get().getDumpInfo();
    }

    public BinaryResource getResource(CacheKey cacheKey) {
        try {
            BinaryResource resource;
            synchronized (this.mLock) {
                resource = this.mStorageSupplier.get().getResource(getResourceId(cacheKey), cacheKey);
                if (resource == null) {
                    this.mCacheEventListener.onMiss();
                } else {
                    this.mCacheEventListener.onHit();
                }
            }
            return resource;
        } catch (Throwable e) {
            this.mCacheErrorLogger.logError(CacheErrorCategory.GENERIC_IO, TAG, "getResource", e);
            this.mCacheEventListener.onReadException();
            return null;
        }
    }

    @VisibleForTesting
    String getResourceId(CacheKey cacheKey) {
        try {
            return SecureHashUtil.makeSHA1HashBase64(cacheKey.toString().getBytes(C1142e.f5201a));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public long getSize() {
        return this.mCacheStats.getSize();
    }

    public boolean hasKey(CacheKey cacheKey) {
        try {
            return this.mStorageSupplier.get().contains(getResourceId(cacheKey), cacheKey);
        } catch (IOException e) {
            return false;
        }
    }

    public BinaryResource insert(CacheKey cacheKey, WriterCallback writerCallback) {
        this.mCacheEventListener.onWriteAttempt();
        String resourceId = getResourceId(cacheKey);
        BinaryResource createTemporaryResource;
        try {
            createTemporaryResource = createTemporaryResource(resourceId, cacheKey);
            this.mStorageSupplier.get().updateResource(resourceId, createTemporaryResource, writerCallback, cacheKey);
            BinaryResource commitResource = commitResource(resourceId, cacheKey, createTemporaryResource);
            deleteTemporaryResource(createTemporaryResource);
            return commitResource;
        } catch (Throwable e) {
            this.mCacheEventListener.onWriteException();
            FLog.m7582d(TAG, "Failed inserting a file into the cache", e);
            throw e;
        } catch (Throwable th) {
            deleteTemporaryResource(createTemporaryResource);
        }
    }

    public boolean isEnabled() {
        try {
            return this.mStorageSupplier.get().isEnabled();
        } catch (IOException e) {
            return false;
        }
    }

    public boolean probe(CacheKey cacheKey) {
        try {
            boolean touch;
            synchronized (this.mLock) {
                touch = this.mStorageSupplier.get().touch(getResourceId(cacheKey), cacheKey);
            }
            return touch;
        } catch (IOException e) {
            this.mCacheEventListener.onReadException();
            return false;
        }
    }

    public void remove(CacheKey cacheKey) {
        synchronized (this.mLock) {
            try {
                this.mStorageSupplier.get().remove(getResourceId(cacheKey));
            } catch (Throwable e) {
                this.mCacheErrorLogger.logError(CacheErrorCategory.DELETE_FILE, TAG, "delete: " + e.getMessage(), e);
            }
        }
    }

    public void trimToMinimum() {
        synchronized (this.mLock) {
            maybeUpdateFileCacheSize();
            long size = this.mCacheStats.getSize();
            if (this.mCacheSizeLimitMinimum <= FUTURE_TIMESTAMP_THRESHOLD_MS || size <= FUTURE_TIMESTAMP_THRESHOLD_MS || size < this.mCacheSizeLimitMinimum) {
                return;
            }
            double d = WeightedLatLng.DEFAULT_INTENSITY - (((double) this.mCacheSizeLimitMinimum) / ((double) size));
            if (d > TRIMMING_LOWER_BOUND) {
                trimBy(d);
            }
        }
    }

    public void trimToNothing() {
        clearAll();
    }
}
