package com.fimi.soul.view.photodraweeview;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import com.amap.api.maps.model.GroundOverlayOptions;
import com.facebook.drawee.drawable.ScalingUtils.ScaleType;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.DraweeView;
import java.lang.ref.WeakReference;
import org.codehaus.jackson.org.objectweb.asm.Type;

/* renamed from: com.fimi.soul.view.photodraweeview.a */
public class C2022a implements OnTouchListener, C2020f, C2021i {
    private static final int f10936e = -1;
    private static final int f10937f = 0;
    private static final int f10938g = 1;
    private static final int f10939h = 2;
    private C2028j f10940A;
    private OnLongClickListener f10941B;
    private C2027h f10942C;
    private final float[] f10943i;
    private final RectF f10944j;
    private final Interpolator f10945k;
    private float f10946l;
    private float f10947m;
    private float f10948n;
    private long f10949o;
    private C2029k f10950p;
    private GestureDetectorCompat f10951q;
    private boolean f10952r;
    private boolean f10953s;
    private int f10954t;
    private final Matrix f10955u;
    private int f10956v;
    private int f10957w;
    private C2025d f10958x;
    private WeakReference<DraweeView<GenericDraweeHierarchy>> f10959y;
    private C1587g f10960z;

    public C2022a(DraweeView<GenericDraweeHierarchy> draweeView) {
        this.f10943i = new float[9];
        this.f10944j = new RectF();
        this.f10945k = new AccelerateDecelerateInterpolator();
        this.f10946l = C2020f.f10933c;
        this.f10947m = C2020f.f10932b;
        this.f10948n = C2020f.f10931a;
        this.f10949o = 200;
        this.f10952r = false;
        this.f10953s = true;
        this.f10954t = f10939h;
        this.f10955u = new Matrix();
        this.f10956v = f10936e;
        this.f10957w = f10936e;
        this.f10959y = new WeakReference(draweeView);
        ((GenericDraweeHierarchy) draweeView.getHierarchy()).setActualImageScaleType(ScaleType.FIT_CENTER);
        draweeView.setOnTouchListener(this);
        this.f10950p = new C2029k(draweeView.getContext(), this);
        this.f10951q = new GestureDetectorCompat(draweeView.getContext(), new C2023b(this));
        this.f10951q.setOnDoubleTapListener(new C2026e(this));
    }

    private float m12939a(Matrix matrix, int i) {
        matrix.getValues(this.f10943i);
        return this.f10943i[i];
    }

    private RectF m12940a(Matrix matrix) {
        DraweeView a = m12954a();
        if (a == null || (this.f10957w == f10936e && this.f10956v == f10936e)) {
            return null;
        }
        this.f10944j.set(0.0f, 0.0f, (float) this.f10957w, (float) this.f10956v);
        ((GenericDraweeHierarchy) a.getHierarchy()).getActualImageBounds(this.f10944j);
        matrix.mapRect(this.f10944j);
        return this.f10944j;
    }

    private void m12942a(View view, Runnable runnable) {
        if (VERSION.SDK_INT >= 16) {
            view.postOnAnimation(runnable);
        } else {
            view.postDelayed(runnable, 16);
        }
    }

    private static void m12945b(float f, float f2, float f3) {
        if (f >= f2) {
            throw new IllegalArgumentException("MinZoom has to be less than MidZoom");
        } else if (f2 >= f3) {
            throw new IllegalArgumentException("MidZoom has to be less than MaxZoom");
        }
    }

    private int m12948h() {
        DraweeView a = m12954a();
        return a != null ? (a.getWidth() - a.getPaddingLeft()) - a.getPaddingRight() : f10937f;
    }

    private int m12949i() {
        DraweeView a = m12954a();
        return a != null ? (a.getHeight() - a.getPaddingTop()) - a.getPaddingBottom() : f10937f;
    }

    private void m12950j() {
        if (this.f10957w != f10936e || this.f10956v != f10936e) {
            m12951k();
        }
    }

    private void m12951k() {
        this.f10955u.reset();
        m12964e();
        DraweeView a = m12954a();
        if (a != null) {
            a.invalidate();
        }
    }

    private void m12952l() {
        DraweeView a = m12954a();
        if (a != null && getScale() < this.f10946l) {
            RectF c = m12962c();
            if (c != null) {
                a.post(new C2024c(this, getScale(), this.f10946l, c.centerX(), c.centerY()));
            }
        }
    }

    private void m12953m() {
        if (this.f10958x != null) {
            this.f10958x.m12968a();
            this.f10958x = null;
        }
    }

    @Nullable
    public DraweeView<GenericDraweeHierarchy> m12954a() {
        return (DraweeView) this.f10959y.get();
    }

    public void m12955a(float f, float f2) {
        DraweeView a = m12954a();
        if (a != null && !this.f10950p.m12977a()) {
            this.f10955u.postTranslate(f, f2);
            m12963d();
            ViewParent parent = a.getParent();
            if (parent != null) {
                if (!this.f10953s || this.f10950p.m12977a() || this.f10952r) {
                    parent.requestDisallowInterceptTouchEvent(true);
                } else if (this.f10954t == f10939h || ((this.f10954t == 0 && f >= C2020f.f10933c) || (this.f10954t == f10938g && f <= GroundOverlayOptions.NO_DIMENSION))) {
                    parent.requestDisallowInterceptTouchEvent(false);
                }
            }
        }
    }

    public void m12956a(float f, float f2, float f3) {
        if (getScale() < this.f10948n || f < C2020f.f10933c) {
            if (this.f10942C != null) {
                this.f10942C.m12971a(f, f2, f3);
            }
            this.f10955u.postScale(f, f, f2, f3);
            m12963d();
        }
    }

    public void m12957a(float f, float f2, float f3, float f4) {
        DraweeView a = m12954a();
        if (a != null) {
            this.f10958x = new C2025d(this, a.getContext());
            this.f10958x.m12969a(m12948h(), m12949i(), (int) f3, (int) f4);
            a.post(this.f10958x);
        }
    }

    public void m12958a(float f, float f2, float f3, boolean z) {
        DraweeView a = m12954a();
        if (a != null && f >= this.f10946l && f <= this.f10948n) {
            if (z) {
                a.post(new C2024c(this, getScale(), f, f2, f3));
                return;
            }
            this.f10955u.setScale(f, f, f2, f3);
            m12963d();
        }
    }

    public void m12959a(float f, boolean z) {
        DraweeView a = m12954a();
        if (a != null) {
            m12958a(f, (float) (a.getRight() / f10939h), (float) (a.getBottom() / f10939h), false);
        }
    }

    public void m12960a(int i, int i2) {
        this.f10957w = i;
        this.f10956v = i2;
        m12950j();
    }

    public Matrix m12961b() {
        return this.f10955u;
    }

    public RectF m12962c() {
        m12964e();
        return m12940a(m12961b());
    }

    public void m12963d() {
        DraweeView a = m12954a();
        if (a != null && m12964e()) {
            a.invalidate();
        }
    }

    public boolean m12964e() {
        float f = 0.0f;
        RectF a = m12940a(m12961b());
        if (a == null) {
            return false;
        }
        float height = a.height();
        float width = a.width();
        int i = m12949i();
        height = height <= ((float) i) ? ((((float) i) - height) / 2.0f) - a.top : a.top > 0.0f ? -a.top : a.bottom < ((float) i) ? ((float) i) - a.bottom : 0.0f;
        i = m12948h();
        if (width <= ((float) i)) {
            f = ((((float) i) - width) / 2.0f) - a.left;
            this.f10954t = f10939h;
        } else if (a.left > 0.0f) {
            f = -a.left;
            this.f10954t = f10937f;
        } else if (a.right < ((float) i)) {
            f = ((float) i) - a.right;
            this.f10954t = f10938g;
        } else {
            this.f10954t = f10936e;
        }
        this.f10955u.postTranslate(f, height);
        return true;
    }

    public void m12965f() {
        m12952l();
    }

    protected void m12966g() {
        m12953m();
    }

    public float getMaximumScale() {
        return this.f10948n;
    }

    public float getMediumScale() {
        return this.f10947m;
    }

    public float getMinimumScale() {
        return this.f10946l;
    }

    public C1587g getOnPhotoTapListener() {
        return this.f10960z;
    }

    public C2028j getOnViewTapListener() {
        return this.f10940A;
    }

    public float getScale() {
        return (float) Math.sqrt((double) (((float) Math.pow((double) m12939a(this.f10955u, (int) f10937f), 2.0d)) + ((float) Math.pow((double) m12939a(this.f10955u, 3), 2.0d))));
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z = false;
        ViewParent parent;
        switch (MotionEventCompat.getActionMasked(motionEvent)) {
            case f10937f /*0*/:
                parent = view.getParent();
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
                m12953m();
                break;
            case f10938g /*1*/:
            case Type.BYTE /*3*/:
                parent = view.getParent();
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(false);
                    break;
                }
                break;
        }
        boolean a = this.f10950p.m12977a();
        boolean b = this.f10950p.m12979b();
        boolean a2 = this.f10950p.m12978a(motionEvent);
        boolean z2 = (a || this.f10950p.m12977a()) ? false : true;
        a = (b || this.f10950p.m12979b()) ? false : true;
        if (z2 && a) {
            z = true;
        }
        this.f10952r = z;
        return this.f10951q.onTouchEvent(motionEvent) ? true : a2;
    }

    public void setAllowParentInterceptOnEdge(boolean z) {
        this.f10953s = z;
    }

    public void setMaximumScale(float f) {
        C2022a.m12945b(this.f10946l, this.f10947m, f);
        this.f10948n = f;
    }

    public void setMediumScale(float f) {
        C2022a.m12945b(this.f10946l, f, this.f10948n);
        this.f10947m = f;
    }

    public void setMinimumScale(float f) {
        C2022a.m12945b(f, this.f10947m, this.f10948n);
        this.f10946l = f;
    }

    public void setOnDoubleTapListener(OnDoubleTapListener onDoubleTapListener) {
        if (onDoubleTapListener != null) {
            this.f10951q.setOnDoubleTapListener(onDoubleTapListener);
        } else {
            this.f10951q.setOnDoubleTapListener(new C2026e(this));
        }
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.f10941B = onLongClickListener;
    }

    public void setOnPhotoTapListener(C1587g c1587g) {
        this.f10960z = c1587g;
    }

    public void setOnScaleChangeListener(C2027h c2027h) {
        this.f10942C = c2027h;
    }

    public void setOnViewTapListener(C2028j c2028j) {
        this.f10940A = c2028j;
    }

    public void setScale(float f) {
        m12959a(f, false);
    }

    public void setZoomTransitionDuration(long j) {
        if (j < 0) {
            j = 200;
        }
        this.f10949o = j;
    }
}
