package com.p054c.p055a.p063b.p067c;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;

/* renamed from: com.c.a.b.c.d */
public class C0915d extends Drawable {
    protected final float f4815a;
    protected final int f4816b;
    protected final RectF f4817c;
    protected final RectF f4818d;
    protected final BitmapShader f4819e;
    protected final Paint f4820f;

    public C0915d(Bitmap bitmap, int i, int i2) {
        this.f4817c = new RectF();
        this.f4815a = (float) i;
        this.f4816b = i2;
        this.f4819e = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);
        this.f4818d = new RectF((float) i2, (float) i2, (float) (bitmap.getWidth() - i2), (float) (bitmap.getHeight() - i2));
        this.f4820f = new Paint();
        this.f4820f.setAntiAlias(true);
        this.f4820f.setShader(this.f4819e);
    }

    public void draw(Canvas canvas) {
        canvas.drawRoundRect(this.f4817c, this.f4815a, this.f4815a, this.f4820f);
    }

    public int getOpacity() {
        return -3;
    }

    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.f4817c.set((float) this.f4816b, (float) this.f4816b, (float) (rect.width() - this.f4816b), (float) (rect.height() - this.f4816b));
        Matrix matrix = new Matrix();
        matrix.setRectToRect(this.f4818d, this.f4817c, ScaleToFit.FILL);
        this.f4819e.setLocalMatrix(matrix);
    }

    public void setAlpha(int i) {
        this.f4820f.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.f4820f.setColorFilter(colorFilter);
    }
}
