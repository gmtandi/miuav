package com.fimi.soul.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint.Style;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import com.fimi.soul.utils.be;
import com.fimi.soul.view.photodraweeview.C2020f;

public class StrokeTextView extends TextView {
    private TextView f10473a;

    public StrokeTextView(Context context) {
        super(context);
        this.f10473a = null;
        this.f10473a = new TextView(context);
        be.m12359a(context.getAssets(), this.f10473a, this);
        m12643a();
    }

    public StrokeTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f10473a = null;
        this.f10473a = new TextView(context, attributeSet);
        be.m12359a(context.getAssets(), this.f10473a, this);
        m12643a();
    }

    public StrokeTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f10473a = null;
        this.f10473a = new TextView(context, attributeSet, i);
        be.m12359a(context.getAssets(), this.f10473a, this);
        m12643a();
    }

    public void m12643a() {
        TextPaint paint = this.f10473a.getPaint();
        paint.setStrokeWidth(C2020f.f10933c);
        paint.setStyle(Style.STROKE);
        this.f10473a.setTextColor(1275068416);
        this.f10473a.setGravity(getGravity());
    }

    protected void onDraw(Canvas canvas) {
        this.f10473a.draw(canvas);
        super.onDraw(canvas);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.f10473a.layout(i, i2, i3, i4);
    }

    protected void onMeasure(int i, int i2) {
        CharSequence text = this.f10473a.getText();
        if (text == null || !text.equals(getText())) {
            this.f10473a.setText(getText());
            postInvalidate();
        }
        super.onMeasure(i, i2);
        this.f10473a.measure(i, i2);
    }

    public void setLayoutParams(LayoutParams layoutParams) {
        super.setLayoutParams(layoutParams);
        this.f10473a.setLayoutParams(layoutParams);
    }
}
