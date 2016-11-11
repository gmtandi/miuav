package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;

public class BranchOnSeparateImagesProducer implements Producer<EncodedImage> {
    private final Producer<EncodedImage> mInputProducer1;
    private final Producer<EncodedImage> mInputProducer2;

    class OnFirstImageConsumer extends DelegatingConsumer<EncodedImage, EncodedImage> {
        private ProducerContext mProducerContext;

        private OnFirstImageConsumer(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
            super(consumer);
            this.mProducerContext = producerContext;
        }

        private boolean isResultGoodEnough(EncodedImage encodedImage, ImageRequest imageRequest) {
            return encodedImage != null && encodedImage.getWidth() >= imageRequest.getPreferredWidth() && encodedImage.getHeight() >= imageRequest.getPreferredHeight();
        }

        protected void onFailureImpl(Throwable th) {
            BranchOnSeparateImagesProducer.this.mInputProducer2.produceResults(getConsumer(), this.mProducerContext);
        }

        protected void onNewResultImpl(EncodedImage encodedImage, boolean z) {
            ImageRequest imageRequest = this.mProducerContext.getImageRequest();
            boolean isResultGoodEnough = isResultGoodEnough(encodedImage, imageRequest);
            if (encodedImage != null && (isResultGoodEnough || imageRequest.getLocalThumbnailPreviewsEnabled())) {
                Consumer consumer = getConsumer();
                boolean z2 = z && isResultGoodEnough;
                consumer.onNewResult(encodedImage, z2);
            }
            if (z && !isResultGoodEnough) {
                BranchOnSeparateImagesProducer.this.mInputProducer2.produceResults(getConsumer(), this.mProducerContext);
            }
        }
    }

    public BranchOnSeparateImagesProducer(Producer<EncodedImage> producer, Producer<EncodedImage> producer2) {
        this.mInputProducer1 = producer;
        this.mInputProducer2 = producer2;
    }

    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        this.mInputProducer1.produceResults(new OnFirstImageConsumer(consumer, producerContext, null), producerContext);
    }
}
