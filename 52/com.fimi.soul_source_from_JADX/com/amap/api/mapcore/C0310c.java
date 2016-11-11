package com.amap.api.mapcore;

import com.amap.api.mapcore.util.bm;
import com.amap.api.mapcore.util.bp;
import com.amap.api.mapcore.util.bp.C0359a;
import com.amap.api.mapcore.util.bu;
import com.amap.api.mapcore.util.bv;
import com.amap.api.mapcore.util.bv.C0363a;
import com.amap.api.mapcore.util.ce;
import com.amap.api.maps.MapsInitializer;

/* renamed from: com.amap.api.mapcore.c */
class C0310c extends Thread {
    final /* synthetic */ AMapDelegateImp f1923a;

    C0310c(AMapDelegateImp aMapDelegateImp) {
        this.f1923a = aMapDelegateImp;
    }

    public void run() {
        try {
            if (MapsInitializer.getNetWorkEnable()) {
                bv a = new C0363a(C0330s.f2069b, "3.3.1", C0330s.f2071d).m3757a(new String[]{"com.amap.api.maps", "com.amap.api.mapcore", "com.autonavi.amap.mapcore"}).m3758a();
                bm.m3660b(this.f1923a.f1504H, a);
                if (bm.f2214a == 0) {
                    this.f1923a.f1534l.sendEmptyMessage(2);
                }
                C0359a a2 = bp.m3677a(this.f1923a.f1504H, a, "common;exception;sdkcoordinate");
                if (a2 != null) {
                    if (a2.f2252g != null) {
                        a.m3764a(a2.f2252g.f2239a);
                    }
                    if (a2.f2254i != null) {
                        new bu(this.f1923a.f1504H, C0330s.f2069b, a2.f2254i.f2241a, a2.f2254i.f2242b).m3746a();
                    }
                }
                C0330s.f2075h = a;
                ce.m3828a(this.f1923a.f1504H, a);
                interrupt();
                this.f1923a.m2518f(false);
            }
        } catch (Throwable th) {
            interrupt();
            ce.m3829a(th, "AMapDelegateImpGLSurfaceView", "mVerfy");
            th.printStackTrace();
        }
    }
}
