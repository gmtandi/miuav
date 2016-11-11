package com.fimi.soul.module.setting;

import android.util.Log;
import com.fimi.kernel.p082c.C1156a;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.biz.p098j.C1330i;
import com.fimi.soul.entity.FlightTimeInfo;
import com.fimi.soul.entity.PlaneMsg;

/* renamed from: com.fimi.soul.module.setting.w */
class C1893w implements C1330i {
    final /* synthetic */ C1156a f9620a;
    final /* synthetic */ C1892v f9621b;

    C1893w(C1892v c1892v, C1156a c1156a) {
        this.f9621b = c1892v;
        this.f9620a = c1156a;
    }

    public void m11898a(PlaneMsg planeMsg) {
        if (planeMsg.isSuccess()) {
            FlightTimeInfo flightTimeInfo = (FlightTimeInfo) planeMsg.getData();
            this.f9620a.m7929a(C1236a.f5597U, 0);
            this.f9620a.m7929a(C1236a.f5599W, 0);
            Log.d("moweiru", flightTimeInfo.getTotalRealFlyTime() + "getTotalRealFlyTime");
            this.f9620a.m7929a(C1236a.f5595S, flightTimeInfo.getTotalFlyTime());
            this.f9620a.m7929a(C1236a.f5596T, flightTimeInfo.getTotalRealFlyTime());
            this.f9620a.m7929a(C1236a.f5600X, flightTimeInfo.getTotalDistance());
            this.f9620a.m7928a(C1236a.f5598V, flightTimeInfo.getFlyCount());
            this.f9621b.f9619a.m11585a();
        }
    }
}
