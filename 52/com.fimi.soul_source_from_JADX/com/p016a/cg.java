package com.p016a;

import android.text.TextUtils;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.autonavi.aps.amapapi.model.AmapLoc;
import com.tencent.open.yyb.TitleBar;

/* renamed from: com.a.cg */
public class cg {
    private static cg f743a;
    private AmapLoc f744b;
    private long f745c;
    private long f746d;

    static {
        f743a = null;
    }

    private cg() {
        this.f744b = null;
        this.f745c = 0;
        this.f746d = 0;
    }

    public static synchronized cg m1293a() {
        cg cgVar;
        synchronized (cg.class) {
            if (f743a == null) {
                f743a = new cg();
            }
            cgVar = f743a;
        }
        return cgVar;
    }

    private AmapLoc m1294c(AmapLoc amapLoc) {
        if (dn.m1512a(amapLoc) && (amapLoc.m5318b() == 5 || amapLoc.m5318b() == 6)) {
            amapLoc.m5313a(2);
        }
        return amapLoc;
    }

    public synchronized AmapLoc m1295a(AmapLoc amapLoc) {
        if (!dn.m1512a(this.f744b) || !dn.m1512a(amapLoc)) {
            this.f745c = dn.m1519b();
            this.f744b = amapLoc;
            amapLoc = this.f744b;
        } else if (amapLoc.m5343k() != this.f744b.m5343k() || amapLoc.m5341j() >= BitmapDescriptorFactory.HUE_MAGENTA) {
            if (amapLoc.m5335g().equals(GeocodeSearch.GPS)) {
                this.f745c = dn.m1519b();
                this.f744b = amapLoc;
                amapLoc = this.f744b;
            } else if (amapLoc.m5305B() != this.f744b.m5305B()) {
                this.f745c = dn.m1519b();
                this.f744b = amapLoc;
                amapLoc = this.f744b;
            } else if (amapLoc.m5304A().equals(this.f744b.m5304A()) || TextUtils.isEmpty(amapLoc.m5304A())) {
                float a = dn.m1496a(amapLoc, this.f744b);
                float j = this.f744b.m5341j();
                float j2 = amapLoc.m5341j();
                float f = j2 - j;
                long b = dn.m1519b();
                long j3 = b - this.f745c;
                if ((j < 101.0f && j2 > 299.0f) || (j > 299.0f && j2 > 299.0f)) {
                    if (this.f746d == 0) {
                        this.f746d = b;
                    } else if (b - this.f746d > 30000) {
                        this.f745c = b;
                        this.f744b = amapLoc;
                        this.f746d = 0;
                        amapLoc = this.f744b;
                    }
                    this.f744b = m1294c(this.f744b);
                    amapLoc = this.f744b;
                } else if (j2 >= 100.0f || j <= 299.0f) {
                    if (j2 <= 299.0f) {
                        this.f746d = 0;
                    }
                    if (a >= TitleBar.SHAREBTN_RIGHT_MARGIN || ((double) a) <= 0.1d) {
                        if (f < BitmapDescriptorFactory.HUE_MAGENTA) {
                            this.f745c = dn.m1519b();
                            this.f744b = amapLoc;
                            amapLoc = this.f744b;
                        } else if (j3 >= 30000) {
                            this.f745c = dn.m1519b();
                            this.f744b = amapLoc;
                            amapLoc = this.f744b;
                        } else {
                            this.f744b = m1294c(this.f744b);
                            amapLoc = this.f744b;
                        }
                    } else if (f >= -300.0f) {
                        this.f744b = m1294c(this.f744b);
                        amapLoc = this.f744b;
                    } else if (j / j2 >= 2.0f) {
                        this.f745c = b;
                        this.f744b = amapLoc;
                        amapLoc = this.f744b;
                    } else {
                        this.f744b = m1294c(this.f744b);
                        amapLoc = this.f744b;
                    }
                } else {
                    this.f745c = b;
                    this.f744b = amapLoc;
                    this.f746d = 0;
                    amapLoc = this.f744b;
                }
            } else {
                this.f745c = dn.m1519b();
                this.f744b = amapLoc;
                amapLoc = this.f744b;
            }
        }
        return amapLoc;
    }

    public AmapLoc m1296b(AmapLoc amapLoc) {
        return amapLoc;
    }

    public synchronized void m1297b() {
        this.f744b = null;
        this.f745c = 0;
        this.f746d = 0;
    }
}
