package com.fimi.soul.view.sliding;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.fimi.kernel.p084e.C1186y;

public class FmTabItemView extends LinearLayout {
    private Context f11019a;
    private int f11020b;
    private TextView f11021c;
    private Drawable f11022d;
    private Drawable f11023e;
    private Drawable f11024f;
    private Drawable f11025g;
    private int f11026h;
    private int f11027i;
    private int f11028j;
    private int f11029k;

    public FmTabItemView(Context context) {
        this(context, null);
    }

    public FmTabItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOrientation(0);
        setGravity(17);
        setPadding(10, 10, 10, 10);
        this.f11019a = context;
        this.f11021c = new TextView(context);
        this.f11021c.setGravity(17);
        this.f11021c.setLayoutParams(new LayoutParams(-1, -2));
        this.f11021c.setFocusable(true);
        this.f11021c.setPadding(0, 0, 0, 0);
        this.f11021c.setCompoundDrawablePadding(10);
        this.f11021c.setSingleLine();
        addView(this.f11021c);
    }

    public void m13006a(int i, int i2, int i3, int i4) {
        this.f11026h = C1186y.m8318e(this.f11019a, (float) i);
        this.f11027i = C1186y.m8318e(this.f11019a, (float) i2);
        this.f11028j = C1186y.m8318e(this.f11019a, (float) i3);
        this.f11029k = C1186y.m8318e(this.f11019a, (float) i4);
    }

    public void m13007a(int i, String str) {
        this.f11020b = i;
        this.f11021c.setText(str);
    }

    public void m13008a(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        this.f11022d = drawable;
        this.f11023e = drawable2;
        this.f11024f = drawable3;
        this.f11025g = drawable4;
        if (this.f11022d != null) {
            this.f11022d.setBounds(this.f11026h, this.f11027i, this.f11028j, this.f11029k);
        }
        if (this.f11023e != null) {
            this.f11023e.setBounds(this.f11026h, this.f11027i, this.f11028j, this.f11029k);
        }
        if (this.f11024f != null) {
            this.f11024f.setBounds(this.f11026h, this.f11027i, this.f11028j, this.f11029k);
        }
        if (this.f11025g != null) {
            this.f11025g.setBounds(this.f11026h, this.f11027i, this.f11028j, this.f11029k);
        }
        this.f11021c.setCompoundDrawables(this.f11022d, this.f11023e, this.f11024f, this.f11025g);
    }

    public int getIndex() {
        return this.f11020b;
    }

    public TextView getTextView() {
        return this.f11021c;
    }

    public void setTabBackgroundDrawable(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    public void setTabBackgroundResource(int i) {
        setBackgroundResource(i);
    }

    public void setTabTextColor(int i) {
        this.f11021c.setTextColor(i);
    }

    public void setTabTextSize(int i) {
        C1186y.m8313b(this.f11021c, (float) i);
    }
}
