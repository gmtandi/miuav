package com.amap.api.mapcore;

import android.location.Location;
import com.amap.api.mapcore.util.ce;
import com.amap.api.maps.LocationSource.OnLocationChangedListener;

/* renamed from: com.amap.api.mapcore.l */
class C0320l implements OnLocationChangedListener {
    Location f1944a;
    private ab f1945b;

    C0320l(ab abVar) {
        this.f1945b = abVar;
    }

    public void onLocationChanged(Location location) {
        this.f1944a = location;
        try {
            if (this.f1945b.m2326y()) {
                this.f1945b.m2251a(location);
            }
        } catch (Throwable e) {
            ce.m3829a(e, "AMapOnLocationChangedListener", "onLocationChanged");
            e.printStackTrace();
        }
    }
}
