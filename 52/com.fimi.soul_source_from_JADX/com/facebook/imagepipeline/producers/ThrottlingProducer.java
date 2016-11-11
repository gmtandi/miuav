package com.facebook.imagepipeline.producers;

import android.util.Pair;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

public class ThrottlingProducer<T> implements Producer<T> {
    @VisibleForTesting
    static final String PRODUCER_NAME = "ThrottlingProducer";
    private final Executor mExecutor;
    private final Producer<T> mInputProducer;
    private final int mMaxSimultaneousRequests;
    @GuardedBy("this")
    private int mNumCurrentRequests;
    @GuardedBy("this")
    private final ConcurrentLinkedQueue<Pair<Consumer<T>, ProducerContext>> mPendingRequests;

    class ThrottlerConsumer extends DelegatingConsumer<T, T> {

        /* renamed from: com.facebook.imagepipeline.producers.ThrottlingProducer.ThrottlerConsumer.1 */
        class C10751 implements Runnable {
            final /* synthetic */ Pair val$nextRequestPair;

            C10751(Pair pair) {
                this.val$nextRequestPair = pair;
            }

            public void run() {
                ThrottlingProducer.this.produceResultsInternal((Consumer) this.val$nextRequestPair.first, (ProducerContext) this.val$nextRequestPair.second);
            }
        }

        private ThrottlerConsumer(Consumer<T> consumer) {
            super(consumer);
        }

        private void onRequestFinished() {
            synchronized (ThrottlingProducer.this) {
                Pair pair = (Pair) ThrottlingProducer.this.mPendingRequests.poll();
                if (pair == null) {
                    ThrottlingProducer.this.mNumCurrentRequests = ThrottlingProducer.this.mNumCurrentRequests - 1;
                }
            }
            if (pair != null) {
                ThrottlingProducer.this.mExecutor.execute(new C10751(pair));
            }
        }

        protected void onCancellationImpl() {
            getConsumer().onCancellation();
            onRequestFinished();
        }

        protected void onFailureImpl(Throwable th) {
            getConsumer().onFailure(th);
            onRequestFinished();
        }

        protected void onNewResultImpl(T t, boolean z) {
            getConsumer().onNewResult(t, z);
            if (z) {
                onRequestFinished();
            }
        }
    }

    public ThrottlingProducer(int i, Executor executor, Producer<T> producer) {
        this.mMaxSimultaneousRequests = i;
        this.mExecutor = (Executor) Preconditions.checkNotNull(executor);
        this.mInputProducer = (Producer) Preconditions.checkNotNull(producer);
        this.mPendingRequests = new ConcurrentLinkedQueue();
        this.mNumCurrentRequests = 0;
    }

    public void produceResults(Consumer<T> consumer, ProducerContext producerContext) {
        Object obj;
        producerContext.getListener().onProducerStart(producerContext.getId(), PRODUCER_NAME);
        synchronized (this) {
            if (this.mNumCurrentRequests >= this.mMaxSimultaneousRequests) {
                this.mPendingRequests.add(Pair.create(consumer, producerContext));
                obj = 1;
            } else {
                this.mNumCurrentRequests++;
                obj = null;
            }
        }
        if (obj == null) {
            produceResultsInternal(consumer, producerContext);
        }
    }

    void produceResultsInternal(Consumer<T> consumer, ProducerContext producerContext) {
        producerContext.getListener().onProducerFinishWithSuccess(producerContext.getId(), PRODUCER_NAME, null);
        this.mInputProducer.produceResults(new ThrottlerConsumer(consumer, null), producerContext);
    }
}
