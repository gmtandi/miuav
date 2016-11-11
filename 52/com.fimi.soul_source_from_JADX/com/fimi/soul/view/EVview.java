package com.fimi.soul.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import com.amap.api.maps.model.GroundOverlayOptions;
import com.fimi.soul.C1205R;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.tencent.connect.common.Constants;
import com.tencent.open.yyb.TitleBar;

public class EVview extends View {
    private Paint f10321a;
    private Paint f10322b;
    private Paint f10323c;
    private int f10324d;
    private float f10325e;
    private Context f10326f;

    public EVview(Context context) {
        super(context);
        this.f10324d = 6;
        this.f10325e = 0.0f;
        this.f10326f = context;
        m12578a();
    }

    public EVview(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f10324d = 6;
        this.f10325e = 0.0f;
        this.f10326f = context;
        m12578a();
    }

    void m12578a() {
        this.f10321a = new Paint();
        this.f10321a.setColor(-1);
        this.f10321a.setAlpha(80);
        this.f10321a.setStrokeWidth(C2020f.f10931a);
        this.f10321a.setStyle(Style.FILL);
        this.f10321a.setAntiAlias(true);
        this.f10321a.setTextSize(25.0f);
        this.f10322b = new Paint();
        this.f10322b.setColor(getResources().getColor(C1205R.color.changebetery));
        this.f10322b.setStrokeWidth(C2020f.f10931a);
        this.f10322b.setAntiAlias(true);
        this.f10322b.setStyle(Style.FILL);
    }

    public void m12579a(float f) {
        this.f10325e = f;
        if (f == -2.0f) {
            this.f10324d = 0;
        } else if (f == -1.7f) {
            this.f10324d = 1;
        } else if (f == -1.3f) {
            this.f10324d = 2;
        } else if (f == GroundOverlayOptions.NO_DIMENSION) {
            this.f10324d = 3;
        } else if (f == -0.7f) {
            this.f10324d = 4;
        } else if (f == -0.3f) {
            this.f10324d = 5;
        } else if (f == 0.0f) {
            this.f10324d = 6;
        } else if (f == 0.3f) {
            this.f10324d = 7;
        } else if (f == 0.7f) {
            this.f10324d = 8;
        } else if (f == C2020f.f10933c) {
            this.f10324d = 9;
        } else if (f == 1.3f) {
            this.f10324d = 10;
        } else if (f == 1.7f) {
            this.f10324d = 11;
        } else if (f == 2.0f) {
            this.f10324d = 12;
        }
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f10321a.setColor(-1);
        canvas.drawLine(50.0f, getY() + 28.0f, ((float) getWidth()) - 50.0f, getY() + 28.0f, this.f10321a);
        float width = (((float) getWidth()) - 100.0f) / 12.0f;
        for (int i = 0; i <= 12; i++) {
            this.f10321a.setColor(-1);
            if (i % 2 != 0) {
                canvas.drawLine(50.0f + (((float) i) * width), 23.0f + getY(), 50.0f + (((float) i) * width), 33.0f + getY(), this.f10321a);
            } else {
                canvas.drawLine(50.0f + (((float) i) * width), 13.0f + getY(), 50.0f + (((float) i) * width), 43.0f + getY(), this.f10321a);
            }
            if (i == 0) {
                if (this.f10324d != 0) {
                    canvas.drawText("-2", ((((float) i) * width) + 50.0f) - (this.f10321a.getTextSize() / C2020f.f10931a), getY() + 70.0f, this.f10321a);
                }
            } else if (i == 6) {
                if (this.f10324d != 6) {
                    canvas.drawText(Constants.VIA_RESULT_SUCCESS, ((((float) i) * width) + 50.0f) - (this.f10321a.getTextSize() / C2020f.f10931a), getY() + 70.0f, this.f10321a);
                }
            } else if (i == 12 && this.f10324d != 12) {
                canvas.drawText(Constants.VIA_SSO_LOGIN, ((((float) i) * width) + 50.0f) - (this.f10321a.getTextSize() / C2020f.f10931a), getY() + 70.0f, this.f10321a);
            }
        }
        canvas.drawCircle((((float) this.f10324d) * width) + 50.0f, getY() + 28.0f, TitleBar.BACKBTN_LEFT_MARGIN, this.f10322b);
        this.f10321a.setColor(this.f10326f.getResources().getColor(C1205R.color.ev_color));
        canvas.drawText(String.valueOf(this.f10325e), (50.0f + (((float) this.f10324d) * width)) - (this.f10321a.getTextSize() / 2.0f), getY() + 70.0f, this.f10321a);
    }
}
