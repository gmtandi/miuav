package com.p016a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.GpsStatus.NmeaListener;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Looper;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.fimi.soul.module.setting.newhand.C1873o;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.hoho.android.usbserial.driver.FtdiSerialDriver;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.platformtools.Util;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TreeMap;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

/* renamed from: com.a.eg */
public final class eg {
    private static int f1072I;
    private static String[] f1073J;
    static String f1074a;
    protected static boolean f1075b;
    protected static boolean f1076c;
    private static eg f1077z;
    private ei f1078A;
    private ej f1079B;
    private CellLocation f1080C;
    private ek f1081D;
    private List f1082E;
    private Timer f1083F;
    private Thread f1084G;
    private Looper f1085H;
    Object f1086d;
    boolean f1087e;
    private Context f1088f;
    private TelephonyManager f1089g;
    private LocationManager f1090h;
    private WifiManager f1091i;
    private SensorManager f1092j;
    private String f1093k;
    private String f1094l;
    private String f1095m;
    private boolean f1096n;
    private int f1097o;
    private boolean f1098p;
    private long f1099q;
    private String f1100r;
    private String f1101s;
    private int f1102t;
    private int f1103u;
    private int f1104v;
    private String f1105w;
    private long f1106x;
    private long f1107y;

    static {
        f1077z = null;
        f1072I = C1873o.ak;
        f1074a = C2915a.f14760f;
        f1075b = true;
        f1076c = false;
        f1073J = new String[]{"android.permission.READ_PHONE_STATE", "android.permission.ACCESS_WIFI_STATE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.CHANGE_WIFI_STATE", "android.permission.ACCESS_NETWORK_STATE"};
    }

    private eg(Context context) {
        this.f1088f = null;
        this.f1089g = null;
        this.f1090h = null;
        this.f1091i = null;
        this.f1092j = null;
        this.f1093k = C2915a.f14760f;
        this.f1094l = C2915a.f14760f;
        this.f1095m = C2915a.f14760f;
        this.f1096n = false;
        this.f1097o = 0;
        this.f1098p = false;
        this.f1099q = -1;
        this.f1100r = C2915a.f14760f;
        this.f1101s = C2915a.f14760f;
        this.f1102t = 0;
        this.f1103u = 0;
        this.f1104v = 0;
        this.f1105w = C2915a.f14760f;
        this.f1106x = 0;
        this.f1107y = 0;
        this.f1078A = null;
        this.f1079B = null;
        this.f1080C = null;
        this.f1081D = null;
        this.f1082E = new ArrayList();
        this.f1083F = null;
        this.f1084G = null;
        this.f1085H = null;
        this.f1086d = new Object();
        this.f1087e = false;
        if (context != null) {
            this.f1088f = context;
            this.f1093k = Build.MODEL;
            this.f1089g = (TelephonyManager) context.getSystemService("phone");
            this.f1090h = (LocationManager) context.getSystemService("location");
            this.f1091i = (WifiManager) context.getSystemService("wifi");
            this.f1092j = (SensorManager) context.getSystemService("sensor");
            if (this.f1089g != null && this.f1091i != null) {
                try {
                    this.f1094l = this.f1089g.getDeviceId();
                } catch (Exception e) {
                }
                this.f1095m = this.f1089g.getSubscriberId();
                if (this.f1091i.getConnectionInfo() != null) {
                    this.f1101s = this.f1091i.getConnectionInfo().getMacAddress();
                    if (this.f1101s != null && this.f1101s.length() > 0) {
                        this.f1101s = this.f1101s.replace(":", C2915a.f14760f);
                    }
                }
                String[] b = eg.m1698b(this.f1089g);
                this.f1102t = Integer.parseInt(b[0]);
                this.f1103u = Integer.parseInt(b[1]);
                this.f1104v = this.f1089g.getNetworkType();
                this.f1105w = context.getPackageName();
                this.f1096n = this.f1089g.getPhoneType() == 2;
            }
        }
    }

    private CellLocation m1665A() {
        if (this.f1089g == null) {
            return null;
        }
        CellLocation b;
        try {
            b = eg.m1692b((List) ed.m1645a(this.f1089g, "getAllCellInfo", new Object[0]));
        } catch (NoSuchMethodException e) {
            b = null;
        } catch (Exception e2) {
            b = null;
        }
        return b;
    }

    private static int m1666a(CellLocation cellLocation, Context context) {
        if (System.getInt(context.getContentResolver(), "airplane_mode_on", 0) == 1 || cellLocation == null) {
            return 9;
        }
        if (cellLocation instanceof GsmCellLocation) {
            return 1;
        }
        try {
            Class.forName("android.telephony.cdma.CdmaCellLocation");
            return 2;
        } catch (Exception e) {
            return 9;
        }
    }

    protected static eg m1671a(Context context) {
        if (f1077z == null && eg.m1702c(context)) {
            Object obj;
            LocationManager locationManager = (LocationManager) context.getSystemService("location");
            if (locationManager != null) {
                for (String str : locationManager.getAllProviders()) {
                    if (!str.equals("passive")) {
                        if (str.equals(GeocodeSearch.GPS)) {
                        }
                    }
                    obj = 1;
                    break;
                }
            }
            obj = null;
            if (obj != null) {
                f1077z = new eg(context);
            }
        }
        return f1077z;
    }

    private void m1677a(BroadcastReceiver broadcastReceiver) {
        if (broadcastReceiver != null && this.f1088f != null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
            this.f1088f.registerReceiver(broadcastReceiver, intentFilter);
        }
    }

    private void m1678a(NmeaListener nmeaListener) {
        if (this.f1090h != null && nmeaListener != null) {
            this.f1090h.removeNmeaListener(nmeaListener);
        }
    }

    private static void m1679a(WifiManager wifiManager) {
        if (wifiManager != null) {
            try {
                ed.m1645a(wifiManager, "startScanActive", new Object[0]);
            } catch (Exception e) {
                wifiManager.startScan();
            }
        }
    }

    private void m1680a(PhoneStateListener phoneStateListener) {
        if (this.f1089g != null) {
            this.f1089g.listen(phoneStateListener, 0);
        }
    }

    static /* synthetic */ void m1681a(eg egVar, NmeaListener nmeaListener) {
        if (egVar.f1090h != null && nmeaListener != null) {
            egVar.f1090h.addNmeaListener(nmeaListener);
        }
    }

    static /* synthetic */ void m1683a(eg egVar, PhoneStateListener phoneStateListener) {
        if (egVar.f1089g != null) {
            egVar.f1089g.listen(phoneStateListener, 273);
        }
    }

    private static void m1684a(List list) {
        if (list != null && list.size() > 0) {
            Object hashMap = new HashMap();
            for (int i = 0; i < list.size(); i++) {
                ScanResult scanResult = (ScanResult) list.get(i);
                if (scanResult.SSID == null) {
                    scanResult.SSID = "null";
                }
                hashMap.put(Integer.valueOf(scanResult.level), scanResult);
            }
            TreeMap treeMap = new TreeMap(Collections.reverseOrder());
            treeMap.putAll(hashMap);
            list.clear();
            for (Object obj : treeMap.keySet()) {
                list.add(treeMap.get(obj));
            }
            hashMap.clear();
            treeMap.clear();
        }
    }

    private boolean m1685a(CellLocation cellLocation) {
        if (cellLocation == null) {
            return false;
        }
        switch (eg.m1666a(cellLocation, this.f1088f)) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                if (gsmCellLocation.getLac() == -1 || gsmCellLocation.getLac() == 0 || gsmCellLocation.getLac() > Util.MASK_16BIT || gsmCellLocation.getCid() == -1 || gsmCellLocation.getCid() == 0 || gsmCellLocation.getCid() == Util.MASK_16BIT) {
                    return false;
                }
                if (gsmCellLocation.getCid() >= 268435455) {
                    return false;
                }
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                try {
                    if (ed.m1650b(cellLocation, "getSystemId", new Object[0]) <= 0 || ed.m1650b(cellLocation, "getNetworkId", new Object[0]) < 0) {
                        return false;
                    }
                    if (ed.m1650b(cellLocation, "getBaseStationId", new Object[0]) < 0) {
                        return false;
                    }
                } catch (Exception e) {
                    break;
                }
                break;
        }
        return true;
    }

    private static boolean m1687a(Object obj) {
        try {
            Method declaredMethod = WifiManager.class.getDeclaredMethod("isScanAlwaysAvailable", null);
            if (declaredMethod != null) {
                return ((Boolean) declaredMethod.invoke(obj, null)).booleanValue();
            }
        } catch (Exception e) {
        }
        return false;
    }

    private static int m1690b(Object obj) {
        try {
            Method declaredMethod = Sensor.class.getDeclaredMethod("getMinDelay", null);
            if (declaredMethod != null) {
                return ((Integer) declaredMethod.invoke(obj, null)).intValue();
            }
        } catch (Exception e) {
        }
        return 0;
    }

    private static CellLocation m1692b(List list) {
        int i;
        if (list == null || list.isEmpty()) {
            return null;
        }
        CellLocation cellLocation;
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        CellLocation cellLocation2 = null;
        int i2 = 0;
        int i3 = 0;
        CellLocation cellLocation3 = null;
        while (i2 < list.size()) {
            CellLocation cellLocation4;
            Object obj = list.get(i2);
            if (obj != null) {
                try {
                    Class loadClass = systemClassLoader.loadClass("android.telephony.CellInfoGsm");
                    Class loadClass2 = systemClassLoader.loadClass("android.telephony.CellInfoWcdma");
                    Class loadClass3 = systemClassLoader.loadClass("android.telephony.CellInfoLte");
                    Class loadClass4 = systemClassLoader.loadClass("android.telephony.CellInfoCdma");
                    i = loadClass.isInstance(obj) ? 1 : loadClass2.isInstance(obj) ? 2 : loadClass3.isInstance(obj) ? 3 : loadClass4.isInstance(obj) ? 4 : 0;
                    if (i > 0) {
                        Object obj2 = null;
                        if (i == 1) {
                            try {
                                obj2 = loadClass.cast(obj);
                            } catch (Exception e) {
                                i3 = i;
                                cellLocation4 = cellLocation2;
                            }
                        } else if (i == 2) {
                            obj2 = loadClass2.cast(obj);
                        } else if (i == 3) {
                            obj2 = loadClass3.cast(obj);
                        } else if (i == 4) {
                            obj2 = loadClass4.cast(obj);
                        }
                        obj = ed.m1645a(obj2, "getCellIdentity", new Object[0]);
                        if (obj != null) {
                            if (i != 4) {
                                int b;
                                if (i != 3) {
                                    i3 = ed.m1650b(obj, "getLac", new Object[0]);
                                    b = ed.m1650b(obj, "getCid", new Object[0]);
                                    cellLocation4 = new GsmCellLocation();
                                    cellLocation4.setLacAndCid(i3, b);
                                    cellLocation = cellLocation3;
                                    cellLocation3 = cellLocation4;
                                    break;
                                }
                                i3 = ed.m1650b(obj, "getTac", new Object[0]);
                                b = ed.m1650b(obj, "getCi", new Object[0]);
                                cellLocation4 = new GsmCellLocation();
                                try {
                                    cellLocation4.setLacAndCid(i3, b);
                                    cellLocation = cellLocation3;
                                    cellLocation3 = cellLocation4;
                                    break;
                                } catch (Exception e2) {
                                    i3 = i;
                                }
                            } else {
                                cellLocation = new CdmaCellLocation();
                                try {
                                    cellLocation.setCellLocationData(ed.m1650b(obj, "getBasestationId", new Object[0]), ed.m1650b(obj, "getLatitude", new Object[0]), ed.m1650b(obj, "getLongitude", new Object[0]), ed.m1650b(obj, "getSystemId", new Object[0]), ed.m1650b(obj, "getNetworkId", new Object[0]));
                                    cellLocation3 = cellLocation2;
                                    break;
                                } catch (Exception e3) {
                                    cellLocation3 = cellLocation;
                                    cellLocation4 = cellLocation2;
                                    i3 = i;
                                }
                            }
                        } else {
                            i3 = i;
                            cellLocation4 = cellLocation2;
                        }
                    } else {
                        i3 = i;
                        cellLocation4 = cellLocation2;
                    }
                } catch (Exception e4) {
                    cellLocation4 = cellLocation2;
                }
            } else {
                cellLocation4 = cellLocation2;
            }
            i2++;
            cellLocation2 = cellLocation4;
        }
        i = i3;
        cellLocation = cellLocation3;
        cellLocation3 = cellLocation2;
        return i != 4 ? cellLocation3 : cellLocation;
    }

    private void m1694b(BroadcastReceiver broadcastReceiver) {
        if (broadcastReceiver != null && this.f1088f != null) {
            try {
                this.f1088f.unregisterReceiver(broadcastReceiver);
            } catch (Exception e) {
            }
        }
    }

    protected static boolean m1697b(Context context) {
        if (context == null) {
            return true;
        }
        boolean z;
        if (!Secure.getString(context.getContentResolver(), "mock_location").equals(Constants.VIA_RESULT_SUCCESS)) {
            PackageManager packageManager = context.getPackageManager();
            List<ApplicationInfo> installedApplications = packageManager.getInstalledApplications(SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
            String str = "android.permission.ACCESS_MOCK_LOCATION";
            String packageName = context.getPackageName();
            z = false;
            for (ApplicationInfo applicationInfo : installedApplications) {
                if (z) {
                    break;
                }
                boolean z2;
                try {
                    String[] strArr = packageManager.getPackageInfo(applicationInfo.packageName, Opcodes.ACC_SYNTHETIC).requestedPermissions;
                    if (strArr != null) {
                        int length = strArr.length;
                        int i = 0;
                        while (i < length) {
                            if (!strArr[i].equals(str)) {
                                i++;
                            } else if (!applicationInfo.packageName.equals(packageName)) {
                                z2 = true;
                                z = z2;
                            }
                        }
                    }
                } catch (Exception e) {
                    z2 = z;
                }
            }
        } else {
            z = false;
        }
        return z;
    }

    private static String[] m1698b(TelephonyManager telephonyManager) {
        int i = 0;
        String str = null;
        if (telephonyManager != null) {
            str = telephonyManager.getNetworkOperator();
        }
        String[] strArr = new String[]{Constants.VIA_RESULT_SUCCESS, Constants.VIA_RESULT_SUCCESS};
        if (TextUtils.isDigitsOnly(str) && str.length() > 4) {
            strArr[0] = str.substring(0, 3);
            char[] toCharArray = str.substring(3).toCharArray();
            while (i < toCharArray.length && Character.isDigit(toCharArray[i])) {
                i++;
            }
            strArr[1] = str.substring(3, i + 3);
        }
        return strArr;
    }

    private static boolean m1702c(Context context) {
        try {
            String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), Opcodes.ACC_SYNTHETIC).requestedPermissions;
            for (Object obj : f1073J) {
                boolean z;
                if (!(strArr == null || obj == null)) {
                    for (String equals : strArr) {
                        if (equals.equals(obj)) {
                            z = true;
                            break;
                        }
                    }
                }
                z = false;
                if (!z) {
                    return false;
                }
            }
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    private void m1710z() {
        if (this.f1091i != null) {
            try {
                if (f1075b) {
                    eg.m1679a(this.f1091i);
                }
            } catch (Exception e) {
            }
        }
    }

    protected final List m1711a(float f) {
        List arrayList = new ArrayList();
        long currentTimeMillis = System.currentTimeMillis();
        if (Math.abs(f) <= C2020f.f10933c) {
            f = C2020f.f10933c;
        }
        if (m1719c()) {
            CellLocation cellLocation = (CellLocation) m1732j().get(1);
            if (cellLocation != null && (cellLocation instanceof GsmCellLocation)) {
                arrayList.add(Integer.valueOf(((GsmCellLocation) cellLocation).getLac()));
                arrayList.add(Integer.valueOf(((GsmCellLocation) cellLocation).getCid()));
                if (((double) (currentTimeMillis - ((Long) m1732j().get(0)).longValue())) <= 50000.0d / ((double) f)) {
                    arrayList.add(Integer.valueOf(1));
                } else {
                    arrayList.add(Integer.valueOf(0));
                }
            }
        }
        return arrayList;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected final java.util.List m1712a(boolean r9) {
        /*
        r8 = this;
        r1 = 0;
        r3 = new java.util.ArrayList;
        r3.<init>();
        r0 = r8.m1721d();
        if (r0 == 0) goto L_0x004b;
    L_0x000c:
        r0 = new java.util.ArrayList;
        r0.<init>();
        monitor-enter(r8);
        if (r9 != 0) goto L_0x0024;
    L_0x0014:
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x0048 }
        r6 = r8.f1106x;	 Catch:{ all -> 0x0048 }
        r4 = r4 - r6;
        r6 = 3500; // 0xdac float:4.905E-42 double:1.729E-320;
        r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r2 >= 0) goto L_0x0041;
    L_0x0021:
        r2 = 1;
    L_0x0022:
        if (r2 == 0) goto L_0x0046;
    L_0x0024:
        r4 = r8.f1106x;	 Catch:{ all -> 0x0048 }
        r2 = java.lang.Long.valueOf(r4);	 Catch:{ all -> 0x0048 }
        r0.add(r2);	 Catch:{ all -> 0x0048 }
    L_0x002d:
        r2 = r8.f1082E;	 Catch:{ all -> 0x0048 }
        r2 = r2.size();	 Catch:{ all -> 0x0048 }
        if (r1 >= r2) goto L_0x0043;
    L_0x0035:
        r2 = r8.f1082E;	 Catch:{ all -> 0x0048 }
        r2 = r2.get(r1);	 Catch:{ all -> 0x0048 }
        r3.add(r2);	 Catch:{ all -> 0x0048 }
        r1 = r1 + 1;
        goto L_0x002d;
    L_0x0041:
        r2 = r1;
        goto L_0x0022;
    L_0x0043:
        r0.add(r3);	 Catch:{ all -> 0x0048 }
    L_0x0046:
        monitor-exit(r8);	 Catch:{ all -> 0x0048 }
    L_0x0047:
        return r0;
    L_0x0048:
        r0 = move-exception;
        monitor-exit(r8);
        throw r0;
    L_0x004b:
        r0 = new java.util.ArrayList;
        r0.<init>();
        goto L_0x0047;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.eg.a(boolean):java.util.List");
    }

    protected final void m1713a() {
        String str = C2915a.f14760f;
        m1717b();
        this.f1087e = true;
        this.f1084G = new eh(this, str);
        this.f1084G.start();
    }

    protected final void m1714a(int i) {
        if (i != f1072I) {
            synchronized (this) {
                this.f1082E.clear();
            }
            if (this.f1081D != null) {
                m1694b(this.f1081D);
                this.f1081D = null;
            }
            if (this.f1083F != null) {
                this.f1083F.cancel();
                this.f1083F = null;
            }
            if (i >= FtdiSerialDriver.USB_WRITE_TIMEOUT_MILLIS) {
                f1072I = i;
                this.f1083F = new Timer();
                this.f1081D = new ek();
                m1677a(this.f1081D);
                m1710z();
            }
        }
    }

    protected final String m1715b(int i) {
        ArrayList arrayList = new ArrayList();
        if (this.f1092j == null) {
            return "null";
        }
        List sensorList = this.f1092j.getSensorList(-1);
        return (sensorList == null || sensorList.get(i) == null || ((Sensor) sensorList.get(i)).getName() == null || ((Sensor) sensorList.get(i)).getName().length() <= 0) ? "null" : ((Sensor) sensorList.get(i)).getName();
    }

    protected final List m1716b(float f) {
        List arrayList = new ArrayList();
        long currentTimeMillis = System.currentTimeMillis();
        if (Math.abs(f) <= C2020f.f10933c) {
            f = C2020f.f10933c;
        }
        if (m1719c()) {
            CellLocation cellLocation = (CellLocation) m1732j().get(1);
            if (cellLocation != null && (cellLocation instanceof CdmaCellLocation)) {
                CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
                arrayList.add(Integer.valueOf(cdmaCellLocation.getSystemId()));
                arrayList.add(Integer.valueOf(cdmaCellLocation.getNetworkId()));
                arrayList.add(Integer.valueOf(cdmaCellLocation.getBaseStationId()));
                arrayList.add(Integer.valueOf(cdmaCellLocation.getBaseStationLongitude()));
                arrayList.add(Integer.valueOf(cdmaCellLocation.getBaseStationLatitude()));
                if (((double) (currentTimeMillis - ((Long) m1732j().get(0)).longValue())) <= 50000.0d / ((double) f)) {
                    arrayList.add(Integer.valueOf(1));
                } else {
                    arrayList.add(Integer.valueOf(0));
                }
            }
        }
        return arrayList;
    }

    protected final void m1717b() {
        synchronized (this.f1086d) {
            this.f1087e = false;
            if (this.f1078A != null) {
                m1680a(this.f1078A);
                this.f1078A = null;
            }
            if (this.f1079B != null) {
                m1678a(this.f1079B);
                this.f1079B = null;
            }
            if (this.f1083F != null) {
                this.f1083F.cancel();
                this.f1083F = null;
            }
            if (this.f1085H != null) {
                this.f1085H.quit();
                this.f1085H = null;
            }
            if (this.f1084G != null) {
                this.f1084G.interrupt();
                this.f1084G = null;
            }
        }
    }

    protected final double m1718c(int i) {
        ArrayList arrayList = new ArrayList();
        if (this.f1092j == null) {
            return 0.0d;
        }
        List sensorList = this.f1092j.getSensorList(-1);
        return (sensorList == null || sensorList.get(i) == null) ? 0.0d : (double) ((Sensor) sensorList.get(i)).getMaximumRange();
    }

    protected final boolean m1719c() {
        CellLocation cellLocation = null;
        if (this.f1089g != null && this.f1089g.getSimState() == 5 && this.f1098p) {
            return true;
        }
        if (this.f1089g != null) {
            try {
                cellLocation = this.f1089g.getCellLocation();
            } catch (Exception e) {
            }
            if (cellLocation != null) {
                this.f1107y = System.currentTimeMillis();
                this.f1080C = cellLocation;
                return true;
            }
        }
        return false;
    }

    protected final int m1720d(int i) {
        ArrayList arrayList = new ArrayList();
        if (this.f1092j == null) {
            return 0;
        }
        List sensorList = this.f1092j.getSensorList(-1);
        return (sensorList == null || sensorList.get(i) == null) ? 0 : eg.m1690b(sensorList.get(i));
    }

    protected final boolean m1721d() {
        return this.f1091i != null && (this.f1091i.isWifiEnabled() || eg.m1687a(this.f1091i));
    }

    protected final int m1722e(int i) {
        ArrayList arrayList = new ArrayList();
        if (this.f1092j == null) {
            return 0;
        }
        List sensorList = this.f1092j.getSensorList(-1);
        return (sensorList == null || sensorList.get(i) == null) ? 0 : (int) (((double) ((Sensor) sensorList.get(i)).getPower()) * 100.0d);
    }

    protected final boolean m1723e() {
        try {
            if (this.f1090h != null && this.f1090h.isProviderEnabled(GeocodeSearch.GPS)) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    protected final double m1724f(int i) {
        ArrayList arrayList = new ArrayList();
        if (this.f1092j == null) {
            return 0.0d;
        }
        List sensorList = this.f1092j.getSensorList(-1);
        return (sensorList == null || sensorList.get(i) == null) ? 0.0d : (double) ((Sensor) sensorList.get(i)).getResolution();
    }

    protected final String m1725f() {
        if (this.f1093k == null) {
            this.f1093k = Build.MODEL;
        }
        return this.f1093k != null ? this.f1093k : C2915a.f14760f;
    }

    protected final byte m1726g(int i) {
        ArrayList arrayList = new ArrayList();
        if (this.f1092j == null) {
            return Byte.MAX_VALUE;
        }
        List sensorList = this.f1092j.getSensorList(-1);
        return (sensorList == null || sensorList.get(i) == null || ((Sensor) sensorList.get(i)).getType() > Opcodes.LAND) ? Byte.MAX_VALUE : (byte) ((Sensor) sensorList.get(i)).getType();
    }

    protected final String m1727g() {
        if (this.f1094l == null && this.f1088f != null) {
            this.f1089g = (TelephonyManager) this.f1088f.getSystemService("phone");
            if (this.f1089g != null) {
                try {
                    this.f1094l = this.f1089g.getDeviceId();
                } catch (Exception e) {
                }
            }
        }
        return this.f1094l != null ? this.f1094l : C2915a.f14760f;
    }

    protected final String m1728h() {
        if (this.f1095m == null && this.f1088f != null) {
            this.f1089g = (TelephonyManager) this.f1088f.getSystemService("phone");
            if (this.f1089g != null) {
                this.f1095m = this.f1089g.getSubscriberId();
            }
        }
        return this.f1095m != null ? this.f1095m : C2915a.f14760f;
    }

    protected final String m1729h(int i) {
        ArrayList arrayList = new ArrayList();
        if (this.f1092j == null) {
            return "null";
        }
        List sensorList = this.f1092j.getSensorList(-1);
        return (sensorList == null || sensorList.get(i) == null || ((Sensor) sensorList.get(i)).getVendor() == null || ((Sensor) sensorList.get(i)).getVendor().length() <= 0) ? "null" : ((Sensor) sensorList.get(i)).getVendor();
    }

    protected final byte m1730i(int i) {
        ArrayList arrayList = new ArrayList();
        if (this.f1092j == null) {
            return Byte.MAX_VALUE;
        }
        List sensorList = this.f1092j.getSensorList(-1);
        return (sensorList == null || sensorList.get(i) == null || ((Sensor) sensorList.get(i)).getType() > Opcodes.LAND) ? Byte.MAX_VALUE : (byte) ((Sensor) sensorList.get(i)).getVersion();
    }

    protected final boolean m1731i() {
        return this.f1096n;
    }

    protected final List m1732j() {
        if (System.getInt(this.f1088f.getContentResolver(), "airplane_mode_on", 0) == 1) {
            return new ArrayList();
        }
        if (!m1719c()) {
            return new ArrayList();
        }
        Object A;
        List arrayList = new ArrayList();
        if (!m1685a(this.f1080C)) {
            A = m1665A();
            if (m1685a((CellLocation) A)) {
                this.f1107y = System.currentTimeMillis();
                arrayList.add(Long.valueOf(this.f1107y));
                arrayList.add(A);
                return arrayList;
            }
        }
        A = this.f1080C;
        arrayList.add(Long.valueOf(this.f1107y));
        arrayList.add(A);
        return arrayList;
    }

    protected final byte m1733k() {
        return m1719c() ? (byte) this.f1097o : Byte.MIN_VALUE;
    }

    protected final List m1734l() {
        List arrayList = new ArrayList();
        if (this.f1089g == null) {
            return arrayList;
        }
        if (!m1719c()) {
            return arrayList;
        }
        if (this.f1089g.getSimState() == 1) {
            return arrayList;
        }
        int i = 0;
        for (NeighboringCellInfo neighboringCellInfo : this.f1089g.getNeighboringCellInfo()) {
            if (i > 15) {
                break;
            } else if (!(neighboringCellInfo.getLac() == 0 || neighboringCellInfo.getLac() == Util.MASK_16BIT || neighboringCellInfo.getCid() == Util.MASK_16BIT || neighboringCellInfo.getCid() == 268435455)) {
                arrayList.add(neighboringCellInfo);
                i++;
            }
        }
        return arrayList;
    }

    protected final List m1735m() {
        long j;
        Object obj;
        List arrayList = new ArrayList();
        String str = C2915a.f14760f;
        if (m1723e()) {
            long j2 = this.f1099q;
            j = j2;
            obj = this.f1100r;
        } else {
            String str2 = str;
            j = -1;
            String str3 = str2;
        }
        if (j <= 0) {
            j = System.currentTimeMillis() / 1000;
        }
        if (j > 2147483647L) {
            j /= 1000;
        }
        arrayList.add(Long.valueOf(j));
        arrayList.add(obj);
        return arrayList;
    }

    protected final long m1736n() {
        long j = 0;
        long j2 = this.f1099q;
        if (j2 > 0) {
            j = j2;
            int length = String.valueOf(j2).length();
            while (length != 13) {
                j = length > 13 ? j / 10 : j * 10;
                length = String.valueOf(j).length();
            }
        }
        return j;
    }

    protected final String m1737o() {
        if (this.f1101s == null && this.f1088f != null) {
            this.f1091i = (WifiManager) this.f1088f.getSystemService("wifi");
            if (!(this.f1091i == null || this.f1091i.getConnectionInfo() == null)) {
                this.f1101s = this.f1091i.getConnectionInfo().getMacAddress();
                if (this.f1101s != null && this.f1101s.length() > 0) {
                    this.f1101s = this.f1101s.replace(":", C2915a.f14760f);
                }
            }
        }
        return this.f1101s != null ? this.f1101s : C2915a.f14760f;
    }

    protected final int m1738p() {
        return this.f1102t;
    }

    protected final int m1739q() {
        return this.f1103u;
    }

    protected final int m1740r() {
        return this.f1104v;
    }

    protected final String m1741s() {
        if (this.f1105w == null && this.f1088f != null) {
            this.f1105w = this.f1088f.getPackageName();
        }
        return this.f1105w != null ? this.f1105w : C2915a.f14760f;
    }

    protected final List m1742t() {
        int i = 0;
        List arrayList = new ArrayList();
        if (m1721d()) {
            List a = m1712a(true);
            List list = (List) a.get(1);
            long longValue = ((Long) a.get(0)).longValue();
            eg.m1684a(list);
            arrayList.add(Long.valueOf(longValue));
            if (list != null && list.size() > 0) {
                while (i < list.size()) {
                    ScanResult scanResult = (ScanResult) list.get(i);
                    if (arrayList.size() - 1 >= 40) {
                        break;
                    }
                    if (scanResult != null) {
                        List arrayList2 = new ArrayList();
                        arrayList2.add(scanResult.BSSID.replace(":", C2915a.f14760f));
                        arrayList2.add(Integer.valueOf(scanResult.level));
                        arrayList2.add(scanResult.SSID);
                        arrayList.add(arrayList2);
                    }
                    i++;
                }
            }
        }
        return arrayList;
    }

    protected final void m1743u() {
        synchronized (this) {
            this.f1082E.clear();
        }
        if (this.f1081D != null) {
            m1694b(this.f1081D);
            this.f1081D = null;
        }
        if (this.f1083F != null) {
            this.f1083F.cancel();
            this.f1083F = null;
        }
        this.f1083F = new Timer();
        this.f1081D = new ek();
        m1677a(this.f1081D);
        m1710z();
    }

    protected final void m1744v() {
        synchronized (this) {
            this.f1082E.clear();
        }
        if (this.f1081D != null) {
            m1694b(this.f1081D);
            this.f1081D = null;
        }
        if (this.f1083F != null) {
            this.f1083F.cancel();
            this.f1083F = null;
        }
    }

    protected final byte m1745w() {
        ArrayList arrayList = new ArrayList();
        if (this.f1092j == null) {
            return (byte) 0;
        }
        List sensorList = this.f1092j.getSensorList(-1);
        return sensorList != null ? (byte) sensorList.size() : (byte) 0;
    }

    protected final Context m1746x() {
        return this.f1088f;
    }
}
