package com.fimi.soul.view;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

class aj extends SimpleOnGestureListener {
    final /* synthetic */ HorizontalListView f10669a;

    private aj(HorizontalListView horizontalListView) {
        this.f10669a = horizontalListView;
    }

    public boolean onDown(MotionEvent motionEvent) {
        return this.f10669a.m12628a(motionEvent);
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return this.f10669a.m12629a(motionEvent, motionEvent2, f, f2);
    }

    public void onLongPress(MotionEvent motionEvent) {
        this.f10669a.m12617f();
        int a = this.f10669a.m12606c((int) motionEvent.getX(), (int) motionEvent.getY());
        if (a >= 0 && !this.f10669a.f10358G) {
            View childAt = this.f10669a.getChildAt(a);
            OnItemLongClickListener onItemLongClickListener = this.f10669a.getOnItemLongClickListener();
            if (onItemLongClickListener != null) {
                int e = this.f10669a.f10378v + a;
                if (onItemLongClickListener.onItemLongClick(this.f10669a, childAt, e, this.f10669a.f10364b.getItemId(e))) {
                    this.f10669a.performHapticFeedback(0);
                }
            }
        }
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        this.f10669a.m12597a(Boolean.valueOf(true));
        this.f10669a.setCurrentScrollState(an.SCROLL_STATE_TOUCH_SCROLL);
        this.f10669a.m12617f();
        HorizontalListView horizontalListView = this.f10669a;
        horizontalListView.f10366d += (int) f;
        this.f10669a.m12625j(Math.round(f));
        this.f10669a.requestLayout();
        return true;
    }

    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        this.f10669a.m12617f();
        OnItemClickListener onItemClickListener = this.f10669a.getOnItemClickListener();
        int a = this.f10669a.m12606c((int) motionEvent.getX(), (int) motionEvent.getY());
        if (a >= 0 && !this.f10669a.f10358G) {
            View childAt = this.f10669a.getChildAt(a);
            int e = this.f10669a.f10378v + a;
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(this.f10669a, childAt, e, this.f10669a.f10364b.getItemId(e));
                return true;
            }
        }
        if (!(this.f10669a.f10360I == null || this.f10669a.f10358G)) {
            this.f10669a.f10360I.onClick(this.f10669a);
        }
        return false;
    }
}
