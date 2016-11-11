package com.facebook.imagepipeline.core;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap.Config;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.memory.NoOpMemoryTrimmableRegistry;
import com.facebook.imagepipeline.animated.factory.AnimatedImageFactory;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.DefaultBitmapMemoryCacheParamsSupplier;
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory;
import com.facebook.imagepipeline.cache.DefaultEncodedMemoryCacheParamsSupplier;
import com.facebook.imagepipeline.cache.ImageCacheStatsTracker;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.cache.NoOpImageCacheStatsTracker;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.memory.PoolConfig;
import com.facebook.imagepipeline.memory.PoolFactory;
import com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nullable;

public class ImagePipelineConfig {
    @Nullable
    private final AnimatedImageFactory mAnimatedImageFactory;
    private final Config mBitmapConfig;
    private final Supplier<MemoryCacheParams> mBitmapMemoryCacheParamsSupplier;
    private final CacheKeyFactory mCacheKeyFactory;
    private final Context mContext;
    private final boolean mDecodeFileDescriptorEnabled;
    private final boolean mDecodeMemoryFileEnabled;
    private final boolean mDownsampleEnabled;
    private final Supplier<MemoryCacheParams> mEncodedMemoryCacheParamsSupplier;
    private final ExecutorSupplier mExecutorSupplier;
    private final ImageCacheStatsTracker mImageCacheStatsTracker;
    @Nullable
    private final ImageDecoder mImageDecoder;
    private final Supplier<Boolean> mIsPrefetchEnabledSupplier;
    private final DiskCacheConfig mMainDiskCacheConfig;
    private final MemoryTrimmableRegistry mMemoryTrimmableRegistry;
    private final NetworkFetcher mNetworkFetcher;
    @Nullable
    private final PlatformBitmapFactory mPlatformBitmapFactory;
    private final PoolFactory mPoolFactory;
    private final ProgressiveJpegConfig mProgressiveJpegConfig;
    private final Set<RequestListener> mRequestListeners;
    private final boolean mResizeAndRotateEnabledForNetwork;
    private final DiskCacheConfig mSmallImageDiskCacheConfig;

    /* renamed from: com.facebook.imagepipeline.core.ImagePipelineConfig.1 */
    class C10251 implements Supplier<Boolean> {
        C10251() {
        }

        public Boolean get() {
            return Boolean.valueOf(true);
        }
    }

    /* renamed from: com.facebook.imagepipeline.core.ImagePipelineConfig.2 */
    final class C10262 implements Supplier<File> {
        final /* synthetic */ Context val$context;

        C10262(Context context) {
            this.val$context = context;
        }

        public File get() {
            return this.val$context.getApplicationContext().getCacheDir();
        }
    }

    public class Builder {
        private AnimatedImageFactory mAnimatedImageFactory;
        private Config mBitmapConfig;
        private Supplier<MemoryCacheParams> mBitmapMemoryCacheParamsSupplier;
        private CacheKeyFactory mCacheKeyFactory;
        private final Context mContext;
        private boolean mDecodeFileDescriptorEnabled;
        private boolean mDecodeMemoryFileEnabled;
        private boolean mDownsampleEnabled;
        private Supplier<MemoryCacheParams> mEncodedMemoryCacheParamsSupplier;
        private ExecutorSupplier mExecutorSupplier;
        private ImageCacheStatsTracker mImageCacheStatsTracker;
        private ImageDecoder mImageDecoder;
        private Supplier<Boolean> mIsPrefetchEnabledSupplier;
        private DiskCacheConfig mMainDiskCacheConfig;
        private MemoryTrimmableRegistry mMemoryTrimmableRegistry;
        private NetworkFetcher mNetworkFetcher;
        private PlatformBitmapFactory mPlatformBitmapFactory;
        private PoolFactory mPoolFactory;
        private ProgressiveJpegConfig mProgressiveJpegConfig;
        private Set<RequestListener> mRequestListeners;
        private boolean mResizeAndRotateEnabledForNetwork;
        private DiskCacheConfig mSmallImageDiskCacheConfig;

        private Builder(Context context) {
            this.mDownsampleEnabled = false;
            this.mDecodeFileDescriptorEnabled = this.mDownsampleEnabled;
            this.mResizeAndRotateEnabledForNetwork = true;
            this.mContext = (Context) Preconditions.checkNotNull(context);
        }

        public ImagePipelineConfig build() {
            return new ImagePipelineConfig();
        }

        public Builder setAnimatedImageFactory(AnimatedImageFactory animatedImageFactory) {
            this.mAnimatedImageFactory = animatedImageFactory;
            return this;
        }

        public Builder setBitmapMemoryCacheParamsSupplier(Supplier<MemoryCacheParams> supplier) {
            this.mBitmapMemoryCacheParamsSupplier = (Supplier) Preconditions.checkNotNull(supplier);
            return this;
        }

        public Builder setBitmapsConfig(Config config) {
            this.mBitmapConfig = config;
            return this;
        }

        public Builder setCacheKeyFactory(CacheKeyFactory cacheKeyFactory) {
            this.mCacheKeyFactory = cacheKeyFactory;
            return this;
        }

        public Builder setDecodeFileDescriptorEnabled(boolean z) {
            this.mDecodeFileDescriptorEnabled = z;
            return this;
        }

        public Builder setDecodeMemoryFileEnabled(boolean z) {
            this.mDecodeMemoryFileEnabled = z;
            return this;
        }

        public Builder setDownsampleEnabled(boolean z) {
            this.mDownsampleEnabled = z;
            return this;
        }

        public Builder setEncodedMemoryCacheParamsSupplier(Supplier<MemoryCacheParams> supplier) {
            this.mEncodedMemoryCacheParamsSupplier = (Supplier) Preconditions.checkNotNull(supplier);
            return this;
        }

        public Builder setExecutorSupplier(ExecutorSupplier executorSupplier) {
            this.mExecutorSupplier = executorSupplier;
            return this;
        }

        public Builder setImageCacheStatsTracker(ImageCacheStatsTracker imageCacheStatsTracker) {
            this.mImageCacheStatsTracker = imageCacheStatsTracker;
            return this;
        }

        public Builder setImageDecoder(ImageDecoder imageDecoder) {
            this.mImageDecoder = imageDecoder;
            return this;
        }

        public Builder setIsPrefetchEnabledSupplier(Supplier<Boolean> supplier) {
            this.mIsPrefetchEnabledSupplier = supplier;
            return this;
        }

        public Builder setMainDiskCacheConfig(DiskCacheConfig diskCacheConfig) {
            this.mMainDiskCacheConfig = diskCacheConfig;
            return this;
        }

        public Builder setMemoryTrimmableRegistry(MemoryTrimmableRegistry memoryTrimmableRegistry) {
            this.mMemoryTrimmableRegistry = memoryTrimmableRegistry;
            return this;
        }

        public Builder setNetworkFetcher(NetworkFetcher networkFetcher) {
            this.mNetworkFetcher = networkFetcher;
            return this;
        }

        public Builder setPlatformBitmapFactory(PlatformBitmapFactory platformBitmapFactory) {
            this.mPlatformBitmapFactory = platformBitmapFactory;
            return this;
        }

        public Builder setPoolFactory(PoolFactory poolFactory) {
            this.mPoolFactory = poolFactory;
            return this;
        }

        public Builder setProgressiveJpegConfig(ProgressiveJpegConfig progressiveJpegConfig) {
            this.mProgressiveJpegConfig = progressiveJpegConfig;
            return this;
        }

        public Builder setRequestListeners(Set<RequestListener> set) {
            this.mRequestListeners = set;
            return this;
        }

        public Builder setResizeAndRotateEnabledForNetwork(boolean z) {
            this.mResizeAndRotateEnabledForNetwork = z;
            return this;
        }

        public Builder setSmallImageDiskCacheConfig(DiskCacheConfig diskCacheConfig) {
            this.mSmallImageDiskCacheConfig = diskCacheConfig;
            return this;
        }
    }

    private ImagePipelineConfig(Builder builder) {
        this.mAnimatedImageFactory = builder.mAnimatedImageFactory;
        this.mBitmapMemoryCacheParamsSupplier = builder.mBitmapMemoryCacheParamsSupplier == null ? new DefaultBitmapMemoryCacheParamsSupplier((ActivityManager) builder.mContext.getSystemService("activity")) : builder.mBitmapMemoryCacheParamsSupplier;
        this.mBitmapConfig = builder.mBitmapConfig == null ? Config.ARGB_8888 : builder.mBitmapConfig;
        this.mCacheKeyFactory = builder.mCacheKeyFactory == null ? DefaultCacheKeyFactory.getInstance() : builder.mCacheKeyFactory;
        this.mContext = (Context) Preconditions.checkNotNull(builder.mContext);
        boolean z = builder.mDownsampleEnabled && builder.mDecodeFileDescriptorEnabled;
        this.mDecodeFileDescriptorEnabled = z;
        this.mDecodeMemoryFileEnabled = builder.mDecodeMemoryFileEnabled;
        this.mDownsampleEnabled = builder.mDownsampleEnabled;
        this.mEncodedMemoryCacheParamsSupplier = builder.mEncodedMemoryCacheParamsSupplier == null ? new DefaultEncodedMemoryCacheParamsSupplier() : builder.mEncodedMemoryCacheParamsSupplier;
        this.mImageCacheStatsTracker = builder.mImageCacheStatsTracker == null ? NoOpImageCacheStatsTracker.getInstance() : builder.mImageCacheStatsTracker;
        this.mImageDecoder = builder.mImageDecoder;
        this.mIsPrefetchEnabledSupplier = builder.mIsPrefetchEnabledSupplier == null ? new C10251() : builder.mIsPrefetchEnabledSupplier;
        this.mMainDiskCacheConfig = builder.mMainDiskCacheConfig == null ? getDefaultMainDiskCacheConfig(builder.mContext) : builder.mMainDiskCacheConfig;
        this.mMemoryTrimmableRegistry = builder.mMemoryTrimmableRegistry == null ? NoOpMemoryTrimmableRegistry.getInstance() : builder.mMemoryTrimmableRegistry;
        this.mNetworkFetcher = builder.mNetworkFetcher == null ? new HttpUrlConnectionNetworkFetcher() : builder.mNetworkFetcher;
        this.mPlatformBitmapFactory = builder.mPlatformBitmapFactory;
        this.mPoolFactory = builder.mPoolFactory == null ? new PoolFactory(PoolConfig.newBuilder().build()) : builder.mPoolFactory;
        this.mProgressiveJpegConfig = builder.mProgressiveJpegConfig == null ? new SimpleProgressiveJpegConfig() : builder.mProgressiveJpegConfig;
        this.mRequestListeners = builder.mRequestListeners == null ? new HashSet() : builder.mRequestListeners;
        this.mResizeAndRotateEnabledForNetwork = builder.mResizeAndRotateEnabledForNetwork;
        this.mSmallImageDiskCacheConfig = builder.mSmallImageDiskCacheConfig == null ? this.mMainDiskCacheConfig : builder.mSmallImageDiskCacheConfig;
        this.mExecutorSupplier = builder.mExecutorSupplier == null ? new DefaultExecutorSupplier(this.mPoolFactory.getFlexByteArrayPoolMaxNumThreads()) : builder.mExecutorSupplier;
    }

    private static DiskCacheConfig getDefaultMainDiskCacheConfig(Context context) {
        return DiskCacheConfig.newBuilder().setBaseDirectoryPathSupplier(new C10262(context)).setBaseDirectoryName("image_cache").setMaxCacheSize(41943040).setMaxCacheSizeOnLowDiskSpace(10485760).setMaxCacheSizeOnVeryLowDiskSpace(2097152).build();
    }

    public static Builder newBuilder(Context context) {
        return new Builder(null);
    }

    @Nullable
    public AnimatedImageFactory getAnimatedImageFactory() {
        return this.mAnimatedImageFactory;
    }

    public Config getBitmapConfig() {
        return this.mBitmapConfig;
    }

    public Supplier<MemoryCacheParams> getBitmapMemoryCacheParamsSupplier() {
        return this.mBitmapMemoryCacheParamsSupplier;
    }

    public CacheKeyFactory getCacheKeyFactory() {
        return this.mCacheKeyFactory;
    }

    public Context getContext() {
        return this.mContext;
    }

    public Supplier<MemoryCacheParams> getEncodedMemoryCacheParamsSupplier() {
        return this.mEncodedMemoryCacheParamsSupplier;
    }

    public ExecutorSupplier getExecutorSupplier() {
        return this.mExecutorSupplier;
    }

    public ImageCacheStatsTracker getImageCacheStatsTracker() {
        return this.mImageCacheStatsTracker;
    }

    @Nullable
    public ImageDecoder getImageDecoder() {
        return this.mImageDecoder;
    }

    public Supplier<Boolean> getIsPrefetchEnabledSupplier() {
        return this.mIsPrefetchEnabledSupplier;
    }

    public DiskCacheConfig getMainDiskCacheConfig() {
        return this.mMainDiskCacheConfig;
    }

    public MemoryTrimmableRegistry getMemoryTrimmableRegistry() {
        return this.mMemoryTrimmableRegistry;
    }

    public NetworkFetcher getNetworkFetcher() {
        return this.mNetworkFetcher;
    }

    @Nullable
    public PlatformBitmapFactory getPlatformBitmapFactory() {
        return this.mPlatformBitmapFactory;
    }

    public PoolFactory getPoolFactory() {
        return this.mPoolFactory;
    }

    public ProgressiveJpegConfig getProgressiveJpegConfig() {
        return this.mProgressiveJpegConfig;
    }

    public Set<RequestListener> getRequestListeners() {
        return Collections.unmodifiableSet(this.mRequestListeners);
    }

    public DiskCacheConfig getSmallImageDiskCacheConfig() {
        return this.mSmallImageDiskCacheConfig;
    }

    public boolean isDecodeFileDescriptorEnabled() {
        return this.mDecodeFileDescriptorEnabled;
    }

    public boolean isDecodeMemoryFileEnabled() {
        return this.mDecodeMemoryFileEnabled;
    }

    public boolean isDownsampleEnabled() {
        return this.mDownsampleEnabled;
    }

    public boolean isResizeAndRotateEnabledForNetwork() {
        return this.mResizeAndRotateEnabledForNetwork;
    }
}
