package com.facebook.imagepipeline.cache;

import com.facebook.binaryresource.BinaryResource;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.WriterCallback;
import com.facebook.cache.disk.FileCache;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.memory.PooledByteStreams;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import p000a.C0018s;

public class BufferedDiskCache {
    private static final Class<?> TAG;
    private final FileCache mFileCache;
    private final ImageCacheStatsTracker mImageCacheStatsTracker;
    private final PooledByteBufferFactory mPooledByteBufferFactory;
    private final PooledByteStreams mPooledByteStreams;
    private final Executor mReadExecutor;
    private final StagingArea mStagingArea;
    private final Executor mWriteExecutor;

    /* renamed from: com.facebook.imagepipeline.cache.BufferedDiskCache.1 */
    class C10071 implements Callable<Boolean> {
        final /* synthetic */ CacheKey val$key;

        C10071(CacheKey cacheKey) {
            this.val$key = cacheKey;
        }

        public Boolean call() {
            EncodedImage encodedImage = BufferedDiskCache.this.mStagingArea.get(this.val$key);
            if (encodedImage != null) {
                encodedImage.close();
                FLog.m7618v(BufferedDiskCache.TAG, "Found image for %s in staging area", this.val$key.toString());
                BufferedDiskCache.this.mImageCacheStatsTracker.onStagingAreaHit();
                return Boolean.valueOf(true);
            }
            FLog.m7618v(BufferedDiskCache.TAG, "Did not find image for %s in staging area", this.val$key.toString());
            BufferedDiskCache.this.mImageCacheStatsTracker.onStagingAreaMiss();
            try {
                return Boolean.valueOf(BufferedDiskCache.this.mFileCache.hasKey(this.val$key));
            } catch (Exception e) {
                return Boolean.valueOf(false);
            }
        }
    }

    /* renamed from: com.facebook.imagepipeline.cache.BufferedDiskCache.2 */
    class C10082 implements Callable<EncodedImage> {
        final /* synthetic */ AtomicBoolean val$isCancelled;
        final /* synthetic */ CacheKey val$key;

        C10082(AtomicBoolean atomicBoolean, CacheKey cacheKey) {
            this.val$isCancelled = atomicBoolean;
            this.val$key = cacheKey;
        }

        public EncodedImage call() {
            if (this.val$isCancelled.get()) {
                throw new CancellationException();
            }
            EncodedImage encodedImage = BufferedDiskCache.this.mStagingArea.get(this.val$key);
            if (encodedImage != null) {
                FLog.m7618v(BufferedDiskCache.TAG, "Found image for %s in staging area", this.val$key.toString());
                BufferedDiskCache.this.mImageCacheStatsTracker.onStagingAreaHit();
            } else {
                FLog.m7618v(BufferedDiskCache.TAG, "Did not find image for %s in staging area", this.val$key.toString());
                BufferedDiskCache.this.mImageCacheStatsTracker.onStagingAreaMiss();
                CloseableReference of;
                try {
                    of = CloseableReference.of(BufferedDiskCache.this.readFromDiskCache(this.val$key));
                    encodedImage = new EncodedImage(of);
                    CloseableReference.closeSafely(of);
                } catch (Exception e) {
                    return null;
                } catch (Throwable th) {
                    CloseableReference.closeSafely(of);
                }
            }
            if (!Thread.interrupted()) {
                return encodedImage;
            }
            FLog.m7617v(BufferedDiskCache.TAG, "Host thread was interrupted, decreasing reference count");
            if (encodedImage != null) {
                encodedImage.close();
            }
            throw new InterruptedException();
        }
    }

    /* renamed from: com.facebook.imagepipeline.cache.BufferedDiskCache.3 */
    class C10093 implements Runnable {
        final /* synthetic */ EncodedImage val$finalEncodedImage;
        final /* synthetic */ CacheKey val$key;

        C10093(CacheKey cacheKey, EncodedImage encodedImage) {
            this.val$key = cacheKey;
            this.val$finalEncodedImage = encodedImage;
        }

        public void run() {
            try {
                BufferedDiskCache.this.writeToDiskCache(this.val$key, this.val$finalEncodedImage);
            } finally {
                BufferedDiskCache.this.mStagingArea.remove(this.val$key, this.val$finalEncodedImage);
                EncodedImage.closeSafely(this.val$finalEncodedImage);
            }
        }
    }

    /* renamed from: com.facebook.imagepipeline.cache.BufferedDiskCache.4 */
    class C10104 implements Callable<Void> {
        final /* synthetic */ CacheKey val$key;

        C10104(CacheKey cacheKey) {
            this.val$key = cacheKey;
        }

        public Void call() {
            BufferedDiskCache.this.mStagingArea.remove(this.val$key);
            BufferedDiskCache.this.mFileCache.remove(this.val$key);
            return null;
        }
    }

    /* renamed from: com.facebook.imagepipeline.cache.BufferedDiskCache.5 */
    class C10115 implements Callable<Void> {
        C10115() {
        }

        public Void call() {
            BufferedDiskCache.this.mStagingArea.clearAll();
            BufferedDiskCache.this.mFileCache.clearAll();
            return null;
        }
    }

    /* renamed from: com.facebook.imagepipeline.cache.BufferedDiskCache.6 */
    class C10126 implements WriterCallback {
        final /* synthetic */ EncodedImage val$encodedImage;

        C10126(EncodedImage encodedImage) {
            this.val$encodedImage = encodedImage;
        }

        public void write(OutputStream outputStream) {
            BufferedDiskCache.this.mPooledByteStreams.copy(this.val$encodedImage.getInputStream(), outputStream);
        }
    }

    static {
        TAG = BufferedDiskCache.class;
    }

    public BufferedDiskCache(FileCache fileCache, PooledByteBufferFactory pooledByteBufferFactory, PooledByteStreams pooledByteStreams, Executor executor, Executor executor2, ImageCacheStatsTracker imageCacheStatsTracker) {
        this.mFileCache = fileCache;
        this.mPooledByteBufferFactory = pooledByteBufferFactory;
        this.mPooledByteStreams = pooledByteStreams;
        this.mReadExecutor = executor;
        this.mWriteExecutor = executor2;
        this.mImageCacheStatsTracker = imageCacheStatsTracker;
        this.mStagingArea = StagingArea.getInstance();
    }

    private PooledByteBuffer readFromDiskCache(CacheKey cacheKey) {
        InputStream openStream;
        try {
            FLog.m7618v(TAG, "Disk cache read for %s", cacheKey.toString());
            BinaryResource resource = this.mFileCache.getResource(cacheKey);
            if (resource == null) {
                FLog.m7618v(TAG, "Disk cache miss for %s", cacheKey.toString());
                this.mImageCacheStatsTracker.onDiskCacheMiss();
                return null;
            }
            FLog.m7618v(TAG, "Found entry in disk cache for %s", cacheKey.toString());
            this.mImageCacheStatsTracker.onDiskCacheHit();
            openStream = resource.openStream();
            PooledByteBuffer newByteBuffer = this.mPooledByteBufferFactory.newByteBuffer(openStream, (int) resource.size());
            openStream.close();
            FLog.m7618v(TAG, "Successful read from disk cache for %s", cacheKey.toString());
            return newByteBuffer;
        } catch (Throwable e) {
            FLog.m7636w(TAG, e, "Exception reading from cache for %s", cacheKey.toString());
            this.mImageCacheStatsTracker.onDiskCacheGetFail();
            throw e;
        } catch (Throwable th) {
            openStream.close();
        }
    }

    private void writeToDiskCache(CacheKey cacheKey, EncodedImage encodedImage) {
        FLog.m7618v(TAG, "About to write to disk-cache for key %s", cacheKey.toString());
        try {
            this.mFileCache.insert(cacheKey, new C10126(encodedImage));
            FLog.m7618v(TAG, "Successful disk-cache write for key %s", cacheKey.toString());
        } catch (Throwable e) {
            FLog.m7636w(TAG, e, "Failed to write to disk-cache for key %s", cacheKey.toString());
        }
    }

    public C0018s<Void> clearAll() {
        this.mStagingArea.clearAll();
        try {
            return C0018s.m80a(new C10115(), this.mWriteExecutor);
        } catch (Exception e) {
            FLog.m7636w(TAG, (Throwable) e, "Failed to schedule disk-cache clear", new Object[0]);
            return C0018s.m76a(e);
        }
    }

    public C0018s<Boolean> contains(CacheKey cacheKey) {
        Preconditions.checkNotNull(cacheKey);
        EncodedImage encodedImage = this.mStagingArea.get(cacheKey);
        if (encodedImage != null) {
            encodedImage.close();
            FLog.m7618v(TAG, "Found image for %s in staging area", cacheKey.toString());
            this.mImageCacheStatsTracker.onStagingAreaHit();
            return C0018s.m77a(Boolean.valueOf(true));
        }
        try {
            return C0018s.m80a(new C10071(cacheKey), this.mReadExecutor);
        } catch (Exception e) {
            FLog.m7636w(TAG, (Throwable) e, "Failed to schedule disk-cache read for %s", cacheKey.toString());
            return C0018s.m76a(e);
        }
    }

    public C0018s<EncodedImage> get(CacheKey cacheKey, AtomicBoolean atomicBoolean) {
        Preconditions.checkNotNull(cacheKey);
        Preconditions.checkNotNull(atomicBoolean);
        Object obj = this.mStagingArea.get(cacheKey);
        if (obj != null) {
            FLog.m7618v(TAG, "Found image for %s in staging area", cacheKey.toString());
            this.mImageCacheStatsTracker.onStagingAreaHit();
            return C0018s.m77a(obj);
        }
        try {
            return C0018s.m80a(new C10082(atomicBoolean, cacheKey), this.mReadExecutor);
        } catch (Exception e) {
            FLog.m7636w(TAG, (Throwable) e, "Failed to schedule disk-cache read for %s", cacheKey.toString());
            return C0018s.m76a(e);
        }
    }

    public void put(CacheKey cacheKey, EncodedImage encodedImage) {
        Preconditions.checkNotNull(cacheKey);
        Preconditions.checkArgument(EncodedImage.isValid(encodedImage));
        this.mStagingArea.put(cacheKey, encodedImage);
        EncodedImage cloneOrNull = EncodedImage.cloneOrNull(encodedImage);
        try {
            this.mWriteExecutor.execute(new C10093(cacheKey, cloneOrNull));
        } catch (Throwable e) {
            FLog.m7636w(TAG, e, "Failed to schedule disk-cache write for %s", cacheKey.toString());
            this.mStagingArea.remove(cacheKey, encodedImage);
            EncodedImage.closeSafely(cloneOrNull);
        }
    }

    public C0018s<Void> remove(CacheKey cacheKey) {
        Preconditions.checkNotNull(cacheKey);
        this.mStagingArea.remove(cacheKey);
        try {
            return C0018s.m80a(new C10104(cacheKey), this.mWriteExecutor);
        } catch (Exception e) {
            FLog.m7636w(TAG, (Throwable) e, "Failed to schedule disk-cache remove for %s", cacheKey.toString());
            return C0018s.m76a(e);
        }
    }
}
