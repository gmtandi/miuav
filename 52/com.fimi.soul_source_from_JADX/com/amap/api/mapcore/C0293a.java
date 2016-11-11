package com.amap.api.mapcore;

import android.content.Context;
import android.graphics.Point;
import android.os.Message;
import com.amap.api.mapcore.C0325p.C0324a;
import com.amap.api.mapcore.util.C0381f;
import com.amap.api.mapcore.util.bj;
import com.amap.api.mapcore.util.ce;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.GroundOverlayOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.WeightedLatLng;
import com.autonavi.amap.mapcore.BaseMapCallImplement;
import com.autonavi.amap.mapcore.Convert;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapCore;
import com.autonavi.amap.mapcore.MapProjection;
import com.autonavi.amap.mapcore.MapSourceGridData;
import com.fimi.soul.view.photodraweeview.C2020f;
import java.util.ArrayList;
import javax.microedition.khronos.opengles.GL10;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.amap.api.mapcore.a */
class C0293a extends BaseMapCallImplement {
    IPoint f1550a;
    float f1551b;
    float f1552c;
    float f1553d;
    IPoint f1554e;
    private AMapDelegateImp f1555f;
    private float f1556g;
    private int f1557h;
    private int f1558i;

    public C0293a(AMapDelegateImp aMapDelegateImp) {
        this.f1556g = GroundOverlayOptions.NO_DIMENSION;
        this.f1550a = new IPoint();
        this.f1554e = new IPoint();
        this.f1555f = aMapDelegateImp;
    }

    private float m2552a(LatLng latLng, LatLng latLng2, int i, int i2) {
        float a;
        MapProjection c = this.f1555f.m2508c();
        c.setMapAngle(0.0f);
        c.setCameraHeaderAngle(0.0f);
        c.recalculate();
        IPoint iPoint = new IPoint();
        IPoint iPoint2 = new IPoint();
        this.f1555f.m2499b(latLng.latitude, latLng.longitude, iPoint);
        this.f1555f.m2499b(latLng2.latitude, latLng2.longitude, iPoint2);
        double d = (double) (iPoint.f3714x - iPoint2.f3714x);
        double d2 = (double) (iPoint2.f3715y - iPoint.f3715y);
        if (d <= 0.0d) {
            d = WeightedLatLng.DEFAULT_INTENSITY;
        }
        if (d2 <= 0.0d) {
            d2 = WeightedLatLng.DEFAULT_INTENSITY;
        }
        d = Math.log(((double) i) / d) / Math.log(2.0d);
        double min = Math.min(d, Math.log(((double) i2) / d2) / Math.log(2.0d));
        Object obj = Math.abs(min - d) < 1.0E-7d ? 1 : null;
        float a2 = bj.m3602a((float) (((double) c.getMapZoomer()) + Math.floor(min)));
        while (true) {
            a = bj.m3602a((float) (((double) a2) + 0.1d));
            c.setMapZoomer(a);
            c.recalculate();
            this.f1555f.m2499b(latLng.latitude, latLng.longitude, iPoint);
            this.f1555f.m2499b(latLng2.latitude, latLng2.longitude, iPoint2);
            d = (double) (iPoint.f3714x - iPoint2.f3714x);
            min = (double) (iPoint2.f3715y - iPoint.f3715y);
            if (obj != null) {
                if (d >= ((double) i)) {
                    break;
                }
                if (a < C0330s.f2073f) {
                    return a;
                }
                a2 = a;
            } else {
                if (min >= ((double) i2)) {
                    break;
                }
                if (a < C0330s.f2073f) {
                    return a;
                }
                a2 = a;
            }
        }
        return (float) (((double) a) - 0.1d);
    }

    private IPoint m2553a(MapProjection mapProjection) {
        return m2554a(mapProjection, this.f1557h, this.f1558i);
    }

    private IPoint m2554a(MapProjection mapProjection, int i, int i2) {
        FPoint fPoint = new FPoint();
        mapProjection.win2Map(i, i2, fPoint);
        IPoint iPoint = new IPoint();
        mapProjection.map2Geo(fPoint.f3693x, fPoint.f3694y, iPoint);
        return iPoint;
    }

    private void m2555a(MapProjection mapProjection, float f) {
        m2556a(mapProjection, f, this.f1557h, this.f1558i);
    }

    private void m2556a(MapProjection mapProjection, float f, int i, int i2) {
        IPoint a = m2554a(mapProjection, i, i2);
        mapProjection.setMapZoomer(f);
        m2560a(mapProjection, a, i, i2);
    }

    private void m2557a(MapProjection mapProjection, C0325p c0325p) {
        mapProjection.setMapAngle(c0325p.f2032g);
        m2558a(mapProjection, c0325p.f2040o);
    }

    private void m2558a(MapProjection mapProjection, IPoint iPoint) {
        m2560a(mapProjection, iPoint, this.f1557h, this.f1558i);
    }

    private void m2559a(MapProjection mapProjection, IPoint iPoint, float f, float f2, float f3) {
        mapProjection.setMapZoomer(f);
        mapProjection.setMapAngle(f2);
        mapProjection.setCameraHeaderAngle(f3);
        m2558a(mapProjection, iPoint);
    }

    private void m2560a(MapProjection mapProjection, IPoint iPoint, int i, int i2) {
        mapProjection.recalculate();
        IPoint a = m2554a(mapProjection, i, i2);
        IPoint iPoint2 = new IPoint();
        mapProjection.getGeoCenter(iPoint2);
        mapProjection.setGeoCenter((iPoint2.f3714x + iPoint.f3714x) - a.f3714x, (iPoint2.f3715y + iPoint.f3715y) - a.f3715y);
    }

    private void m2561b(C0325p c0325p) {
        MapCore a = this.f1555f.m2450a();
        MapProjection c = this.f1555f.m2508c();
        LatLngBounds latLngBounds = c0325p.f2034i;
        int i = c0325p.f2036k;
        int i2 = c0325p.f2037l;
        int i3 = c0325p.f2035j;
        IPoint iPoint = new IPoint();
        IPoint iPoint2 = new IPoint();
        MapProjection.lonlat2Geo(latLngBounds.northeast.longitude, latLngBounds.northeast.latitude, iPoint);
        MapProjection.lonlat2Geo(latLngBounds.southwest.longitude, latLngBounds.southwest.latitude, iPoint2);
        int i4 = iPoint2.f3715y - iPoint.f3715y;
        int i5 = i - (i3 * 2);
        i = i2 - (i3 * 2);
        if (iPoint.f3714x - iPoint2.f3714x >= 0 || i4 >= 0) {
            if (i5 <= 0) {
                i5 = 1;
            }
            if (i <= 0) {
                i = 1;
            }
            float a2 = m2552a(latLngBounds.northeast, latLngBounds.southwest, i5, i);
            i5 = (iPoint.f3714x + iPoint2.f3714x) / 2;
            int i6 = (iPoint.f3715y + iPoint2.f3715y) / 2;
            c.setMapZoomer(a2);
            c.setGeoCenter(i5, i6);
            c.setCameraHeaderAngle(0.0f);
            c.setMapAngle(0.0f);
            a.setMapstate(c);
        }
    }

    private void m2562b(MapProjection mapProjection, C0325p c0325p) {
        mapProjection.setMapZoomer(c0325p.f2029d);
        m2558a(mapProjection, c0325p.f2040o);
    }

    private void m2563c(MapProjection mapProjection, C0325p c0325p) {
        IPoint a = m2553a(mapProjection);
        mapProjection.setCameraHeaderAngle(c0325p.f2031f);
        m2558a(mapProjection, a);
    }

    private void m2564d(MapProjection mapProjection, C0325p c0325p) {
        IPoint a = m2553a(mapProjection);
        mapProjection.setMapAngle(c0325p.f2032g);
        m2558a(mapProjection, a);
    }

    public void OnMapDestory(GL10 gl10, MapCore mapCore) {
        super.OnMapDestory(mapCore);
    }

    public void OnMapLoaderError(int i) {
    }

    public void OnMapProcessEvent(MapCore mapCore) {
        float f = 0.0f;
        if (this.f1555f != null && this.f1555f.m2428O()) {
            this.f1555f.m2429P();
        }
        if (this.f1555f != null) {
            float F = this.f1555f.m2419F();
            m2567a(mapCore);
            while (true) {
                au a = this.f1555f.f1527e.m2796a();
                if (a == null) {
                    break;
                } else if (a.f1626a == 2) {
                    if (a.m2795a()) {
                        mapCore.setParameter(2010, 4, 0, 0, 0);
                    } else {
                        mapCore.setParameter(2010, 0, 0, 0, 0);
                    }
                }
            }
            mapCore.setMapstate(this.f1555f.m2508c());
            if (!(this.f1551b < this.f1555f.m2545t() || this.f1556g == F || this.f1555f.f1534l == null)) {
                this.f1555f.f1534l.obtainMessage(21).sendToTarget();
            }
            f = F;
        }
        this.f1556g = f;
    }

    public void OnMapReferencechanged(MapCore mapCore, String str, String str2) {
        try {
            if (this.f1555f.m2414A().m2731d()) {
                this.f1555f.m2525i();
            }
            if (this.f1555f.m2414A().m2727b()) {
                this.f1555f.m2528j();
            }
            this.f1555f.m2539o(true);
        } catch (Throwable e) {
            ce.m3829a(e, "AMapCallback", "OnMapReferencechanged");
            e.printStackTrace();
        }
        this.f1555f.m2434U();
    }

    public void OnMapSufaceChanged(GL10 gl10, MapCore mapCore, int i, int i2) {
    }

    public void OnMapSurfaceCreate(GL10 gl10, MapCore mapCore) {
        super.OnMapSurfaceCreate(mapCore);
    }

    public void OnMapSurfaceRenderer(GL10 gl10, MapCore mapCore, int i) {
        super.OnMapSurfaceRenderer(gl10, mapCore, i);
        if (i == 3) {
            this.f1555f.f1530h.m4251a(gl10, true, this.f1555f.m2430Q());
            if (this.f1555f.f1535m != null) {
                this.f1555f.f1535m.onDrawFrame(gl10);
            }
        }
    }

    public void m2565a(int i, int i2) {
        this.f1557h = i;
        this.f1558i = i2;
    }

    void m2566a(C0325p c0325p) {
        MapCore a = this.f1555f.m2450a();
        MapProjection c = this.f1555f.m2508c();
        c.recalculate();
        c0325p.f2029d = this.f1555f.m2497b(c0325p.f2029d);
        float b;
        switch (C0303b.f1648a[c0325p.f2026a.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                if (c0325p.f2039n) {
                    m2558a(c, c0325p.f2040o);
                } else {
                    c.setGeoCenter(c0325p.f2040o.f3714x, c0325p.f2040o.f3715y);
                }
                a.setMapstate(c);
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                if (c0325p.f2039n) {
                    m2564d(c, c0325p);
                } else {
                    c.setMapAngle(c0325p.f2032g);
                }
                a.setMapstate(c);
            case Type.BYTE /*3*/:
                if (c0325p.f2039n) {
                    m2557a(c, c0325p);
                } else {
                    c.setMapAngle(c0325p.f2032g);
                    c.setGeoCenter(c0325p.f2040o.f3714x, c0325p.f2040o.f3715y);
                }
                a.setMapstate(c);
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                c0325p.f2031f = bj.m3603a(c0325p.f2031f, c.getMapZoomer());
                if (c0325p.f2039n) {
                    m2563c(c, c0325p);
                } else {
                    c.setCameraHeaderAngle(c0325p.f2031f);
                }
                a.setMapstate(c);
            case Type.INT /*5*/:
                if (c0325p.f2039n) {
                    m2562b(c, c0325p);
                } else {
                    c.setGeoCenter(c0325p.f2040o.f3714x, c0325p.f2040o.f3715y);
                    c.setMapZoomer(c0325p.f2029d);
                }
                a.setMapstate(c);
            case Type.FLOAT /*6*/:
                LatLng latLng = c0325p.f2033h.target;
                IPoint iPoint = new IPoint();
                MapProjection.lonlat2Geo(latLng.longitude, latLng.latitude, iPoint);
                float a2 = bj.m3602a(c0325p.f2033h.zoom);
                float f = c0325p.f2033h.bearing;
                float a3 = bj.m3603a(c0325p.f2033h.tilt, a2);
                if (c0325p.f2039n) {
                    m2559a(c, iPoint, a2, f, a3);
                } else {
                    c.setGeoCenter(iPoint.f3714x, iPoint.f3715y);
                    c.setMapZoomer(a2);
                    c.setMapAngle(f);
                    c.setCameraHeaderAngle(a3);
                }
                a.setMapstate(c);
            case Type.LONG /*7*/:
                b = this.f1555f.m2497b(c.getMapZoomer() + C2020f.f10933c);
                if (c0325p.f2039n) {
                    m2555a(c, b);
                } else {
                    c.setMapZoomer(b);
                }
                a.setMapstate(c);
            case Type.DOUBLE /*8*/:
                b = this.f1555f.m2497b(c.getMapZoomer() - C2020f.f10933c);
                if (c0325p.f2039n) {
                    m2555a(c, b);
                } else {
                    c.setMapZoomer(b);
                }
                c.setMapZoomer(b);
                a.setMapstate(c);
            case Type.ARRAY /*9*/:
                b = c0325p.f2029d;
                if (c0325p.f2039n) {
                    m2555a(c, b);
                } else {
                    c.setMapZoomer(b);
                }
                a.setMapstate(c);
            case Type.OBJECT /*10*/:
                b = this.f1555f.m2497b(c.getMapZoomer() + c0325p.f2030e);
                Point point = c0325p.f2038m;
                if (point != null) {
                    m2556a(c, b, point.x, point.y);
                } else if (c0325p.f2039n) {
                    m2555a(c, b);
                } else {
                    c.setMapZoomer(b);
                }
                a.setMapstate(c);
            case Opcodes.T_LONG /*11*/:
                b = c0325p.f2027b;
                b += ((float) this.f1555f.m2536n()) / 2.0f;
                float o = c0325p.f2028c + (((float) this.f1555f.m2538o()) / 2.0f);
                IPoint iPoint2 = new IPoint();
                this.f1555f.m2460a((int) b, (int) o, iPoint2);
                c.setGeoCenter(iPoint2.f3714x, iPoint2.f3715y);
                a.setMapstate(c);
            case Opcodes.FCONST_1 /*12*/:
                c0325p.f2026a = C0324a.newLatLngBoundsWithSize;
                c0325p.f2036k = this.f1555f.m2536n();
                c0325p.f2037l = this.f1555f.m2538o();
                m2561b(c0325p);
            case Opcodes.FCONST_2 /*13*/:
                m2561b(c0325p);
            case Opcodes.DCONST_0 /*14*/:
                c0325p.f2031f = bj.m3603a(c0325p.f2031f, c0325p.f2029d);
                if (c0325p.f2039n) {
                    m2559a(c, c0325p.f2040o, c0325p.f2029d, c0325p.f2032g, c0325p.f2031f);
                } else {
                    c.setGeoCenter(c0325p.f2040o.f3714x, c0325p.f2040o.f3715y);
                    c.setMapZoomer(c0325p.f2029d);
                    c.setMapAngle(c0325p.f2032g);
                    c.setCameraHeaderAngle(c0325p.f2031f);
                }
                a.setMapstate(c);
            default:
                a.setMapstate(c);
        }
    }

    void m2567a(MapCore mapCore) {
        Object obj = null;
        MapProjection c = this.f1555f.m2508c();
        float mapZoomer = c.getMapZoomer();
        float cameraHeaderAngle = c.getCameraHeaderAngle();
        float mapAngle = c.getMapAngle();
        c.getGeoCenter(this.f1554e);
        int i = 0;
        while (this.f1555f.ab()) {
            C0325p c2 = this.f1555f.f1527e.m2800c();
            if (c2 == null) {
                break;
            }
            try {
                m2566a(c2);
                i |= c2.f2041p;
            } catch (Throwable e) {
                ce.m3829a(e, "AMapCallback", "runMessage");
                e.printStackTrace();
            }
        }
        this.f1551b = c.getMapZoomer();
        this.f1552c = c.getCameraHeaderAngle();
        this.f1553d = c.getMapAngle();
        c.getGeoCenter(this.f1550a);
        if (!(mapZoomer == this.f1551b && this.f1552c == cameraHeaderAngle && this.f1553d == mapAngle && this.f1550a.f3714x == this.f1554e.f3714x && this.f1550a.f3715y == this.f1554e.f3715y)) {
            obj = 1;
        }
        if (obj != null) {
            try {
                this.f1555f.m2518f(false);
                if (this.f1555f.m2416C() != null) {
                    DPoint dPoint = new DPoint();
                    MapProjection.geo2LonLat(this.f1550a.f3714x, this.f1550a.f3715y, dPoint);
                    this.f1555f.m2486a(new CameraPosition(new LatLng(dPoint.f3692y, dPoint.f3691x, false), this.f1551b, this.f1552c, this.f1553d));
                }
                this.f1555f.m2420G();
            } catch (Throwable e2) {
                ce.m3829a(e2, "AMapCallback", "runMessage cameraChange");
                e2.printStackTrace();
                return;
            }
        }
        this.f1555f.m2518f(true);
        if (i != 0) {
            if (i != 0) {
                this.f1555f.m2539o(true);
            } else {
                this.f1555f.m2539o(false);
            }
            Message message = new Message();
            message.what = 17;
            this.f1555f.f1534l.sendMessage(message);
        }
        if (!(this.f1552c == cameraHeaderAngle && this.f1553d == mapAngle) && this.f1555f.m2414A().m2731d()) {
            this.f1555f.m2525i();
        }
        if (this.f1555f.m2414A().m2727b()) {
            this.f1555f.m2528j();
        }
    }

    public Context getContext() {
        return this.f1555f.m2435V();
    }

    public String getMapSvrAddress() {
        return "http://mps.amap.com";
    }

    public boolean isMapEngineValid() {
        return this.f1555f.m2450a() != null ? this.f1555f.m2450a().isMapEngineValid() : false;
    }

    public void onIndoorBuildingActivity(MapCore mapCore, byte[] bArr) {
        C0381f c0381f = null;
        if (bArr != null) {
            try {
                C0381f c0381f2 = new C0381f();
                byte b = bArr[0];
                c0381f2.f2444a = new String(bArr, 1, b);
                int i = b + 1;
                int i2 = i + 1;
                b = bArr[i];
                c0381f2.f2445b = new String(bArr, i2, b);
                i = b + i2;
                i2 = i + 1;
                b = bArr[i];
                c0381f2.activeFloorName = new String(bArr, i2, b);
                i = b + i2;
                c0381f2.activeFloorIndex = Convert.getInt(bArr, i);
                i += 4;
                i2 = i + 1;
                b = bArr[i];
                c0381f2.poiid = new String(bArr, i2, b);
                i = b + i2;
                c0381f2.f2446c = Convert.getInt(bArr, i);
                i += 4;
                c0381f2.floor_indexs = new int[c0381f2.f2446c];
                c0381f2.floor_names = new String[c0381f2.f2446c];
                c0381f2.f2447d = new String[c0381f2.f2446c];
                for (int i3 = 0; i3 < c0381f2.f2446c; i3++) {
                    c0381f2.floor_indexs[i3] = Convert.getInt(bArr, i);
                    i2 = i + 4;
                    i = i2 + 1;
                    byte b2 = bArr[i2];
                    if (b2 > null) {
                        c0381f2.floor_names[i3] = new String(bArr, i, b2);
                        i2 = i + b2;
                    } else {
                        i2 = i;
                    }
                    i = i2 + 1;
                    b2 = bArr[i2];
                    if (b2 > null) {
                        c0381f2.f2447d[i3] = new String(bArr, i, b2);
                        i += b2;
                    }
                }
                c0381f2.f2448e = Convert.getInt(bArr, i);
                i += 4;
                if (c0381f2.f2448e > 0) {
                    c0381f2.f2449f = new int[c0381f2.f2448e];
                    int i4 = i;
                    for (i = 0; i < c0381f2.f2448e; i++) {
                        c0381f2.f2449f[i] = Convert.getInt(bArr, i4);
                        i4 += 4;
                    }
                }
                c0381f = c0381f2;
            } catch (Throwable th) {
                th.printStackTrace();
                ce.m3829a(th, "AMapCallback", "onIndoorBuildingActivity");
                return;
            }
        }
        this.f1555f.m2466a(c0381f);
    }

    public void onIndoorDataRequired(MapCore mapCore, int i, String[] strArr, int[] iArr, int[] iArr2) {
        if (strArr != null && strArr.length != 0) {
            ArrayList reqGridList = getReqGridList(i);
            if (reqGridList != null) {
                reqGridList.clear();
                for (int i2 = 0; i2 < strArr.length; i2++) {
                    reqGridList.add(new MapSourceGridData(strArr[i2], i, iArr[i2], iArr2[i2]));
                }
                if (i != 5) {
                    proccessRequiredData(mapCore, reqGridList, i);
                }
            }
        }
    }

    public void requestRender() {
        this.f1555f.m2518f(false);
    }
}
