package com.fimi.soul.view;

import android.content.Context;
import android.content.res.Resources;
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
import com.fimi.kernel.p084e.C1186y;
import com.tencent.mm.sdk.platformtools.Util;

public class SwitchButtonStoke extends View {
    SimpleSpringListener f10474a;
    private SpringSystem f10475b;
    private Spring f10476c;
    private float f10477d;
    private int f10478e;
    private int f10479f;
    private int f10480g;
    private int f10481h;
    private int f10482i;
    private int f10483j;
    private Paint f10484k;
    private boolean f10485l;
    private int f10486m;
    private float f10487n;
    private float f10488o;
    private float f10489p;
    private float f10490q;
    private float f10491r;
    private int f10492s;
    private float f10493t;
    private RectF f10494u;
    private boolean f10495v;
    private bj f10496w;

    private SwitchButtonStoke(Context context) {
        super(context);
        this.f10478e = 0;
        this.f10479f = Color.parseColor("#63000000");
        this.f10480g = Color.parseColor("#51000000");
        this.f10481h = Color.parseColor("#ededed");
        this.f10482i = Color.parseColor("#ededed");
        this.f10483j = Color.parseColor("#ff4f00");
        this.f10485l = false;
        this.f10486m = 1;
        this.f10494u = new RectF();
        this.f10495v = true;
        this.f10474a = new bi(this);
    }

    public SwitchButtonStoke(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f10478e = 0;
        this.f10479f = Color.parseColor("#63000000");
        this.f10480g = Color.parseColor("#51000000");
        this.f10481h = Color.parseColor("#ededed");
        this.f10482i = Color.parseColor("#ededed");
        this.f10483j = Color.parseColor("#ff4f00");
        this.f10485l = false;
        this.f10486m = 1;
        this.f10494u = new RectF();
        this.f10495v = true;
        this.f10474a = new bi(this);
        setup(attributeSet);
    }

    public SwitchButtonStoke(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f10478e = 0;
        this.f10479f = Color.parseColor("#63000000");
        this.f10480g = Color.parseColor("#51000000");
        this.f10481h = Color.parseColor("#ededed");
        this.f10482i = Color.parseColor("#ededed");
        this.f10483j = Color.parseColor("#ff4f00");
        this.f10485l = false;
        this.f10486m = 1;
        this.f10494u = new RectF();
        this.f10495v = true;
        this.f10474a = new bi(this);
        setup(attributeSet);
    }

    private int m12644a(int i, int i2, int i3) {
        return Math.min(Math.max(i, i2), i3);
    }

    private void m12645a(double d) {
        this.f10493t = (float) SpringUtil.mapValueFromRangeToRange(d, 0.0d, WeightedLatLng.DEFAULT_INTENSITY, (double) this.f10490q, (double) this.f10491r);
        float mapValueFromRangeToRange = (float) SpringUtil.mapValueFromRangeToRange(WeightedLatLng.DEFAULT_INTENSITY - d, 0.0d, WeightedLatLng.DEFAULT_INTENSITY, 10.0d, (double) this.f10492s);
        int blue = Color.blue(this.f10483j);
        int red = Color.red(this.f10483j);
        int green = Color.green(this.f10483j);
        int blue2 = Color.blue(this.f10482i);
        int red2 = Color.red(this.f10482i);
        red = (int) SpringUtil.mapValueFromRangeToRange(WeightedLatLng.DEFAULT_INTENSITY - d, 0.0d, WeightedLatLng.DEFAULT_INTENSITY, (double) red, (double) red2);
        int mapValueFromRangeToRange2 = (int) SpringUtil.mapValueFromRangeToRange(WeightedLatLng.DEFAULT_INTENSITY - d, 0.0d, WeightedLatLng.DEFAULT_INTENSITY, (double) green, (double) Color.green(this.f10482i));
        this.f10481h = Color.rgb(m12644a(red, 0, Util.MASK_8BIT), m12644a(mapValueFromRangeToRange2, 0, Util.MASK_8BIT), m12644a((int) SpringUtil.mapValueFromRangeToRange(WeightedLatLng.DEFAULT_INTENSITY - d, 0.0d, WeightedLatLng.DEFAULT_INTENSITY, (double) blue, (double) blue2), 0, Util.MASK_8BIT));
        postInvalidate();
    }

    private void m12648a(boolean z) {
        double d = WeightedLatLng.DEFAULT_INTENSITY;
        if (z) {
            Spring spring = this.f10476c;
            if (!this.f10485l) {
                d = 0.0d;
            }
            spring.setEndValue(d);
            return;
        }
        this.f10476c.setCurrentValue(this.f10485l ? WeightedLatLng.DEFAULT_INTENSITY : 0.0d);
        if (!this.f10485l) {
            d = 0.0d;
        }
        m12645a(d);
    }

    private void m12649b() {
        this.f10485l = !this.f10485l;
        m12648a(true);
        setSwitchState(this.f10485l);
        if (this.f10496w != null) {
            this.f10496w.m11044a(this, this.f10485l);
        }
    }

    public void m12650a(boolean z, boolean z2) {
        this.f10485l = z;
        m12648a(z2);
    }

    public boolean m12651a() {
        return this.f10495v;
    }

    public void draw(Canvas canvas) {
        this.f10484k.setStyle(Style.STROKE);
        this.f10484k.setAntiAlias(true);
        float a = C1186y.m8296a(getContext(), 0.5f);
        this.f10494u.set(a, a, ((float) getWidth()) - a, ((float) getHeight()) - a);
        this.f10484k.setColor(this.f10479f);
        this.f10484k.setStrokeWidth(C1186y.m8296a(getContext(), 0.7f));
        canvas.drawRoundRect(this.f10494u, this.f10477d, this.f10477d, this.f10484k);
        this.f10484k.setStyle(Style.FILL);
        this.f10484k.setXfermode(new PorterDuffXfermode(Mode.SRC_OVER));
        this.f10494u.set((this.f10493t - this.f10477d) + 4.0f, (this.f10487n - this.f10477d) + 4.0f, (this.f10493t - 4.0f) + this.f10477d, (this.f10487n + this.f10477d) - 4.0f);
        if (this.f10485l) {
            this.f10484k.setColor(this.f10483j);
        } else {
            this.f10484k.setColor(this.f10480g);
        }
        canvas.drawRoundRect(this.f10494u, this.f10477d, this.f10477d, this.f10484k);
        a = ((float) this.f10492s) * 0.45f;
        this.f10494u.set(this.f10493t - a, this.f10487n - a, this.f10493t + a, this.f10487n + a);
        this.f10484k.setColor(this.f10481h);
        canvas.drawRoundRect(this.f10494u, a, a, this.f10484k);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f10476c.addListener(this.f10474a);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f10476c.removeListener(this.f10474a);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int width = getWidth();
        int height = getHeight();
        this.f10477d = ((float) Math.min(width, height)) * 0.5f;
        this.f10487n = this.f10477d;
        this.f10488o = this.f10477d;
        this.f10489p = ((float) width) - this.f10477d;
        this.f10490q = this.f10488o + ((float) this.f10486m);
        this.f10491r = this.f10489p - ((float) this.f10486m);
        this.f10492s = height - (this.f10486m * 4);
        this.f10493t = this.f10485l ? this.f10491r : this.f10490q;
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
        this.f10495v = z;
    }

    public void setOnSwitchListener(bj bjVar) {
        this.f10496w = bjVar;
    }

    public void setSwitchState(boolean z) {
        m12650a(z, true);
    }

    public void setup(AttributeSet attributeSet) {
        this.f10484k = new Paint(1);
        this.f10484k.setStyle(Style.STROKE);
        this.f10484k.setStrokeCap(Cap.ROUND);
        this.f10475b = SpringSystem.create();
        this.f10476c = this.f10475b.createSpring();
        this.f10476c.setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(50.0d, 7.0d));
        setOnClickListener(new bh(this));
    }
}
