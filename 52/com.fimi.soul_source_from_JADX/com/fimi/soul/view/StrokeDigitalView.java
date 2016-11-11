package com.fimi.soul.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint.Style;
import android.support.v4.view.ViewCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import com.fimi.soul.utils.be;
import com.fimi.soul.view.photodraweeview.C2020f;

public class StrokeDigitalView extends TextView {
    private TextView f10472a;

    public StrokeDigitalView(Context context) {
        super(context);
        this.f10472a = null;
        this.f10472a = new TextView(context);
        be.m12368b(context.getAssets(), this.f10472a, this);
        m12642a();
    }

    public StrokeDigitalView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f10472a = null;
        this.f10472a = new TextView(context, attributeSet);
        be.m12368b(context.getAssets(), this.f10472a, this);
        m12642a();
    }

    public StrokeDigitalView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f10472a = null;
        this.f10472a = new TextView(context, attributeSet, i);
        be.m12368b(context.getAssets(), this.f10472a, this);
        m12642a();
    }

    public void m12642a() {
        TextPaint paint = this.f10472a.getPaint();
        paint.setStrokeWidth(C2020f.f10933c);
        paint.setStyle(Style.STROKE);
        this.f10472a.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.f10472a.setGravity(getGravity());
    }

    protected void onDraw(Canvas canvas) {
        this.f10472a.draw(canvas);
        super.onDraw(canvas);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.f10472a.layout(i, i2, i3, i4);
    }

    protected void onMeasure(int i, int i2) {
        CharSequence text = this.f10472a.getText();
        if (text == null || !text.equals(getText())) {
            this.f10472a.setText(getText());
            postInvalidate();
        }
        super.onMeasure(i, i2);
        this.f10472a.measure(i, i2);
    }

    public void setLayoutParams(LayoutParams layoutParams) {
        super.setLayoutParams(layoutParams);
        this.f10472a.setLayoutParams(layoutParams);
    }
}
