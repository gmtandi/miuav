package com.facebook.imagepipeline.producers;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.imagepipeline.request.ImageRequest.RequestLevel;
import com.fimi.soul.view.photodraweeview.C2020f;
import java.util.Map;

public class EncodedMemoryCacheProducer implements Producer<EncodedImage> {
    @VisibleForTesting
    static final String PRODUCER_NAME = "EncodedMemoryCacheProducer";
    @VisibleForTesting
    static final String VALUE_FOUND = "cached_value_found";
    private final CacheKeyFactory mCacheKeyFactory;
    private final Producer<EncodedImage> mInputProducer;
    private final MemoryCache<CacheKey, PooledByteBuffer> mMemoryCache;

    /* renamed from: com.facebook.imagepipeline.producers.EncodedMemoryCacheProducer.1 */
    class C10471 extends DelegatingConsumer<EncodedImage, EncodedImage> {
        final /* synthetic */ CacheKey val$cacheKey;

        C10471(Consumer consumer, CacheKey cacheKey) {
            this.val$cacheKey = cacheKey;
            super(consumer);
        }

        public void onNewResultImpl(EncodedImage encodedImage, boolean z) {
            if (!z || encodedImage == null) {
                getConsumer().onNewResult(encodedImage, z);
                return;
            }
            EncodedImage byteBufferRef = encodedImage.getByteBufferRef();
            if (byteBufferRef != null) {
                try {
                    CloseableReference cache = EncodedMemoryCacheProducer.this.mMemoryCache.cache(this.val$cacheKey, byteBufferRef);
                    if (cache != null) {
                        try {
                            byteBufferRef = new EncodedImage(cache);
                            byteBufferRef.copyMetaDataFrom(encodedImage);
                            try {
                                getConsumer().onProgressUpdate(C2020f.f10933c);
                                getConsumer().onNewResult(byteBufferRef, true);
                                return;
                            } finally {
                                EncodedImage.closeSafely(byteBufferRef);
                            }
                        } finally {
                            CloseableReference.closeSafely(cache);
                        }
                    }
                } finally {
                    CloseableReference.closeSafely((CloseableReference) byteBufferRef);
                }
            }
            getConsumer().onNewResult(encodedImage, true);
        }
    }

    public EncodedMemoryCacheProducer(MemoryCache<CacheKey, PooledByteBuffer> memoryCache, CacheKeyFactory cacheKeyFactory, Producer<EncodedImage> producer) {
        this.mMemoryCache = memoryCache;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mInputProducer = producer;
    }

    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        Map map = null;
        String id = producerContext.getId();
        ProducerListener listener = producerContext.getListener();
        listener.onProducerStart(id, PRODUCER_NAME);
        CacheKey encodedCacheKey = this.mCacheKeyFactory.getEncodedCacheKey(producerContext.getImageRequest());
        CloseableReference closeableReference = this.mMemoryCache.get(encodedCacheKey);
        if (closeableReference != null) {
            EncodedImage encodedImage;
            try {
                encodedImage = new EncodedImage(closeableReference);
                String str = PRODUCER_NAME;
                if (listener.requiresExtraMap(id)) {
                    map = ImmutableMap.of(VALUE_FOUND, "true");
                }
                listener.onProducerFinishWithSuccess(id, str, map);
                consumer.onProgressUpdate(C2020f.f10933c);
                consumer.onNewResult(encodedImage, true);
                EncodedImage.closeSafely(encodedImage);
                CloseableReference.closeSafely(closeableReference);
            } catch (Throwable th) {
                CloseableReference.closeSafely(closeableReference);
            }
        } else if (producerContext.getLowestPermittedRequestLevel().getValue() >= RequestLevel.ENCODED_MEMORY_CACHE.getValue()) {
            r3 = PRODUCER_NAME;
            if (listener.requiresExtraMap(id)) {
                map = ImmutableMap.of(VALUE_FOUND, "false");
            }
            listener.onProducerFinishWithSuccess(id, r3, map);
            consumer.onNewResult(null, true);
            CloseableReference.closeSafely(closeableReference);
        } else {
            Consumer c10471 = new C10471(consumer, encodedCacheKey);
            r3 = PRODUCER_NAME;
            if (listener.requiresExtraMap(id)) {
                map = ImmutableMap.of(VALUE_FOUND, "false");
            }
            listener.onProducerFinishWithSuccess(id, r3, map);
            this.mInputProducer.produceResults(c10471, producerContext);
            CloseableReference.closeSafely(closeableReference);
        }
    }
}
