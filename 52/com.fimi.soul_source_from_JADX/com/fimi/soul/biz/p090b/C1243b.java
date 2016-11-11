package com.fimi.soul.biz.p090b;

import android.content.Context;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.Polyline;
import com.fimi.soul.C1205R;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.utils.ad;
import com.fimi.soul.utils.aj;
import java.util.List;

/* renamed from: com.fimi.soul.biz.b.b */
public class C1243b {
    public volatile boolean f5637a;
    private AMap f5638b;
    private Context f5639c;
    private C1433a f5640d;
    private Marker f5641e;
    private Polyline f5642f;

    public C1243b(AMap aMap, Context context, C1433a c1433a) {
        this.f5640d = c1433a;
        this.f5638b = aMap;
        this.f5639c = context;
    }

    public void m8543a() {
        aj a = ad.m12227a((double) this.f5640d.m9617t().m10361e(), (double) this.f5640d.m9617t().m10360d());
        m8544a(new LatLng(a.m12250a(), a.m12252b()));
    }

    public void m8544a(LatLng latLng) {
        if (this.f5641e == null) {
            this.f5641e = this.f5638b.addMarker(new MarkerOptions().icon(C1255m.m8605a(C1205R.drawable.img_fly_origin_blue)).position(latLng));
            this.f5641e.setAnchor(0.5f, 0.88f);
            this.f5641e.setObject(this);
        }
    }

    public void m8545b() {
        if (this.f5641e != null) {
            this.f5641e.setIcon(C1255m.m8605a(C1205R.drawable.img_fly_origin_red));
        }
    }

    public void m8546b(LatLng latLng) {
        List b = C1247f.m8565k().m8574b();
        if (b != null && !b.contains(latLng)) {
            b.add(latLng);
            if (b.size() < 2) {
                return;
            }
            if (this.f5642f == null) {
                this.f5642f = this.f5638b.addPolyline(C1254l.m8604a(b, 4, this.f5639c.getResources().getColor(C1205R.color.drone_inface_line)).zIndex(50.0f));
            } else {
                this.f5642f.setPoints(b);
            }
        }
    }

    public void m8547c() {
        if (this.f5641e != null) {
            this.f5641e.destroy();
            this.f5641e = null;
        }
        if (this.f5642f != null) {
            this.f5642f.remove();
            this.f5642f = null;
        }
    }

    public void m8548d() {
        this.f5637a = false;
        if (this.f5641e != null) {
            this.f5641e.setTitle(this.f5640d.f6507c.getString(C1205R.string.delete_marker));
            this.f5641e.showInfoWindow();
        }
    }

    public void m8549e() {
        this.f5637a = false;
        if (this.f5641e != null) {
            this.f5641e.hideInfoWindow();
        }
    }

    public void m8550f() {
        this.f5637a = true;
        if (this.f5641e != null) {
            this.f5641e.hideInfoWindow();
        }
    }

    public boolean m8551g() {
        return this.f5641e == null;
    }
}
