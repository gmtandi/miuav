package com.amap.api.services.core;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.provider.Settings.System;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.fimi.soul.entity.User;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.p122a.p123a.C2915a;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/* renamed from: com.amap.api.services.core.z */
public class C0500z {
    private static String f3180a;
    private static boolean f3181b;
    private static String f3182c;
    private static String f3183d;
    private static String f3184e;
    private static String f3185f;

    /* renamed from: com.amap.api.services.core.z.a */
    class C0499a extends DefaultHandler {
        C0499a() {
        }

        public void characters(char[] cArr, int i, int i2) {
            if (C0500z.f3181b) {
                C0500z.f3180a = new String(cArr, i, i2);
            }
        }

        public void endElement(String str, String str2, String str3) {
            C0500z.f3181b = false;
        }

        public void startElement(String str, String str2, String str3, Attributes attributes) {
            if (str2.equals("string") && "UTDID".equals(attributes.getValue(User.FN_NAME))) {
                C0500z.f3181b = true;
            }
        }
    }

    static {
        f3180a = null;
        f3181b = false;
        f3182c = null;
        f3183d = null;
        f3184e = null;
        f3185f = null;
    }

    public static String m4886a(Context context) {
        try {
            if (f3180a != null && !C2915a.f14760f.equals(f3180a)) {
                return f3180a;
            }
            if (context.checkCallingOrSelfPermission("android.permission.WRITE_SETTINGS") == 0) {
                f3180a = System.getString(context.getContentResolver(), "mqBRboGZkQPcAkyk");
            }
            if (!(f3180a == null || C2915a.f14760f.equals(f3180a))) {
                return f3180a;
            }
            try {
                if ("mounted".equals(Environment.getExternalStorageState())) {
                    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/.UTSystemConfig/Global/Alvin2.xml");
                    if (file.exists()) {
                        SAXParserFactory.newInstance().newSAXParser().parse(file, new C0499a());
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e2) {
                e2.printStackTrace();
            } catch (SAXException e3) {
                e3.printStackTrace();
            } catch (IOException e4) {
                e4.printStackTrace();
            } catch (Throwable th) {
                th.printStackTrace();
            }
            return f3180a;
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    private static List<ScanResult> m4888a(List<ScanResult> list) {
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

    static String m4891b(Context context) {
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
                        List a = C0500z.m4888a(scanResults);
                        Object obj = 1;
                        int i = 0;
                        while (i < a.size() && i < 10) {
                            ScanResult scanResult = (ScanResult) a.get(i);
                            if (obj != null) {
                                obj = null;
                            } else {
                                stringBuilder.append("||");
                            }
                            stringBuilder.append(scanResult.BSSID);
                            i++;
                        }
                    }
                    return stringBuilder.toString();
                }
            } catch (Throwable th) {
                ay.m4590a(th, "DeviceInfo", "getWifiMacs");
                th.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }

    static String m4892c(Context context) {
        try {
            if (f3182c != null && !C2915a.f14760f.equals(f3182c)) {
                return f3182c;
            }
            if (context.checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE") != 0) {
                return f3182c;
            }
            f3182c = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            return f3182c;
        } catch (Throwable th) {
            ay.m4590a(th, "DeviceInfo", "getDeviceMac");
            th.printStackTrace();
        }
    }

    static String m4893d(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            if (context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
                return stringBuilder.toString();
            }
            CellLocation cellLocation = ((TelephonyManager) context.getSystemService("phone")).getCellLocation();
            if (cellLocation instanceof GsmCellLocation) {
                GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                stringBuilder.append(gsmCellLocation.getLac()).append("||").append(gsmCellLocation.getCid()).append("&bt=gsm");
            } else if (cellLocation instanceof CdmaCellLocation) {
                CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
                int systemId = cdmaCellLocation.getSystemId();
                int networkId = cdmaCellLocation.getNetworkId();
                int baseStationId = cdmaCellLocation.getBaseStationId();
                if (systemId < 0 || networkId < 0 || baseStationId < 0) {
                    stringBuilder.append(systemId).append("||").append(networkId).append("||").append(baseStationId).append("&bt=cdma");
                } else {
                    stringBuilder.append(systemId).append("||").append(networkId).append("||").append(baseStationId).append("&bt=cdma");
                }
            }
            return stringBuilder.toString();
        } catch (Throwable th) {
            ay.m4590a(th, "DeviceInfo", "cellInfo");
            th.printStackTrace();
        }
    }

    static String m4894e(Context context) {
        String str = C2915a.f14760f;
        try {
            str = C0500z.m4911v(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return str;
    }

    static int m4895f(Context context) {
        int i = -1;
        try {
            i = C0500z.m4912w(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return i;
    }

    public static int m4896g(Context context) {
        int i = -1;
        try {
            i = C0500z.m4910u(context);
        } catch (Throwable th) {
            ay.m4590a(th, "DeviceInfo", "getActiveNetWorkType");
            th.printStackTrace();
        }
        return i;
    }

    static int m4897h(Context context) {
        int i = -1;
        try {
            i = C0500z.m4910u(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return i;
    }

    static String m4898i(Context context) {
        String extraInfo;
        try {
            if (context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
                return null;
            }
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return null;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return null;
            }
            extraInfo = activeNetworkInfo.getExtraInfo();
            return extraInfo;
        } catch (Throwable th) {
            ay.m4590a(th, "DeviceInfo", "getNetworkExtraInfo");
            th.printStackTrace();
            extraInfo = null;
        }
    }

    static String m4899j(Context context) {
        try {
            if (f3183d != null && !C2915a.f14760f.equals(f3183d)) {
                return f3183d;
            }
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            int i2 = displayMetrics.heightPixels;
            f3183d = i2 > i ? i + "*" + i2 : i2 + "*" + i;
            return f3183d;
        } catch (Throwable th) {
            ay.m4590a(th, "DeviceInfo", "getReslution");
            th.printStackTrace();
        }
    }

    static String m4900k(Context context) {
        try {
            return C0500z.m4909t(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    static String m4901l(Context context) {
        try {
            return C0500z.m4909t(context);
        } catch (Throwable th) {
            ay.m4590a(th, "DeviceInfo", "getActiveNetworkTypeName");
            th.printStackTrace();
            return null;
        }
    }

    static String m4902m(Context context) {
        try {
            if (f3184e != null && !C2915a.f14760f.equals(f3184e)) {
                return f3184e;
            }
            if (context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
                return f3184e;
            }
            f3184e = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            return f3184e;
        } catch (Throwable th) {
            ay.m4590a(th, "DeviceInfo", "getDeviceID");
            th.printStackTrace();
        }
    }

    static String m4903n(Context context) {
        String str = null;
        try {
            str = C0500z.m4907r(context);
        } catch (Throwable th) {
            ay.m4590a(th, "DeviceInfo", "getSubscriberId");
            th.printStackTrace();
        }
        return str;
    }

    static String m4904o(Context context) {
        try {
            return C0500z.m4908s(context);
        } catch (Throwable th) {
            ay.m4590a(th, "DeviceInfo", "getNetworkOperatorName");
            th.printStackTrace();
            return null;
        }
    }

    static String m4905p(Context context) {
        try {
            return C0500z.m4908s(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    static String m4906q(Context context) {
        try {
            return C0500z.m4907r(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private static String m4907r(Context context) {
        if (f3185f != null && !C2915a.f14760f.equals(f3185f)) {
            return f3185f;
        }
        if (context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
            return f3185f;
        }
        f3185f = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
        return f3185f;
    }

    private static String m4908s(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") != 0 ? null : ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName();
    }

    private static String m4909t(Context context) {
        if (context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
            return C2915a.f14760f;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return C2915a.f14760f;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo == null ? C2915a.f14760f : activeNetworkInfo.getTypeName();
    }

    private static int m4910u(Context context) {
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

    private static String m4911v(Context context) {
        String str = C2915a.f14760f;
        String n = C0500z.m4903n(context);
        return (n == null || n.length() < 5) ? str : n.substring(3, 5);
    }

    private static int m4912w(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") != 0 ? -1 : ((TelephonyManager) context.getSystemService("phone")).getNetworkType();
    }
}
