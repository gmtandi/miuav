package com.p016a;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.maps.model.WeightedLatLng;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.autonavi.aps.amapapi.model.AmapLoc;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import org.p122a.p123a.C2915a;

/* renamed from: com.a.cv */
public class cv {
    private static cv f802a;
    private Hashtable<String, ArrayList<cw>> f803b;
    private long f804c;
    private boolean f805d;

    static {
        f802a = null;
    }

    private cv() {
        this.f803b = new Hashtable();
        this.f804c = 0;
        this.f805d = false;
    }

    public static synchronized cv m1394a() {
        cv cvVar;
        synchronized (cv.class) {
            if (f802a == null) {
                f802a = new cv();
            }
            cvVar = f802a;
        }
        return cvVar;
    }

    private synchronized cw m1395a(StringBuilder stringBuilder, String str) {
        cw cwVar;
        if (this.f803b.isEmpty() || TextUtils.isEmpty(stringBuilder)) {
            cwVar = null;
        } else if (this.f803b.containsKey(str)) {
            Hashtable hashtable = new Hashtable();
            Hashtable hashtable2 = new Hashtable();
            Hashtable hashtable3 = new Hashtable();
            ArrayList arrayList = (ArrayList) this.f803b.get(str);
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                cw cwVar2 = (cw) arrayList.get(size);
                if (!TextUtils.isEmpty(cwVar2.m1409b())) {
                    Object obj;
                    String str2;
                    if (m1398c(cwVar2.m1409b(), stringBuilder)) {
                        if (m1404b(cwVar2.m1409b(), stringBuilder)) {
                            cwVar = cwVar2;
                            break;
                        }
                        obj = 1;
                    } else {
                        obj = null;
                    }
                    m1396a(cwVar2.m1409b(), hashtable);
                    m1396a(stringBuilder.toString(), hashtable2);
                    hashtable3.clear();
                    for (String str22 : hashtable.keySet()) {
                        hashtable3.put(str22, C2915a.f14760f);
                    }
                    for (String str222 : hashtable2.keySet()) {
                        hashtable3.put(str222, C2915a.f14760f);
                    }
                    Set keySet = hashtable3.keySet();
                    double[] dArr = new double[keySet.size()];
                    double[] dArr2 = new double[keySet.size()];
                    Iterator it = keySet.iterator();
                    int i = 0;
                    while (it != null && it.hasNext()) {
                        str222 = (String) it.next();
                        dArr[i] = hashtable.containsKey(str222) ? WeightedLatLng.DEFAULT_INTENSITY : 0.0d;
                        dArr2[i] = hashtable2.containsKey(str222) ? WeightedLatLng.DEFAULT_INTENSITY : 0.0d;
                        i++;
                    }
                    keySet.clear();
                    double[] a = m1397a(dArr, dArr2);
                    if (a[0] < 0.800000011920929d) {
                        if (a[1] < 0.618d) {
                            if (obj != null && a[0] >= 0.618d) {
                                cwVar = cwVar2;
                                break;
                            }
                        }
                        cwVar = cwVar2;
                        break;
                    }
                    cwVar = cwVar2;
                    break;
                }
            }
            cwVar = null;
            hashtable.clear();
            hashtable2.clear();
            hashtable3.clear();
        } else {
            cwVar = null;
        }
        return cwVar;
    }

    private void m1396a(String str, Hashtable<String, String> hashtable) {
        if (!TextUtils.isEmpty(str)) {
            hashtable.clear();
            for (Object obj : str.split("#")) {
                if (!(TextUtils.isEmpty(obj) || obj.contains("|"))) {
                    hashtable.put(obj, C2915a.f14760f);
                }
            }
        }
    }

    private double[] m1397a(double[] dArr, double[] dArr2) {
        int i;
        double[] dArr3 = new double[3];
        double d = 0.0d;
        double d2 = 0.0d;
        double d3 = 0.0d;
        int i2 = 0;
        int i3 = 0;
        for (i = 0; i < dArr.length; i++) {
            d2 += dArr[i] * dArr[i];
            d3 += dArr2[i] * dArr2[i];
            d += dArr[i] * dArr2[i];
            if (dArr2[i] == WeightedLatLng.DEFAULT_INTENSITY) {
                i2++;
                if (dArr[i] == WeightedLatLng.DEFAULT_INTENSITY) {
                    i3++;
                }
            }
        }
        dArr3[0] = d / (Math.sqrt(d3) * Math.sqrt(d2));
        dArr3[1] = (WeightedLatLng.DEFAULT_INTENSITY * ((double) i3)) / ((double) i2);
        dArr3[2] = (double) i3;
        for (i = 0; i < dArr3.length - 1; i++) {
            if (dArr3[i] > WeightedLatLng.DEFAULT_INTENSITY) {
                dArr3[i] = WeightedLatLng.DEFAULT_INTENSITY;
            }
            dArr3[i] = Double.parseDouble(dn.m1505a(Double.valueOf(dArr3[i]), "#.00"));
        }
        return dArr3;
    }

    private boolean m1398c(String str, StringBuilder stringBuilder) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(stringBuilder)) {
            return false;
        }
        if (!str.contains(",access") || stringBuilder.indexOf(",access") == -1) {
            return false;
        }
        String[] split = str.split(",access");
        Object substring = split[0].contains("#") ? split[0].substring(split[0].lastIndexOf("#") + 1) : split[0];
        return TextUtils.isEmpty(substring) ? false : stringBuilder.toString().contains(substring + ",access");
    }

    public synchronized AmapLoc m1399a(String str, StringBuilder stringBuilder) {
        AmapLoc amapLoc;
        if (str.contains(GeocodeSearch.GPS)) {
            amapLoc = null;
        } else if (m1403b()) {
            m1405c();
            amapLoc = null;
        } else if (this.f803b.isEmpty()) {
            amapLoc = null;
        } else {
            cw a;
            String str2 = "found#\u2297";
            String str3;
            if (str.contains("cgiwifi")) {
                a = m1395a(stringBuilder, str);
                if (a != null) {
                    str3 = "found#cgiwifi";
                }
            } else if (str.contains("wifi")) {
                a = m1395a(stringBuilder, str);
                if (a != null) {
                    str3 = "found#wifi";
                }
            } else if (str.contains("cgi")) {
                a = this.f803b.containsKey(str) ? (cw) ((ArrayList) this.f803b.get(str)).get(0) : null;
                if (a != null) {
                    str3 = "found#cgi";
                }
            } else {
                a = null;
            }
            if (a == null || !dn.m1512a(a.m1406a())) {
                amapLoc = null;
            } else {
                a.m1406a().m5333f("mem");
                if (TextUtils.isEmpty(ev.f1148g)) {
                    ev.f1148g = String.valueOf(a.m1406a().m5305B());
                }
                amapLoc = a.m1406a();
            }
        }
        return amapLoc;
    }

    public void m1400a(Context context) {
        if (!this.f805d) {
            dn.m1519b();
            try {
                cx.m1410a().m1412a(context);
            } catch (Throwable th) {
                ev.m1777a(th, "Cache", "loadDB");
            }
            this.f805d = true;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void m1401a(java.lang.String r7, java.lang.StringBuilder r8, com.autonavi.aps.amapapi.model.AmapLoc r9, android.content.Context r10, boolean r11) {
        /*
        r6 = this;
        r0 = 0;
        monitor-enter(r6);
        r1 = r6.m1402a(r7, r9);	 Catch:{ all -> 0x010c }
        if (r1 != 0) goto L_0x000a;
    L_0x0008:
        monitor-exit(r6);
        return;
    L_0x000a:
        r1 = r9.m5345l();	 Catch:{ all -> 0x010c }
        r2 = "mem";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x010c }
        if (r1 != 0) goto L_0x0008;
    L_0x0016:
        r1 = r9.m5345l();	 Catch:{ all -> 0x010c }
        r2 = "file";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x010c }
        if (r1 != 0) goto L_0x0008;
    L_0x0022:
        r1 = r9.m5347m();	 Catch:{ all -> 0x010c }
        r2 = "-3";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x010c }
        if (r1 != 0) goto L_0x0008;
    L_0x002e:
        r1 = r6.m1403b();	 Catch:{ all -> 0x010c }
        if (r1 == 0) goto L_0x0037;
    L_0x0034:
        r6.m1405c();	 Catch:{ all -> 0x010c }
    L_0x0037:
        r1 = r9.m5308E();	 Catch:{ all -> 0x010c }
        r2 = "offpct";
        r2 = com.p016a.dn.m1514a(r1, r2);	 Catch:{ all -> 0x010c }
        if (r2 == 0) goto L_0x004b;
    L_0x0043:
        r2 = "offpct";
        r1.remove(r2);	 Catch:{ all -> 0x010c }
        r9.m5316a(r1);	 Catch:{ all -> 0x010c }
    L_0x004b:
        r1 = "wifi";
        r1 = r7.contains(r1);	 Catch:{ all -> 0x010c }
        if (r1 == 0) goto L_0x011b;
    L_0x0053:
        r1 = android.text.TextUtils.isEmpty(r8);	 Catch:{ all -> 0x010c }
        if (r1 != 0) goto L_0x0008;
    L_0x0059:
        r1 = r9.m5341j();	 Catch:{ all -> 0x010c }
        r2 = 1133903872; // 0x43960000 float:300.0 double:5.60222949E-315;
        r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1));
        if (r1 < 0) goto L_0x010f;
    L_0x0063:
        r1 = r8.toString();	 Catch:{ all -> 0x010c }
        r2 = "#";
        r2 = r1.split(r2);	 Catch:{ all -> 0x010c }
        r3 = r2.length;	 Catch:{ all -> 0x010c }
        r1 = r0;
    L_0x006f:
        if (r1 >= r3) goto L_0x0080;
    L_0x0071:
        r4 = r2[r1];	 Catch:{ all -> 0x010c }
        r5 = ",";
        r4 = r4.contains(r5);	 Catch:{ all -> 0x010c }
        if (r4 == 0) goto L_0x007d;
    L_0x007b:
        r0 = r0 + 1;
    L_0x007d:
        r1 = r1 + 1;
        goto L_0x006f;
    L_0x0080:
        r1 = 8;
        if (r0 >= r1) goto L_0x0008;
    L_0x0084:
        r0 = "cgiwifi";
        r0 = r7.contains(r0);	 Catch:{ all -> 0x010c }
        if (r0 == 0) goto L_0x00b3;
    L_0x008c:
        r0 = r9.m5306C();	 Catch:{ all -> 0x010c }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ all -> 0x010c }
        if (r0 != 0) goto L_0x00b3;
    L_0x0096:
        r0 = "cgiwifi";
        r1 = "cgi";
        r1 = r7.replace(r0, r1);	 Catch:{ all -> 0x010c }
        r3 = r9.m5307D();	 Catch:{ all -> 0x010c }
        r0 = com.p016a.dn.m1512a(r3);	 Catch:{ all -> 0x010c }
        if (r0 == 0) goto L_0x00b3;
    L_0x00a8:
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x010c }
        r2.<init>();	 Catch:{ all -> 0x010c }
        r5 = 1;
        r0 = r6;
        r4 = r10;
        r0.m1401a(r1, r2, r3, r4, r5);	 Catch:{ all -> 0x010c }
    L_0x00b3:
        r0 = r6.m1399a(r7, r8);	 Catch:{ all -> 0x010c }
        r1 = com.p016a.dn.m1512a(r0);	 Catch:{ all -> 0x010c }
        if (r1 == 0) goto L_0x00cc;
    L_0x00bd:
        r0 = r0.m5309F();	 Catch:{ all -> 0x010c }
        r1 = 3;
        r1 = r9.m5325c(r1);	 Catch:{ all -> 0x010c }
        r0 = r0.equals(r1);	 Catch:{ all -> 0x010c }
        if (r0 != 0) goto L_0x0008;
    L_0x00cc:
        r0 = com.p016a.dn.m1519b();	 Catch:{ all -> 0x010c }
        r6.f804c = r0;	 Catch:{ all -> 0x010c }
        r1 = new com.a.cw;	 Catch:{ all -> 0x010c }
        r1.<init>(r6);	 Catch:{ all -> 0x010c }
        r1.m1407a(r9);	 Catch:{ all -> 0x010c }
        r0 = android.text.TextUtils.isEmpty(r8);	 Catch:{ all -> 0x010c }
        if (r0 == 0) goto L_0x013a;
    L_0x00e0:
        r0 = 0;
    L_0x00e1:
        r1.m1408a(r0);	 Catch:{ all -> 0x010c }
        r0 = r6.f803b;	 Catch:{ all -> 0x010c }
        r0 = r0.containsKey(r7);	 Catch:{ all -> 0x010c }
        if (r0 == 0) goto L_0x013f;
    L_0x00ec:
        r0 = r6.f803b;	 Catch:{ all -> 0x010c }
        r0 = r0.get(r7);	 Catch:{ all -> 0x010c }
        r0 = (java.util.ArrayList) r0;	 Catch:{ all -> 0x010c }
        r0.add(r1);	 Catch:{ all -> 0x010c }
    L_0x00f7:
        if (r11 == 0) goto L_0x0008;
    L_0x00f9:
        r0 = com.p016a.cx.m1410a();	 Catch:{ Throwable -> 0x0102 }
        r0.m1415a(r7, r9, r8, r10);	 Catch:{ Throwable -> 0x0102 }
        goto L_0x0008;
    L_0x0102:
        r0 = move-exception;
        r1 = "Cache";
        r2 = "add";
        com.p016a.ev.m1777a(r0, r1, r2);	 Catch:{ all -> 0x010c }
        goto L_0x0008;
    L_0x010c:
        r0 = move-exception;
        monitor-exit(r6);
        throw r0;
    L_0x010f:
        r0 = r9.m5341j();	 Catch:{ all -> 0x010c }
        r1 = 1092616192; // 0x41200000 float:10.0 double:5.398241246E-315;
        r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1));
        if (r0 > 0) goto L_0x0084;
    L_0x0119:
        goto L_0x0008;
    L_0x011b:
        r0 = "cgi";
        r0 = r7.contains(r0);	 Catch:{ all -> 0x010c }
        if (r0 == 0) goto L_0x00b3;
    L_0x0123:
        r0 = ",";
        r0 = r8.indexOf(r0);	 Catch:{ all -> 0x010c }
        r1 = -1;
        if (r0 != r1) goto L_0x0008;
    L_0x012c:
        r0 = r9.m5347m();	 Catch:{ all -> 0x010c }
        r1 = "4";
        r0 = r0.equals(r1);	 Catch:{ all -> 0x010c }
        if (r0 == 0) goto L_0x00b3;
    L_0x0138:
        goto L_0x0008;
    L_0x013a:
        r0 = r8.toString();	 Catch:{ all -> 0x010c }
        goto L_0x00e1;
    L_0x013f:
        r0 = new java.util.ArrayList;	 Catch:{ all -> 0x010c }
        r0.<init>();	 Catch:{ all -> 0x010c }
        r0.add(r1);	 Catch:{ all -> 0x010c }
        r1 = r6.f803b;	 Catch:{ all -> 0x010c }
        r1.put(r7, r0);	 Catch:{ all -> 0x010c }
        goto L_0x00f7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.cv.a(java.lang.String, java.lang.StringBuilder, com.autonavi.aps.amapapi.model.AmapLoc, android.content.Context, boolean):void");
    }

    public boolean m1402a(String str, AmapLoc amapLoc) {
        return !TextUtils.isEmpty(str) && dn.m1512a(amapLoc) && !str.startsWith("#") && str.contains("network");
    }

    public boolean m1403b() {
        return this.f804c == 0 ? false : this.f803b.size() > 360 ? true : dn.m1519b() - this.f804c > 36000000;
    }

    public boolean m1404b(String str, StringBuilder stringBuilder) {
        String[] split = str.split("#");
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < split.length) {
            if (split[i].contains(",nb") || split[i].contains(",access")) {
                arrayList.add(split[i]);
            }
            i++;
        }
        String[] split2 = stringBuilder.toString().split("#");
        i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < split2.length) {
            if (split2[i].contains(",nb") || split2[i].contains(",access")) {
                i2++;
                if (arrayList.contains(split2[i])) {
                    i3++;
                }
            }
            i++;
        }
        return ((double) (i3 * 2)) >= ((double) (arrayList.size() + i2)) * 0.618d;
    }

    public void m1405c() {
        this.f804c = 0;
        if (!this.f803b.isEmpty()) {
            this.f803b.clear();
        }
        this.f805d = false;
    }
}
