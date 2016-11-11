package com.fimi.soul.view;

import android.content.Context;
import android.graphics.PointF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyViewPage extends ViewPager {
    PointF f10421a;
    PointF f10422b;
    private float f10423c;

    public MyViewPage(Context context) {
        super(context);
        this.f10421a = new PointF();
        this.f10422b = new PointF();
    }

    public MyViewPage(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f10421a = new PointF();
        this.f10422b = new PointF();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        System.out.println("MyViewPage2 onInterceptTouchEvent");
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.f10422b.x = motionEvent.getX();
        this.f10422b.y = motionEvent.getY();
        if (motionEvent.getAction() == 0) {
            this.f10421a.x = motionEvent.getX();
            this.f10421a.y = motionEvent.getY();
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        if (motionEvent.getAction() == 2) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        if (motionEvent.getAction() == 1 && this.f10421a.x == this.f10422b.x && this.f10421a.y == this.f10422b.y) {
            return true;
        }
        try {
            return super.onTouchEvent(motionEvent);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }
}
