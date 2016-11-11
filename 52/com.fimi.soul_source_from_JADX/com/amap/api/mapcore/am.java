package com.amap.api.mapcore;

import android.graphics.Point;
import android.graphics.PointF;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.TileProjection;
import com.amap.api.maps.model.VisibleRegion;

public interface am {
    float m2688a(int i);

    Point m2689a(LatLng latLng);

    LatLng m2690a(Point point);

    LatLngBounds m2691a(LatLng latLng, float f);

    TileProjection m2692a(LatLngBounds latLngBounds, int i, int i2);

    VisibleRegion m2693a();

    PointF m2694b(LatLng latLng);
}
