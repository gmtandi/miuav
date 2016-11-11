package com.fimi.soul.module.droneFragment;

import android.annotation.TargetApi;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.PoiItem;
import com.fimi.soul.C1205R;
import com.fimi.soul.drone.p116g.C1543c;
import com.fimi.soul.view.photodraweeview.C2020f;
import java.util.HashMap;

@TargetApi(19)
/* renamed from: com.fimi.soul.module.droneFragment.t */
public class C1706t {
    private static C1706t f8331e;
    Marker f8332a;
    private PoiItem f8333b;
    private AMap f8334c;
    private HashMap<String, Marker> f8335d;
    private Marker f8336f;
    private BitmapDescriptor f8337g;
    private BitmapDescriptor f8338h;

    private C1706t() {
        this.f8335d = new HashMap();
    }

    public static C1706t m11144a() {
        if (f8331e == null) {
            f8331e = new C1706t();
        }
        return f8331e;
    }

    private MarkerOptions m11145d(String str) {
        return new MarkerOptions().position(new LatLng(this.f8333b.getLatLonPoint().getLatitude(), this.f8333b.getLatLonPoint().getLongitude())).icon(m11150c(str));
    }

    public void m11146a(AMap aMap, PoiItem poiItem) {
        this.f8334c = aMap;
        this.f8333b = poiItem;
    }

    public void m11147a(String str) {
        if (this.f8335d.containsKey(str)) {
            ((Marker) this.f8335d.get(str)).setPosition(new LatLng(this.f8333b.getLatLonPoint().getLatitude(), this.f8333b.getLatLonPoint().getLongitude()));
            return;
        }
        this.f8332a = this.f8334c.addMarker(m11145d(str));
        this.f8332a.setObject(str);
        if (C1543c.f7224Y.equals(str)) {
            this.f8332a.setAnchor(0.15f, C2020f.f10933c);
            this.f8332a.setZIndex(80.0f);
        } else {
            this.f8332a.setAnchor(0.5f, 0.5f);
            this.f8332a.setZIndex(100.0f);
        }
        this.f8335d.put(str, this.f8332a);
    }

    public void m11148b() {
        for (Object obj : this.f8335d.keySet()) {
            ((Marker) this.f8335d.get(obj)).remove();
        }
        this.f8335d.clear();
    }

    public void m11149b(String str) {
        if (this.f8335d.containsKey(str)) {
            this.f8336f = (Marker) this.f8335d.get(str);
            this.f8335d.remove(str);
            this.f8336f.remove();
        }
    }

    protected BitmapDescriptor m11150c(String str) {
        if (C1543c.f7223X.equals(str)) {
            if (this.f8338h == null) {
                this.f8338h = BitmapDescriptorFactory.fromResource(C1205R.drawable.fuselage);
            }
            return this.f8338h;
        }
        if (this.f8337g == null) {
            this.f8337g = BitmapDescriptorFactory.fromResource(C1205R.drawable.home_point);
        }
        return this.f8337g;
    }

    public PoiItem m11151c() {
        return this.f8333b;
    }

    public void m11152d() {
        if (this.f8337g != null) {
            this.f8337g.recycle();
        }
        if (this.f8338h != null) {
            this.f8338h.recycle();
        }
    }
}
