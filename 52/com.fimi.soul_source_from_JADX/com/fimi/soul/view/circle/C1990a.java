package com.fimi.soul.view.circle;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;

/* renamed from: com.fimi.soul.view.circle.a */
public class C1990a extends Drawable {
    public static final String f10780a = "CircleDrawable";
    protected final Paint f10781b;
    protected final int f10782c;
    protected final BitmapShader f10783d;
    protected float f10784e;
    protected Bitmap f10785f;

    public C1990a(Bitmap bitmap) {
        this(bitmap, 0);
    }

    public C1990a(Bitmap bitmap, int i) {
        this.f10782c = i;
        this.f10785f = bitmap;
        this.f10783d = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);
        this.f10781b = new Paint();
        this.f10781b.setAntiAlias(true);
        this.f10781b.setShader(this.f10783d);
    }

    public void m12820a() {
        Rect bounds = getBounds();
        if (bounds != null) {
            Matrix matrix = new Matrix();
            float width = ((float) bounds.width()) / ((float) this.f10785f.getWidth());
            float height = ((float) bounds.height()) / ((float) this.f10785f.getHeight());
            if (width <= height) {
                width = height;
            }
            matrix.postScale(width, width);
            this.f10783d.setLocalMatrix(matrix);
        }
    }

    public void m12821b() {
        Rect bounds = getBounds();
        this.f10784e = bounds.width() < bounds.height() ? (((float) bounds.width()) / 2.0f) - ((float) this.f10782c) : (((float) bounds.height()) / 2.0f) - ((float) this.f10782c);
    }

    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        canvas.drawCircle(((float) bounds.width()) / 2.0f, ((float) bounds.height()) / 2.0f, this.f10784e, this.f10781b);
    }

    public int getOpacity() {
        return -3;
    }

    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        m12820a();
        m12821b();
    }

    public void setAlpha(int i) {
        this.f10781b.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.f10781b.setColorFilter(colorFilter);
    }
}
