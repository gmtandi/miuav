package com.fimi.soul.biz.p103k;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.fimi.soul.base.C1236a;

/* renamed from: com.fimi.soul.biz.k.t */
class C1395t implements AMapLocationListener {
    final /* synthetic */ C1394s f6275a;

    C1395t(C1394s c1394s) {
        this.f6275a = c1394s;
    }

    public void onLocationChanged(AMapLocation aMapLocation) {
        if (!(aMapLocation == null || 0.0d == aMapLocation.getLatitude() || 0.0d == aMapLocation.getLongitude())) {
            if (this.f6275a.f6268d == null) {
                this.f6275a.m9344b(aMapLocation);
            } else if (this.f6275a.m9348a(aMapLocation, this.f6275a.f6268d)) {
                this.f6275a.m9344b(aMapLocation);
            } else {
                this.f6275a.m9344b(this.f6275a.f6268d);
            }
        }
        C1236a.m8532a("====location:" + aMapLocation.getLatitude() + " | " + aMapLocation.getLongitude(), C1394s.class);
    }
}
