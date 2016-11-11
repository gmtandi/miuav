package com.fimi.kernel.view.percent;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.RelativeLayout;

public class PercentRelativeLayout extends RelativeLayout {
    private final C1202a f5411a;

    public class LayoutParams extends android.widget.RelativeLayout.LayoutParams implements C1201d {
        private C1203b f5410a;

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f5410a = C1202a.m8401a(context, attributeSet);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public C1203b m8399a() {
            return this.f5410a;
        }

        protected void setBaseAttributes(TypedArray typedArray, int i, int i2) {
            C1202a.m8407a((android.view.ViewGroup.LayoutParams) this, typedArray, i, i2);
        }
    }

    public PercentRelativeLayout(Context context) {
        super(context);
        this.f5411a = new C1202a(this);
    }

    public PercentRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f5411a = new C1202a(this);
    }

    public PercentRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f5411a = new C1202a(this);
    }

    public LayoutParams m8400a(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public /* synthetic */ android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return m8400a(attributeSet);
    }

    public /* synthetic */ android.widget.RelativeLayout.LayoutParams m19391generateLayoutParams(AttributeSet attributeSet) {
        return m8400a(attributeSet);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.f5411a.m8417a();
    }

    protected void onMeasure(int i, int i2) {
        this.f5411a.m8418a(i, i2);
        super.onMeasure(i, i2);
        if (this.f5411a.m8419b()) {
            super.onMeasure(i, i2);
        }
    }
}
