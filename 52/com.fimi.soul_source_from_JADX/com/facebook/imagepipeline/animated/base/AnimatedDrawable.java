package com.facebook.imagepipeline.animated.base;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.animation.LinearInterpolator;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.time.MonotonicClock;
import com.facebook.drawable.base.DrawableWithCaches;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.p017b.p018a.as;
import com.p017b.p018a.az;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AnimatedDrawable extends Drawable implements DrawableWithCaches, AnimatableDrawable {
    private static final int NO_FRAME = -1;
    private static final int POLL_FOR_RENDERED_FRAME_MS = 5;
    private static final Class<?> TAG;
    private static final long WATCH_DOG_TIMER_MIN_TIMEOUT_MS = 1000;
    private static final long WATCH_DOG_TIMER_POLL_INTERVAL_MS = 2000;
    private AnimatedDrawableCachingBackend mAnimatedDrawableBackend;
    private final AnimatedDrawableDiagnostics mAnimatedDrawableDiagnostics;
    private boolean mApplyTransformation;
    private final Rect mDstRect;
    private final int mDurationMs;
    private final int mFrameCount;
    private boolean mHaveWatchdogScheduled;
    private final Runnable mInvalidateTask;
    private boolean mInvalidateTaskScheduled;
    private boolean mIsRunning;
    private CloseableReference<Bitmap> mLastDrawnFrame;
    private int mLastDrawnFrameMonotonicNumber;
    private int mLastDrawnFrameNumber;
    private long mLastInvalidateTimeMs;
    private volatile String mLogId;
    private final MonotonicClock mMonotonicClock;
    private final Runnable mNextFrameTask;
    private long mNextFrameTaskMs;
    private final Paint mPaint;
    private int mPendingRenderedFrameMonotonicNumber;
    private int mPendingRenderedFrameNumber;
    private final ScheduledExecutorService mScheduledExecutorServiceForUiThread;
    private int mScheduledFrameMonotonicNumber;
    private int mScheduledFrameNumber;
    private final Runnable mStartTask;
    private long mStartTimeMs;
    private float mSx;
    private float mSy;
    private final int mTotalLoops;
    private final Paint mTransparentPaint;
    private boolean mWaitingForDraw;
    private final Runnable mWatchdogTask;

    /* renamed from: com.facebook.imagepipeline.animated.base.AnimatedDrawable.1 */
    class C09901 implements Runnable {
        C09901() {
        }

        public void run() {
            AnimatedDrawable.this.onStart();
        }
    }

    /* renamed from: com.facebook.imagepipeline.animated.base.AnimatedDrawable.2 */
    class C09912 implements Runnable {
        C09912() {
        }

        public void run() {
            FLog.m7618v(AnimatedDrawable.TAG, "(%s) Next Frame Task", AnimatedDrawable.this.mLogId);
            AnimatedDrawable.this.onNextFrame();
        }
    }

    /* renamed from: com.facebook.imagepipeline.animated.base.AnimatedDrawable.3 */
    class C09923 implements Runnable {
        C09923() {
        }

        public void run() {
            FLog.m7618v(AnimatedDrawable.TAG, "(%s) Invalidate Task", AnimatedDrawable.this.mLogId);
            AnimatedDrawable.this.mInvalidateTaskScheduled = false;
            AnimatedDrawable.this.doInvalidateSelf();
        }
    }

    /* renamed from: com.facebook.imagepipeline.animated.base.AnimatedDrawable.4 */
    class C09934 implements Runnable {
        C09934() {
        }

        public void run() {
            FLog.m7618v(AnimatedDrawable.TAG, "(%s) Watchdog Task", AnimatedDrawable.this.mLogId);
            AnimatedDrawable.this.doWatchdogCheck();
        }
    }

    /* renamed from: com.facebook.imagepipeline.animated.base.AnimatedDrawable.5 */
    class C09945 implements az {
        C09945() {
        }

        public void onAnimationUpdate(as asVar) {
            AnimatedDrawable.this.setLevel(((Integer) asVar.m5547u()).intValue());
        }
    }

    static {
        TAG = AnimatedDrawable.class;
    }

    public AnimatedDrawable(ScheduledExecutorService scheduledExecutorService, AnimatedDrawableCachingBackend animatedDrawableCachingBackend, AnimatedDrawableDiagnostics animatedDrawableDiagnostics, MonotonicClock monotonicClock) {
        this.mPaint = new Paint(6);
        this.mDstRect = new Rect();
        this.mLastDrawnFrameNumber = NO_FRAME;
        this.mLastDrawnFrameMonotonicNumber = NO_FRAME;
        this.mLastInvalidateTimeMs = -1;
        this.mSx = C2020f.f10933c;
        this.mSy = C2020f.f10933c;
        this.mNextFrameTaskMs = -1;
        this.mStartTask = new C09901();
        this.mNextFrameTask = new C09912();
        this.mInvalidateTask = new C09923();
        this.mWatchdogTask = new C09934();
        this.mScheduledExecutorServiceForUiThread = scheduledExecutorService;
        this.mAnimatedDrawableBackend = animatedDrawableCachingBackend;
        this.mAnimatedDrawableDiagnostics = animatedDrawableDiagnostics;
        this.mMonotonicClock = monotonicClock;
        this.mDurationMs = this.mAnimatedDrawableBackend.getDurationMs();
        this.mFrameCount = this.mAnimatedDrawableBackend.getFrameCount();
        this.mAnimatedDrawableDiagnostics.setBackend(this.mAnimatedDrawableBackend);
        this.mTotalLoops = this.mAnimatedDrawableBackend.getLoopCount();
        this.mTransparentPaint = new Paint();
        this.mTransparentPaint.setColor(0);
        this.mTransparentPaint.setStyle(Style.FILL);
        resetToPreviewFrame();
    }

    private void computeAndScheduleNextFrame(boolean z) {
        if (this.mDurationMs != 0) {
            long now = this.mMonotonicClock.now();
            int i = (int) ((now - this.mStartTimeMs) / ((long) this.mDurationMs));
            if (this.mTotalLoops <= 0 || i < this.mTotalLoops) {
                int i2 = (int) ((now - this.mStartTimeMs) % ((long) this.mDurationMs));
                int frameForTimestampMs = this.mAnimatedDrawableBackend.getFrameForTimestampMs(i2);
                Object obj = this.mScheduledFrameNumber != frameForTimestampMs ? 1 : null;
                this.mScheduledFrameNumber = frameForTimestampMs;
                this.mScheduledFrameMonotonicNumber = (i * this.mFrameCount) + frameForTimestampMs;
                if (!z) {
                    return;
                }
                if (obj != null) {
                    doInvalidateSelf();
                    return;
                }
                int timestampMsForFrame = (this.mAnimatedDrawableBackend.getTimestampMsForFrame(this.mScheduledFrameNumber) + this.mAnimatedDrawableBackend.getDurationMsForFrame(this.mScheduledFrameNumber)) - i2;
                i = (this.mScheduledFrameNumber + 1) % this.mFrameCount;
                now += (long) timestampMsForFrame;
                if (this.mNextFrameTaskMs == -1 || this.mNextFrameTaskMs > now) {
                    FLog.m7620v(TAG, "(%s) Next frame (%d) in %d ms", this.mLogId, Integer.valueOf(i), Integer.valueOf(timestampMsForFrame));
                    unscheduleSelf(this.mNextFrameTask);
                    scheduleSelf(this.mNextFrameTask, now);
                    this.mNextFrameTaskMs = now;
                }
            }
        }
    }

    private void doInvalidateSelf() {
        this.mWaitingForDraw = true;
        this.mLastInvalidateTimeMs = this.mMonotonicClock.now();
        invalidateSelf();
    }

    private void doWatchdogCheck() {
        boolean z = false;
        this.mHaveWatchdogScheduled = false;
        if (this.mIsRunning) {
            long now = this.mMonotonicClock.now();
            boolean z2 = this.mWaitingForDraw && now - this.mLastInvalidateTimeMs > WATCH_DOG_TIMER_MIN_TIMEOUT_MS;
            if (this.mNextFrameTaskMs != -1 && now - this.mNextFrameTaskMs > WATCH_DOG_TIMER_MIN_TIMEOUT_MS) {
                z = true;
            }
            if (z2 || r2) {
                dropCaches();
                doInvalidateSelf();
                return;
            }
            this.mScheduledExecutorServiceForUiThread.schedule(this.mWatchdogTask, WATCH_DOG_TIMER_POLL_INTERVAL_MS, TimeUnit.MILLISECONDS);
            this.mHaveWatchdogScheduled = true;
        }
    }

    private void onNextFrame() {
        this.mNextFrameTaskMs = -1;
        if (this.mIsRunning && this.mDurationMs != 0) {
            this.mAnimatedDrawableDiagnostics.onNextFrameMethodBegin();
            try {
                computeAndScheduleNextFrame(true);
            } finally {
                this.mAnimatedDrawableDiagnostics.onNextFrameMethodEnd();
            }
        }
    }

    private void onStart() {
        if (this.mIsRunning) {
            this.mAnimatedDrawableDiagnostics.onStartMethodBegin();
            try {
                this.mStartTimeMs = this.mMonotonicClock.now();
                this.mScheduledFrameNumber = 0;
                this.mScheduledFrameMonotonicNumber = 0;
                long durationMsForFrame = this.mStartTimeMs + ((long) this.mAnimatedDrawableBackend.getDurationMsForFrame(0));
                scheduleSelf(this.mNextFrameTask, durationMsForFrame);
                this.mNextFrameTaskMs = durationMsForFrame;
                doInvalidateSelf();
            } finally {
                this.mAnimatedDrawableDiagnostics.onStartMethodEnd();
            }
        }
    }

    private boolean renderFrame(Canvas canvas, int i, int i2) {
        CloseableReference bitmapForFrame = this.mAnimatedDrawableBackend.getBitmapForFrame(i);
        if (bitmapForFrame == null) {
            return false;
        }
        canvas.drawBitmap((Bitmap) bitmapForFrame.get(), 0.0f, 0.0f, this.mPaint);
        if (this.mLastDrawnFrame != null) {
            this.mLastDrawnFrame.close();
        }
        if (this.mIsRunning && i2 > this.mLastDrawnFrameMonotonicNumber) {
            int i3 = (i2 - this.mLastDrawnFrameMonotonicNumber) + NO_FRAME;
            this.mAnimatedDrawableDiagnostics.incrementDrawnFrames(1);
            this.mAnimatedDrawableDiagnostics.incrementDroppedFrames(i3);
            if (i3 > 0) {
                FLog.m7619v(TAG, "(%s) Dropped %d frames", this.mLogId, Integer.valueOf(i3));
            }
        }
        this.mLastDrawnFrame = bitmapForFrame;
        this.mLastDrawnFrameNumber = i;
        this.mLastDrawnFrameMonotonicNumber = i2;
        FLog.m7619v(TAG, "(%s) Drew frame %d", this.mLogId, Integer.valueOf(i));
        return true;
    }

    private void resetToPreviewFrame() {
        this.mScheduledFrameNumber = this.mAnimatedDrawableBackend.getFrameForPreview();
        this.mScheduledFrameMonotonicNumber = this.mScheduledFrameNumber;
        this.mPendingRenderedFrameNumber = NO_FRAME;
        this.mPendingRenderedFrameMonotonicNumber = NO_FRAME;
    }

    private void scheduleInvalidatePoll() {
        if (!this.mInvalidateTaskScheduled) {
            this.mInvalidateTaskScheduled = true;
            scheduleSelf(this.mInvalidateTask, 5);
        }
    }

    public az createAnimatorUpdateListener() {
        return new C09945();
    }

    public as createValueAnimator() {
        int loopCount = this.mAnimatedDrawableBackend.getLoopCount();
        as asVar = new as();
        asVar.m5526a(0, this.mDurationMs);
        asVar.m5537d((long) this.mDurationMs);
        if (loopCount == 0) {
            loopCount = NO_FRAME;
        }
        asVar.m5520a(loopCount);
        asVar.m5531b(1);
        asVar.m5522a(new LinearInterpolator());
        asVar.m5524a(createAnimatorUpdateListener());
        return asVar;
    }

    public as createValueAnimator(int i) {
        as createValueAnimator = createValueAnimator();
        createValueAnimator.m5520a(Math.max(i / this.mAnimatedDrawableBackend.getDurationMs(), 1));
        return createValueAnimator;
    }

    public boolean didLastDrawRender() {
        return this.mLastDrawnFrame != null;
    }

    public void draw(Canvas canvas) {
        int i = 0;
        this.mAnimatedDrawableDiagnostics.onDrawMethodBegin();
        try {
            this.mWaitingForDraw = false;
            if (this.mIsRunning && !this.mHaveWatchdogScheduled) {
                this.mScheduledExecutorServiceForUiThread.schedule(this.mWatchdogTask, WATCH_DOG_TIMER_POLL_INTERVAL_MS, TimeUnit.MILLISECONDS);
                this.mHaveWatchdogScheduled = true;
            }
            if (this.mApplyTransformation) {
                this.mDstRect.set(getBounds());
                if (!this.mDstRect.isEmpty()) {
                    AnimatedDrawableCachingBackend forNewBounds = this.mAnimatedDrawableBackend.forNewBounds(this.mDstRect);
                    if (forNewBounds != this.mAnimatedDrawableBackend) {
                        this.mAnimatedDrawableBackend.dropCaches();
                        this.mAnimatedDrawableBackend = forNewBounds;
                        this.mAnimatedDrawableDiagnostics.setBackend(forNewBounds);
                    }
                    this.mSx = ((float) this.mDstRect.width()) / ((float) this.mAnimatedDrawableBackend.getRenderedWidth());
                    this.mSy = ((float) this.mDstRect.height()) / ((float) this.mAnimatedDrawableBackend.getRenderedHeight());
                    this.mApplyTransformation = false;
                }
            }
            if (!this.mDstRect.isEmpty()) {
                boolean renderFrame;
                canvas.save();
                canvas.scale(this.mSx, this.mSy);
                if (this.mPendingRenderedFrameNumber != NO_FRAME) {
                    renderFrame = renderFrame(canvas, this.mPendingRenderedFrameNumber, this.mPendingRenderedFrameMonotonicNumber);
                    i = 0 | renderFrame;
                    if (renderFrame) {
                        FLog.m7619v(TAG, "(%s) Rendered pending frame %d", this.mLogId, Integer.valueOf(this.mPendingRenderedFrameNumber));
                        this.mPendingRenderedFrameNumber = NO_FRAME;
                        this.mPendingRenderedFrameMonotonicNumber = NO_FRAME;
                    } else {
                        FLog.m7619v(TAG, "(%s) Trying again later for pending %d", this.mLogId, Integer.valueOf(this.mPendingRenderedFrameNumber));
                        scheduleInvalidatePoll();
                    }
                }
                if (this.mPendingRenderedFrameNumber == NO_FRAME) {
                    if (this.mIsRunning) {
                        computeAndScheduleNextFrame(false);
                    }
                    renderFrame = renderFrame(canvas, this.mScheduledFrameNumber, this.mScheduledFrameMonotonicNumber);
                    i |= renderFrame;
                    if (renderFrame) {
                        FLog.m7619v(TAG, "(%s) Rendered current frame %d", this.mLogId, Integer.valueOf(this.mScheduledFrameNumber));
                        if (this.mIsRunning) {
                            computeAndScheduleNextFrame(true);
                        }
                    } else {
                        FLog.m7619v(TAG, "(%s) Trying again later for current %d", this.mLogId, Integer.valueOf(this.mScheduledFrameNumber));
                        this.mPendingRenderedFrameNumber = this.mScheduledFrameNumber;
                        this.mPendingRenderedFrameMonotonicNumber = this.mScheduledFrameMonotonicNumber;
                        scheduleInvalidatePoll();
                    }
                }
                if (i == 0 && this.mLastDrawnFrame != null) {
                    canvas.drawBitmap((Bitmap) this.mLastDrawnFrame.get(), 0.0f, 0.0f, this.mPaint);
                    FLog.m7619v(TAG, "(%s) Rendered last known frame %d", this.mLogId, Integer.valueOf(this.mLastDrawnFrameNumber));
                    i = 1;
                }
                if (i == 0) {
                    CloseableReference previewBitmap = this.mAnimatedDrawableBackend.getPreviewBitmap();
                    if (previewBitmap != null) {
                        canvas.drawBitmap((Bitmap) previewBitmap.get(), 0.0f, 0.0f, this.mPaint);
                        previewBitmap.close();
                        FLog.m7618v(TAG, "(%s) Rendered preview frame", this.mLogId);
                        i = 1;
                    }
                }
                if (i == 0) {
                    canvas.drawRect(0.0f, 0.0f, (float) this.mDstRect.width(), (float) this.mDstRect.height(), this.mTransparentPaint);
                    FLog.m7618v(TAG, "(%s) Failed to draw a frame", this.mLogId);
                }
                canvas.restore();
                this.mAnimatedDrawableDiagnostics.drawDebugOverlay(canvas, this.mDstRect);
                this.mAnimatedDrawableDiagnostics.onDrawMethodEnd();
            }
        } finally {
            this.mAnimatedDrawableDiagnostics.onDrawMethodEnd();
        }
    }

    public void dropCaches() {
        FLog.m7618v(TAG, "(%s) Dropping caches", this.mLogId);
        if (this.mLastDrawnFrame != null) {
            this.mLastDrawnFrame.close();
            this.mLastDrawnFrame = null;
            this.mLastDrawnFrameNumber = NO_FRAME;
            this.mLastDrawnFrameMonotonicNumber = NO_FRAME;
        }
        this.mAnimatedDrawableBackend.dropCaches();
    }

    protected void finalize() {
        super.finalize();
        if (this.mLastDrawnFrame != null) {
            this.mLastDrawnFrame.close();
            this.mLastDrawnFrame = null;
        }
    }

    public int getIntrinsicHeight() {
        return this.mAnimatedDrawableBackend.getHeight();
    }

    public int getIntrinsicWidth() {
        return this.mAnimatedDrawableBackend.getWidth();
    }

    public int getOpacity() {
        return -3;
    }

    @VisibleForTesting
    int getScheduledFrameNumber() {
        return this.mScheduledFrameNumber;
    }

    public boolean isRunning() {
        return this.mIsRunning;
    }

    @VisibleForTesting
    boolean isWaitingForDraw() {
        return this.mWaitingForDraw;
    }

    @VisibleForTesting
    boolean isWaitingForNextFrame() {
        return this.mNextFrameTaskMs != -1;
    }

    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.mApplyTransformation = true;
        if (this.mLastDrawnFrame != null) {
            this.mLastDrawnFrame.close();
            this.mLastDrawnFrame = null;
        }
        this.mLastDrawnFrameNumber = NO_FRAME;
        this.mLastDrawnFrameMonotonicNumber = NO_FRAME;
        this.mAnimatedDrawableBackend.dropCaches();
    }

    protected boolean onLevelChange(int i) {
        if (this.mIsRunning) {
            return false;
        }
        int frameForTimestampMs = this.mAnimatedDrawableBackend.getFrameForTimestampMs(i);
        if (frameForTimestampMs == this.mScheduledFrameNumber) {
            return false;
        }
        try {
            this.mScheduledFrameNumber = frameForTimestampMs;
            this.mScheduledFrameMonotonicNumber = frameForTimestampMs;
            doInvalidateSelf();
            return true;
        } catch (IllegalStateException e) {
            return false;
        }
    }

    public void setAlpha(int i) {
        this.mPaint.setAlpha(i);
        doInvalidateSelf();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
        doInvalidateSelf();
    }

    public void setLogId(String str) {
        this.mLogId = str;
    }

    public void start() {
        if (this.mDurationMs != 0 && this.mFrameCount > 1) {
            this.mIsRunning = true;
            scheduleSelf(this.mStartTask, this.mMonotonicClock.now());
        }
    }

    public void stop() {
        this.mIsRunning = false;
    }
}
