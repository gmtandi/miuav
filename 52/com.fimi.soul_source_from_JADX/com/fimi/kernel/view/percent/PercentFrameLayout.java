package com.fimi.kernel.view.percent;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.FrameLayout;

public class PercentFrameLayout extends FrameLayout {
    private final C1202a f5406a;

    public class LayoutParams extends android.widget.FrameLayout.LayoutParams implements C1201d {
        private C1203b f5405a;

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(int i, int i2, int i3) {
            super(i, i2, i3);
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f5405a = C1202a.m8401a(context, attributeSet);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(android.widget.FrameLayout.LayoutParams layoutParams) {
            super(layoutParams);
            this.gravity = layoutParams.gravity;
        }

        public LayoutParams(LayoutParams layoutParams) {
            this((android.widget.FrameLayout.LayoutParams) layoutParams);
            this.f5405a = layoutParams.f5405a;
        }

        public C1203b m8395a() {
            return this.f5405a;
        }

        protected void setBaseAttributes(TypedArray typedArray, int i, int i2) {
            C1202a.m8407a((android.view.ViewGroup.LayoutParams) this, typedArray, i, i2);
        }
    }

    public PercentFrameLayout(Context context) {
        super(context);
        this.f5406a = new C1202a(this);
    }

    public PercentFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f5406a = new C1202a(this);
    }

    public PercentFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f5406a = new C1202a(this);
    }

    public LayoutParams m8396a(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public /* synthetic */ android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return m8396a(attributeSet);
    }

    public /* synthetic */ android.widget.FrameLayout.LayoutParams m19390generateLayoutParams(AttributeSet attributeSet) {
        return m8396a(attributeSet);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.f5406a.m8417a();
    }

    protected void onMeasure(int i, int i2) {
        this.f5406a.m8418a(i, i2);
        super.onMeasure(i, i2);
        if (this.f5406a.m8419b()) {
            super.onMeasure(i, i2);
        }
    }
}
