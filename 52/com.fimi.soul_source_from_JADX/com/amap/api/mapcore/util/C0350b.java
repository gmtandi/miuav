package com.amap.api.mapcore.util;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import com.tencent.mm.sdk.platformtools.Util;

/* renamed from: com.amap.api.mapcore.util.b */
public abstract class C0350b {
    protected final Context f2154a;
    protected boolean f2155b;
    protected MotionEvent f2156c;
    protected MotionEvent f2157d;
    protected float f2158e;
    protected float f2159f;
    protected long f2160g;

    public C0350b(Context context) {
        this.f2154a = context;
    }

    protected void m3505a() {
        if (this.f2156c != null) {
            this.f2156c.recycle();
            this.f2156c = null;
        }
        if (this.f2157d != null) {
            this.f2157d.recycle();
            this.f2157d = null;
        }
        this.f2155b = false;
    }

    protected abstract void m3506a(int i, MotionEvent motionEvent);

    public boolean m3507a(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & Util.MASK_8BIT;
        if (this.f2155b) {
            m3508b(action, motionEvent);
        } else {
            m3506a(action, motionEvent);
        }
        return true;
    }

    protected abstract void m3508b(int i, MotionEvent motionEvent);

    protected void m3509b(MotionEvent motionEvent) {
        if (this.f2156c != null) {
            MotionEvent motionEvent2 = this.f2156c;
            if (this.f2157d != null) {
                this.f2157d.recycle();
                this.f2157d = null;
            }
            this.f2157d = MotionEvent.obtain(motionEvent);
            this.f2160g = motionEvent.getEventTime() - motionEvent2.getEventTime();
            this.f2158e = motionEvent.getPressure(m3510c(motionEvent));
            this.f2159f = motionEvent2.getPressure(m3510c(motionEvent2));
        }
    }

    public final int m3510c(MotionEvent motionEvent) {
        return (motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
    }
}
