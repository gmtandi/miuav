package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.maps.model.LatLng;
import com.autonavi.amap.mapcore.CoordUtil;
import com.autonavi.amap.mapcore.DPoint;
import java.io.File;
import java.math.BigDecimal;

/* renamed from: com.amap.api.mapcore.util.a */
public class C0334a {
    static double f2078a;
    private static boolean f2079b;

    static {
        f2079b = false;
        f2078a = 3.141592653589793d;
    }

    private static double m3345a(double d) {
        return Math.sin((3000.0d * d) * (f2078a / 180.0d)) * 2.0E-5d;
    }

    public static double m3346a(double d, double d2) {
        return (Math.cos(d2 / 100000.0d) * (d / 18000.0d)) + (Math.sin(d / 100000.0d) * (d2 / 9000.0d));
    }

    private static double m3347a(double d, int i) {
        return new BigDecimal(d).setScale(i, 4).doubleValue();
    }

    public static LatLng m3348a(Context context, LatLng latLng) {
        if (context == null) {
            return null;
        }
        String a = bu.m3743a(context, "libwgs2gcj.so");
        if (!(TextUtils.isEmpty(a) || !new File(a).exists() || f2079b)) {
            try {
                System.load(a);
                f2079b = true;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        DPoint a2 = C0334a.m3352a(new DPoint(latLng.longitude, latLng.latitude), f2079b);
        return new LatLng(a2.f3692y, a2.f3691x, false);
    }

    public static LatLng m3349a(LatLng latLng) {
        if (latLng != null) {
            try {
                DPoint a = C0334a.m3351a(new DPoint(latLng.longitude, latLng.latitude), 2);
                return new LatLng(a.f3692y, a.f3691x, false);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return latLng;
    }

    private static DPoint m3350a(double d, double d2, double d3, double d4) {
        DPoint dPoint = new DPoint();
        double d5 = d - d3;
        double d6 = d2 - d4;
        DPoint d7 = C0334a.m3357d(d5, d6);
        dPoint.f3691x = C0334a.m3347a((d5 + d) - d7.f3691x, 8);
        dPoint.f3692y = C0334a.m3347a((d2 + d6) - d7.f3692y, 8);
        return dPoint;
    }

    private static DPoint m3351a(DPoint dPoint, int i) {
        double d = 0.006401062d;
        double d2 = 0.0060424805d;
        int i2 = 0;
        DPoint dPoint2 = null;
        while (i2 < i) {
            DPoint a = C0334a.m3350a(dPoint.f3691x, dPoint.f3692y, d, d2);
            d = dPoint.f3691x - a.f3691x;
            d2 = dPoint.f3692y - a.f3692y;
            i2++;
            dPoint2 = a;
        }
        return dPoint2;
    }

    private static DPoint m3352a(DPoint dPoint, boolean z) {
        double[] dArr;
        double[] dArr2;
        try {
            dArr2 = new double[2];
            if (z) {
                if (CoordUtil.convertToGcj(new double[]{dPoint.f3691x, dPoint.f3692y}, dArr2) != 0) {
                    dArr2 = dq.m4051a(dPoint.f3691x, dPoint.f3692y);
                }
                dArr = dArr2;
            } else {
                dArr = dq.m4051a(dPoint.f3691x, dPoint.f3692y);
            }
        } catch (Throwable th) {
            return dPoint;
        }
        return new DPoint(dArr[0], dArr[1]);
    }

    private static double m3353b(double d) {
        return Math.cos((3000.0d * d) * (f2078a / 180.0d)) * 3.0E-6d;
    }

    public static double m3354b(double d, double d2) {
        return (Math.sin(d2 / 100000.0d) * (d / 18000.0d)) + (Math.cos(d / 100000.0d) * (d2 / 9000.0d));
    }

    public static LatLng m3355b(Context context, LatLng latLng) {
        try {
            DPoint c = C0334a.m3356c(latLng.longitude, latLng.latitude);
            latLng = C0334a.m3348a(context, new LatLng(c.f3692y, c.f3691x, false));
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return latLng;
    }

    private static DPoint m3356c(double d, double d2) {
        double d3 = (double) (((long) (100000.0d * d)) % 36000000);
        double d4 = (double) (((long) (100000.0d * d2)) % 36000000);
        int i = (int) ((-C0334a.m3354b(d3, d4)) + d4);
        int i2 = (int) (((double) (d3 > 0.0d ? 1 : -1)) + ((-C0334a.m3346a((double) ((int) ((-C0334a.m3346a(d3, d4)) + d3)), (double) i)) + d3));
        return new DPoint(((double) i2) / 100000.0d, ((double) ((int) (((double) (d4 > 0.0d ? 1 : -1)) + ((-C0334a.m3354b((double) i2, (double) i)) + d4)))) / 100000.0d);
    }

    private static DPoint m3357d(double d, double d2) {
        DPoint dPoint = new DPoint();
        double sin = (Math.sin(C0334a.m3353b(d) + Math.atan2(d2, d)) * (C0334a.m3345a(d2) + Math.sqrt((d * d) + (d2 * d2)))) + 0.006d;
        dPoint.f3691x = C0334a.m3347a((Math.cos(C0334a.m3353b(d) + Math.atan2(d2, d)) * (C0334a.m3345a(d2) + Math.sqrt((d * d) + (d2 * d2)))) + 0.0065d, 8);
        dPoint.f3692y = C0334a.m3347a(sin, 8);
        return dPoint;
    }
}
