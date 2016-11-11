package com.tencent.connect.avatar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.view.View;

/* renamed from: com.tencent.connect.avatar.b */
public class C2207b extends View {
    private Rect f11464a;
    private Paint f11465b;

    public C2207b(Context context) {
        super(context);
        m13315b();
    }

    private void m13315b() {
        this.f11465b = new Paint();
    }

    public Rect m13316a() {
        if (this.f11464a == null) {
            this.f11464a = new Rect();
            int measuredWidth = getMeasuredWidth();
            int measuredHeight = getMeasuredHeight();
            int min = Math.min(Math.min((measuredHeight - 60) - 80, measuredWidth), 640);
            measuredWidth = (measuredWidth - min) / 2;
            measuredHeight = (measuredHeight - min) / 2;
            this.f11464a.set(measuredWidth, measuredHeight, measuredWidth + min, min + measuredHeight);
        }
        return this.f11464a;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect a = m13316a();
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        this.f11465b.setStyle(Style.FILL);
        this.f11465b.setColor(Color.argb(100, 0, 0, 0));
        canvas.drawRect(0.0f, 0.0f, (float) measuredWidth, (float) a.top, this.f11465b);
        canvas.drawRect(0.0f, (float) a.bottom, (float) measuredWidth, (float) measuredHeight, this.f11465b);
        canvas.drawRect(0.0f, (float) a.top, (float) a.left, (float) a.bottom, this.f11465b);
        canvas.drawRect((float) a.right, (float) a.top, (float) measuredWidth, (float) a.bottom, this.f11465b);
        canvas.drawColor(Color.argb(100, 0, 0, 0));
        this.f11465b.setStyle(Style.STROKE);
        this.f11465b.setColor(-1);
        canvas.drawRect((float) a.left, (float) a.top, (float) (a.right - 1), (float) a.bottom, this.f11465b);
    }
}
