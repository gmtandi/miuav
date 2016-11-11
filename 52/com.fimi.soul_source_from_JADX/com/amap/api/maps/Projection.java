package com.amap.api.maps;

import android.graphics.Point;
import android.graphics.PointF;
import android.os.RemoteException;
import com.amap.api.mapcore.am;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.RuntimeRemoteException;
import com.amap.api.maps.model.TileProjection;
import com.amap.api.maps.model.VisibleRegion;

public class Projection {
    private final am f2631a;

    Projection(am amVar) {
        this.f2631a = amVar;
    }

    public TileProjection fromBoundsToTile(LatLngBounds latLngBounds, int i, int i2) {
        try {
            return this.f2631a.m2692a(latLngBounds, i, i2);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public LatLng fromScreenLocation(Point point) {
        try {
            return this.f2631a.m2690a(point);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public LatLngBounds getMapBounds(LatLng latLng, float f) {
        try {
            return this.f2631a.m2691a(latLng, f);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public VisibleRegion getVisibleRegion() {
        try {
            return this.f2631a.m2693a();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    @Deprecated
    public PointF toMapLocation(LatLng latLng) {
        try {
            return this.f2631a.m2694b(latLng);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public PointF toOpenGLLocation(LatLng latLng) {
        try {
            return this.f2631a.m2694b(latLng);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public float toOpenGLWidth(int i) {
        try {
            return this.f2631a.m2688a(i);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public Point toScreenLocation(LatLng latLng) {
        try {
            return this.f2631a.m2689a(latLng);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
