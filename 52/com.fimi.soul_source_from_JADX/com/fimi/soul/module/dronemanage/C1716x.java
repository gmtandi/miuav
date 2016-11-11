package com.fimi.soul.module.dronemanage;

import android.os.Handler;
import android.os.Message;
import com.amap.api.maps.model.PolylineOptions;
import com.fimi.kernel.p084e.ah;

/* renamed from: com.fimi.soul.module.dronemanage.x */
abstract class C1716x {
    private C1737y f8426a;
    public PolylineOptions f8427b;

    public C1716x() {
        this.f8426a = new C1737y(this);
        this.f8427b = new PolylineOptions();
        this.f8427b.color(-256).width(5.0f);
    }

    protected abstract void m11204a(Message message);

    protected void m11205a(Runnable runnable) {
        ah.m8075a(runnable);
    }

    public void m11206h() {
        if (this.f8427b != null) {
            this.f8427b = null;
            this.f8427b = new PolylineOptions();
            this.f8427b.color(-256).width(5.0f);
        }
    }

    protected Handler m11207i() {
        return this.f8426a;
    }
}
