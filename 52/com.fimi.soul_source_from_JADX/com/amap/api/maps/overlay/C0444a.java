package com.amap.api.maps.overlay;

import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.amap.api.maps.overlay.a */
class C0444a {
    public static int f2867a;

    static {
        f2867a = Opcodes.ACC_STRICT;
    }

    C0444a() {
    }

    public static LatLng m4420a(LatLonPoint latLonPoint) {
        return new LatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude());
    }

    public static ArrayList<LatLng> m4421a(List<LatLonPoint> list) {
        ArrayList<LatLng> arrayList = new ArrayList();
        for (LatLonPoint a : list) {
            arrayList.add(C0444a.m4420a(a));
        }
        return arrayList;
    }
}
