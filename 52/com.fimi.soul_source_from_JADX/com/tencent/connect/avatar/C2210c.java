package com.tencent.connect.avatar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import com.facebook.imageutils.BitmapUtil;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.tencent.mm.sdk.platformtools.Util;
import com.tencent.open.yyb.TitleBar;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.tencent.connect.avatar.c */
public class C2210c extends ImageView {
    final String f11468a;
    public boolean f11469b;
    private Matrix f11470c;
    private Matrix f11471d;
    private int f11472e;
    private float f11473f;
    private float f11474g;
    private Bitmap f11475h;
    private boolean f11476i;
    private float f11477j;
    private float f11478k;
    private PointF f11479l;
    private PointF f11480m;
    private float f11481n;
    private float f11482o;
    private Rect f11483p;

    /* renamed from: com.tencent.connect.avatar.c.1 */
    class C22091 implements Runnable {
        final /* synthetic */ C2210c f11467a;

        /* renamed from: com.tencent.connect.avatar.c.1.1 */
        class C22081 implements Runnable {
            final /* synthetic */ C22091 f11466a;

            C22081(C22091 c22091) {
                this.f11466a = c22091;
            }

            public void run() {
                this.f11466a.f11467a.clearAnimation();
                this.f11466a.f11467a.m13322b();
            }
        }

        C22091(C2210c c2210c) {
            this.f11467a = c2210c;
        }

        public void run() {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.f11467a.post(new C22081(this));
            this.f11467a.f11476i = false;
        }
    }

    public C2210c(Context context) {
        super(context);
        this.f11470c = new Matrix();
        this.f11471d = new Matrix();
        this.f11472e = 0;
        this.f11473f = C2020f.f10933c;
        this.f11474g = C2020f.f10933c;
        this.f11476i = false;
        this.f11468a = "TouchView";
        this.f11479l = new PointF();
        this.f11480m = new PointF();
        this.f11481n = C2020f.f10933c;
        this.f11482o = 0.0f;
        this.f11469b = false;
        this.f11483p = new Rect();
        getDrawingRect(this.f11483p);
        m13318a();
    }

    private float m13317a(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() < 2) {
            return 0.0f;
        }
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return FloatMath.sqrt((x * x) + (y * y));
    }

    private void m13318a() {
    }

    private void m13319a(PointF pointF) {
        float f = C2020f.f10933c;
        if (this.f11475h != null) {
            float[] fArr = new float[9];
            this.f11470c.getValues(fArr);
            float f2 = fArr[2];
            float f3 = fArr[5];
            float f4 = fArr[0];
            float width = ((float) this.f11475h.getWidth()) * f4;
            float height = ((float) this.f11475h.getHeight()) * f4;
            f4 = ((float) this.f11483p.left) - f2;
            if (f4 <= C2020f.f10933c) {
                f4 = C2020f.f10933c;
            }
            f2 = (f2 + width) - ((float) this.f11483p.right);
            if (f2 <= C2020f.f10933c) {
                f2 = C2020f.f10933c;
            }
            width = ((f4 * ((float) this.f11483p.width())) / (f2 + f4)) + ((float) this.f11483p.left);
            f2 = ((float) this.f11483p.top) - f3;
            f4 = (f3 + height) - ((float) this.f11483p.bottom);
            if (f2 <= C2020f.f10933c) {
                f2 = C2020f.f10933c;
            }
            if (f4 > C2020f.f10933c) {
                f = f4;
            }
            pointF.set(width, ((((float) this.f11483p.height()) * f2) / (f2 + f)) + ((float) this.f11483p.top));
        }
    }

    private void m13322b() {
        if (this.f11475h != null) {
            float width = (float) this.f11483p.width();
            float height = (float) this.f11483p.height();
            float[] fArr = new float[9];
            this.f11470c.getValues(fArr);
            float f = fArr[2];
            float f2 = fArr[5];
            float f3 = fArr[0];
            Animation animation = null;
            if (f3 > this.f11473f) {
                this.f11482o = this.f11473f / f3;
                this.f11470c.postScale(this.f11482o, this.f11482o, this.f11480m.x, this.f11480m.y);
                setImageMatrix(this.f11470c);
                animation = new ScaleAnimation(C2020f.f10933c / this.f11482o, C2020f.f10933c, C2020f.f10933c / this.f11482o, C2020f.f10933c, this.f11480m.x, this.f11480m.y);
            } else if (f3 < this.f11474g) {
                this.f11482o = this.f11474g / f3;
                this.f11470c.postScale(this.f11482o, this.f11482o, this.f11480m.x, this.f11480m.y);
                animation = new ScaleAnimation(C2020f.f10933c, this.f11482o, C2020f.f10933c, this.f11482o, this.f11480m.x, this.f11480m.y);
            } else {
                Object obj = null;
                float width2 = ((float) this.f11475h.getWidth()) * f3;
                f3 *= (float) this.f11475h.getHeight();
                float f4 = ((float) this.f11483p.left) - f;
                float f5 = ((float) this.f11483p.top) - f2;
                if (f4 < 0.0f) {
                    f = (float) this.f11483p.left;
                    obj = 1;
                }
                if (f5 < 0.0f) {
                    f2 = (float) this.f11483p.top;
                    obj = 1;
                }
                f5 = f3 - f5;
                if (width2 - f4 < width) {
                    f = ((float) this.f11483p.left) - (width2 - width);
                    obj = 1;
                }
                if (f5 < height) {
                    f2 = ((float) this.f11483p.top) - (f3 - height);
                    obj = 1;
                }
                if (obj != null) {
                    float f6 = fArr[2] - f;
                    width = fArr[5] - f2;
                    fArr[2] = f;
                    fArr[5] = f2;
                    this.f11470c.setValues(fArr);
                    setImageMatrix(this.f11470c);
                    animation = new TranslateAnimation(f6, 0.0f, width, 0.0f);
                } else {
                    setImageMatrix(this.f11470c);
                }
            }
            if (animation != null) {
                this.f11476i = true;
                animation.setDuration(300);
                startAnimation(animation);
                new Thread(new C22091(this)).start();
            }
        }
    }

    private void m13323c() {
        if (this.f11475h != null) {
            float[] fArr = new float[9];
            this.f11470c.getValues(fArr);
            float max = Math.max(((float) this.f11483p.width()) / ((float) this.f11475h.getWidth()), ((float) this.f11483p.height()) / ((float) this.f11475h.getHeight()));
            this.f11477j = ((float) this.f11483p.left) - (((((float) this.f11475h.getWidth()) * max) - ((float) this.f11483p.width())) / 2.0f);
            this.f11478k = ((float) this.f11483p.top) - (((((float) this.f11475h.getHeight()) * max) - ((float) this.f11483p.height())) / 2.0f);
            fArr[2] = this.f11477j;
            fArr[5] = this.f11478k;
            fArr[4] = max;
            fArr[0] = max;
            this.f11470c.setValues(fArr);
            this.f11473f = Math.min(BitmapUtil.MAX_BITMAP_SIZE / ((float) this.f11475h.getWidth()), BitmapUtil.MAX_BITMAP_SIZE / ((float) this.f11475h.getHeight()));
            this.f11474g = max;
            if (this.f11473f < this.f11474g) {
                this.f11473f = this.f11474g;
            }
            setImageMatrix(this.f11470c);
        }
    }

    public void m13324a(Rect rect) {
        this.f11483p = rect;
        if (this.f11475h != null) {
            m13323c();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.f11476i) {
            switch (motionEvent.getAction() & Util.MASK_8BIT) {
                case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                    this.f11470c.set(getImageMatrix());
                    this.f11471d.set(this.f11470c);
                    this.f11479l.set(motionEvent.getX(), motionEvent.getY());
                    this.f11472e = 1;
                    break;
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                case Type.FLOAT /*6*/:
                    m13322b();
                    this.f11472e = 0;
                    break;
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (this.f11472e != 1) {
                        if (this.f11472e == 2) {
                            this.f11470c.set(this.f11470c);
                            float a = m13317a(motionEvent);
                            if (a > TitleBar.SHAREBTN_RIGHT_MARGIN) {
                                this.f11470c.set(this.f11471d);
                                a /= this.f11481n;
                                this.f11470c.postScale(a, a, this.f11480m.x, this.f11480m.y);
                            }
                            setImageMatrix(this.f11470c);
                            break;
                        }
                    }
                    this.f11470c.set(this.f11471d);
                    this.f11470c.postTranslate(motionEvent.getX() - this.f11479l.x, motionEvent.getY() - this.f11479l.y);
                    setImageMatrix(this.f11470c);
                    break;
                    break;
                case Type.INT /*5*/:
                    this.f11481n = m13317a(motionEvent);
                    if (this.f11481n > TitleBar.SHAREBTN_RIGHT_MARGIN) {
                        this.f11471d.set(this.f11470c);
                        m13319a(this.f11480m);
                        this.f11472e = 2;
                        break;
                    }
                    break;
            }
            this.f11469b = true;
        }
        return true;
    }

    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        this.f11475h = bitmap;
        if (bitmap != null) {
            this.f11475h = bitmap;
        }
    }
}
