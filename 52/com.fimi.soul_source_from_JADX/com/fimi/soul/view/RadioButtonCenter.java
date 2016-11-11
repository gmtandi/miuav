package com.fimi.soul.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;
import com.fimi.soul.C1205R;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.util.TokenBuffer.Segment;
import p147m.framework.ui.widget.asyncview.AsyncImageView;

public class RadioButtonCenter extends RadioButton {
    private Drawable f10424a;

    public RadioButtonCenter(Context context) {
        super(context);
    }

    public RadioButtonCenter(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RadioButtonCenter(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C1205R.styleable.RadioButtonCenter, i, 0);
        try {
            this.f10424a = obtainStyledAttributes.getDrawable(0);
            setButtonDrawable(AsyncImageView.f14604a);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f10424a != null) {
            this.f10424a.setState(getDrawableState());
            int gravity = getGravity() & Opcodes.IREM;
            int intrinsicHeight = this.f10424a.getIntrinsicHeight();
            int i = 0;
            switch (gravity) {
                case Segment.TOKENS_PER_SEGMENT /*16*/:
                    i = (getHeight() - intrinsicHeight) / 2;
                    break;
                case Opcodes.LASTORE /*80*/:
                    i = getHeight() - intrinsicHeight;
                    break;
            }
            gravity = this.f10424a.getIntrinsicWidth();
            int width = (getWidth() - gravity) / 2;
            this.f10424a.setBounds(width, i, gravity + width, intrinsicHeight + i);
            this.f10424a.draw(canvas);
        }
    }
}
