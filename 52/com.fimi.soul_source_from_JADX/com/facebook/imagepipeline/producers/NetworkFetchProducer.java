package com.facebook.imagepipeline.producers;

import android.os.SystemClock;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.ByteArrayPool;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.memory.PooledByteBufferOutputStream;
import com.facebook.imagepipeline.producers.NetworkFetcher.Callback;
import com.fimi.soul.view.photodraweeview.C2020f;
import java.io.InputStream;
import java.util.Map;
import javax.annotation.Nullable;

public class NetworkFetchProducer implements Producer<EncodedImage> {
    public static final String INTERMEDIATE_RESULT_PRODUCER_EVENT = "intermediate_result";
    @VisibleForTesting
    static final String PRODUCER_NAME = "NetworkFetchProducer";
    private static final int READ_SIZE = 16384;
    @VisibleForTesting
    static final long TIME_BETWEEN_PARTIAL_RESULTS_MS = 100;
    private final ByteArrayPool mByteArrayPool;
    private final NetworkFetcher mNetworkFetcher;
    private final PooledByteBufferFactory mPooledByteBufferFactory;

    /* renamed from: com.facebook.imagepipeline.producers.NetworkFetchProducer.1 */
    class C10621 implements Callback {
        final /* synthetic */ FetchState val$fetchState;

        C10621(FetchState fetchState) {
            this.val$fetchState = fetchState;
        }

        public void onCancellation() {
            NetworkFetchProducer.this.onCancellation(this.val$fetchState);
        }

        public void onFailure(Throwable th) {
            NetworkFetchProducer.this.onFailure(this.val$fetchState, th);
        }

        public void onResponse(InputStream inputStream, int i) {
            NetworkFetchProducer.this.onResponse(this.val$fetchState, inputStream, i);
        }
    }

    public NetworkFetchProducer(PooledByteBufferFactory pooledByteBufferFactory, ByteArrayPool byteArrayPool, NetworkFetcher networkFetcher) {
        this.mPooledByteBufferFactory = pooledByteBufferFactory;
        this.mByteArrayPool = byteArrayPool;
        this.mNetworkFetcher = networkFetcher;
    }

    private static float calculateProgress(int i, int i2) {
        return i2 > 0 ? ((float) i) / ((float) i2) : C2020f.f10933c - ((float) Math.exp(((double) (-i)) / 50000.0d));
    }

    @Nullable
    private Map<String, String> getExtraMap(FetchState fetchState, int i) {
        return !fetchState.getListener().requiresExtraMap(fetchState.getId()) ? null : this.mNetworkFetcher.getExtraMap(fetchState, i);
    }

    private void handleFinalResult(PooledByteBufferOutputStream pooledByteBufferOutputStream, FetchState fetchState) {
        fetchState.getListener().onProducerFinishWithSuccess(fetchState.getId(), PRODUCER_NAME, getExtraMap(fetchState, pooledByteBufferOutputStream.size()));
        notifyConsumer(pooledByteBufferOutputStream, true, fetchState.getConsumer());
    }

    private void maybeHandleIntermediateResult(PooledByteBufferOutputStream pooledByteBufferOutputStream, FetchState fetchState) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (shouldPropagateIntermediateResults(fetchState) && elapsedRealtime - fetchState.getLastIntermediateResultTimeMs() >= TIME_BETWEEN_PARTIAL_RESULTS_MS) {
            fetchState.setLastIntermediateResultTimeMs(elapsedRealtime);
            fetchState.getListener().onProducerEvent(fetchState.getId(), PRODUCER_NAME, INTERMEDIATE_RESULT_PRODUCER_EVENT);
            notifyConsumer(pooledByteBufferOutputStream, false, fetchState.getConsumer());
        }
    }

    private void notifyConsumer(PooledByteBufferOutputStream pooledByteBufferOutputStream, boolean z, Consumer<EncodedImage> consumer) {
        Throwable th;
        CloseableReference of = CloseableReference.of(pooledByteBufferOutputStream.toByteBuffer());
        EncodedImage encodedImage;
        try {
            encodedImage = new EncodedImage(of);
            try {
                encodedImage.parseMetaData();
                consumer.onNewResult(encodedImage, z);
                EncodedImage.closeSafely(encodedImage);
                CloseableReference.closeSafely(of);
            } catch (Throwable th2) {
                th = th2;
                EncodedImage.closeSafely(encodedImage);
                CloseableReference.closeSafely(of);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            encodedImage = null;
            EncodedImage.closeSafely(encodedImage);
            CloseableReference.closeSafely(of);
            throw th;
        }
    }

    private void onCancellation(FetchState fetchState) {
        fetchState.getListener().onProducerFinishWithCancellation(fetchState.getId(), PRODUCER_NAME, null);
        fetchState.getConsumer().onCancellation();
    }

    private void onFailure(FetchState fetchState, Throwable th) {
        fetchState.getListener().onProducerFinishWithFailure(fetchState.getId(), PRODUCER_NAME, th, null);
        fetchState.getConsumer().onFailure(th);
    }

    private void onResponse(FetchState fetchState, InputStream inputStream, int i) {
        PooledByteBufferOutputStream newOutputStream = i > 0 ? this.mPooledByteBufferFactory.newOutputStream(i) : this.mPooledByteBufferFactory.newOutputStream();
        byte[] bArr = (byte[]) this.mByteArrayPool.get(READ_SIZE);
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read < 0) {
                    break;
                } else if (read > 0) {
                    newOutputStream.write(bArr, 0, read);
                    maybeHandleIntermediateResult(newOutputStream, fetchState);
                    fetchState.getConsumer().onProgressUpdate(calculateProgress(newOutputStream.size(), i));
                }
            } finally {
                this.mByteArrayPool.release(bArr);
                newOutputStream.close();
            }
        }
        this.mNetworkFetcher.onFetchCompletion(fetchState, newOutputStream.size());
        handleFinalResult(newOutputStream, fetchState);
    }

    private boolean shouldPropagateIntermediateResults(FetchState fetchState) {
        return !fetchState.getContext().getImageRequest().getProgressiveRenderingEnabled() ? false : this.mNetworkFetcher.shouldPropagate(fetchState);
    }

    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        producerContext.getListener().onProducerStart(producerContext.getId(), PRODUCER_NAME);
        FetchState createFetchState = this.mNetworkFetcher.createFetchState(consumer, producerContext);
        this.mNetworkFetcher.fetch(createFetchState, new C10621(createFetchState));
    }
}
