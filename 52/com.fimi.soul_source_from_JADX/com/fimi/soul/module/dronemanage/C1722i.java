package com.fimi.soul.module.dronemanage;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.graphics.Point;
import android.location.Location;
import android.support.v4.internal.view.SupportMenu;
import android.util.DisplayMetrics;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.LatLngBounds.Builder;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.maps.model.WeightedLatLng;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.fimi.kernel.p083d.C1160b;
import com.fimi.kernel.p084e.ab;
import com.fimi.soul.C1205R;
import com.fimi.soul.biz.p092c.C1263a;
import com.fimi.soul.biz.p092c.C1266d;
import com.fimi.soul.drone.C1234i;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.p116g.C1543c;
import com.fimi.soul.drone.p117h.aj;
import com.fimi.soul.drone.p117h.ao;
import com.fimi.soul.drone.p117h.bh;
import com.fimi.soul.module.droneFragment.C1691e;
import com.fimi.soul.module.droneFragment.C1706t;
import com.fimi.soul.utils.C1966f;
import com.fimi.soul.utils.ac;
import com.fimi.soul.utils.ad;
import com.fimi.soul.view.photodraweeview.C2020f;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.module.dronemanage.i */
public class C1722i implements C1234i {
    PolylineOptions f8472a;
    List<bh> f8473b;
    C1706t f8474c;
    PolylineOptions f8475d;
    int f8476e;
    private AMap f8477f;
    private Context f8478g;
    private C1433a f8479h;
    private LatLng f8480i;
    private LatLng f8481j;
    private int f8482k;
    private boolean f8483l;
    private Marker f8484m;
    private float f8485n;
    private C1738z f8486o;
    private C1717d f8487p;
    private int f8488q;
    private boolean f8489r;
    private int f8490s;
    private Polyline f8491t;
    private Polyline f8492u;
    private List<LatLng> f8493v;
    private float f8494w;

    public C1722i(AMap aMap, Context context, C1433a c1433a) {
        this.f8481j = null;
        this.f8482k = 20;
        this.f8483l = true;
        this.f8475d = null;
        this.f8493v = new ArrayList();
        this.f8494w = 0.0f;
        this.f8476e = 0;
        this.f8477f = aMap;
        this.f8478g = context;
        this.f8479h = c1433a;
        this.f8474c = C1706t.m11144a();
        m11242a();
    }

    private double m11228a(Marker marker, bh bhVar, LatLng latLng) {
        double a = ac.m12223c(latLng, bhVar.m10499b()).m12254a();
        marker.setTitle(this.f8478g.getResources().getString(C1205R.string.distancetag) + ab.m8015a(a, 1) + this.f8478g.getResources().getString(C1205R.string.meter));
        return a;
    }

    private void m11229a(aj ajVar) {
        com.fimi.soul.utils.aj a = ad.m12227a(ajVar.m10301b(), ajVar.m10302c());
        this.f8480i = new LatLng(a.m12250a(), a.m12252b());
        this.f8474c.m11146a(this.f8477f, new PoiItem(C2915a.f14760f, new LatLonPoint(this.f8480i.latitude, this.f8480i.longitude), C2915a.f14760f, C2915a.f14760f));
        this.f8474c.m11147a(C1543c.f7224Y);
    }

    private void m11231b(LatLng latLng) {
        DisplayMetrics displayMetrics = this.f8478g.getResources().getDisplayMetrics();
        Point toScreenLocation = this.f8477f.getProjection().toScreenLocation(latLng);
        if (toScreenLocation.y > displayMetrics.heightPixels || toScreenLocation.y < 0 || toScreenLocation.x < 0 || toScreenLocation.x > displayMetrics.widthPixels) {
            this.f8479h.m9589a(C1584h.CHANGELOCATIONBUTTONOUT);
            if (this.f8479h.m9614q().m10272a() / 100.0d >= WeightedLatLng.DEFAULT_INTENSITY) {
                this.f8477f.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, this.f8494w));
                return;
            }
            return;
        }
        this.f8479h.m9589a(C1584h.CHANGELOCATIONBUTTONIN);
    }

    private void m11232c(LatLng latLng) {
        if (this.f8479h.aa()) {
            m11240h(latLng);
            m11239g(latLng);
        } else if (this.f8491t != null) {
            this.f8491t.remove();
        }
        m11237f(latLng);
        m11235e(latLng);
        m11233d(latLng);
    }

    private void m11233d(LatLng latLng) {
        if (aj.m11198a() == 1) {
            this.f8473b = this.f8486o.m11308g();
        } else if (aj.m11198a() == 2) {
            this.f8473b = this.f8487p.m11217d();
        }
        if (this.f8473b != null && this.f8473b.size() > 0 && this.f8482k == 6 && this.f8488q > 0 && this.f8488q <= this.f8473b.size()) {
            m11228a(this.f8484m, (bh) this.f8473b.get(this.f8488q - 1), latLng);
            if (this.f8482k == 6) {
                this.f8484m.showInfoWindow();
            }
        } else if (this.f8482k == 4) {
            if (this.f8473b != null && this.f8473b.size() > 0) {
                m11228a(this.f8484m, (bh) this.f8473b.get(0), latLng);
                this.f8484m.showInfoWindow();
            }
        } else if (this.f8484m.isInfoWindowShown() && C1691e.m11080d().get() != 1) {
            this.f8484m.hideInfoWindow();
        }
    }

    private void m11234e() {
        Builder builder = new Builder();
        Location b = this.f8479h.m9592b();
        builder.include(this.f8481j);
        if (b != null) {
            builder.include(new LatLng(b.getLatitude(), b.getLongitude()));
        }
        if (this.f8480i != null) {
            builder.include(this.f8480i);
        }
        m11243a(builder.build());
        C1263a a = C1263a.m8645a();
        a.m8649a(true);
        a.m8648a(C1266d.DRONE);
    }

    private void m11235e(LatLng latLng) {
        if (this.f8484m != null) {
            C1691e.m11079c().m11085a(this.f8479h);
            this.f8484m.setPosition(latLng);
        } else {
            this.f8484m = this.f8477f.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(C1205R.drawable.icon_fly_handpiece_location)).draggable(false));
        }
        this.f8484m.setAnchor(0.5f, 0.5f);
        if (String.valueOf(this.f8485n) != null) {
            this.f8484m.setRotateAngle((-this.f8485n) + m11238g());
        }
    }

    private void m11236f() {
        if (!this.f8479h.m9570Q()) {
            if (!this.f8479h.m9569P().m9995a()) {
                if (this.f8484m != null) {
                    this.f8484m.destroy();
                    this.f8484m = null;
                    C1706t.m11144a().m11149b(C1543c.f7223X);
                }
                if (this.f8492u != null) {
                    this.f8492u.remove();
                    this.f8492u = null;
                }
                if (this.f8491t != null) {
                    this.f8491t.remove();
                    this.f8491t = null;
                }
                C1706t.m11144a().m11149b(C1543c.f7224Y);
            } else if (!(this.f8492u == null || this.f8492u.isDottedLine())) {
                this.f8492u.setDottedLine(true);
            }
            C1691e.m11081f();
        }
    }

    private void m11237f(LatLng latLng) {
        if (this.f8477f.getCameraPosition() != null) {
            this.f8494w = this.f8477f.getCameraPosition().zoom;
        }
        if (this.f8483l && this.f8481j.latitude > WeightedLatLng.DEFAULT_INTENSITY && this.f8481j.longitude > WeightedLatLng.DEFAULT_INTENSITY && this.f8479h.m9617t().m10359c() >= 6) {
            this.f8476e++;
            this.f8479h.m9603f(true);
            this.f8494w = 18.0f;
            if (this.f8479h.aa()) {
                Builder builder = new Builder();
                Location b = this.f8479h.m9592b();
                builder.include(this.f8481j);
                if (b != null) {
                    builder.include(new LatLng(b.getLatitude(), b.getLongitude()));
                }
                if (this.f8480i != null) {
                    builder.include(this.f8480i);
                }
                m11243a(builder.build());
                return;
            }
            this.f8483l = false;
            this.f8477f.animateCamera(CameraUpdateFactory.newLatLngZoom(this.f8481j, this.f8494w));
        }
    }

    private synchronized float m11238g() {
        float f = 0.0f;
        synchronized (this) {
            try {
                CameraPosition cameraPosition = this.f8477f.getCameraPosition();
                if (cameraPosition != null) {
                    f = cameraPosition.bearing;
                }
            } catch (Exception e) {
            }
        }
        return f;
    }

    private void m11239g(LatLng latLng) {
        if (this.f8480i != null) {
            this.f8493v.add(latLng);
            this.f8493v.add(this.f8480i);
            if (this.f8475d == null) {
                this.f8475d = new PolylineOptions();
                this.f8475d.color(this.f8479h.f6507c.getResources().getColor(C1205R.color.drone_home_line)).width(5.0f);
            }
            if (this.f8492u != null && this.f8492u.isDottedLine()) {
                this.f8492u.remove();
                this.f8492u = null;
            }
            if (this.f8492u != null) {
                this.f8492u.setPoints(this.f8493v);
            } else {
                this.f8492u = this.f8477f.addPolyline(this.f8475d.add(latLng, this.f8480i));
            }
            this.f8493v.clear();
        }
    }

    private void m11240h(LatLng latLng) {
        if (this.f8491t != null) {
            this.f8491t.remove();
        }
        this.f8491t = this.f8477f.addPolyline(m11241a(latLng));
    }

    public PolylineOptions m11241a(LatLng latLng) {
        if (this.f8472a == null) {
            this.f8472a = new PolylineOptions();
            this.f8472a.setDottedLine(true);
            this.f8472a.width(C2020f.f10931a);
            this.f8472a.color(SupportMenu.CATEGORY_MASK);
        }
        this.f8472a.add(latLng);
        List points = this.f8472a.getPoints();
        if (points != null && points.size() >= 10) {
            points.remove(points.get(0));
        }
        return this.f8472a;
    }

    public void m11242a() {
        this.f8479h.m9590a((C1234i) this);
        C1691e.m11079c().m11087e();
    }

    public void m11243a(LatLngBounds latLngBounds) {
        this.f8477f.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, C1966f.m12465a(this.f8479h.f6507c, BitmapDescriptorFactory.HUE_ORANGE), C1966f.m12465a(this.f8479h.f6507c, BitmapDescriptorFactory.HUE_ORANGE), 0), new C1723j(this));
    }

    public void m11244a(Marker marker) {
        if (this.f8481j != null && this.f8484m != null) {
            this.f8484m.setTitle(this.f8478g.getResources().getString(C1205R.string.distancetag) + ab.m8015a(ac.m12223c(this.f8481j, marker.getPosition()).m12254a(), 1) + this.f8478g.getResources().getString(C1205R.string.meter));
            this.f8484m.showInfoWindow();
        }
    }

    public void m11245a(C1717d c1717d) {
        this.f8487p = c1717d;
    }

    public void m11246a(C1738z c1738z) {
        this.f8486o = c1738z;
    }

    public void m11247b() {
        this.f8483l = true;
    }

    public void m11248c() {
        C1691e.m11079c().m11086b(0);
        C1691e.m11079c();
        C1691e.m11081f();
        this.f8474c.m11152d();
    }

    public void m11249d() {
        if (this.f8484m != null) {
            this.f8484m.destroy();
            this.f8484m = null;
        }
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        switch (C1724k.f8496a[c1584h.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                m11236f();
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                if (c1433a.m9617t().m10359c() > 3) {
                    ao t = c1433a.m9617t();
                    com.fimi.soul.utils.aj a = ad.m12227a((double) t.m10361e(), (double) t.m10360d());
                    this.f8481j = new LatLng(a.m12250a(), a.m12252b());
                    c1433a.m9585a(this.f8481j);
                    if (c1433a.ah().m10169d() != (byte) 2) {
                        m11232c(this.f8481j);
                    }
                }
            case Type.BYTE /*3*/:
                m11236f();
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                if (!c1433a.aa() || c1433a.ah().m10172g().isLightStream()) {
                    this.f8489r = false;
                    C1706t.m11144a().m11149b(C1543c.f7224Y);
                    if (this.f8492u != null) {
                        this.f8492u.remove();
                        this.f8492u = null;
                    }
                } else if (c1433a.m9618u().m10305f()) {
                    m11229a(c1433a.m9618u());
                    if (!this.f8489r && this.f8490s == 2 && this.f8482k == 1) {
                        this.f8489r = true;
                        Editor edit = com.fimi.kernel.p084e.ad.m8019a(this.f8478g).edit();
                        edit.putFloat(C1543c.bG, (float) c1433a.m9618u().m10301b()).commit();
                        edit.putFloat(C1543c.bH, (float) c1433a.m9618u().m10302c()).commit();
                        if (C1160b.m7953b(c1433a.f6507c) != null) {
                            C1160b.m7953b(c1433a.f6507c).m7959a(c1433a.f6507c.getString(C1205R.string.set_home));
                        }
                        m11234e();
                    }
                    if (this.f8490s != this.f8482k) {
                        this.f8490s = this.f8482k;
                    }
                }
                this.f8488q = c1433a.m9618u().m10304e();
            case Type.INT /*5*/:
                this.f8485n = (float) (c1433a.m9614q().m10295k() / 10.0d);
            case Type.FLOAT /*6*/:
                this.f8482k = c1433a.ah().m10170e();
            case Type.LONG /*7*/:
                if (String.valueOf(this.f8485n) != null && this.f8484m != null) {
                    this.f8484m.setRotateAngle((-this.f8485n) + c1433a.ac());
                }
            default:
        }
    }
}
