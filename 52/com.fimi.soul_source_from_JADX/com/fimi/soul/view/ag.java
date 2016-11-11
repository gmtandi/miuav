package com.fimi.soul.view;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class ag implements OnTouchListener {
    final /* synthetic */ HorizontalListView f10666a;

    ag(HorizontalListView horizontalListView) {
        this.f10666a = horizontalListView;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.f10666a.f10368l.onTouchEvent(motionEvent);
    }
}
