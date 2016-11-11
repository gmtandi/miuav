package com.fimi.soul.view.cropimage;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/* renamed from: com.fimi.soul.view.cropimage.d */
public class C1994d {
    public static final String f10815a = "RotateBitmap";
    private Bitmap f10816b;
    private int f10817c;

    public C1994d(Bitmap bitmap) {
        this.f10816b = bitmap;
        this.f10817c = 0;
    }

    public C1994d(Bitmap bitmap, int i) {
        this.f10816b = bitmap;
        this.f10817c = i % 360;
    }

    public int m12842a() {
        return this.f10817c;
    }

    public void m12843a(int i) {
        this.f10817c = i;
    }

    public void m12844a(Bitmap bitmap) {
        this.f10816b = bitmap;
    }

    public Bitmap m12845b() {
        return this.f10816b;
    }

    public Matrix m12846c() {
        Matrix matrix = new Matrix();
        if (this.f10817c != 0) {
            matrix.preTranslate((float) (-(this.f10816b.getWidth() / 2)), (float) (-(this.f10816b.getHeight() / 2)));
            matrix.postRotate((float) this.f10817c);
            matrix.postTranslate((float) (m12849f() / 2), (float) (m12848e() / 2));
        }
        return matrix;
    }

    public boolean m12847d() {
        return (this.f10817c / 90) % 2 != 0;
    }

    public int m12848e() {
        return m12847d() ? this.f10816b.getWidth() : this.f10816b.getHeight();
    }

    public int m12849f() {
        return m12847d() ? this.f10816b.getHeight() : this.f10816b.getWidth();
    }

    public void m12850g() {
        if (this.f10816b != null) {
            this.f10816b.recycle();
            this.f10816b = null;
        }
    }
}
