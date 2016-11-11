package com.fimi.soul.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;
import com.fimi.soul.p087b.C1224m;

public class MyGridView extends GridView {
    private C1224m f10417a;

    public MyGridView(Context context) {
        super(context);
    }

    public MyGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MyGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onMeasure(int i, int i2) {
        if (this.f10417a != null) {
            this.f10417a.m8478a();
        }
        super.onMeasure(i, i2);
    }

    public void setAdapter(C1224m c1224m) {
        this.f10417a = c1224m;
        super.setAdapter(c1224m);
    }
}
