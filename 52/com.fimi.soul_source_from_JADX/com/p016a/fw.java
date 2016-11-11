package com.p016a;

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
import com.tencent.mm.sdk.platformtools.PhoneUtil;
import java.io.File;
import java.util.List;
import javax.xml.parsers.SAXParserFactory;
import org.p122a.p123a.C2915a;

/* renamed from: com.a.fw */
public class fw {
    private static String f1241a;
    private static boolean f1242b;
    private static String f1243c;
    private static String f1244d;
    private static String f1245e;
    private static String f1246f;

    static {
        f1241a = C2915a.f14760f;
        f1242b = false;
        f1243c = C2915a.f14760f;
        f1244d = C2915a.f14760f;
        f1245e = C2915a.f14760f;
        f1246f = C2915a.f14760f;
    }

    static String m1871a(Context context) {
        try {
            return fw.m1896u(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return C2915a.f14760f;
        }
    }

    private static List<ScanResult> m1873a(List<ScanResult> list) {
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

    public static void m1874a() {
        try {
            if (VERSION.SDK_INT > 14) {
                TrafficStats.class.getDeclaredMethod("setThreadStatsTag", new Class[]{Integer.TYPE}).invoke(null, new Object[]{Integer.valueOf(40964)});
            }
        } catch (Throwable e) {
            C0247g.m1917a(e, "DeviceInfo", "setTraficTag");
        } catch (Throwable e2) {
            C0247g.m1917a(e2, "DeviceInfo", "setTraficTag");
        } catch (Throwable e22) {
            C0247g.m1917a(e22, "DeviceInfo", "setTraficTag");
        } catch (Throwable e222) {
            C0247g.m1917a(e222, "DeviceInfo", "setTraficTag");
        } catch (Throwable e2222) {
            C0247g.m1917a(e2222, "DeviceInfo", "setTraficTag");
        }
    }

    static String m1876b(Context context) {
        String str = C2915a.f14760f;
        try {
            str = fw.m1898w(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return str;
    }

    static int m1878c(Context context) {
        int i = -1;
        try {
            i = fw.m1899x(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return i;
    }

    static int m1879d(Context context) {
        int i = -1;
        try {
            i = fw.m1897v(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return i;
    }

    static String m1880e(Context context) {
        try {
            return fw.m1895t(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return C2915a.f14760f;
        }
    }

    public static String m1881f(Context context) {
        try {
            if (f1241a != null && !C2915a.f14760f.equals(f1241a)) {
                return f1241a;
            }
            if (context.checkCallingOrSelfPermission("android.permission.WRITE_SETTINGS") == 0) {
                f1241a = System.getString(context.getContentResolver(), "mqBRboGZkQPcAkyk");
            }
            if (!(f1241a == null || C2915a.f14760f.equals(f1241a))) {
                return f1241a;
            }
            try {
                if ("mounted".equals(Environment.getExternalStorageState())) {
                    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/.UTSystemConfig/Global/Alvin2.xml");
                    if (file.exists()) {
                        SAXParserFactory.newInstance().newSAXParser().parse(file, new fx());
                    }
                }
            } catch (Throwable e) {
                C0247g.m1917a(e, "DeviceInfo", "getUTDID");
            } catch (Throwable e2) {
                C0247g.m1917a(e2, "DeviceInfo", "getUTDID");
            } catch (Throwable e22) {
                C0247g.m1917a(e22, "DeviceInfo", "getUTDID");
            } catch (Throwable e222) {
                C0247g.m1917a(e222, "DeviceInfo", "getUTDID");
            } catch (Throwable e2222) {
                C0247g.m1917a(e2222, "DeviceInfo", "getUTDID");
            }
            return f1241a;
        } catch (Throwable e22222) {
            C0247g.m1917a(e22222, "DeviceInfo", "getUTDID");
        }
    }

    static String m1882g(Context context) {
        if (context != null) {
            try {
                if (context.checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE") == 0) {
                    WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                    if (wifiManager.isWifiEnabled()) {
                        return wifiManager.getConnectionInfo().getBSSID();
                    }
                    return null;
                }
            } catch (Throwable th) {
                C0247g.m1917a(th, "DeviceInfo", "getWifiMacs");
            }
        }
        return null;
    }

    static String m1883h(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        if (context != null) {
            try {
                if (context.checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE") == 0) {
                    WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                    if (wifiManager.isWifiEnabled()) {
                        List scanResults = wifiManager.getScanResults();
                        if (scanResults == null || scanResults.size() == 0) {
                            return stringBuilder.toString();
                        }
                        List a = fw.m1873a(scanResults);
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
                C0247g.m1917a(th, "DeviceInfo", "getWifiMacs");
            }
        }
        return stringBuilder.toString();
    }

    static String m1884i(Context context) {
        try {
            if (f1243c != null && !C2915a.f14760f.equals(f1243c)) {
                return f1243c;
            }
            if (context.checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE") != 0) {
                return f1243c;
            }
            f1243c = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            return f1243c;
        } catch (Throwable th) {
            C0247g.m1917a(th, "DeviceInfo", "getDeviceMac");
        }
    }

    static String[] m1885j(Context context) {
        try {
            if (context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") == 0 && context.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0) {
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
            C0247g.m1917a(th, "DeviceInfo", "cellInfo");
        }
    }

    static String m1886k(Context context) {
        String str = C2915a.f14760f;
        String networkOperator;
        try {
            if (context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
                return str;
            }
            networkOperator = ((TelephonyManager) context.getSystemService("phone")).getNetworkOperator();
            if (TextUtils.isEmpty(networkOperator) && networkOperator.length() < 3) {
                return str;
            }
            networkOperator = networkOperator.substring(3);
            return networkOperator;
        } catch (Throwable th) {
            th.printStackTrace();
            C0247g.m1917a(th, "DeviceInfo", "getMNC");
            networkOperator = str;
        }
    }

    public static int m1887l(Context context) {
        int i = -1;
        try {
            i = fw.m1899x(context);
        } catch (Throwable th) {
            C0247g.m1917a(th, "DeviceInfo", "getNetWorkType");
        }
        return i;
    }

    public static int m1888m(Context context) {
        int i = -1;
        try {
            i = fw.m1897v(context);
        } catch (Throwable th) {
            C0247g.m1917a(th, "DeviceInfo", "getActiveNetWorkType");
        }
        return i;
    }

    public static NetworkInfo m1889n(Context context) {
        if (context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
            return null;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        return connectivityManager == null ? null : connectivityManager.getActiveNetworkInfo();
    }

    static String m1890o(Context context) {
        String str = null;
        try {
            NetworkInfo n = fw.m1889n(context);
            if (n != null) {
                str = n.getExtraInfo();
            }
        } catch (Throwable th) {
            C0247g.m1917a(th, "DeviceInfo", "getNetworkExtraInfo");
        }
        return str;
    }

    static String m1891p(Context context) {
        try {
            if (f1244d != null && !C2915a.f14760f.equals(f1244d)) {
                return f1244d;
            }
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            int i2 = displayMetrics.heightPixels;
            f1244d = i2 > i ? i + "*" + i2 : i2 + "*" + i;
            return f1244d;
        } catch (Throwable th) {
            C0247g.m1917a(th, "DeviceInfo", "getReslution");
        }
    }

    public static String m1892q(Context context) {
        try {
            if (f1245e != null && !C2915a.f14760f.equals(f1245e)) {
                return f1245e;
            }
            if (context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
                return f1245e;
            }
            f1245e = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            if (f1245e == null) {
                f1245e = C2915a.f14760f;
            }
            return f1245e;
        } catch (Throwable th) {
            C0247g.m1917a(th, "DeviceInfo", "getDeviceID");
        }
    }

    public static String m1893r(Context context) {
        String str = C2915a.f14760f;
        try {
            str = fw.m1895t(context);
        } catch (Throwable th) {
            C0247g.m1917a(th, "DeviceInfo", "getSubscriberId");
        }
        return str;
    }

    static String m1894s(Context context) {
        try {
            return fw.m1896u(context);
        } catch (Throwable th) {
            C0247g.m1917a(th, "DeviceInfo", "getNetworkOperatorName");
            return C2915a.f14760f;
        }
    }

    private static String m1895t(Context context) {
        if (f1246f != null && !C2915a.f14760f.equals(f1246f)) {
            return f1246f;
        }
        if (context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
            return f1246f;
        }
        f1246f = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
        if (f1246f == null) {
            f1246f = C2915a.f14760f;
        }
        return f1246f;
    }

    private static String m1896u(Context context) {
        if (context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
            return null;
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        CharSequence simOperatorName = telephonyManager.getSimOperatorName();
        return TextUtils.isEmpty(simOperatorName) ? telephonyManager.getNetworkOperatorName() : simOperatorName;
    }

    private static int m1897v(Context context) {
        if (context == null || context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
            return -1;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return -1;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo == null ? -1 : activeNetworkInfo.getType();
    }

    private static String m1898w(Context context) {
        String str = C2915a.f14760f;
        String r = fw.m1893r(context);
        return (r == null || r.length() < 5) ? str : r.substring(3, 5);
    }

    private static int m1899x(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") != 0 ? -1 : ((TelephonyManager) context.getSystemService("phone")).getNetworkType();
    }
}
