package com.fimi.soul.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.fimi.soul.C1205R;
import com.fimi.soul.view.photodraweeview.C2020f;

public class BatteryView extends View {
    Paint f10261a;
    Paint f10262b;
    Rect f10263c;
    double f10264d;
    int f10265e;
    double f10266f;
    double f10267g;
    int f10268h;
    int f10269i;
    int f10270j;
    int f10271k;
    private int f10272l;
    private float f10273m;

    public BatteryView(Context context) {
        super(context);
        this.f10272l = 100;
        this.f10261a = null;
        this.f10262b = null;
        this.f10263c = null;
        this.f10264d = 100.0d;
    }

    public BatteryView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f10272l = 100;
        this.f10261a = null;
        this.f10262b = null;
        this.f10263c = null;
        this.f10264d = 100.0d;
        this.f10273m = getContext().getResources().getDisplayMetrics().density;
    }

    public void m12564a(int i, double d) {
        this.f10272l = i;
        if (this.f10264d > 0.0d) {
            this.f10264d = d;
        }
        if (this.f10272l < 0) {
            this.f10272l = 0;
        }
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f = (float) (((double) this.f10272l) / this.f10264d);
        float f2 = f * 360.0f;
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(C1205R.color.battery));
        paint.setAntiAlias(true);
        canvas.drawCircle(this.f10273m * 12.0f, this.f10273m * 12.0f, this.f10273m * 12.0f, paint);
        paint = new Paint();
        paint.setColor(getResources().getColor(C1205R.color.black));
        paint.setAntiAlias(true);
        canvas.drawCircle(this.f10273m * 12.0f, this.f10273m * 12.0f, 11.0f * this.f10273m, paint);
        Paint paint2 = new Paint();
        if (((double) f) <= 0.25d) {
            paint2.setColor(getResources().getColor(C1205R.color.battery_red));
        } else if (((double) f) > 0.25d && ((double) f) <= 0.5d) {
            paint2.setColor(getResources().getColor(C1205R.color.battery_orange));
        } else if (((double) f) <= 0.5d || ((double) f) > 0.75d) {
            paint2.setColor(getResources().getColor(C1205R.color.battery_green));
        } else {
            paint2.setColor(getResources().getColor(C1205R.color.battery_yellow));
        }
        paint2.setAntiAlias(true);
        canvas.drawArc(new RectF(this.f10273m * C2020f.f10931a, this.f10273m * C2020f.f10931a, 21.0f * this.f10273m, 21.0f * this.f10273m), BitmapDescriptorFactory.HUE_VIOLET - f2, f2, true, paint2);
    }
}
