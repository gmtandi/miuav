package com.fimi.soul.biz.p090b;

import android.content.Context;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;
import java.util.List;

/* renamed from: com.fimi.soul.biz.b.l */
public class C1254l {
    public static MarkerOptions m8602a(LatLng latLng, int i) {
        return new MarkerOptions().position(latLng).icon(C1255m.m8605a(i)).draggable(true);
    }

    public static MarkerOptions m8603a(LatLng latLng, Context context, int i, boolean z, int i2) {
        return new MarkerOptions().position(latLng).icon(C1255m.m8606a(context, i2, i, z)).draggable(true);
    }

    public static PolylineOptions m8604a(List<LatLng> list, int i, int i2) {
        return new PolylineOptions().addAll(list).color(i2).width((float) i);
    }
}
