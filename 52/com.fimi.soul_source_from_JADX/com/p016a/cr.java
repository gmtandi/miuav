package com.p016a;

import android.content.ContentResolver;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import java.util.List;
import org.json.JSONObject;

/* renamed from: com.a.cr */
public class cr {
    private WifiManager f786a;
    private JSONObject f787b;
    private Context f788c;

    public cr(Context context, WifiManager wifiManager, JSONObject jSONObject) {
        this.f786a = wifiManager;
        this.f787b = jSONObject;
        this.f788c = context;
    }

    private boolean m1364a(WifiInfo wifiInfo) {
        return (wifiInfo == null || TextUtils.isEmpty(wifiInfo.getBSSID()) || wifiInfo.getSSID() == null || wifiInfo.getBSSID().equals("00:00:00:00:00:00") || wifiInfo.getBSSID().contains(" :") || TextUtils.isEmpty(wifiInfo.getSSID())) ? false : true;
    }

    public List<ScanResult> m1365a() {
        List<ScanResult> list = null;
        try {
            if (this.f786a != null) {
                list = this.f786a.getScanResults();
            }
        } catch (Throwable th) {
            ev.m1777a(th, "WifiManagerWrapper", "getScanResults");
        }
        return list;
    }

    public void m1366a(JSONObject jSONObject) {
        this.f787b = jSONObject;
    }

    public void m1367a(boolean z) {
        Context context = this.f788c;
        if (this.f786a != null && context != null && z && dn.m1526c() > 17) {
            String str = "autoenablewifialwaysscan";
            if (dn.m1514a(this.f787b, str)) {
                try {
                    if (Constants.VIA_RESULT_SUCCESS.equals(this.f787b.getString(str))) {
                        return;
                    }
                } catch (Throwable th) {
                    ev.m1777a(th, "WifiManagerWrapper", "enableWifiAlwaysScan1");
                }
            }
            ContentResolver contentResolver = context.getContentResolver();
            String str2 = "android.provider.Settings$Global";
            try {
                if (((Integer) dl.m1488a(str2, "getInt", new Object[]{contentResolver, "wifi_scan_always_enabled"}, new Class[]{ContentResolver.class, String.class})).intValue() == 0) {
                    dl.m1488a(str2, "putInt", new Object[]{contentResolver, "wifi_scan_always_enabled", Integer.valueOf(1)}, new Class[]{ContentResolver.class, String.class, Integer.TYPE});
                }
            } catch (Throwable th2) {
                ev.m1777a(th2, "WifiManagerWrapper", "enableWifiAlwaysScan");
            }
        }
    }

    public boolean m1368a(ConnectivityManager connectivityManager) {
        boolean z = true;
        WifiManager wifiManager = this.f786a;
        if (wifiManager == null || !m1373f()) {
            return false;
        }
        try {
            if (!(dd.m1441a(connectivityManager.getActiveNetworkInfo()) == 1 && m1364a(wifiManager.getConnectionInfo()))) {
                z = false;
            }
            return z;
        } catch (Throwable th) {
            ev.m1777a(th, "WifiManagerWrapper", "wifiAccess");
            return false;
        }
    }

    public WifiInfo m1369b() {
        return this.f786a != null ? this.f786a.getConnectionInfo() : null;
    }

    public int m1370c() {
        return this.f786a != null ? this.f786a.getWifiState() : 4;
    }

    public boolean m1371d() {
        return this.f786a != null ? this.f786a.startScan() : false;
    }

    public boolean m1372e() {
        try {
            return String.valueOf(dl.m1486a(this.f786a, "startScanActive", new Object[0])).equals("true");
        } catch (Throwable th) {
            ev.m1777a(th, "WifiManagerWrapper", "startScanActive");
            return false;
        }
    }

    public boolean m1373f() {
        boolean z = false;
        WifiManager wifiManager = this.f786a;
        if (wifiManager != null) {
            try {
                z = wifiManager.isWifiEnabled();
            } catch (Throwable th) {
                ev.m1777a(th, "WifiManagerWrapper", "wifiEnabled1");
            }
            if (!z && dn.m1526c() > 17) {
                try {
                    z = String.valueOf(dl.m1486a(wifiManager, "isScanAlwaysAvailable", new Object[0])).equals("true");
                } catch (Throwable th2) {
                    ev.m1777a(th2, "WifiManagerWrapper", "wifiEnabled");
                }
            }
        }
        return z;
    }
}
