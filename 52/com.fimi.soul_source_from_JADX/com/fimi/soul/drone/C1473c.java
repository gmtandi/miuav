package com.fimi.soul.drone;

import com.fimi.kernel.C1189f;
import com.fimi.kernel.p082c.C1156a;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.biz.p098j.C1330i;
import com.fimi.soul.entity.FlightTimeInfo;
import com.fimi.soul.entity.PlaneMsg;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.drone.c */
class C1473c implements C1330i {
    final /* synthetic */ C1433a f7026a;

    C1473c(C1433a c1433a) {
        this.f7026a = c1433a;
    }

    public void m9853a(PlaneMsg planeMsg) {
        if (planeMsg.isSuccess()) {
            C1156a c = C1189f.m8333c();
            FlightTimeInfo flightTimeInfo = (FlightTimeInfo) planeMsg.getData();
            c.m7929a(C1236a.f5597U, 0);
            c.m7929a(C1236a.f5599W, 0);
            c.m7929a(C1236a.f5595S, flightTimeInfo.getTotalFlyTime());
            c.m7929a(C1236a.f5596T, flightTimeInfo.getTotalRealFlyTime());
            c.m7929a(C1236a.f5600X, flightTimeInfo.getTotalDistance());
            c.m7928a(C1236a.f5598V, flightTimeInfo.getFlyCount());
        } else if (this.f7026a.f6510f != null && !this.f7026a.f6508d.equals(C2915a.f14760f) && !C2915a.f14760f.equals(this.f7026a.f6509e)) {
            this.f7026a.f6510f.m12522b(this.f7026a.f6508d, this.f7026a.f6509e);
        }
    }
}
