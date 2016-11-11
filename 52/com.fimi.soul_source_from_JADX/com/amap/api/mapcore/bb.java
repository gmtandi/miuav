package com.amap.api.mapcore;

import android.content.Context;
import android.location.Location;
import android.os.RemoteException;
import com.amap.api.mapcore.util.ce;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapProjection;
import com.fimi.soul.view.photodraweeview.C2020f;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

class bb {
    private ab f1691a;
    private Marker f1692b;
    private ad f1693c;
    private MyLocationStyle f1694d;
    private LatLng f1695e;
    private double f1696f;
    private Context f1697g;
    private bk f1698h;
    private int f1699i;
    private boolean f1700j;
    private final String f1701k;
    private final String f1702l;
    private boolean f1703m;

    bb(ab abVar, Context context) {
        this.f1699i = 1;
        this.f1700j = false;
        this.f1701k = "location_map_gps_locked.png";
        this.f1702l = "location_map_gps_3d.png";
        this.f1703m = false;
        this.f1697g = context;
        this.f1691a = abVar;
        this.f1698h = new bk(this.f1697g, abVar);
    }

    private void m2893b(float f) {
        if (this.f1691a != null) {
            try {
                this.f1691a.m2253a(C0325p.m3307c(f));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void m2894b(Location location) {
        float bearing = location.getBearing() % 360.0f;
        if (bearing > BitmapDescriptorFactory.HUE_CYAN) {
            bearing -= 360.0f;
        } else if (bearing < -180.0f) {
            bearing += 360.0f;
        }
        if (this.f1692b != null) {
            this.f1692b.setRotateAngle(-bearing);
        }
    }

    private void m2895c(float f) {
        if (this.f1691a != null) {
            try {
                this.f1691a.m2253a(C0325p.m3308d(f));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void m2896f() {
        if (this.f1692b != null) {
            m2895c(0.0f);
            this.f1698h.m3042b();
            if (!this.f1703m) {
                this.f1692b.setIcon(BitmapDescriptorFactory.fromAsset("location_map_gps_locked.png"));
            }
            this.f1692b.setFlat(false);
            m2893b(0.0f);
        }
    }

    private void m2897g() {
        if (this.f1692b != null) {
            m2895c(0.0f);
            this.f1698h.m3042b();
            if (!this.f1703m) {
                this.f1692b.setIcon(BitmapDescriptorFactory.fromAsset("location_map_gps_locked.png"));
            }
            this.f1692b.setFlat(false);
            m2893b(0.0f);
        }
    }

    private void m2898h() {
        if (this.f1692b != null) {
            this.f1692b.setRotateAngle(0.0f);
            this.f1698h.m3040a();
            if (!this.f1703m) {
                this.f1692b.setIcon(BitmapDescriptorFactory.fromAsset("location_map_gps_3d.png"));
            }
            this.f1692b.setFlat(true);
            try {
                this.f1691a.m2253a(C0325p.m3292a(17.0f));
                m2893b(45.0f);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void m2899i() {
        if (this.f1699i != 1 || !this.f1700j) {
            try {
                IPoint iPoint = new IPoint();
                MapProjection.lonlat2Geo(this.f1695e.longitude, this.f1695e.latitude, iPoint);
                this.f1691a.m2285b(C0325p.m3302a(iPoint));
            } catch (Throwable e) {
                ce.m3829a(e, "MyLocationOverlay", "locaitonFollow");
                e.printStackTrace();
            }
        }
    }

    private void m2900j() {
        if (this.f1694d == null) {
            this.f1694d = new MyLocationStyle();
            this.f1694d.myLocationIcon(BitmapDescriptorFactory.fromAsset("location_map_gps_locked.png"));
            m2902l();
            return;
        }
        this.f1703m = true;
        m2902l();
    }

    private void m2901k() {
        if (this.f1693c != null) {
            try {
                this.f1691a.m2279a(this.f1693c.m2574c());
            } catch (Throwable e) {
                ce.m3829a(e, "MyLocationOverlay", "locationIconRemove");
                e.printStackTrace();
            }
            this.f1693c = null;
        }
        if (this.f1692b != null) {
            this.f1692b.remove();
            this.f1692b.destroy();
            this.f1692b = null;
            this.f1698h.m3041a(null);
        }
    }

    private void m2902l() {
        try {
            this.f1693c = this.f1691a.m2232a(new CircleOptions().strokeWidth(this.f1694d.getStrokeWidth()).fillColor(this.f1694d.getRadiusFillColor()).strokeColor(this.f1694d.getStrokeColor()).center(new LatLng(0.0d, 0.0d)).zIndex(C2020f.f10933c));
            if (this.f1695e != null) {
                this.f1693c.m2635a(this.f1695e);
            }
            this.f1693c.m2633a(this.f1696f);
            this.f1692b = this.f1691a.m2238a(new MarkerOptions().visible(false).anchor(this.f1694d.getAnchorU(), this.f1694d.getAnchorV()).icon(this.f1694d.getMyLocationIcon()).position(new LatLng(0.0d, 0.0d)));
            m2905a(this.f1699i);
            if (this.f1695e != null) {
                this.f1692b.setPosition(this.f1695e);
                this.f1692b.setVisible(true);
            }
            this.f1698h.m3041a(this.f1692b);
        } catch (Throwable e) {
            ce.m3829a(e, "MyLocationOverlay", "myLocStyle");
            e.printStackTrace();
        }
    }

    public void m2903a() {
        if (this.f1699i == 3 && this.f1698h != null) {
            this.f1698h.m3040a();
        }
    }

    public void m2904a(float f) {
        if (this.f1692b != null) {
            this.f1692b.setRotateAngle(f);
        }
    }

    public void m2905a(int i) {
        this.f1699i = i;
        this.f1700j = false;
        switch (this.f1699i) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                m2896f();
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                m2897g();
            case Type.BYTE /*3*/:
                m2898h();
            default:
        }
    }

    public void m2906a(Location location) {
        if (location != null) {
            this.f1695e = new LatLng(location.getLatitude(), location.getLongitude());
            this.f1696f = (double) location.getAccuracy();
            if (this.f1692b == null && this.f1693c == null) {
                m2900j();
            }
            if (this.f1692b != null) {
                this.f1692b.setPosition(this.f1695e);
            }
            if (this.f1693c != null) {
                try {
                    this.f1693c.m2635a(this.f1695e);
                    if (this.f1696f != -1.0d) {
                        this.f1693c.m2633a(this.f1696f);
                    }
                } catch (Throwable e) {
                    ce.m3829a(e, "MyLocationOverlay", "setCentAndRadius");
                    e.printStackTrace();
                }
                m2899i();
                if (this.f1699i != 3) {
                    m2894b(location);
                }
                this.f1700j = true;
            }
        }
    }

    public void m2907a(MyLocationStyle myLocationStyle) {
        try {
            this.f1694d = myLocationStyle;
            if (this.f1692b != null || this.f1693c != null) {
                m2901k();
                this.f1698h.m3041a(this.f1692b);
                m2900j();
            }
        } catch (Throwable th) {
            ce.m3829a(th, "MyLocationOverlay", "setMyLocationStyle");
            th.printStackTrace();
        }
    }

    public void m2908b() {
        m2901k();
        if (this.f1698h != null) {
            this.f1698h.m3042b();
            this.f1698h = null;
        }
    }

    public String m2909c() {
        return this.f1692b != null ? this.f1692b.getId() : null;
    }

    public String m2910d() {
        return this.f1693c != null ? this.f1693c.m2574c() : null;
    }

    public void m2911e() {
        this.f1693c = null;
        this.f1692b = null;
    }
}
