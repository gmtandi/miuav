package com.fimi.soul.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.fimi.soul.C1205R;
import org.codehaus.jackson.smile.SmileConstants;

public class RemoteRocker extends View {
    private static final float f10425h = 0.2373f;
    private static final float f10426i = 0.228f;
    private Paint f10427a;
    private Bitmap f10428b;
    private Paint f10429c;
    private RectF f10430d;
    private RectF f10431e;
    private RectF f10432f;
    private RectF f10433g;
    private float f10434j;
    private bf f10435k;
    private float f10436l;
    private float f10437m;
    private float f10438n;
    private float f10439o;

    public RemoteRocker(Context context) {
        super(context);
        this.f10434j = -90.0f;
        this.f10436l = 0.0f;
        this.f10437m = 0.0f;
        this.f10438n = 0.0f;
        this.f10439o = 0.0f;
        m12638b();
    }

    public RemoteRocker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f10434j = -90.0f;
        this.f10436l = 0.0f;
        this.f10437m = 0.0f;
        this.f10438n = 0.0f;
        this.f10439o = 0.0f;
        m12638b();
    }

    public RemoteRocker(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f10434j = -90.0f;
        this.f10436l = 0.0f;
        this.f10437m = 0.0f;
        this.f10438n = 0.0f;
        this.f10439o = 0.0f;
        m12638b();
    }

    private void m12638b() {
        this.f10427a = new Paint();
        this.f10429c = new Paint();
    }

    public void m12639a() {
        if (this.f10428b != null) {
            this.f10428b.recycle();
            this.f10428b = null;
            System.gc();
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = new RectF(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
        if (this.f10428b == null) {
            setRemoteRocker(this.f10435k);
        }
        canvas.drawBitmap(this.f10428b, null, rectF, this.f10427a);
        canvas.drawArc(this.f10430d, this.f10434j, this.f10436l, false, this.f10429c);
        canvas.drawArc(this.f10431e, this.f10434j, this.f10437m, false, this.f10429c);
        canvas.drawArc(this.f10432f, this.f10434j, this.f10439o, false, this.f10429c);
        canvas.drawArc(this.f10433g, this.f10434j, this.f10438n, false, this.f10429c);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.f10429c.setStrokeWidth(((float) getWidth()) * 0.0046499968f);
        this.f10429c.setColor(-14172853);
        this.f10429c.setAntiAlias(true);
        this.f10429c.setStyle(Style.STROKE);
        this.f10430d = new RectF(((float) (getWidth() / 2)) - ((((float) getWidth()) * f10426i) / 2.0f), (((float) getWidth()) * 0.0092999935f) / 2.0f, ((float) (getWidth() / 2)) + ((((float) getWidth()) * f10426i) / 2.0f), ((float) getWidth()) * 0.23265f);
        this.f10431e = new RectF((((float) getWidth()) * 0.0092999935f) / 2.0f, ((float) (getWidth() / 2)) - ((((float) getWidth()) * f10426i) / 2.0f), ((float) getWidth()) * 0.23265f, (((float) getWidth()) / 2.0f) + ((((float) getWidth()) * f10426i) / 2.0f));
        this.f10432f = new RectF(((float) getWidth()) - (((float) getWidth()) * 0.23265f), ((float) (getWidth() / 2)) - ((((float) getWidth()) * f10426i) / 2.0f), ((float) getWidth()) - (((float) getWidth()) * 0.0046499968f), (((float) getWidth()) / 2.0f) + ((((float) getWidth()) * f10426i) / 2.0f));
        this.f10433g = new RectF(((float) (getWidth() / 2)) - ((((float) getWidth()) * f10426i) / 2.0f), ((float) getWidth()) - (((float) getWidth()) * 0.23265f), ((float) (getWidth() / 2)) + ((((float) getWidth()) * f10426i) / 2.0f), ((float) getWidth()) - ((((float) getWidth()) * 0.0092999935f) / 2.0f));
    }

    public void setBottomSweepAngle(float f) {
        this.f10439o = f;
        if (this.f10437m < 360.0f) {
            this.f10437m = 0.0f;
        }
        if (this.f10438n < 360.0f) {
            this.f10438n = 0.0f;
        }
        if (this.f10436l < 360.0f) {
            this.f10436l = 0.0f;
        }
        invalidate();
    }

    public void setLeftSweepAngle(float f) {
        this.f10437m = f;
        if (this.f10436l < 360.0f) {
            this.f10436l = 0.0f;
        }
        if (this.f10438n < 360.0f) {
            this.f10438n = 0.0f;
        }
        if (this.f10439o < 360.0f) {
            this.f10439o = 0.0f;
        }
        invalidate();
    }

    public void setRemoteRocker(bf bfVar) {
        this.f10435k = bfVar;
        switch (be.f10724a[bfVar.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                this.f10428b = BitmapFactory.decodeResource(getResources(), C1205R.drawable.newbie_left_rocker_bg);
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                this.f10428b = BitmapFactory.decodeResource(getResources(), C1205R.drawable.newbie_right_rocker_bg);
            default:
        }
    }

    public void setRightSweepAngle(float f) {
        this.f10438n = f;
        if (this.f10437m < 360.0f) {
            this.f10437m = 0.0f;
        }
        if (this.f10436l < 360.0f) {
            this.f10436l = 0.0f;
        }
        if (this.f10439o < 360.0f) {
            this.f10439o = 0.0f;
        }
        invalidate();
    }

    public void setTopSweepAngle(float f) {
        this.f10436l = f;
        if (this.f10437m < 360.0f) {
            this.f10437m = 0.0f;
        }
        if (this.f10438n < 360.0f) {
            this.f10438n = 0.0f;
        }
        if (this.f10439o < 360.0f) {
            this.f10439o = 0.0f;
        }
        invalidate();
    }
}
