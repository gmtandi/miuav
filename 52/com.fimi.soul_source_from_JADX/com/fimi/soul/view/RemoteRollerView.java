package com.fimi.soul.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.fimi.kernel.p084e.C1186y;
import com.fimi.soul.C1205R;
import it.p074a.p075a.C2799f;

public class RemoteRollerView extends View {
    private static final float f10440A = 0.1718f;
    private static final float f10441B = 0.35f;
    private static final float f10442C = 0.522f;
    private static final int f10443D = 21;
    private static final int f10444E = 22;
    public static final int f10445a = 0;
    public static final int f10446b = 1;
    public static final int f10447c = 2;
    public static final int f10448d = 3;
    public static final int f10449e = 51;
    public static final int f10450f = 1;
    public static final int f10451g = 50;
    private static final int f10452m = -14671840;
    private static final float f10453w = 0.2229f;
    private static final float f10454x = 0.3851f;
    private static final float f10455y = 0.175f;
    private static final float f10456z = 0.4685f;
    private Bitmap f10457h;
    private Bitmap f10458i;
    private Bitmap f10459j;
    private Bitmap f10460k;
    private Paint f10461l;
    private int f10462n;
    private RectF f10463o;
    private RectF f10464p;
    private Bitmap f10465q;
    private int f10466r;
    private float f10467s;
    private float f10468t;
    private float f10469u;
    private Context f10470v;

    public RemoteRollerView(Context context) {
        this(context, null);
    }

    public RemoteRollerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, f10445a);
        this.f10470v = context;
        this.f10457h = BitmapFactory.decodeResource(getResources(), C1205R.drawable.arrow_left_up_empty);
        this.f10458i = BitmapFactory.decodeResource(getResources(), C1205R.drawable.arrow_left_down_empty);
        this.f10459j = BitmapFactory.decodeResource(getResources(), C1205R.drawable.arrow_right_up_empty);
        this.f10460k = BitmapFactory.decodeResource(getResources(), C1205R.drawable.arrow_right_down_empty);
        this.f10461l = new Paint();
        this.f10461l.setAntiAlias(true);
        this.f10461l.setColor(f10452m);
        this.f10461l.setStrokeWidth(C1186y.m8308b(context) * 0.092f);
        this.f10461l.setStyle(Style.STROKE);
        setLayerType(f10447c, this.f10461l);
    }

    public RemoteRollerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f10462n = f10445a;
        this.f10466r = f10445a;
        this.f10467s = 0.0f;
    }

    private void m12640a(int i, Canvas canvas) {
        switch (i) {
            case f10445a /*0*/:
                canvas.drawBitmap(this.f10457h, ((float) getWidth()) * f10453w, ((float) getHeight()) * f10454x, this.f10461l);
                this.f10463o = new RectF(((float) getWidth()) * f10440A, ((float) getHeight()) * f10441B, (((float) getWidth()) * f10440A) + this.f10469u, (((float) getHeight()) * f10441B) + this.f10469u);
                this.f10464p = new RectF(((float) getWidth()) * f10453w, ((float) getHeight()) * f10454x, (((float) getWidth()) * f10453w) + ((float) this.f10457h.getWidth()), (((float) getHeight()) * f10454x) + ((float) this.f10457h.getHeight()));
                this.f10466r = C2799f.f14254D;
                this.f10467s = ((this.f10468t - 50.0f) / 50.0f) * 21.0f;
            case f10450f /*1*/:
                canvas.drawBitmap(this.f10458i, ((float) getWidth()) * f10455y, ((float) getHeight()) * f10456z, this.f10461l);
                this.f10463o = new RectF(((float) getWidth()) * f10440A, ((float) getHeight()) * f10441B, (((float) getWidth()) * f10440A) + this.f10469u, (((float) getHeight()) * f10441B) + this.f10469u);
                this.f10464p = new RectF(((float) getWidth()) * f10455y, ((float) getHeight()) * f10456z, (((float) getWidth()) * f10455y) + ((float) this.f10458i.getWidth()), (((float) getHeight()) * f10456z) + ((float) this.f10458i.getHeight()));
                this.f10466r = C2799f.f14288z;
                this.f10467s = ((-this.f10468t) / 50.0f) * 22.0f;
            case f10447c /*2*/:
                canvas.drawBitmap(this.f10459j, ((float) (getWidth() - this.f10459j.getWidth())) - (((float) getWidth()) * f10453w), ((float) getHeight()) * f10454x, this.f10461l);
                this.f10463o = new RectF((((float) getWidth()) - (((float) getWidth()) * f10440A)) - this.f10469u, ((float) getHeight()) * f10441B, ((float) getWidth()) - (((float) getWidth()) * f10440A), (((float) getHeight()) * f10441B) + this.f10469u);
                this.f10464p = new RectF(((float) (getWidth() - this.f10459j.getWidth())) - (((float) getWidth()) * f10453w), ((float) getHeight()) * f10454x, ((float) getWidth()) - (((float) getWidth()) * f10453w), (((float) getHeight()) * f10454x) + ((float) this.f10457h.getHeight()));
                this.f10466r = 314;
                this.f10467s = ((-(this.f10468t - 50.0f)) / 50.0f) * 21.0f;
            case f10448d /*3*/:
                canvas.drawBitmap(this.f10460k, ((float) (getWidth() - this.f10460k.getWidth())) - (((float) getWidth()) * f10455y), ((float) getHeight()) * f10456z, this.f10461l);
                this.f10463o = new RectF((((float) getWidth()) - (((float) getWidth()) * f10440A)) - this.f10469u, ((float) getHeight()) * f10441B, ((float) getWidth()) - (((float) getWidth()) * f10440A), (((float) getHeight()) * f10441B) + this.f10469u);
                this.f10464p = new RectF(((float) (getWidth() - this.f10460k.getWidth())) - (((float) getWidth()) * f10455y), ((float) getHeight()) * f10456z, ((float) getWidth()) - (((float) getWidth()) * f10455y), (((float) getHeight()) * f10456z) + ((float) this.f10460k.getHeight()));
                this.f10466r = 325;
                this.f10467s = (this.f10468t / 50.0f) * 22.0f;
            default:
        }
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        m12640a(this.f10462n, canvas);
        canvas.drawArc(this.f10463o, (float) this.f10466r, this.f10467s, false, this.f10461l);
        this.f10461l.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(this.f10465q, null, this.f10464p, this.f10461l);
        this.f10461l.setXfermode(null);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.f10469u = C1186y.m8308b(this.f10470v) * f10442C;
    }

    public void setInitView(int i) {
        this.f10462n = i;
        switch (i) {
            case f10445a /*0*/:
                this.f10465q = BitmapFactory.decodeResource(getResources(), C1205R.drawable.arrow_left_up_full);
            case f10450f /*1*/:
                this.f10465q = BitmapFactory.decodeResource(getResources(), C1205R.drawable.arrow_left_down_full);
            case f10447c /*2*/:
                this.f10465q = BitmapFactory.decodeResource(getResources(), C1205R.drawable.arrow_right_up_full);
            case f10448d /*3*/:
                this.f10465q = BitmapFactory.decodeResource(getResources(), C1205R.drawable.arrow_right_down_full);
            default:
        }
    }

    public void setSweep(float f) {
        this.f10468t = f;
        invalidate();
    }
}
