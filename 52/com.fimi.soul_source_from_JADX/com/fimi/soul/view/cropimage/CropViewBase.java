package com.fimi.soul.view.cropimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.fimi.soul.view.photodraweeview.C2020f;

public abstract class CropViewBase extends ImageView {
    public static final int f10786g = 0;
    public static final int f10787h = 1;
    public static final int f10788i = 2;
    static final float f10789n = 1.25f;
    private static final String f10790o = "ImageViewTouchBase";
    protected Matrix f10791a;
    protected Matrix f10792b;
    public final C1994d f10793c;
    int f10794d;
    int f10795e;
    float f10796f;
    protected int f10797j;
    protected Handler f10798k;
    protected int f10799l;
    protected int f10800m;
    private final Matrix f10801p;
    private final float[] f10802q;
    private C1993c f10803r;
    private Runnable f10804s;

    public CropViewBase(Context context) {
        super(context);
        this.f10791a = new Matrix();
        this.f10792b = new Matrix();
        this.f10801p = new Matrix();
        this.f10802q = new float[9];
        this.f10793c = new C1994d(null);
        this.f10794d = -1;
        this.f10795e = -1;
        this.f10797j = f10786g;
        this.f10798k = new Handler();
        this.f10804s = null;
        m12824e();
    }

    public CropViewBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f10791a = new Matrix();
        this.f10792b = new Matrix();
        this.f10801p = new Matrix();
        this.f10802q = new float[9];
        this.f10793c = new C1994d(null);
        this.f10794d = -1;
        this.f10795e = -1;
        this.f10797j = f10786g;
        this.f10798k = new Handler();
        this.f10804s = null;
        m12824e();
    }

    private void m12822a(Bitmap bitmap, int i) {
        super.setImageBitmap(bitmap);
        Drawable drawable = getDrawable();
        if (drawable != null) {
            drawable.setDither(true);
        }
        Bitmap b = this.f10793c.m12845b();
        this.f10793c.m12844a(bitmap);
        this.f10793c.m12843a(i);
        if (b != null && b != bitmap && this.f10803r != null) {
            this.f10803r.m12841a(b);
        }
    }

    private void m12823a(C1994d c1994d, Matrix matrix) {
        float width = (float) getWidth();
        float height = (float) getHeight();
        float f = (float) c1994d.m12849f();
        float e = (float) c1994d.m12848e();
        matrix.reset();
        float min = Math.min(Math.min(width / f, 2.0f), Math.min(height / e, 2.0f));
        matrix.postConcat(c1994d.m12846c());
        matrix.postScale(min, min);
        matrix.postTranslate((width - (f * min)) / 2.0f, (height - (e * min)) / 2.0f);
    }

    private void m12824e() {
        setScaleType(ScaleType.MATRIX);
    }

    protected float m12825a(Matrix matrix) {
        return m12826a(matrix, (int) f10786g);
    }

    protected float m12826a(Matrix matrix, int i) {
        matrix.getValues(this.f10802q);
        return this.f10802q[i];
    }

    public void m12827a() {
        m12832a(null, true);
    }

    protected void m12828a(float f) {
        m12830a(f, ((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f);
    }

    protected void m12829a(float f, float f2) {
        this.f10792b.postTranslate(f, f2);
    }

    protected void m12830a(float f, float f2, float f3) {
        if (f > this.f10796f) {
            f = this.f10796f;
        }
        float scale = f / getScale();
        this.f10792b.postScale(scale, scale, f2, f3);
        setImageMatrix(getImageViewMatrix());
        m12834a(true, true);
    }

    protected void m12831a(float f, float f2, float f3, float f4) {
        float scale = (f - getScale()) / f4;
        float scale2 = getScale();
        this.f10798k.post(new C1992b(this, f4, System.currentTimeMillis(), scale2, scale, f2, f3));
    }

    public void m12832a(Bitmap bitmap, boolean z) {
        m12833a(new C1994d(bitmap), z);
    }

    public void m12833a(C1994d c1994d, boolean z) {
        if (getWidth() <= 0) {
            this.f10804s = new C1991a(this, c1994d, z);
            return;
        }
        if (c1994d.m12845b() != null) {
            m12823a(c1994d, this.f10791a);
            m12822a(c1994d.m12845b(), c1994d.m12842a());
        } else {
            this.f10791a.reset();
            setImageBitmap(null);
        }
        if (z) {
            this.f10792b.reset();
        }
        setImageMatrix(getImageViewMatrix());
        this.f10796f = m12835b();
    }

    public void m12834a(boolean z, boolean z2) {
        float f = 0.0f;
        if (this.f10793c.m12845b() != null) {
            int height;
            Matrix imageViewMatrix = getImageViewMatrix();
            RectF rectF = new RectF(0.0f, 0.0f, (float) this.f10793c.m12845b().getWidth(), (float) this.f10793c.m12845b().getHeight());
            imageViewMatrix.mapRect(rectF);
            float height2 = rectF.height();
            float width = rectF.width();
            if (z2) {
                height = getHeight();
                if (height2 < ((float) height)) {
                    height2 = ((((float) height) - height2) / 2.0f) - rectF.top;
                } else if (rectF.top > 0.0f) {
                    height2 = -rectF.top;
                } else if (rectF.bottom < ((float) height)) {
                    height2 = ((float) getHeight()) - rectF.bottom;
                }
                if (z) {
                    height = getWidth();
                    if (width < ((float) height)) {
                        f = ((((float) height) - width) / 2.0f) - rectF.left;
                    } else if (rectF.left > 0.0f) {
                        f = -rectF.left;
                    } else if (rectF.right < ((float) height)) {
                        f = ((float) height) - rectF.right;
                    }
                }
                m12829a(f, height2);
                setImageMatrix(getImageViewMatrix());
            }
            height2 = 0.0f;
            if (z) {
                height = getWidth();
                if (width < ((float) height)) {
                    f = ((((float) height) - width) / 2.0f) - rectF.left;
                } else if (rectF.left > 0.0f) {
                    f = -rectF.left;
                } else if (rectF.right < ((float) height)) {
                    f = ((float) height) - rectF.right;
                }
            }
            m12829a(f, height2);
            setImageMatrix(getImageViewMatrix());
        }
    }

    protected float m12835b() {
        if (this.f10793c.m12845b() == null) {
            return C2020f.f10933c;
        }
        float max = Math.max(((float) this.f10793c.m12849f()) / ((float) this.f10794d), ((float) this.f10793c.m12848e()) / ((float) this.f10795e)) * 4.0f;
        return max >= C2020f.f10933c ? max : C2020f.f10933c;
    }

    protected void m12836b(float f) {
        if (getScale() < this.f10796f && this.f10793c.m12845b() != null) {
            this.f10792b.postScale(f, f, ((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f);
            setImageMatrix(getImageViewMatrix());
        }
    }

    protected void m12837b(float f, float f2) {
        m12829a(f, f2);
        setImageMatrix(getImageViewMatrix());
    }

    protected void m12838c() {
        m12836b(f10789n);
    }

    protected void m12839c(float f) {
        if (this.f10793c.m12845b() != null) {
            float width = ((float) getWidth()) / 2.0f;
            float height = ((float) getHeight()) / 2.0f;
            Matrix matrix = new Matrix(this.f10792b);
            matrix.postScale(C2020f.f10933c / f, C2020f.f10933c / f, width, height);
            if (m12825a(matrix) < C2020f.f10933c) {
                this.f10792b.setScale(C2020f.f10933c, C2020f.f10933c, width, height);
            } else {
                this.f10792b.postScale(C2020f.f10933c / f, C2020f.f10933c / f, width, height);
            }
            setImageMatrix(getImageViewMatrix());
            m12834a(true, true);
        }
    }

    protected void m12840d() {
        m12839c(f10789n);
    }

    protected Matrix getImageViewMatrix() {
        this.f10801p.set(this.f10791a);
        this.f10801p.postConcat(this.f10792b);
        return this.f10801p;
    }

    public float getScale() {
        return m12825a(this.f10792b);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || getScale() <= C2020f.f10933c) {
            return super.onKeyDown(i, keyEvent);
        }
        m12828a((float) C2020f.f10933c);
        return true;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.f10794d = i3 - i;
        this.f10795e = i4 - i2;
        Runnable runnable = this.f10804s;
        if (runnable != null) {
            this.f10804s = null;
            runnable.run();
        }
        if (this.f10793c.m12845b() != null) {
            m12823a(this.f10793c, this.f10791a);
            setImageMatrix(getImageViewMatrix());
        }
    }

    public void setImageBitmap(Bitmap bitmap) {
        m12822a(bitmap, (int) f10786g);
    }

    public void setRecycler(C1993c c1993c) {
        this.f10803r = c1993c;
    }
}
