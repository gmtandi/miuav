package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.request.Postprocessor;
import com.facebook.imagepipeline.request.RepeatedPostprocessor;
import com.facebook.imagepipeline.request.RepeatedPostprocessorRunner;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public class PostprocessorProducer implements Producer<CloseableReference<CloseableImage>> {
    @VisibleForTesting
    static final String NAME = "PostprocessorProducer";
    @VisibleForTesting
    static final String POSTPROCESSOR = "Postprocessor";
    private final PlatformBitmapFactory mBitmapFactory;
    private final Executor mExecutor;
    private final Producer<CloseableReference<CloseableImage>> mInputProducer;

    class PostprocessorConsumer extends DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>> {
        @GuardedBy("PostprocessorConsumer.this")
        private boolean mIsClosed;
        @GuardedBy("PostprocessorConsumer.this")
        private boolean mIsDirty;
        @GuardedBy("PostprocessorConsumer.this")
        private boolean mIsLast;
        @GuardedBy("PostprocessorConsumer.this")
        private boolean mIsPostProcessingRunning;
        private final ProducerListener mListener;
        private final Postprocessor mPostprocessor;
        private final String mRequestId;
        @GuardedBy("PostprocessorConsumer.this")
        @Nullable
        private CloseableReference<CloseableImage> mSourceImageRef;

        /* renamed from: com.facebook.imagepipeline.producers.PostprocessorProducer.PostprocessorConsumer.1 */
        class C10651 extends BaseProducerContextCallbacks {
            final /* synthetic */ PostprocessorProducer val$this$0;

            C10651(PostprocessorProducer postprocessorProducer) {
                this.val$this$0 = postprocessorProducer;
            }

            public void onCancellationRequested() {
                PostprocessorConsumer.this.maybeNotifyOnCancellation();
            }
        }

        /* renamed from: com.facebook.imagepipeline.producers.PostprocessorProducer.PostprocessorConsumer.2 */
        class C10662 implements Runnable {
            C10662() {
            }

            public void run() {
                synchronized (PostprocessorConsumer.this) {
                    CloseableReference access$300 = PostprocessorConsumer.this.mSourceImageRef;
                    boolean access$400 = PostprocessorConsumer.this.mIsLast;
                    PostprocessorConsumer.this.mSourceImageRef = null;
                    PostprocessorConsumer.this.mIsDirty = false;
                }
                if (CloseableReference.isValid(access$300)) {
                    try {
                        PostprocessorConsumer.this.doPostprocessing(access$300, access$400);
                    } finally {
                        CloseableReference.closeSafely(access$300);
                    }
                }
                PostprocessorConsumer.this.clearRunningAndStartIfDirty();
            }
        }

        public PostprocessorConsumer(Consumer<CloseableReference<CloseableImage>> consumer, ProducerListener producerListener, String str, Postprocessor postprocessor, ProducerContext producerContext) {
            super(consumer);
            this.mSourceImageRef = null;
            this.mIsLast = false;
            this.mIsDirty = false;
            this.mIsPostProcessingRunning = false;
            this.mListener = producerListener;
            this.mRequestId = str;
            this.mPostprocessor = postprocessor;
            producerContext.addCallbacks(new C10651(PostprocessorProducer.this));
        }

        private void clearRunningAndStartIfDirty() {
            synchronized (this) {
                this.mIsPostProcessingRunning = false;
                boolean runningIfDirtyAndNotRunning = setRunningIfDirtyAndNotRunning();
            }
            if (runningIfDirtyAndNotRunning) {
                submitPostprocessing();
            }
        }

        private boolean close() {
            boolean z = true;
            synchronized (this) {
                if (this.mIsClosed) {
                    z = false;
                } else {
                    CloseableReference closeableReference = this.mSourceImageRef;
                    this.mSourceImageRef = null;
                    this.mIsClosed = true;
                    CloseableReference.closeSafely(closeableReference);
                }
            }
            return z;
        }

        private void doPostprocessing(CloseableReference<CloseableImage> closeableReference, boolean z) {
            Preconditions.checkArgument(CloseableReference.isValid(closeableReference));
            if (shouldPostprocess((CloseableImage) closeableReference.get())) {
                this.mListener.onProducerStart(this.mRequestId, PostprocessorProducer.NAME);
                CloseableReference closeableReference2 = null;
                try {
                    closeableReference2 = postprocessInternal((CloseableImage) closeableReference.get());
                    this.mListener.onProducerFinishWithSuccess(this.mRequestId, PostprocessorProducer.NAME, getExtraMap(this.mListener, this.mRequestId, this.mPostprocessor));
                    maybeNotifyOnNewResult(closeableReference2, z);
                } catch (Throwable e) {
                    this.mListener.onProducerFinishWithFailure(this.mRequestId, PostprocessorProducer.NAME, e, getExtraMap(this.mListener, this.mRequestId, this.mPostprocessor));
                    maybeNotifyOnFailure(e);
                } finally {
                    CloseableReference.closeSafely(closeableReference2);
                }
            } else {
                maybeNotifyOnNewResult(closeableReference, z);
            }
        }

        private Map<String, String> getExtraMap(ProducerListener producerListener, String str, Postprocessor postprocessor) {
            return !producerListener.requiresExtraMap(str) ? null : ImmutableMap.of(PostprocessorProducer.POSTPROCESSOR, postprocessor.getName());
        }

        private synchronized boolean isClosed() {
            return this.mIsClosed;
        }

        private void maybeNotifyOnCancellation() {
            if (close()) {
                getConsumer().onCancellation();
            }
        }

        private void maybeNotifyOnFailure(Throwable th) {
            if (close()) {
                getConsumer().onFailure(th);
            }
        }

        private void maybeNotifyOnNewResult(CloseableReference<CloseableImage> closeableReference, boolean z) {
            if ((!z && !isClosed()) || (z && close())) {
                getConsumer().onNewResult(closeableReference, z);
            }
        }

        private CloseableReference<CloseableImage> postprocessInternal(CloseableImage closeableImage) {
            CloseableStaticBitmap closeableStaticBitmap = (CloseableStaticBitmap) closeableImage;
            CloseableReference process = this.mPostprocessor.process(closeableStaticBitmap.getUnderlyingBitmap(), PostprocessorProducer.this.mBitmapFactory);
            try {
                CloseableReference<CloseableImage> of = CloseableReference.of(new CloseableStaticBitmap(process, closeableImage.getQualityInfo(), closeableStaticBitmap.getRotationAngle()));
                return of;
            } finally {
                CloseableReference.closeSafely(process);
            }
        }

        private synchronized boolean setRunningIfDirtyAndNotRunning() {
            boolean z = true;
            synchronized (this) {
                if (this.mIsClosed || !this.mIsDirty || this.mIsPostProcessingRunning || !CloseableReference.isValid(this.mSourceImageRef)) {
                    z = false;
                } else {
                    this.mIsPostProcessingRunning = true;
                }
            }
            return z;
        }

        private boolean shouldPostprocess(CloseableImage closeableImage) {
            return closeableImage instanceof CloseableStaticBitmap;
        }

        private void submitPostprocessing() {
            PostprocessorProducer.this.mExecutor.execute(new C10662());
        }

        private void updateSourceImageRef(@Nullable CloseableReference<CloseableImage> closeableReference, boolean z) {
            synchronized (this) {
                if (this.mIsClosed) {
                    return;
                }
                CloseableReference closeableReference2 = this.mSourceImageRef;
                this.mSourceImageRef = CloseableReference.cloneOrNull((CloseableReference) closeableReference);
                this.mIsLast = z;
                this.mIsDirty = true;
                boolean runningIfDirtyAndNotRunning = setRunningIfDirtyAndNotRunning();
                CloseableReference.closeSafely(closeableReference2);
                if (runningIfDirtyAndNotRunning) {
                    submitPostprocessing();
                }
            }
        }

        protected void onCancellationImpl() {
            maybeNotifyOnCancellation();
        }

        protected void onFailureImpl(Throwable th) {
            maybeNotifyOnFailure(th);
        }

        protected void onNewResultImpl(CloseableReference<CloseableImage> closeableReference, boolean z) {
            if (CloseableReference.isValid(closeableReference)) {
                updateSourceImageRef(closeableReference, z);
            } else if (z) {
                maybeNotifyOnNewResult(null, true);
            }
        }
    }

    class RepeatedPostprocessorConsumer extends DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>> implements RepeatedPostprocessorRunner {
        @GuardedBy("RepeatedPostprocessorConsumer.this")
        private boolean mIsClosed;
        @GuardedBy("RepeatedPostprocessorConsumer.this")
        @Nullable
        private CloseableReference<CloseableImage> mSourceImageRef;

        /* renamed from: com.facebook.imagepipeline.producers.PostprocessorProducer.RepeatedPostprocessorConsumer.1 */
        class C10671 extends BaseProducerContextCallbacks {
            final /* synthetic */ PostprocessorProducer val$this$0;

            C10671(PostprocessorProducer postprocessorProducer) {
                this.val$this$0 = postprocessorProducer;
            }

            public void onCancellationRequested() {
                if (RepeatedPostprocessorConsumer.this.close()) {
                    RepeatedPostprocessorConsumer.this.getConsumer().onCancellation();
                }
            }
        }

        private RepeatedPostprocessorConsumer(PostprocessorConsumer postprocessorConsumer, RepeatedPostprocessor repeatedPostprocessor, ProducerContext producerContext) {
            super(postprocessorConsumer);
            this.mIsClosed = false;
            this.mSourceImageRef = null;
            repeatedPostprocessor.setCallback(this);
            producerContext.addCallbacks(new C10671(PostprocessorProducer.this));
        }

        private boolean close() {
            boolean z = true;
            synchronized (this) {
                if (this.mIsClosed) {
                    z = false;
                } else {
                    CloseableReference closeableReference = this.mSourceImageRef;
                    this.mSourceImageRef = null;
                    this.mIsClosed = true;
                    CloseableReference.closeSafely(closeableReference);
                }
            }
            return z;
        }

        private void setSourceImageRef(CloseableReference<CloseableImage> closeableReference) {
            synchronized (this) {
                if (this.mIsClosed) {
                    return;
                }
                CloseableReference closeableReference2 = this.mSourceImageRef;
                this.mSourceImageRef = CloseableReference.cloneOrNull((CloseableReference) closeableReference);
                CloseableReference.closeSafely(closeableReference2);
            }
        }

        private void updateInternal() {
            synchronized (this) {
                if (this.mIsClosed) {
                    return;
                }
                CloseableReference cloneOrNull = CloseableReference.cloneOrNull(this.mSourceImageRef);
                try {
                    getConsumer().onNewResult(cloneOrNull, false);
                } finally {
                    CloseableReference.closeSafely(cloneOrNull);
                }
            }
        }

        protected void onCancellationImpl() {
            if (close()) {
                getConsumer().onCancellation();
            }
        }

        protected void onFailureImpl(Throwable th) {
            if (close()) {
                getConsumer().onFailure(th);
            }
        }

        protected void onNewResultImpl(CloseableReference<CloseableImage> closeableReference, boolean z) {
            if (z) {
                setSourceImageRef(closeableReference);
                updateInternal();
            }
        }

        public synchronized void update() {
            updateInternal();
        }
    }

    class SingleUsePostprocessorConsumer extends DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>> {
        private SingleUsePostprocessorConsumer(PostprocessorConsumer postprocessorConsumer) {
            super(postprocessorConsumer);
        }

        protected void onNewResultImpl(CloseableReference<CloseableImage> closeableReference, boolean z) {
            if (z) {
                getConsumer().onNewResult(closeableReference, z);
            }
        }
    }

    public PostprocessorProducer(Producer<CloseableReference<CloseableImage>> producer, PlatformBitmapFactory platformBitmapFactory, Executor executor) {
        this.mInputProducer = (Producer) Preconditions.checkNotNull(producer);
        this.mBitmapFactory = platformBitmapFactory;
        this.mExecutor = (Executor) Preconditions.checkNotNull(executor);
    }

    public void produceResults(Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext) {
        Consumer repeatedPostprocessorConsumer;
        ProducerListener listener = producerContext.getListener();
        Postprocessor postprocessor = producerContext.getImageRequest().getPostprocessor();
        PostprocessorConsumer postprocessorConsumer = new PostprocessorConsumer(consumer, listener, producerContext.getId(), postprocessor, producerContext);
        if (postprocessor instanceof RepeatedPostprocessor) {
            repeatedPostprocessorConsumer = new RepeatedPostprocessorConsumer(postprocessorConsumer, (RepeatedPostprocessor) postprocessor, producerContext, null);
        } else {
            repeatedPostprocessorConsumer = new SingleUsePostprocessorConsumer(postprocessorConsumer, null);
        }
        this.mInputProducer.produceResults(repeatedPostprocessorConsumer, producerContext);
    }
}
