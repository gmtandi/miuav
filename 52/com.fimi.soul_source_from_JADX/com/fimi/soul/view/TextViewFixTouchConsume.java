package com.fimi.soul.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

public class TextViewFixTouchConsume extends TextView {
    boolean f10497a;
    boolean f10498b;

    public TextViewFixTouchConsume(Context context) {
        super(context);
        this.f10497a = true;
    }

    public TextViewFixTouchConsume(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f10497a = true;
    }

    public TextViewFixTouchConsume(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f10497a = true;
    }

    public boolean hasFocus() {
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.f10498b = false;
        return this.f10497a ? this.f10498b : super.onTouchEvent(motionEvent);
    }
}
