package com.fimi.soul.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.TabWidget;
import android.widget.TextView;
import com.fimi.soul.view.photodraweeview.C2020f;

public class BadgeView extends TextView {
    public static final int f10238a = 1;
    public static final int f10239b = 2;
    public static final int f10240c = 3;
    public static final int f10241d = 4;
    public static final int f10242e = 5;
    public static final int f10243f = 10;
    private static final int f10244g = 5;
    private static final int f10245h = 5;
    private static final int f10246i = 8;
    private static final int f10247j = 2;
    private static final int f10248k;
    private static final int f10249l = -1;
    private static Animation f10250m;
    private static Animation f10251n;
    private Context f10252o;
    private View f10253p;
    private int f10254q;
    private int f10255r;
    private int f10256s;
    private int f10257t;
    private boolean f10258u;
    private ShapeDrawable f10259v;
    private int f10260w;

    static {
        f10248k = Color.parseColor("#CCFF0000");
    }

    public BadgeView(Context context) {
        this(context, (AttributeSet) null, 16842884);
    }

    public BadgeView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842884);
    }

    public BadgeView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, null, f10248k);
    }

    public BadgeView(Context context, AttributeSet attributeSet, int i, View view, int i2) {
        super(context, attributeSet, i);
        m12545a(context, view, i2);
    }

    public BadgeView(Context context, View view) {
        this(context, null, 16842884, view, f10248k);
    }

    public BadgeView(Context context, TabWidget tabWidget, int i) {
        this(context, null, 16842884, tabWidget, i);
    }

    private void m12545a(Context context, View view, int i) {
        this.f10252o = context;
        this.f10253p = view;
        this.f10260w = i;
        this.f10254q = f10247j;
        this.f10255r = m12550c((int) f10245h);
        this.f10256s = this.f10255r;
        this.f10257t = f10248k;
        setTypeface(Typeface.DEFAULT_BOLD);
        int c = m12550c((int) f10245h);
        setPadding(c, f10248k, c, f10248k);
        setTextColor(f10249l);
        f10250m = new AlphaAnimation(0.0f, C2020f.f10933c);
        f10250m.setInterpolator(new DecelerateInterpolator());
        f10250m.setDuration(500);
        f10251n = new AlphaAnimation(C2020f.f10933c, 0.0f);
        f10251n.setInterpolator(new AccelerateInterpolator());
        f10251n.setDuration(500);
        this.f10258u = false;
        if (this.f10253p != null) {
            m12546a(this.f10253p);
        } else {
            m12553a();
        }
    }

    private void m12546a(View view) {
        LayoutParams layoutParams = view.getLayoutParams();
        ViewParent parent = view.getParent();
        View frameLayout = new FrameLayout(this.f10252o);
        if (view instanceof TabWidget) {
            View childTabViewAt = ((TabWidget) view).getChildTabViewAt(this.f10260w);
            this.f10253p = childTabViewAt;
            ((ViewGroup) childTabViewAt).addView(frameLayout, new LayoutParams(f10249l, f10249l));
            setVisibility(f10246i);
            frameLayout.addView(this);
            return;
        }
        ViewGroup viewGroup = (ViewGroup) parent;
        int indexOfChild = viewGroup.indexOfChild(view);
        viewGroup.removeView(view);
        viewGroup.addView(frameLayout, indexOfChild, layoutParams);
        frameLayout.addView(view);
        setVisibility(f10246i);
        frameLayout.addView(this);
        viewGroup.invalidate();
    }

    private void m12547a(boolean z, Animation animation) {
        if (getBackground() == null) {
            if (this.f10259v == null) {
                this.f10259v = getDefaultBackground();
            }
            setBackgroundDrawable(this.f10259v);
        }
        m12551d();
        if (z) {
            startAnimation(animation);
        }
        setVisibility(f10248k);
        this.f10258u = true;
    }

    private void m12548a(boolean z, Animation animation, Animation animation2) {
        boolean z2 = true;
        if (this.f10258u) {
            if (!z || animation2 == null) {
                z2 = false;
            }
            m12549b(z2, animation2);
            return;
        }
        if (!z || animation == null) {
            z2 = false;
        }
        m12547a(z2, animation);
    }

    private void m12549b(boolean z, Animation animation) {
        setVisibility(f10246i);
        if (z) {
            startAnimation(animation);
        }
        this.f10258u = false;
    }

    private int m12550c(int i) {
        return (int) TypedValue.applyDimension(f10238a, (float) i, getResources().getDisplayMetrics());
    }

    private void m12551d() {
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        switch (this.f10254q) {
            case f10238a /*1*/:
                layoutParams.gravity = 51;
                layoutParams.setMargins(this.f10255r, this.f10256s, f10248k, f10248k);
                break;
            case f10247j /*2*/:
                layoutParams.gravity = 53;
                layoutParams.setMargins(f10248k, this.f10256s, this.f10255r, f10248k);
                break;
            case f10240c /*3*/:
                layoutParams.gravity = 83;
                layoutParams.setMargins(this.f10255r, f10248k, f10248k, this.f10256s);
                break;
            case f10241d /*4*/:
                layoutParams.gravity = 85;
                layoutParams.setMargins(f10248k, f10248k, this.f10255r, this.f10256s);
                break;
            case f10245h /*5*/:
                layoutParams.gravity = 17;
                layoutParams.setMargins(f10248k, f10248k, f10248k, f10248k);
                break;
            case f10243f /*10*/:
                layoutParams.gravity = 17;
                layoutParams.setMargins(this.f10255r, f10248k, f10248k, f10248k);
                break;
        }
        setLayoutParams(layoutParams);
    }

    private ShapeDrawable getDefaultBackground() {
        int c = m12550c((int) f10246i);
        float[] fArr = new float[f10246i];
        fArr[f10248k] = (float) c;
        fArr[f10238a] = (float) c;
        fArr[f10247j] = (float) c;
        fArr[f10240c] = (float) c;
        fArr[f10241d] = (float) c;
        fArr[f10245h] = (float) c;
        fArr[6] = (float) c;
        fArr[7] = (float) c;
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(fArr, null, null));
        shapeDrawable.getPaint().setColor(this.f10257t);
        return shapeDrawable;
    }

    public int m12552a(int i) {
        int i2 = f10248k;
        CharSequence text = getText();
        if (text != null) {
            try {
                i2 = Integer.parseInt(text.toString());
            } catch (NumberFormatException e) {
            }
        }
        i2 += i;
        setText(String.valueOf(i2));
        return i2;
    }

    public void m12553a() {
        m12547a(false, null);
    }

    public void m12554a(int i, int i2) {
        this.f10255r = i;
        this.f10256s = i2;
    }

    public void m12555a(Animation animation) {
        m12547a(true, animation);
    }

    public void m12556a(Animation animation, Animation animation2) {
        m12548a(true, animation, animation2);
    }

    public void m12557a(boolean z) {
        m12547a(z, f10250m);
    }

    public int m12558b(int i) {
        return m12552a(-i);
    }

    public void m12559b() {
        m12549b(false, null);
    }

    public void m12560b(Animation animation) {
        m12549b(true, animation);
    }

    public void m12561b(boolean z) {
        m12549b(z, f10251n);
    }

    public void m12562c() {
        m12548a(false, null, null);
    }

    public void m12563c(boolean z) {
        m12548a(z, f10250m, f10251n);
    }

    public int getBadgeBackgroundColor() {
        return this.f10257t;
    }

    public int getBadgePosition() {
        return this.f10254q;
    }

    public int getHorizontalBadgeMargin() {
        return this.f10255r;
    }

    public View getTarget() {
        return this.f10253p;
    }

    public int getVerticalBadgeMargin() {
        return this.f10256s;
    }

    public boolean isShown() {
        return this.f10258u;
    }

    public void setBadgeBackgroundColor(int i) {
        this.f10257t = i;
        this.f10259v = getDefaultBackground();
    }

    public void setBadgeMargin(int i) {
        this.f10255r = i;
        this.f10256s = i;
    }

    public void setBadgePosition(int i) {
        this.f10254q = i;
    }
}
