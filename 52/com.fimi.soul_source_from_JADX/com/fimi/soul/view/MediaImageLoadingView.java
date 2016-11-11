package com.fimi.soul.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.fimi.soul.C1205R;

public class MediaImageLoadingView extends View {
    private Paint f10388a;
    private float f10389b;

    public MediaImageLoadingView(Context context) {
        this(context, null);
        m12633c();
    }

    public MediaImageLoadingView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        m12633c();
    }

    public MediaImageLoadingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m12633c();
    }

    private void m12633c() {
        setBackgroundResource(C1205R.drawable.download_circle_bg);
        this.f10388a = new Paint();
        this.f10388a.setAntiAlias(true);
        this.f10388a.setStrokeWidth(2.0f);
        this.f10388a.setStyle(Style.STROKE);
    }

    public void m12634a() {
        this.f10388a.setColor(getResources().getColor(C1205R.color.loading_blue));
        invalidate();
    }

    public void m12635b() {
        this.f10388a.setColor(getResources().getColor(C1205R.color.loading_hot));
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(new RectF(2.0f, 2.0f, (float) (getWidth() - 2), (float) (getWidth() - 2)), -90.0f, this.f10389b, false, this.f10388a);
    }

    public void setPaintColor(int i) {
        this.f10388a.setColor(i);
    }

    public void setSweepAngle(float f) {
        this.f10389b = 3.6f * f;
        invalidate();
    }
}
