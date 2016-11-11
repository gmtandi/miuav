package com.facebook.imagepipeline.core;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build.VERSION;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.disk.DiskCacheFactory;
import com.facebook.cache.disk.DiskStorageCache;
import com.facebook.common.executors.DefaultSerialExecutorService;
import com.facebook.common.executors.SerialExecutorService;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.internal.AndroidPredicates;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.time.MonotonicClock;
import com.facebook.common.time.RealtimeSinceBootClock;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableBackend;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableOptions;
import com.facebook.imagepipeline.animated.base.AnimatedImageResult;
import com.facebook.imagepipeline.animated.factory.AnimatedDrawableFactory;
import com.facebook.imagepipeline.animated.factory.AnimatedImageFactory;
import com.facebook.imagepipeline.animated.impl.AnimatedDrawableBackendImpl;
import com.facebook.imagepipeline.animated.impl.AnimatedDrawableBackendProvider;
import com.facebook.imagepipeline.animated.impl.AnimatedDrawableCachingBackendImpl;
import com.facebook.imagepipeline.animated.impl.AnimatedDrawableCachingBackendImplProvider;
import com.facebook.imagepipeline.animated.util.AnimatedDrawableUtil;
import com.facebook.imagepipeline.bitmaps.ArtBitmapFactory;
import com.facebook.imagepipeline.bitmaps.EmptyJpegGenerator;
import com.facebook.imagepipeline.bitmaps.GingerbreadBitmapFactory;
import com.facebook.imagepipeline.bitmaps.HoneycombBitmapFactory;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.cache.BitmapCountingMemoryCacheFactory;
import com.facebook.imagepipeline.cache.BitmapMemoryCacheFactory;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import com.facebook.imagepipeline.cache.EncodedCountingMemoryCacheFactory;
import com.facebook.imagepipeline.cache.EncodedMemoryCacheFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.memory.PoolFactory;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.imagepipeline.platform.ArtDecoder;
import com.facebook.imagepipeline.platform.GingerbreadPurgeableDecoder;
import com.facebook.imagepipeline.platform.KitKatPurgeableDecoder;
import com.facebook.imagepipeline.platform.PlatformDecoder;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class ImagePipelineFactory {
    private static ImagePipelineFactory sInstance;
    private AnimatedDrawableBackendProvider mAnimatedDrawableBackendProvider;
    private AnimatedDrawableFactory mAnimatedDrawableFactory;
    private AnimatedDrawableUtil mAnimatedDrawableUtil;
    private AnimatedImageFactory mAnimatedImageFactory;
    private CountingMemoryCache<CacheKey, CloseableImage> mBitmapCountingMemoryCache;
    private MemoryCache<CacheKey, CloseableImage> mBitmapMemoryCache;
    private final ImagePipelineConfig mConfig;
    private CountingMemoryCache<CacheKey, PooledByteBuffer> mEncodedCountingMemoryCache;
    private MemoryCache<CacheKey, PooledByteBuffer> mEncodedMemoryCache;
    private ImageDecoder mImageDecoder;
    private ImagePipeline mImagePipeline;
    private BufferedDiskCache mMainBufferedDiskCache;
    private DiskStorageCache mMainDiskStorageCache;
    private PlatformBitmapFactory mPlatformBitmapFactory;
    private PlatformDecoder mPlatformDecoder;
    private ProducerFactory mProducerFactory;
    private ProducerSequenceFactory mProducerSequenceFactory;
    private BufferedDiskCache mSmallImageBufferedDiskCache;
    private DiskStorageCache mSmallImageDiskStorageCache;

    /* renamed from: com.facebook.imagepipeline.core.ImagePipelineFactory.1 */
    final class C10271 implements AnimatedDrawableCachingBackendImplProvider {
        final /* synthetic */ ActivityManager val$activityManager;
        final /* synthetic */ AnimatedDrawableUtil val$animatedDrawableUtil;
        final /* synthetic */ MonotonicClock val$monotonicClock;
        final /* synthetic */ SerialExecutorService val$serialExecutorService;

        C10271(SerialExecutorService serialExecutorService, ActivityManager activityManager, AnimatedDrawableUtil animatedDrawableUtil, MonotonicClock monotonicClock) {
            this.val$serialExecutorService = serialExecutorService;
            this.val$activityManager = activityManager;
            this.val$animatedDrawableUtil = animatedDrawableUtil;
            this.val$monotonicClock = monotonicClock;
        }

        public AnimatedDrawableCachingBackendImpl get(AnimatedDrawableBackend animatedDrawableBackend, AnimatedDrawableOptions animatedDrawableOptions) {
            return new AnimatedDrawableCachingBackendImpl(this.val$serialExecutorService, this.val$activityManager, this.val$animatedDrawableUtil, this.val$monotonicClock, animatedDrawableBackend, animatedDrawableOptions);
        }
    }

    /* renamed from: com.facebook.imagepipeline.core.ImagePipelineFactory.2 */
    class C10282 implements AnimatedDrawableBackendProvider {
        C10282() {
        }

        public AnimatedDrawableBackend get(AnimatedImageResult animatedImageResult, Rect rect) {
            return new AnimatedDrawableBackendImpl(ImagePipelineFactory.this.getAnimatedDrawableUtil(), animatedImageResult, rect);
        }
    }

    /* renamed from: com.facebook.imagepipeline.core.ImagePipelineFactory.3 */
    final class C10293 implements AnimatedDrawableBackendProvider {
        final /* synthetic */ AnimatedDrawableUtil val$animatedDrawableUtil;

        C10293(AnimatedDrawableUtil animatedDrawableUtil) {
            this.val$animatedDrawableUtil = animatedDrawableUtil;
        }

        public AnimatedDrawableBackend get(AnimatedImageResult animatedImageResult, Rect rect) {
            return new AnimatedDrawableBackendImpl(this.val$animatedDrawableUtil, animatedImageResult, rect);
        }
    }

    static {
        sInstance = null;
    }

    public ImagePipelineFactory(ImagePipelineConfig imagePipelineConfig) {
        this.mConfig = (ImagePipelineConfig) Preconditions.checkNotNull(imagePipelineConfig);
    }

    public static AnimatedDrawableFactory buildAnimatedDrawableFactory(SerialExecutorService serialExecutorService, ActivityManager activityManager, AnimatedDrawableUtil animatedDrawableUtil, AnimatedDrawableBackendProvider animatedDrawableBackendProvider, ScheduledExecutorService scheduledExecutorService, MonotonicClock monotonicClock, Resources resources) {
        return new AnimatedDrawableFactory(animatedDrawableBackendProvider, new C10271(serialExecutorService, activityManager, animatedDrawableUtil, monotonicClock), animatedDrawableUtil, scheduledExecutorService, resources);
    }

    public static AnimatedImageFactory buildAnimatedImageFactory(AnimatedDrawableUtil animatedDrawableUtil, PlatformBitmapFactory platformBitmapFactory) {
        return new AnimatedImageFactory(new C10293(animatedDrawableUtil), platformBitmapFactory);
    }

    public static PlatformBitmapFactory buildPlatformBitmapFactory(PoolFactory poolFactory, PlatformDecoder platformDecoder) {
        return VERSION.SDK_INT >= 21 ? new ArtBitmapFactory(poolFactory.getBitmapPool()) : VERSION.SDK_INT >= 11 ? new HoneycombBitmapFactory(new EmptyJpegGenerator(poolFactory.getPooledByteBufferFactory()), platformDecoder) : new GingerbreadBitmapFactory();
    }

    public static PlatformDecoder buildPlatformDecoder(PoolFactory poolFactory, boolean z) {
        return VERSION.SDK_INT >= 21 ? new ArtDecoder(poolFactory.getBitmapPool(), poolFactory.getFlexByteArrayPoolMaxNumThreads()) : (!z || VERSION.SDK_INT >= 19) ? new KitKatPurgeableDecoder(poolFactory.getFlexByteArrayPool()) : new GingerbreadPurgeableDecoder();
    }

    private AnimatedDrawableUtil getAnimatedDrawableUtil() {
        if (this.mAnimatedDrawableUtil == null) {
            this.mAnimatedDrawableUtil = new AnimatedDrawableUtil();
        }
        return this.mAnimatedDrawableUtil;
    }

    private AnimatedImageFactory getAnimatedImageFactory() {
        if (this.mAnimatedImageFactory == null) {
            if (this.mConfig.getAnimatedImageFactory() != null) {
                this.mAnimatedImageFactory = this.mConfig.getAnimatedImageFactory();
            } else {
                this.mAnimatedImageFactory = buildAnimatedImageFactory(getAnimatedDrawableUtil(), getPlatformBitmapFactory());
            }
        }
        return this.mAnimatedImageFactory;
    }

    private ImageDecoder getImageDecoder() {
        if (this.mImageDecoder == null) {
            if (this.mConfig.getImageDecoder() != null) {
                this.mImageDecoder = this.mConfig.getImageDecoder();
            } else {
                this.mImageDecoder = new ImageDecoder(getAnimatedImageFactory(), getPlatformDecoder(), this.mConfig.getBitmapConfig());
            }
        }
        return this.mImageDecoder;
    }

    public static ImagePipelineFactory getInstance() {
        return (ImagePipelineFactory) Preconditions.checkNotNull(sInstance, "ImagePipelineFactory was not initialized!");
    }

    private BufferedDiskCache getMainBufferedDiskCache() {
        if (this.mMainBufferedDiskCache == null) {
            this.mMainBufferedDiskCache = new BufferedDiskCache(getMainDiskStorageCache(), this.mConfig.getPoolFactory().getPooledByteBufferFactory(), this.mConfig.getPoolFactory().getPooledByteStreams(), this.mConfig.getExecutorSupplier().forLocalStorageRead(), this.mConfig.getExecutorSupplier().forLocalStorageWrite(), this.mConfig.getImageCacheStatsTracker());
        }
        return this.mMainBufferedDiskCache;
    }

    private ProducerFactory getProducerFactory() {
        if (this.mProducerFactory == null) {
            this.mProducerFactory = new ProducerFactory(this.mConfig.getContext(), this.mConfig.getPoolFactory().getSmallByteArrayPool(), getImageDecoder(), this.mConfig.getProgressiveJpegConfig(), this.mConfig.isDownsampleEnabled(), this.mConfig.isResizeAndRotateEnabledForNetwork(), this.mConfig.getExecutorSupplier(), this.mConfig.getPoolFactory().getPooledByteBufferFactory(), getBitmapMemoryCache(), getEncodedMemoryCache(), getMainBufferedDiskCache(), getSmallImageBufferedDiskCache(), this.mConfig.getCacheKeyFactory(), getPlatformBitmapFactory(), this.mConfig.isDecodeFileDescriptorEnabled());
        }
        return this.mProducerFactory;
    }

    private ProducerSequenceFactory getProducerSequenceFactory() {
        if (this.mProducerSequenceFactory == null) {
            this.mProducerSequenceFactory = new ProducerSequenceFactory(getProducerFactory(), this.mConfig.getNetworkFetcher(), this.mConfig.isResizeAndRotateEnabledForNetwork(), this.mConfig.isDownsampleEnabled());
        }
        return this.mProducerSequenceFactory;
    }

    private BufferedDiskCache getSmallImageBufferedDiskCache() {
        if (this.mSmallImageBufferedDiskCache == null) {
            this.mSmallImageBufferedDiskCache = new BufferedDiskCache(getSmallImageDiskStorageCache(), this.mConfig.getPoolFactory().getPooledByteBufferFactory(), this.mConfig.getPoolFactory().getPooledByteStreams(), this.mConfig.getExecutorSupplier().forLocalStorageRead(), this.mConfig.getExecutorSupplier().forLocalStorageWrite(), this.mConfig.getImageCacheStatsTracker());
        }
        return this.mSmallImageBufferedDiskCache;
    }

    public static void initialize(Context context) {
        initialize(ImagePipelineConfig.newBuilder(context).build());
    }

    public static void initialize(ImagePipelineConfig imagePipelineConfig) {
        sInstance = new ImagePipelineFactory(imagePipelineConfig);
    }

    public static void shutDown() {
        if (sInstance != null) {
            sInstance.getBitmapMemoryCache().removeAll(AndroidPredicates.True());
            sInstance.getEncodedMemoryCache().removeAll(AndroidPredicates.True());
            sInstance = null;
        }
    }

    public AnimatedDrawableBackendProvider getAnimatedDrawableBackendProvider() {
        if (this.mAnimatedDrawableBackendProvider == null) {
            this.mAnimatedDrawableBackendProvider = new C10282();
        }
        return this.mAnimatedDrawableBackendProvider;
    }

    public AnimatedDrawableFactory getAnimatedDrawableFactory() {
        if (this.mAnimatedDrawableFactory == null) {
            this.mAnimatedDrawableFactory = buildAnimatedDrawableFactory(new DefaultSerialExecutorService(this.mConfig.getExecutorSupplier().forDecode()), (ActivityManager) this.mConfig.getContext().getSystemService("activity"), getAnimatedDrawableUtil(), getAnimatedDrawableBackendProvider(), UiThreadImmediateExecutorService.getInstance(), RealtimeSinceBootClock.get(), this.mConfig.getContext().getResources());
        }
        return this.mAnimatedDrawableFactory;
    }

    public CountingMemoryCache<CacheKey, CloseableImage> getBitmapCountingMemoryCache() {
        if (this.mBitmapCountingMemoryCache == null) {
            this.mBitmapCountingMemoryCache = BitmapCountingMemoryCacheFactory.get(this.mConfig.getBitmapMemoryCacheParamsSupplier(), this.mConfig.getMemoryTrimmableRegistry());
        }
        return this.mBitmapCountingMemoryCache;
    }

    public MemoryCache<CacheKey, CloseableImage> getBitmapMemoryCache() {
        if (this.mBitmapMemoryCache == null) {
            this.mBitmapMemoryCache = BitmapMemoryCacheFactory.get(getBitmapCountingMemoryCache(), this.mConfig.getImageCacheStatsTracker());
        }
        return this.mBitmapMemoryCache;
    }

    public CountingMemoryCache<CacheKey, PooledByteBuffer> getEncodedCountingMemoryCache() {
        if (this.mEncodedCountingMemoryCache == null) {
            this.mEncodedCountingMemoryCache = EncodedCountingMemoryCacheFactory.get(this.mConfig.getEncodedMemoryCacheParamsSupplier(), this.mConfig.getMemoryTrimmableRegistry());
        }
        return this.mEncodedCountingMemoryCache;
    }

    public MemoryCache<CacheKey, PooledByteBuffer> getEncodedMemoryCache() {
        if (this.mEncodedMemoryCache == null) {
            this.mEncodedMemoryCache = EncodedMemoryCacheFactory.get(getEncodedCountingMemoryCache(), this.mConfig.getImageCacheStatsTracker());
        }
        return this.mEncodedMemoryCache;
    }

    public ImagePipeline getImagePipeline() {
        if (this.mImagePipeline == null) {
            this.mImagePipeline = new ImagePipeline(getProducerSequenceFactory(), this.mConfig.getRequestListeners(), this.mConfig.getIsPrefetchEnabledSupplier(), getBitmapMemoryCache(), getEncodedMemoryCache(), getMainBufferedDiskCache(), getSmallImageBufferedDiskCache(), this.mConfig.getCacheKeyFactory());
        }
        return this.mImagePipeline;
    }

    public DiskStorageCache getMainDiskStorageCache() {
        if (this.mMainDiskStorageCache == null) {
            this.mMainDiskStorageCache = DiskCacheFactory.newDiskStorageCache(this.mConfig.getMainDiskCacheConfig());
        }
        return this.mMainDiskStorageCache;
    }

    public PlatformBitmapFactory getPlatformBitmapFactory() {
        if (this.mPlatformBitmapFactory == null) {
            this.mPlatformBitmapFactory = buildPlatformBitmapFactory(this.mConfig.getPoolFactory(), getPlatformDecoder());
        }
        return this.mPlatformBitmapFactory;
    }

    public PlatformDecoder getPlatformDecoder() {
        if (this.mPlatformDecoder == null) {
            this.mPlatformDecoder = buildPlatformDecoder(this.mConfig.getPoolFactory(), this.mConfig.isDecodeMemoryFileEnabled());
        }
        return this.mPlatformDecoder;
    }

    public DiskStorageCache getSmallImageDiskStorageCache() {
        if (this.mSmallImageDiskStorageCache == null) {
            this.mSmallImageDiskStorageCache = DiskCacheFactory.newDiskStorageCache(this.mConfig.getSmallImageDiskCacheConfig());
        }
        return this.mSmallImageDiskStorageCache;
    }
}
