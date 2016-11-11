package com.fimi.soul.media.gallery;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ExpandableListView;
import org.codehaus.jackson.smile.SmileConstants;

public class MyExpandableListView extends ExpandableListView {
    private float f7780a;
    private float f7781b;
    private int f7782c;

    public MyExpandableListView(Context context) {
        super(context);
        m10750a();
    }

    public MyExpandableListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m10750a();
    }

    public MyExpandableListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m10750a();
    }

    private void m10750a() {
        this.f7782c = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        switch (action) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                this.f7780a = x;
                this.f7781b = y;
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                action = (int) (this.f7780a - x);
                boolean z = Math.abs(action) > this.f7782c && Math.abs(((int) (this.f7781b - y)) / action) < 1;
                if (z) {
                    return true;
                }
                break;
        }
        return false;
    }
}
