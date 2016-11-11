package com.fimi.soul.service;

import com.fimi.kernel.p082c.C1156a;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.biz.p098j.C1330i;
import com.fimi.soul.entity.FlightTimeInfo;
import com.fimi.soul.entity.PlaneMsg;

/* renamed from: com.fimi.soul.service.t */
class C1953t implements C1330i {
    final /* synthetic */ C1156a f9994a;
    final /* synthetic */ C1952s f9995b;

    C1953t(C1952s c1952s, C1156a c1156a) {
        this.f9995b = c1952s;
        this.f9994a = c1156a;
    }

    public void m12191a(PlaneMsg planeMsg) {
        if (planeMsg.isSuccess()) {
            FlightTimeInfo flightTimeInfo = (FlightTimeInfo) planeMsg.getData();
            this.f9994a.m7929a(C1236a.f5595S, flightTimeInfo.getTotalFlyTime());
            this.f9994a.m7929a(C1236a.f5596T, flightTimeInfo.getTotalRealFlyTime());
            this.f9994a.m7929a(C1236a.f5600X, flightTimeInfo.getTotalDistance());
            this.f9994a.m7928a(C1236a.f5598V, flightTimeInfo.getFlyCount());
        }
    }
}
