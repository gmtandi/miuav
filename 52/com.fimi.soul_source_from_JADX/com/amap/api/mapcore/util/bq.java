package com.amap.api.mapcore.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.Settings.System;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.fimi.soul.entity.User;
import com.tencent.mm.sdk.platformtools.PhoneUtil;
import java.io.File;
import java.util.List;
import javax.xml.parsers.SAXParserFactory;
import org.p122a.p123a.C2915a;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class bq {
    private static String f2258a;
    private static boolean f2259b;
    private static String f2260c;
    private static String f2261d;
    private static String f2262e;
    private static String f2263f;

    /* renamed from: com.amap.api.mapcore.util.bq.a */
    class C0361a extends DefaultHandler {
        C0361a() {
        }

        public void characters(char[] cArr, int i, int i2) {
            if (bq.f2259b) {
                bq.f2258a = new String(cArr, i, i2);
            }
        }

        public void endElement(String str, String str2, String str3) {
            bq.f2259b = false;
        }

        public void startElement(String str, String str2, String str3, Attributes attributes) {
            if (str2.equals("string") && "UTDID".equals(attributes.getValue(User.FN_NAME))) {
                bq.f2259b = true;
            }
        }
    }

    static {
        f2258a = C2915a.f14760f;
        f2259b = false;
        f2260c = C2915a.f14760f;
        f2261d = C2915a.f14760f;
        f2262e = C2915a.f14760f;
        f2263f = C2915a.f14760f;
    }

    static String m3685a(Context context) {
        try {
            return m3711u(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return C2915a.f14760f;
        }
    }

    private static List<ScanResult> m3687a(List<ScanResult> list) {
        int size = list.size();
        for (int i = 0; i < size - 1; i++) {
            for (int i2 = 1; i2 < size - i; i2++) {
                if (((ScanResult) list.get(i2 - 1)).level > ((ScanResult) list.get(i2)).level) {
                    ScanResult scanResult = (ScanResult) list.get(i2 - 1);
                    list.set(i2 - 1, list.get(i2));
                    list.set(i2, scanResult);
                }
            }
        }
        return list;
    }

    public static void m3688a() {
        try {
            if (VERSION.SDK_INT > 14) {
                TrafficStats.class.getDeclaredMethod("setThreadStatsTag", new Class[]{Integer.TYPE}).invoke(null, new Object[]{Integer.valueOf(40964)});
            }
        } catch (Throwable e) {
            cb.m3809a(e, "DeviceInfo", "setTraficTag");
        } catch (Throwable e2) {
            cb.m3809a(e2, "DeviceInfo", "setTraficTag");
        } catch (Throwable e22) {
            cb.m3809a(e22, "DeviceInfo", "setTraficTag");
        } catch (Throwable e222) {
            cb.m3809a(e222, "DeviceInfo", "setTraficTag");
        } catch (Throwable e2222) {
            cb.m3809a(e2222, "DeviceInfo", "setTraficTag");
        }
    }

    private static boolean m3689a(Context context, String str) {
        return context.checkCallingOrSelfPermission(str) == 0;
    }

    static String m3691b(Context context) {
        String str = C2915a.f14760f;
        try {
            str = m3714x(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return str;
    }

    static int m3693c(Context context) {
        int i = -1;
        try {
            i = m3715y(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return i;
    }

    static int m3694d(Context context) {
        int i = -1;
        try {
            i = m3712v(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return i;
    }

    static String m3695e(Context context) {
        try {
            return m3710t(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return C2915a.f14760f;
        }
    }

    public static String m3696f(Context context) {
        try {
            if (f2258a != null && !C2915a.f14760f.equals(f2258a)) {
                return f2258a;
            }
            if (m3689a(context, "android.permission.WRITE_SETTINGS")) {
                f2258a = System.getString(context.getContentResolver(), "mqBRboGZkQPcAkyk");
            }
            if (!(f2258a == null || C2915a.f14760f.equals(f2258a))) {
                return f2258a;
            }
            try {
                if ("mounted".equals(Environment.getExternalStorageState())) {
                    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/.UTSystemConfig/Global/Alvin2.xml");
                    if (file.exists()) {
                        SAXParserFactory.newInstance().newSAXParser().parse(file, new C0361a());
                    }
                }
            } catch (Throwable e) {
                cb.m3809a(e, "DeviceInfo", "getUTDID");
            } catch (Throwable e2) {
                cb.m3809a(e2, "DeviceInfo", "getUTDID");
            } catch (Throwable e22) {
                cb.m3809a(e22, "DeviceInfo", "getUTDID");
            } catch (Throwable e222) {
                cb.m3809a(e222, "DeviceInfo", "getUTDID");
            } catch (Throwable e2222) {
                cb.m3809a(e2222, "DeviceInfo", "getUTDID");
            }
            return f2258a;
        } catch (Throwable e22222) {
            cb.m3809a(e22222, "DeviceInfo", "getUTDID");
        }
    }

    static String m3697g(Context context) {
        if (context != null) {
            try {
                if (m3689a(context, "android.permission.ACCESS_WIFI_STATE")) {
                    WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                    if (wifiManager.isWifiEnabled()) {
                        return wifiManager.getConnectionInfo().getBSSID();
                    }
                    return null;
                }
            } catch (Throwable th) {
                cb.m3809a(th, "DeviceInfo", "getWifiMacs");
            }
        }
        return null;
    }

    static String m3698h(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        if (context != null) {
            try {
                if (m3689a(context, "android.permission.ACCESS_WIFI_STATE")) {
                    WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                    if (wifiManager.isWifiEnabled()) {
                        List scanResults = wifiManager.getScanResults();
                        if (scanResults == null || scanResults.size() == 0) {
                            return stringBuilder.toString();
                        }
                        List a = m3687a(scanResults);
                        Object obj = 1;
                        int i = 0;
                        while (i < a.size() && i < 7) {
                            ScanResult scanResult = (ScanResult) a.get(i);
                            if (obj != null) {
                                obj = null;
                            } else {
                                stringBuilder.append(";");
                            }
                            stringBuilder.append(scanResult.BSSID);
                            i++;
                        }
                    }
                    return stringBuilder.toString();
                }
            } catch (Throwable th) {
                cb.m3809a(th, "DeviceInfo", "getWifiMacs");
            }
        }
        return stringBuilder.toString();
    }

    public static String m3699i(Context context) {
        try {
            if (f2260c != null && !C2915a.f14760f.equals(f2260c)) {
                return f2260c;
            }
            if (!m3689a(context, "android.permission.ACCESS_WIFI_STATE")) {
                return f2260c;
            }
            f2260c = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            return f2260c;
        } catch (Throwable th) {
            cb.m3809a(th, "DeviceInfo", "getDeviceMac");
        }
    }

    static String[] m3700j(Context context) {
        try {
            if (m3689a(context, "android.permission.READ_PHONE_STATE") && m3689a(context, "android.permission.ACCESS_COARSE_LOCATION")) {
                CellLocation cellLocation = ((TelephonyManager) context.getSystemService("phone")).getCellLocation();
                int cid;
                int lac;
                if (cellLocation instanceof GsmCellLocation) {
                    GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                    cid = gsmCellLocation.getCid();
                    lac = gsmCellLocation.getLac();
                    return new String[]{lac + "||" + cid, PhoneUtil.CELL_GSM};
                }
                if (cellLocation instanceof CdmaCellLocation) {
                    CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
                    cid = cdmaCellLocation.getSystemId();
                    lac = cdmaCellLocation.getNetworkId();
                    int baseStationId = cdmaCellLocation.getBaseStationId();
                    if (cid < 0 || lac < 0 || baseStationId < 0) {
                    }
                    return new String[]{cid + "||" + lac + "||" + baseStationId, PhoneUtil.CELL_CDMA};
                }
                return new String[]{C2915a.f14760f, C2915a.f14760f};
            }
            return new String[]{C2915a.f14760f, C2915a.f14760f};
        } catch (Throwable th) {
            cb.m3809a(th, "DeviceInfo", "cellInfo");
        }
    }

    static String m3701k(Context context) {
        String str = C2915a.f14760f;
        try {
            if (m3689a(context, "android.permission.READ_PHONE_STATE")) {
                String networkOperator = m3716z(context).getNetworkOperator();
                if (!TextUtils.isEmpty(networkOperator) || networkOperator.length() >= 3) {
                    str = networkOperator.substring(3);
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
            cb.m3809a(th, "DeviceInfo", "getMNC");
        }
        return str;
    }

    public static int m3702l(Context context) {
        int i = -1;
        try {
            i = m3715y(context);
        } catch (Throwable th) {
            cb.m3809a(th, "DeviceInfo", "getNetWorkType");
        }
        return i;
    }

    public static int m3703m(Context context) {
        int i = -1;
        try {
            i = m3712v(context);
        } catch (Throwable th) {
            cb.m3809a(th, "DeviceInfo", "getActiveNetWorkType");
        }
        return i;
    }

    public static NetworkInfo m3704n(Context context) {
        if (!m3689a(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return null;
        }
        ConnectivityManager w = m3713w(context);
        return w != null ? w.getActiveNetworkInfo() : null;
    }

    static String m3705o(Context context) {
        String str = null;
        try {
            NetworkInfo n = m3704n(context);
            if (n != null) {
                str = n.getExtraInfo();
            }
        } catch (Throwable th) {
            cb.m3809a(th, "DeviceInfo", "getNetworkExtraInfo");
        }
        return str;
    }

    static String m3706p(Context context) {
        try {
            if (f2261d != null && !C2915a.f14760f.equals(f2261d)) {
                return f2261d;
            }
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            int i2 = displayMetrics.heightPixels;
            f2261d = i2 > i ? i + "*" + i2 : i2 + "*" + i;
            return f2261d;
        } catch (Throwable th) {
            cb.m3809a(th, "DeviceInfo", "getReslution");
        }
    }

    public static String m3707q(Context context) {
        try {
            if (f2262e != null && !C2915a.f14760f.equals(f2262e)) {
                return f2262e;
            }
            if (!m3689a(context, "android.permission.READ_PHONE_STATE")) {
                return f2262e;
            }
            f2262e = m3716z(context).getDeviceId();
            if (f2262e == null) {
                f2262e = C2915a.f14760f;
            }
            return f2262e;
        } catch (Throwable th) {
            cb.m3809a(th, "DeviceInfo", "getDeviceID");
        }
    }

    public static String m3708r(Context context) {
        String str = C2915a.f14760f;
        try {
            str = m3710t(context);
        } catch (Throwable th) {
            cb.m3809a(th, "DeviceInfo", "getSubscriberId");
        }
        return str;
    }

    static String m3709s(Context context) {
        try {
            return m3711u(context);
        } catch (Throwable th) {
            cb.m3809a(th, "DeviceInfo", "getNetworkOperatorName");
            return C2915a.f14760f;
        }
    }

    private static String m3710t(Context context) {
        if (f2263f != null && !C2915a.f14760f.equals(f2263f)) {
            return f2263f;
        }
        if (!m3689a(context, "android.permission.READ_PHONE_STATE")) {
            return f2263f;
        }
        f2263f = m3716z(context).getSubscriberId();
        if (f2263f == null) {
            f2263f = C2915a.f14760f;
        }
        return f2263f;
    }

    private static String m3711u(Context context) {
        if (!m3689a(context, "android.permission.READ_PHONE_STATE")) {
            return null;
        }
        Object simOperatorName = m3716z(context).getSimOperatorName();
        return TextUtils.isEmpty(simOperatorName) ? m3716z(context).getNetworkOperatorName() : simOperatorName;
    }

    private static int m3712v(Context context) {
        if (context == null || !m3689a(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return -1;
        }
        ConnectivityManager w = m3713w(context);
        if (w == null) {
            return -1;
        }
        NetworkInfo activeNetworkInfo = w.getActiveNetworkInfo();
        return activeNetworkInfo != null ? activeNetworkInfo.getType() : -1;
    }

    private static ConnectivityManager m3713w(Context context) {
        return (ConnectivityManager) context.getSystemService("connectivity");
    }

    private static String m3714x(Context context) {
        String str = C2915a.f14760f;
        String r = m3708r(context);
        return (r == null || r.length() < 5) ? str : r.substring(3, 5);
    }

    private static int m3715y(Context context) {
        return !m3689a(context, "android.permission.READ_PHONE_STATE") ? -1 : m3716z(context).getNetworkType();
    }

    private static TelephonyManager m3716z(Context context) {
        return (TelephonyManager) context.getSystemService("phone");
    }
}
