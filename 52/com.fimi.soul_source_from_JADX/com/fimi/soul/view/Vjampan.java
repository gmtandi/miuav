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
import com.fimi.soul.view.photodraweeview.C2020f;
import com.tencent.open.yyb.TitleBar;

public class Vjampan extends View {
    int f10644a;
    int f10645b;
    float f10646c;
    private int f10647d;
    private Bitmap f10648e;
    private Bitmap f10649f;
    private int f10650g;
    private float f10651h;

    public Vjampan(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f10647d = 0;
        this.f10651h = C2020f.f10933c;
        this.f10646c = getResources().getDisplayMetrics().density;
        this.f10649f = BitmapFactory.decodeResource(getResources(), C1205R.drawable.jampan);
        this.f10644a = this.f10649f.getHeight();
        this.f10645b = this.f10649f.getWidth();
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(this.f10649f, 0.0f, 0.0f, null);
        Paint paint = new Paint();
        paint.setColor(-256);
        paint.setStyle(Style.FILL);
        paint.setAlpha(100);
        if (this.f10647d >= this.f10644a / 2) {
            canvas.drawRoundRect(new RectF(0.0f, (float) (this.f10644a - this.f10647d), (float) this.f10645b, (float) (this.f10644a / 2)), TitleBar.BACKBTN_LEFT_MARGIN, TitleBar.BACKBTN_LEFT_MARGIN, paint);
        } else if (this.f10647d >= 0 && this.f10647d < this.f10644a / 2) {
            canvas.drawRoundRect(new RectF(0.0f, (float) (this.f10644a / 2), (float) this.f10645b, (float) (this.f10644a - this.f10647d)), TitleBar.BACKBTN_LEFT_MARGIN, TitleBar.BACKBTN_LEFT_MARGIN, paint);
        }
        super.onDraw(canvas);
    }

    public void setScale(int i) {
        if (((double) this.f10646c) == 1.5d) {
            this.f10647d = i * 2;
        } else if (this.f10646c == 2.0f) {
            this.f10647d = i * 3;
        } else if (this.f10646c == C2020f.f10931a) {
            this.f10647d = (int) (((double) i) * 4.5d);
        }
        invalidate();
    }
}
