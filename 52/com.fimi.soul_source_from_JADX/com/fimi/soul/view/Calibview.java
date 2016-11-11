package com.fimi.soul.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.fimi.soul.utils.C1966f;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.tencent.open.yyb.TitleBar;

public class Calibview extends View {
    Context f10291a;
    RectF f10292b;
    private float f10293c;
    private int f10294d;
    private int f10295e;
    private int f10296f;
    private int f10297g;
    private int f10298h;
    private int f10299i;
    private int f10300j;

    public Calibview(Context context) {
        super(context);
        this.f10294d = 10;
        this.f10295e = 100;
        this.f10296f = 60;
        this.f10291a = context;
    }

    public Calibview(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f10294d = 10;
        this.f10295e = 100;
        this.f10296f = 60;
        this.f10291a = context;
        this.f10293c = getResources().getDisplayMetrics().density;
        if (((double) this.f10293c) == 1.5d) {
            this.f10295e = C1966f.m12465a(context, 65.0f);
            this.f10294d = C1966f.m12465a(context, 5.0f);
            this.f10296f = C1966f.m12465a(context, 40.0f);
        } else if (this.f10293c == 2.0f) {
            this.f10295e = C1966f.m12465a(context, 50.0f);
            this.f10294d = C1966f.m12465a(context, 5.0f);
            this.f10296f = C1966f.m12465a(context, BitmapDescriptorFactory.HUE_ORANGE);
        } else if (this.f10293c == C2020f.f10931a) {
            this.f10295e = C1966f.m12465a(context, 33.0f);
            this.f10294d = C1966f.m12465a(context, C2020f.f10931a);
            this.f10296f = C1966f.m12465a(context, TitleBar.BACKBTN_LEFT_MARGIN);
        }
    }

    public void m12573a(int i, int i2, int i3, int i4) {
        this.f10297g = i;
        this.f10298h = i2;
        this.f10299i = i3;
        this.f10300j = i4;
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        Paint paint = new Paint();
        paint.setStrokeWidth(C2020f.f10931a);
        paint.setStyle(Style.STROKE);
        paint.setColor(-1);
        paint.setAntiAlias(true);
        canvas.drawCircle(((float) this.f10295e) * this.f10293c, ((float) this.f10295e) * this.f10293c, ((float) this.f10296f) * this.f10293c, paint);
        paint.setStyle(Style.FILL);
        canvas.drawCircle(((float) this.f10295e) * this.f10293c, ((float) this.f10295e) * this.f10293c, ((float) this.f10294d) * this.f10293c, paint);
        paint.setStyle(Style.STROKE);
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        paint.setStrokeWidth(C2020f.f10931a);
        this.f10292b = new RectF(((float) (this.f10295e - this.f10296f)) * this.f10293c, ((float) (this.f10295e - this.f10294d)) * this.f10293c, ((float) (this.f10295e - this.f10294d)) * this.f10293c, ((float) (this.f10295e + this.f10294d)) * this.f10293c);
        canvas.drawRoundRect(this.f10292b, this.f10293c * TitleBar.SHAREBTN_RIGHT_MARGIN, this.f10293c * TitleBar.SHAREBTN_RIGHT_MARGIN, paint);
        this.f10292b = new RectF(((float) (this.f10295e - this.f10294d)) * this.f10293c, ((float) (this.f10295e - this.f10296f)) * this.f10293c, ((float) (this.f10295e + this.f10294d)) * this.f10293c, ((float) (this.f10295e - this.f10294d)) * this.f10293c);
        canvas.drawRoundRect(this.f10292b, this.f10293c * TitleBar.SHAREBTN_RIGHT_MARGIN, this.f10293c * TitleBar.SHAREBTN_RIGHT_MARGIN, paint);
        this.f10292b = new RectF(((float) (this.f10295e + this.f10294d)) * this.f10293c, ((float) (this.f10295e - this.f10294d)) * this.f10293c, ((float) (this.f10295e + this.f10296f)) * this.f10293c, ((float) (this.f10295e + this.f10294d)) * this.f10293c);
        canvas.drawRoundRect(this.f10292b, this.f10293c * TitleBar.SHAREBTN_RIGHT_MARGIN, this.f10293c * TitleBar.SHAREBTN_RIGHT_MARGIN, paint);
        this.f10292b = new RectF(((float) (this.f10295e - this.f10294d)) * this.f10293c, ((float) (this.f10295e + this.f10294d)) * this.f10293c, ((float) (this.f10295e + this.f10294d)) * this.f10293c, ((float) (this.f10295e + this.f10296f)) * this.f10293c);
        canvas.drawRoundRect(this.f10292b, this.f10293c * TitleBar.SHAREBTN_RIGHT_MARGIN, this.f10293c * TitleBar.SHAREBTN_RIGHT_MARGIN, paint);
        Paint paint2 = new Paint();
        paint2.setColor(ViewCompat.MEASURED_STATE_MASK);
        paint2.setTextSize(15.0f * this.f10293c);
        paint2.setTextAlign(Align.CENTER);
        paint.setStrokeWidth(C2020f.f10933c);
        paint2.setAntiAlias(true);
        canvas.drawText((this.f10298h * 2) + "%", ((float) ((this.f10295e - this.f10296f) - 20)) * this.f10293c, ((float) (this.f10295e + 2)) * this.f10293c, paint2);
        canvas.drawText((this.f10297g * 2) + "%", ((float) this.f10295e) * this.f10293c, ((float) ((this.f10295e - this.f10296f) - 5)) * this.f10293c, paint2);
        canvas.drawText((this.f10299i * 2) + "%", ((float) ((this.f10295e + this.f10296f) + 20)) * this.f10293c, ((float) (this.f10295e + 2)) * this.f10293c, paint2);
        canvas.drawText((this.f10300j * 2) + "%", ((float) this.f10295e) * this.f10293c, ((float) ((this.f10295e + this.f10296f) + 15)) * this.f10293c, paint2);
        paint.setStyle(Style.FILL);
        paint.setColor(-16711936);
        this.f10292b = new RectF(((float) ((this.f10295e - this.f10294d) - this.f10298h)) * this.f10293c, ((float) (this.f10295e - this.f10294d)) * this.f10293c, ((float) (this.f10295e - this.f10294d)) * this.f10293c, ((float) (this.f10295e + this.f10294d)) * this.f10293c);
        canvas.drawRoundRect(this.f10292b, this.f10293c * TitleBar.SHAREBTN_RIGHT_MARGIN, this.f10293c * TitleBar.SHAREBTN_RIGHT_MARGIN, paint);
        this.f10292b = new RectF(((float) (this.f10295e - this.f10294d)) * this.f10293c, ((float) ((this.f10295e - this.f10294d) - this.f10297g)) * this.f10293c, ((float) (this.f10295e + this.f10294d)) * this.f10293c, ((float) (this.f10295e - this.f10294d)) * this.f10293c);
        canvas.drawRoundRect(this.f10292b, this.f10293c * TitleBar.SHAREBTN_RIGHT_MARGIN, this.f10293c * TitleBar.SHAREBTN_RIGHT_MARGIN, paint);
        this.f10292b = new RectF(((float) (this.f10295e + this.f10294d)) * this.f10293c, ((float) (this.f10295e - this.f10294d)) * this.f10293c, ((float) ((this.f10295e + this.f10294d) + this.f10299i)) * this.f10293c, ((float) (this.f10295e + this.f10294d)) * this.f10293c);
        canvas.drawRoundRect(this.f10292b, this.f10293c * TitleBar.SHAREBTN_RIGHT_MARGIN, this.f10293c * TitleBar.SHAREBTN_RIGHT_MARGIN, paint);
        this.f10292b = new RectF(((float) (this.f10295e - this.f10294d)) * this.f10293c, ((float) (this.f10295e + this.f10294d)) * this.f10293c, ((float) (this.f10295e + this.f10294d)) * this.f10293c, ((float) ((this.f10295e + this.f10294d) + this.f10300j)) * this.f10293c);
        canvas.drawRoundRect(this.f10292b, this.f10293c * TitleBar.SHAREBTN_RIGHT_MARGIN, this.f10293c * TitleBar.SHAREBTN_RIGHT_MARGIN, paint);
    }
}
