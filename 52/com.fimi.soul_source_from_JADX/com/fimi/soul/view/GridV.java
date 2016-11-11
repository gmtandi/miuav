package com.fimi.soul.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

public class GridV extends GridView {
    public GridV(Context context) {
        super(context);
    }

    public GridV(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public GridV(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return motionEvent.getAction() == 2 ? true : super.dispatchTouchEvent(motionEvent);
    }
}
