package com.tencent.mm.sdk.platformtools;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.tencent.mm.sdk.platformtools.MTimerHandler.CallBack;
import com.tencent.mm.sdk.platformtools.PhoneUtil.MacInfo;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.util.LinkedList;
import java.util.List;
import org.p122a.p123a.C2915a;

public class LBSManager extends BroadcastReceiver {
    public static final String FILTER_GPS = "filter_gps";
    public static final int INVALID_ACC = -1000;
    public static final float INVALID_LAT = -1000.0f;
    public static final float INVALID_LNG = -1000.0f;
    public static final int MM_SOURCE_HARDWARE = 0;
    public static final int MM_SOURCE_NET = 1;
    public static final int MM_SOURCE_REPORT_HARWARE = 3;
    public static final int MM_SOURCE_REPORT_NETWORK = 4;
    private static LocationCache ab;
    private Context f11807R;
    private OnLocationGotListener ac;
    private LocationManager ad;
    private PendingIntent ae;
    private boolean af;
    boolean ag;
    boolean ah;
    boolean ai;
    int aj;
    private MTimerHandler ak;

    /* renamed from: com.tencent.mm.sdk.platformtools.LBSManager.1 */
    class C22701 implements CallBack {
        final /* synthetic */ LBSManager al;

        C22701(LBSManager lBSManager) {
            this.al = lBSManager;
        }

        public boolean onTimerExpired() {
            Log.m13547v("MicroMsg.LBSManager", "get location by GPS failed.");
            this.al.ag = true;
            this.al.start();
            this.al.af = false;
            return false;
        }
    }

    class LocationCache {
        int f11806I;
        float am;
        float an;
        int ao;
        long time;

        LocationCache() {
            this.am = LBSManager.INVALID_LNG;
            this.an = LBSManager.INVALID_LNG;
            this.ao = LBSManager.INVALID_ACC;
            this.f11806I = LBSManager.MM_SOURCE_NET;
        }
    }

    public interface OnLocationGotListener {
        void onLocationGot(float f, float f2, int i, int i2, String str, String str2, boolean z);
    }

    public LBSManager(Context context, OnLocationGotListener onLocationGotListener) {
        this.af = false;
        this.ah = false;
        this.ai = false;
        this.ak = new MTimerHandler(new C22701(this), false);
        this.ac = onLocationGotListener;
        this.ag = false;
        this.aj = MM_SOURCE_HARDWARE;
        this.f11807R = context;
        PhoneUtil.getSignalStrength(context);
        this.ad = (LocationManager) context.getSystemService("location");
        m13535b();
        this.ae = PendingIntent.getBroadcast(context, MM_SOURCE_HARDWARE, new Intent(FILTER_GPS), 134217728);
    }

    private boolean m13535b() {
        if (this.ad == null) {
            return false;
        }
        try {
            this.ad.sendExtraCommand(GeocodeSearch.GPS, "force_xtra_injection", null);
            this.ad.sendExtraCommand(GeocodeSearch.GPS, "force_time_injection", null);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void m13536c() {
        this.ak.stopTimer();
        this.ag = true;
    }

    public static void setLocationCache(float f, float f2, int i, int i2) {
        if (i != 0) {
            Log.m13547v("MicroMsg.LBSManager", "setLocationCache [" + f + MiPushClient.ACCEPT_TIME_SEPARATOR + f2 + "] acc:" + i + " source:" + i2);
            if (ab == null) {
                ab = new LocationCache();
            }
            ab.am = f;
            ab.an = f2;
            ab.ao = i;
            ab.time = System.currentTimeMillis();
            ab.f11806I = i2;
        }
    }

    public String getTelLocation() {
        return PhoneUtil.getCellXml(PhoneUtil.getCellInfoList(this.f11807R));
    }

    public String getWIFILocation() {
        WifiManager wifiManager = (WifiManager) this.f11807R.getSystemService("wifi");
        if (wifiManager == null) {
            Log.m13541e("MicroMsg.LBSManager", "no wifi service");
            return C2915a.f14760f;
        } else if (wifiManager.getConnectionInfo() == null) {
            Log.m13541e("MicroMsg.LBSManager", "WIFILocation wifi info null");
            return C2915a.f14760f;
        } else {
            List linkedList = new LinkedList();
            List scanResults = wifiManager.getScanResults();
            if (scanResults != null) {
                for (int i = MM_SOURCE_HARDWARE; i < scanResults.size(); i += MM_SOURCE_NET) {
                    linkedList.add(new MacInfo(((ScanResult) scanResults.get(i)).BSSID, ((ScanResult) scanResults.get(i)).level));
                }
            }
            return PhoneUtil.getMacXml(linkedList);
        }
    }

    public boolean isGpsEnable() {
        try {
            return this.ad.isProviderEnabled(GeocodeSearch.GPS);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isNetworkPrividerEnable() {
        try {
            return this.ad.isProviderEnabled("network");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void onReceive(Context context, Intent intent) {
        Location location = (Location) intent.getExtras().get("location");
        this.aj += MM_SOURCE_NET;
        if (location != null) {
            boolean equals = GeocodeSearch.GPS.equals(location.getProvider());
            if (((equals && location.getAccuracy() <= 200.0f) || (!equals && location.getAccuracy() <= 1000.0f)) && location.getAccuracy() > 0.0f) {
                int i = equals ? MM_SOURCE_HARDWARE : MM_SOURCE_NET;
                setLocationCache((float) location.getLatitude(), (float) location.getLongitude(), (int) location.getAccuracy(), i);
                if (this.ac == null) {
                    return;
                }
                if (!this.ag || !this.ah || !this.ai) {
                    String nullAsNil = Util.nullAsNil(getWIFILocation());
                    String nullAsNil2 = Util.nullAsNil(getTelLocation());
                    if (!this.ag) {
                        m13536c();
                        this.ag = true;
                        Log.m13547v("MicroMsg.LBSManager", "location by provider ok:[" + location.getLatitude() + " , " + location.getLongitude() + "]  accuracy:" + location.getAccuracy() + "  retry count:" + this.aj + " isGpsProvider:" + equals);
                        this.ac.onLocationGot((float) location.getLatitude(), (float) location.getLongitude(), (int) location.getAccuracy(), i, nullAsNil, nullAsNil2, true);
                    } else if (!this.ah && i == 0) {
                        this.ah = true;
                        Log.m13547v("MicroMsg.LBSManager", "report location by GPS ok:[" + location.getLatitude() + " , " + location.getLongitude() + "]  accuracy:" + location.getAccuracy() + "  retry count:" + this.aj + " isGpsProvider:" + equals);
                        this.ac.onLocationGot((float) location.getLatitude(), (float) location.getLongitude(), (int) location.getAccuracy(), MM_SOURCE_REPORT_HARWARE, nullAsNil, nullAsNil2, true);
                    } else if (!this.ai && i == MM_SOURCE_NET) {
                        this.ai = true;
                        Log.m13547v("MicroMsg.LBSManager", "report location by Network ok:[" + location.getLatitude() + " , " + location.getLongitude() + "]  accuracy:" + location.getAccuracy() + "  retry count:" + this.aj + " isGpsProvider:" + equals);
                        this.ac.onLocationGot((float) location.getLatitude(), (float) location.getLongitude(), (int) location.getAccuracy(), MM_SOURCE_REPORT_NETWORK, nullAsNil, nullAsNil2, true);
                    }
                }
            }
        }
    }

    public void removeGpsUpdate() {
        Log.m13547v("MicroMsg.LBSManager", "removed gps update");
        if (this.ad != null) {
            this.ad.removeUpdates(this.ae);
        }
        try {
            this.f11807R.unregisterReceiver(this);
        } catch (Exception e) {
            Log.m13547v("MicroMsg.LBSManager", "location receiver has already unregistered");
        }
    }

    public void removeListener() {
        Log.m13547v("MicroMsg.LBSManager", "removed gps update on destroy");
        removeGpsUpdate();
        if (this.ak != null) {
            m13536c();
        }
        this.ac = null;
        this.f11807R = null;
        this.ak = null;
        this.ad = null;
    }

    public void requestGpsUpdate() {
        if (isGpsEnable() || isNetworkPrividerEnable()) {
            Log.m13547v("MicroMsg.LBSManager", "requested gps update");
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(FILTER_GPS);
            this.f11807R.registerReceiver(this, intentFilter);
            if (isGpsEnable()) {
                this.ad.requestLocationUpdates(GeocodeSearch.GPS, 500, 0.0f, this.ae);
            }
            if (isNetworkPrividerEnable()) {
                this.ad.requestLocationUpdates("network", 500, 0.0f, this.ae);
            }
        }
    }

    public void start() {
        String nullAsNil = Util.nullAsNil(getWIFILocation());
        String nullAsNil2 = Util.nullAsNil(getTelLocation());
        int i = (isGpsEnable() || isNetworkPrividerEnable()) ? true : MM_SOURCE_HARDWARE;
        if (i == 0 || this.af) {
            if (ab == null) {
                i = MM_SOURCE_HARDWARE;
            } else if (System.currentTimeMillis() - ab.time > 180000 || ab.ao <= 0) {
                i = MM_SOURCE_HARDWARE;
            } else {
                boolean z = true;
            }
            if (i == 0) {
                this.ag = true;
                if (nullAsNil.equals(C2915a.f14760f) && nullAsNil2.equals(C2915a.f14760f)) {
                    Log.m13547v("MicroMsg.LBSManager", "get location by network failed");
                    if (this.ac != null) {
                        this.ac.onLocationGot(INVALID_LNG, INVALID_LNG, INVALID_ACC, MM_SOURCE_HARDWARE, C2915a.f14760f, C2915a.f14760f, false);
                        return;
                    }
                    return;
                }
                Log.m13547v("MicroMsg.LBSManager", "get location by network ok, macs : " + nullAsNil + " cell ids :" + nullAsNil2);
                if (this.ac != null) {
                    this.ac.onLocationGot(INVALID_LNG, INVALID_LNG, INVALID_ACC, MM_SOURCE_HARDWARE, nullAsNil, nullAsNil2, true);
                    return;
                }
                return;
            } else if (this.ac != null) {
                this.ag = true;
                Log.m13547v("MicroMsg.LBSManager", "location by GPS cache ok:[" + ab.am + " , " + ab.an + "]  accuracy:" + ab.ao + " source:" + ab.f11806I);
                this.ac.onLocationGot(ab.am, ab.an, ab.ao, ab.f11806I, nullAsNil, nullAsNil2, true);
                return;
            } else {
                return;
            }
        }
        this.af = true;
        this.aj = MM_SOURCE_HARDWARE;
        requestGpsUpdate();
        this.ak.startTimer(3000);
    }
}
