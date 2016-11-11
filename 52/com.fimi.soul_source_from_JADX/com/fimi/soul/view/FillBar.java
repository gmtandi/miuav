package com.fimi.soul.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.View;
import com.fimi.soul.view.photodraweeview.C2020f;

public class FillBar extends View {
    SurfaceHolder f10327a;
    private Paint f10328b;
    private Paint f10329c;
    private Path f10330d;
    private Path f10331e;
    private int f10332f;
    private int f10333g;
    private float f10334h;
    private float f10335i;
    private float f10336j;
    private boolean f10337k;
    private int f10338l;
    private int f10339m;
    private int f10340n;
    private int f10341o;
    private int f10342p;
    private boolean f10343q;

    public FillBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f10330d = new Path();
        this.f10331e = new Path();
        this.f10334h = 0.0f;
        this.f10335i = 0.5f;
        this.f10336j = 0.5f;
        this.f10337k = false;
        this.f10343q = false;
        m12580b();
    }

    private void m12580b() {
        this.f10338l = Color.parseColor("#D6D6D6");
        this.f10339m = Color.parseColor("#FFFFFF");
        this.f10340n = Color.parseColor("#F49748");
        this.f10328b = new Paint();
        this.f10328b.setAntiAlias(false);
        this.f10328b.setStyle(Style.STROKE);
        this.f10328b.setStrokeWidth(C2020f.f10931a);
        this.f10329c = new Paint(this.f10328b);
        this.f10329c.setStyle(Style.FILL);
    }

    public void m12581a(int i, int i2) {
        this.f10341o = i;
        this.f10342p = i2;
    }

    public void m12582a(boolean z) {
        this.f10343q = z;
    }

    public boolean m12583a() {
        return this.f10337k;
    }

    public int getColorBar() {
        return this.f10340n;
    }

    public int getColorMin() {
        return this.f10339m;
    }

    public int getColorOutline() {
        return this.f10338l;
    }

    public float getMax() {
        return this.f10336j;
    }

    public int getMaxValue() {
        return this.f10342p + ((int) (getMax() * ((float) (this.f10341o - this.f10342p))));
    }

    public float getMin() {
        return this.f10335i;
    }

    public int getMinValue() {
        return this.f10342p + ((int) (getMin() * ((float) (this.f10341o - this.f10342p))));
    }

    public float getPercentage() {
        return this.f10334h;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f = this.f10332f < this.f10333g ? (float) this.f10332f : ((float) this.f10332f) * (C2020f.f10933c - this.f10334h);
        float f2 = this.f10332f < this.f10333g ? ((float) this.f10333g) * this.f10334h : (float) this.f10333g;
        this.f10329c.setColor(this.f10338l);
        this.f10330d.reset();
        this.f10330d.moveTo(0.0f, 0.0f);
        this.f10330d.lineTo(0.0f, (float) this.f10332f);
        this.f10330d.lineTo((float) this.f10333g, (float) this.f10332f);
        this.f10330d.lineTo((float) this.f10333g, 0.0f);
        this.f10330d.lineTo(0.0f, 0.0f);
        canvas.drawPath(this.f10330d, this.f10329c);
        this.f10329c.setColor(this.f10340n);
        this.f10331e.reset();
        if (this.f10343q) {
            if (this.f10332f > this.f10333g) {
                this.f10331e.moveTo(0.0f, 0.0f);
                this.f10331e.lineTo(0.0f, ((float) this.f10332f) - f);
                this.f10331e.lineTo((float) this.f10333g, ((float) this.f10332f) - f);
                this.f10331e.lineTo((float) this.f10333g, 0.0f);
                this.f10331e.lineTo(0.0f, 0.0f);
            } else {
                this.f10331e.moveTo(0.0f, 0.0f);
                this.f10331e.lineTo(0.0f, (float) this.f10332f);
                this.f10331e.lineTo(((float) this.f10333g) - f2, (float) this.f10332f);
                this.f10331e.lineTo(((float) this.f10333g) - f2, 0.0f);
                this.f10331e.lineTo(0.0f, 0.0f);
            }
        } else if (this.f10332f > this.f10333g) {
            this.f10331e.moveTo(0.0f, f);
            this.f10331e.lineTo(0.0f, (float) this.f10332f);
            this.f10331e.lineTo(f2, (float) this.f10332f);
            this.f10331e.lineTo(f2, f);
            this.f10331e.lineTo(0.0f, f);
        } else {
            this.f10331e.moveTo(0.0f, 0.0f);
            this.f10331e.lineTo(0.0f, (float) this.f10332f);
            this.f10331e.lineTo(f2, (float) this.f10332f);
            this.f10331e.lineTo(f2, 0.0f);
            this.f10331e.lineTo(0.0f, 0.0f);
        }
        canvas.drawPath(this.f10331e, this.f10329c);
        this.f10328b.setColor(this.f10339m);
        if (m12583a()) {
            this.f10330d.reset();
            if (this.f10343q) {
                if (this.f10332f > this.f10333g) {
                    f = ((float) this.f10332f) * this.f10335i;
                    this.f10330d.reset();
                    this.f10330d.moveTo(0.0f, f);
                    this.f10330d.lineTo((float) this.f10333g, f);
                    canvas.drawPath(this.f10330d, this.f10328b);
                    this.f10330d.reset();
                    f = ((float) this.f10332f) * this.f10336j;
                    this.f10330d.moveTo(0.0f, f);
                    this.f10330d.lineTo((float) this.f10333g, f);
                    canvas.drawPath(this.f10330d, this.f10328b);
                } else {
                    f = ((float) this.f10333g) * this.f10336j;
                    this.f10330d.reset();
                    this.f10330d.moveTo(f, 0.0f);
                    this.f10330d.lineTo(f, (float) this.f10332f);
                    canvas.drawPath(this.f10330d, this.f10328b);
                    f = ((float) this.f10333g) * this.f10335i;
                    this.f10330d.reset();
                    this.f10330d.moveTo(f, 0.0f);
                    this.f10330d.lineTo(f, (float) this.f10332f);
                    canvas.drawPath(this.f10330d, this.f10328b);
                }
            } else if (this.f10332f > this.f10333g) {
                f = ((float) this.f10332f) * (C2020f.f10933c - this.f10335i);
                this.f10330d.reset();
                this.f10330d.moveTo(0.0f, f);
                this.f10330d.lineTo((float) this.f10333g, f);
                canvas.drawPath(this.f10330d, this.f10328b);
                this.f10330d.reset();
                f = ((float) this.f10332f) * (C2020f.f10933c - this.f10336j);
                this.f10330d.moveTo(0.0f, f);
                this.f10330d.lineTo((float) this.f10333g, f);
                canvas.drawPath(this.f10330d, this.f10328b);
            } else {
                f = ((float) this.f10333g) * this.f10335i;
                this.f10330d.reset();
                this.f10330d.moveTo(f, 0.0f);
                this.f10330d.lineTo(f, (float) this.f10332f);
                canvas.drawPath(this.f10330d, this.f10328b);
                f = ((float) this.f10333g) * this.f10336j;
                this.f10330d.reset();
                this.f10330d.moveTo(f, 0.0f);
                this.f10330d.lineTo(f, (float) this.f10332f);
                canvas.drawPath(this.f10330d, this.f10328b);
            }
        }
        invalidate();
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.f10333g = i - 1;
        this.f10332f = i2 - 1;
    }

    public void setColorBar(int i) {
        this.f10340n = i;
    }

    public void setColorMin(int i) {
        this.f10339m = i;
    }

    public void setColorOutline(int i) {
        this.f10338l = i;
    }

    public void setPercentage(float f) {
        this.f10334h = f;
        this.f10335i = this.f10335i > f ? f : this.f10335i;
        if (this.f10336j >= f) {
            f = this.f10336j;
        }
        this.f10336j = f;
        invalidate();
    }

    public void setShowMinMax(boolean z) {
        this.f10337k = z;
        if (z) {
            this.f10335i = 0.5f;
            this.f10336j = 0.5f;
        }
        invalidate();
    }

    public void setValue(int i) {
        setPercentage(((float) (i - this.f10342p)) / ((float) (this.f10341o - this.f10342p)));
    }
}
