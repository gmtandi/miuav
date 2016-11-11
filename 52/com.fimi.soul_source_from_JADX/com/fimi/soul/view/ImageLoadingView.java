package com.fimi.soul.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.fimi.soul.C1205R;

public class ImageLoadingView extends ImageView {
    private Paint f10383a;
    private float f10384b;

    public ImageLoadingView(Context context) {
        this(context, null);
        m12630c();
    }

    public ImageLoadingView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        m12630c();
    }

    public ImageLoadingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m12630c();
    }

    private void m12630c() {
        setBackgroundResource(C1205R.drawable.download_circle_bg);
        this.f10383a = new Paint();
        this.f10383a.setAntiAlias(true);
        this.f10383a.setStrokeWidth(2.0f);
        this.f10383a.setStyle(Style.STROKE);
    }

    public void m12631a() {
        this.f10383a.setColor(getResources().getColor(C1205R.color.loading_blue));
        invalidate();
    }

    public void m12632b() {
        this.f10383a.setColor(getResources().getColor(C1205R.color.loading_hot));
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(new RectF(2.0f, 2.0f, (float) (getWidth() - 2), (float) (getWidth() - 2)), -90.0f, this.f10384b, false, this.f10383a);
    }

    public void setPaintColor(int i) {
        this.f10383a.setColor(i);
    }

    public void setSweepAngle(float f) {
        this.f10384b = 3.6f * f;
        invalidate();
    }
}
