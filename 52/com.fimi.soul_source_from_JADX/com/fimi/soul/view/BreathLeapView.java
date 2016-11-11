package com.fimi.soul.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.fimi.kernel.p084e.C1186y;
import com.fimi.soul.C1205R;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.tencent.mm.sdk.platformtools.Util;
import com.tencent.open.yyb.TitleBar;
import it.p074a.p075a.C2799f;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

public class BreathLeapView extends View {
    private float f10274a;
    private float f10275b;
    private Paint f10276c;
    private Paint f10277d;
    private Paint f10278e;
    private Paint f10279f;
    private Paint f10280g;
    private Paint f10281h;
    private Paint f10282i;
    private Paint f10283j;
    private Paint f10284k;
    private Paint f10285l;
    private Bitmap f10286m;
    private Bitmap f10287n;
    private Bitmap f10288o;
    private int f10289p;
    private float f10290q;

    public BreathLeapView(Context context) {
        super(context, null);
        this.f10289p = 0;
        this.f10290q = 0.0f;
    }

    public BreathLeapView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        this.f10276c = new Paint();
        this.f10277d = new Paint();
        this.f10278e = new Paint();
        this.f10279f = new Paint();
        this.f10280g = new Paint();
        this.f10281h = new Paint();
        this.f10282i = new Paint();
        this.f10283j = new Paint();
        this.f10284k = new Paint();
        this.f10285l = new Paint();
        setBackgroundResource(C1205R.drawable.check_connect_bg);
        Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), C1205R.drawable.connent_on);
        Bitmap decodeResource2 = BitmapFactory.decodeResource(getResources(), C1205R.drawable.round);
        Bitmap decodeResource3 = BitmapFactory.decodeResource(getResources(), C1205R.drawable.connent);
        float b = ((C1186y.m8308b(context) < ((float) C1186y.m8298a(context)) ? C1186y.m8308b(context) : (float) C1186y.m8298a(context)) * 0.4925f) / ((float) decodeResource3.getHeight());
        Matrix matrix = new Matrix();
        matrix.postScale(b, b);
        this.f10286m = Bitmap.createBitmap(decodeResource3, 0, 0, decodeResource3.getWidth(), decodeResource3.getWidth(), matrix, true);
        this.f10287n = Bitmap.createBitmap(decodeResource, 0, 0, decodeResource.getWidth(), decodeResource.getWidth(), matrix, true);
        this.f10288o = Bitmap.createBitmap(decodeResource2, 0, 0, decodeResource2.getWidth(), decodeResource2.getWidth(), matrix, true);
        this.f10290q = context.getResources().getDimension(C1205R.dimen.unit);
        if (!(decodeResource == null || decodeResource.isRecycled())) {
            decodeResource.isRecycled();
        }
        if (!(decodeResource2 == null || decodeResource2.isRecycled())) {
            decodeResource2.isRecycled();
        }
        if (decodeResource3 != null && !decodeResource3.isRecycled()) {
            decodeResource3.isRecycled();
        }
    }

    public BreathLeapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f10289p = 0;
        this.f10290q = 0.0f;
    }

    private void m12568b() {
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{0, Util.MASK_8BIT, 0});
        ofInt.addUpdateListener(new C1987a(this));
        ofInt.setDuration(400);
        ofInt.start();
    }

    private void m12570c() {
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{Util.MASK_8BIT, Util.MASK_8BIT, 50});
        ofInt.addUpdateListener(new C1988b(this));
        ofInt.setDuration(300);
        ofInt.start();
    }

    public void m12572a() {
        if (!(this.f10287n == null || this.f10287n.isRecycled())) {
            this.f10287n.recycle();
            this.f10287n = null;
        }
        if (!(this.f10288o == null || this.f10288o.isRecycled())) {
            this.f10288o.recycle();
            this.f10288o = null;
        }
        if (this.f10286m != null && !this.f10286m.isRecycled()) {
            this.f10286m.recycle();
            this.f10286m = null;
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f10285l.setAlpha(this.f10289p);
        canvas.drawBitmap(this.f10287n, (this.f10274a / 2.0f) - ((float) (this.f10287n.getWidth() / 2)), ((this.f10275b * 0.15f) + ((float) (this.f10286m.getHeight() / 2))) - ((float) (this.f10287n.getHeight() / 2)), this.f10285l);
        canvas.drawBitmap(this.f10286m, (this.f10274a / 2.0f) - ((float) (this.f10286m.getWidth() / 2)), this.f10275b * 0.15f, this.f10284k);
        canvas.drawBitmap(this.f10288o, ((this.f10274a / 2.0f) - ((float) (this.f10287n.getWidth() / 2))) - (this.f10290q * 8.0f), ((this.f10275b * 0.15f) + ((float) (this.f10286m.getHeight() / 2))) - ((float) (this.f10288o.getHeight() / 2)), this.f10283j);
        canvas.drawBitmap(this.f10288o, (((this.f10274a / 2.0f) - ((float) (this.f10287n.getWidth() / 2))) - (this.f10290q * 8.0f)) - (this.f10290q * TitleBar.SHAREBTN_RIGHT_MARGIN), ((this.f10275b * 0.15f) + ((float) (this.f10286m.getHeight() / 2))) - ((float) (this.f10288o.getHeight() / 2)), this.f10282i);
        canvas.drawBitmap(this.f10288o, (((this.f10274a / 2.0f) - ((float) (this.f10287n.getWidth() / 2))) - (this.f10290q * 8.0f)) - (this.f10290q * TitleBar.BACKBTN_LEFT_MARGIN), ((this.f10275b * 0.15f) + ((float) (this.f10286m.getHeight() / 2))) - ((float) (this.f10288o.getHeight() / 2)), this.f10281h);
        canvas.drawBitmap(this.f10288o, (((this.f10274a / 2.0f) - ((float) (this.f10287n.getWidth() / 2))) - (this.f10290q * 8.0f)) - (this.f10290q * BitmapDescriptorFactory.HUE_ORANGE), ((this.f10275b * 0.15f) + ((float) (this.f10286m.getHeight() / 2))) - ((float) (this.f10288o.getHeight() / 2)), this.f10280g);
        canvas.drawBitmap(this.f10288o, (((this.f10274a / 2.0f) - ((float) (this.f10287n.getWidth() / 2))) - (this.f10290q * 8.0f)) - (this.f10290q * 40.0f), ((this.f10275b * 0.15f) + ((float) (this.f10286m.getHeight() / 2))) - ((float) (this.f10288o.getHeight() / 2)), this.f10279f);
        canvas.drawBitmap(this.f10288o, (((this.f10274a / 2.0f) - ((float) (this.f10287n.getWidth() / 2))) - (this.f10290q * 8.0f)) - (this.f10290q * 50.0f), ((this.f10275b * 0.15f) + ((float) (this.f10286m.getHeight() / 2))) - ((float) (this.f10288o.getHeight() / 2)), this.f10278e);
        canvas.drawBitmap(this.f10288o, (((this.f10274a / 2.0f) - ((float) (this.f10287n.getWidth() / 2))) - (this.f10290q * 8.0f)) - (this.f10290q * BitmapDescriptorFactory.HUE_YELLOW), ((this.f10275b * 0.15f) + ((float) (this.f10286m.getHeight() / 2))) - ((float) (this.f10288o.getHeight() / 2)), this.f10277d);
        canvas.drawBitmap(this.f10288o, (((this.f10274a / 2.0f) - ((float) (this.f10287n.getWidth() / 2))) - (this.f10290q * 8.0f)) - (this.f10290q * 70.0f), ((this.f10275b * 0.15f) + ((float) (this.f10286m.getHeight() / 2))) - ((float) (this.f10288o.getHeight() / 2)), this.f10276c);
        canvas.drawBitmap(this.f10288o, ((this.f10274a / 2.0f) + ((float) (this.f10287n.getWidth() / 2))) + (this.f10290q * C2020f.f10931a), ((this.f10275b * 0.15f) + ((float) (this.f10286m.getHeight() / 2))) - ((float) (this.f10288o.getHeight() / 2)), this.f10283j);
        canvas.drawBitmap(this.f10288o, (((this.f10274a / 2.0f) + ((float) (this.f10287n.getWidth() / 2))) + (this.f10290q * C2020f.f10931a)) + (this.f10290q * TitleBar.SHAREBTN_RIGHT_MARGIN), ((this.f10275b * 0.15f) + ((float) (this.f10286m.getHeight() / 2))) - ((float) (this.f10288o.getHeight() / 2)), this.f10282i);
        canvas.drawBitmap(this.f10288o, (((this.f10274a / 2.0f) + ((float) (this.f10287n.getWidth() / 2))) + (this.f10290q * C2020f.f10931a)) + (this.f10290q * TitleBar.BACKBTN_LEFT_MARGIN), ((this.f10275b * 0.15f) + ((float) (this.f10286m.getHeight() / 2))) - ((float) (this.f10288o.getHeight() / 2)), this.f10281h);
        canvas.drawBitmap(this.f10288o, (((this.f10274a / 2.0f) + ((float) (this.f10287n.getWidth() / 2))) + (this.f10290q * C2020f.f10931a)) + (this.f10290q * BitmapDescriptorFactory.HUE_ORANGE), ((this.f10275b * 0.15f) + ((float) (this.f10286m.getHeight() / 2))) - ((float) (this.f10288o.getHeight() / 2)), this.f10280g);
        canvas.drawBitmap(this.f10288o, (((this.f10274a / 2.0f) + ((float) (this.f10287n.getWidth() / 2))) + (this.f10290q * C2020f.f10931a)) + (this.f10290q * 40.0f), ((this.f10275b * 0.15f) + ((float) (this.f10286m.getHeight() / 2))) - ((float) (this.f10288o.getHeight() / 2)), this.f10279f);
        canvas.drawBitmap(this.f10288o, (((this.f10274a / 2.0f) + ((float) (this.f10287n.getWidth() / 2))) + (this.f10290q * C2020f.f10931a)) + (this.f10290q * 50.0f), ((this.f10275b * 0.15f) + (((float) this.f10286m.getHeight()) / 2.0f)) - ((float) (this.f10288o.getHeight() / 2)), this.f10278e);
        canvas.drawBitmap(this.f10288o, (((this.f10274a / 2.0f) + ((float) (this.f10287n.getWidth() / 2))) + (this.f10290q * C2020f.f10931a)) + (this.f10290q * BitmapDescriptorFactory.HUE_YELLOW), ((this.f10275b * 0.15f) + (((float) this.f10286m.getHeight()) / 2.0f)) - ((float) (this.f10288o.getHeight() / 2)), this.f10277d);
        canvas.drawBitmap(this.f10288o, (((this.f10274a / 2.0f) + ((float) (this.f10287n.getWidth() / 2))) + (this.f10290q * C2020f.f10931a)) + (this.f10290q * 70.0f), ((this.f10275b * 0.15f) + ((float) (this.f10286m.getHeight() / 2))) - ((float) (this.f10288o.getHeight() / 2)), this.f10276c);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.f10274a = (float) getMeasuredWidth();
        this.f10275b = (float) getMeasuredHeight();
    }

    public void setting(int i) {
        switch (i) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                this.f10276c.setAlpha(Util.MASK_8BIT);
                this.f10277d.setAlpha(50);
                this.f10278e.setAlpha(50);
                this.f10279f.setAlpha(50);
                this.f10280g.setAlpha(50);
                this.f10281h.setAlpha(50);
                this.f10282i.setAlpha(50);
                this.f10283j.setAlpha(50);
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                this.f10276c.setAlpha(C2799f.f14282t);
                this.f10277d.setAlpha(Util.MASK_8BIT);
                this.f10278e.setAlpha(50);
                this.f10279f.setAlpha(50);
                this.f10280g.setAlpha(50);
                this.f10281h.setAlpha(50);
                this.f10282i.setAlpha(50);
                this.f10283j.setAlpha(50);
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                this.f10276c.setAlpha(Opcodes.FCMPG);
                this.f10277d.setAlpha(C2799f.f14282t);
                this.f10278e.setAlpha(Util.MASK_8BIT);
                this.f10279f.setAlpha(50);
                this.f10280g.setAlpha(50);
                this.f10281h.setAlpha(50);
                this.f10282i.setAlpha(50);
                this.f10283j.setAlpha(50);
                break;
            case Type.BYTE /*3*/:
                this.f10276c.setAlpha(100);
                this.f10277d.setAlpha(Opcodes.FCMPG);
                this.f10278e.setAlpha(C2799f.f14282t);
                this.f10279f.setAlpha(Util.MASK_8BIT);
                this.f10280g.setAlpha(50);
                this.f10281h.setAlpha(50);
                this.f10282i.setAlpha(50);
                this.f10283j.setAlpha(50);
                break;
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                this.f10276c.setAlpha(50);
                this.f10277d.setAlpha(100);
                this.f10278e.setAlpha(Opcodes.FCMPG);
                this.f10279f.setAlpha(C2799f.f14282t);
                this.f10280g.setAlpha(Util.MASK_8BIT);
                this.f10281h.setAlpha(50);
                this.f10282i.setAlpha(50);
                this.f10283j.setAlpha(50);
                break;
            case Type.INT /*5*/:
                this.f10276c.setAlpha(50);
                this.f10277d.setAlpha(50);
                this.f10278e.setAlpha(100);
                this.f10279f.setAlpha(Opcodes.FCMPG);
                this.f10280g.setAlpha(C2799f.f14282t);
                this.f10281h.setAlpha(C2799f.f14257G);
                this.f10282i.setAlpha(50);
                this.f10283j.setAlpha(50);
                break;
            case Type.FLOAT /*6*/:
                this.f10276c.setAlpha(50);
                this.f10277d.setAlpha(50);
                this.f10278e.setAlpha(50);
                this.f10279f.setAlpha(100);
                this.f10280g.setAlpha(Opcodes.FCMPG);
                this.f10281h.setAlpha(C2799f.f14282t);
                this.f10282i.setAlpha(Util.MASK_8BIT);
                this.f10283j.setAlpha(50);
                break;
            case Type.LONG /*7*/:
                this.f10276c.setAlpha(50);
                this.f10277d.setAlpha(50);
                this.f10278e.setAlpha(50);
                this.f10279f.setAlpha(50);
                this.f10280g.setAlpha(100);
                this.f10281h.setAlpha(Opcodes.FCMPG);
                this.f10282i.setAlpha(C2799f.f14282t);
                this.f10283j.setAlpha(Util.MASK_8BIT);
                m12568b();
                m12570c();
                break;
        }
        invalidate();
    }
}
