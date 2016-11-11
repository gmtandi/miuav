package com.facebook.drawee.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import com.facebook.common.activitylistener.ActivityListener;
import com.facebook.common.activitylistener.BaseActivityListener;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.components.DraweeEventTracker;
import com.facebook.drawee.components.DraweeEventTracker.Event;
import com.facebook.drawee.drawable.VisibilityAwareDrawable;
import com.facebook.drawee.drawable.VisibilityCallback;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.interfaces.DraweeHierarchy;
import javax.annotation.Nullable;

public class DraweeHolder<DH extends DraweeHierarchy> implements VisibilityCallback {
    private final ActivityListener mActivityListener;
    private DraweeController mController;
    private final DraweeEventTracker mEventTracker;
    private DH mHierarchy;
    private boolean mIsActivityStarted;
    private boolean mIsControllerAttached;
    private boolean mIsHolderAttached;
    private boolean mIsVisible;

    /* renamed from: com.facebook.drawee.view.DraweeHolder.1 */
    class C09881 extends BaseActivityListener {
        C09881() {
        }

        public void onStart(Activity activity) {
            DraweeHolder.this.setActivityStarted(true);
        }

        public void onStop(Activity activity) {
            DraweeHolder.this.setActivityStarted(false);
        }
    }

    public DraweeHolder(@Nullable DH dh) {
        this.mIsControllerAttached = false;
        this.mIsHolderAttached = false;
        this.mIsVisible = true;
        this.mIsActivityStarted = true;
        this.mController = null;
        this.mEventTracker = new DraweeEventTracker();
        if (dh != null) {
            setHierarchy(dh);
        }
        this.mActivityListener = new C09881();
    }

    private void attachController() {
        if (!this.mIsControllerAttached) {
            this.mEventTracker.recordEvent(Event.ON_ATTACH_CONTROLLER);
            this.mIsControllerAttached = true;
            if (this.mController != null && this.mController.getHierarchy() != null) {
                this.mController.onAttach();
            }
        }
    }

    private void attachOrDetachController() {
        if (this.mIsHolderAttached && this.mIsVisible && this.mIsActivityStarted) {
            attachController();
        } else {
            detachController();
        }
    }

    public static <DH extends DraweeHierarchy> DraweeHolder<DH> create(@Nullable DH dh, Context context) {
        DraweeHolder<DH> draweeHolder = new DraweeHolder(dh);
        draweeHolder.registerWithContext(context);
        return draweeHolder;
    }

    private void detachController() {
        if (this.mIsControllerAttached) {
            this.mEventTracker.recordEvent(Event.ON_DETACH_CONTROLLER);
            this.mIsControllerAttached = false;
            if (this.mController != null) {
                this.mController.onDetach();
            }
        }
    }

    private void setActivityStarted(boolean z) {
        this.mEventTracker.recordEvent(z ? Event.ON_ACTIVITY_START : Event.ON_ACTIVITY_STOP);
        this.mIsActivityStarted = z;
        attachOrDetachController();
    }

    private void setVisibilityCallback(@Nullable VisibilityCallback visibilityCallback) {
        Drawable topLevelDrawable = getTopLevelDrawable();
        if (topLevelDrawable instanceof VisibilityAwareDrawable) {
            ((VisibilityAwareDrawable) topLevelDrawable).setVisibilityCallback(visibilityCallback);
        }
    }

    @Nullable
    public DraweeController getController() {
        return this.mController;
    }

    public DH getHierarchy() {
        return (DraweeHierarchy) Preconditions.checkNotNull(this.mHierarchy);
    }

    public Drawable getTopLevelDrawable() {
        return this.mHierarchy == null ? null : this.mHierarchy.getTopLevelDrawable();
    }

    public boolean hasHierarchy() {
        return this.mHierarchy != null;
    }

    public void onAttach() {
        this.mEventTracker.recordEvent(Event.ON_HOLDER_ATTACH);
        this.mIsHolderAttached = true;
        attachOrDetachController();
    }

    public void onDetach() {
        this.mEventTracker.recordEvent(Event.ON_HOLDER_DETACH);
        this.mIsHolderAttached = false;
        attachOrDetachController();
    }

    public void onDraw() {
        if (!this.mIsControllerAttached) {
            FLog.wtf(DraweeEventTracker.class, "%x: Draw requested for a non-attached controller %x. %s", Integer.valueOf(System.identityHashCode(this)), Integer.valueOf(System.identityHashCode(this.mController)), toString());
            this.mIsHolderAttached = true;
            this.mIsVisible = true;
            this.mIsActivityStarted = true;
            attachOrDetachController();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.mController == null ? false : this.mController.onTouchEvent(motionEvent);
    }

    public void onVisibilityChange(boolean z) {
        if (this.mIsVisible != z) {
            this.mEventTracker.recordEvent(z ? Event.ON_DRAWABLE_SHOW : Event.ON_DRAWABLE_HIDE);
            this.mIsVisible = z;
            attachOrDetachController();
        }
    }

    public void registerWithContext(Context context) {
    }

    public void setController(@Nullable DraweeController draweeController) {
        boolean z = this.mIsControllerAttached;
        if (z) {
            detachController();
        }
        if (this.mController != null) {
            this.mEventTracker.recordEvent(Event.ON_CLEAR_OLD_CONTROLLER);
            this.mController.setHierarchy(null);
        }
        this.mController = draweeController;
        if (this.mController != null) {
            this.mEventTracker.recordEvent(Event.ON_SET_CONTROLLER);
            this.mController.setHierarchy(this.mHierarchy);
        } else {
            this.mEventTracker.recordEvent(Event.ON_CLEAR_CONTROLLER);
        }
        if (z) {
            attachController();
        }
    }

    public void setHierarchy(DH dh) {
        this.mEventTracker.recordEvent(Event.ON_SET_HIERARCHY);
        setVisibilityCallback(null);
        this.mHierarchy = (DraweeHierarchy) Preconditions.checkNotNull(dh);
        onVisibilityChange(this.mHierarchy.getTopLevelDrawable().isVisible());
        setVisibilityCallback(this);
        if (this.mController != null) {
            this.mController.setHierarchy(dh);
        }
    }

    public String toString() {
        return Objects.toStringHelper((Object) this).add("controllerAttached", this.mIsControllerAttached).add("holderAttached", this.mIsHolderAttached).add("drawableVisible", this.mIsVisible).add("activityStarted", this.mIsActivityStarted).add("events", this.mEventTracker.toString()).toString();
    }
}
