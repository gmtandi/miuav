package com.fimi.soul.utils;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ac {
    private static final double f10012b = 6372797.560856d;
    public List<LatLng> f10013a;

    public static LatLng m12217a(LatLng latLng, double d, double d2) {
        double d3 = latLng.latitude;
        double d4 = latLng.longitude;
        d3 = Math.toRadians(d3);
        d4 = Math.toRadians(d4);
        double toRadians = Math.toRadians(d);
        double d5 = d2 / f10012b;
        double asin = Math.asin((Math.sin(d3) * Math.cos(d5)) + ((Math.cos(d3) * Math.sin(d5)) * Math.cos(toRadians)));
        return new LatLng(Math.toDegrees(asin), Math.toDegrees(Math.atan2((Math.sin(toRadians) * Math.sin(d5)) * Math.cos(d3), Math.cos(d5) - (Math.sin(d3) * Math.sin(asin))) + d4));
    }

    public static Double m12218a(double d) {
        return Double.valueOf(Math.toDegrees(d / 6378100.0d));
    }

    public static Double m12219a(LatLng latLng, LatLng latLng2) {
        return Double.valueOf(Math.hypot(latLng.latitude - latLng2.latitude, latLng.longitude - latLng2.longitude));
    }

    public static List<Polyline> m12220a(AMap aMap, ArrayList<LatLng> arrayList, int i) {
        List<Polyline> arrayList2 = new ArrayList();
        int i2 = 0;
        Object obj = null;
        while (i2 < arrayList.size() - 1) {
            Object obj2;
            double e = m12225e((LatLng) arrayList.get(i2), (LatLng) arrayList.get(i2 + 1));
            if (e >= 0.02d) {
                int i3 = (int) (e / 0.02d);
                double d = (((LatLng) arrayList.get(i2 + 1)).latitude - ((LatLng) arrayList.get(i2)).latitude) / ((double) i3);
                double d2 = (((LatLng) arrayList.get(i2 + 1)).longitude - ((LatLng) arrayList.get(i2)).longitude) / ((double) i3);
                obj2 = obj;
                LatLng latLng = new LatLng(((LatLng) arrayList.get(i2)).latitude, ((LatLng) arrayList.get(i2)).longitude);
                int i4 = 0;
                while (i4 < i3) {
                    LatLng latLng2 = new LatLng(latLng.latitude + d, latLng.longitude + d2);
                    if (obj2 == null) {
                        arrayList2.add(aMap.addPolyline(new PolylineOptions().add(latLng).add(latLng2).color(i)));
                        obj2 = 1;
                    } else {
                        obj2 = null;
                    }
                    i4++;
                    latLng = latLng2;
                }
            } else if (obj == null) {
                arrayList2.add(aMap.addPolyline(new PolylineOptions().add((LatLng) arrayList.get(i2)).add((LatLng) arrayList.get(i2 + 1)).color(i)));
                obj2 = 1;
            } else {
                obj2 = null;
            }
            i2++;
            obj = obj2;
        }
        return arrayList2;
    }

    public static double m12221b(LatLng latLng, LatLng latLng2) {
        double toRadians = Math.toRadians(latLng.latitude - latLng2.latitude);
        double toRadians2 = Math.toRadians(latLng.longitude - latLng2.longitude);
        toRadians = Math.sin(toRadians * 0.5d);
        toRadians *= toRadians;
        toRadians2 = Math.sin(toRadians2 * 0.5d);
        return Math.toDegrees(Math.asin(Math.sqrt(toRadians + ((toRadians2 * toRadians2) * (Math.cos(Math.toRadians(latLng.latitude)) * Math.cos(Math.toRadians(latLng2.latitude)))))) * 2.0d);
    }

    public static Double m12222b(double d) {
        return Double.valueOf(6378100.0d * Math.toRadians(d));
    }

    public static ak m12223c(LatLng latLng, LatLng latLng2) {
        return new ak(f10012b * Math.toRadians(m12221b(latLng, latLng2)));
    }

    public static double m12224d(LatLng latLng, LatLng latLng2) {
        double toRadians = Math.toRadians(latLng.latitude);
        double toRadians2 = Math.toRadians(latLng.longitude);
        double toRadians3 = Math.toRadians(latLng2.latitude);
        double toRadians4 = Math.toRadians(latLng2.longitude);
        toRadians = Math.toDegrees(Math.atan2(Math.sin(toRadians4 - toRadians2) * Math.cos(toRadians3), (Math.cos(toRadians) * Math.sin(toRadians3)) - ((Math.sin(toRadians) * Math.cos(toRadians3)) * Math.cos(toRadians4 - toRadians2))));
        return toRadians >= 0.0d ? toRadians : toRadians + 360.0d;
    }

    private static double m12225e(LatLng latLng, LatLng latLng2) {
        return new BigDecimal(m12223c(latLng, latLng2).m12254a()).setScale(3, RoundingMode.DOWN).doubleValue();
    }
}
