package com.fimi.soul.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.fimi.kernel.p084e.C1186y;
import com.fimi.soul.C1205R;
import com.tencent.mm.sdk.platformtools.Util;
import it.p074a.p075a.C2799f;

public class TranslationView extends View {
    private static final float f10499q = 0.25f;
    private Camera f10500a;
    private Matrix f10501b;
    private float f10502c;
    private float f10503d;
    private float f10504e;
    private int f10505f;
    private Paint f10506g;
    private Paint f10507h;
    private Paint f10508i;
    private Bitmap f10509j;
    private int f10510k;
    private AnimatorSet f10511l;
    private Bitmap f10512m;
    private Bitmap f10513n;
    private float f10514o;
    private float f10515p;

    public TranslationView(Context context) {
        this(context, null);
    }

    public TranslationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        this.f10500a = new Camera();
        this.f10501b = new Matrix();
        Options options = new Options();
        float b = C1186y.m8308b(context) < ((float) C1186y.m8298a(context)) ? C1186y.m8308b(context) : (float) C1186y.m8298a(context);
        Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), C1205R.drawable.usb_plane_start);
        Bitmap decodeResource2 = BitmapFactory.decodeResource(getResources(), C1205R.drawable.usb_plane_end);
        b = ((b * 1004.0f) / 1080.0f) / ((float) decodeResource2.getWidth());
        Matrix matrix = new Matrix();
        matrix.postScale(b, b);
        this.f10512m = Bitmap.createBitmap(decodeResource, 0, 0, decodeResource.getWidth(), decodeResource.getHeight(), matrix, true);
        this.f10513n = Bitmap.createBitmap(decodeResource2, 0, 0, decodeResource2.getWidth(), decodeResource2.getHeight(), matrix, true);
        this.f10509j = BitmapFactory.decodeResource(getResources(), C1205R.drawable.check_connect_bg);
        this.f10500a.save();
        this.f10500a.translate(0.0f, 0.0f, 500.0f);
        this.f10500a.getMatrix(this.f10501b);
        this.f10500a.restore();
        decodeResource = Bitmap.createBitmap(this.f10512m, 0, 0, this.f10512m.getWidth(), this.f10512m.getHeight(), this.f10501b, true);
        this.f10514o = (float) decodeResource.getWidth();
        this.f10515p = ((float) this.f10512m.getWidth()) / ((float) decodeResource.getWidth());
        this.f10506g = new Paint();
        this.f10508i = new Paint();
        this.f10508i.setAlpha(Util.MASK_8BIT);
        this.f10507h = new Paint();
    }

    public TranslationView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f10504e = 0.0f;
        this.f10505f = 0;
        this.f10510k = C2799f.f14263a;
    }

    public void m12659a() {
        this.f10502c = ((float) (-getMeasuredHeight())) * f10499q;
        this.f10503d = 0.0f;
        this.f10504e = 0.0f;
        this.f10505f = 0;
        this.f10508i.setAlpha(0);
        this.f10506g.setAlpha(Util.MASK_8BIT);
        invalidate();
    }

    public void m12660a(int i) {
        this.f10510k = i;
        Animator ofFloat = ValueAnimator.ofFloat(new float[]{((float) (-getMeasuredHeight())) * f10499q, (((float) (getMeasuredWidth() / 2)) - (this.f10514o / 2.0f)) * this.f10515p});
        ofFloat.addUpdateListener(new bl(this));
        Animator ofFloat2 = ValueAnimator.ofFloat(new float[]{0.0f, ((float) (-getHeight())) * 0.2444f});
        ofFloat2.addUpdateListener(new bm(this));
        Animator ofFloat3 = ValueAnimator.ofFloat(new float[]{0.0f, 500.0f});
        ofFloat3.addUpdateListener(new bn(this));
        Animator ofInt = ValueAnimator.ofInt(new int[]{0, Util.MASK_8BIT});
        ofInt.addUpdateListener(new bo(this));
        this.f10511l = new AnimatorSet();
        this.f10511l.setDuration((long) this.f10510k);
        this.f10511l.play(ofFloat).with(ofFloat2).with(ofFloat3).with(ofInt);
        this.f10511l.start();
    }

    public void m12661b() {
        if (this.f10511l.isStarted()) {
            this.f10511l.cancel();
            m12659a();
        }
    }

    public void m12662c() {
        if (!(this.f10513n == null || this.f10513n.isRecycled())) {
            this.f10513n = null;
        }
        if (!(this.f10512m == null || this.f10512m.isRecycled())) {
            this.f10512m = null;
        }
        if (this.f10509j != null && !this.f10509j.isRecycled()) {
            this.f10509j = null;
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f10500a.save();
        this.f10500a.translate(this.f10502c, this.f10503d, this.f10504e);
        this.f10500a.getMatrix(this.f10501b);
        this.f10500a.restore();
        this.f10507h.setAlpha(this.f10505f);
        canvas.drawBitmap(this.f10509j, null, new Rect(0, 0, getWidth(), getHeight()), this.f10507h);
        canvas.drawBitmap(this.f10512m, this.f10501b, this.f10506g);
        canvas.drawBitmap(this.f10513n, this.f10501b, this.f10508i);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.f10502c = ((float) (-getMeasuredHeight())) * f10499q;
        this.f10503d = 0.0f;
        this.f10504e = 0.0f;
    }

    public void setDuration(int i) {
        this.f10510k = i;
    }
}
