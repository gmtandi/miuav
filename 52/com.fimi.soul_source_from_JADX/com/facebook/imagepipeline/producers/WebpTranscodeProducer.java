package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.TriState;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imageformat.ImageFormatChecker;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.memory.PooledByteBufferOutputStream;
import com.facebook.imagepipeline.nativecode.WebpTranscoder;
import java.io.InputStream;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

public class WebpTranscodeProducer implements Producer<EncodedImage> {
    private static final int DEFAULT_JPEG_QUALITY = 80;
    private static final String PRODUCER_NAME = "WebpTranscodeProducer";
    private final Executor mExecutor;
    private final Producer<EncodedImage> mInputProducer;
    private final PooledByteBufferFactory mPooledByteBufferFactory;

    /* renamed from: com.facebook.imagepipeline.producers.WebpTranscodeProducer.1 */
    class C10761 extends StatefulProducerRunnable<EncodedImage> {
        final /* synthetic */ EncodedImage val$encodedImageCopy;

        C10761(Consumer consumer, ProducerListener producerListener, String str, String str2, EncodedImage encodedImage) {
            this.val$encodedImageCopy = encodedImage;
            super(consumer, producerListener, str, str2);
        }

        protected void disposeResult(EncodedImage encodedImage) {
            EncodedImage.closeSafely(encodedImage);
        }

        protected EncodedImage getResult() {
            PooledByteBufferOutputStream newOutputStream = WebpTranscodeProducer.this.mPooledByteBufferFactory.newOutputStream();
            CloseableReference of;
            try {
                WebpTranscodeProducer.doTranscode(this.val$encodedImageCopy, newOutputStream);
                of = CloseableReference.of(newOutputStream.toByteBuffer());
                EncodedImage encodedImage = new EncodedImage(of);
                encodedImage.copyMetaDataFrom(this.val$encodedImageCopy);
                CloseableReference.closeSafely(of);
                newOutputStream.close();
                return encodedImage;
            } catch (Throwable th) {
                newOutputStream.close();
            }
        }

        protected void onCancellation() {
            EncodedImage.closeSafely(this.val$encodedImageCopy);
            super.onCancellation();
        }

        protected void onFailure(Exception exception) {
            EncodedImage.closeSafely(this.val$encodedImageCopy);
            super.onFailure(exception);
        }

        protected void onSuccess(EncodedImage encodedImage) {
            EncodedImage.closeSafely(this.val$encodedImageCopy);
            super.onSuccess(encodedImage);
        }
    }

    /* renamed from: com.facebook.imagepipeline.producers.WebpTranscodeProducer.2 */
    /* synthetic */ class C10772 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$imageformat$ImageFormat;

        static {
            $SwitchMap$com$facebook$imageformat$ImageFormat = new int[ImageFormat.values().length];
            try {
                $SwitchMap$com$facebook$imageformat$ImageFormat[ImageFormat.WEBP_SIMPLE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$facebook$imageformat$ImageFormat[ImageFormat.WEBP_LOSSLESS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$facebook$imageformat$ImageFormat[ImageFormat.WEBP_EXTENDED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$facebook$imageformat$ImageFormat[ImageFormat.WEBP_EXTENDED_WITH_ALPHA.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$facebook$imageformat$ImageFormat[ImageFormat.UNKNOWN.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    class WebpTranscodeConsumer extends DelegatingConsumer<EncodedImage, EncodedImage> {
        private final ProducerContext mContext;
        private TriState mShouldTranscodeWhenFinished;

        public WebpTranscodeConsumer(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
            super(consumer);
            this.mContext = producerContext;
            this.mShouldTranscodeWhenFinished = TriState.UNSET;
        }

        protected void onNewResultImpl(@Nullable EncodedImage encodedImage, boolean z) {
            if (this.mShouldTranscodeWhenFinished == TriState.UNSET && encodedImage != null) {
                this.mShouldTranscodeWhenFinished = WebpTranscodeProducer.shouldTranscode(encodedImage);
            }
            if (this.mShouldTranscodeWhenFinished == TriState.NO) {
                getConsumer().onNewResult(encodedImage, z);
            } else if (!z) {
            } else {
                if (this.mShouldTranscodeWhenFinished != TriState.YES || encodedImage == null) {
                    getConsumer().onNewResult(encodedImage, z);
                } else {
                    WebpTranscodeProducer.this.transcodeLastResult(encodedImage, getConsumer(), this.mContext);
                }
            }
        }
    }

    public WebpTranscodeProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, Producer<EncodedImage> producer) {
        this.mExecutor = (Executor) Preconditions.checkNotNull(executor);
        this.mPooledByteBufferFactory = (PooledByteBufferFactory) Preconditions.checkNotNull(pooledByteBufferFactory);
        this.mInputProducer = (Producer) Preconditions.checkNotNull(producer);
    }

    private static void doTranscode(EncodedImage encodedImage, PooledByteBufferOutputStream pooledByteBufferOutputStream) {
        InputStream inputStream = encodedImage.getInputStream();
        switch (C10772.$SwitchMap$com$facebook$imageformat$ImageFormat[ImageFormatChecker.getImageFormat_WrapIOException(inputStream).ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
            case Type.BYTE /*3*/:
                WebpTranscoder.transcodeWebpToJpeg(inputStream, pooledByteBufferOutputStream, DEFAULT_JPEG_QUALITY);
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                WebpTranscoder.transcodeWebpToPng(inputStream, pooledByteBufferOutputStream);
            default:
                throw new IllegalArgumentException("Wrong image format");
        }
    }

    private static TriState shouldTranscode(EncodedImage encodedImage) {
        Preconditions.checkNotNull(encodedImage);
        ImageFormat imageFormat_WrapIOException = ImageFormatChecker.getImageFormat_WrapIOException(encodedImage.getInputStream());
        switch (C10772.$SwitchMap$com$facebook$imageformat$ImageFormat[imageFormat_WrapIOException.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
            case Type.BYTE /*3*/:
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                return TriState.valueOf(!WebpTranscoder.isWebpNativelySupported(imageFormat_WrapIOException));
            case Type.INT /*5*/:
                return TriState.UNSET;
            default:
                return TriState.NO;
        }
    }

    private void transcodeLastResult(EncodedImage encodedImage, Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        Preconditions.checkNotNull(encodedImage);
        Consumer<EncodedImage> consumer2 = consumer;
        this.mExecutor.execute(new C10761(consumer2, producerContext.getListener(), PRODUCER_NAME, producerContext.getId(), EncodedImage.cloneOrNull(encodedImage)));
    }

    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        this.mInputProducer.produceResults(new WebpTranscodeConsumer(consumer, producerContext), producerContext);
    }
}
