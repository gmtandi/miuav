package com.p016a;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Looper;
import android.os.Messenger;
import com.amap.api.fence.Fence;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.APSService;
import com.amap.api.location.LocationManagerBase;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.OAuth;
import com.xiaomi.market.sdk.C2537j;
import java.util.ArrayList;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

/* renamed from: com.a.a */
public class C0238a implements LocationManagerBase {
    public static boolean f475t;
    private boolean f476A;
    private ServiceConnection f477B;
    private ArrayList<C0243d> f478C;
    private int f479D;
    private AMapLocation f480E;
    AMapLocationClientOption f481a;
    C0246f f482b;
    ff f483c;
    ArrayList<AMapLocationListener> f484d;
    fe f485e;
    boolean f486f;
    fh f487g;
    Messenger f488h;
    Messenger f489i;
    C0245e f490j;
    Intent f491k;
    int f492l;
    int f493m;
    boolean f494n;
    long f495o;
    long f496p;
    AMapLocation f497q;
    long f498r;
    long f499s;
    private Context f500u;
    private boolean f501v;
    private boolean f502w;
    private long f503x;
    private boolean f504y;
    private boolean f505z;

    static {
        f475t = false;
    }

    public C0238a(Context context, Intent intent) {
        this.f483c = null;
        this.f501v = false;
        this.f502w = true;
        this.f484d = new ArrayList();
        this.f486f = false;
        this.f503x = 0;
        this.f504y = true;
        this.f505z = false;
        this.f476A = false;
        this.f488h = null;
        this.f489i = null;
        this.f491k = null;
        this.f492l = 0;
        this.f493m = 0;
        this.f494n = false;
        this.f495o = 0;
        this.f496p = 0;
        this.f497q = null;
        this.f498r = 0;
        this.f499s = 0;
        this.f477B = new am(this);
        this.f478C = new ArrayList();
        this.f479D = 0;
        this.f480E = null;
        this.f500u = context;
        this.f491k = intent;
        m944b();
    }

    private C0243d m938a(AMapLocation aMapLocation, int i) {
        return new C0243d(this, aMapLocation, i);
    }

    private AMapLocation m940a(AMapLocation aMapLocation, AMapLocation aMapLocation2) {
        this.f480E = aMapLocation2;
        long b = dn.m1519b();
        if (aMapLocation == null) {
            this.f499s = 0;
            this.f479D = 0;
            return aMapLocation2;
        } else if (aMapLocation.getLocationType() != 1) {
            this.f499s = 0;
            this.f479D = 0;
            return aMapLocation2;
        } else if (aMapLocation2.getLocationType() != 1) {
            this.f499s = 0;
            this.f479D = 0;
            return aMapLocation2;
        } else if (b - this.f498r < 5000) {
            if (dn.m1497a(new double[]{aMapLocation.getLatitude(), aMapLocation.getLongitude(), aMapLocation2.getLatitude(), aMapLocation2.getLongitude()}) > ((((aMapLocation.getSpeed() + aMapLocation2.getSpeed()) * ((float) (aMapLocation2.getTime() - aMapLocation.getTime()))) / 2000.0f) + (2.0f * (aMapLocation.getAccuracy() + aMapLocation2.getAccuracy()))) + 3000.0f) {
                if (this.f499s == 0) {
                    this.f499s = dn.m1519b();
                }
                if (b - this.f499s < 30000) {
                    this.f479D = 1;
                    return aMapLocation;
                }
                this.f499s = 0;
                this.f479D = 0;
                return aMapLocation2;
            }
            this.f499s = 0;
            this.f479D = 0;
            return aMapLocation2;
        } else {
            this.f499s = 0;
            this.f479D = 0;
            return aMapLocation2;
        }
    }

    private void m941a(Intent intent) {
        if (intent == null) {
            try {
                intent = new Intent(this.f500u, APSService.class);
            } catch (Throwable th) {
                ev.m1777a(th, "AMapLocationManager", "startService");
                return;
            }
        }
        intent.putExtra(OAuth.API_KEY, ev.f1142a);
        String e = fn.m1841e(this.f500u);
        intent.putExtra(C2537j.f12839W, this.f500u.getPackageName());
        intent.putExtra("sha1AndPackage", e);
        this.f500u.bindService(intent, this.f477B, 1);
    }

    private void m944b() {
        m941a(this.f491k);
        this.f487g = fh.m1812a(this.f500u);
        if (Looper.myLooper() == null) {
            this.f482b = new C0246f(this, this.f500u.getMainLooper());
        } else {
            this.f482b = new C0246f(this);
        }
        this.f489i = new Messenger(this.f482b);
        this.f483c = new ff(this.f500u, this.f482b);
        try {
            this.f485e = new fe(this.f500u);
        } catch (Throwable th) {
            ev.m1777a(th, "AMapLocationManager", "init");
        }
    }

    private void m947c() {
        if (this.f490j == null) {
            this.f490j = new C0245e(this, "locationThread");
            this.f490j.setPriority(5);
            this.f490j.start();
        }
    }

    private boolean m950d() {
        return dn.m1519b() - this.f495o > 10000;
    }

    private boolean m952e() {
        return this.f496p != 0 && dn.m1519b() - this.f496p > 30000;
    }

    private void m954f() {
        Object obj = 1;
        Object obj2 = null;
        try {
            if (this.f500u.checkCallingOrSelfPermission("android.permission.SYSTEM_ALERT_WINDOW") == 0) {
                obj2 = 1;
            } else if (this.f500u instanceof Activity) {
                int i = 1;
                obj = null;
            } else {
                obj = null;
            }
            if (obj2 != null) {
                Builder builder = new Builder(this.f500u);
                builder.setMessage(dj.m1477j());
                if (!(C2915a.f14760f.equals(dj.m1478k()) || dj.m1478k() == null)) {
                    builder.setPositiveButton(dj.m1478k(), new C0241b(this));
                }
                builder.setNegativeButton(dj.m1479l(), new C0242c(this));
                AlertDialog create = builder.create();
                if (obj != null) {
                    create.getWindow().setType(2003);
                }
                create.setCanceledOnTouchOutside(false);
                create.show();
                return;
            }
            m956g();
        } catch (Throwable th) {
            m956g();
            ev.m1777a(th, "AMapLocationManager", "showDialog");
        }
    }

    private void m956g() {
        Intent intent;
        try {
            intent = new Intent();
            intent.setComponent(new ComponentName("com.autonavi.minimap", dj.m1482o()));
            intent.setFlags(268435456);
            intent.setData(Uri.parse(dj.m1480m()));
            this.f500u.startActivity(intent);
        } catch (Throwable th) {
            ev.m1777a(th, "AMapLocationManager", "callAMap part2");
        }
    }

    void m962a() {
        if (this.f490j != null) {
            this.f490j.f1043a = false;
            this.f490j.interrupt();
        }
        this.f490j = null;
    }

    public void addGeoFenceAlert(String str, double d, double d2, float f, long j, PendingIntent pendingIntent) {
        Fence fence = new Fence();
        fence.f1340b = str;
        fence.f1342d = d;
        fence.f1341c = d2;
        fence.f1343e = f;
        fence.f1339a = pendingIntent;
        fence.m2067a(j);
        if (this.f485e != null) {
            this.f485e.m1808a(fence, fence.f1339a);
        }
    }

    public AMapLocation getLastKnownLocation() {
        AMapLocation aMapLocation = null;
        try {
            aMapLocation = this.f487g.m1813a();
        } catch (Throwable th) {
            ev.m1777a(th, "AMapLocationManager", "getLastKnownLocation");
        }
        return aMapLocation;
    }

    public String getVersion() {
        return "2.4.1";
    }

    public boolean isStarted() {
        return this.f501v;
    }

    public void onDestroy() {
        this.f504y = true;
        stopLocation();
        if (this.f485e != null) {
            this.f485e.m1804a();
        }
        if (this.f477B != null) {
            this.f500u.unbindService(this.f477B);
        }
        if (this.f484d != null) {
            this.f484d.clear();
            this.f484d = null;
        }
        this.f477B = null;
        if (this.f482b != null) {
            this.f482b.removeCallbacksAndMessages(null);
        }
    }

    public void removeGeoFenceAlert(PendingIntent pendingIntent) {
        if (this.f485e != null) {
            this.f485e.m1806a(pendingIntent);
        }
    }

    public void removeGeoFenceAlert(PendingIntent pendingIntent, String str) {
        if (this.f485e != null) {
            this.f485e.m1807a(pendingIntent, str);
        }
    }

    public void setLocationListener(AMapLocationListener aMapLocationListener) {
        if (aMapLocationListener == null) {
            throw new IllegalArgumentException("listener\u53c2\u6570\u4e0d\u80fd\u4e3anull");
        }
        if (this.f484d == null) {
            this.f484d = new ArrayList();
        }
        if (!this.f484d.contains(aMapLocationListener)) {
            this.f484d.add(aMapLocationListener);
        }
    }

    public void setLocationOption(AMapLocationClientOption aMapLocationClientOption) {
        this.f481a = aMapLocationClientOption;
    }

    public void startAssistantLocation() {
        if (this.f482b != null) {
            this.f482b.sendEmptyMessage(Opcodes.LSUB);
        }
    }

    public void startLocation() {
        if (this.f481a == null) {
            this.f481a = new AMapLocationClientOption();
        }
        this.f502w = false;
        switch (ch.f747a[this.f481a.getLocationMode().ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                this.f476A = false;
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
            case Type.BYTE /*3*/:
                this.f476A = true;
                break;
            default:
                this.f476A = false;
                break;
        }
        m947c();
    }

    public void stopAssistantLocation() {
        if (this.f482b != null) {
            this.f482b.sendEmptyMessage(Opcodes.FSUB);
        }
    }

    public void stopLocation() {
        this.f476A = false;
        m962a();
        if (this.f505z) {
            this.f483c.m1809a();
            this.f505z = false;
        }
        this.f494n = false;
        this.f501v = false;
        this.f502w = true;
        this.f496p = 0;
        this.f495o = 0;
        this.f493m = 0;
        this.f492l = 0;
        this.f497q = null;
        this.f498r = 0;
        this.f478C.clear();
        this.f479D = 0;
        this.f480E = null;
    }

    public void unRegisterLocationListener(AMapLocationListener aMapLocationListener) {
        if (!this.f484d.isEmpty() && this.f484d.contains(aMapLocationListener)) {
            this.f484d.remove(aMapLocationListener);
        }
        if (this.f484d.isEmpty()) {
            stopLocation();
        }
    }
}
