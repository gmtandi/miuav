package com.facebook.imagepipeline.core;

import android.net.Uri;
import com.android.internal.util.Predicate;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.UriUtil;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSources;
import com.facebook.datasource.SettableDataSource;
import com.facebook.imagepipeline.cache.BitmapMemoryCacheKey;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.datasource.CloseableProducerToDataSourceAdapter;
import com.facebook.imagepipeline.datasource.ProducerToDataSourceAdapter;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.listener.ForwardingRequestListener;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.producers.ProducerListener;
import com.facebook.imagepipeline.producers.SettableProducerContext;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequest.RequestLevel;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.concurrent.ThreadSafe;
import p000a.C0001q;
import p000a.C0018s;

@ThreadSafe
public class ImagePipeline {
    private static final CancellationException PREFETCH_EXCEPTION;
    private final MemoryCache<CacheKey, CloseableImage> mBitmapMemoryCache;
    private final CacheKeyFactory mCacheKeyFactory;
    private final MemoryCache<CacheKey, PooledByteBuffer> mEncodedMemoryCache;
    private AtomicLong mIdCounter;
    private final Supplier<Boolean> mIsPrefetchEnabledSupplier;
    private final BufferedDiskCache mMainBufferedDiskCache;
    private final ProducerSequenceFactory mProducerSequenceFactory;
    private final RequestListener mRequestListener;
    private final BufferedDiskCache mSmallImageBufferedDiskCache;

    /* renamed from: com.facebook.imagepipeline.core.ImagePipeline.1 */
    class C10181 implements Supplier<DataSource<CloseableReference<CloseableImage>>> {
        final /* synthetic */ boolean val$bitmapCacheOnly;
        final /* synthetic */ Object val$callerContext;
        final /* synthetic */ ImageRequest val$imageRequest;

        C10181(boolean z, ImageRequest imageRequest, Object obj) {
            this.val$bitmapCacheOnly = z;
            this.val$imageRequest = imageRequest;
            this.val$callerContext = obj;
        }

        public DataSource<CloseableReference<CloseableImage>> get() {
            return this.val$bitmapCacheOnly ? ImagePipeline.this.fetchImageFromBitmapCache(this.val$imageRequest, this.val$callerContext) : ImagePipeline.this.fetchDecodedImage(this.val$imageRequest, this.val$callerContext);
        }

        public String toString() {
            return Objects.toStringHelper((Object) this).add("uri", this.val$imageRequest.getSourceUri()).toString();
        }
    }

    /* renamed from: com.facebook.imagepipeline.core.ImagePipeline.2 */
    class C10192 implements Supplier<DataSource<CloseableReference<PooledByteBuffer>>> {
        final /* synthetic */ Object val$callerContext;
        final /* synthetic */ ImageRequest val$imageRequest;

        C10192(ImageRequest imageRequest, Object obj) {
            this.val$imageRequest = imageRequest;
            this.val$callerContext = obj;
        }

        public DataSource<CloseableReference<PooledByteBuffer>> get() {
            return ImagePipeline.this.fetchEncodedImage(this.val$imageRequest, this.val$callerContext);
        }

        public String toString() {
            return Objects.toStringHelper((Object) this).add("uri", this.val$imageRequest.getSourceUri()).toString();
        }
    }

    /* renamed from: com.facebook.imagepipeline.core.ImagePipeline.3 */
    class C10203 implements Predicate<CacheKey> {
        final /* synthetic */ String val$cacheKeySourceString;

        C10203(String str) {
            this.val$cacheKeySourceString = str;
        }

        public boolean apply(CacheKey cacheKey) {
            return cacheKey.toString().equals(this.val$cacheKeySourceString);
        }
    }

    /* renamed from: com.facebook.imagepipeline.core.ImagePipeline.4 */
    class C10214 implements Predicate<CacheKey> {
        C10214() {
        }

        public boolean apply(CacheKey cacheKey) {
            return true;
        }
    }

    /* renamed from: com.facebook.imagepipeline.core.ImagePipeline.5 */
    class C10225 implements C0001q<Boolean, Void> {
        final /* synthetic */ SettableDataSource val$dataSource;

        C10225(SettableDataSource settableDataSource) {
            this.val$dataSource = settableDataSource;
        }

        public Void then(C0018s<Boolean> c0018s) {
            SettableDataSource settableDataSource = this.val$dataSource;
            boolean z = (c0018s.m104c() || c0018s.m107d() || !((Boolean) c0018s.m108e()).booleanValue()) ? false : true;
            settableDataSource.setResult(Boolean.valueOf(z));
            return null;
        }
    }

    /* renamed from: com.facebook.imagepipeline.core.ImagePipeline.6 */
    class C10236 implements C0001q<Boolean, C0018s<Boolean>> {
        final /* synthetic */ CacheKey val$cacheKey;

        C10236(CacheKey cacheKey) {
            this.val$cacheKey = cacheKey;
        }

        public C0018s<Boolean> then(C0018s<Boolean> c0018s) {
            return (c0018s.m104c() || c0018s.m107d() || !((Boolean) c0018s.m108e()).booleanValue()) ? ImagePipeline.this.mSmallImageBufferedDiskCache.contains(this.val$cacheKey) : C0018s.m77a(Boolean.valueOf(true));
        }
    }

    /* renamed from: com.facebook.imagepipeline.core.ImagePipeline.7 */
    class C10247 implements Predicate<CacheKey> {
        final /* synthetic */ String val$cacheKeySourceString;

        C10247(String str) {
            this.val$cacheKeySourceString = str;
        }

        public boolean apply(CacheKey cacheKey) {
            return cacheKey instanceof BitmapMemoryCacheKey ? ((BitmapMemoryCacheKey) cacheKey).getSourceUriString().equals(this.val$cacheKeySourceString) : false;
        }
    }

    static {
        PREFETCH_EXCEPTION = new CancellationException("Prefetching is not enabled");
    }

    public ImagePipeline(ProducerSequenceFactory producerSequenceFactory, Set<RequestListener> set, Supplier<Boolean> supplier, MemoryCache<CacheKey, CloseableImage> memoryCache, MemoryCache<CacheKey, PooledByteBuffer> memoryCache2, BufferedDiskCache bufferedDiskCache, BufferedDiskCache bufferedDiskCache2, CacheKeyFactory cacheKeyFactory) {
        this.mIdCounter = new AtomicLong();
        this.mProducerSequenceFactory = producerSequenceFactory;
        this.mRequestListener = new ForwardingRequestListener(set);
        this.mIsPrefetchEnabledSupplier = supplier;
        this.mBitmapMemoryCache = memoryCache;
        this.mEncodedMemoryCache = memoryCache2;
        this.mMainBufferedDiskCache = bufferedDiskCache;
        this.mSmallImageBufferedDiskCache = bufferedDiskCache2;
        this.mCacheKeyFactory = cacheKeyFactory;
    }

    private String generateUniqueFutureId() {
        return String.valueOf(this.mIdCounter.getAndIncrement());
    }

    private Predicate<CacheKey> predicateForUri(Uri uri) {
        return new C10247(this.mCacheKeyFactory.getCacheKeySourceUri(uri).toString());
    }

    private <T> DataSource<CloseableReference<T>> submitFetchRequest(Producer<CloseableReference<T>> producer, ImageRequest imageRequest, RequestLevel requestLevel, Object obj) {
        boolean z = false;
        try {
            RequestLevel max = RequestLevel.getMax(imageRequest.getLowestPermittedRequestLevel(), requestLevel);
            String generateUniqueFutureId = generateUniqueFutureId();
            ProducerListener producerListener = this.mRequestListener;
            if (imageRequest.getProgressiveRenderingEnabled() || !UriUtil.isNetworkUri(imageRequest.getSourceUri())) {
                z = true;
            }
            return CloseableProducerToDataSourceAdapter.create(producer, new SettableProducerContext(imageRequest, generateUniqueFutureId, producerListener, obj, max, false, z, imageRequest.getPriority()), this.mRequestListener);
        } catch (Throwable e) {
            return DataSources.immediateFailedDataSource(e);
        }
    }

    private DataSource<Void> submitPrefetchRequest(Producer<Void> producer, ImageRequest imageRequest, RequestLevel requestLevel, Object obj) {
        try {
            return ProducerToDataSourceAdapter.create(producer, new SettableProducerContext(imageRequest, generateUniqueFutureId(), this.mRequestListener, obj, RequestLevel.getMax(imageRequest.getLowestPermittedRequestLevel(), requestLevel), true, false, Priority.LOW), this.mRequestListener);
        } catch (Throwable e) {
            return DataSources.immediateFailedDataSource(e);
        }
    }

    public void clearCaches() {
        clearMemoryCaches();
        clearDiskCaches();
    }

    public void clearDiskCaches() {
        this.mMainBufferedDiskCache.clearAll();
        this.mSmallImageBufferedDiskCache.clearAll();
    }

    public void clearMemoryCaches() {
        Predicate c10214 = new C10214();
        this.mBitmapMemoryCache.removeAll(c10214);
        this.mEncodedMemoryCache.removeAll(c10214);
    }

    public void evictFromCache(Uri uri) {
        evictFromMemoryCache(uri);
        evictFromDiskCache(uri);
    }

    public void evictFromDiskCache(Uri uri) {
        evictFromDiskCache(ImageRequest.fromUri(uri));
    }

    public void evictFromDiskCache(ImageRequest imageRequest) {
        CacheKey encodedCacheKey = this.mCacheKeyFactory.getEncodedCacheKey(imageRequest);
        this.mMainBufferedDiskCache.remove(encodedCacheKey);
        this.mSmallImageBufferedDiskCache.remove(encodedCacheKey);
    }

    public void evictFromMemoryCache(Uri uri) {
        this.mBitmapMemoryCache.removeAll(predicateForUri(uri));
        this.mEncodedMemoryCache.removeAll(new C10203(this.mCacheKeyFactory.getCacheKeySourceUri(uri).toString()));
    }

    public DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(ImageRequest imageRequest, Object obj) {
        try {
            return submitFetchRequest(this.mProducerSequenceFactory.getDecodedImageProducerSequence(imageRequest), imageRequest, RequestLevel.FULL_FETCH, obj);
        } catch (Throwable e) {
            return DataSources.immediateFailedDataSource(e);
        }
    }

    public DataSource<CloseableReference<PooledByteBuffer>> fetchEncodedImage(ImageRequest imageRequest, Object obj) {
        Preconditions.checkNotNull(imageRequest.getSourceUri());
        try {
            Producer encodedImageProducerSequence = this.mProducerSequenceFactory.getEncodedImageProducerSequence(imageRequest);
            if (imageRequest.getResizeOptions() != null) {
                imageRequest = ImageRequestBuilder.fromRequest(imageRequest).setResizeOptions(null).build();
            }
            return submitFetchRequest(encodedImageProducerSequence, imageRequest, RequestLevel.FULL_FETCH, obj);
        } catch (Throwable e) {
            return DataSources.immediateFailedDataSource(e);
        }
    }

    public DataSource<CloseableReference<CloseableImage>> fetchImageFromBitmapCache(ImageRequest imageRequest, Object obj) {
        try {
            return submitFetchRequest(this.mProducerSequenceFactory.getDecodedImageProducerSequence(imageRequest), imageRequest, RequestLevel.BITMAP_MEMORY_CACHE, obj);
        } catch (Throwable e) {
            return DataSources.immediateFailedDataSource(e);
        }
    }

    public Supplier<DataSource<CloseableReference<CloseableImage>>> getDataSourceSupplier(ImageRequest imageRequest, Object obj, boolean z) {
        return new C10181(z, imageRequest, obj);
    }

    public Supplier<DataSource<CloseableReference<PooledByteBuffer>>> getEncodedImageDataSourceSupplier(ImageRequest imageRequest, Object obj) {
        return new C10192(imageRequest, obj);
    }

    public boolean isInBitmapMemoryCache(Uri uri) {
        return this.mBitmapMemoryCache.contains(predicateForUri(uri));
    }

    public boolean isInBitmapMemoryCache(ImageRequest imageRequest) {
        CloseableReference closeableReference = this.mBitmapMemoryCache.get(this.mCacheKeyFactory.getBitmapCacheKey(imageRequest));
        try {
            boolean isValid = CloseableReference.isValid(closeableReference);
            return isValid;
        } finally {
            CloseableReference.closeSafely(closeableReference);
        }
    }

    public DataSource<Boolean> isInDiskCache(Uri uri) {
        return isInDiskCache(ImageRequest.fromUri(uri));
    }

    public DataSource<Boolean> isInDiskCache(ImageRequest imageRequest) {
        CacheKey encodedCacheKey = this.mCacheKeyFactory.getEncodedCacheKey(imageRequest);
        DataSource create = SettableDataSource.create();
        this.mMainBufferedDiskCache.contains(encodedCacheKey).m99b(new C10236(encodedCacheKey)).m95a(new C10225(create));
        return create;
    }

    public DataSource<Void> prefetchToBitmapCache(ImageRequest imageRequest, Object obj) {
        if (!((Boolean) this.mIsPrefetchEnabledSupplier.get()).booleanValue()) {
            return DataSources.immediateFailedDataSource(PREFETCH_EXCEPTION);
        }
        try {
            return submitPrefetchRequest(this.mProducerSequenceFactory.getDecodedImagePrefetchProducerSequence(imageRequest), imageRequest, RequestLevel.FULL_FETCH, obj);
        } catch (Throwable e) {
            return DataSources.immediateFailedDataSource(e);
        }
    }

    public DataSource<Void> prefetchToDiskCache(ImageRequest imageRequest, Object obj) {
        if (!((Boolean) this.mIsPrefetchEnabledSupplier.get()).booleanValue()) {
            return DataSources.immediateFailedDataSource(PREFETCH_EXCEPTION);
        }
        try {
            return submitPrefetchRequest(this.mProducerSequenceFactory.getEncodedImagePrefetchProducerSequence(imageRequest), imageRequest, RequestLevel.FULL_FETCH, obj);
        } catch (Throwable e) {
            return DataSources.immediateFailedDataSource(e);
        }
    }
}
