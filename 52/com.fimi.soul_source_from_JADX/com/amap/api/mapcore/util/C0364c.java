package com.amap.api.mapcore.util;

import android.content.Context;
import android.view.MotionEvent;
import com.tencent.mm.sdk.platformtools.Util;
import java.lang.reflect.Method;

/* renamed from: com.amap.api.mapcore.util.c */
public class C0364c {
    protected Method f2297a;
    protected Method f2298b;
    protected Method f2299c;
    private boolean f2300d;
    private final C0279a f2301e;
    private long f2302f;
    private boolean f2303g;

    /* renamed from: com.amap.api.mapcore.util.c.a */
    public interface C0279a {
        void m2199a();

        void m2200a(float f, float f2, float f3, float f4, float f5);

        boolean m2201a(MotionEvent motionEvent, float f, float f2, float f3, float f4);
    }

    public C0364c(Context context, C0279a c0279a) {
        this.f2300d = false;
        this.f2302f = 0;
        this.f2303g = false;
        this.f2301e = c0279a;
        m3805c();
    }

    private void m3805c() {
        try {
            this.f2297a = MotionEvent.class.getMethod("getPointerCount", new Class[0]);
            this.f2298b = MotionEvent.class.getMethod("getX", new Class[]{Integer.TYPE});
            this.f2299c = MotionEvent.class.getMethod("getY", new Class[]{Integer.TYPE});
            this.f2300d = true;
        } catch (Throwable th) {
            this.f2300d = false;
            ce.m3829a(th, "MultiTouchSupport", "initMethods");
            th.printStackTrace();
        }
    }

    public boolean m3806a() {
        return this.f2303g;
    }

    public boolean m3807a(MotionEvent motionEvent) {
        if (!this.f2300d) {
            return false;
        }
        int action = motionEvent.getAction() & Util.MASK_8BIT;
        try {
            if (((Integer) this.f2297a.invoke(motionEvent, new Object[0])).intValue() < 2) {
                this.f2302f = 0;
                this.f2303g = false;
                return false;
            }
            Float f = (Float) this.f2298b.invoke(motionEvent, new Object[]{Integer.valueOf(0)});
            Float f2 = (Float) this.f2298b.invoke(motionEvent, new Object[]{Integer.valueOf(1)});
            Float f3 = (Float) this.f2299c.invoke(motionEvent, new Object[]{Integer.valueOf(0)});
            Float f4 = (Float) this.f2299c.invoke(motionEvent, new Object[]{Integer.valueOf(1)});
            float sqrt = (float) Math.sqrt((double) (((f2.floatValue() - f.floatValue()) * (f2.floatValue() - f.floatValue())) + ((f4.floatValue() - f3.floatValue()) * (f4.floatValue() - f3.floatValue()))));
            if (action == 5) {
                this.f2301e.m2200a(sqrt, f.floatValue(), f3.floatValue(), f2.floatValue(), f4.floatValue());
                this.f2303g = true;
                return true;
            } else if (action == 6) {
                this.f2302f = motionEvent.getEventTime();
                if (motionEvent.getPointerCount() == 2 && this.f2302f - motionEvent.getDownTime() < 100) {
                    this.f2301e.m2199a();
                }
                if (this.f2303g) {
                    this.f2303g = false;
                }
                return false;
            } else {
                if (this.f2303g && action == 2) {
                    return this.f2301e.m2201a(motionEvent, f.floatValue(), f3.floatValue(), f2.floatValue(), f4.floatValue());
                }
                return false;
            }
        } catch (Throwable th) {
            ce.m3829a(th, "MultiTouchSupport", "onTouchEvent");
            th.printStackTrace();
        }
    }

    public long m3808b() {
        return this.f2302f;
    }
}
