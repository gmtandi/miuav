package com.fimi.soul.utils;

import android.graphics.Point;
import com.amap.api.maps.AMap;
import com.amap.api.maps.Projection;
import com.amap.api.maps.SupportMapFragment;
import com.amap.api.maps.model.LatLng;
import java.util.ArrayList;
import java.util.List;

public class MapProjection extends SupportMapFragment {
    public static List<LatLng> m12196a(List<Point> list, AMap aMap) {
        List<LatLng> arrayList = new ArrayList();
        Projection projection = aMap.getProjection();
        for (Point fromScreenLocation : list) {
            arrayList.add(projection.fromScreenLocation(fromScreenLocation));
        }
        return arrayList;
    }
}
