package com.fimi.soul.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.fimi.soul.C1205R;

public class CircleImageView extends ImageView {
    private static final ScaleType f10301a;
    private static final Config f10302b;
    private static final int f10303c = 2;
    private static final int f10304d = 0;
    private static final int f10305e = -16777216;
    private final RectF f10306f;
    private final RectF f10307g;
    private final Matrix f10308h;
    private final Paint f10309i;
    private final Paint f10310j;
    private int f10311k;
    private int f10312l;
    private Bitmap f10313m;
    private BitmapShader f10314n;
    private int f10315o;
    private int f10316p;
    private float f10317q;
    private float f10318r;
    private boolean f10319s;
    private boolean f10320t;

    static {
        f10301a = ScaleType.CENTER_CROP;
        f10302b = Config.ARGB_8888;
    }

    public CircleImageView(Context context) {
        super(context);
        this.f10306f = new RectF();
        this.f10307g = new RectF();
        this.f10308h = new Matrix();
        this.f10309i = new Paint();
        this.f10310j = new Paint();
        this.f10311k = f10305e;
        this.f10312l = f10304d;
        m12575a();
    }

    public CircleImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, f10304d);
    }

    public CircleImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f10306f = new RectF();
        this.f10307g = new RectF();
        this.f10308h = new Matrix();
        this.f10309i = new Paint();
        this.f10310j = new Paint();
        this.f10311k = f10305e;
        this.f10312l = f10304d;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C1205R.styleable.CircleImageView, i, f10304d);
        this.f10312l = obtainStyledAttributes.getDimensionPixelSize(f10304d, f10304d);
        this.f10311k = obtainStyledAttributes.getColor(1, f10305e);
        obtainStyledAttributes.recycle();
        m12575a();
    }

    private Bitmap m12574a(Drawable drawable) {
        if (drawable == null) {
            return BitmapFactory.decodeResource(getResources(), C1205R.drawable.defaultavatar);
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            Bitmap createBitmap = drawable instanceof ColorDrawable ? Bitmap.createBitmap(f10303c, f10303c, f10302b) : Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), f10302b);
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(f10304d, f10304d, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return createBitmap;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }

    private void m12575a() {
        super.setScaleType(f10301a);
        this.f10319s = true;
        if (this.f10320t) {
            m12576b();
            this.f10320t = false;
        }
    }

    private void m12576b() {
        if (!this.f10319s) {
            this.f10320t = true;
        } else if (this.f10313m != null) {
            this.f10314n = new BitmapShader(this.f10313m, TileMode.CLAMP, TileMode.CLAMP);
            this.f10309i.setAntiAlias(true);
            this.f10309i.setShader(this.f10314n);
            this.f10310j.setStyle(Style.STROKE);
            this.f10310j.setAntiAlias(true);
            this.f10310j.setColor(this.f10311k);
            this.f10310j.setStrokeWidth((float) this.f10312l);
            this.f10316p = this.f10313m.getHeight();
            this.f10315o = this.f10313m.getWidth();
            this.f10307g.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
            this.f10318r = Math.min((this.f10307g.height() - ((float) this.f10312l)) / 2.0f, (this.f10307g.width() - ((float) this.f10312l)) / 2.0f);
            this.f10306f.set((float) this.f10312l, (float) this.f10312l, this.f10307g.width() - ((float) this.f10312l), this.f10307g.height() - ((float) this.f10312l));
            this.f10317q = Math.min(this.f10306f.height() / 2.0f, this.f10306f.width() / 2.0f);
            m12577c();
            invalidate();
        }
    }

    private void m12577c() {
        float height;
        float width;
        float f = 0.0f;
        this.f10308h.set(null);
        if (((float) this.f10315o) * this.f10306f.height() > this.f10306f.width() * ((float) this.f10316p)) {
            height = this.f10306f.height() / ((float) this.f10316p);
            width = (this.f10306f.width() - (((float) this.f10315o) * height)) * 0.5f;
        } else {
            height = this.f10306f.width() / ((float) this.f10315o);
            width = 0.0f;
            f = (this.f10306f.height() - (((float) this.f10316p) * height)) * 0.5f;
        }
        this.f10308h.setScale(height, height);
        this.f10308h.postTranslate((float) (((int) (width + 0.5f)) + this.f10312l), (float) (((int) (f + 0.5f)) + this.f10312l));
        this.f10314n.setLocalMatrix(this.f10308h);
    }

    public int getBorderColor() {
        return this.f10311k;
    }

    public int getBorderWidth() {
        return this.f10312l;
    }

    public ScaleType getScaleType() {
        return f10301a;
    }

    protected void onDraw(Canvas canvas) {
        if (getDrawable() != null) {
            canvas.drawCircle((float) (getWidth() / f10303c), (float) (getHeight() / f10303c), this.f10317q, this.f10309i);
            if (this.f10312l != 0) {
                canvas.drawCircle((float) (getWidth() / f10303c), (float) (getHeight() / f10303c), this.f10318r, this.f10310j);
            }
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        m12576b();
    }

    public void setAdjustViewBounds(boolean z) {
        if (z) {
            throw new IllegalArgumentException("adjustViewBounds not supported.");
        }
    }

    public void setBorderColor(int i) {
        if (i != this.f10311k) {
            this.f10311k = i;
            this.f10310j.setColor(this.f10311k);
            invalidate();
        }
    }

    public void setBorderWidth(int i) {
        if (i != this.f10312l) {
            this.f10312l = i;
            m12576b();
        }
    }

    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        this.f10313m = bitmap;
        m12576b();
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        this.f10313m = m12574a(drawable);
        m12576b();
    }

    public void setImageResource(int i) {
        super.setImageResource(i);
        this.f10313m = m12574a(getDrawable());
        m12576b();
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        this.f10313m = m12574a(getDrawable());
        m12576b();
    }

    public void setScaleType(ScaleType scaleType) {
        if (scaleType != f10301a) {
            throw new IllegalArgumentException(String.format("ScaleType %s not supported.", new Object[]{scaleType}));
        }
    }
}
