package com.fimi.soul.module.droneFragment;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.fimi.soul.C1205R;
import com.fimi.soul.utils.C1966f;
import com.tencent.open.yyb.TitleBar;
import java.util.Iterator;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

public class SectorProgressBar extends View {
    private static int f7967d = 0;
    private static final int f7968e = 10;
    boolean f7969a;
    float f7970b;
    ObjectAnimator f7971c;
    private Bitmap f7972f;
    private float f7973g;
    private float f7974h;
    private RectF f7975i;
    private PorterDuffXfermode f7976j;
    private Paint f7977k;
    private ac f7978l;
    private int f7979m;
    private int f7980n;
    private boolean f7981o;
    private Context f7982p;
    private float f7983q;
    private float f7984r;
    private AnimatorSet f7985s;

    static {
        f7967d = 944;
    }

    public SectorProgressBar(Context context) {
        super(context);
        this.f7972f = null;
        this.f7973g = 0.0f;
        this.f7974h = 40.0f;
        this.f7975i = null;
        this.f7976j = null;
        this.f7978l = ac.LeftBottom;
        this.f7981o = false;
        this.f7983q = 0.0f;
        this.f7984r = 0.0f;
        this.f7969a = true;
        this.f7970b = 0.0f;
        this.f7982p = context;
        m10904c();
    }

    public SectorProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7972f = null;
        this.f7973g = 0.0f;
        this.f7974h = 40.0f;
        this.f7975i = null;
        this.f7976j = null;
        this.f7978l = ac.LeftBottom;
        this.f7981o = false;
        this.f7983q = 0.0f;
        this.f7984r = 0.0f;
        this.f7969a = true;
        this.f7970b = 0.0f;
        this.f7982p = context;
        m10904c();
    }

    public SectorProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f7972f = null;
        this.f7973g = 0.0f;
        this.f7974h = 40.0f;
        this.f7975i = null;
        this.f7976j = null;
        this.f7978l = ac.LeftBottom;
        this.f7981o = false;
        this.f7983q = 0.0f;
        this.f7984r = 0.0f;
        this.f7969a = true;
        this.f7970b = 0.0f;
        this.f7982p = context;
        m10904c();
    }

    private void m10904c() {
        this.f7984r = getResources().getDisplayMetrics().density;
        this.f7977k = new Paint();
        this.f7977k.setAntiAlias(true);
        setLayerType(2, this.f7977k);
        f7967d = (getResources().getDisplayMetrics().widthPixels / 2) - C1966f.m12466b(this.f7982p, 16.0f);
        setSectorProgressBarType(this.f7978l);
    }

    public void m10905a() {
        if (this.f7985s != null) {
            Iterator it = this.f7985s.getChildAnimations().iterator();
            while (it.hasNext()) {
                ((Animator) it.next()).cancel();
            }
            this.f7985s.cancel();
            this.f7985s = null;
        }
    }

    public void m10906a(float f) {
        if (this.f7971c == null) {
            this.f7971c = new ObjectAnimator();
            this.f7971c.setTarget(this);
            this.f7971c.setPropertyName("float");
            this.f7971c.setEvaluator(new FloatEvaluator());
            this.f7971c.addUpdateListener(new C1711y(this));
        }
        if (!this.f7971c.isRunning()) {
            this.f7971c.setObjectValues(new Object[]{Float.valueOf(this.f7970b), Float.valueOf(f)});
            this.f7970b = f;
            this.f7971c.setDuration(200);
            this.f7971c.start();
        }
    }

    public void m10907a(int i, int i2) {
        Animator ofObject = ObjectAnimator.ofObject(this, "float", new FloatEvaluator(), new Object[]{Integer.valueOf(i2), Integer.valueOf(100)});
        this.f7970b = this.f7983q;
        ofObject.addUpdateListener(new C1712z(this));
        Animator ofObject2 = ObjectAnimator.ofObject(this, "float", new FloatEvaluator(), new Object[]{Integer.valueOf(100), Integer.valueOf(i2)});
        ofObject2.setInterpolator(new OvershootInterpolator());
        ofObject2.addUpdateListener(new aa(this));
        this.f7985s = new AnimatorSet();
        this.f7985s.play(ofObject2).after(ofObject);
        this.f7985s.setDuration((long) i);
        this.f7985s.start();
    }

    public void m10908b() {
        if (this.f7971c != null) {
            this.f7971c.cancel();
            this.f7971c = null;
        }
    }

    public float getProgress() {
        return this.f7983q;
    }

    public ac getSectorProgressBarType() {
        return this.f7978l;
    }

    protected void onDraw(Canvas canvas) {
        float f;
        this.f7979m = canvas.getWidth();
        this.f7980n = canvas.getHeight();
        if (!this.f7981o) {
            m10904c();
            this.f7981o = true;
        }
        switch (ab.f8164a[this.f7978l.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                this.f7976j = new PorterDuffXfermode(Mode.SRC_OUT);
                f = (((-this.f7983q) / 100.0f) * 50.0f) + 50.0f;
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                this.f7976j = new PorterDuffXfermode(Mode.SRC_OUT);
                f = (((-this.f7983q) / 100.0f) * 50.0f) + 50.0f;
                break;
            case Type.BYTE /*3*/:
                this.f7976j = new PorterDuffXfermode(Mode.SRC_IN);
                f = (this.f7983q / 100.0f) * 50.0f;
                break;
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                this.f7976j = new PorterDuffXfermode(Mode.SRC_IN);
                f = (this.f7983q / 100.0f) * 50.0f;
                break;
            case Type.INT /*5*/:
                this.f7976j = new PorterDuffXfermode(Mode.SRC_OUT);
                f = (((-this.f7983q) / 100.0f) * 50.0f) + 50.0f;
                break;
            case Type.FLOAT /*6*/:
                this.f7976j = new PorterDuffXfermode(Mode.SRC_IN);
                f = (this.f7983q / 100.0f) * 50.0f;
                break;
            default:
                f = 0.0f;
                break;
        }
        RectF rectF = new RectF(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight());
        canvas.drawArc(this.f7975i, this.f7973g, this.f7974h + f, true, this.f7977k);
        this.f7977k.setXfermode(this.f7976j);
        canvas.drawBitmap(this.f7972f, null, rectF, this.f7977k);
        if (this.f7972f != null && this.f7972f.isRecycled()) {
            this.f7972f.recycle();
        }
        this.f7977k.setXfermode(null);
        if (this.f7969a) {
            this.f7969a = false;
            m10906a(100.0f);
        }
    }

    public void setProgress(float f) {
        this.f7983q = f;
    }

    public void setSectorProgressBarType(ac acVar) {
        switch (ab.f8164a[acVar.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                this.f7975i = new RectF((float) ((this.f7979m - 10) - (f7967d * 2)), (float) ((-f7967d) + this.f7980n), (float) (this.f7979m - 10), (float) (f7967d + this.f7980n));
                this.f7972f = BitmapFactory.decodeResource(getResources(), C1205R.drawable.lowpower);
                this.f7973g = -90.0f;
                this.f7974h = 40.0f;
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                this.f7975i = new RectF(TitleBar.SHAREBTN_RIGHT_MARGIN, (float) (-f7967d), (float) ((f7967d * 2) + f7968e), (float) f7967d);
                this.f7972f = BitmapFactory.decodeResource(getResources(), C1205R.drawable.distance);
                this.f7973g = 90.0f;
                this.f7974h = 40.0f;
                break;
            case Type.BYTE /*3*/:
                this.f7975i = new RectF((float) ((this.f7979m - 10) - (f7967d * 2)), (float) (-f7967d), (float) (this.f7979m - 10), (float) f7967d);
                this.f7972f = BitmapFactory.decodeResource(getResources(), C1205R.drawable.gpssign);
                this.f7973g = 0.0f;
                this.f7974h = 0.0f;
                break;
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                this.f7975i = new RectF((float) ((this.f7979m - 10) - (f7967d * 2)), (float) (-f7967d), (float) (this.f7979m - 10), (float) f7967d);
                this.f7972f = BitmapFactory.decodeResource(getResources(), C1205R.drawable.sign);
                this.f7973g = 0.0f;
                this.f7974h = 0.0f;
                break;
            case Type.INT /*5*/:
                this.f7975i = new RectF((float) ((this.f7979m - 10) - (f7967d * 2)), (float) ((-f7967d) + this.f7980n), (float) (this.f7979m - 10), (float) (f7967d + this.f7980n));
                this.f7972f = BitmapFactory.decodeResource(getResources(), C1205R.drawable.fullpower);
                this.f7973g = -90.0f;
                this.f7974h = 40.0f;
                break;
            case Type.FLOAT /*6*/:
                this.f7975i = new RectF(TitleBar.SHAREBTN_RIGHT_MARGIN, (float) ((-f7967d) + this.f7980n), (float) ((f7967d * 2) + f7968e), (float) (this.f7980n + f7967d));
                this.f7972f = BitmapFactory.decodeResource(getResources(), C1205R.drawable.hight);
                this.f7973g = BitmapDescriptorFactory.HUE_CYAN;
                this.f7974h = 0.0f;
                break;
        }
        this.f7978l = acVar;
    }
}
