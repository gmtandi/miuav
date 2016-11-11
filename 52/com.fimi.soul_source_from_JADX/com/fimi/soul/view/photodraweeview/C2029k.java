package com.fimi.soul.view.photodraweeview;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.fimi.soul.view.photodraweeview.k */
public class C2029k implements OnScaleGestureListener {
    private static final int f10973c = -1;
    float f10974a;
    float f10975b;
    private final float f10976d;
    private final float f10977e;
    private final ScaleGestureDetector f10978f;
    private final C2021i f10979g;
    private VelocityTracker f10980h;
    private boolean f10981i;
    private int f10982j;
    private int f10983k;

    public C2029k(Context context, C2021i c2021i) {
        this.f10982j = f10973c;
        this.f10983k = 0;
        this.f10978f = new ScaleGestureDetector(context, this);
        this.f10979g = c2021i;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.f10977e = (float) viewConfiguration.getScaledMinimumFlingVelocity();
        this.f10976d = (float) viewConfiguration.getScaledTouchSlop();
    }

    private void m12973a(int i, MotionEvent motionEvent) {
        int i2 = 0;
        switch (i) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                this.f10982j = motionEvent.getPointerId(0);
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
            case Type.BYTE /*3*/:
                this.f10982j = f10973c;
                break;
            case Type.FLOAT /*6*/:
                int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
                if (MotionEventCompat.getPointerId(motionEvent, actionIndex) == this.f10982j) {
                    actionIndex = actionIndex == 0 ? 1 : 0;
                    this.f10982j = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                    this.f10974a = MotionEventCompat.getX(motionEvent, actionIndex);
                    this.f10975b = MotionEventCompat.getY(motionEvent, actionIndex);
                    break;
                }
                break;
        }
        if (this.f10982j != f10973c) {
            i2 = this.f10982j;
        }
        this.f10983k = MotionEventCompat.findPointerIndex(motionEvent, i2);
    }

    private float m12974b(MotionEvent motionEvent) {
        try {
            return MotionEventCompat.getX(motionEvent, this.f10983k);
        } catch (Exception e) {
            return motionEvent.getX();
        }
    }

    private void m12975b(int i, MotionEvent motionEvent) {
        boolean z = false;
        float yVelocity;
        switch (i) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                this.f10980h = VelocityTracker.obtain();
                if (this.f10980h != null) {
                    this.f10980h.addMovement(motionEvent);
                }
                this.f10974a = m12974b(motionEvent);
                this.f10975b = m12976c(motionEvent);
                this.f10981i = false;
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                if (this.f10981i && this.f10980h != null) {
                    this.f10974a = m12974b(motionEvent);
                    this.f10975b = m12976c(motionEvent);
                    this.f10980h.addMovement(motionEvent);
                    this.f10980h.computeCurrentVelocity(XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER);
                    float xVelocity = this.f10980h.getXVelocity();
                    yVelocity = this.f10980h.getYVelocity();
                    if (Math.max(Math.abs(xVelocity), Math.abs(yVelocity)) >= this.f10977e) {
                        this.f10979g.m12937a(this.f10974a, this.f10975b, -xVelocity, -yVelocity);
                    }
                }
                if (this.f10980h != null) {
                    this.f10980h.recycle();
                    this.f10980h = null;
                }
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                yVelocity = m12974b(motionEvent);
                float c = m12976c(motionEvent);
                float f = yVelocity - this.f10974a;
                float f2 = c - this.f10975b;
                if (!this.f10981i) {
                    if (Math.sqrt((double) ((f * f) + (f2 * f2))) >= ((double) this.f10976d)) {
                        z = true;
                    }
                    this.f10981i = z;
                }
                if (this.f10981i) {
                    this.f10979g.m12935a(f, f2);
                    this.f10974a = yVelocity;
                    this.f10975b = c;
                    if (this.f10980h != null) {
                        this.f10980h.addMovement(motionEvent);
                    }
                }
            case Type.BYTE /*3*/:
                if (this.f10980h != null) {
                    this.f10980h.recycle();
                    this.f10980h = null;
                }
            default:
        }
    }

    private float m12976c(MotionEvent motionEvent) {
        try {
            return MotionEventCompat.getY(motionEvent, this.f10983k);
        } catch (Exception e) {
            return motionEvent.getY();
        }
    }

    public boolean m12977a() {
        return this.f10978f.isInProgress();
    }

    public boolean m12978a(MotionEvent motionEvent) {
        this.f10978f.onTouchEvent(motionEvent);
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        m12973a(actionMasked, motionEvent);
        m12975b(actionMasked, motionEvent);
        return true;
    }

    public boolean m12979b() {
        return this.f10981i;
    }

    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        float scaleFactor = scaleGestureDetector.getScaleFactor();
        if (Float.isNaN(scaleFactor) || Float.isInfinite(scaleFactor)) {
            return false;
        }
        this.f10979g.m12936a(scaleFactor, scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
        return true;
    }

    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        return true;
    }

    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        this.f10979g.m12938f();
    }
}
