package com.fimi.soul.media.gallery;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class MyImageView extends ImageView {
    private am f7783a;

    public MyImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MyImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.f7783a != null) {
            this.f7783a.m10758a(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void setOnMeasureListener(am amVar) {
        this.f7783a = amVar;
    }
}
