package com.facebook.cache.disk;

import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.cache.common.CacheErrorLogger.CacheErrorCategory;
import com.facebook.common.file.FileTree;
import com.facebook.common.file.FileUtils;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import java.io.File;
import javax.annotation.Nullable;

public class DefaultDiskStorageSupplier implements DiskStorageSupplier {
    private static final Class<?> TAG;
    private final String mBaseDirectoryName;
    private final Supplier<File> mBaseDirectoryPathSupplier;
    private final CacheErrorLogger mCacheErrorLogger;
    @VisibleForTesting
    volatile State mCurrentState;
    private final int mVersion;

    @VisibleForTesting
    class State {
        @Nullable
        public final File rootDirectory;
        @Nullable
        public final DiskStorage storage;

        @VisibleForTesting
        State(@Nullable File file, @Nullable DiskStorage diskStorage) {
            this.storage = diskStorage;
            this.rootDirectory = file;
        }
    }

    static {
        TAG = DefaultDiskStorageSupplier.class;
    }

    public DefaultDiskStorageSupplier(int i, Supplier<File> supplier, String str, CacheErrorLogger cacheErrorLogger) {
        this.mVersion = i;
        this.mCacheErrorLogger = cacheErrorLogger;
        this.mBaseDirectoryPathSupplier = supplier;
        this.mBaseDirectoryName = str;
        this.mCurrentState = new State(null, null);
    }

    private void createStorage() {
        File file = new File((File) this.mBaseDirectoryPathSupplier.get(), this.mBaseDirectoryName);
        createRootDirectoryIfNecessary(file);
        this.mCurrentState = new State(file, new DefaultDiskStorage(file, this.mVersion, this.mCacheErrorLogger));
    }

    private boolean shouldCreateNewStorage() {
        State state = this.mCurrentState;
        return state.storage == null || state.rootDirectory == null || !state.rootDirectory.exists();
    }

    @VisibleForTesting
    void createRootDirectoryIfNecessary(File file) {
        try {
            FileUtils.mkdirs(file);
            FLog.m7578d(TAG, "Created cache directory %s", file.getAbsolutePath());
        } catch (Throwable e) {
            this.mCacheErrorLogger.logError(CacheErrorCategory.WRITE_CREATE_DIR, TAG, "createRootDirectoryIfNecessary", e);
            throw e;
        }
    }

    @VisibleForTesting
    void deleteOldStorageIfNecessary() {
        if (this.mCurrentState.storage != null && this.mCurrentState.rootDirectory != null) {
            FileTree.deleteRecursively(this.mCurrentState.rootDirectory);
        }
    }

    public synchronized DiskStorage get() {
        if (shouldCreateNewStorage()) {
            deleteOldStorageIfNecessary();
            createStorage();
        }
        return (DiskStorage) Preconditions.checkNotNull(this.mCurrentState.storage);
    }
}
