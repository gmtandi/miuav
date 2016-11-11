package com.facebook.imagepipeline.producers;

import android.graphics.Bitmap;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegParser;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.memory.ByteArrayPool;
import com.facebook.imagepipeline.producers.JobScheduler.JobRunnable;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public class DecodeProducer implements Producer<CloseableReference<CloseableImage>> {
    private static final String BITMAP_SIZE_KEY = "bitmapSize";
    private static final String HAS_GOOD_QUALITY_KEY = "hasGoodQuality";
    private static final String IS_FINAL_KEY = "isFinal";
    public static final String PRODUCER_NAME = "DecodeProducer";
    private final ByteArrayPool mByteArrayPool;
    private final boolean mDownsampleEnabled;
    private final boolean mDownsampleEnabledForNetwork;
    private final Executor mExecutor;
    private final ImageDecoder mImageDecoder;
    private final Producer<EncodedImage> mInputProducer;
    private final ProgressiveJpegConfig mProgressiveJpegConfig;

    abstract class ProgressiveDecoder extends DelegatingConsumer<EncodedImage, CloseableReference<CloseableImage>> {
        private final ImageDecodeOptions mImageDecodeOptions;
        @GuardedBy("this")
        private boolean mIsFinished;
        private final JobScheduler mJobScheduler;
        private final ProducerContext mProducerContext;
        private final ProducerListener mProducerListener;

        /* renamed from: com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder.1 */
        class C10431 implements JobRunnable {
            final /* synthetic */ ProducerContext val$producerContext;
            final /* synthetic */ DecodeProducer val$this$0;

            C10431(DecodeProducer decodeProducer, ProducerContext producerContext) {
                this.val$this$0 = decodeProducer;
                this.val$producerContext = producerContext;
            }

            public void run(EncodedImage encodedImage, boolean z) {
                if (encodedImage != null) {
                    if (DecodeProducer.this.mDownsampleEnabled) {
                        ImageRequest imageRequest = this.val$producerContext.getImageRequest();
                        if (DecodeProducer.this.mDownsampleEnabledForNetwork || !UriUtil.isNetworkUri(imageRequest.getSourceUri())) {
                            encodedImage.setSampleSize(DownsampleUtil.determineSampleSize(imageRequest, encodedImage));
                        }
                    }
                    ProgressiveDecoder.this.doDecode(encodedImage, z);
                }
            }
        }

        /* renamed from: com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder.2 */
        class C10442 extends BaseProducerContextCallbacks {
            final /* synthetic */ DecodeProducer val$this$0;

            C10442(DecodeProducer decodeProducer) {
                this.val$this$0 = decodeProducer;
            }

            public void onIsIntermediateResultExpectedChanged() {
                if (ProgressiveDecoder.this.mProducerContext.isIntermediateResultExpected()) {
                    ProgressiveDecoder.this.mJobScheduler.scheduleJob();
                }
            }
        }

        public ProgressiveDecoder(Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext) {
            super(consumer);
            this.mProducerContext = producerContext;
            this.mProducerListener = producerContext.getListener();
            this.mImageDecodeOptions = producerContext.getImageRequest().getImageDecodeOptions();
            this.mIsFinished = false;
            this.mJobScheduler = new JobScheduler(DecodeProducer.this.mExecutor, new C10431(DecodeProducer.this, producerContext), this.mImageDecodeOptions.minDecodeIntervalMs);
            this.mProducerContext.addCallbacks(new C10442(DecodeProducer.this));
        }

        private void doDecode(EncodedImage encodedImage, boolean z) {
            if (!isFinished() && EncodedImage.isValid(encodedImage)) {
                long queuedTime;
                QualityInfo qualityInfo;
                CloseableImage closeableImage;
                try {
                    queuedTime = this.mJobScheduler.getQueuedTime();
                    int size = z ? encodedImage.getSize() : getIntermediateImageEndOffset(encodedImage);
                    qualityInfo = z ? ImmutableQualityInfo.FULL_QUALITY : getQualityInfo();
                    this.mProducerListener.onProducerStart(this.mProducerContext.getId(), DecodeProducer.PRODUCER_NAME);
                    closeableImage = null;
                    closeableImage = DecodeProducer.this.mImageDecoder.decodeImage(encodedImage, size, qualityInfo, this.mImageDecodeOptions);
                    this.mProducerListener.onProducerFinishWithSuccess(this.mProducerContext.getId(), DecodeProducer.PRODUCER_NAME, getExtraMap(closeableImage, queuedTime, qualityInfo, z));
                    handleResult(closeableImage, z);
                } catch (Throwable e) {
                    Throwable th = e;
                    this.mProducerListener.onProducerFinishWithFailure(this.mProducerContext.getId(), DecodeProducer.PRODUCER_NAME, th, getExtraMap(closeableImage, queuedTime, qualityInfo, z));
                    handleError(th);
                } finally {
                    EncodedImage.closeSafely(encodedImage);
                }
            }
        }

        private Map<String, String> getExtraMap(@Nullable CloseableImage closeableImage, long j, QualityInfo qualityInfo, boolean z) {
            if (!this.mProducerListener.requiresExtraMap(this.mProducerContext.getId())) {
                return null;
            }
            String valueOf = String.valueOf(j);
            String valueOf2 = String.valueOf(qualityInfo.isOfGoodEnoughQuality());
            String valueOf3 = String.valueOf(z);
            if (!(closeableImage instanceof CloseableStaticBitmap)) {
                return ImmutableMap.of("queueTime", valueOf, DecodeProducer.HAS_GOOD_QUALITY_KEY, valueOf2, DecodeProducer.IS_FINAL_KEY, valueOf3);
            }
            Bitmap underlyingBitmap = ((CloseableStaticBitmap) closeableImage).getUnderlyingBitmap();
            return ImmutableMap.of(DecodeProducer.BITMAP_SIZE_KEY, underlyingBitmap.getWidth() + "x" + underlyingBitmap.getHeight(), "queueTime", valueOf, DecodeProducer.HAS_GOOD_QUALITY_KEY, valueOf2, DecodeProducer.IS_FINAL_KEY, valueOf3);
        }

        private void handleCancellation() {
            maybeFinish(true);
            getConsumer().onCancellation();
        }

        private void handleError(Throwable th) {
            maybeFinish(true);
            getConsumer().onFailure(th);
        }

        private void handleResult(CloseableImage closeableImage, boolean z) {
            CloseableReference of = CloseableReference.of(closeableImage);
            try {
                maybeFinish(z);
                getConsumer().onNewResult(of, z);
            } finally {
                CloseableReference.closeSafely(of);
            }
        }

        private synchronized boolean isFinished() {
            return this.mIsFinished;
        }

        private void maybeFinish(boolean z) {
            synchronized (this) {
                if (z) {
                    if (!this.mIsFinished) {
                        this.mIsFinished = true;
                        this.mJobScheduler.clearJob();
                        return;
                    }
                }
            }
        }

        protected abstract int getIntermediateImageEndOffset(EncodedImage encodedImage);

        protected abstract QualityInfo getQualityInfo();

        public void onCancellationImpl() {
            handleCancellation();
        }

        public void onFailureImpl(Throwable th) {
            handleError(th);
        }

        public void onNewResultImpl(EncodedImage encodedImage, boolean z) {
            if (z && !EncodedImage.isValid(encodedImage)) {
                handleError(new NullPointerException("Encoded image is not valid."));
            } else if (!updateDecodeJob(encodedImage, z)) {
            } else {
                if (z || this.mProducerContext.isIntermediateResultExpected()) {
                    this.mJobScheduler.scheduleJob();
                }
            }
        }

        protected boolean updateDecodeJob(EncodedImage encodedImage, boolean z) {
            return this.mJobScheduler.updateJob(encodedImage, z);
        }
    }

    class LocalImagesProgressiveDecoder extends ProgressiveDecoder {
        public LocalImagesProgressiveDecoder(Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext) {
            super(consumer, producerContext);
        }

        protected int getIntermediateImageEndOffset(EncodedImage encodedImage) {
            return encodedImage.getSize();
        }

        protected QualityInfo getQualityInfo() {
            return ImmutableQualityInfo.of(0, false, false);
        }

        protected synchronized boolean updateDecodeJob(EncodedImage encodedImage, boolean z) {
            return !z ? false : super.updateDecodeJob(encodedImage, z);
        }
    }

    class NetworkImagesProgressiveDecoder extends ProgressiveDecoder {
        private int mLastScheduledScanNumber;
        private final ProgressiveJpegConfig mProgressiveJpegConfig;
        private final ProgressiveJpegParser mProgressiveJpegParser;

        public NetworkImagesProgressiveDecoder(Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext, ProgressiveJpegParser progressiveJpegParser, ProgressiveJpegConfig progressiveJpegConfig) {
            super(consumer, producerContext);
            this.mProgressiveJpegParser = (ProgressiveJpegParser) Preconditions.checkNotNull(progressiveJpegParser);
            this.mProgressiveJpegConfig = (ProgressiveJpegConfig) Preconditions.checkNotNull(progressiveJpegConfig);
            this.mLastScheduledScanNumber = 0;
        }

        protected int getIntermediateImageEndOffset(EncodedImage encodedImage) {
            return this.mProgressiveJpegParser.getBestScanEndOffset();
        }

        protected QualityInfo getQualityInfo() {
            return this.mProgressiveJpegConfig.getQualityInfo(this.mProgressiveJpegParser.getBestScanNumber());
        }

        protected synchronized boolean updateDecodeJob(EncodedImage encodedImage, boolean z) {
            boolean z2 = false;
            synchronized (this) {
                boolean updateDecodeJob = super.updateDecodeJob(encodedImage, z);
                if (!z && EncodedImage.isValid(encodedImage)) {
                    if (this.mProgressiveJpegParser.parseMoreData(encodedImage)) {
                        int bestScanNumber = this.mProgressiveJpegParser.getBestScanNumber();
                        if (bestScanNumber > this.mLastScheduledScanNumber && bestScanNumber >= this.mProgressiveJpegConfig.getNextScanNumberToDecode(this.mLastScheduledScanNumber)) {
                            this.mLastScheduledScanNumber = bestScanNumber;
                        }
                    }
                }
                z2 = updateDecodeJob;
            }
            return z2;
        }
    }

    public DecodeProducer(ByteArrayPool byteArrayPool, Executor executor, ImageDecoder imageDecoder, ProgressiveJpegConfig progressiveJpegConfig, boolean z, boolean z2, Producer<EncodedImage> producer) {
        this.mByteArrayPool = (ByteArrayPool) Preconditions.checkNotNull(byteArrayPool);
        this.mExecutor = (Executor) Preconditions.checkNotNull(executor);
        this.mImageDecoder = (ImageDecoder) Preconditions.checkNotNull(imageDecoder);
        this.mProgressiveJpegConfig = (ProgressiveJpegConfig) Preconditions.checkNotNull(progressiveJpegConfig);
        this.mDownsampleEnabled = z;
        this.mDownsampleEnabledForNetwork = z2;
        this.mInputProducer = (Producer) Preconditions.checkNotNull(producer);
    }

    public void produceResults(Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext) {
        Consumer networkImagesProgressiveDecoder;
        if (UriUtil.isNetworkUri(producerContext.getImageRequest().getSourceUri())) {
            networkImagesProgressiveDecoder = new NetworkImagesProgressiveDecoder(consumer, producerContext, new ProgressiveJpegParser(this.mByteArrayPool), this.mProgressiveJpegConfig);
        } else {
            networkImagesProgressiveDecoder = new LocalImagesProgressiveDecoder(consumer, producerContext);
        }
        this.mInputProducer.produceResults(networkImagesProgressiveDecoder, producerContext);
    }
}
