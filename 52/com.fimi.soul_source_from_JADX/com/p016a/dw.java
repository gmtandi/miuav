package com.p016a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.facebook.common.util.UriUtil;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.market.sdk.C2537j;
import it.p074a.p075a.C2799f;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.p122a.p123a.C2915a;

/* renamed from: com.a.dw */
public class dw {
    private static float f981M;
    private static float f982N;
    private static float f983O;
    private static float f984P;
    private static int f985Q;
    private static int f986R;
    private static int f987S;
    private static int f988T;
    private static int f989U;
    private static int f990V;
    private static int f991W;
    protected static boolean f992a;
    protected static boolean f993b;
    private static int f994d;
    private static int f995e;
    private static int f996f;
    private static int f997g;
    private static int f998h;
    private static int f999i;
    private static Object f1000j;
    private static dw f1001k;
    private er f1002A;
    private volatile Handler f1003B;
    private es f1004C;
    private LocationListener f1005D;
    private BroadcastReceiver f1006E;
    private BroadcastReceiver f1007F;
    private GpsStatus f1008G;
    private int f1009H;
    private int f1010I;
    private HashMap f1011J;
    private int f1012K;
    private int f1013L;
    Object f1014c;
    private boolean f1015l;
    private int f1016m;
    private int f1017n;
    private int f1018o;
    private Context f1019p;
    private LocationManager f1020q;
    private eg f1021r;
    private eu f1022s;
    private fb f1023t;
    private ed f1024u;
    private fa f1025v;
    private et f1026w;
    private dx f1027x;
    private Thread f1028y;
    private Looper f1029z;

    static {
        f992a = false;
        f993b = true;
        f994d = 10;
        f995e = 2;
        f996f = 10;
        f997g = 10;
        f998h = 50;
        f999i = C2799f.f14282t;
        f1000j = new Object();
        f981M = 1.1f;
        f982N = 2.2f;
        f983O = 2.3f;
        f984P = 3.8f;
        f985Q = 3;
        f986R = 10;
        f987S = 2;
        f988T = 7;
        f989U = 20;
        f990V = 70;
        f991W = Opcodes.ISHL;
    }

    private dw(Context context) {
        this.f1015l = false;
        this.f1016m = -1;
        this.f1017n = 0;
        this.f1018o = 0;
        this.f1028y = null;
        this.f1029z = null;
        this.f1002A = null;
        this.f1003B = null;
        this.f1014c = new Object();
        this.f1004C = new es(this);
        this.f1005D = new em(this);
        this.f1006E = new en(this);
        this.f1007F = new eo(this);
        this.f1008G = null;
        this.f1009H = 0;
        this.f1010I = 0;
        this.f1011J = null;
        this.f1012K = 0;
        this.f1013L = 0;
        this.f1019p = context;
        this.f1021r = eg.m1671a(context);
        this.f1027x = new dx();
        this.f1022s = new eu(this.f1021r);
        this.f1024u = new ed(context);
        this.f1023t = new fb(this.f1024u);
        this.f1025v = new fa(this.f1024u);
        this.f1020q = (LocationManager) this.f1019p.getSystemService("location");
        this.f1026w = et.m1751a(this.f1019p);
        this.f1026w.m1758a(this.f1004C);
        m1624o();
        List allProviders = this.f1020q.getAllProviders();
        boolean z = allProviders != null && allProviders.contains(GeocodeSearch.GPS) && allProviders.contains("passive");
        this.f1015l = z;
        if (context != null) {
            eg.f1074a = context.getPackageName();
        } else {
            Log.d(eg.f1074a, "Error: No SD Card!");
        }
    }

    static /* synthetic */ int m1586a(dw dwVar, fd fdVar, int i) {
        if (dwVar.f1012K >= f986R) {
            return 1;
        }
        if (dwVar.f1012K <= f985Q) {
            return 4;
        }
        double c = fdVar.m1798c();
        if (c <= ((double) f981M)) {
            return 1;
        }
        if (c >= ((double) f982N)) {
            return 4;
        }
        c = fdVar.m1797b();
        return c > ((double) f983O) ? c >= ((double) f984P) ? 4 : i < f988T ? i <= f987S ? 4 : dwVar.f1011J != null ? dwVar.m1587a(dwVar.f1011J) : 3 : 1 : 1;
    }

    private int m1587a(HashMap hashMap) {
        if (this.f1009H > 4) {
            int i;
            List arrayList = new ArrayList();
            List arrayList2 = new ArrayList();
            int i2 = 0;
            for (Entry value : hashMap.entrySet()) {
                List list = (List) value.getValue();
                if (list != null) {
                    Object a = m1598a(list);
                    if (a != null) {
                        arrayList.add(a);
                        i = i2 + 1;
                        arrayList2.add(Integer.valueOf(i2));
                        i2 = i;
                    }
                }
                i = i2;
                i2 = i;
            }
            if (!arrayList.isEmpty()) {
                double[] dArr = new double[2];
                int size = arrayList.size();
                for (int i3 = 0; i3 < size; i3++) {
                    double[] dArr2 = (double[]) arrayList.get(i3);
                    i = ((Integer) arrayList2.get(i3)).intValue();
                    dArr2[0] = dArr2[0] * ((double) i);
                    dArr2[1] = dArr2[1] * ((double) i);
                    dArr[0] = dArr[0] + dArr2[0];
                    dArr[1] = dArr[1] + dArr2[1];
                }
                dArr[0] = dArr[0] / ((double) size);
                dArr[1] = dArr[1] / ((double) size);
                double d = dArr[0];
                double d2 = dArr[1];
                double toDegrees = d2 == 0.0d ? d > 0.0d ? 90.0d : d < 0.0d ? 270.0d : 0.0d : Math.toDegrees(Math.atan(d / d2));
                double[] dArr3 = new double[]{Math.sqrt((d * d) + (d2 * d2)), toDegrees};
                String.format(Locale.CHINA, "%d,%d,%d,%d", new Object[]{Long.valueOf(Math.round(dArr[0] * 100.0d)), Long.valueOf(Math.round(dArr[1] * 100.0d)), Long.valueOf(Math.round(dArr3[0] * 100.0d)), Long.valueOf(Math.round(dArr3[1] * 100.0d))});
                if (dArr3[0] <= ((double) f990V)) {
                    return 1;
                }
                if (dArr3[0] >= ((double) f991W)) {
                    return 4;
                }
            }
        }
        return 3;
    }

    public static dw m1591a(Context context) {
        if (f1001k == null) {
            synchronized (f1000j) {
                if (f1001k == null) {
                    f1001k = new dw(context);
                }
            }
        }
        return f1001k;
    }

    static /* synthetic */ String m1593a(dw dwVar, String str) {
        return str;
    }

    public static String m1594a(String str) {
        return str.equals(C2537j.aq) ? "V1.0.0r" : str.equals("date") ? "COL.15.0929r" : null;
    }

    static /* synthetic */ void m1597a(dw dwVar, Location location, int i, long j) {
        dv a;
        Long valueOf;
        System.currentTimeMillis();
        boolean a2 = dwVar.f1022s.m1770a(location);
        if (a2) {
            dwVar.f1022s.f1140b.f1163b = new Location(location);
        }
        boolean b = dwVar.f1022s.m1771b(location);
        if (b) {
            dwVar.f1022s.f1139a.f1171b = new Location(location);
        }
        int i2 = 0;
        if (a2) {
            i2 = 1;
            if (b) {
                i2 = 3;
            }
        } else if (b) {
            i2 = 2;
        }
        try {
            dx dxVar = dwVar.f1027x;
            a = dx.m1635a(location, dwVar.f1021r, i2, (byte) dwVar.f1013L, j, false);
        } catch (Exception e) {
            a = null;
        }
        if (!(a == null || dwVar.f1021r == null)) {
            List m = dwVar.f1021r.m1735m();
            valueOf = Long.valueOf(0);
            if (m != null && m.size() > 0) {
                valueOf = (Long) m.get(0);
            }
            dwVar.f1023t.m1793a(valueOf.longValue(), a.m1584a());
        }
        if (dwVar.f1019p != null && dwVar.f1027x != null) {
            SharedPreferences sharedPreferences = dwVar.f1019p.getSharedPreferences("app_pref", 0);
            if (!sharedPreferences.getString("get_sensor", C2915a.f14760f).equals("true")) {
                try {
                    dxVar = dwVar.f1027x;
                    a = dx.m1635a(null, dwVar.f1021r, i2, (byte) dwVar.f1013L, j, true);
                } catch (Exception e2) {
                    a = null;
                }
                if (a != null && dwVar.f1021r != null) {
                    List m2 = dwVar.f1021r.m1735m();
                    valueOf = Long.valueOf(0);
                    if (m2 != null && m2.size() > 0) {
                        valueOf = (Long) m2.get(0);
                    }
                    dwVar.f1023t.m1793a(valueOf.longValue(), a.m1584a());
                    sharedPreferences.edit().putString("get_sensor", "true").commit();
                }
            }
        }
    }

    private double[] m1598a(List list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        double[] dArr = new double[2];
        for (GpsSatellite gpsSatellite : list) {
            if (gpsSatellite != null) {
                double elevation = (double) (90.0f - gpsSatellite.getElevation());
                double azimuth = (double) gpsSatellite.getAzimuth();
                double[] dArr2 = new double[]{Math.sin(Math.toRadians(azimuth)) * elevation, elevation * Math.cos(Math.toRadians(azimuth))};
                dArr[0] = dArr[0] + dArr2[0];
                dArr[1] = dArr[1] + dArr2[1];
            }
        }
        int size = list.size();
        dArr[0] = dArr[0] / ((double) size);
        dArr[1] = dArr[1] / ((double) size);
        return dArr;
    }

    private void m1624o() {
        this.f1017n = this.f1026w.m1761b() * XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER;
        this.f1018o = this.f1026w.m1763c();
        eu euVar = this.f1022s;
        int i = this.f1017n;
        i = this.f1018o;
        eu.m1767a();
    }

    public void m1625a() {
        eg.f1075b = true;
        if (this.f1015l && this.f1021r != null && !f992a) {
            f992a = true;
            IntentFilter intentFilter = new IntentFilter("android.location.GPS_ENABLED_CHANGE");
            intentFilter.addAction("android.location.GPS_FIX_CHANGE");
            f993b = true;
            this.f1019p.registerReceiver(this.f1007F, intentFilter);
            intentFilter = new IntentFilter();
            intentFilter.setPriority(XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER);
            intentFilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
            intentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
            intentFilter.addAction("android.intent.action.MEDIA_EJECT");
            intentFilter.addDataScheme(UriUtil.LOCAL_FILE_SCHEME);
            this.f1019p.registerReceiver(this.f1006E, intentFilter);
            String str = C2915a.f14760f;
            this.f1020q.removeGpsStatusListener(this.f1002A);
            this.f1020q.removeNmeaListener(this.f1002A);
            this.f1002A = null;
            this.f1020q.removeUpdates(this.f1005D);
            if (this.f1029z != null) {
                this.f1029z.quit();
                this.f1029z = null;
            }
            if (this.f1028y != null) {
                this.f1028y.interrupt();
                this.f1028y = null;
            }
            this.f1028y = new ep(this, str);
            this.f1028y.start();
            this.f1021r.m1713a();
        }
    }

    public void m1626a(int i) {
        if (i == Opcodes.ACC_NATIVE || i == 8736 || i == 768) {
            this.f1024u.m1660a(i);
            return;
        }
        throw new RuntimeException("invalid Size! must be COLLECTOR_SMALL_SIZE or COLLECTOR_BIG_SIZE or COLLECTOR_MEDIUM_SIZE");
    }

    public void m1627a(ec ecVar, String str) {
        if (!eg.f1076c) {
            boolean a = this.f1026w.m1760a(str);
            if (ecVar != null) {
                byte[] a2 = ecVar.m1639a();
                if (a && a2 != null) {
                    NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.f1019p.getSystemService("connectivity")).getActiveNetworkInfo();
                    if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                        if (activeNetworkInfo.getType() == 1) {
                            this.f1026w.m1757a(a2.length + this.f1026w.m1765e());
                        } else {
                            this.f1026w.m1762b(a2.length + this.f1026w.m1766f());
                        }
                    }
                }
                ecVar.m1638a(a);
                this.f1025v.m1792a(ecVar);
            }
        }
    }

    public void m1628b() {
        eg.f1075b = false;
        this.f1003B = null;
        eg.f1076c = false;
        if (this.f1015l && this.f1021r != null && f992a) {
            if (this.f1007F != null) {
                try {
                    this.f1019p.unregisterReceiver(this.f1007F);
                    this.f1019p.unregisterReceiver(this.f1006E);
                } catch (Exception e) {
                }
            }
            if (this.f1021r != null) {
                this.f1021r.m1744v();
            }
            synchronized (this.f1014c) {
                f992a = false;
                this.f1020q.removeGpsStatusListener(this.f1002A);
                this.f1020q.removeNmeaListener(this.f1002A);
                this.f1002A = null;
                this.f1020q.removeUpdates(this.f1005D);
                if (this.f1029z != null) {
                    this.f1029z.quit();
                    this.f1029z = null;
                }
                if (this.f1028y != null) {
                    this.f1028y.interrupt();
                    this.f1028y = null;
                }
            }
            this.f1021r.m1717b();
        }
    }

    public void m1629b(int i) {
        if (this.f1021r != null) {
            this.f1021r.m1714a(i);
        }
    }

    public void m1630c() {
        if (this.f1015l) {
            m1628b();
        }
    }

    public boolean m1631d() {
        return f992a;
    }

    public ec m1632e() {
        if (this.f1025v == null) {
            return null;
        }
        m1633f();
        return (!this.f1026w.m1759a() || eg.f1076c) ? null : this.f1025v.m1791a(this.f1026w.m1764d());
    }

    public boolean m1633f() {
        if (this.f1021r == null) {
            return false;
        }
        List m = this.f1021r.m1735m();
        return (m == null || m.size() <= 0) ? false : this.f1024u.m1663b(((Long) m.get(0)).longValue());
    }

    public int m1634g() {
        return this.f1025v != null ? this.f1025v.m1790a() : 0;
    }
}
