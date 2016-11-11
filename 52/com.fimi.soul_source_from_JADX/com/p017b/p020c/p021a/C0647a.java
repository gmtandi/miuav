package com.p017b.p020c.p021a;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import com.fimi.soul.view.photodraweeview.C2020f;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/* renamed from: com.b.c.a.a */
public final class C0647a extends Animation {
    public static final boolean f3927a;
    private static final WeakHashMap<View, C0647a> f3928b;
    private final WeakReference<View> f3929c;
    private final Camera f3930d;
    private boolean f3931e;
    private float f3932f;
    private float f3933g;
    private float f3934h;
    private float f3935i;
    private float f3936j;
    private float f3937k;
    private float f3938l;
    private float f3939m;
    private float f3940n;
    private float f3941o;
    private final RectF f3942p;
    private final RectF f3943q;
    private final Matrix f3944r;

    static {
        f3927a = Integer.valueOf(VERSION.SDK).intValue() < 11;
        f3928b = new WeakHashMap();
    }

    private C0647a(View view) {
        this.f3930d = new Camera();
        this.f3932f = C2020f.f10933c;
        this.f3938l = C2020f.f10933c;
        this.f3939m = C2020f.f10933c;
        this.f3942p = new RectF();
        this.f3943q = new RectF();
        this.f3944r = new Matrix();
        setDuration(0);
        setFillAfter(true);
        view.setAnimation(this);
        this.f3929c = new WeakReference(view);
    }

    public static C0647a m5712a(View view) {
        Animation animation = (C0647a) f3928b.get(view);
        if (animation != null && animation == view.getAnimation()) {
            return animation;
        }
        C0647a c0647a = new C0647a(view);
        f3928b.put(view, c0647a);
        return c0647a;
    }

    private void m5713a(Matrix matrix, View view) {
        float width = (float) view.getWidth();
        float height = (float) view.getHeight();
        boolean z = this.f3931e;
        float f = z ? this.f3933g : width / 2.0f;
        float f2 = z ? this.f3934h : height / 2.0f;
        float f3 = this.f3935i;
        float f4 = this.f3936j;
        float f5 = this.f3937k;
        if (!(f3 == 0.0f && f4 == 0.0f && f5 == 0.0f)) {
            Camera camera = this.f3930d;
            camera.save();
            camera.rotateX(f3);
            camera.rotateY(f4);
            camera.rotateZ(-f5);
            camera.getMatrix(matrix);
            camera.restore();
            matrix.preTranslate(-f, -f2);
            matrix.postTranslate(f, f2);
        }
        f3 = this.f3938l;
        f4 = this.f3939m;
        if (!(f3 == C2020f.f10933c && f4 == C2020f.f10933c)) {
            matrix.postScale(f3, f4);
            matrix.postTranslate((-(f / width)) * ((f3 * width) - width), (-(f2 / height)) * ((f4 * height) - height));
        }
        matrix.postTranslate(this.f3940n, this.f3941o);
    }

    private void m5714a(RectF rectF, View view) {
        rectF.set(0.0f, 0.0f, (float) view.getWidth(), (float) view.getHeight());
        Matrix matrix = this.f3944r;
        matrix.reset();
        m5713a(matrix, view);
        this.f3944r.mapRect(rectF);
        rectF.offset((float) view.getLeft(), (float) view.getTop());
        if (rectF.right < rectF.left) {
            float f = rectF.right;
            rectF.right = rectF.left;
            rectF.left = f;
        }
        if (rectF.bottom < rectF.top) {
            f = rectF.top;
            rectF.top = rectF.bottom;
            rectF.bottom = f;
        }
    }

    private void m5715o() {
        View view = (View) this.f3929c.get();
        if (view != null) {
            m5714a(this.f3942p, view);
        }
    }

    private void m5716p() {
        View view = (View) this.f3929c.get();
        if (view != null && view.getParent() != null) {
            RectF rectF = this.f3943q;
            m5714a(rectF, view);
            rectF.union(this.f3942p);
            ((View) view.getParent()).invalidate((int) Math.floor((double) rectF.left), (int) Math.floor((double) rectF.top), (int) Math.ceil((double) rectF.right), (int) Math.ceil((double) rectF.bottom));
        }
    }

    public float m5717a() {
        return this.f3932f;
    }

    public void m5718a(float f) {
        if (this.f3932f != f) {
            this.f3932f = f;
            View view = (View) this.f3929c.get();
            if (view != null) {
                view.invalidate();
            }
        }
    }

    public void m5719a(int i) {
        View view = (View) this.f3929c.get();
        if (view != null) {
            view.scrollTo(i, view.getScrollY());
        }
    }

    protected void applyTransformation(float f, Transformation transformation) {
        View view = (View) this.f3929c.get();
        if (view != null) {
            transformation.setAlpha(this.f3932f);
            m5713a(transformation.getMatrix(), view);
        }
    }

    public float m5720b() {
        return this.f3933g;
    }

    public void m5721b(float f) {
        if (!this.f3931e || this.f3933g != f) {
            m5715o();
            this.f3931e = true;
            this.f3933g = f;
            m5716p();
        }
    }

    public void m5722b(int i) {
        View view = (View) this.f3929c.get();
        if (view != null) {
            view.scrollTo(view.getScrollX(), i);
        }
    }

    public float m5723c() {
        return this.f3934h;
    }

    public void m5724c(float f) {
        if (!this.f3931e || this.f3934h != f) {
            m5715o();
            this.f3931e = true;
            this.f3934h = f;
            m5716p();
        }
    }

    public float m5725d() {
        return this.f3937k;
    }

    public void m5726d(float f) {
        if (this.f3937k != f) {
            m5715o();
            this.f3937k = f;
            m5716p();
        }
    }

    public float m5727e() {
        return this.f3935i;
    }

    public void m5728e(float f) {
        if (this.f3935i != f) {
            m5715o();
            this.f3935i = f;
            m5716p();
        }
    }

    public float m5729f() {
        return this.f3936j;
    }

    public void m5730f(float f) {
        if (this.f3936j != f) {
            m5715o();
            this.f3936j = f;
            m5716p();
        }
    }

    public float m5731g() {
        return this.f3938l;
    }

    public void m5732g(float f) {
        if (this.f3938l != f) {
            m5715o();
            this.f3938l = f;
            m5716p();
        }
    }

    public float m5733h() {
        return this.f3939m;
    }

    public void m5734h(float f) {
        if (this.f3939m != f) {
            m5715o();
            this.f3939m = f;
            m5716p();
        }
    }

    public int m5735i() {
        View view = (View) this.f3929c.get();
        return view == null ? 0 : view.getScrollX();
    }

    public void m5736i(float f) {
        if (this.f3940n != f) {
            m5715o();
            this.f3940n = f;
            m5716p();
        }
    }

    public int m5737j() {
        View view = (View) this.f3929c.get();
        return view == null ? 0 : view.getScrollY();
    }

    public void m5738j(float f) {
        if (this.f3941o != f) {
            m5715o();
            this.f3941o = f;
            m5716p();
        }
    }

    public float m5739k() {
        return this.f3940n;
    }

    public void m5740k(float f) {
        View view = (View) this.f3929c.get();
        if (view != null) {
            m5736i(f - ((float) view.getLeft()));
        }
    }

    public float m5741l() {
        return this.f3941o;
    }

    public void m5742l(float f) {
        View view = (View) this.f3929c.get();
        if (view != null) {
            m5738j(f - ((float) view.getTop()));
        }
    }

    public float m5743m() {
        View view = (View) this.f3929c.get();
        return view == null ? 0.0f : ((float) view.getLeft()) + this.f3940n;
    }

    public float m5744n() {
        View view = (View) this.f3929c.get();
        return view == null ? 0.0f : ((float) view.getTop()) + this.f3941o;
    }
}
