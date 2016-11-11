package com.fimi.kernel.view.percent;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class PercentLinearLayout extends LinearLayout {
    private static final String f5408a = "PercentLinearLayout";
    private C1202a f5409b;

    public class LayoutParams extends android.widget.LinearLayout.LayoutParams implements C1201d {
        private C1203b f5407a;

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f5407a = C1202a.m8401a(context, attributeSet);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public C1203b m8397a() {
            return this.f5407a;
        }

        protected void setBaseAttributes(TypedArray typedArray, int i, int i2) {
            C1202a.m8407a((android.view.ViewGroup.LayoutParams) this, typedArray, i, i2);
        }
    }

    public PercentLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f5409b = new C1202a(this);
    }

    private int getScreenHeight() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public LayoutParams m8398a(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public /* synthetic */ android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return m8398a(attributeSet);
    }

    public /* synthetic */ android.widget.LinearLayout.LayoutParams m19392generateLayoutParams(AttributeSet attributeSet) {
        return m8398a(attributeSet);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.f5409b.m8417a();
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i2);
        int mode = MeasureSpec.getMode(i2);
        size = MeasureSpec.makeMeasureSpec(size, mode);
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(i), MeasureSpec.getMode(i));
        Log.d(f5408a, MeasureSpec.toString(i2));
        if (mode == 0 && getParent() != null && (getParent() instanceof ScrollView)) {
            Context context = getContext();
            if (context instanceof Activity) {
                size = ((Activity) context).findViewById(16908290).getMeasuredHeight();
                Log.d(f5408a, "measuredHeight = " + size);
            } else {
                size = getScreenHeight();
                Log.d(f5408a, "scHeight = " + size);
            }
            size = MeasureSpec.makeMeasureSpec(size, mode);
        }
        this.f5409b.m8418a(makeMeasureSpec, size);
        super.onMeasure(i, i2);
        if (this.f5409b.m8419b()) {
            super.onMeasure(i, i2);
        }
    }
}
