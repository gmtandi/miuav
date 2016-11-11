package com.facebook.imagepipeline.datasource;

import com.facebook.common.internal.Preconditions;
import com.facebook.datasource.AbstractDataSource;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.producers.BaseConsumer;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.producers.SettableProducerContext;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class AbstractProducerToDataSourceAdapter<T> extends AbstractDataSource<T> {
    private final RequestListener mRequestListener;
    private final SettableProducerContext mSettableProducerContext;

    /* renamed from: com.facebook.imagepipeline.datasource.AbstractProducerToDataSourceAdapter.1 */
    class C10311 extends BaseConsumer<T> {
        C10311() {
        }

        protected void onCancellationImpl() {
            AbstractProducerToDataSourceAdapter.this.onCancellationImpl();
        }

        protected void onFailureImpl(Throwable th) {
            AbstractProducerToDataSourceAdapter.this.onFailureImpl(th);
        }

        protected void onNewResultImpl(@Nullable T t, boolean z) {
            AbstractProducerToDataSourceAdapter.this.onNewResultImpl(t, z);
        }

        protected void onProgressUpdateImpl(float f) {
            AbstractProducerToDataSourceAdapter.this.setProgress(f);
        }
    }

    protected AbstractProducerToDataSourceAdapter(Producer<T> producer, SettableProducerContext settableProducerContext, RequestListener requestListener) {
        this.mSettableProducerContext = settableProducerContext;
        this.mRequestListener = requestListener;
        this.mRequestListener.onRequestStart(settableProducerContext.getImageRequest(), this.mSettableProducerContext.getCallerContext(), this.mSettableProducerContext.getId(), this.mSettableProducerContext.isPrefetch());
        producer.produceResults(createConsumer(), settableProducerContext);
    }

    private Consumer<T> createConsumer() {
        return new C10311();
    }

    private synchronized void onCancellationImpl() {
        Preconditions.checkState(isClosed());
    }

    private void onFailureImpl(Throwable th) {
        if (super.setFailure(th)) {
            this.mRequestListener.onRequestFailure(this.mSettableProducerContext.getImageRequest(), this.mSettableProducerContext.getId(), th, this.mSettableProducerContext.isPrefetch());
        }
    }

    public boolean close() {
        if (!super.close()) {
            return false;
        }
        if (!super.isFinished()) {
            this.mRequestListener.onRequestCancellation(this.mSettableProducerContext.getId());
            this.mSettableProducerContext.cancel();
        }
        return true;
    }

    protected void onNewResultImpl(@Nullable T t, boolean z) {
        if (super.setResult(t, z) && z) {
            this.mRequestListener.onRequestSuccess(this.mSettableProducerContext.getImageRequest(), this.mSettableProducerContext.getId(), this.mSettableProducerContext.isPrefetch());
        }
    }
}
