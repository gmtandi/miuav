package android.support.v4.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Build.VERSION;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.AbsListView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.GroundOverlayOptions;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

public class SwipeRefreshLayout extends ViewGroup {
    private static final float ACCELERATE_INTERPOLATION_FACTOR = 1.5f;
    private static final float DECELERATE_INTERPOLATION_FACTOR = 2.0f;
    private static final int INVALID_POINTER = -1;
    private static final int[] LAYOUT_ATTRS;
    private static final String LOG_TAG;
    private static final float MAX_SWIPE_DISTANCE_FACTOR = 0.6f;
    private static final float PROGRESS_BAR_HEIGHT = 4.0f;
    private static final int REFRESH_TRIGGER_DISTANCE = 120;
    private static final long RETURN_TO_ORIGINAL_POSITION_TIMEOUT = 300;
    private final AccelerateInterpolator mAccelerateInterpolator;
    private int mActivePointerId;
    private final Animation mAnimateToStartPosition;
    private final Runnable mCancel;
    private float mCurrPercentage;
    private int mCurrentTargetOffsetTop;
    private final DecelerateInterpolator mDecelerateInterpolator;
    private float mDistanceToTriggerSync;
    private int mFrom;
    private float mFromPercentage;
    private float mInitialMotionY;
    private boolean mIsBeingDragged;
    private float mLastMotionY;
    private OnRefreshListener mListener;
    private int mMediumAnimationDuration;
    private int mOriginalOffsetTop;
    private SwipeProgressBar mProgressBar;
    private int mProgressBarHeight;
    private boolean mRefreshing;
    private final Runnable mReturnToStartPosition;
    private final AnimationListener mReturnToStartPositionListener;
    private boolean mReturningToStart;
    private final AnimationListener mShrinkAnimationListener;
    private Animation mShrinkTrigger;
    private View mTarget;
    private int mTouchSlop;

    /* renamed from: android.support.v4.widget.SwipeRefreshLayout.1 */
    class C01071 extends Animation {
        C01071() {
        }

        public void applyTransformation(float f, Transformation transformation) {
            int i = 0;
            if (SwipeRefreshLayout.this.mFrom != SwipeRefreshLayout.this.mOriginalOffsetTop) {
                i = SwipeRefreshLayout.this.mFrom + ((int) (((float) (SwipeRefreshLayout.this.mOriginalOffsetTop - SwipeRefreshLayout.this.mFrom)) * f));
            }
            i -= SwipeRefreshLayout.this.mTarget.getTop();
            int top = SwipeRefreshLayout.this.mTarget.getTop();
            if (i + top < 0) {
                i = 0 - top;
            }
            SwipeRefreshLayout.this.setTargetOffsetTopAndBottom(i);
        }
    }

    /* renamed from: android.support.v4.widget.SwipeRefreshLayout.2 */
    class C01082 extends Animation {
        C01082() {
        }

        public void applyTransformation(float f, Transformation transformation) {
            SwipeRefreshLayout.this.mProgressBar.setTriggerPercentage(SwipeRefreshLayout.this.mFromPercentage + ((0.0f - SwipeRefreshLayout.this.mFromPercentage) * f));
        }
    }

    class BaseAnimationListener implements AnimationListener {
        private BaseAnimationListener() {
        }

        public void onAnimationEnd(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }
    }

    /* renamed from: android.support.v4.widget.SwipeRefreshLayout.3 */
    class C01093 extends BaseAnimationListener {
        C01093() {
            super(null);
        }

        public void onAnimationEnd(Animation animation) {
            SwipeRefreshLayout.this.mCurrentTargetOffsetTop = 0;
        }
    }

    /* renamed from: android.support.v4.widget.SwipeRefreshLayout.4 */
    class C01104 extends BaseAnimationListener {
        C01104() {
            super(null);
        }

        public void onAnimationEnd(Animation animation) {
            SwipeRefreshLayout.this.mCurrPercentage = 0.0f;
        }
    }

    /* renamed from: android.support.v4.widget.SwipeRefreshLayout.5 */
    class C01115 implements Runnable {
        C01115() {
        }

        public void run() {
            SwipeRefreshLayout.this.mReturningToStart = true;
            SwipeRefreshLayout.this.animateOffsetToStartPosition(SwipeRefreshLayout.this.mCurrentTargetOffsetTop + SwipeRefreshLayout.this.getPaddingTop(), SwipeRefreshLayout.this.mReturnToStartPositionListener);
        }
    }

    /* renamed from: android.support.v4.widget.SwipeRefreshLayout.6 */
    class C01126 implements Runnable {
        C01126() {
        }

        public void run() {
            SwipeRefreshLayout.this.mReturningToStart = true;
            if (SwipeRefreshLayout.this.mProgressBar != null) {
                SwipeRefreshLayout.this.mFromPercentage = SwipeRefreshLayout.this.mCurrPercentage;
                SwipeRefreshLayout.this.mShrinkTrigger.setDuration((long) SwipeRefreshLayout.this.mMediumAnimationDuration);
                SwipeRefreshLayout.this.mShrinkTrigger.setAnimationListener(SwipeRefreshLayout.this.mShrinkAnimationListener);
                SwipeRefreshLayout.this.mShrinkTrigger.reset();
                SwipeRefreshLayout.this.mShrinkTrigger.setInterpolator(SwipeRefreshLayout.this.mDecelerateInterpolator);
                SwipeRefreshLayout.this.startAnimation(SwipeRefreshLayout.this.mShrinkTrigger);
            }
            SwipeRefreshLayout.this.animateOffsetToStartPosition(SwipeRefreshLayout.this.mCurrentTargetOffsetTop + SwipeRefreshLayout.this.getPaddingTop(), SwipeRefreshLayout.this.mReturnToStartPositionListener);
        }
    }

    public interface OnRefreshListener {
        void onRefresh();
    }

    static {
        LOG_TAG = SwipeRefreshLayout.class.getSimpleName();
        LAYOUT_ATTRS = new int[]{16842766};
    }

    public SwipeRefreshLayout(Context context) {
        this(context, null);
    }

    public SwipeRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mRefreshing = false;
        this.mDistanceToTriggerSync = GroundOverlayOptions.NO_DIMENSION;
        this.mFromPercentage = 0.0f;
        this.mCurrPercentage = 0.0f;
        this.mActivePointerId = INVALID_POINTER;
        this.mAnimateToStartPosition = new C01071();
        this.mShrinkTrigger = new C01082();
        this.mReturnToStartPositionListener = new C01093();
        this.mShrinkAnimationListener = new C01104();
        this.mReturnToStartPosition = new C01115();
        this.mCancel = new C01126();
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mMediumAnimationDuration = getResources().getInteger(17694721);
        setWillNotDraw(false);
        this.mProgressBar = new SwipeProgressBar(this);
        this.mProgressBarHeight = (int) (getResources().getDisplayMetrics().density * PROGRESS_BAR_HEIGHT);
        this.mDecelerateInterpolator = new DecelerateInterpolator(DECELERATE_INTERPOLATION_FACTOR);
        this.mAccelerateInterpolator = new AccelerateInterpolator(ACCELERATE_INTERPOLATION_FACTOR);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, LAYOUT_ATTRS);
        setEnabled(obtainStyledAttributes.getBoolean(0, true));
        obtainStyledAttributes.recycle();
    }

    private void animateOffsetToStartPosition(int i, AnimationListener animationListener) {
        this.mFrom = i;
        this.mAnimateToStartPosition.reset();
        this.mAnimateToStartPosition.setDuration((long) this.mMediumAnimationDuration);
        this.mAnimateToStartPosition.setAnimationListener(animationListener);
        this.mAnimateToStartPosition.setInterpolator(this.mDecelerateInterpolator);
        this.mTarget.startAnimation(this.mAnimateToStartPosition);
    }

    private void ensureTarget() {
        if (this.mTarget == null) {
            if (getChildCount() <= 1 || isInEditMode()) {
                this.mTarget = getChildAt(0);
                this.mOriginalOffsetTop = this.mTarget.getTop() + getPaddingTop();
            } else {
                throw new IllegalStateException("SwipeRefreshLayout can host only one direct child");
            }
        }
        if (this.mDistanceToTriggerSync == GroundOverlayOptions.NO_DIMENSION && getParent() != null && ((View) getParent()).getHeight() > 0) {
            this.mDistanceToTriggerSync = (float) ((int) Math.min(((float) ((View) getParent()).getHeight()) * MAX_SWIPE_DISTANCE_FACTOR, getResources().getDisplayMetrics().density * BitmapDescriptorFactory.HUE_GREEN));
        }
    }

    private void onSecondaryPointerUp(MotionEvent motionEvent) {
        int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (MotionEventCompat.getPointerId(motionEvent, actionIndex) == this.mActivePointerId) {
            actionIndex = actionIndex == 0 ? 1 : 0;
            this.mLastMotionY = MotionEventCompat.getY(motionEvent, actionIndex);
            this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, actionIndex);
        }
    }

    private void setTargetOffsetTopAndBottom(int i) {
        this.mTarget.offsetTopAndBottom(i);
        this.mCurrentTargetOffsetTop = this.mTarget.getTop();
    }

    private void setTriggerPercentage(float f) {
        if (f == 0.0f) {
            this.mCurrPercentage = 0.0f;
            return;
        }
        this.mCurrPercentage = f;
        this.mProgressBar.setTriggerPercentage(f);
    }

    private void startRefresh() {
        removeCallbacks(this.mCancel);
        this.mReturnToStartPosition.run();
        setRefreshing(true);
        this.mListener.onRefresh();
    }

    private void updateContentOffsetTop(int i) {
        int top = this.mTarget.getTop();
        if (((float) i) > this.mDistanceToTriggerSync) {
            i = (int) this.mDistanceToTriggerSync;
        } else if (i < 0) {
            i = 0;
        }
        setTargetOffsetTopAndBottom(i - top);
    }

    private void updatePositionTimeout() {
        removeCallbacks(this.mCancel);
        postDelayed(this.mCancel, RETURN_TO_ORIGINAL_POSITION_TIMEOUT);
    }

    public boolean canChildScrollUp() {
        boolean z = true;
        if (VERSION.SDK_INT >= 14) {
            return ViewCompat.canScrollVertically(this.mTarget, INVALID_POINTER);
        }
        if (this.mTarget instanceof AbsListView) {
            AbsListView absListView = (AbsListView) this.mTarget;
            return absListView.getChildCount() > 0 && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0).getTop() < absListView.getPaddingTop());
        } else {
            if (this.mTarget.getScrollY() <= 0) {
                z = false;
            }
            return z;
        }
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        this.mProgressBar.draw(canvas);
    }

    public boolean isRefreshing() {
        return this.mRefreshing;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        removeCallbacks(this.mCancel);
        removeCallbacks(this.mReturnToStartPosition);
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.mReturnToStartPosition);
        removeCallbacks(this.mCancel);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        ensureTarget();
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (this.mReturningToStart && actionMasked == 0) {
            this.mReturningToStart = false;
        }
        if (!isEnabled() || this.mReturningToStart || canChildScrollUp()) {
            return false;
        }
        switch (actionMasked) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                float y = motionEvent.getY();
                this.mInitialMotionY = y;
                this.mLastMotionY = y;
                this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                this.mIsBeingDragged = false;
                this.mCurrPercentage = 0.0f;
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
            case Type.BYTE /*3*/:
                this.mIsBeingDragged = false;
                this.mCurrPercentage = 0.0f;
                this.mActivePointerId = INVALID_POINTER;
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                if (this.mActivePointerId != INVALID_POINTER) {
                    actionMasked = MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId);
                    if (actionMasked >= 0) {
                        float y2 = MotionEventCompat.getY(motionEvent, actionMasked);
                        if (y2 - this.mInitialMotionY > ((float) this.mTouchSlop)) {
                            this.mLastMotionY = y2;
                            this.mIsBeingDragged = true;
                            break;
                        }
                    }
                    Log.e(LOG_TAG, "Got ACTION_MOVE event but have an invalid active pointer id.");
                    return false;
                }
                Log.e(LOG_TAG, "Got ACTION_MOVE event but don't have an active pointer id.");
                return false;
                break;
            case Type.FLOAT /*6*/:
                onSecondaryPointerUp(motionEvent);
                break;
        }
        return this.mIsBeingDragged;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        this.mProgressBar.setBounds(0, 0, measuredWidth, this.mProgressBarHeight);
        if (getChildCount() != 0) {
            View childAt = getChildAt(0);
            int paddingLeft = getPaddingLeft();
            int paddingTop = this.mCurrentTargetOffsetTop + getPaddingTop();
            childAt.layout(paddingLeft, paddingTop, ((measuredWidth - getPaddingLeft()) - getPaddingRight()) + paddingLeft, ((measuredHeight - getPaddingTop()) - getPaddingBottom()) + paddingTop);
        }
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (getChildCount() > 1 && !isInEditMode()) {
            throw new IllegalStateException("SwipeRefreshLayout can host only one direct child");
        } else if (getChildCount() > 0) {
            getChildAt(0).measure(MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824), MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), 1073741824));
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (this.mReturningToStart && actionMasked == 0) {
            this.mReturningToStart = false;
        }
        if (!isEnabled() || this.mReturningToStart || canChildScrollUp()) {
            return false;
        }
        float y;
        switch (actionMasked) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                y = motionEvent.getY();
                this.mInitialMotionY = y;
                this.mLastMotionY = y;
                this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                this.mIsBeingDragged = false;
                this.mCurrPercentage = 0.0f;
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
            case Type.BYTE /*3*/:
                this.mIsBeingDragged = false;
                this.mCurrPercentage = 0.0f;
                this.mActivePointerId = INVALID_POINTER;
                return false;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                actionMasked = MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId);
                if (actionMasked >= 0) {
                    float y2 = MotionEventCompat.getY(motionEvent, actionMasked);
                    y = y2 - this.mInitialMotionY;
                    if (!this.mIsBeingDragged && y > ((float) this.mTouchSlop)) {
                        this.mIsBeingDragged = true;
                    }
                    if (this.mIsBeingDragged) {
                        if (y > this.mDistanceToTriggerSync) {
                            startRefresh();
                        } else {
                            setTriggerPercentage(this.mAccelerateInterpolator.getInterpolation(y / this.mDistanceToTriggerSync));
                            updateContentOffsetTop((int) y);
                            if (this.mLastMotionY <= y2 || this.mTarget.getTop() != getPaddingTop()) {
                                updatePositionTimeout();
                            } else {
                                removeCallbacks(this.mCancel);
                            }
                        }
                        this.mLastMotionY = y2;
                        break;
                    }
                }
                Log.e(LOG_TAG, "Got ACTION_MOVE event but have an invalid active pointer id.");
                return false;
                break;
            case Type.INT /*5*/:
                int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
                this.mLastMotionY = MotionEventCompat.getY(motionEvent, actionIndex);
                this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                break;
            case Type.FLOAT /*6*/:
                onSecondaryPointerUp(motionEvent);
                break;
        }
        return true;
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
    }

    @Deprecated
    public void setColorScheme(int i, int i2, int i3, int i4) {
        setColorSchemeResources(i, i2, i3, i4);
    }

    public void setColorSchemeColors(int i, int i2, int i3, int i4) {
        ensureTarget();
        this.mProgressBar.setColorScheme(i, i2, i3, i4);
    }

    public void setColorSchemeResources(int i, int i2, int i3, int i4) {
        Resources resources = getResources();
        setColorSchemeColors(resources.getColor(i), resources.getColor(i2), resources.getColor(i3), resources.getColor(i4));
    }

    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.mListener = onRefreshListener;
    }

    public void setRefreshing(boolean z) {
        if (this.mRefreshing != z) {
            ensureTarget();
            this.mCurrPercentage = 0.0f;
            this.mRefreshing = z;
            if (this.mRefreshing) {
                this.mProgressBar.start();
            } else {
                this.mProgressBar.stop();
            }
        }
    }
}
