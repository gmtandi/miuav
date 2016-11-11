package com.fimi.soul.view.circle;

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
import com.fimi.soul.base.C1236a;

public class CircleImageView extends ImageView {
    private static final ScaleType f10760a;
    private static final Config f10761b;
    private static final int f10762c = 2;
    private static final int f10763d = 0;
    private static final int f10764e = -16777216;
    private final RectF f10765f;
    private final RectF f10766g;
    private final Matrix f10767h;
    private final Paint f10768i;
    private final Paint f10769j;
    private int f10770k;
    private int f10771l;
    private Bitmap f10772m;
    private BitmapShader f10773n;
    private int f10774o;
    private int f10775p;
    private float f10776q;
    private float f10777r;
    private boolean f10778s;
    private boolean f10779t;

    static {
        f10760a = ScaleType.CENTER_CROP;
        f10761b = Config.ARGB_8888;
    }

    public CircleImageView(Context context) {
        super(context);
        this.f10765f = new RectF();
        this.f10766g = new RectF();
        this.f10767h = new Matrix();
        this.f10768i = new Paint();
        this.f10769j = new Paint();
        this.f10770k = f10764e;
        this.f10771l = f10763d;
        m12817a();
    }

    public CircleImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, f10763d);
    }

    public CircleImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f10765f = new RectF();
        this.f10766g = new RectF();
        this.f10767h = new Matrix();
        this.f10768i = new Paint();
        this.f10769j = new Paint();
        this.f10770k = f10764e;
        this.f10771l = f10763d;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C1205R.styleable.CircleImageView, i, f10763d);
        this.f10771l = obtainStyledAttributes.getDimensionPixelSize(f10763d, f10763d);
        this.f10770k = obtainStyledAttributes.getColor(1, f10764e);
        obtainStyledAttributes.recycle();
        m12817a();
    }

    private Bitmap m12816a(Drawable drawable) {
        C1236a.m8532a((drawable instanceof BitmapDrawable) + "|drawable=" + drawable, CircleImageView.class);
        if (drawable == null) {
            return BitmapFactory.decodeResource(getResources(), C1205R.drawable.defaultavatar);
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            Bitmap createBitmap = drawable instanceof ColorDrawable ? Bitmap.createBitmap(f10762c, f10762c, f10761b) : Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), f10761b);
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(f10763d, f10763d, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return createBitmap;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }

    private void m12817a() {
        super.setScaleType(f10760a);
        this.f10778s = true;
        if (this.f10779t) {
            m12818b();
            this.f10779t = false;
        }
    }

    private void m12818b() {
        if (!this.f10778s) {
            this.f10779t = true;
        } else if (this.f10772m != null) {
            this.f10773n = new BitmapShader(this.f10772m, TileMode.CLAMP, TileMode.CLAMP);
            this.f10768i.setAntiAlias(true);
            this.f10768i.setShader(this.f10773n);
            this.f10769j.setStyle(Style.STROKE);
            this.f10769j.setAntiAlias(true);
            this.f10769j.setColor(this.f10770k);
            this.f10769j.setStrokeWidth((float) this.f10771l);
            this.f10775p = this.f10772m.getHeight();
            this.f10774o = this.f10772m.getWidth();
            this.f10766g.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
            this.f10777r = Math.min((this.f10766g.height() - ((float) this.f10771l)) / 2.0f, (this.f10766g.width() - ((float) this.f10771l)) / 2.0f);
            this.f10765f.set((float) this.f10771l, (float) this.f10771l, this.f10766g.width() - ((float) this.f10771l), this.f10766g.height() - ((float) this.f10771l));
            this.f10776q = Math.min(this.f10765f.height() / 2.0f, this.f10765f.width() / 2.0f);
            m12819c();
            invalidate();
        }
    }

    private void m12819c() {
        float height;
        float width;
        float f = 0.0f;
        this.f10767h.set(null);
        if (((float) this.f10774o) * this.f10765f.height() > this.f10765f.width() * ((float) this.f10775p)) {
            height = this.f10765f.height() / ((float) this.f10775p);
            width = (this.f10765f.width() - (((float) this.f10774o) * height)) * 0.5f;
        } else {
            height = this.f10765f.width() / ((float) this.f10774o);
            width = 0.0f;
            f = (this.f10765f.height() - (((float) this.f10775p) * height)) * 0.5f;
        }
        this.f10767h.setScale(height, height);
        this.f10767h.postTranslate((float) (((int) (width + 0.5f)) + this.f10771l), (float) (((int) (f + 0.5f)) + this.f10771l));
        this.f10773n.setLocalMatrix(this.f10767h);
    }

    public int getBorderColor() {
        return this.f10770k;
    }

    public int getBorderWidth() {
        return this.f10771l;
    }

    public ScaleType getScaleType() {
        return f10760a;
    }

    protected void onDraw(Canvas canvas) {
        if (getDrawable() != null) {
            canvas.drawCircle((float) (getWidth() / f10762c), (float) (getHeight() / f10762c), this.f10776q, this.f10768i);
            if (this.f10771l != 0) {
                canvas.drawCircle((float) (getWidth() / f10762c), (float) (getHeight() / f10762c), this.f10777r, this.f10769j);
            }
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        m12818b();
    }

    public void setAdjustViewBounds(boolean z) {
        if (z) {
            throw new IllegalArgumentException("adjustViewBounds not supported.");
        }
    }

    public void setBorderColor(int i) {
        if (i != this.f10770k) {
            this.f10770k = i;
            this.f10769j.setColor(this.f10770k);
            invalidate();
        }
    }

    public void setBorderWidth(int i) {
        if (i != this.f10771l) {
            this.f10771l = i;
            m12818b();
        }
    }

    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        this.f10772m = bitmap;
        m12818b();
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        this.f10772m = m12816a(drawable);
        m12818b();
    }

    public void setImageResource(int i) {
        super.setImageResource(i);
        this.f10772m = m12816a(getDrawable());
        m12818b();
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        this.f10772m = m12816a(getDrawable());
        m12818b();
    }

    public void setScaleType(ScaleType scaleType) {
        if (scaleType != f10760a) {
            throw new IllegalArgumentException(String.format("ScaleType %s not supported.", new Object[]{scaleType}));
        }
    }
}
