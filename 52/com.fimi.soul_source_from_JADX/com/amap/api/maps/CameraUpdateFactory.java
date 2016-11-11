package com.amap.api.maps;

import android.graphics.Point;
import com.amap.api.mapcore.C0325p;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapProjection;

public final class CameraUpdateFactory {
    public static CameraUpdate changeBearing(float f) {
        return new CameraUpdate(C0325p.m3308d(f % 360.0f));
    }

    public static CameraUpdate changeBearingGeoCenter(float f, IPoint iPoint) {
        return new CameraUpdate(C0325p.m3295a(f % 360.0f, iPoint));
    }

    public static CameraUpdate changeLatLng(LatLng latLng) {
        IPoint iPoint = new IPoint();
        MapProjection.lonlat2Geo(latLng.longitude, latLng.latitude, iPoint);
        return new CameraUpdate(C0325p.m3302a(iPoint));
    }

    public static CameraUpdate changeTilt(float f) {
        return new CameraUpdate(C0325p.m3307c(f));
    }

    public static CameraUpdate newCameraPosition(CameraPosition cameraPosition) {
        return new CameraUpdate(C0325p.m3296a(cameraPosition));
    }

    public static CameraUpdate newLatLng(LatLng latLng) {
        return new CameraUpdate(C0325p.m3297a(latLng));
    }

    public static CameraUpdate newLatLngBounds(LatLngBounds latLngBounds, int i) {
        return new CameraUpdate(C0325p.m3300a(latLngBounds, i));
    }

    public static CameraUpdate newLatLngBounds(LatLngBounds latLngBounds, int i, int i2, int i3) {
        return new CameraUpdate(C0325p.m3301a(latLngBounds, i, i2, i3));
    }

    public static CameraUpdate newLatLngZoom(LatLng latLng, float f) {
        return new CameraUpdate(C0325p.m3298a(latLng, f));
    }

    public static CameraUpdate scrollBy(float f, float f2) {
        return new CameraUpdate(C0325p.m3293a(f, f2));
    }

    public static CameraUpdate zoomBy(float f) {
        return new CameraUpdate(C0325p.m3305b(f));
    }

    public static CameraUpdate zoomBy(float f, Point point) {
        return new CameraUpdate(C0325p.m3294a(f, point));
    }

    public static CameraUpdate zoomIn() {
        return new CameraUpdate(C0325p.m3304b());
    }

    public static CameraUpdate zoomOut() {
        return new CameraUpdate(C0325p.m3306c());
    }

    public static CameraUpdate zoomTo(float f) {
        return new CameraUpdate(C0325p.m3292a(f));
    }
}
