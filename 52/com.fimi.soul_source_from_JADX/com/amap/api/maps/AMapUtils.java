package com.amap.api.maps;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import com.amap.api.mapcore.C0330s;
import com.amap.api.mapcore.util.bk;
import com.amap.api.mapcore.util.bl;
import com.amap.api.mapcore.util.bm;
import com.amap.api.mapcore.util.bv.C0363a;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.NaviPara;
import com.amap.api.maps.model.PoiPara;
import com.amap.api.maps.model.RoutePara;
import com.amap.api.maps.model.WeightedLatLng;
import org.p122a.p123a.C2915a;

public class AMapUtils {
    public static final int BUS_COMFORT = 4;
    public static final int BUS_MONEY_LITTLE = 1;
    public static final int BUS_NO_SUBWAY = 5;
    public static final int BUS_TIME_FIRST = 0;
    public static final int BUS_TRANSFER_LITTLE = 2;
    public static final int BUS_WALK_LITTLE = 3;
    public static final int DRIVING_AVOID_CONGESTION = 4;
    public static final int DRIVING_DEFAULT = 0;
    public static final int DRIVING_NO_HIGHWAY = 3;
    public static final int DRIVING_NO_HIGHWAY_AVOID_CONGESTION = 6;
    public static final int DRIVING_NO_HIGHWAY_AVOID_SHORT_MONEY = 5;
    public static final int DRIVING_NO_HIGHWAY_SAVE_MONEY_AVOID_CONGESTION = 8;
    public static final int DRIVING_SAVE_MONEY = 1;
    public static final int DRIVING_SAVE_MONEY_AVOID_CONGESTION = 7;
    public static final int DRIVING_SHORT_DISTANCE = 2;

    /* renamed from: com.amap.api.maps.AMapUtils.a */
    class C0426a extends Thread {
        String f2618a;
        Context f2619b;

        public C0426a(String str, Context context) {
            this.f2618a = C2915a.f14760f;
            this.f2618a = str;
            if (context != null) {
                this.f2619b = context.getApplicationContext();
            }
        }

        public void run() {
            if (this.f2619b != null) {
                try {
                    C0363a c0363a = new C0363a(this.f2618a, "3.3.1", C0330s.f2071d);
                    String[] strArr = new String[AMapUtils.DRIVING_SAVE_MONEY];
                    strArr[AMapUtils.DRIVING_DEFAULT] = "com.amap.api.maps";
                    bm.m3660b(this.f2619b, c0363a.m3757a(strArr).m3758a());
                    interrupt();
                } catch (bk e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String m4309a(NaviPara naviPara, Context context) {
        Object[] objArr = new Object[DRIVING_AVOID_CONGESTION];
        objArr[DRIVING_DEFAULT] = bl.m3648b(context);
        objArr[DRIVING_SAVE_MONEY] = Double.valueOf(naviPara.getTargetPoint().latitude);
        objArr[DRIVING_SHORT_DISTANCE] = Double.valueOf(naviPara.getTargetPoint().longitude);
        objArr[DRIVING_NO_HIGHWAY] = Integer.valueOf(naviPara.getNaviStyle());
        return String.format("androidamap://navi?sourceApplication=%s&lat=%f&lon=%f&dev=0&style=%d", objArr);
    }

    private static String m4310a(PoiPara poiPara, Context context) {
        Object[] objArr = new Object[DRIVING_SHORT_DISTANCE];
        objArr[DRIVING_DEFAULT] = bl.m3648b(context);
        objArr[DRIVING_SAVE_MONEY] = poiPara.getKeywords();
        String format = String.format("androidamap://arroundpoi?sourceApplication=%s&keywords=%s&dev=0", objArr);
        return poiPara.getCenter() != null ? format + "&lat=" + poiPara.getCenter().latitude + "&lon=" + poiPara.getCenter().longitude : format;
    }

    private static void m4311a(RoutePara routePara, Context context, int i) {
        if (!m4312a(context)) {
            throw new AMapException(AMapException.AMAP_NOT_SUPPORT);
        } else if (m4313a(routePara)) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.addFlags(276824064);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(m4314b(routePara, context, i)));
                intent.setPackage("com.autonavi.minimap");
                new C0426a("oan", context).start();
                context.startActivity(intent);
            } catch (Throwable th) {
                AMapException aMapException = new AMapException(AMapException.AMAP_NOT_SUPPORT);
            }
        } else {
            throw new AMapException(AMapException.ILLEGAL_AMAP_ARGUMENT);
        }
    }

    private static boolean m4312a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.autonavi.minimap", DRIVING_DEFAULT) != null;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    private static boolean m4313a(RoutePara routePara) {
        return (routePara.getStartPoint() == null || routePara.getEndPoint() == null || routePara.getStartName() == null || routePara.getStartName().trim().length() <= 0 || routePara.getEndName() == null || routePara.getEndName().trim().length() <= 0) ? false : true;
    }

    private static String m4314b(RoutePara routePara, Context context, int i) {
        Object[] objArr = new Object[DRIVING_NO_HIGHWAY_SAVE_MONEY_AVOID_CONGESTION];
        objArr[DRIVING_DEFAULT] = bl.m3648b(context);
        objArr[DRIVING_SAVE_MONEY] = Double.valueOf(routePara.getStartPoint().latitude);
        objArr[DRIVING_SHORT_DISTANCE] = Double.valueOf(routePara.getStartPoint().longitude);
        objArr[DRIVING_NO_HIGHWAY] = routePara.getStartName();
        objArr[DRIVING_AVOID_CONGESTION] = Double.valueOf(routePara.getEndPoint().latitude);
        objArr[DRIVING_NO_HIGHWAY_AVOID_SHORT_MONEY] = Double.valueOf(routePara.getEndPoint().longitude);
        objArr[DRIVING_NO_HIGHWAY_AVOID_CONGESTION] = routePara.getEndName();
        objArr[DRIVING_SAVE_MONEY_AVOID_CONGESTION] = Integer.valueOf(i);
        String format = String.format("androidamap://route?sourceApplication=%s&slat=%f&slon=%f&sname=%s&dlat=%f&dlon=%f&dname=%s&dev=0&t=%d", objArr);
        return i == DRIVING_SAVE_MONEY ? format + "&m=" + routePara.getTransitRouteStyle() : i == DRIVING_SHORT_DISTANCE ? format + "&m=" + routePara.getDrivingRouteStyle() : format;
    }

    public static float calculateArea(LatLng latLng, LatLng latLng2) {
        double sin = Math.sin((latLng.latitude * 3.141592653589793d) / 180.0d) - Math.sin((latLng2.latitude * 3.141592653589793d) / 180.0d);
        double d = (latLng2.longitude - latLng.longitude) / 360.0d;
        if (d < 0.0d) {
            d += WeightedLatLng.DEFAULT_INTENSITY;
        }
        return (float) (d * ((6378137.0d * (6.283185307179586d * 6378137.0d)) * sin));
    }

    public static float calculateLineDistance(LatLng latLng, LatLng latLng2) {
        double d = latLng.longitude;
        double d2 = latLng.latitude;
        d *= 0.01745329251994329d;
        d2 *= 0.01745329251994329d;
        double d3 = latLng2.longitude * 0.01745329251994329d;
        double d4 = latLng2.latitude * 0.01745329251994329d;
        double sin = Math.sin(d);
        double sin2 = Math.sin(d2);
        d = Math.cos(d);
        d2 = Math.cos(d2);
        double sin3 = Math.sin(d3);
        double sin4 = Math.sin(d4);
        d3 = Math.cos(d3);
        d4 = Math.cos(d4);
        double[] dArr = new double[DRIVING_NO_HIGHWAY];
        double[] dArr2 = new double[DRIVING_NO_HIGHWAY];
        dArr[DRIVING_DEFAULT] = d * d2;
        dArr[DRIVING_SAVE_MONEY] = d2 * sin;
        dArr[DRIVING_SHORT_DISTANCE] = sin2;
        dArr2[DRIVING_DEFAULT] = d4 * d3;
        dArr2[DRIVING_SAVE_MONEY] = d4 * sin3;
        dArr2[DRIVING_SHORT_DISTANCE] = sin4;
        return (float) (Math.asin(Math.sqrt((((dArr[DRIVING_DEFAULT] - dArr2[DRIVING_DEFAULT]) * (dArr[DRIVING_DEFAULT] - dArr2[DRIVING_DEFAULT])) + ((dArr[DRIVING_SAVE_MONEY] - dArr2[DRIVING_SAVE_MONEY]) * (dArr[DRIVING_SAVE_MONEY] - dArr2[DRIVING_SAVE_MONEY]))) + ((dArr[DRIVING_SHORT_DISTANCE] - dArr2[DRIVING_SHORT_DISTANCE]) * (dArr[DRIVING_SHORT_DISTANCE] - dArr2[DRIVING_SHORT_DISTANCE]))) / 2.0d) * 1.27420015798544E7d);
    }

    public static void getLatestAMapApp(Context context) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addFlags(276824064);
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setData(Uri.parse("http://wap.amap.com/"));
            new C0426a("glaa", context).start();
            context.startActivity(intent);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void openAMapDrivingRoute(RoutePara routePara, Context context) {
        m4311a(routePara, context, DRIVING_SHORT_DISTANCE);
    }

    public static void openAMapNavi(NaviPara naviPara, Context context) {
        if (!m4312a(context)) {
            throw new AMapException(AMapException.AMAP_NOT_SUPPORT);
        } else if (naviPara.getTargetPoint() != null) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.addFlags(276824064);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(m4309a(naviPara, context)));
                intent.setPackage("com.autonavi.minimap");
                new C0426a("oan", context).start();
                context.startActivity(intent);
            } catch (Throwable th) {
                AMapException aMapException = new AMapException(AMapException.AMAP_NOT_SUPPORT);
            }
        } else {
            throw new AMapException(AMapException.ILLEGAL_AMAP_ARGUMENT);
        }
    }

    public static void openAMapPoiNearbySearch(PoiPara poiPara, Context context) {
        if (!m4312a(context)) {
            throw new AMapException(AMapException.AMAP_NOT_SUPPORT);
        } else if (poiPara.getKeywords() == null || poiPara.getKeywords().trim().length() <= 0) {
            throw new AMapException(AMapException.ILLEGAL_AMAP_ARGUMENT);
        } else {
            try {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.addFlags(276824064);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(m4310a(poiPara, context)));
                intent.setPackage("com.autonavi.minimap");
                new C0426a("oan", context).start();
                context.startActivity(intent);
            } catch (Throwable th) {
                AMapException aMapException = new AMapException(AMapException.AMAP_NOT_SUPPORT);
            }
        }
    }

    public static void openAMapTransitRoute(RoutePara routePara, Context context) {
        m4311a(routePara, context, DRIVING_SAVE_MONEY);
    }

    public static void openAMapWalkingRoute(RoutePara routePara, Context context) {
        m4311a(routePara, context, DRIVING_AVOID_CONGESTION);
    }
}
