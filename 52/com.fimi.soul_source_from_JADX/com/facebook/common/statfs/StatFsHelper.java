package com.facebook.common.statfs;

import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import com.facebook.common.internal.Throwables;
import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class StatFsHelper {
    private static final long RESTAT_INTERVAL_MS;
    private static StatFsHelper sStatsFsHelper;
    private final Lock lock;
    private volatile File mExternalPath;
    private volatile StatFs mExternalStatFs;
    private volatile boolean mInitialized;
    private volatile File mInternalPath;
    private volatile StatFs mInternalStatFs;
    @GuardedBy("lock")
    private long mLastRestatTime;

    public enum StorageType {
        INTERNAL,
        EXTERNAL
    }

    static {
        RESTAT_INTERVAL_MS = TimeUnit.MINUTES.toMillis(2);
    }

    protected StatFsHelper() {
        this.mInternalStatFs = null;
        this.mExternalStatFs = null;
        this.mInitialized = false;
        this.lock = new ReentrantLock();
    }

    protected static StatFs createStatFs(String str) {
        return new StatFs(str);
    }

    private void ensureInitialized() {
        if (!this.mInitialized) {
            this.lock.lock();
            try {
                if (!this.mInitialized) {
                    this.mInternalPath = Environment.getDataDirectory();
                    this.mExternalPath = Environment.getExternalStorageDirectory();
                    updateStats();
                    this.mInitialized = true;
                }
                this.lock.unlock();
            } catch (Throwable th) {
                this.lock.unlock();
            }
        }
    }

    public static synchronized StatFsHelper getInstance() {
        StatFsHelper statFsHelper;
        synchronized (StatFsHelper.class) {
            if (sStatsFsHelper == null) {
                sStatsFsHelper = new StatFsHelper();
            }
            statFsHelper = sStatsFsHelper;
        }
        return statFsHelper;
    }

    private void maybeUpdateStats() {
        if (this.lock.tryLock()) {
            try {
                if (SystemClock.elapsedRealtime() - this.mLastRestatTime > RESTAT_INTERVAL_MS) {
                    updateStats();
                }
                this.lock.unlock();
            } catch (Throwable th) {
                this.lock.unlock();
            }
        }
    }

    @GuardedBy("lock")
    private void updateStats() {
        this.mInternalStatFs = updateStatsHelper(this.mInternalStatFs, this.mInternalPath);
        this.mExternalStatFs = updateStatsHelper(this.mExternalStatFs, this.mExternalPath);
        this.mLastRestatTime = SystemClock.elapsedRealtime();
    }

    private StatFs updateStatsHelper(@Nullable StatFs statFs, @Nullable File file) {
        if (file == null || !file.exists()) {
            return null;
        }
        if (statFs == null) {
            try {
                return createStatFs(file.getAbsolutePath());
            } catch (IllegalArgumentException e) {
                return null;
            } catch (Throwable th) {
                RuntimeException propagate = Throwables.propagate(th);
            }
        } else {
            statFs.restat(file.getAbsolutePath());
            return statFs;
        }
    }

    public long getAvailableStorageSpace(StorageType storageType) {
        ensureInitialized();
        maybeUpdateStats();
        StatFs statFs = storageType == StorageType.INTERNAL ? this.mInternalStatFs : this.mExternalStatFs;
        if (statFs == null) {
            return 0;
        }
        return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
    }

    public void resetStats() {
        if (this.lock.tryLock()) {
            try {
                ensureInitialized();
                updateStats();
            } finally {
                this.lock.unlock();
            }
        }
    }

    public boolean testLowDiskSpace(StorageType storageType, long j) {
        ensureInitialized();
        long availableStorageSpace = getAvailableStorageSpace(storageType);
        return availableStorageSpace <= 0 || availableStorageSpace < j;
    }
}
