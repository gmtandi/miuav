package com.facebook.drawee.controller;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.components.DeferredReleaser;
import com.facebook.drawee.components.DeferredReleaser.Releasable;
import com.facebook.drawee.components.DraweeEventTracker;
import com.facebook.drawee.components.DraweeEventTracker.Event;
import com.facebook.drawee.components.RetryManager;
import com.facebook.drawee.gestures.GestureDetector;
import com.facebook.drawee.gestures.GestureDetector.ClickListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.interfaces.DraweeHierarchy;
import com.facebook.drawee.interfaces.SettableDraweeHierarchy;
import com.fimi.soul.view.photodraweeview.C2020f;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import org.p122a.p123a.C2915a;

@NotThreadSafe
public abstract class AbstractDraweeController<T, INFO> implements Releasable, ClickListener, DraweeController {
    private static final Class<?> TAG;
    private Object mCallerContext;
    @Nullable
    private ControllerListener<INFO> mControllerListener;
    @Nullable
    private Drawable mControllerOverlay;
    @Nullable
    private DataSource<T> mDataSource;
    private final DeferredReleaser mDeferredReleaser;
    @Nullable
    private Drawable mDrawable;
    private final DraweeEventTracker mEventTracker;
    @Nullable
    private T mFetchedImage;
    @Nullable
    private GestureDetector mGestureDetector;
    private boolean mHasFetchFailed;
    private String mId;
    private boolean mIsAttached;
    private boolean mIsRequestSubmitted;
    private boolean mRetainImageOnFailure;
    @Nullable
    private RetryManager mRetryManager;
    @Nullable
    private SettableDraweeHierarchy mSettableDraweeHierarchy;
    private final Executor mUiThreadImmediateExecutor;

    /* renamed from: com.facebook.drawee.controller.AbstractDraweeController.1 */
    class C09831 extends BaseDataSubscriber<T> {
        final /* synthetic */ String val$id;
        final /* synthetic */ boolean val$wasImmediate;

        C09831(String str, boolean z) {
            this.val$id = str;
            this.val$wasImmediate = z;
        }

        public void onFailureImpl(DataSource<T> dataSource) {
            AbstractDraweeController.this.onFailureInternal(this.val$id, dataSource, dataSource.getFailureCause(), true);
        }

        public void onNewResultImpl(DataSource<T> dataSource) {
            boolean isFinished = dataSource.isFinished();
            float progress = dataSource.getProgress();
            Object result = dataSource.getResult();
            if (result != null) {
                AbstractDraweeController.this.onNewResultInternal(this.val$id, dataSource, result, progress, isFinished, this.val$wasImmediate);
            } else if (isFinished) {
                AbstractDraweeController.this.onFailureInternal(this.val$id, dataSource, new NullPointerException(), true);
            }
        }

        public void onProgressUpdate(DataSource<T> dataSource) {
            boolean isFinished = dataSource.isFinished();
            AbstractDraweeController.this.onProgressUpdateInternal(this.val$id, dataSource, dataSource.getProgress(), isFinished);
        }
    }

    class InternalForwardingListener<INFO> extends ForwardingControllerListener<INFO> {
        private InternalForwardingListener() {
        }

        public static <INFO> InternalForwardingListener<INFO> createInternal(ControllerListener<? super INFO> controllerListener, ControllerListener<? super INFO> controllerListener2) {
            InternalForwardingListener<INFO> internalForwardingListener = new InternalForwardingListener();
            internalForwardingListener.addListener(controllerListener);
            internalForwardingListener.addListener(controllerListener2);
            return internalForwardingListener;
        }
    }

    static {
        TAG = AbstractDraweeController.class;
    }

    public AbstractDraweeController(DeferredReleaser deferredReleaser, Executor executor, String str, Object obj) {
        this.mEventTracker = new DraweeEventTracker();
        this.mDeferredReleaser = deferredReleaser;
        this.mUiThreadImmediateExecutor = executor;
        init(str, obj);
    }

    private void init(String str, Object obj) {
        this.mEventTracker.recordEvent(Event.ON_INIT_CONTROLLER);
        if (this.mDeferredReleaser != null) {
            this.mDeferredReleaser.cancelDeferredRelease(this);
        }
        this.mIsAttached = false;
        releaseFetch();
        this.mRetainImageOnFailure = false;
        if (this.mRetryManager != null) {
            this.mRetryManager.init();
        }
        if (this.mGestureDetector != null) {
            this.mGestureDetector.init();
            this.mGestureDetector.setClickListener(this);
        }
        if (this.mControllerListener instanceof InternalForwardingListener) {
            ((InternalForwardingListener) this.mControllerListener).clearListeners();
        } else {
            this.mControllerListener = null;
        }
        if (this.mSettableDraweeHierarchy != null) {
            this.mSettableDraweeHierarchy.reset();
            this.mSettableDraweeHierarchy.setControllerOverlay(null);
            this.mSettableDraweeHierarchy = null;
        }
        this.mControllerOverlay = null;
        if (FLog.isLoggable(2)) {
            FLog.m7620v(TAG, "controller %x %s -> %s: initialize", Integer.valueOf(System.identityHashCode(this)), this.mId, (Object) str);
        }
        this.mId = str;
        this.mCallerContext = obj;
    }

    private boolean isExpectedDataSource(String str, DataSource<T> dataSource) {
        return str.equals(this.mId) && dataSource == this.mDataSource && this.mIsRequestSubmitted;
    }

    private void logMessageAndFailure(String str, Throwable th) {
        if (FLog.isLoggable(2)) {
            FLog.m7621v(TAG, "controller %x %s: %s: failure: %s", Integer.valueOf(System.identityHashCode(this)), this.mId, (Object) str, (Object) th);
        }
    }

    private void logMessageAndImage(String str, T t) {
        if (FLog.isLoggable(2)) {
            FLog.m7623v(TAG, "controller %x %s: %s: image: %s %x", Integer.valueOf(System.identityHashCode(this)), this.mId, str, getImageClass(t), Integer.valueOf(getImageHash(t)));
        }
    }

    private void onFailureInternal(String str, DataSource<T> dataSource, Throwable th, boolean z) {
        if (isExpectedDataSource(str, dataSource)) {
            this.mEventTracker.recordEvent(z ? Event.ON_DATASOURCE_FAILURE : Event.ON_DATASOURCE_FAILURE_INT);
            if (z) {
                logMessageAndFailure("final_failed @ onFailure", th);
                this.mDataSource = null;
                this.mHasFetchFailed = true;
                if (this.mRetainImageOnFailure && this.mDrawable != null) {
                    this.mSettableDraweeHierarchy.setImage(this.mDrawable, C2020f.f10933c, true);
                } else if (shouldRetryOnTap()) {
                    this.mSettableDraweeHierarchy.setRetry(th);
                } else {
                    this.mSettableDraweeHierarchy.setFailure(th);
                }
                getControllerListener().onFailure(this.mId, th);
                return;
            }
            logMessageAndFailure("intermediate_failed @ onFailure", th);
            getControllerListener().onIntermediateImageFailed(this.mId, th);
            return;
        }
        logMessageAndFailure("ignore_old_datasource @ onFailure", th);
        dataSource.close();
    }

    private void onNewResultInternal(String str, DataSource<T> dataSource, @Nullable T t, float f, boolean z, boolean z2) {
        if (isExpectedDataSource(str, dataSource)) {
            this.mEventTracker.recordEvent(z ? Event.ON_DATASOURCE_RESULT : Event.ON_DATASOURCE_RESULT_INT);
            try {
                Drawable createDrawable = createDrawable(t);
                T t2 = this.mFetchedImage;
                Drawable drawable = this.mDrawable;
                this.mFetchedImage = t;
                this.mDrawable = createDrawable;
                if (z) {
                    try {
                        logMessageAndImage("set_final_result @ onNewResult", t);
                        this.mDataSource = null;
                        this.mSettableDraweeHierarchy.setImage(createDrawable, C2020f.f10933c, z2);
                        getControllerListener().onFinalImageSet(str, getImageInfo(t), getAnimatable());
                    } catch (Throwable th) {
                        if (!(drawable == null || drawable == createDrawable)) {
                            releaseDrawable(drawable);
                        }
                        if (!(t2 == null || t2 == t)) {
                            logMessageAndImage("release_previous_result @ onNewResult", t2);
                            releaseImage(t2);
                        }
                    }
                } else {
                    logMessageAndImage("set_intermediate_result @ onNewResult", t);
                    this.mSettableDraweeHierarchy.setImage(createDrawable, f, z2);
                    getControllerListener().onIntermediateImageSet(str, getImageInfo(t));
                }
                if (!(drawable == null || drawable == createDrawable)) {
                    releaseDrawable(drawable);
                }
                if (t2 != null && t2 != t) {
                    logMessageAndImage("release_previous_result @ onNewResult", t2);
                    releaseImage(t2);
                    return;
                }
                return;
            } catch (Throwable e) {
                logMessageAndImage("drawable_failed @ onNewResult", t);
                releaseImage(t);
                onFailureInternal(str, dataSource, e, z);
                return;
            }
        }
        logMessageAndImage("ignore_old_datasource @ onNewResult", t);
        releaseImage(t);
        dataSource.close();
    }

    private void onProgressUpdateInternal(String str, DataSource<T> dataSource, float f, boolean z) {
        if (!isExpectedDataSource(str, dataSource)) {
            logMessageAndFailure("ignore_old_datasource @ onProgress", null);
            dataSource.close();
        } else if (!z) {
            this.mSettableDraweeHierarchy.setProgress(f, false);
        }
    }

    private void releaseFetch() {
        boolean z = this.mIsRequestSubmitted;
        this.mIsRequestSubmitted = false;
        this.mHasFetchFailed = false;
        if (this.mDataSource != null) {
            this.mDataSource.close();
            this.mDataSource = null;
        }
        if (this.mDrawable != null) {
            releaseDrawable(this.mDrawable);
        }
        this.mDrawable = null;
        if (this.mFetchedImage != null) {
            logMessageAndImage(C2915a.f14757c, this.mFetchedImage);
            releaseImage(this.mFetchedImage);
            this.mFetchedImage = null;
        }
        if (z) {
            getControllerListener().onRelease(this.mId);
        }
    }

    private boolean shouldRetryOnTap() {
        return this.mHasFetchFailed && this.mRetryManager != null && this.mRetryManager.shouldRetryOnTap();
    }

    public void addControllerListener(ControllerListener<? super INFO> controllerListener) {
        Preconditions.checkNotNull(controllerListener);
        if (this.mControllerListener instanceof InternalForwardingListener) {
            ((InternalForwardingListener) this.mControllerListener).addListener(controllerListener);
        } else if (this.mControllerListener != null) {
            this.mControllerListener = InternalForwardingListener.createInternal(this.mControllerListener, controllerListener);
        } else {
            this.mControllerListener = controllerListener;
        }
    }

    protected abstract Drawable createDrawable(T t);

    @Nullable
    public Animatable getAnimatable() {
        return this.mDrawable instanceof Animatable ? (Animatable) this.mDrawable : null;
    }

    public Object getCallerContext() {
        return this.mCallerContext;
    }

    protected ControllerListener<INFO> getControllerListener() {
        return this.mControllerListener == null ? BaseControllerListener.getNoOpListener() : this.mControllerListener;
    }

    @Nullable
    protected Drawable getControllerOverlay() {
        return this.mControllerOverlay;
    }

    protected abstract DataSource<T> getDataSource();

    @Nullable
    protected GestureDetector getGestureDetector() {
        return this.mGestureDetector;
    }

    @Nullable
    public DraweeHierarchy getHierarchy() {
        return this.mSettableDraweeHierarchy;
    }

    public String getId() {
        return this.mId;
    }

    protected String getImageClass(@Nullable T t) {
        return t != null ? t.getClass().getSimpleName() : "<null>";
    }

    protected int getImageHash(@Nullable T t) {
        return System.identityHashCode(t);
    }

    @Nullable
    protected abstract INFO getImageInfo(T t);

    @Nullable
    protected RetryManager getRetryManager() {
        return this.mRetryManager;
    }

    protected void initialize(String str, Object obj) {
        init(str, obj);
    }

    public void onAttach() {
        if (FLog.isLoggable(2)) {
            FLog.m7620v(TAG, "controller %x %s: onAttach: %s", Integer.valueOf(System.identityHashCode(this)), this.mId, this.mIsRequestSubmitted ? "request already submitted" : "request needs submit");
        }
        this.mEventTracker.recordEvent(Event.ON_ATTACH_CONTROLLER);
        Preconditions.checkNotNull(this.mSettableDraweeHierarchy);
        this.mDeferredReleaser.cancelDeferredRelease(this);
        this.mIsAttached = true;
        if (!this.mIsRequestSubmitted) {
            submitRequest();
        }
    }

    public boolean onClick() {
        if (FLog.isLoggable(2)) {
            FLog.m7619v(TAG, "controller %x %s: onClick", Integer.valueOf(System.identityHashCode(this)), this.mId);
        }
        if (!shouldRetryOnTap()) {
            return false;
        }
        this.mRetryManager.notifyTapToRetry();
        this.mSettableDraweeHierarchy.reset();
        submitRequest();
        return true;
    }

    public void onDetach() {
        if (FLog.isLoggable(2)) {
            FLog.m7619v(TAG, "controller %x %s: onDetach", Integer.valueOf(System.identityHashCode(this)), this.mId);
        }
        this.mEventTracker.recordEvent(Event.ON_DETACH_CONTROLLER);
        this.mIsAttached = false;
        this.mDeferredReleaser.scheduleDeferredRelease(this);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (FLog.isLoggable(2)) {
            FLog.m7620v(TAG, "controller %x %s: onTouchEvent %s", Integer.valueOf(System.identityHashCode(this)), this.mId, (Object) motionEvent);
        }
        if (this.mGestureDetector == null) {
            return false;
        }
        if (!this.mGestureDetector.isCapturingGesture() && !shouldHandleGesture()) {
            return false;
        }
        this.mGestureDetector.onTouchEvent(motionEvent);
        return true;
    }

    public void release() {
        this.mEventTracker.recordEvent(Event.ON_RELEASE_CONTROLLER);
        if (this.mRetryManager != null) {
            this.mRetryManager.reset();
        }
        if (this.mGestureDetector != null) {
            this.mGestureDetector.reset();
        }
        if (this.mSettableDraweeHierarchy != null) {
            this.mSettableDraweeHierarchy.reset();
        }
        releaseFetch();
    }

    protected abstract void releaseDrawable(@Nullable Drawable drawable);

    protected abstract void releaseImage(@Nullable T t);

    public void removeControllerListener(ControllerListener<? super INFO> controllerListener) {
        Preconditions.checkNotNull(controllerListener);
        if (this.mControllerListener instanceof InternalForwardingListener) {
            ((InternalForwardingListener) this.mControllerListener).removeListener(controllerListener);
        } else if (this.mControllerListener == controllerListener) {
            this.mControllerListener = null;
        }
    }

    protected void setControllerOverlay(@Nullable Drawable drawable) {
        this.mControllerOverlay = drawable;
        if (this.mSettableDraweeHierarchy != null) {
            this.mSettableDraweeHierarchy.setControllerOverlay(this.mControllerOverlay);
        }
    }

    protected void setGestureDetector(@Nullable GestureDetector gestureDetector) {
        this.mGestureDetector = gestureDetector;
        if (this.mGestureDetector != null) {
            this.mGestureDetector.setClickListener(this);
        }
    }

    public void setHierarchy(@Nullable DraweeHierarchy draweeHierarchy) {
        if (FLog.isLoggable(2)) {
            FLog.m7620v(TAG, "controller %x %s: setHierarchy: %s", Integer.valueOf(System.identityHashCode(this)), this.mId, (Object) draweeHierarchy);
        }
        this.mEventTracker.recordEvent(draweeHierarchy != null ? Event.ON_SET_HIERARCHY : Event.ON_CLEAR_HIERARCHY);
        if (this.mIsRequestSubmitted) {
            this.mDeferredReleaser.cancelDeferredRelease(this);
            release();
        }
        if (this.mSettableDraweeHierarchy != null) {
            this.mSettableDraweeHierarchy.setControllerOverlay(null);
            this.mSettableDraweeHierarchy = null;
        }
        if (draweeHierarchy != null) {
            Preconditions.checkArgument(draweeHierarchy instanceof SettableDraweeHierarchy);
            this.mSettableDraweeHierarchy = (SettableDraweeHierarchy) draweeHierarchy;
            this.mSettableDraweeHierarchy.setControllerOverlay(this.mControllerOverlay);
        }
    }

    protected void setRetainImageOnFailure(boolean z) {
        this.mRetainImageOnFailure = z;
    }

    protected void setRetryManager(@Nullable RetryManager retryManager) {
        this.mRetryManager = retryManager;
    }

    protected boolean shouldHandleGesture() {
        return shouldRetryOnTap();
    }

    protected void submitRequest() {
        this.mEventTracker.recordEvent(Event.ON_DATASOURCE_SUBMIT);
        getControllerListener().onSubmit(this.mId, this.mCallerContext);
        this.mSettableDraweeHierarchy.setProgress(0.0f, true);
        this.mIsRequestSubmitted = true;
        this.mHasFetchFailed = false;
        this.mDataSource = getDataSource();
        if (FLog.isLoggable(2)) {
            FLog.m7620v(TAG, "controller %x %s: submitRequest: dataSource: %x", Integer.valueOf(System.identityHashCode(this)), this.mId, Integer.valueOf(System.identityHashCode(this.mDataSource)));
        }
        this.mDataSource.subscribe(new C09831(this.mId, this.mDataSource.hasResult()), this.mUiThreadImmediateExecutor);
    }

    public String toString() {
        return Objects.toStringHelper((Object) this).add("isAttached", this.mIsAttached).add("isRequestSubmitted", this.mIsRequestSubmitted).add("hasFetchFailed", this.mHasFetchFailed).add("fetchedImage", getImageHash(this.mFetchedImage)).add("events", this.mEventTracker.toString()).toString();
    }
}
