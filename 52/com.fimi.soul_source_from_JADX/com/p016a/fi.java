package com.p016a;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.location.CoordUtil;
import com.amap.api.location.DPoint;
import java.io.File;
import java.math.BigDecimal;

/* renamed from: com.a.fi */
public class fi {
    static double f1207a;
    private static boolean f1208b;

    static {
        f1208b = false;
        f1207a = 3.141592653589793d;
    }

    private static double m1815a(double d) {
        return Math.sin((3000.0d * d) * (f1207a / 180.0d)) * 2.0E-5d;
    }

    public static double m1816a(double d, double d2) {
        return (Math.cos(d2 / 100000.0d) * (d / 18000.0d)) + (Math.sin(d / 100000.0d) * (d2 / 9000.0d));
    }

    private static double m1817a(double d, int i) {
        return new BigDecimal(d).setScale(i, 4).doubleValue();
    }

    private static DPoint m1818a(double d, double d2, double d3, double d4) {
        DPoint dPoint = new DPoint();
        double d5 = d - d3;
        double d6 = d2 - d4;
        DPoint d7 = fi.m1828d(d5, d6);
        dPoint.setLongitude(fi.m1817a((d5 + d) - d7.getLongitude(), 8));
        dPoint.setLatitude(fi.m1817a((d2 + d6) - d7.getLatitude(), 8));
        return dPoint;
    }

    public static DPoint m1819a(Context context, double d, double d2) {
        return context == null ? null : fi.m1820a(context, new DPoint(d2, d));
    }

    public static DPoint m1820a(Context context, DPoint dPoint) {
        if (context == null) {
            return null;
        }
        String a = gb.m1927a(context, "libwgs2gcj.so");
        if (!(TextUtils.isEmpty(a) || !new File(a).exists() || f1208b)) {
            try {
                System.load(a);
                f1208b = true;
            } catch (Throwable th) {
                ev.m1777a(th, "OffsetUtil", "offset");
            }
        }
        return fi.m1823a(dPoint, f1208b);
    }

    public static DPoint m1821a(DPoint dPoint) {
        if (dPoint != null) {
            try {
                dPoint = fi.m1822a(dPoint, 2);
            } catch (Throwable th) {
                ev.m1777a(th, "OffsetUtil", "B2G");
            }
        }
        return dPoint;
    }

    private static DPoint m1822a(DPoint dPoint, int i) {
        double d = 0.006401062d;
        double d2 = 0.0060424805d;
        int i2 = 0;
        DPoint dPoint2 = null;
        while (i2 < i) {
            DPoint a = fi.m1818a(dPoint.getLongitude(), dPoint.getLatitude(), d, d2);
            d = dPoint.getLongitude() - a.getLongitude();
            d2 = dPoint.getLatitude() - a.getLatitude();
            i2++;
            dPoint2 = a;
        }
        return dPoint2;
    }

    private static DPoint m1823a(DPoint dPoint, boolean z) {
        double[] dArr;
        double[] dArr2;
        try {
            dArr2 = new double[2];
            if (z) {
                if (CoordUtil.convertToGcj(new double[]{dPoint.getLongitude(), dPoint.getLatitude()}, dArr2) != 0) {
                    dArr2 = C0244do.m1545a(dPoint.getLongitude(), dPoint.getLatitude());
                }
                dArr = dArr2;
            } else {
                dArr = C0244do.m1545a(dPoint.getLongitude(), dPoint.getLatitude());
            }
        } catch (Throwable th) {
            ev.m1777a(th, "OffsetUtil", "cover part2");
            return dPoint;
        }
        return new DPoint(dArr[1], dArr[0]);
    }

    private static double m1824b(double d) {
        return Math.cos((3000.0d * d) * (f1207a / 180.0d)) * 3.0E-6d;
    }

    public static double m1825b(double d, double d2) {
        return (Math.sin(d2 / 100000.0d) * (d / 18000.0d)) + (Math.cos(d / 100000.0d) * (d2 / 9000.0d));
    }

    public static DPoint m1826b(Context context, DPoint dPoint) {
        try {
            dPoint = fi.m1820a(context, fi.m1827c(dPoint.getLongitude(), dPoint.getLatitude()));
        } catch (Throwable th) {
            ev.m1777a(th, "OffsetUtil", "marbar2G");
        }
        return dPoint;
    }

    private static DPoint m1827c(double d, double d2) {
        double d3 = (double) (((long) (100000.0d * d)) % 36000000);
        double d4 = (double) (((long) (100000.0d * d2)) % 36000000);
        int i = (int) ((-fi.m1825b(d3, d4)) + d4);
        int i2 = (int) (((double) (d3 > 0.0d ? 1 : -1)) + ((-fi.m1816a((double) ((int) ((-fi.m1816a(d3, d4)) + d3)), (double) i)) + d3));
        return new DPoint(((double) ((int) (((double) (d4 > 0.0d ? 1 : -1)) + ((-fi.m1825b((double) i2, (double) i)) + d4)))) / 100000.0d, ((double) i2) / 100000.0d);
    }

    private static DPoint m1828d(double d, double d2) {
        DPoint dPoint = new DPoint();
        double sin = (Math.sin(fi.m1824b(d) + Math.atan2(d2, d)) * (fi.m1815a(d2) + Math.sqrt((d * d) + (d2 * d2)))) + 0.006d;
        dPoint.setLongitude(fi.m1817a((Math.cos(fi.m1824b(d) + Math.atan2(d2, d)) * (fi.m1815a(d2) + Math.sqrt((d * d) + (d2 * d2)))) + 0.0065d, 8));
        dPoint.setLatitude(fi.m1817a(sin, 8));
        return dPoint;
    }
}
