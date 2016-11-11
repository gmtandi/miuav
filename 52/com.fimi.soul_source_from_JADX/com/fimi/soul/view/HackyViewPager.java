package com.fimi.soul.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class HackyViewPager extends ViewPager {
    private static final String f10344a = "HackyViewPager";
    private boolean f10345b;

    public HackyViewPager(Context context) {
        super(context);
        this.f10345b = true;
    }

    public HackyViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f10345b = true;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        try {
            z = super.onInterceptTouchEvent(motionEvent);
        } catch (IllegalArgumentException e) {
            Log.e(f10344a, "hacky viewpager error1");
        } catch (ArrayIndexOutOfBoundsException e2) {
            Log.e(f10344a, "hacky viewpager error2");
        }
        return z;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return !this.f10345b ? false : super.onTouchEvent(motionEvent);
    }

    public void scrollTo(int i, int i2) {
        if (this.f10345b) {
            super.scrollTo(i, i2);
        }
    }

    public void setScrollble(boolean z) {
        this.f10345b = z;
    }
}
