package com.fimi.soul.media.gallery;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

class ac extends SimpleOnGestureListener {
    final /* synthetic */ HorizontalListView f7786a;

    private ac(HorizontalListView horizontalListView) {
        this.f7786a = horizontalListView;
    }

    public boolean onDown(MotionEvent motionEvent) {
        return this.f7786a.m10743a(motionEvent);
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return this.f7786a.m10744a(motionEvent, motionEvent2, f, f2);
    }

    public void onLongPress(MotionEvent motionEvent) {
        this.f7786a.m10732f();
        int a = this.f7786a.m10721c((int) motionEvent.getX(), (int) motionEvent.getY());
        if (a >= 0 && !this.f7786a.f7743G) {
            View childAt = this.f7786a.getChildAt(a);
            OnItemLongClickListener onItemLongClickListener = this.f7786a.getOnItemLongClickListener();
            if (onItemLongClickListener != null) {
                int e = this.f7786a.f7763v + a;
                if (onItemLongClickListener.onItemLongClick(this.f7786a, childAt, e, this.f7786a.f7749b.getItemId(e))) {
                    this.f7786a.performHapticFeedback(0);
                }
            }
        }
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        this.f7786a.m10712a(Boolean.valueOf(true));
        this.f7786a.setCurrentScrollState(ag.SCROLL_STATE_TOUCH_SCROLL);
        this.f7786a.m10732f();
        HorizontalListView horizontalListView = this.f7786a;
        horizontalListView.f7751d += (int) f;
        this.f7786a.m10740j(Math.round(f));
        this.f7786a.requestLayout();
        return true;
    }

    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        this.f7786a.m10732f();
        OnItemClickListener onItemClickListener = this.f7786a.getOnItemClickListener();
        int a = this.f7786a.m10721c((int) motionEvent.getX(), (int) motionEvent.getY());
        if (a >= 0 && !this.f7786a.f7743G) {
            View childAt = this.f7786a.getChildAt(a);
            int e = this.f7786a.f7763v + a;
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(this.f7786a, childAt, e, this.f7786a.f7749b.getItemId(e));
                return true;
            }
        }
        if (!(this.f7786a.f7745I == null || this.f7786a.f7743G)) {
            this.f7786a.f7745I.onClick(this.f7786a);
        }
        return false;
    }
}
