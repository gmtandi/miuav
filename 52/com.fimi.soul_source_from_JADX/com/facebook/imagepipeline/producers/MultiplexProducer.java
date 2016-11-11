package com.facebook.imagepipeline.producers;

import android.util.Pair;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Sets;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.imagepipeline.common.Priority;
import java.io.Closeable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class MultiplexProducer<K, T extends Closeable> implements Producer<T> {
    private final Producer<T> mInputProducer;
    @GuardedBy("this")
    @VisibleForTesting
    final Map<K, Multiplexer> mMultiplexers;

    @VisibleForTesting
    class Multiplexer {
        private final CopyOnWriteArraySet<Pair<Consumer<T>, ProducerContext>> mConsumerContextPairs;
        @GuardedBy("Multiplexer.this")
        @Nullable
        private com.facebook.imagepipeline.producers.MultiplexProducer$Multiplexer.ForwardingConsumer mForwardingConsumer;
        private final K mKey;
        @GuardedBy("Multiplexer.this")
        @Nullable
        private T mLastIntermediateResult;
        @GuardedBy("Multiplexer.this")
        private float mLastProgress;
        @GuardedBy("Multiplexer.this")
        @Nullable
        private BaseProducerContext mMultiplexProducerContext;

        /* renamed from: com.facebook.imagepipeline.producers.MultiplexProducer.Multiplexer.1 */
        class C10611 extends BaseProducerContextCallbacks {
            final /* synthetic */ Pair val$consumerContextPair;

            C10611(Pair pair) {
                this.val$consumerContextPair = pair;
            }

            public void onCancellationRequested() {
                List list;
                List list2;
                BaseProducerContext baseProducerContext;
                List list3 = null;
                synchronized (Multiplexer.this) {
                    boolean remove = Multiplexer.this.mConsumerContextPairs.remove(this.val$consumerContextPair);
                    if (!remove) {
                        list = null;
                        list2 = null;
                        baseProducerContext = null;
                    } else if (Multiplexer.this.mConsumerContextPairs.isEmpty()) {
                        list2 = null;
                        baseProducerContext = Multiplexer.this.mMultiplexProducerContext;
                        list = null;
                    } else {
                        List access$400 = Multiplexer.this.updateIsPrefetch();
                        list2 = Multiplexer.this.updatePriority();
                        list = list2;
                        list2 = access$400;
                        baseProducerContext = null;
                        list3 = Multiplexer.this.updateIsIntermediateResultExpected();
                    }
                }
                BaseProducerContext.callOnIsPrefetchChanged(list2);
                BaseProducerContext.callOnPriorityChanged(list);
                BaseProducerContext.callOnIsIntermediateResultExpectedChanged(list3);
                if (baseProducerContext != null) {
                    baseProducerContext.cancel();
                }
                if (remove) {
                    ((Consumer) this.val$consumerContextPair.first).onCancellation();
                }
            }

            public void onIsIntermediateResultExpectedChanged() {
                BaseProducerContext.callOnIsIntermediateResultExpectedChanged(Multiplexer.this.updateIsIntermediateResultExpected());
            }

            public void onIsPrefetchChanged() {
                BaseProducerContext.callOnIsPrefetchChanged(Multiplexer.this.updateIsPrefetch());
            }

            public void onPriorityChanged() {
                BaseProducerContext.callOnPriorityChanged(Multiplexer.this.updatePriority());
            }
        }

        class ForwardingConsumer extends BaseConsumer<T> {
            private ForwardingConsumer() {
            }

            protected void onCancellationImpl() {
                Multiplexer.this.onCancelled(this);
            }

            protected void onFailureImpl(Throwable th) {
                Multiplexer.this.onFailure(this, th);
            }

            protected void onNewResultImpl(T t, boolean z) {
                Multiplexer.this.onNextResult(this, t, z);
            }

            protected void onProgressUpdateImpl(float f) {
                Multiplexer.this.onProgressUpdate(this, f);
            }
        }

        public Multiplexer(K k) {
            this.mConsumerContextPairs = Sets.newCopyOnWriteArraySet();
            this.mKey = k;
        }

        private void addCallbacks(Pair<Consumer<T>, ProducerContext> pair, ProducerContext producerContext) {
            producerContext.addCallbacks(new C10611(pair));
        }

        private void closeSafely(Closeable closeable) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        }

        private synchronized boolean computeIsIntermediateResultExpected() {
            boolean z;
            Iterator it = this.mConsumerContextPairs.iterator();
            while (it.hasNext()) {
                if (((ProducerContext) ((Pair) it.next()).second).isIntermediateResultExpected()) {
                    z = true;
                    break;
                }
            }
            z = false;
            return z;
        }

        private synchronized boolean computeIsPrefetch() {
            boolean z;
            Iterator it = this.mConsumerContextPairs.iterator();
            while (it.hasNext()) {
                if (!((ProducerContext) ((Pair) it.next()).second).isPrefetch()) {
                    z = false;
                    break;
                }
            }
            z = true;
            return z;
        }

        private synchronized Priority computePriority() {
            Priority priority;
            Priority priority2 = Priority.LOW;
            Iterator it = this.mConsumerContextPairs.iterator();
            priority = priority2;
            while (it.hasNext()) {
                priority = Priority.getHigherPriority(priority, ((ProducerContext) ((Pair) it.next()).second).getPriority());
            }
            return priority;
        }

        private void startInputProducerIfHasAttachedConsumers() {
            boolean z = true;
            synchronized (this) {
                Preconditions.checkArgument(this.mMultiplexProducerContext == null);
                if (this.mForwardingConsumer != null) {
                    z = false;
                }
                Preconditions.checkArgument(z);
                if (this.mConsumerContextPairs.isEmpty()) {
                    MultiplexProducer.this.removeMultiplexer(this.mKey, this);
                    return;
                }
                ProducerContext producerContext = (ProducerContext) ((Pair) this.mConsumerContextPairs.iterator().next()).second;
                this.mMultiplexProducerContext = new BaseProducerContext(producerContext.getImageRequest(), producerContext.getId(), producerContext.getListener(), producerContext.getCallerContext(), producerContext.getLowestPermittedRequestLevel(), computeIsPrefetch(), computeIsIntermediateResultExpected(), computePriority());
                this.mForwardingConsumer = new ForwardingConsumer();
                ProducerContext producerContext2 = this.mMultiplexProducerContext;
                Consumer consumer = this.mForwardingConsumer;
                MultiplexProducer.this.mInputProducer.produceResults(consumer, producerContext2);
            }
        }

        @Nullable
        private synchronized List<ProducerContextCallbacks> updateIsIntermediateResultExpected() {
            return this.mMultiplexProducerContext == null ? null : this.mMultiplexProducerContext.setIsIntermediateResultExpectedNoCallbacks(computeIsIntermediateResultExpected());
        }

        @Nullable
        private synchronized List<ProducerContextCallbacks> updateIsPrefetch() {
            return this.mMultiplexProducerContext == null ? null : this.mMultiplexProducerContext.setIsPrefetchNoCallbacks(computeIsPrefetch());
        }

        @Nullable
        private synchronized List<ProducerContextCallbacks> updatePriority() {
            return this.mMultiplexProducerContext == null ? null : this.mMultiplexProducerContext.setPriorityNoCallbacks(computePriority());
        }

        public boolean addNewConsumer(Consumer<T> consumer, ProducerContext producerContext) {
            Pair create = Pair.create(consumer, producerContext);
            synchronized (this) {
                if (MultiplexProducer.this.getExistingMultiplexer(this.mKey) != this) {
                    return false;
                }
                this.mConsumerContextPairs.add(create);
                List updateIsPrefetch = updateIsPrefetch();
                List updatePriority = updatePriority();
                List updateIsIntermediateResultExpected = updateIsIntermediateResultExpected();
                Closeable closeable = this.mLastIntermediateResult;
                float f = this.mLastProgress;
                BaseProducerContext.callOnIsPrefetchChanged(updateIsPrefetch);
                BaseProducerContext.callOnPriorityChanged(updatePriority);
                BaseProducerContext.callOnIsIntermediateResultExpectedChanged(updateIsIntermediateResultExpected);
                synchronized (create) {
                    synchronized (this) {
                        if (closeable != this.mLastIntermediateResult) {
                            closeable = null;
                        } else if (closeable != null) {
                            closeable = MultiplexProducer.this.cloneOrNull(closeable);
                        }
                    }
                    if (closeable != null) {
                        if (f > 0.0f) {
                            consumer.onProgressUpdate(f);
                        }
                        consumer.onNewResult(closeable, false);
                        closeSafely(closeable);
                    }
                }
                addCallbacks(create, producerContext);
                return true;
            }
        }

        public void onCancelled(com.facebook.imagepipeline.producers.MultiplexProducer$Multiplexer.ForwardingConsumer forwardingConsumer) {
            synchronized (this) {
                if (this.mForwardingConsumer != forwardingConsumer) {
                    return;
                }
                this.mForwardingConsumer = null;
                this.mMultiplexProducerContext = null;
                closeSafely(this.mLastIntermediateResult);
                this.mLastIntermediateResult = null;
                startInputProducerIfHasAttachedConsumers();
            }
        }

        public void onFailure(com.facebook.imagepipeline.producers.MultiplexProducer$Multiplexer.ForwardingConsumer forwardingConsumer, Throwable th) {
            synchronized (this) {
                if (this.mForwardingConsumer != forwardingConsumer) {
                    return;
                }
                Iterator it = this.mConsumerContextPairs.iterator();
                this.mConsumerContextPairs.clear();
                MultiplexProducer.this.removeMultiplexer(this.mKey, this);
                closeSafely(this.mLastIntermediateResult);
                this.mLastIntermediateResult = null;
                while (it.hasNext()) {
                    Pair pair = (Pair) it.next();
                    synchronized (pair) {
                        ((Consumer) pair.first).onFailure(th);
                    }
                }
            }
        }

        public void onNextResult(com.facebook.imagepipeline.producers.MultiplexProducer$Multiplexer.ForwardingConsumer forwardingConsumer, T t, boolean z) {
            synchronized (this) {
                if (this.mForwardingConsumer != forwardingConsumer) {
                    return;
                }
                closeSafely(this.mLastIntermediateResult);
                this.mLastIntermediateResult = null;
                Iterator it = this.mConsumerContextPairs.iterator();
                if (z) {
                    this.mConsumerContextPairs.clear();
                    MultiplexProducer.this.removeMultiplexer(this.mKey, this);
                } else {
                    this.mLastIntermediateResult = MultiplexProducer.this.cloneOrNull(t);
                }
                while (it.hasNext()) {
                    Pair pair = (Pair) it.next();
                    synchronized (pair) {
                        ((Consumer) pair.first).onNewResult(t, z);
                    }
                }
            }
        }

        public void onProgressUpdate(com.facebook.imagepipeline.producers.MultiplexProducer$Multiplexer.ForwardingConsumer forwardingConsumer, float f) {
            synchronized (this) {
                if (this.mForwardingConsumer != forwardingConsumer) {
                    return;
                }
                this.mLastProgress = f;
                Iterator it = this.mConsumerContextPairs.iterator();
                while (it.hasNext()) {
                    Pair pair = (Pair) it.next();
                    synchronized (pair) {
                        ((Consumer) pair.first).onProgressUpdate(f);
                    }
                }
            }
        }
    }

    protected MultiplexProducer(Producer<T> producer) {
        this.mInputProducer = producer;
        this.mMultiplexers = new HashMap();
    }

    private synchronized Multiplexer createAndPutNewMultiplexer(K k) {
        Multiplexer multiplexer;
        multiplexer = new Multiplexer(k);
        this.mMultiplexers.put(k, multiplexer);
        return multiplexer;
    }

    private synchronized Multiplexer getExistingMultiplexer(K k) {
        return (Multiplexer) this.mMultiplexers.get(k);
    }

    private synchronized void removeMultiplexer(K k, Multiplexer multiplexer) {
        if (this.mMultiplexers.get(k) == multiplexer) {
            this.mMultiplexers.remove(k);
        }
    }

    protected abstract T cloneOrNull(T t);

    protected abstract K getKey(ProducerContext producerContext);

    public void produceResults(Consumer<T> consumer, ProducerContext producerContext) {
        Object key = getKey(producerContext);
        Multiplexer existingMultiplexer;
        do {
            Object obj = null;
            synchronized (this) {
                existingMultiplexer = getExistingMultiplexer(key);
                if (existingMultiplexer == null) {
                    existingMultiplexer = createAndPutNewMultiplexer(key);
                    obj = 1;
                }
            }
        } while (!existingMultiplexer.addNewConsumer(consumer, producerContext));
        if (obj != null) {
            existingMultiplexer.startInputProducerIfHasAttachedConsumers();
        }
    }
}
