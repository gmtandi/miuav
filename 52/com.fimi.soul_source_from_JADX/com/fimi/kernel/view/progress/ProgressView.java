package com.fimi.kernel.view.progress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.internal.view.SupportMenu;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import com.fimi.kernel.p084e.C1186y;

public class ProgressView extends View {
    private static final int[] f5435a;
    private float f5436b;
    private float f5437c;
    private Paint f5438d;
    private int f5439e;
    private int f5440f;
    private int f5441g;
    private int f5442h;

    static {
        f5435a = new int[]{-16711936, -256, SupportMenu.CATEGORY_MASK};
    }

    public ProgressView(Context context) {
        super(context);
        this.f5441g = 0;
        this.f5442h = 0;
        m8425a(context);
    }

    public ProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f5441g = 0;
        this.f5442h = 0;
        m8425a(context);
    }

    public ProgressView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f5441g = 0;
        this.f5442h = 0;
        m8425a(context);
    }

    private int m8424a(int i) {
        return (int) ((((float) (i >= 0 ? 1 : -1)) * 0.5f) + (((float) i) * getContext().getResources().getDisplayMetrics().density));
    }

    private void m8425a(Context context) {
    }

    public float getCurrentCount() {
        return this.f5437c;
    }

    public float getMaxCount() {
        return this.f5436b;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f5438d = new Paint();
        this.f5438d.setAntiAlias(true);
        int i = this.f5440f / 2;
        if (this.f5442h == 0) {
            this.f5438d.setColor(771751935);
        } else {
            this.f5438d.setColor(this.f5442h);
        }
        canvas.drawRoundRect(new RectF(0.0f, 0.0f, (float) this.f5439e, (float) this.f5440f), (float) i, (float) i, this.f5438d);
        RectF rectF = new RectF(0.0f, 0.0f, (this.f5437c / this.f5436b) * ((float) this.f5439e), (float) this.f5440f);
        if (this.f5441g == 0) {
            this.f5438d.setColor(1895825407);
        } else {
            this.f5438d.setColor(this.f5441g);
        }
        canvas.drawRoundRect(rectF, (float) i, (float) i, this.f5438d);
    }

    protected void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size2 = MeasureSpec.getSize(i2);
        if (mode == 1073741824 || mode == C1186y.f5353a) {
            this.f5439e = size;
        } else {
            this.f5439e = 0;
        }
        if (mode2 == C1186y.f5353a || mode2 == 0) {
            this.f5440f = m8424a(15);
        } else {
            this.f5440f = size2;
        }
        setMeasuredDimension(this.f5439e, this.f5440f);
    }

    public void setBackColor(int i) {
        this.f5442h = i;
    }

    public void setCurrentCount(float f) {
        if (f > this.f5436b) {
            f = this.f5436b;
        }
        this.f5437c = f;
        invalidate();
    }

    public void setFrontColor(int i) {
        this.f5441g = i;
    }

    public void setMaxCount(float f) {
        this.f5436b = f;
    }
}
