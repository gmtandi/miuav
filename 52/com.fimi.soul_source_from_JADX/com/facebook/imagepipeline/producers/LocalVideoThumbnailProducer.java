package com.facebook.imagepipeline.producers;

import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.bitmaps.SimpleBitmapReleaser;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.Map;
import java.util.concurrent.Executor;

public class LocalVideoThumbnailProducer implements Producer<CloseableReference<CloseableImage>> {
    @VisibleForTesting
    static final String CREATED_THUMBNAIL = "createdThumbnail";
    @VisibleForTesting
    static final String PRODUCER_NAME = "VideoThumbnailProducer";
    private final Executor mExecutor;

    /* renamed from: com.facebook.imagepipeline.producers.LocalVideoThumbnailProducer.1 */
    class C10581 extends StatefulProducerRunnable<CloseableReference<CloseableImage>> {
        final /* synthetic */ ImageRequest val$imageRequest;

        C10581(Consumer consumer, ProducerListener producerListener, String str, String str2, ImageRequest imageRequest) {
            this.val$imageRequest = imageRequest;
            super(consumer, producerListener, str, str2);
        }

        protected void disposeResult(CloseableReference<CloseableImage> closeableReference) {
            CloseableReference.closeSafely((CloseableReference) closeableReference);
        }

        protected Map<String, String> getExtraMapOnSuccess(CloseableReference<CloseableImage> closeableReference) {
            return ImmutableMap.of(LocalVideoThumbnailProducer.CREATED_THUMBNAIL, String.valueOf(closeableReference != null));
        }

        protected CloseableReference<CloseableImage> getResult() {
            Bitmap createVideoThumbnail = ThumbnailUtils.createVideoThumbnail(this.val$imageRequest.getSourceFile().getPath(), LocalVideoThumbnailProducer.calculateKind(this.val$imageRequest));
            return createVideoThumbnail == null ? null : CloseableReference.of(new CloseableStaticBitmap(createVideoThumbnail, SimpleBitmapReleaser.getInstance(), ImmutableQualityInfo.FULL_QUALITY, 0));
        }
    }

    /* renamed from: com.facebook.imagepipeline.producers.LocalVideoThumbnailProducer.2 */
    class C10592 extends BaseProducerContextCallbacks {
        final /* synthetic */ StatefulProducerRunnable val$cancellableProducerRunnable;

        C10592(StatefulProducerRunnable statefulProducerRunnable) {
            this.val$cancellableProducerRunnable = statefulProducerRunnable;
        }

        public void onCancellationRequested() {
            this.val$cancellableProducerRunnable.cancel();
        }
    }

    public LocalVideoThumbnailProducer(Executor executor) {
        this.mExecutor = executor;
    }

    private static int calculateKind(ImageRequest imageRequest) {
        return (imageRequest.getPreferredWidth() > 96 || imageRequest.getPreferredHeight() > 96) ? 1 : 3;
    }

    public void produceResults(Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext) {
        Consumer<CloseableReference<CloseableImage>> consumer2 = consumer;
        Runnable c10581 = new C10581(consumer2, producerContext.getListener(), PRODUCER_NAME, producerContext.getId(), producerContext.getImageRequest());
        producerContext.addCallbacks(new C10592(c10581));
        this.mExecutor.execute(c10581);
    }
}
