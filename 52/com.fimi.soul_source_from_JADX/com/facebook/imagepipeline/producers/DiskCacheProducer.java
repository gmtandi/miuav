package com.facebook.imagepipeline.producers;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequest.ImageType;
import com.facebook.imagepipeline.request.ImageRequest.RequestLevel;
import com.fimi.soul.view.photodraweeview.C2020f;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import p000a.C0001q;
import p000a.C0018s;

public class DiskCacheProducer implements Producer<EncodedImage> {
    @VisibleForTesting
    static final String PRODUCER_NAME = "DiskCacheProducer";
    @VisibleForTesting
    static final String VALUE_FOUND = "cached_value_found";
    private final CacheKeyFactory mCacheKeyFactory;
    private final BufferedDiskCache mDefaultBufferedDiskCache;
    private final Producer<EncodedImage> mInputProducer;
    private final BufferedDiskCache mSmallImageBufferedDiskCache;

    /* renamed from: com.facebook.imagepipeline.producers.DiskCacheProducer.1 */
    class C10451 implements C0001q<EncodedImage, Void> {
        final /* synthetic */ BufferedDiskCache val$cache;
        final /* synthetic */ CacheKey val$cacheKey;
        final /* synthetic */ Consumer val$consumer;
        final /* synthetic */ ProducerListener val$listener;
        final /* synthetic */ ProducerContext val$producerContext;
        final /* synthetic */ String val$requestId;

        C10451(ProducerListener producerListener, String str, Consumer consumer, BufferedDiskCache bufferedDiskCache, CacheKey cacheKey, ProducerContext producerContext) {
            this.val$listener = producerListener;
            this.val$requestId = str;
            this.val$consumer = consumer;
            this.val$cache = bufferedDiskCache;
            this.val$cacheKey = cacheKey;
            this.val$producerContext = producerContext;
        }

        public Void then(C0018s<EncodedImage> c0018s) {
            if (c0018s.m104c() || (c0018s.m107d() && (c0018s.m109f() instanceof CancellationException))) {
                this.val$listener.onProducerFinishWithCancellation(this.val$requestId, DiskCacheProducer.PRODUCER_NAME, null);
                this.val$consumer.onCancellation();
            } else if (c0018s.m107d()) {
                this.val$listener.onProducerFinishWithFailure(this.val$requestId, DiskCacheProducer.PRODUCER_NAME, c0018s.m109f(), null);
                DiskCacheProducer.this.maybeStartInputProducer(this.val$consumer, new DiskCacheConsumer(this.val$consumer, this.val$cache, this.val$cacheKey, null), this.val$producerContext);
            } else {
                EncodedImage encodedImage = (EncodedImage) c0018s.m108e();
                if (encodedImage != null) {
                    this.val$listener.onProducerFinishWithSuccess(this.val$requestId, DiskCacheProducer.PRODUCER_NAME, DiskCacheProducer.getExtraMap(this.val$listener, this.val$requestId, true));
                    this.val$consumer.onProgressUpdate(C2020f.f10933c);
                    this.val$consumer.onNewResult(encodedImage, true);
                    encodedImage.close();
                } else {
                    this.val$listener.onProducerFinishWithSuccess(this.val$requestId, DiskCacheProducer.PRODUCER_NAME, DiskCacheProducer.getExtraMap(this.val$listener, this.val$requestId, false));
                    DiskCacheProducer.this.maybeStartInputProducer(this.val$consumer, new DiskCacheConsumer(this.val$consumer, this.val$cache, this.val$cacheKey, null), this.val$producerContext);
                }
            }
            return null;
        }
    }

    /* renamed from: com.facebook.imagepipeline.producers.DiskCacheProducer.2 */
    class C10462 extends BaseProducerContextCallbacks {
        final /* synthetic */ AtomicBoolean val$isCancelled;

        C10462(AtomicBoolean atomicBoolean) {
            this.val$isCancelled = atomicBoolean;
        }

        public void onCancellationRequested() {
            this.val$isCancelled.set(true);
        }
    }

    class DiskCacheConsumer extends DelegatingConsumer<EncodedImage, EncodedImage> {
        private final BufferedDiskCache mCache;
        private final CacheKey mCacheKey;

        private DiskCacheConsumer(Consumer<EncodedImage> consumer, BufferedDiskCache bufferedDiskCache, CacheKey cacheKey) {
            super(consumer);
            this.mCache = bufferedDiskCache;
            this.mCacheKey = cacheKey;
        }

        public void onNewResultImpl(EncodedImage encodedImage, boolean z) {
            if (encodedImage != null && z) {
                this.mCache.put(this.mCacheKey, encodedImage);
            }
            getConsumer().onNewResult(encodedImage, z);
        }
    }

    public DiskCacheProducer(BufferedDiskCache bufferedDiskCache, BufferedDiskCache bufferedDiskCache2, CacheKeyFactory cacheKeyFactory, Producer<EncodedImage> producer) {
        this.mDefaultBufferedDiskCache = bufferedDiskCache;
        this.mSmallImageBufferedDiskCache = bufferedDiskCache2;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mInputProducer = producer;
    }

    @VisibleForTesting
    static Map<String, String> getExtraMap(ProducerListener producerListener, String str, boolean z) {
        return !producerListener.requiresExtraMap(str) ? null : ImmutableMap.of(VALUE_FOUND, String.valueOf(z));
    }

    private void maybeStartInputProducer(Consumer<EncodedImage> consumer, Consumer<EncodedImage> consumer2, ProducerContext producerContext) {
        if (producerContext.getLowestPermittedRequestLevel().getValue() >= RequestLevel.DISK_CACHE.getValue()) {
            consumer.onNewResult(null, true);
        } else {
            this.mInputProducer.produceResults(consumer2, producerContext);
        }
    }

    private void subscribeTaskForRequestCancellation(AtomicBoolean atomicBoolean, ProducerContext producerContext) {
        producerContext.addCallbacks(new C10462(atomicBoolean));
    }

    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        ImageRequest imageRequest = producerContext.getImageRequest();
        if (imageRequest.isDiskCacheEnabled()) {
            ProducerListener listener = producerContext.getListener();
            String id = producerContext.getId();
            listener.onProducerStart(id, PRODUCER_NAME);
            CacheKey encodedCacheKey = this.mCacheKeyFactory.getEncodedCacheKey(imageRequest);
            BufferedDiskCache bufferedDiskCache = imageRequest.getImageType() == ImageType.SMALL ? this.mSmallImageBufferedDiskCache : this.mDefaultBufferedDiskCache;
            C0001q c10451 = new C10451(listener, id, consumer, bufferedDiskCache, encodedCacheKey, producerContext);
            AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            bufferedDiskCache.get(encodedCacheKey, atomicBoolean).m95a(c10451);
            subscribeTaskForRequestCancellation(atomicBoolean, producerContext);
            return;
        }
        maybeStartInputProducer(consumer, consumer, producerContext);
    }
}
