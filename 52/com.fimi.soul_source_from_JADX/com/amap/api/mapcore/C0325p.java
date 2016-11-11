package com.amap.api.mapcore;

import android.graphics.Point;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.autonavi.amap.mapcore.IPoint;

/* renamed from: com.amap.api.mapcore.p */
public class C0325p {
    C0324a f2026a;
    float f2027b;
    float f2028c;
    float f2029d;
    float f2030e;
    float f2031f;
    float f2032g;
    CameraPosition f2033h;
    LatLngBounds f2034i;
    int f2035j;
    int f2036k;
    int f2037l;
    Point f2038m;
    boolean f2039n;
    IPoint f2040o;
    boolean f2041p;

    /* renamed from: com.amap.api.mapcore.p.a */
    enum C0324a {
        none,
        zoomIn,
        changeCenter,
        changeTilt,
        changeBearing,
        changeBearingGeoCenter,
        changeGeoCenterZoom,
        zoomOut,
        zoomTo,
        zoomBy,
        scrollBy,
        newCameraPosition,
        newLatLngBounds,
        newLatLngBoundsWithSize,
        changeGeoCenterZoomTiltBearing
    }

    private C0325p() {
        this.f2026a = C0324a.none;
        this.f2038m = null;
        this.f2039n = false;
        this.f2041p = false;
    }

    public static C0325p m3291a() {
        return new C0325p();
    }

    public static C0325p m3292a(float f) {
        C0325p a = C0325p.m3291a();
        a.f2026a = C0324a.zoomTo;
        a.f2029d = f;
        return a;
    }

    public static C0325p m3293a(float f, float f2) {
        C0325p a = C0325p.m3291a();
        a.f2026a = C0324a.scrollBy;
        a.f2027b = f;
        a.f2028c = f2;
        return a;
    }

    public static C0325p m3294a(float f, Point point) {
        C0325p a = C0325p.m3291a();
        a.f2026a = C0324a.zoomBy;
        a.f2030e = f;
        a.f2038m = point;
        return a;
    }

    public static C0325p m3295a(float f, IPoint iPoint) {
        C0325p a = C0325p.m3291a();
        a.f2026a = C0324a.changeBearingGeoCenter;
        a.f2032g = f;
        a.f2040o = iPoint;
        return a;
    }

    public static C0325p m3296a(CameraPosition cameraPosition) {
        C0325p a = C0325p.m3291a();
        a.f2026a = C0324a.newCameraPosition;
        a.f2033h = cameraPosition;
        return a;
    }

    public static C0325p m3297a(LatLng latLng) {
        return C0325p.m3296a(CameraPosition.builder().target(latLng).build());
    }

    public static C0325p m3298a(LatLng latLng, float f) {
        return C0325p.m3296a(CameraPosition.builder().target(latLng).zoom(f).build());
    }

    public static C0325p m3299a(LatLng latLng, float f, float f2, float f3) {
        return C0325p.m3296a(CameraPosition.builder().target(latLng).zoom(f).bearing(f2).tilt(f3).build());
    }

    public static C0325p m3300a(LatLngBounds latLngBounds, int i) {
        C0325p a = C0325p.m3291a();
        a.f2026a = C0324a.newLatLngBounds;
        a.f2034i = latLngBounds;
        a.f2035j = i;
        return a;
    }

    public static C0325p m3301a(LatLngBounds latLngBounds, int i, int i2, int i3) {
        C0325p a = C0325p.m3291a();
        a.f2026a = C0324a.newLatLngBoundsWithSize;
        a.f2034i = latLngBounds;
        a.f2035j = i3;
        a.f2036k = i;
        a.f2037l = i2;
        return a;
    }

    public static C0325p m3302a(IPoint iPoint) {
        C0325p a = C0325p.m3291a();
        a.f2026a = C0324a.changeCenter;
        a.f2040o = iPoint;
        return a;
    }

    static C0325p m3303a(IPoint iPoint, float f, float f2, float f3) {
        C0325p a = C0325p.m3291a();
        a.f2026a = C0324a.changeGeoCenterZoomTiltBearing;
        a.f2040o = iPoint;
        a.f2029d = f;
        a.f2032g = f2;
        a.f2031f = f3;
        return a;
    }

    public static C0325p m3304b() {
        C0325p a = C0325p.m3291a();
        a.f2026a = C0324a.zoomIn;
        return a;
    }

    public static C0325p m3305b(float f) {
        return C0325p.m3294a(f, null);
    }

    public static C0325p m3306c() {
        C0325p a = C0325p.m3291a();
        a.f2026a = C0324a.zoomOut;
        return a;
    }

    public static C0325p m3307c(float f) {
        C0325p a = C0325p.m3291a();
        a.f2026a = C0324a.changeTilt;
        a.f2031f = f;
        return a;
    }

    public static C0325p m3308d(float f) {
        C0325p a = C0325p.m3291a();
        a.f2026a = C0324a.changeBearing;
        a.f2032g = f;
        return a;
    }
}
