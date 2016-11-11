package com.fimi.soul.utils;

import android.graphics.Point;
import com.amap.api.maps.model.WeightedLatLng;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class az {
    public static double m12302a(Point point, Point point2, Point point3) {
        double d = (double) point.x;
        double d2 = (double) point.y;
        double d3 = (double) point2.x;
        double d4 = (double) point2.y;
        double d5 = (double) point3.x;
        double d6 = (double) point3.y;
        double d7 = d3 - d;
        double d8 = d4 - d2;
        if (!(d7 == 0.0d && d8 == 0.0d)) {
            double d9 = (((d5 - d) * d7) + ((d6 - d2) * d8)) / ((d7 * d7) + (d8 * d8));
            if (d9 > WeightedLatLng.DEFAULT_INTENSITY) {
                d2 = d4;
                d4 = d3;
            } else if (d9 > 0.0d) {
                d4 = (d7 * d9) + d;
                d2 += d8 * d9;
            }
            d4 = d5 - d4;
            d2 = d6 - d2;
            return (d2 * d2) + (d4 * d4);
        }
        d4 = d;
        d4 = d5 - d4;
        d2 = d6 - d2;
        return (d2 * d2) + (d4 * d4);
    }

    public static List<Point> m12303a(List<Point> list, double d) {
        double d2 = d * d;
        int size = list.size() - 1;
        double d3 = 0.0d;
        int i = 0;
        int i2 = 1;
        while (i2 < list.size() - 1) {
            int i3;
            double a = m12302a((Point) list.get(0), (Point) list.get(size), (Point) list.get(i2));
            if (a > d3) {
                d3 = a;
                i3 = i2;
            } else {
                i3 = i;
            }
            i2++;
            i = i3;
        }
        List<Point> arrayList = new ArrayList();
        if (d3 > d2) {
            Collection a2 = m12303a(list.subList(0, i + 1), d);
            Collection a3 = m12303a(list.subList(i, size + 1), d);
            a2.remove(a2.size() - 1);
            arrayList.addAll(a2);
            arrayList.addAll(a3);
        } else {
            arrayList.add(list.get(0));
            arrayList.add(list.get(size));
        }
        return arrayList;
    }
}
