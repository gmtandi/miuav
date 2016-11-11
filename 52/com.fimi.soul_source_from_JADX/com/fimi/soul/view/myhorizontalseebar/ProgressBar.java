package com.fimi.soul.view.myhorizontalseebar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.ViewDebug.ExportedProperty;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import com.fimi.soul.C1205R;
import com.fimi.soul.view.photodraweeview.C2020f;
import java.util.ArrayList;

public class ProgressBar extends View {
    private static final int f10851a = 10000;
    private static final int f10852b = 200;
    private boolean f10853A;
    private Interpolator f10854B;
    private C2014j f10855C;
    private long f10856D;
    private boolean f10857E;
    private boolean f10858F;
    private boolean f10859G;
    private boolean f10860H;
    private final ArrayList<C2012h> f10861I;
    private C2011g f10862J;
    int f10863c;
    int f10864d;
    int f10865e;
    int f10866f;
    Bitmap f10867g;
    protected int f10868h;
    protected int f10869i;
    protected int f10870j;
    protected int f10871k;
    protected int f10872l;
    protected int f10873m;
    private int f10874n;
    private int f10875o;
    private int f10876p;
    private int f10877q;
    private int f10878r;
    private boolean f10879s;
    private boolean f10880t;
    private Transformation f10881u;
    private AlphaAnimation f10882v;
    private boolean f10883w;
    private Drawable f10884x;
    private Drawable f10885y;
    private Drawable f10886z;

    class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        int f10897a;
        int f10898b;

        static {
            CREATOR = new C2015k();
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f10897a = parcel.readInt();
            this.f10898b = parcel.readInt();
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f10897a);
            parcel.writeInt(this.f10898b);
        }
    }

    public ProgressBar(Context context) {
        this(context, null);
    }

    public ProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842871);
    }

    public ProgressBar(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public ProgressBar(Context context, AttributeSet attributeSet, int i, int i2) {
        boolean z = false;
        super(context, attributeSet, i);
        this.f10861I = new ArrayList();
        this.f10856D = Thread.currentThread().getId();
        m12854a();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C1205R.styleable.ProgressBar, i, i2);
        this.f10853A = true;
        Drawable drawable = obtainStyledAttributes.getDrawable(6);
        if (drawable != null) {
            setProgressDrawable(m12852a(drawable, false));
        }
        this.f10878r = obtainStyledAttributes.getInt(7, this.f10878r);
        this.f10863c = obtainStyledAttributes.getDimensionPixelSize(4, this.f10863c);
        this.f10864d = obtainStyledAttributes.getDimensionPixelSize(0, this.f10864d);
        this.f10865e = obtainStyledAttributes.getDimensionPixelSize(5, this.f10865e);
        this.f10866f = obtainStyledAttributes.getDimensionPixelSize(1, this.f10866f);
        this.f10877q = obtainStyledAttributes.getInt(8, this.f10877q);
        setMax(obtainStyledAttributes.getInt(2, this.f10876p));
        setProgress(obtainStyledAttributes.getInt(3, this.f10874n));
        setSecondaryProgress(obtainStyledAttributes.getInt(9, this.f10875o));
        drawable = obtainStyledAttributes.getDrawable(10);
        if (drawable != null) {
            setIndeterminateDrawable(m12851a(drawable));
        }
        this.f10880t = obtainStyledAttributes.getBoolean(11, this.f10880t);
        this.f10853A = false;
        if (this.f10880t || obtainStyledAttributes.getBoolean(12, this.f10879s)) {
            z = true;
        }
        setIndeterminate(z);
        obtainStyledAttributes.recycle();
    }

    private Drawable m12851a(Drawable drawable) {
        if (!(drawable instanceof AnimationDrawable)) {
            return drawable;
        }
        AnimationDrawable animationDrawable = (AnimationDrawable) drawable;
        int numberOfFrames = animationDrawable.getNumberOfFrames();
        Drawable animationDrawable2 = new AnimationDrawable();
        animationDrawable2.setOneShot(animationDrawable.isOneShot());
        for (int i = 0; i < numberOfFrames; i++) {
            Drawable a = m12852a(animationDrawable.getFrame(i), true);
            a.setLevel(f10851a);
            animationDrawable2.addFrame(a, animationDrawable.getDuration(i));
        }
        animationDrawable2.setLevel(f10851a);
        return animationDrawable2;
    }

    private Drawable m12852a(Drawable drawable, boolean z) {
        int i = 0;
        if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            int numberOfLayers = layerDrawable.getNumberOfLayers();
            Drawable[] drawableArr = new Drawable[numberOfLayers];
            for (int i2 = 0; i2 < numberOfLayers; i2++) {
                int id = layerDrawable.getId(i2);
                Drawable drawable2 = layerDrawable.getDrawable(i2);
                boolean z2 = id == 16908301 || id == 16908303;
                drawableArr[i2] = m12852a(drawable2, z2);
            }
            Drawable layerDrawable2 = new LayerDrawable(drawableArr);
            while (i < numberOfLayers) {
                layerDrawable2.setId(i, layerDrawable.getId(i));
                i++;
            }
            return layerDrawable2;
        } else if (drawable instanceof StateListDrawable) {
            StateListDrawable stateListDrawable = (StateListDrawable) drawable;
            return new StateListDrawable();
        } else if (!(drawable instanceof BitmapDrawable)) {
            return drawable;
        } else {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            if (this.f10867g == null) {
                this.f10867g = bitmap;
            }
            Drawable shapeDrawable = new ShapeDrawable(getDrawableShape());
            shapeDrawable.getPaint().setShader(new BitmapShader(bitmap, TileMode.REPEAT, TileMode.CLAMP));
            return z ? new ClipDrawable(shapeDrawable, 3, 1) : shapeDrawable;
        }
    }

    private void m12854a() {
        this.f10876p = 100;
        this.f10874n = 0;
        this.f10875o = 0;
        this.f10879s = false;
        this.f10880t = false;
        this.f10878r = 4000;
        this.f10877q = 1;
        this.f10863c = 24;
        this.f10864d = 48;
        this.f10865e = 24;
        this.f10866f = 48;
    }

    private void m12855a(int i, int i2) {
        int i3;
        int i4 = i - (this.f10871k + this.f10870j);
        int i5 = i2 - (this.f10872l + this.f10873m);
        if (this.f10884x != null) {
            int i6;
            if (this.f10880t && !(this.f10884x instanceof AnimationDrawable)) {
                float intrinsicWidth = ((float) this.f10884x.getIntrinsicWidth()) / ((float) this.f10884x.getIntrinsicHeight());
                float f = ((float) i4) / ((float) i5);
                if (intrinsicWidth != f) {
                    if (f > intrinsicWidth) {
                        i3 = (int) (intrinsicWidth * ((float) i5));
                        i6 = (i4 - i3) / 2;
                        i3 = i6 + i3;
                        i4 = i5;
                        i5 = 0;
                    } else {
                        i3 = (int) ((C2020f.f10933c / intrinsicWidth) * ((float) i4));
                        i6 = (i5 - i3) / 2;
                        i5 = i6 + i3;
                        i3 = i4;
                        i4 = i5;
                        i5 = i6;
                        i6 = 0;
                    }
                    this.f10884x.setBounds(i6, i5, i3, i4);
                }
            }
            i6 = 0;
            i3 = i4;
            i4 = i5;
            i5 = 0;
            this.f10884x.setBounds(i6, i5, i3, i4);
        } else {
            i3 = i4;
            i4 = i5;
        }
        if (this.f10885y != null) {
            this.f10885y.setBounds(0, 0, i3, i4);
        }
    }

    private synchronized void m12856a(int i, int i2, boolean z) {
        if (this.f10856D == Thread.currentThread().getId()) {
            m12857a(i, i2, z, true);
        } else {
            if (this.f10855C == null) {
                this.f10855C = new C2014j();
            }
            this.f10861I.add(C2012h.m12898a(i, i2, z));
            if (this.f10859G && !this.f10860H) {
                post(this.f10855C);
                this.f10860H = true;
            }
        }
    }

    private synchronized void m12857a(int i, int i2, boolean z, boolean z2) {
        float f = this.f10876p > 0 ? ((float) i2) / ((float) this.f10876p) : 0.0f;
        Drawable drawable = this.f10886z;
        if (drawable != null) {
            Drawable drawable2 = null;
            if (drawable instanceof LayerDrawable) {
                drawable2 = ((LayerDrawable) drawable).findDrawableByLayerId(i);
            }
            int i3 = (int) (10000.0f * f);
            if (drawable2 != null) {
                drawable = drawable2;
            }
            drawable.setLevel(i3);
        } else {
            invalidate();
        }
        if (z2 && i == 16908301) {
            m12862a(f, z);
        }
    }

    private void m12860b() {
        int[] drawableState = getDrawableState();
        if (this.f10885y != null && this.f10885y.isStateful()) {
            this.f10885y.setState(drawableState);
        }
        if (this.f10884x != null && this.f10884x.isStateful()) {
            this.f10884x.setState(drawableState);
        }
    }

    private void m12861c() {
        if (this.f10862J == null) {
            this.f10862J = new C2011g();
        } else {
            removeCallbacks(this.f10862J);
        }
        postDelayed(this.f10862J, 200);
    }

    void m12862a(float f, boolean z) {
    }

    public void m12863a(int i) {
        Drawable drawable = this.f10886z;
    }

    synchronized void m12864a(int i, boolean z) {
        if (!this.f10879s) {
            int i2 = i < 0 ? 0 : i;
            if (i2 > this.f10876p) {
                i2 = this.f10876p;
            }
            if (i2 != this.f10874n) {
                this.f10874n = i2;
                m12856a(16908301, this.f10874n, z);
            }
        }
    }

    public void m12865a(Context context, int i) {
        setInterpolator(AnimationUtils.loadInterpolator(context, i));
    }

    public final synchronized void m12866b(int i) {
        setProgress(this.f10874n + i);
    }

    public final synchronized void m12867c(int i) {
        setSecondaryProgress(this.f10875o + i);
    }

    @ExportedProperty(category = "progress")
    public synchronized boolean m12868d() {
        return this.f10879s;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        m12860b();
    }

    void m12869e() {
        if (getVisibility() == 0) {
            if (this.f10884x instanceof Animatable) {
                this.f10857E = true;
                this.f10883w = false;
            } else {
                this.f10883w = true;
                if (this.f10854B == null) {
                    this.f10854B = new LinearInterpolator();
                }
                if (this.f10881u == null) {
                    this.f10881u = new Transformation();
                } else {
                    this.f10881u.clear();
                }
                if (this.f10882v == null) {
                    this.f10882v = new AlphaAnimation(0.0f, C2020f.f10933c);
                } else {
                    this.f10882v.reset();
                }
                this.f10882v.setRepeatMode(this.f10877q);
                this.f10882v.setRepeatCount(-1);
                this.f10882v.setDuration((long) this.f10878r);
                this.f10882v.setInterpolator(this.f10854B);
                this.f10882v.setStartTime(-1);
            }
            postInvalidate();
        }
    }

    void m12870f() {
        this.f10883w = false;
        if (this.f10884x instanceof Animatable) {
            ((Animatable) this.f10884x).stop();
            this.f10857E = false;
        }
        postInvalidate();
    }

    Drawable getCurrentDrawable() {
        return this.f10886z;
    }

    Shape getDrawableShape() {
        return new RoundRectShape(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f}, null, null);
    }

    public Drawable getIndeterminateDrawable() {
        return this.f10884x;
    }

    public Interpolator getInterpolator() {
        return this.f10854B;
    }

    @ExportedProperty(category = "progress")
    public synchronized int getMax() {
        return this.f10876p;
    }

    @ExportedProperty(category = "progress")
    public synchronized int getProgress() {
        return this.f10879s ? 0 : this.f10874n;
    }

    public Drawable getProgressDrawable() {
        return this.f10885y;
    }

    @ExportedProperty(category = "progress")
    public synchronized int getSecondaryProgress() {
        return this.f10879s ? 0 : this.f10875o;
    }

    public void invalidateDrawable(Drawable drawable) {
        if (!this.f10858F) {
            if (verifyDrawable(drawable)) {
                Rect bounds = drawable.getBounds();
                int i = this.f10868h + this.f10870j;
                int i2 = this.f10869i + this.f10872l;
                invalidate(bounds.left + i, bounds.top + i2, i + bounds.right, bounds.bottom + i2);
                return;
            }
            super.invalidateDrawable(drawable);
        }
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        if (this.f10885y != null) {
            this.f10885y.jumpToCurrentState();
        }
        if (this.f10884x != null) {
            this.f10884x.jumpToCurrentState();
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f10879s) {
            m12869e();
        }
        if (this.f10861I != null) {
            synchronized (this) {
                int size = this.f10861I.size();
                for (int i = 0; i < size; i++) {
                    C2012h c2012h = (C2012h) this.f10861I.get(i);
                    m12857a(c2012h.f10909a, c2012h.f10910b, c2012h.f10911c, true);
                    c2012h.m12904c();
                }
                this.f10861I.clear();
            }
        }
        this.f10859G = true;
    }

    protected void onDetachedFromWindow() {
        if (this.f10879s) {
            m12870f();
        }
        if (this.f10855C != null) {
            removeCallbacks(this.f10855C);
        }
        if (this.f10855C != null && this.f10860H) {
            removeCallbacks(this.f10855C);
        }
        if (this.f10862J != null) {
            removeCallbacks(this.f10862J);
        }
        super.onDetachedFromWindow();
        this.f10859G = false;
    }

    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Drawable drawable = this.f10886z;
        if (drawable != null) {
            canvas.save();
            canvas.translate((float) this.f10870j, (float) this.f10872l);
            long drawingTime = getDrawingTime();
            if (this.f10883w) {
                this.f10882v.getTransformation(drawingTime, this.f10881u);
                float alpha = this.f10881u.getAlpha();
                try {
                    this.f10858F = true;
                    drawable.setLevel((int) (alpha * 10000.0f));
                    this.f10858F = false;
                    postInvalidateOnAnimation();
                } catch (Throwable th) {
                    this.f10858F = false;
                }
            }
            drawable.draw(canvas);
            canvas.restore();
            if (this.f10857E && (drawable instanceof Animatable)) {
                ((Animatable) drawable).start();
                this.f10857E = false;
            }
        }
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(ProgressBar.class.getName());
        accessibilityEvent.setItemCount(this.f10876p);
        accessibilityEvent.setCurrentItemIndex(this.f10874n);
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(ProgressBar.class.getName());
    }

    protected synchronized void onMeasure(int i, int i2) {
        int i3 = 0;
        synchronized (this) {
            int max;
            Drawable drawable = this.f10886z;
            if (drawable != null) {
                max = Math.max(this.f10863c, Math.min(this.f10864d, drawable.getIntrinsicWidth()));
                i3 = Math.max(this.f10865e, Math.min(this.f10866f, drawable.getIntrinsicHeight()));
            } else {
                max = 0;
            }
            m12860b();
            setMeasuredDimension(resolveSizeAndState(max + (this.f10870j + this.f10871k), i, 0), resolveSizeAndState(i3 + (this.f10872l + this.f10873m), i2, 0));
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setProgress(savedState.f10897a);
        setSecondaryProgress(savedState.f10898b);
    }

    public Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.f10897a = this.f10874n;
        savedState.f10898b = this.f10875o;
        return savedState;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        m12855a(i, i2);
    }

    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (!this.f10879s) {
            return;
        }
        if (i == 8 || i == 4) {
            m12870f();
        } else {
            m12869e();
        }
    }

    public void postInvalidate() {
        if (!this.f10853A) {
            super.postInvalidate();
        }
    }

    public synchronized void setIndeterminate(boolean z) {
        if (!((this.f10880t && this.f10879s) || z == this.f10879s)) {
            this.f10879s = z;
            if (z) {
                this.f10886z = this.f10884x;
                m12869e();
            } else {
                this.f10886z = this.f10885y;
                m12870f();
            }
        }
    }

    public void setIndeterminateDrawable(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(this);
        }
        if (this.f10879s) {
            this.f10886z = drawable;
            postInvalidate();
        }
    }

    public void setInterpolator(Interpolator interpolator) {
        this.f10854B = interpolator;
    }

    public synchronized void setMax(int i) {
        if (i < 0) {
            i = 0;
        }
        if (i != this.f10876p) {
            this.f10876p = i;
            postInvalidate();
            if (this.f10874n > i) {
                this.f10874n = i;
            }
            m12856a(16908301, this.f10874n, false);
        }
    }

    public synchronized void setProgress(int i) {
        m12864a(i, false);
    }

    public void setProgressDrawable(Drawable drawable) {
        boolean z;
        if (this.f10885y == null || drawable == this.f10885y) {
            z = false;
        } else {
            this.f10885y.setCallback(null);
            z = true;
        }
        if (drawable != null) {
            drawable.setCallback(this);
            int minimumHeight = drawable.getMinimumHeight();
            if (this.f10866f < minimumHeight) {
                this.f10866f = minimumHeight;
                requestLayout();
            }
        }
        this.f10885y = drawable;
        if (!this.f10879s) {
            this.f10886z = drawable;
            postInvalidate();
        }
        if (z) {
            m12855a(getWidth(), getHeight());
            m12860b();
            m12857a(16908301, this.f10874n, false, false);
            m12857a(16908303, this.f10875o, false, false);
        }
    }

    public synchronized void setSecondaryProgress(int i) {
        int i2 = 0;
        synchronized (this) {
            if (!this.f10879s) {
                if (i >= 0) {
                    i2 = i;
                }
                if (i2 > this.f10876p) {
                    i2 = this.f10876p;
                }
                if (i2 != this.f10875o) {
                    this.f10875o = i2;
                    m12856a(16908303, this.f10875o, false);
                }
            }
        }
    }

    public void setVisibility(int i) {
        if (getVisibility() != i) {
            super.setVisibility(i);
            if (!this.f10879s) {
                return;
            }
            if (i == 8 || i == 4) {
                m12870f();
            } else {
                m12869e();
            }
        }
    }

    protected boolean verifyDrawable(Drawable drawable) {
        return drawable == this.f10885y || drawable == this.f10884x || super.verifyDrawable(drawable);
    }
}
