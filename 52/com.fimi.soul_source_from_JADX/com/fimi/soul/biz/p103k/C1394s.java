package com.fimi.soul.biz.p103k;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.hoho.android.usbserial.driver.UsbId;
import it.p074a.p075a.C2799f;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.fimi.soul.biz.k.s */
public class C1394s {
    private static final int f6263a = 10000;
    private static final int f6264b = 2;
    private static C1394s f6265c = null;
    private static final int f6266g = 120000;
    private static int f6267l;
    private AMapLocation f6268d;
    private AMapLocation f6269e;
    private Context f6270f;
    private AMapLocationClient f6271h;
    private AMapLocationClientOption f6272i;
    private List<C1396u> f6273j;
    private final AMapLocationListener f6274k;

    static {
        f6267l = UsbId.SILAB_CP2102;
    }

    @SuppressLint({"ServiceCast"})
    private C1394s(Context context) {
        this.f6268d = null;
        this.f6273j = new ArrayList();
        this.f6274k = new C1395t(this);
        this.f6270f = context;
        this.f6269e = m9343b(context);
    }

    public static C1394s m9340a(Context context) {
        if (f6265c == null) {
            f6265c = new C1394s(context);
        }
        return f6265c;
    }

    private boolean m9342a(String str, String str2) {
        return str == null ? str2 == null : str.equals(str2);
    }

    private AMapLocation m9343b(Context context) {
        return this.f6271h != null ? this.f6271h.getLastKnownLocation() : null;
    }

    private void m9344b(AMapLocation aMapLocation) {
        this.f6268d = aMapLocation;
        for (C1396u a : this.f6273j) {
            a.m9353a(aMapLocation);
        }
    }

    public double m9345a() {
        if (this.f6269e == null) {
            this.f6269e = m9343b(this.f6270f);
        }
        return this.f6269e != null ? this.f6269e.getLatitude() : -1.0d;
    }

    public void m9346a(AMapLocation aMapLocation) {
        this.f6269e = aMapLocation;
    }

    public void m9347a(C1396u c1396u) {
        if (!this.f6273j.contains(c1396u)) {
            this.f6273j.add(c1396u);
        }
        this.f6271h = new AMapLocationClient(this.f6270f);
        this.f6271h.setLocationListener(this.f6274k);
        this.f6272i = new AMapLocationClientOption();
        this.f6272i.setLocationMode(AMapLocationMode.Hight_Accuracy);
        this.f6272i.setGpsFirst(true);
        this.f6272i.setInterval(1000);
        this.f6272i.setNeedAddress(true);
        this.f6272i.setOnceLocation(false);
        this.f6271h.setLocationOption(this.f6272i);
        this.f6271h.startLocation();
    }

    protected boolean m9348a(AMapLocation aMapLocation, AMapLocation aMapLocation2) {
        if (aMapLocation2 == null) {
            return true;
        }
        long time = aMapLocation.getTime() - aMapLocation2.getTime();
        boolean z = time > 120000;
        boolean z2 = time < -120000;
        boolean z3 = time > 0;
        if (z) {
            return true;
        }
        if (z2) {
            return false;
        }
        int accuracy = (int) (aMapLocation.getAccuracy() - aMapLocation2.getAccuracy());
        return !(accuracy < 0) ? (!z3 || (accuracy > 0)) ? z3 && !(accuracy > C2799f.f14282t) && m9342a(aMapLocation.getProvider(), aMapLocation2.getProvider()) : true : true;
    }

    public double m9349b() {
        if (this.f6269e == null) {
            this.f6269e = m9343b(this.f6270f);
        }
        return this.f6269e != null ? this.f6269e.getLongitude() : -1.0d;
    }

    public void m9350b(C1396u c1396u) {
        if (this.f6273j.contains(c1396u)) {
            this.f6273j.remove(c1396u);
        }
    }

    public void m9351c() {
        if (this.f6271h != null) {
            this.f6271h.onDestroy();
            this.f6271h = null;
        }
    }

    public Location m9352d() {
        return this.f6268d == null ? m9343b(this.f6270f) : this.f6268d;
    }
}
