package com.amap.api.services.core;

import com.fimi.kernel.p084e.C1173l;
import com.fimi.soul.base.C1236a;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.message.RMsgInfo;
import com.xiaomi.market.sdk.C2537j;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

/* renamed from: com.amap.api.services.core.d */
public class C0471d {
    public static double m4757a(double d) {
        return Double.parseDouble(new DecimalFormat("0.000000", new DecimalFormatSymbols(Locale.US)).format(d));
    }

    public static double m4758a(int i) {
        return ((double) i) / 111700.0d;
    }

    public static String m4759a(LatLonPoint latLonPoint) {
        if (latLonPoint == null) {
            return C2915a.f14760f;
        }
        double a = C0471d.m4757a(latLonPoint.getLongitude());
        return a + MiPushClient.ACCEPT_TIME_SEPARATOR + C0471d.m4757a(latLonPoint.getLatitude());
    }

    public static String m4760a(Date date) {
        return date != null ? new SimpleDateFormat(C1173l.f5336h).format(date) : C2915a.f14760f;
    }

    public static String m4761a(List<LatLonPoint> list) {
        if (list == null) {
            return C2915a.f14760f;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            LatLonPoint latLonPoint = (LatLonPoint) list.get(i);
            double a = C0471d.m4757a(latLonPoint.getLongitude());
            stringBuffer.append(a).append(MiPushClient.ACCEPT_TIME_SEPARATOR).append(C0471d.m4757a(latLonPoint.getLatitude()));
            stringBuffer.append(";");
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        return stringBuffer.toString();
    }

    public static void m4762a(Throwable th, String str, String str2) {
        ay b = ay.m4591b();
        if (b != null) {
            b.m4593b(th, str, str2);
        }
        th.printStackTrace();
    }

    public static boolean m4763a(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static void m4764b(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(RMsgInfo.COL_STATUS) && jSONObject.has(C2537j.ag)) {
                String string = jSONObject.getString(RMsgInfo.COL_STATUS);
                String string2 = jSONObject.getString(C2537j.ag);
                if (!string.equals(Constants.VIA_TO_TYPE_QQ_GROUP) && string.equals(Constants.VIA_RESULT_SUCCESS)) {
                    if (string2.equals("INVALID_USER_KEY") || string2.equals("INSUFFICIENT_PRIVILEGES") || string2.equals("INVALID_USER_SCODE") || string2.equals("INVALID_USER_SIGNATURE")) {
                        throw new AMapException(AMapException.ERROR_FAILURE_AUTH);
                    } else if (string2.equals("SERVICE_NOT_EXIST") || string2.equals("\u670d\u52a1\u6b63\u5728\u7ef4\u62a4\u4e2d")) {
                        throw new AMapException(AMapException.ERROR_SERVER);
                    } else if (string2.startsWith("UNKNOWN_ERROR")) {
                        throw new AMapException(AMapException.ERROR_UNKNOWN);
                    } else if (string2.equals("INVALID_PARAMS") || string2.equals("\u53c2\u6570\u7f3a\u5931\u6216\u683c\u5f0f\u975e\u6cd5") || string2.equals("\u8d26\u53f7\u672a\u6fc0\u6d3b\u6216\u5df2\u88ab\u51bb\u7ed3")) {
                        throw new AMapException(AMapException.ERROR_INVALID_PARAMETER);
                    } else if (string2.equals("OVER_QUOTA") || string2.equals("USER_VISIT_TOO_FREQUENTLY") || string2.equals("USER_DAILY_VISITS_EXCESS") || string2.equals("IP_FORBIDDEN")) {
                        throw new AMapException(AMapException.ERROR_QUOTA);
                    } else if (string2.equals("SERVICE_RESPONSE_ERROR")) {
                        throw new AMapException(AMapException.ERROR_SERVICE);
                    } else {
                        throw new AMapException(string2);
                    }
                }
            }
        } catch (Throwable e) {
            C0471d.m4762a(e, "CoreUtil", "paseAuthFailurJson");
        }
    }

    public static Date m4765c(String str) {
        Date date = null;
        try {
            date = new SimpleDateFormat(C1236a.f5614l).parse(str);
        } catch (Throwable e) {
            try {
                date = new SimpleDateFormat(C1173l.f5330b).parse(str);
            } catch (ParseException e2) {
                C0471d.m4762a(e, "CoreUtil", "parseString2Date");
            }
        }
        return date;
    }

    public static Date m4766d(String str) {
        Date date = null;
        if (!(str == null || str.trim().equals(C2915a.f14760f))) {
            try {
                date = new SimpleDateFormat("HHmm").parse(str);
            } catch (Throwable e) {
                C0471d.m4762a(e, "CoreUtil", "parseString2Time");
            }
        }
        return date;
    }

    public static Date m4767e(String str) {
        Date date = null;
        if (!(str == null || str.trim().equals(C2915a.f14760f))) {
            try {
                date = new SimpleDateFormat(C1173l.f5336h).parse(str);
            } catch (Throwable e) {
                C0471d.m4762a(e, "CoreUtil", "parseTime");
            }
        }
        return date;
    }
}
