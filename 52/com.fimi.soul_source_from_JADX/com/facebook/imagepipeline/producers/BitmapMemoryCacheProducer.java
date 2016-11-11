package com.facebook.imagepipeline.producers;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.request.ImageRequest.RequestLevel;
import com.fimi.soul.view.photodraweeview.C2020f;
import java.util.Map;

public class BitmapMemoryCacheProducer implements Producer<CloseableReference<CloseableImage>> {
    @VisibleForTesting
    static final String PRODUCER_NAME = "BitmapMemoryCacheProducer";
    @VisibleForTesting
    static final String VALUE_FOUND = "cached_value_found";
    private final CacheKeyFactory mCacheKeyFactory;
    private final Producer<CloseableReference<CloseableImage>> mInputProducer;
    private final MemoryCache<CacheKey, CloseableImage> mMemoryCache;

    /* renamed from: com.facebook.imagepipeline.producers.BitmapMemoryCacheProducer.1 */
    class C10411 extends DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>> {
        final /* synthetic */ CacheKey val$cacheKey;

        C10411(Consumer consumer, CacheKey cacheKey) {
            this.val$cacheKey = cacheKey;
            super(consumer);
        }

        public void onNewResultImpl(CloseableReference<CloseableImage> closeableReference, boolean z) {
            CloseableReference closeableReference2;
            if (closeableReference == null) {
                if (z) {
                    getConsumer().onNewResult(null, true);
                }
            } else if (((CloseableImage) closeableReference.get()).isStateful()) {
                getConsumer().onNewResult(closeableReference, z);
            } else {
                Object obj;
                if (!z) {
                    closeableReference2 = BitmapMemoryCacheProducer.this.mMemoryCache.get(this.val$cacheKey);
                    if (closeableReference2 != null) {
                        try {
                            QualityInfo qualityInfo = ((CloseableImage) closeableReference.get()).getQualityInfo();
                            QualityInfo qualityInfo2 = ((CloseableImage) closeableReference2.get()).getQualityInfo();
                            if (qualityInfo2.isOfFullQuality() || qualityInfo2.getQuality() >= qualityInfo.getQuality()) {
                                getConsumer().onNewResult(closeableReference2, false);
                                return;
                            }
                            CloseableReference.closeSafely(closeableReference2);
                        } finally {
                            CloseableReference.closeSafely(closeableReference2);
                        }
                    }
                }
                closeableReference2 = BitmapMemoryCacheProducer.this.mMemoryCache.cache(this.val$cacheKey, closeableReference);
                if (z) {
                    try {
                        getConsumer().onProgressUpdate(C2020f.f10933c);
                    } catch (Throwable th) {
                        CloseableReference.closeSafely(closeableReference2);
                    }
                }
                Consumer consumer = getConsumer();
                if (closeableReference2 != null) {
                    obj = closeableReference2;
                }
                consumer.onNewResult(obj, z);
                CloseableReference.closeSafely(closeableReference2);
            }
        }
    }

    public BitmapMemoryCacheProducer(MemoryCache<CacheKey, CloseableImage> memoryCache, CacheKeyFactory cacheKeyFactory, Producer<CloseableReference<CloseableImage>> producer) {
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
        listener.onProducerStart(id, getProducerName());
        CacheKey bitmapCacheKey = this.mCacheKeyFactory.getBitmapCacheKey(producerContext.getImageRequest());
        CloseableReference closeableReference = this.mMemoryCache.get(bitmapCacheKey);
        if (closeableReference != null) {
            boolean isOfFullQuality = ((CloseableImage) closeableReference.get()).getQualityInfo().isOfFullQuality();
            if (isOfFullQuality) {
                listener.onProducerFinishWithSuccess(id, getProducerName(), listener.requiresExtraMap(id) ? ImmutableMap.of(VALUE_FOUND, "true") : null);
                consumer.onProgressUpdate(C2020f.f10933c);
            }
            consumer.onNewResult(closeableReference, isOfFullQuality);
            closeableReference.close();
            if (isOfFullQuality) {
                return;
            }
        }
        if (producerContext.getLowestPermittedRequestLevel().getValue() >= RequestLevel.BITMAP_MEMORY_CACHE.getValue()) {
            listener.onProducerFinishWithSuccess(id, getProducerName(), listener.requiresExtraMap(id) ? ImmutableMap.of(VALUE_FOUND, "false") : null);
            consumer.onNewResult(null, true);
            return;
        }
        Consumer wrapConsumer = wrapConsumer(consumer, bitmapCacheKey);
        String producerName = getProducerName();
        if (listener.requiresExtraMap(id)) {
            map = ImmutableMap.of(VALUE_FOUND, "false");
        }
        listener.onProducerFinishWithSuccess(id, producerName, map);
        this.mInputProducer.produceResults(wrapConsumer, producerContext);
    }

    protected Consumer<CloseableReference<CloseableImage>> wrapConsumer(Consumer<CloseableReference<CloseableImage>> consumer, CacheKey cacheKey) {
        return new C10411(consumer, cacheKey);
    }
}
