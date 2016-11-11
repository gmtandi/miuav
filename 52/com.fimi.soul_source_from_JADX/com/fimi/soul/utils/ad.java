package com.fimi.soul.utils;

import com.amap.api.maps.model.WeightedLatLng;

public class ad {
    static final double f10014a = 3.141592653589793d;
    static final double f10015b = 6378245.0d;
    static final double f10016c = 0.006693421622965943d;

    static double m12226a(double d, double d2, double d3, double d4) {
        double cos = (double) (((float) (d - d3)) * ((float) Math.cos((double) ((float) (Math.abs(d2) * 0.0174532925d)))));
        double d5 = (double) ((float) (d2 - d4));
        return Math.sqrt((((d5 * 1.0E7d) * (1.0E7d * d5)) + ((cos * 1.0E7d) * (1.0E7d * cos))) * 1.2392029762268066d);
    }

    public static aj m12227a(double d, double d2) {
        aj ajVar = new aj();
        double d3 = m12232d(d2 - 105.0d, d - 35.0d);
        double e = m12233e(d2 - 105.0d, d - 35.0d);
        double d4 = (d / 180.0d) * f10014a;
        double sin = Math.sin(d4);
        sin = WeightedLatLng.DEFAULT_INTENSITY - (sin * (f10016c * sin));
        double sqrt = Math.sqrt(sin);
        d3 = (d3 * 180.0d) / ((6335552.717000426d / (sin * sqrt)) * f10014a);
        e = ((e * 180.0d) / ((Math.cos(d4) * (f10015b / sqrt)) * f10014a)) + d2;
        ajVar.m12251a(d3 + d);
        ajVar.m12253b(e);
        return ajVar;
    }

    public static aj m12228a(double d, double d2, double d3) {
        aj ajVar = new aj();
        ajVar = new aj();
        aj ajVar2 = new aj();
        ajVar.m12251a(d2);
        ajVar.m12253b(d3);
        aj b = m12230b(ajVar.m12250a(), ajVar.m12252b());
        ajVar = m12227a(b.m12250a(), b.m12252b());
        while (true) {
            double b2 = d3 - ajVar.m12252b();
            double a = d2 - ajVar.m12250a();
            ajVar2.m12253b(b2 + b.m12252b());
            ajVar2.m12251a(a + b.m12250a());
            b.m12253b(ajVar2.m12252b());
            b.m12251a(ajVar2.m12250a());
            aj a2 = m12227a(b.m12250a(), b.m12252b());
            if (d >= m12226a(d3, d2, a2.m12252b(), a2.m12250a())) {
                return ajVar2;
            }
            ajVar = a2;
        }
    }

    public static void m12229a(double d, double d2, double[] dArr) {
        if (m12231c(d, d2)) {
            dArr[0] = d;
            dArr[1] = d2;
            return;
        }
        double d3 = m12232d(d2 - 105.0d, d - 35.0d);
        double e = m12233e(d2 - 105.0d, d - 35.0d);
        double d4 = (d / 180.0d) * f10014a;
        double sin = Math.sin(d4);
        sin = WeightedLatLng.DEFAULT_INTENSITY - (sin * (f10016c * sin));
        double sqrt = Math.sqrt(sin);
        d3 = (d3 * 180.0d) / ((6335552.717000426d / (sin * sqrt)) * f10014a);
        e = (e * 180.0d) / ((Math.cos(d4) * (f10015b / sqrt)) * f10014a);
        dArr[0] = d3 + d;
        dArr[1] = e + d2;
    }

    public static aj m12230b(double d, double d2) {
        aj ajVar = new aj();
        double d3 = m12232d(d2 - 105.0d, d - 35.0d);
        double e = m12233e(d2 - 105.0d, d - 35.0d);
        double d4 = (d / 180.0d) * f10014a;
        double sin = Math.sin(d4);
        sin = WeightedLatLng.DEFAULT_INTENSITY - (sin * (f10016c * sin));
        double sqrt = Math.sqrt(sin);
        d3 = (d3 * 180.0d) / ((6335552.717000426d / (sin * sqrt)) * f10014a);
        e = d2 - ((e * 180.0d) / ((Math.cos(d4) * (f10015b / sqrt)) * f10014a));
        ajVar.m12251a(d - d3);
        ajVar.m12253b(e);
        return ajVar;
    }

    private static boolean m12231c(double d, double d2) {
        return d2 < 72.004d || d2 > 137.8347d || d < 0.8293d || d > 55.8271d;
    }

    private static double m12232d(double d, double d2) {
        return (((((((-100.0d + (2.0d * d)) + (3.0d * d2)) + ((0.2d * d2) * d2)) + ((0.1d * d) * d2)) + (0.2d * Math.sqrt(Math.abs(d)))) + ((((20.0d * Math.sin((6.0d * d) * f10014a)) + (20.0d * Math.sin((2.0d * d) * f10014a))) * 2.0d) / 3.0d)) + ((((20.0d * Math.sin(f10014a * d2)) + (40.0d * Math.sin((d2 / 3.0d) * f10014a))) * 2.0d) / 3.0d)) + ((((160.0d * Math.sin((d2 / 12.0d) * f10014a)) + (320.0d * Math.sin((f10014a * d2) / 30.0d))) * 2.0d) / 3.0d);
    }

    private static double m12233e(double d, double d2) {
        return (((((((300.0d + d) + (2.0d * d2)) + ((0.1d * d) * d)) + ((0.1d * d) * d2)) + (0.1d * Math.sqrt(Math.abs(d)))) + ((((20.0d * Math.sin((6.0d * d) * f10014a)) + (20.0d * Math.sin((2.0d * d) * f10014a))) * 2.0d) / 3.0d)) + ((((20.0d * Math.sin(f10014a * d)) + (40.0d * Math.sin((d / 3.0d) * f10014a))) * 2.0d) / 3.0d)) + ((((150.0d * Math.sin((d / 12.0d) * f10014a)) + (300.0d * Math.sin((d / 30.0d) * f10014a))) * 2.0d) / 3.0d);
    }
}
