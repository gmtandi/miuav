package com.facebook.imagepipeline.producers;

import android.os.Build.VERSION;
import com.facebook.common.internal.Closeables;
import com.facebook.common.internal.Supplier;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.Executor;

public abstract class LocalFetchProducer implements Producer<EncodedImage> {
    private final boolean mDecodeFileDescriptorEnabledForKitKat;
    private final Executor mExecutor;
    private final PooledByteBufferFactory mPooledByteBufferFactory;

    /* renamed from: com.facebook.imagepipeline.producers.LocalFetchProducer.1 */
    class C10551 extends StatefulProducerRunnable<EncodedImage> {
        final /* synthetic */ ImageRequest val$imageRequest;

        C10551(Consumer consumer, ProducerListener producerListener, String str, String str2, ImageRequest imageRequest) {
            this.val$imageRequest = imageRequest;
            super(consumer, producerListener, str, str2);
        }

        protected void disposeResult(EncodedImage encodedImage) {
            EncodedImage.closeSafely(encodedImage);
        }

        protected EncodedImage getResult() {
            EncodedImage encodedImage = LocalFetchProducer.this.getEncodedImage(this.val$imageRequest);
            if (encodedImage == null) {
                return null;
            }
            encodedImage.parseMetaData();
            return encodedImage;
        }
    }

    /* renamed from: com.facebook.imagepipeline.producers.LocalFetchProducer.2 */
    class C10562 extends BaseProducerContextCallbacks {
        final /* synthetic */ StatefulProducerRunnable val$cancellableProducerRunnable;

        C10562(StatefulProducerRunnable statefulProducerRunnable) {
            this.val$cancellableProducerRunnable = statefulProducerRunnable;
        }

        public void onCancellationRequested() {
            this.val$cancellableProducerRunnable.cancel();
        }
    }

    /* renamed from: com.facebook.imagepipeline.producers.LocalFetchProducer.3 */
    class C10573 implements Supplier<FileInputStream> {
        final /* synthetic */ File val$file;

        C10573(File file) {
            this.val$file = file;
        }

        public FileInputStream get() {
            try {
                return new FileInputStream(this.val$file);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected LocalFetchProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, boolean z) {
        this.mExecutor = executor;
        this.mPooledByteBufferFactory = pooledByteBufferFactory;
        boolean z2 = z && VERSION.SDK_INT == 19;
        this.mDecodeFileDescriptorEnabledForKitKat = z2;
    }

    protected EncodedImage getByteBufferBackedEncodedImage(InputStream inputStream, int i) {
        CloseableReference closeableReference = null;
        if (i < 0) {
            try {
                closeableReference = CloseableReference.of(this.mPooledByteBufferFactory.newByteBuffer(inputStream));
            } catch (Throwable th) {
                Closeables.closeQuietly(inputStream);
                CloseableReference.closeSafely(closeableReference);
            }
        } else {
            closeableReference = CloseableReference.of(this.mPooledByteBufferFactory.newByteBuffer(inputStream, i));
        }
        EncodedImage encodedImage = new EncodedImage(closeableReference);
        Closeables.closeQuietly(inputStream);
        CloseableReference.closeSafely(closeableReference);
        return encodedImage;
    }

    protected abstract EncodedImage getEncodedImage(ImageRequest imageRequest);

    protected EncodedImage getEncodedImage(InputStream inputStream, int i) {
        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory();
        return (this.mDecodeFileDescriptorEnabledForKitKat && (inputStream instanceof FileInputStream) && maxMemory >= Math.min(maxMemory - (runtime.totalMemory() - runtime.freeMemory()), 8388608) * 64) ? getInputStreamBackedEncodedImage(new File(inputStream.toString()), i) : getByteBufferBackedEncodedImage(inputStream, i);
    }

    protected EncodedImage getInputStreamBackedEncodedImage(File file, int i) {
        return new EncodedImage(new C10573(file), i);
    }

    protected abstract String getProducerName();

    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        Consumer<EncodedImage> consumer2 = consumer;
        Runnable c10551 = new C10551(consumer2, producerContext.getListener(), getProducerName(), producerContext.getId(), producerContext.getImageRequest());
        producerContext.addCallbacks(new C10562(c10551));
        this.mExecutor.execute(c10551);
    }
}
