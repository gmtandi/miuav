package com.facebook.imagepipeline.producers;

public class SwallowResultProducer<T> implements Producer<Void> {
    private final Producer<T> mInputProducer;

    /* renamed from: com.facebook.imagepipeline.producers.SwallowResultProducer.1 */
    class C10711 extends DelegatingConsumer<T, Void> {
        C10711(Consumer consumer) {
            super(consumer);
        }

        protected void onNewResultImpl(T t, boolean z) {
            if (z) {
                getConsumer().onNewResult(null, z);
            }
        }
    }

    public SwallowResultProducer(Producer<T> producer) {
        this.mInputProducer = producer;
    }

    public void produceResults(Consumer<Void> consumer, ProducerContext producerContext) {
        this.mInputProducer.produceResults(new C10711(consumer), producerContext);
    }
}
