package com.facebook.drawee.gestures;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.facebook.common.internal.VisibleForTesting;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

public class GestureDetector {
    @VisibleForTesting
    long mActionDownTime;
    @VisibleForTesting
    float mActionDownX;
    @VisibleForTesting
    float mActionDownY;
    @VisibleForTesting
    ClickListener mClickListener;
    @VisibleForTesting
    boolean mIsCapturingGesture;
    @VisibleForTesting
    boolean mIsClickCandidate;
    @VisibleForTesting
    final float mSingleTapSlopPx;

    public interface ClickListener {
        boolean onClick();
    }

    public GestureDetector(Context context) {
        this.mSingleTapSlopPx = (float) ViewConfiguration.get(context).getScaledTouchSlop();
        init();
    }

    public static GestureDetector newInstance(Context context) {
        return new GestureDetector(context);
    }

    public void init() {
        this.mClickListener = null;
        reset();
    }

    public boolean isCapturingGesture() {
        return this.mIsCapturingGesture;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                this.mIsCapturingGesture = true;
                this.mIsClickCandidate = true;
                this.mActionDownTime = motionEvent.getEventTime();
                this.mActionDownX = motionEvent.getX();
                this.mActionDownY = motionEvent.getY();
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                this.mIsCapturingGesture = false;
                if (Math.abs(motionEvent.getX() - this.mActionDownX) > this.mSingleTapSlopPx || Math.abs(motionEvent.getY() - this.mActionDownY) > this.mSingleTapSlopPx) {
                    this.mIsClickCandidate = false;
                }
                if (this.mIsClickCandidate && motionEvent.getEventTime() - this.mActionDownTime <= ((long) ViewConfiguration.getLongPressTimeout()) && this.mClickListener != null) {
                    this.mClickListener.onClick();
                }
                this.mIsClickCandidate = false;
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                if (Math.abs(motionEvent.getX() - this.mActionDownX) > this.mSingleTapSlopPx || Math.abs(motionEvent.getY() - this.mActionDownY) > this.mSingleTapSlopPx) {
                    this.mIsClickCandidate = false;
                    break;
                }
            case Type.BYTE /*3*/:
                this.mIsCapturingGesture = false;
                this.mIsClickCandidate = false;
                break;
        }
        return true;
    }

    public void reset() {
        this.mIsCapturingGesture = false;
        this.mIsClickCandidate = false;
    }

    public void setClickListener(ClickListener clickListener) {
        this.mClickListener = clickListener;
    }
}
