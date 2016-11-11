package com.amap.api.mapcore.util;

import android.content.Context;
import android.view.MotionEvent;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.amap.api.mapcore.util.d */
public class C0375d extends C0374e {
    private final C0288a f2403l;
    private boolean f2404m;

    /* renamed from: com.amap.api.mapcore.util.d.a */
    public interface C0288a {
        boolean m2207a(C0375d c0375d);

        boolean m2208b(C0375d c0375d);

        void m2209c(C0375d c0375d);
    }

    public C0375d(Context context, C0288a c0288a) {
        super(context);
        this.f2403l = c0288a;
    }

    protected void m3994a() {
        super.m3505a();
        this.f2404m = false;
    }

    protected void m3995a(int i, MotionEvent motionEvent) {
        switch (i) {
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                if (this.f2404m) {
                    this.f2404m = m3993d(motionEvent);
                    if (!this.f2404m) {
                        this.b = this.f2403l.m2208b(this);
                    }
                }
            case Type.INT /*5*/:
                m3994a();
                this.c = MotionEvent.obtain(motionEvent);
                this.g = 0;
                m3991b(motionEvent);
                this.f2404m = m3993d(motionEvent);
                if (!this.f2404m) {
                    this.b = this.f2403l.m2208b(this);
                }
            case Type.FLOAT /*6*/:
                if (!this.f2404m) {
                }
            default:
        }
    }

    public float m3996b() {
        return (float) (((Math.atan2((double) this.i, (double) this.h) - Math.atan2((double) this.k, (double) this.j)) * 180.0d) / 3.141592653589793d);
    }

    protected void m3997b(int i, MotionEvent motionEvent) {
        switch (i) {
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                m3991b(motionEvent);
                if (this.e / this.f > 0.67f && this.f2403l.m2207a(this) && this.c != null) {
                    this.c.recycle();
                    this.c = MotionEvent.obtain(motionEvent);
                }
            case Type.BYTE /*3*/:
                if (!this.f2404m) {
                    this.f2403l.m2209c(this);
                }
                m3994a();
            case Type.FLOAT /*6*/:
                m3991b(motionEvent);
                if (!this.f2404m) {
                    this.f2403l.m2209c(this);
                }
                m3994a();
            default:
        }
    }
}
