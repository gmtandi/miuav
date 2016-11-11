package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.Closeables;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.TriState;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.nativecode.JpegTranscoder;
import com.facebook.imagepipeline.producers.JobScheduler.JobRunnable;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imageutils.BitmapUtil;
import com.fimi.soul.view.photodraweeview.C2020f;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.p122a.p123a.C2915a;

public class ResizeAndRotateProducer implements Producer<EncodedImage> {
    @VisibleForTesting
    static final int DEFAULT_JPEG_QUALITY = 85;
    private static final String FRACTION_KEY = "Fraction";
    @VisibleForTesting
    static final int MAX_JPEG_SCALE_NUMERATOR = 8;
    @VisibleForTesting
    static final int MIN_TRANSFORM_INTERVAL_MS = 100;
    private static final String ORIGINAL_SIZE_KEY = "Original size";
    private static final String PRODUCER_NAME = "ResizeAndRotateProducer";
    private static final String REQUESTED_SIZE_KEY = "Requested size";
    private static final float ROUNDUP_FRACTION = 0.6666667f;
    private final Executor mExecutor;
    private final Producer<EncodedImage> mInputProducer;
    private final PooledByteBufferFactory mPooledByteBufferFactory;

    class TransformingConsumer extends DelegatingConsumer<EncodedImage, EncodedImage> {
        private boolean mIsCancelled;
        private final JobScheduler mJobScheduler;
        private final ProducerContext mProducerContext;

        /* renamed from: com.facebook.imagepipeline.producers.ResizeAndRotateProducer.TransformingConsumer.1 */
        class C10691 implements JobRunnable {
            final /* synthetic */ ResizeAndRotateProducer val$this$0;

            C10691(ResizeAndRotateProducer resizeAndRotateProducer) {
                this.val$this$0 = resizeAndRotateProducer;
            }

            public void run(EncodedImage encodedImage, boolean z) {
                TransformingConsumer.this.doTransform(encodedImage, z);
            }
        }

        /* renamed from: com.facebook.imagepipeline.producers.ResizeAndRotateProducer.TransformingConsumer.2 */
        class C10702 extends BaseProducerContextCallbacks {
            final /* synthetic */ Consumer val$consumer;
            final /* synthetic */ ResizeAndRotateProducer val$this$0;

            C10702(ResizeAndRotateProducer resizeAndRotateProducer, Consumer consumer) {
                this.val$this$0 = resizeAndRotateProducer;
                this.val$consumer = consumer;
            }

            public void onCancellationRequested() {
                TransformingConsumer.this.mJobScheduler.clearJob();
                TransformingConsumer.this.mIsCancelled = true;
                this.val$consumer.onCancellation();
            }

            public void onIsIntermediateResultExpectedChanged() {
                if (TransformingConsumer.this.mProducerContext.isIntermediateResultExpected()) {
                    TransformingConsumer.this.mJobScheduler.scheduleJob();
                }
            }
        }

        public TransformingConsumer(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
            super(consumer);
            this.mIsCancelled = false;
            this.mProducerContext = producerContext;
            this.mJobScheduler = new JobScheduler(ResizeAndRotateProducer.this.mExecutor, new C10691(ResizeAndRotateProducer.this), ResizeAndRotateProducer.MIN_TRANSFORM_INTERVAL_MS);
            this.mProducerContext.addCallbacks(new C10702(ResizeAndRotateProducer.this, consumer));
        }

        private void doTransform(EncodedImage encodedImage, boolean z) {
            Map extraMap;
            EncodedImage encodedImage2;
            Throwable e;
            InputStream inputStream = null;
            this.mProducerContext.getListener().onProducerStart(this.mProducerContext.getId(), ResizeAndRotateProducer.PRODUCER_NAME);
            ImageRequest imageRequest = this.mProducerContext.getImageRequest();
            OutputStream newOutputStream = ResizeAndRotateProducer.this.mPooledByteBufferFactory.newOutputStream();
            try {
                int access$700 = ResizeAndRotateProducer.getScaleNumerator(imageRequest, encodedImage);
                extraMap = getExtraMap(encodedImage, imageRequest, access$700);
                try {
                    inputStream = encodedImage.getInputStream();
                    JpegTranscoder.transcodeJpeg(inputStream, newOutputStream, ResizeAndRotateProducer.getRotationAngle(imageRequest, encodedImage), access$700, ResizeAndRotateProducer.DEFAULT_JPEG_QUALITY);
                    CloseableReference of = CloseableReference.of(newOutputStream.toByteBuffer());
                    try {
                        encodedImage2 = new EncodedImage(of);
                        encodedImage2.setImageFormat(ImageFormat.JPEG);
                        encodedImage2.parseMetaData();
                        this.mProducerContext.getListener().onProducerFinishWithSuccess(this.mProducerContext.getId(), ResizeAndRotateProducer.PRODUCER_NAME, extraMap);
                        getConsumer().onNewResult(encodedImage2, z);
                        EncodedImage.closeSafely(encodedImage2);
                        CloseableReference.closeSafely(of);
                        Closeables.closeQuietly(inputStream);
                        newOutputStream.close();
                    } catch (Throwable th) {
                        CloseableReference.closeSafely(of);
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        this.mProducerContext.getListener().onProducerFinishWithFailure(this.mProducerContext.getId(), ResizeAndRotateProducer.PRODUCER_NAME, e, extraMap);
                        getConsumer().onFailure(e);
                    } finally {
                        Closeables.closeQuietly(inputStream);
                        newOutputStream.close();
                    }
                }
            } catch (Exception e3) {
                e = e3;
                extraMap = null;
                this.mProducerContext.getListener().onProducerFinishWithFailure(this.mProducerContext.getId(), ResizeAndRotateProducer.PRODUCER_NAME, e, extraMap);
                getConsumer().onFailure(e);
            }
        }

        private Map<String, String> getExtraMap(EncodedImage encodedImage, ImageRequest imageRequest, int i) {
            if (!this.mProducerContext.getListener().requiresExtraMap(this.mProducerContext.getId())) {
                return null;
            }
            return ImmutableMap.of(ResizeAndRotateProducer.ORIGINAL_SIZE_KEY, encodedImage.getWidth() + "x" + encodedImage.getHeight(), ResizeAndRotateProducer.REQUESTED_SIZE_KEY, imageRequest.getResizeOptions() != null ? imageRequest.getResizeOptions().width + "x" + imageRequest.getResizeOptions().height : "Unspecified", ResizeAndRotateProducer.FRACTION_KEY, i > 0 ? i + "/8" : C2915a.f14760f, "queueTime", String.valueOf(this.mJobScheduler.getQueuedTime()));
        }

        protected void onNewResultImpl(@Nullable EncodedImage encodedImage, boolean z) {
            if (!this.mIsCancelled) {
                if (encodedImage != null) {
                    TriState access$500 = ResizeAndRotateProducer.shouldTransform(this.mProducerContext.getImageRequest(), encodedImage);
                    if (!z && access$500 == TriState.UNSET) {
                        return;
                    }
                    if (access$500 != TriState.YES) {
                        getConsumer().onNewResult(encodedImage, z);
                    } else if (!this.mJobScheduler.updateJob(encodedImage, z)) {
                    } else {
                        if (z || this.mProducerContext.isIntermediateResultExpected()) {
                            this.mJobScheduler.scheduleJob();
                        }
                    }
                } else if (z) {
                    getConsumer().onNewResult(null, true);
                }
            }
        }
    }

    public ResizeAndRotateProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, Producer<EncodedImage> producer) {
        this.mExecutor = (Executor) Preconditions.checkNotNull(executor);
        this.mPooledByteBufferFactory = (PooledByteBufferFactory) Preconditions.checkNotNull(pooledByteBufferFactory);
        this.mInputProducer = (Producer) Preconditions.checkNotNull(producer);
    }

    @VisibleForTesting
    static float determineResizeRatio(ResizeOptions resizeOptions, int i, int i2) {
        if (resizeOptions == null) {
            return C2020f.f10933c;
        }
        float max = Math.max(((float) resizeOptions.width) / ((float) i), ((float) resizeOptions.height) / ((float) i2));
        if (((float) i) * max > BitmapUtil.MAX_BITMAP_SIZE) {
            max = BitmapUtil.MAX_BITMAP_SIZE / ((float) i);
        }
        return ((float) i2) * max > BitmapUtil.MAX_BITMAP_SIZE ? BitmapUtil.MAX_BITMAP_SIZE / ((float) i2) : max;
    }

    private static int getRotationAngle(ImageRequest imageRequest, EncodedImage encodedImage) {
        boolean z = false;
        if (!imageRequest.getAutoRotateEnabled()) {
            return 0;
        }
        int rotationAngle = encodedImage.getRotationAngle();
        if (rotationAngle == 0 || rotationAngle == 90 || rotationAngle == Opcodes.GETFIELD || rotationAngle == 270) {
            z = true;
        }
        Preconditions.checkArgument(z);
        return rotationAngle;
    }

    private static int getScaleNumerator(ImageRequest imageRequest, EncodedImage encodedImage) {
        ResizeOptions resizeOptions = imageRequest.getResizeOptions();
        if (resizeOptions == null) {
            return MAX_JPEG_SCALE_NUMERATOR;
        }
        int rotationAngle = getRotationAngle(imageRequest, encodedImage);
        Object obj = (rotationAngle == 90 || rotationAngle == 270) ? 1 : null;
        rotationAngle = roundNumerator(determineResizeRatio(resizeOptions, obj != null ? encodedImage.getHeight() : encodedImage.getWidth(), obj != null ? encodedImage.getWidth() : encodedImage.getHeight()));
        return rotationAngle > MAX_JPEG_SCALE_NUMERATOR ? MAX_JPEG_SCALE_NUMERATOR : rotationAngle < 1 ? 1 : rotationAngle;
    }

    @VisibleForTesting
    static int roundNumerator(float f) {
        return (int) (ROUNDUP_FRACTION + (8.0f * f));
    }

    private static boolean shouldResize(int i) {
        return i < MAX_JPEG_SCALE_NUMERATOR;
    }

    private static TriState shouldTransform(ImageRequest imageRequest, EncodedImage encodedImage) {
        if (encodedImage == null || encodedImage.getImageFormat() == ImageFormat.UNKNOWN) {
            return TriState.UNSET;
        }
        if (encodedImage.getImageFormat() != ImageFormat.JPEG) {
            return TriState.NO;
        }
        boolean z = getRotationAngle(imageRequest, encodedImage) != 0 || shouldResize(getScaleNumerator(imageRequest, encodedImage));
        return TriState.valueOf(z);
    }

    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        this.mInputProducer.produceResults(new TransformingConsumer(consumer, producerContext), producerContext);
    }
}
