package com.fimi.soul.biz.p101h;

import android.database.Cursor;
import android.graphics.Point;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.WeightedLatLng;
import com.fimi.soul.utils.ac;
import com.fimi.soul.utils.ad;
import com.fimi.soul.utils.aj;
import com.tencent.open.yyb.TitleBar;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;

/* renamed from: com.fimi.soul.biz.h.b */
class C1347b implements Runnable {
    final /* synthetic */ C1346a f5997a;

    C1347b(C1346a c1346a) {
        this.f5997a = c1346a;
    }

    public void run() {
        try {
            if (this.f5997a.f5993d.getCameraPosition().zoom < TitleBar.SHAREBTN_RIGHT_MARGIN) {
                for (Circle remove : this.f5997a.f5994e) {
                    remove.remove();
                }
                this.f5997a.f5994e.clear();
                this.f5997a.f5990a.clear();
            }
            LatLng fromScreenLocation = this.f5997a.f5993d.getProjection().fromScreenLocation(new Point(0, 0));
            LatLng latLng = this.f5997a.f5993d.getCameraPosition().target;
            if (latLng != null) {
                aj a = ad.m12228a((double) WeightedLatLng.DEFAULT_INTENSITY, latLng.latitude, latLng.longitude);
                double a2 = ac.m12223c(fromScreenLocation, latLng).m12254a();
                if (a2 < 20000.0d) {
                    a2 = 20000.0d;
                }
                a2 /= 111000.0d;
                if (this.f5997a.f5992c != null) {
                    Cursor rawQuery = this.f5997a.f5992c.rawQuery("SELECT LAT,LON,CLASS from dmz_tb where LAT > " + (a.m12250a() - a2) + " and LAT <" + (a.m12250a() + a2) + " and LON >" + (a.m12252b() - a2) + " and LON < " + (a2 + a.m12252b()), null);
                    if (rawQuery != null && rawQuery.getCount() > 0) {
                        while (rawQuery.moveToNext()) {
                            double d = (double) rawQuery.getFloat(rawQuery.getColumnIndex("LAT"));
                            double d2 = (double) rawQuery.getFloat(rawQuery.getColumnIndex("LON"));
                            byte b = (byte) rawQuery.getInt(rawQuery.getColumnIndex("CLASS"));
                            int i = b & 3;
                            int i2 = ((b & 63) + 1) * XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER;
                            if (d >= 3.52d && d <= 53.0d && d2 >= 73.0d && d2 <= 136.0d) {
                                a = ad.m12227a(d, d2);
                                LatLng latLng2 = new LatLng(a.m12250a(), a.m12252b());
                                if (!this.f5997a.f5990a.contains(latLng2)) {
                                    this.f5997a.f5990a.add(latLng2);
                                    Circle addCircle = this.f5997a.f5993d.addCircle(this.f5997a.f5995f.center(latLng2).radius((double) i2));
                                    if (!this.f5997a.f5994e.contains(addCircle)) {
                                        this.f5997a.f5994e.add(addCircle);
                                    }
                                }
                            }
                        }
                    }
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
