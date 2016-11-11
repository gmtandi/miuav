package com.fimi.kernel.view.button;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.View.MeasureSpec;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.WeightedLatLng;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;
import com.facebook.rebound.SpringUtil;
import com.fimi.kernel.C1087R;
import com.fimi.kernel.p084e.C1186y;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.tencent.mm.sdk.platformtools.Util;

public class SwitchButton extends View {
    SimpleSpringListener f5369a;
    private SpringSystem f5370b;
    private Spring f5371c;
    private float f5372d;
    private int f5373e;
    private int f5374f;
    private int f5375g;
    private int f5376h;
    private int f5377i;
    private Paint f5378j;
    private boolean f5379k;
    private int f5380l;
    private float f5381m;
    private float f5382n;
    private float f5383o;
    private float f5384p;
    private float f5385q;
    private int f5386r;
    private float f5387s;
    private RectF f5388t;
    private boolean f5389u;
    private C1196c f5390v;

    private SwitchButton(Context context) {
        super(context);
        this.f5373e = 0;
        this.f5374f = Color.parseColor("#ffffff");
        this.f5375g = Color.parseColor("#bcbcbd");
        this.f5376h = Color.parseColor("#bcbcbd");
        this.f5377i = Color.parseColor("#ff5400");
        this.f5379k = false;
        this.f5380l = 1;
        this.f5388t = new RectF();
        this.f5389u = true;
        this.f5369a = new C1195b(this);
    }

    public SwitchButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f5373e = 0;
        this.f5374f = Color.parseColor("#ffffff");
        this.f5375g = Color.parseColor("#bcbcbd");
        this.f5376h = Color.parseColor("#bcbcbd");
        this.f5377i = Color.parseColor("#ff5400");
        this.f5379k = false;
        this.f5380l = 1;
        this.f5388t = new RectF();
        this.f5389u = true;
        this.f5369a = new C1195b(this);
        setup(attributeSet);
    }

    public SwitchButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f5373e = 0;
        this.f5374f = Color.parseColor("#ffffff");
        this.f5375g = Color.parseColor("#bcbcbd");
        this.f5376h = Color.parseColor("#bcbcbd");
        this.f5377i = Color.parseColor("#ff5400");
        this.f5379k = false;
        this.f5380l = 1;
        this.f5388t = new RectF();
        this.f5389u = true;
        this.f5369a = new C1195b(this);
        setup(attributeSet);
    }

    private int m8365a(int i, int i2, int i3) {
        return Math.min(Math.max(i, i2), i3);
    }

    private void m8366a(double d) {
        this.f5387s = (float) SpringUtil.mapValueFromRangeToRange(d, 0.0d, WeightedLatLng.DEFAULT_INTENSITY, (double) this.f5384p, (double) this.f5385q);
        float mapValueFromRangeToRange = (float) SpringUtil.mapValueFromRangeToRange(WeightedLatLng.DEFAULT_INTENSITY - d, 0.0d, WeightedLatLng.DEFAULT_INTENSITY, 10.0d, (double) this.f5386r);
        int blue = Color.blue(this.f5377i);
        int red = Color.red(this.f5377i);
        int green = Color.green(this.f5377i);
        int blue2 = Color.blue(this.f5376h);
        int red2 = Color.red(this.f5376h);
        red = (int) SpringUtil.mapValueFromRangeToRange(WeightedLatLng.DEFAULT_INTENSITY - d, 0.0d, WeightedLatLng.DEFAULT_INTENSITY, (double) red, (double) red2);
        int mapValueFromRangeToRange2 = (int) SpringUtil.mapValueFromRangeToRange(WeightedLatLng.DEFAULT_INTENSITY - d, 0.0d, WeightedLatLng.DEFAULT_INTENSITY, (double) green, (double) Color.green(this.f5376h));
        this.f5375g = Color.rgb(m8365a(red, 0, Util.MASK_8BIT), m8365a(mapValueFromRangeToRange2, 0, Util.MASK_8BIT), m8365a((int) SpringUtil.mapValueFromRangeToRange(WeightedLatLng.DEFAULT_INTENSITY - d, 0.0d, WeightedLatLng.DEFAULT_INTENSITY, (double) blue, (double) blue2), 0, Util.MASK_8BIT));
        postInvalidate();
    }

    private void m8369a(boolean z) {
        double d = WeightedLatLng.DEFAULT_INTENSITY;
        if (z) {
            Spring spring = this.f5371c;
            if (!this.f5379k) {
                d = 0.0d;
            }
            spring.setEndValue(d);
            return;
        }
        this.f5371c.setCurrentValue(this.f5379k ? WeightedLatLng.DEFAULT_INTENSITY : 0.0d);
        if (!this.f5379k) {
            d = 0.0d;
        }
        m8366a(d);
    }

    private void m8370b() {
        this.f5379k = !this.f5379k;
        m8369a(true);
        setSwitchState(this.f5379k);
        if (this.f5390v != null) {
            this.f5390v.m8373a(this, this.f5379k);
        }
    }

    public void m8371a(boolean z, boolean z2) {
        this.f5379k = z;
        m8369a(z2);
    }

    public boolean m8372a() {
        return this.f5389u;
    }

    public void draw(Canvas canvas) {
        this.f5378j.setStyle(Style.STROKE);
        this.f5378j.setAntiAlias(true);
        float a = C1186y.m8296a(getContext(), 0.5f);
        this.f5388t.set(a, a, ((float) getWidth()) - a, ((float) getHeight()) - a);
        this.f5378j.setColor(603979775);
        this.f5378j.setStrokeWidth(C1186y.m8296a(getContext(), 0.7f));
        canvas.drawRoundRect(this.f5388t, this.f5372d, this.f5372d, this.f5378j);
        this.f5378j.setStyle(Style.FILL);
        this.f5378j.setXfermode(new PorterDuffXfermode(Mode.SRC_OVER));
        this.f5388t.set((this.f5387s - C2020f.f10933c) - this.f5372d, this.f5381m - this.f5372d, (this.f5387s + 1.1f) + this.f5372d, this.f5381m + this.f5372d);
        this.f5378j.setColor(0);
        canvas.drawRoundRect(this.f5388t, this.f5372d, this.f5372d, this.f5378j);
        a = ((float) this.f5386r) * 0.45f;
        this.f5388t.set(this.f5387s - a, this.f5381m - a, this.f5387s + a, this.f5381m + a);
        this.f5378j.setColor(this.f5375g);
        canvas.drawRoundRect(this.f5388t, a, a, this.f5378j);
    }

    public boolean getToggleOn() {
        return this.f5379k;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f5371c.addListener(this.f5369a);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f5371c.removeListener(this.f5369a);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int width = getWidth();
        int height = getHeight();
        this.f5372d = ((float) Math.min(width, height)) * 0.5f;
        this.f5381m = this.f5372d;
        this.f5382n = this.f5372d;
        this.f5383o = ((float) width) - this.f5372d;
        this.f5384p = this.f5382n + ((float) this.f5380l);
        this.f5385q = this.f5383o - ((float) this.f5380l);
        this.f5386r = height - (this.f5380l * 4);
        this.f5387s = this.f5379k ? this.f5385q : this.f5384p;
    }

    protected void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        MeasureSpec.getMode(i2);
        MeasureSpec.getSize(i);
        int size = MeasureSpec.getSize(i2);
        Resources system = Resources.getSystem();
        if (mode == 0 || mode == C1186y.f5353a) {
            i = MeasureSpec.makeMeasureSpec((int) TypedValue.applyDimension(1, 50.0f, system.getDisplayMetrics()), 1073741824);
        }
        if (size == 0 || size == C1186y.f5353a) {
            i2 = MeasureSpec.makeMeasureSpec((int) TypedValue.applyDimension(1, BitmapDescriptorFactory.HUE_ORANGE, system.getDisplayMetrics()), 1073741824);
        }
        super.onMeasure(i, i2);
    }

    public void setAnimate(boolean z) {
        this.f5389u = z;
    }

    public void setOnSwitchListener(C1196c c1196c) {
        this.f5390v = c1196c;
    }

    public void setSwitchState(boolean z) {
        m8371a(z, true);
    }

    public void setup(AttributeSet attributeSet) {
        this.f5378j = new Paint(1);
        this.f5378j.setStyle(Style.STROKE);
        this.f5378j.setStrokeCap(Cap.ROUND);
        this.f5370b = SpringSystem.create();
        this.f5371c = this.f5370b.createSpring();
        this.f5371c.setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(50.0d, 7.0d));
        setOnClickListener(new C1194a(this));
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C1087R.styleable.SwitchButton);
        this.f5373e = obtainStyledAttributes.getColor(C1087R.styleable.SwitchButton_onColor, this.f5373e);
        this.f5376h = obtainStyledAttributes.getColor(C1087R.styleable.SwitchButton_spotColor, this.f5376h);
        this.f5380l = obtainStyledAttributes.getDimensionPixelSize(C1087R.styleable.SwitchButton_borderWidth, (int) C1186y.m8296a(getContext(), (float) this.f5380l));
        this.f5389u = obtainStyledAttributes.getBoolean(C1087R.styleable.SwitchButton_animate, this.f5389u);
        obtainStyledAttributes.recycle();
    }
}
