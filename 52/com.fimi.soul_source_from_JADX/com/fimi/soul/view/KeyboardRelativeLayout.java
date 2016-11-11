package com.fimi.soul.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class KeyboardRelativeLayout extends RelativeLayout {
    public static final byte f10385a = (byte) -4;
    private static final String f10386b;
    private bb f10387c;

    static {
        f10386b = KeyboardRelativeLayout.class.getSimpleName();
    }

    public KeyboardRelativeLayout(Context context) {
        super(context);
    }

    public KeyboardRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public KeyboardRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected boolean fitSystemWindows(Rect rect) {
        rect.top = 0;
        return super.fitSystemWindows(rect);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.f10387c != null) {
            this.f10387c.m12793a(-4);
        }
    }

    public void setOnKeyboardStateChangedListener(bb bbVar) {
        this.f10387c = bbVar;
    }
}
