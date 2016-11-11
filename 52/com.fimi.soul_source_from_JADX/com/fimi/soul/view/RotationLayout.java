package com.fimi.soul.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.amap.api.maps.model.BitmapDescriptorFactory;

@SuppressLint({"Instantiatable"})
class RotationLayout extends FrameLayout {
    private int f10471a;

    public RotationLayout(Context context) {
        super(context);
    }

    public RotationLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RotationLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void m12641a(int i) {
        this.f10471a = ((i + 360) % 360) / 90;
    }

    public void dispatchDraw(Canvas canvas) {
        if (this.f10471a == 0) {
            super.dispatchDraw(canvas);
            return;
        }
        if (this.f10471a == 1) {
            canvas.translate((float) getWidth(), 0.0f);
            canvas.rotate(90.0f, (float) (getWidth() / 2), 0.0f);
            canvas.translate((float) (getHeight() / 2), (float) (getWidth() / 2));
        } else if (this.f10471a == 2) {
            canvas.rotate(BitmapDescriptorFactory.HUE_CYAN, (float) (getWidth() / 2), (float) (getHeight() / 2));
        } else {
            canvas.translate(0.0f, (float) getHeight());
            canvas.rotate(BitmapDescriptorFactory.HUE_VIOLET, (float) (getWidth() / 2), 0.0f);
            canvas.translate((float) (getHeight() / 2), (float) ((-getWidth()) / 2));
        }
        super.dispatchDraw(canvas);
    }

    protected void onMeasure(int i, int i2) {
        if (this.f10471a == 1 || this.f10471a == 3) {
            super.onMeasure(i, i2);
            setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
            return;
        }
        super.onMeasure(i, i2);
    }
}
