package com.fimi.soul.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import com.fimi.soul.C1205R;

public class MidView extends View {
    public static final int f10390c = 1;
    public static final int f10391d = 2;
    private int f10392A;
    float f10393a;
    float f10394b;
    private Bitmap f10395e;
    private Bitmap f10396f;
    private Bitmap f10397g;
    private Bitmap f10398h;
    private int f10399i;
    private int f10400j;
    private int f10401k;
    private int f10402l;
    private int f10403m;
    private int f10404n;
    private float f10405o;
    private float f10406p;
    private float f10407q;
    private Point f10408r;
    private Point f10409s;
    private Point f10410t;
    private int f10411u;
    private int f10412v;
    private int f10413w;
    private int f10414x;
    private int f10415y;
    private int f10416z;

    public MidView(Context context) {
        super(context);
        this.f10393a = 50.0f;
        this.f10394b = 50.0f;
    }

    public MidView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f10393a = 50.0f;
        this.f10394b = 50.0f;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C1205R.styleable.mycircleView);
        this.f10411u = obtainStyledAttributes.getInteger(0, 0);
        setType(this.f10411u);
        obtainStyledAttributes.recycle();
    }

    public MidView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f10393a = 50.0f;
        this.f10394b = 50.0f;
    }

    public void m12636a(float f, float f2) {
        if (this.f10393a != f || this.f10394b != f2) {
            this.f10405o = ((float) ((this.f10399i / f10391d) - (this.f10401k / f10391d))) / 50.0f;
            this.f10393a = f;
            this.f10394b = f2;
            invalidate();
        }
    }

    public void m12637a(Bitmap... bitmapArr) {
        int length = bitmapArr.length;
        for (int i = 0; i < length; i += f10390c) {
            Bitmap bitmap = bitmapArr[i];
            if (bitmap != null && bitmap.isRecycled()) {
                bitmap.recycle();
            }
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(-1);
        canvas.drawBitmap(this.f10395e, 0.0f, 0.0f, paint);
        canvas.drawBitmap(this.f10397g, (float) ((this.f10399i / f10391d) - (this.f10403m / f10391d)), (float) ((this.f10400j / f10391d) - (this.f10404n / f10391d)), paint);
        this.f10414x = bc.m12796a((float) (this.f10399i / f10391d), (float) (this.f10399i / f10391d), (this.f10393a * this.f10406p) + ((float) this.f10401k), (this.f10394b * this.f10407q) + ((float) this.f10401k));
        if (this.f10414x <= this.f10415y) {
            canvas.drawBitmap(this.f10396f, ((this.f10393a * this.f10406p) + ((float) (this.f10399i / f10391d))) - ((float) (this.f10398h.getWidth() / f10391d)), ((this.f10394b * this.f10407q) + ((float) (this.f10399i / f10391d))) - ((float) (this.f10398h.getHeight() / f10391d)), paint);
            return;
        }
        this.f10409s = bc.m12797a(this.f10408r, new Point((int) ((this.f10393a * this.f10406p) + ((float) this.f10401k)), (int) ((this.f10394b * this.f10407q) + ((float) this.f10401k))), this.f10415y);
        if (this.f10409s.x < (this.f10399i / f10391d) - (this.f10398h.getWidth() / f10391d)) {
            this.f10409s.x = (this.f10399i / f10391d) - (this.f10398h.getWidth() / f10391d);
        }
        if (this.f10411u == f10390c && this.f10409s.x > this.f10398h.getWidth() - (this.f10401k / f10391d)) {
            this.f10409s.x = this.f10398h.getWidth() - (this.f10401k / f10391d);
        }
        if (this.f10409s.y < (this.f10399i / f10391d) - (this.f10398h.getHeight() / f10391d)) {
            this.f10409s.y = (this.f10399i / f10391d) - (this.f10398h.getHeight() / f10391d);
        }
        canvas.drawBitmap(this.f10396f, (float) this.f10409s.x, (float) this.f10409s.y, paint);
        m12637a(this.f10395e, this.f10396f, this.f10397g, this.f10398h);
    }

    public void setType(int i) {
        if (i == f10390c) {
            this.f10395e = BitmapFactory.decodeResource(getResources(), C1205R.drawable.icon_calibration_remote_control_samll);
            this.f10396f = BitmapFactory.decodeResource(getResources(), C1205R.drawable.icon_calibration_annulus_samll);
            this.f10397g = BitmapFactory.decodeResource(getResources(), C1205R.drawable.icon_calibration_circle_samll);
            this.f10398h = BitmapFactory.decodeResource(getResources(), C1205R.drawable.bg_calibration_remote_control_square_samll);
        } else if (i == f10391d) {
            this.f10395e = BitmapFactory.decodeResource(getResources(), C1205R.drawable.bg_calibration_remote_control_square_line);
            this.f10396f = BitmapFactory.decodeResource(getResources(), C1205R.drawable.calibration_annulus);
            this.f10397g = BitmapFactory.decodeResource(getResources(), C1205R.drawable.calibration_circle);
            this.f10398h = BitmapFactory.decodeResource(getResources(), C1205R.drawable.remoterocefbg);
        }
        this.f10403m = this.f10397g.getWidth();
        this.f10404n = this.f10397g.getHeight();
        this.f10399i = this.f10395e.getWidth();
        this.f10400j = this.f10395e.getHeight();
        this.f10401k = this.f10396f.getWidth();
        this.f10402l = this.f10396f.getHeight();
        this.f10415y = ((int) Math.sqrt(Math.pow((double) (this.f10398h.getWidth() / f10391d), 2.0d) + Math.pow((double) (this.f10398h.getHeight() / f10391d), 2.0d))) - (this.f10401k / f10391d);
        this.f10408r = new Point((this.f10399i / f10391d) - (this.f10401k / f10391d), (this.f10400j / f10391d) - (this.f10401k / f10391d));
        this.f10416z = bc.m12796a((float) (this.f10401k / f10391d), (float) (this.f10402l / f10391d), (float) (this.f10399i / f10391d), (float) (this.f10400j / f10391d));
        this.f10406p = ((float) ((this.f10398h.getWidth() / f10391d) - (this.f10401k / f10391d))) / 50.0f;
        this.f10407q = ((float) ((this.f10398h.getHeight() / f10391d) - (this.f10401k / f10391d))) / 50.0f;
    }
}
