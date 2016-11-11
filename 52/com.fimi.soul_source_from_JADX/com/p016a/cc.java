package com.p016a;

import com.amap.api.maps.model.HeatmapTileProvider;
import com.amap.api.maps.model.WeightedLatLng;
import com.fimi.soul.module.setting.newhand.ae;
import com.hoho.android.usbserial.driver.FtdiSerialDriver;
import com.tencent.connect.common.Constants;
import it.p074a.p075a.C2799f;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import org.p122a.p123a.C2915a;

/* renamed from: com.a.cc */
class cc implements Comparable<cc> {
    final /* synthetic */ cb f729a;
    private ArrayList<ce> f730b;
    private ArrayList<ce> f731c;
    private double f732d;
    private String f733e;

    public cc(cb cbVar) {
        this.f729a = cbVar;
        this.f730b = new ArrayList();
        this.f731c = new ArrayList();
        this.f732d = 0.0d;
        this.f733e = C2915a.f14760f;
        this.f732d = 0.0d;
        this.f733e = C2915a.f14760f;
    }

    public int m1282a(cc ccVar) {
        double d = ccVar.f732d - this.f732d;
        return d > 0.0d ? 1 : d == 0.0d ? 0 : -1;
    }

    public void m1283a() {
        double size = (double) this.f731c.size();
        Iterator it = this.f730b.iterator();
        double d = 0.0d;
        while (it.hasNext()) {
            d = (((ce) it.next()).f740e > 1 ? HeatmapTileProvider.DEFAULT_OPACITY : WeightedLatLng.DEFAULT_INTENSITY) + d;
        }
        this.f732d = (d * 3.0d) + size;
        this.f732d += ((d * 3.0d) + 0.1d) * (size + 0.1d);
    }

    public void m1284a(ce ceVar) {
        if (ceVar.f740e == 0) {
            this.f731c.add(ceVar);
        } else if (ceVar.f740e > 0) {
            this.f730b.add(ceVar);
        }
        if (Constants.VIA_RESULT_SUCCESS.equals(this.f733e)) {
            this.f733e = ceVar.f739d;
        }
    }

    public ce m1285b() {
        double d = 0.0d;
        int i = this.f730b.isEmpty() ? 0 : 3;
        if (this.f731c.isEmpty()) {
            Iterator it = this.f730b.iterator();
            if (!it.hasNext()) {
                return null;
            }
            ce ceVar = (ce) it.next();
            if (ceVar.f740e == 1) {
                return new ce(this.f729a, ceVar.f736a, ceVar.f737b, ceVar.f738c, this.f733e, this.f732d, 1);
            }
            int i2 = 0 < ceVar.f738c ? ceVar.f738c : 0;
            double d2 = 0.0d + WeightedLatLng.DEFAULT_INTENSITY;
            return new ce(this.f729a, (0.0d + ceVar.f736a) / d2, (0.0d + ceVar.f737b) / d2, i2 > FtdiSerialDriver.USB_WRITE_TIMEOUT_MILLIS ? FtdiSerialDriver.USB_WRITE_TIMEOUT_MILLIS : i2, this.f733e, this.f732d, 2);
        }
        ArrayList arrayList = new ArrayList();
        Iterator it2 = this.f731c.iterator();
        double d3 = 0.0d;
        double d4 = 0.0d;
        while (it2.hasNext()) {
            ce ceVar2 = (ce) it2.next();
            arrayList.add(Integer.valueOf(ceVar2.f738c));
            d3 += ceVar2.f736a;
            d4 = WeightedLatLng.DEFAULT_INTENSITY + d4;
            d = ceVar2.f737b + d;
        }
        Collections.sort(arrayList);
        i2 = arrayList.size() == 1 ? ((Integer) arrayList.get(0)).intValue() : ((Integer) arrayList.get(arrayList.size() / 2)).intValue();
        int i3 = this.f731c.size() == 1 ? C2799f.f14263a : i2 > C2799f.f14263a ? ae.f9482j : i2 < 30 ? 30 : i2;
        return new ce(this.f729a, d3 / d4, d / d4, i3, this.f733e, this.f732d, i);
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m1282a((cc) obj);
    }
}
