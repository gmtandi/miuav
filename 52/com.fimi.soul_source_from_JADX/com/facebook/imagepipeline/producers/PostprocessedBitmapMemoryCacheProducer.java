package com.facebook.imagepipeline.producers;

import com.android.internal.util.Predicate;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.cache.BitmapMemoryCacheKey;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.Postprocessor;
import com.facebook.imagepipeline.request.RepeatedPostprocessor;
import com.fimi.soul.view.photodraweeview.C2020f;
import java.util.Map;

public class PostprocessedBitmapMemoryCacheProducer implements Producer<CloseableReference<CloseableImage>> {
    @VisibleForTesting
    static final String PRODUCER_NAME = "PostprocessedBitmapMemoryCacheProducer";
    @VisibleForTesting
    static final String VALUE_FOUND = "cached_value_found";
    private final CacheKeyFactory mCacheKeyFactory;
    private final Producer<CloseableReference<CloseableImage>> mInputProducer;
    private final MemoryCache<CacheKey, CloseableImage> mMemoryCache;

    public class CachedPostprocessorConsumer extends DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>> {
        private final CacheKey mCacheKey;
        private final boolean mIsRepeatedProcessor;
        private final MemoryCache<CacheKey, CloseableImage> mMemoryCache;
        private final String mProcessorName;

        /* renamed from: com.facebook.imagepipeline.producers.PostprocessedBitmapMemoryCacheProducer.CachedPostprocessorConsumer.1 */
        class C10631 implements Predicate<CacheKey> {
            C10631() {
            }

            public boolean apply(CacheKey cacheKey) {
                return cacheKey instanceof BitmapMemoryCacheKey ? CachedPostprocessorConsumer.this.mProcessorName.equals(((BitmapMemoryCacheKey) cacheKey).getPostprocessorName()) : false;
            }
        }

        public CachedPostprocessorConsumer(Consumer<CloseableReference<CloseableImage>> consumer, CacheKey cacheKey, boolean z, String str, MemoryCache<CacheKey, CloseableImage> memoryCache) {
            super(consumer);
            this.mCacheKey = cacheKey;
            this.mIsRepeatedProcessor = z;
            this.mProcessorName = str;
            this.mMemoryCache = memoryCache;
        }

        protected void onNewResultImpl(CloseableReference<CloseableImage> closeableReference, boolean z) {
            if (!z && !this.mIsRepeatedProcessor) {
                return;
            }
            if (closeableReference == null) {
                getConsumer().onNewResult(null, z);
                return;
            }
            if (this.mCacheKey != null) {
                this.mMemoryCache.removeAll(new C10631());
                CloseableReference cache = this.mMemoryCache.cache(this.mCacheKey, closeableReference);
            } else {
                CloseableReference<CloseableImage> closeableReference2 = closeableReference;
            }
            try {
                Object obj;
                getConsumer().onProgressUpdate(C2020f.f10933c);
                Consumer consumer = getConsumer();
                if (cache != null) {
                    obj = cache;
                }
                consumer.onNewResult(obj, z);
            } finally {
                CloseableReference.closeSafely(cache);
            }
        }
    }

    public PostprocessedBitmapMemoryCacheProducer(MemoryCache<CacheKey, CloseableImage> memoryCache, CacheKeyFactory cacheKeyFactory, Producer<CloseableReference<CloseableImage>> producer) {
        this.mMemoryCache = memoryCache;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mInputProducer = producer;
    }

    protected String getProducerName() {
        return PRODUCER_NAME;
    }

    public void produceResults(Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext) {
        Map map = null;
        ProducerListener listener = producerContext.getListener();
        String id = producerContext.getId();
        ImageRequest imageRequest = producerContext.getImageRequest();
        Postprocessor postprocessor = imageRequest.getPostprocessor();
        if (postprocessor == null) {
            this.mInputProducer.produceResults(consumer, producerContext);
            return;
        }
        CacheKey postprocessedBitmapCacheKey;
        CloseableReference closeableReference;
        listener.onProducerStart(id, getProducerName());
        if (postprocessor.getPostprocessorCacheKey() != null) {
            postprocessedBitmapCacheKey = this.mCacheKeyFactory.getPostprocessedBitmapCacheKey(imageRequest);
            closeableReference = this.mMemoryCache.get(postprocessedBitmapCacheKey);
        } else {
            closeableReference = null;
            postprocessedBitmapCacheKey = null;
        }
        if (closeableReference != null) {
            listener.onProducerFinishWithSuccess(id, getProducerName(), listener.requiresExtraMap(id) ? ImmutableMap.of(VALUE_FOUND, "true") : null);
            consumer.onProgressUpdate(C2020f.f10933c);
            consumer.onNewResult(closeableReference, true);
            closeableReference.close();
            return;
        }
        Consumer cachedPostprocessorConsumer = new CachedPostprocessorConsumer(consumer, postprocessedBitmapCacheKey, postprocessor instanceof RepeatedPostprocessor, postprocessor.getClass().getName(), this.mMemoryCache);
        String producerName = getProducerName();
        if (listener.requiresExtraMap(id)) {
            map = ImmutableMap.of(VALUE_FOUND, "false");
        }
        listener.onProducerFinishWithSuccess(id, producerName, map);
        this.mInputProducer.produceResults(cachedPostprocessorConsumer, producerContext);
    }
}
