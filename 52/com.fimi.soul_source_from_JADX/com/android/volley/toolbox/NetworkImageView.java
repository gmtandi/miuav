package com.android.volley.toolbox;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class NetworkImageView extends ImageView {
    private String f3575a;
    private int f3576b;
    private int f3577c;
    private C0590q f3578d;
    private C0597x f3579e;

    public NetworkImageView(Context context) {
        this(context, null);
    }

    public NetworkImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NetworkImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private void m5137a() {
        if (this.f3576b != 0) {
            setImageResource(this.f3576b);
        } else {
            setImageBitmap(null);
        }
    }

    public void m5139a(String str, C0590q c0590q) {
        this.f3575a = str;
        this.f3578d = c0590q;
        m5140a(false);
    }

    void m5140a(boolean z) {
        Object obj;
        Object obj2;
        Object obj3 = 1;
        int width = getWidth();
        int height = getHeight();
        ScaleType scaleType = getScaleType();
        if (getLayoutParams() != null) {
            obj = getLayoutParams().width == -2 ? 1 : null;
            obj2 = getLayoutParams().height == -2 ? 1 : null;
        } else {
            obj2 = null;
            obj = null;
        }
        if (obj == null || obj2 == null) {
            obj3 = null;
        }
        if (width != 0 || height != 0 || r1 != null) {
            if (TextUtils.isEmpty(this.f3575a)) {
                if (this.f3579e != null) {
                    this.f3579e.m5269a();
                    this.f3579e = null;
                }
                m5137a();
                return;
            }
            if (!(this.f3579e == null || this.f3579e.m5271c() == null)) {
                if (!this.f3579e.m5271c().equals(this.f3575a)) {
                    this.f3579e.m5269a();
                    m5137a();
                } else {
                    return;
                }
            }
            int i = obj != null ? 0 : width;
            if (obj2 != null) {
                height = 0;
            }
            this.f3579e = this.f3578d.m5247a(this.f3575a, new ad(this, z), i, height, scaleType);
        }
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }

    public String getImageURL() {
        return this.f3575a;
    }

    protected void onDetachedFromWindow() {
        if (this.f3579e != null) {
            this.f3579e.m5269a();
            setImageBitmap(null);
            this.f3579e = null;
        }
        super.onDetachedFromWindow();
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        m5140a(true);
    }

    public void setDefaultImageResId(int i) {
        this.f3576b = i;
    }

    public void setErrorImageResId(int i) {
        this.f3577c = i;
    }
}
