package com.fimi.soul.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import com.fimi.soul.C1205R;
import com.fimi.soul.view.photodraweeview.C2020f;

public class VerticalSeekBar extends View {
    private int f10639a;
    private Bitmap f10640b;
    private Bitmap f10641c;
    private int f10642d;
    private float f10643e;

    public VerticalSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f10639a = 1;
        this.f10643e = C2020f.f10933c;
        this.f10640b = BitmapFactory.decodeResource(getResources(), C1205R.drawable.gimbal_measure);
        this.f10641c = BitmapFactory.decodeResource(getResources(), C1205R.drawable.gimbal_measure_bg);
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(this.f10641c, 0.0f, 0.0f, null);
        canvas.drawBitmap(this.f10640b, 0.0f, this.f10643e, null);
        super.onDraw(canvas);
    }

    public void setScale(int i) {
        int i2 = 100;
        this.f10639a = i;
        this.f10642d = this.f10641c.getHeight() - this.f10640b.getHeight();
        if (this.f10639a < 1) {
            this.f10639a = 1;
            this.f10643e = C2020f.f10933c;
        } else {
            if (this.f10639a <= 100) {
                i2 = this.f10639a;
            }
            this.f10643e = (float) ((i2 * this.f10642d) / 100);
        }
        invalidate();
    }
}
