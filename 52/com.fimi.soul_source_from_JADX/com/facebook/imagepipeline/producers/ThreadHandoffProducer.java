package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import java.util.concurrent.Executor;

public class ThreadHandoffProducer<T> implements Producer<T> {
    @VisibleForTesting
    protected static final String PRODUCER_NAME = "BackgroundThreadHandoffProducer";
    private final Executor mExecutor;
    private final Producer<T> mInputProducer;

    /* renamed from: com.facebook.imagepipeline.producers.ThreadHandoffProducer.1 */
    class C10721 extends StatefulProducerRunnable<T> {
        final /* synthetic */ Consumer val$consumer;
        final /* synthetic */ ProducerContext val$context;
        final /* synthetic */ ProducerListener val$producerListener;
        final /* synthetic */ String val$requestId;

        C10721(Consumer consumer, ProducerListener producerListener, String str, String str2, ProducerListener producerListener2, String str3, Consumer consumer2, ProducerContext producerContext) {
            this.val$producerListener = producerListener2;
            this.val$requestId = str3;
            this.val$consumer = consumer2;
            this.val$context = producerContext;
            super(consumer, producerListener, str, str2);
        }

        protected void disposeResult(T t) {
        }

        protected T getResult() {
            return null;
        }

        protected void onSuccess(T t) {
            this.val$producerListener.onProducerFinishWithSuccess(this.val$requestId, ThreadHandoffProducer.PRODUCER_NAME, null);
            ThreadHandoffProducer.this.mInputProducer.produceResults(this.val$consumer, this.val$context);
        }
    }

    /* renamed from: com.facebook.imagepipeline.producers.ThreadHandoffProducer.2 */
    class C10732 extends BaseProducerContextCallbacks {
        final /* synthetic */ StatefulProducerRunnable val$statefulRunnable;

        C10732(StatefulProducerRunnable statefulProducerRunnable) {
            this.val$statefulRunnable = statefulProducerRunnable;
        }

        public void onCancellationRequested() {
            this.val$statefulRunnable.cancel();
        }
    }

    public ThreadHandoffProducer(Executor executor, Producer<T> producer) {
        this.mExecutor = (Executor) Preconditions.checkNotNull(executor);
        this.mInputProducer = (Producer) Preconditions.checkNotNull(producer);
    }

    public void produceResults(Consumer<T> consumer, ProducerContext producerContext) {
        ProducerListener listener = producerContext.getListener();
        String id = producerContext.getId();
        Runnable c10721 = new C10721(consumer, listener, PRODUCER_NAME, id, listener, id, consumer, producerContext);
        producerContext.addCallbacks(new C10732(c10721));
        this.mExecutor.execute(c10721);
    }
}
