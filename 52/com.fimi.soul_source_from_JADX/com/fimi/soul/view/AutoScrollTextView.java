package com.fimi.soul.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import org.p122a.p123a.C2915a;

public class AutoScrollTextView extends TextView implements OnClickListener {
    public static final String f10224a;
    public boolean f10225b;
    String f10226c;
    private float f10227d;
    private float f10228e;
    private float f10229f;
    private float f10230g;
    private float f10231h;
    private float f10232i;
    private Paint f10233j;
    private CharSequence f10234k;
    private float f10235l;
    private int f10236m;
    private int f10237n;

    static {
        f10224a = AutoScrollTextView.class.getSimpleName();
    }

    public AutoScrollTextView(Context context) {
        super(context);
        this.f10227d = 0.0f;
        this.f10228e = 0.0f;
        this.f10229f = 0.0f;
        this.f10230g = 0.0f;
        this.f10231h = 0.0f;
        this.f10232i = 0.0f;
        this.f10225b = false;
        this.f10233j = null;
        this.f10234k = C2915a.f14760f;
        this.f10235l = 0.5f;
        this.f10236m = ViewCompat.MEASURED_STATE_MASK;
        this.f10237n = 0;
        m12541c();
    }

    public AutoScrollTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f10227d = 0.0f;
        this.f10228e = 0.0f;
        this.f10229f = 0.0f;
        this.f10230g = 0.0f;
        this.f10231h = 0.0f;
        this.f10232i = 0.0f;
        this.f10225b = false;
        this.f10233j = null;
        this.f10234k = C2915a.f14760f;
        this.f10235l = 0.5f;
        this.f10236m = ViewCompat.MEASURED_STATE_MASK;
        this.f10237n = 0;
        m12541c();
    }

    public AutoScrollTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f10227d = 0.0f;
        this.f10228e = 0.0f;
        this.f10229f = 0.0f;
        this.f10230g = 0.0f;
        this.f10231h = 0.0f;
        this.f10232i = 0.0f;
        this.f10225b = false;
        this.f10233j = null;
        this.f10234k = C2915a.f14760f;
        this.f10235l = 0.5f;
        this.f10236m = ViewCompat.MEASURED_STATE_MASK;
        this.f10237n = 0;
        m12541c();
    }

    private void m12541c() {
        setOnClickListener(this);
    }

    public void m12542a() {
        this.f10225b = true;
        invalidate();
    }

    public void m12543a(float f) {
        this.f10234k = super.getText();
        this.f10233j = super.getPaint();
        this.f10234k = getText().toString();
        this.f10227d = this.f10233j.measureText(this.f10234k.toString());
        this.f10228e = (float) getWidth();
        this.f10228e = f;
        this.f10229f = this.f10227d;
        this.f10231h = this.f10228e + this.f10227d;
        this.f10232i = this.f10228e + (this.f10227d * 1.5f);
        this.f10230g = getTextSize() + ((float) getPaddingTop());
        this.f10233j.setColor(this.f10236m);
    }

    public void m12544b() {
        this.f10225b = false;
        invalidate();
    }

    public float getSpeed() {
        return this.f10235l;
    }

    public int getTextColor() {
        return this.f10236m;
    }

    public void onClick(View view) {
        if (this.f10225b) {
            m12544b();
        } else {
            m12542a();
        }
    }

    public void onDraw(Canvas canvas) {
        canvas.drawText(this.f10234k, 0, this.f10234k.length(), this.f10231h - this.f10229f, this.f10230g, this.f10233j);
        if (this.f10225b) {
            this.f10229f += this.f10235l;
            if (this.f10229f > this.f10232i) {
                this.f10237n++;
                if (this.f10237n == this.f10234k.length()) {
                    this.f10237n = 0;
                }
                m12543a(0.0f);
                this.f10229f = this.f10227d;
            }
            invalidate();
        }
    }

    public void setSpeed(float f) {
        this.f10235l = f;
    }

    public void setText(CharSequence charSequence, BufferType bufferType) {
        super.setText(charSequence, bufferType);
        m12543a(0.0f);
    }

    public void setTextColor(int i) {
        this.f10236m = i;
    }
}
