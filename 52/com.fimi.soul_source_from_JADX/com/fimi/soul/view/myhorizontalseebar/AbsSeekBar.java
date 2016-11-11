package com.fimi.soul.view.myhorizontalseebar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.fimi.kernel.p084e.C1186y;
import com.fimi.soul.C1205R;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.tencent.open.yyb.TitleBar;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

public abstract class AbsSeekBar extends ProgressBar {
    private static final int f10887q = 255;
    float f10888a;
    boolean f10889b;
    private Drawable f10890n;
    private int f10891o;
    private int f10892p;
    private float f10893r;
    private int f10894s;
    private float f10895t;
    private boolean f10896u;

    public AbsSeekBar(Context context) {
        super(context);
        this.f10889b = true;
        this.f10892p = 1;
    }

    public AbsSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f10889b = true;
        this.f10892p = 1;
    }

    public AbsSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f10889b = true;
        this.f10892p = 1;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C1205R.styleable.SeekBar, i, 0);
        setThumb(obtainStyledAttributes.getDrawable(0));
        setThumbOffset(obtainStyledAttributes.getDimensionPixelOffset(1, getThumbOffset()));
        obtainStyledAttributes.recycle();
        obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C1205R.styleable.Theme, 0, 0);
        this.f10893r = obtainStyledAttributes.getFloat(0, 0.5f);
        obtainStyledAttributes.recycle();
        this.f10894s = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    private void m12871a(int i, int i2) {
        Drawable currentDrawable = getCurrentDrawable();
        Drawable drawable = this.f10890n;
        int intrinsicHeight = drawable == null ? 0 : drawable.getIntrinsicHeight();
        int min = Math.min(this.f, (i2 - this.l) - this.m);
        int max = getMax();
        float progress = max > 0 ? ((float) getProgress()) / ((float) max) : 0.0f;
        if (intrinsicHeight > min) {
            if (drawable != null) {
                m12872a(i, drawable, progress, 0);
            }
            intrinsicHeight = (intrinsicHeight - min) / 2;
            if (currentDrawable != null) {
                currentDrawable.setBounds(0, intrinsicHeight, (i - this.k) - this.j, ((i2 - this.m) - intrinsicHeight) - this.l);
                return;
            }
            return;
        }
        if (currentDrawable != null) {
            currentDrawable.setBounds(0, 0, (i - this.k) - this.j, (i2 - this.m) - this.l);
        }
        intrinsicHeight = (min - intrinsicHeight) / 2;
        if (drawable != null) {
            m12872a(i, drawable, progress, intrinsicHeight);
        }
    }

    private void m12872a(int i, Drawable drawable, float f, int i2) {
        int i3 = (i - this.j) - this.k;
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        int i4 = (int) (((float) ((i3 - intrinsicWidth) + (this.f10891o * 2))) * f);
        if (i2 == C1186y.f5353a) {
            Rect bounds = drawable.getBounds();
            i2 = bounds.top;
            i3 = bounds.bottom;
        } else {
            i3 = i2 + intrinsicHeight;
        }
        drawable.setBounds(i4, i2, intrinsicWidth + i4, i3);
    }

    private void m12873a(MotionEvent motionEvent) {
        float f;
        float f2 = 0.0f;
        int width = getWidth();
        int i = (width - this.j) - this.k;
        int x = (int) motionEvent.getX();
        if (x < this.j) {
            f = 0.0f;
        } else if (x > width - this.k) {
            f = C2020f.f10933c;
        } else {
            f = ((float) (x - this.j)) / ((float) i);
            f2 = this.f10888a;
        }
        m12864a((int) (f2 + (f * ((float) getMax()))), true);
    }

    private void m12874g() {
    }

    void m12875a() {
        this.f10896u = true;
    }

    void m12876a(float f, boolean z) {
        super.m12862a(f, z);
        Drawable drawable = this.f10890n;
        if (drawable != null) {
            m12872a(getWidth(), drawable, f, C1186y.f5353a);
            invalidate();
        }
    }

    public void m12877a(int i) {
        super.m12863a(i);
    }

    void m12878b() {
        this.f10896u = false;
    }

    void m12879c() {
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable progressDrawable = getProgressDrawable();
        if (progressDrawable != null) {
            progressDrawable.setAlpha(isEnabled() ? f10887q : (int) (255.0f * this.f10893r));
        }
        if (this.f10890n != null && this.f10890n.isStateful()) {
            this.f10890n.setState(getDrawableState());
        }
    }

    public int getKeyProgressIncrement() {
        return this.f10892p;
    }

    public Drawable getThumb() {
        return this.f10890n;
    }

    public int getThumbOffset() {
        return this.f10891o;
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        if (this.f10890n != null) {
            this.f10890n.jumpToCurrentState();
        }
    }

    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f10890n != null) {
            canvas.save();
            canvas.translate((float) (this.j - this.f10891o), (float) this.l);
            this.f10890n.draw(canvas);
            canvas.restore();
        }
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(AbsSeekBar.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(AbsSeekBar.class.getName());
        if (isEnabled()) {
            int progress = getProgress();
            if (progress > 0) {
                accessibilityNodeInfo.addAction(Opcodes.ACC_ANNOTATION);
            }
            if (progress < getMax()) {
                accessibilityNodeInfo.addAction(Opcodes.ACC_SYNTHETIC);
            }
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (isEnabled()) {
            int progress = getProgress();
            switch (i) {
                case Opcodes.ILOAD /*21*/:
                    if (progress > 0) {
                        m12864a(progress - this.f10892p, true);
                        m12879c();
                        return true;
                    }
                    break;
                case Opcodes.LLOAD /*22*/:
                    if (progress < getMax()) {
                        m12864a(progress + this.f10892p, true);
                        m12879c();
                        return true;
                    }
                    break;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    protected synchronized void onMeasure(int i, int i2) {
        int i3 = 0;
        synchronized (this) {
            int max;
            Drawable currentDrawable = getCurrentDrawable();
            int intrinsicHeight = this.f10890n == null ? 0 : this.f10890n.getIntrinsicHeight();
            if (currentDrawable != null) {
                max = Math.max(this.c, Math.min(this.d, currentDrawable.getIntrinsicWidth()));
                i3 = Math.max(intrinsicHeight, Math.max(this.e, Math.min(this.f, currentDrawable.getIntrinsicHeight())));
            } else {
                max = 0;
            }
            setMeasuredDimension(resolveSizeAndState(max + (this.j + this.k), i, 0), resolveSizeAndState(i3 + (this.l + this.m), i2, 0));
        }
    }

    public void onRtlPropertiesChanged(int i) {
        super.onRtlPropertiesChanged(i);
        int max = getMax();
        float progress = max > 0 ? ((float) getProgress()) / ((float) max) : 0.0f;
        Drawable drawable = this.f10890n;
        if (drawable != null) {
            m12872a(getWidth(), drawable, progress, C1186y.f5353a);
            invalidate();
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        m12871a(i, i2);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.f10889b || !isEnabled()) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                if (this.f10890n != null) {
                    invalidate(this.f10890n.getBounds());
                }
                m12875a();
                m12873a(motionEvent);
                m12874g();
                return true;
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                if (this.f10896u) {
                    m12873a(motionEvent);
                    m12878b();
                    setPressed(false);
                } else {
                    m12875a();
                    m12873a(motionEvent);
                    m12878b();
                }
                invalidate();
                return true;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                if (this.f10896u) {
                    m12873a(motionEvent);
                    return true;
                } else if (Math.abs(motionEvent.getX() - this.f10895t) <= ((float) this.f10894s)) {
                    return true;
                } else {
                    setPressed(true);
                    if (this.f10890n != null) {
                        invalidate(this.f10890n.getBounds());
                    }
                    m12875a();
                    m12873a(motionEvent);
                    m12874g();
                    return true;
                }
            case Type.BYTE /*3*/:
                if (this.f10896u) {
                    m12878b();
                    setPressed(false);
                }
                invalidate();
                return true;
            default:
                return true;
        }
    }

    public boolean performAccessibilityAction(int i, Bundle bundle) {
        if (super.performAccessibilityAction(i, bundle)) {
            return true;
        }
        if (!isEnabled()) {
            return false;
        }
        int progress = getProgress();
        int max = Math.max(1, Math.round(((float) getMax()) / 5.0f));
        switch (i) {
            case Opcodes.ACC_SYNTHETIC /*4096*/:
                if (progress >= getMax()) {
                    return false;
                }
                m12864a(progress + max, true);
                m12879c();
                return true;
            case Opcodes.ACC_ANNOTATION /*8192*/:
                if (progress <= 0) {
                    return false;
                }
                m12864a(progress - max, true);
                m12879c();
                return true;
            default:
                return false;
        }
    }

    public void setKeyProgressIncrement(int i) {
        if (i < 0) {
            i = -i;
        }
        this.f10892p = i;
    }

    public synchronized void setMax(int i) {
        super.setMax(i);
        if (this.f10892p == 0 || getMax() / this.f10892p > 20) {
            setKeyProgressIncrement(Math.max(1, Math.round(((float) getMax()) / TitleBar.BACKBTN_LEFT_MARGIN)));
        }
    }

    public void setThumb(Drawable drawable) {
        Object obj;
        if (this.f10890n == null || drawable == this.f10890n) {
            obj = null;
        } else {
            this.f10890n.setCallback(null);
            obj = 1;
        }
        if (drawable != null) {
            drawable.setCallback(this);
            this.f10891o = drawable.getIntrinsicWidth() / 2;
            if (!(obj == null || (drawable.getIntrinsicWidth() == this.f10890n.getIntrinsicWidth() && drawable.getIntrinsicHeight() == this.f10890n.getIntrinsicHeight()))) {
                requestLayout();
            }
        }
        this.f10890n = drawable;
        invalidate();
        if (obj != null) {
            m12871a(getWidth(), getHeight());
            if (drawable != null && drawable.isStateful()) {
                drawable.setState(getDrawableState());
            }
        }
    }

    public void setThumbOffset(int i) {
        this.f10891o = i;
        invalidate();
    }

    protected boolean verifyDrawable(Drawable drawable) {
        return drawable == this.f10890n || super.verifyDrawable(drawable);
    }
}
