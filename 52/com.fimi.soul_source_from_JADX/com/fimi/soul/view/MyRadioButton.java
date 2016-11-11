package com.fimi.soul.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.util.TokenBuffer.Segment;

public class MyRadioButton extends RadioButton {
    private Context f10418a;
    private Drawable f10419b;
    private int f10420c;

    public MyRadioButton(Context context) {
        super(context);
        this.f10418a = context;
    }

    public MyRadioButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f10418a = context;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Drawable drawable = this.f10419b;
        if (drawable != null) {
            int gravity = getGravity() & 17;
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int i = 0;
            switch (gravity) {
                case Segment.TOKENS_PER_SEGMENT /*16*/:
                    i = (getHeight() - intrinsicHeight) / 2;
                    break;
                case Opcodes.LASTORE /*80*/:
                    i = getHeight() - intrinsicHeight;
                    break;
            }
            gravity = (getWidth() - intrinsicWidth) / 2;
            drawable.setBounds(gravity, i, intrinsicWidth + gravity, intrinsicHeight + i);
            drawable.draw(canvas);
        }
    }

    public void setButtonDrawable(int i) {
        if (i == 0 || i != this.f10420c) {
            this.f10420c = i;
            Drawable drawable = null;
            if (this.f10420c != 0) {
                drawable = getResources().getDrawable(this.f10420c);
            }
            setButtonDrawable(drawable);
        }
    }

    public void setButtonDrawable(Drawable drawable) {
        if (drawable != null) {
            if (this.f10419b != null) {
                this.f10419b.setCallback(null);
                unscheduleDrawable(this.f10419b);
            }
            drawable.setCallback(this);
            drawable.setState(getDrawableState());
            drawable.setVisible(getVisibility() == 0, false);
            this.f10419b = drawable;
            this.f10419b.setState(null);
            setMinHeight(this.f10419b.getIntrinsicHeight());
        }
        refreshDrawableState();
    }
}
