package com.p016a;

import android.content.Context;
import android.text.TextUtils;
import java.util.Hashtable;
import org.json.JSONObject;

/* renamed from: com.a.cz */
public class cz {
    private static cz f816a;
    private Hashtable<String, JSONObject> f817b;
    private boolean f818c;

    static {
        f816a = null;
    }

    private cz() {
        this.f817b = new Hashtable();
        this.f818c = false;
    }

    public static synchronized cz m1420a() {
        cz czVar;
        synchronized (cz.class) {
            if (f816a == null) {
                f816a = new cz();
            }
            czVar = f816a;
        }
        return czVar;
    }

    private void m1421d() {
        if (!this.f817b.isEmpty()) {
            this.f817b.clear();
        }
    }

    public void m1422a(Context context) {
        if (cf.f742a && !this.f818c) {
            dn.m1519b();
            try {
                cx.m1410a().m1417b(context);
            } catch (Throwable th) {
                ev.m1777a(th, "HeatMap", "loadDB");
            }
            this.f818c = true;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void m1423a(android.content.Context r10, java.lang.String r11, com.autonavi.aps.amapapi.model.AmapLoc r12) {
        /*
        r9 = this;
        monitor-enter(r9);
        r3 = 0;
        r0 = com.p016a.dn.m1512a(r12);	 Catch:{ all -> 0x0071 }
        if (r0 == 0) goto L_0x000e;
    L_0x0008:
        if (r10 == 0) goto L_0x000e;
    L_0x000a:
        r0 = com.p016a.cf.f742a;	 Catch:{ all -> 0x0071 }
        if (r0 != 0) goto L_0x0010;
    L_0x000e:
        monitor-exit(r9);
        return;
    L_0x0010:
        r0 = r9.f817b;	 Catch:{ all -> 0x0071 }
        r0 = r0.size();	 Catch:{ all -> 0x0071 }
        r1 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        if (r0 <= r1) goto L_0x002e;
    L_0x001a:
        r0 = r12.m5339i();	 Catch:{ all -> 0x0071 }
        r2 = r12.m5337h();	 Catch:{ all -> 0x0071 }
        r3 = com.p016a.cl.m1298a(r0, r2);	 Catch:{ all -> 0x0071 }
        r0 = r9.f817b;	 Catch:{ all -> 0x0071 }
        r0 = r0.containsKey(r3);	 Catch:{ all -> 0x0071 }
        if (r0 == 0) goto L_0x000e;
    L_0x002e:
        if (r3 != 0) goto L_0x003c;
    L_0x0030:
        r0 = r12.m5339i();	 Catch:{ all -> 0x0071 }
        r2 = r12.m5337h();	 Catch:{ all -> 0x0071 }
        r3 = com.p016a.cl.m1298a(r0, r2);	 Catch:{ all -> 0x0071 }
    L_0x003c:
        r0 = new org.json.JSONObject;	 Catch:{ all -> 0x0071 }
        r0.<init>();	 Catch:{ all -> 0x0071 }
        r1 = "key";
        r0.put(r1, r11);	 Catch:{ Throwable -> 0x0068 }
        r1 = "lat";
        r4 = r12.m5339i();	 Catch:{ Throwable -> 0x0068 }
        r0.put(r1, r4);	 Catch:{ Throwable -> 0x0068 }
        r1 = "lon";
        r4 = r12.m5337h();	 Catch:{ Throwable -> 0x0068 }
        r0.put(r1, r4);	 Catch:{ Throwable -> 0x0068 }
        r4 = r0.toString();	 Catch:{ Throwable -> 0x0068 }
        r5 = 1;
        r6 = com.p016a.dn.m1502a();	 Catch:{ Throwable -> 0x0068 }
        r8 = 1;
        r1 = r9;
        r2 = r10;
        r1.m1424a(r2, r3, r4, r5, r6, r8);	 Catch:{ Throwable -> 0x0068 }
        goto L_0x000e;
    L_0x0068:
        r0 = move-exception;
        r1 = "HeatMap";
        r2 = "update";
        com.p016a.ev.m1777a(r0, r1, r2);	 Catch:{ all -> 0x0071 }
        goto L_0x000e;
    L_0x0071:
        r0 = move-exception;
        monitor-exit(r9);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.cz.a(android.content.Context, java.lang.String, com.autonavi.aps.amapapi.model.AmapLoc):void");
    }

    public synchronized void m1424a(Context context, String str, String str2, int i, long j, boolean z) {
        if (context != null) {
            if (!TextUtils.isEmpty(str)) {
                if (cf.f742a) {
                    JSONObject jSONObject = (JSONObject) this.f817b.get(str);
                    JSONObject jSONObject2 = jSONObject == null ? new JSONObject() : jSONObject;
                    try {
                        jSONObject2.put("x", str2);
                        jSONObject2.put("time", j);
                        if (this.f817b.containsKey(str)) {
                            jSONObject2.put("num", jSONObject2.getInt("num") + i);
                        } else {
                            jSONObject2.put("num", i);
                        }
                    } catch (Throwable th) {
                        ev.m1777a(th, "HeatMap", "update1");
                    }
                    this.f817b.put(str, jSONObject2);
                    if (z) {
                        try {
                            cx.m1410a().m1414a(context, str, str2, j);
                        } catch (Throwable th2) {
                            ev.m1777a(th2, "HeatMap", "update");
                        }
                    }
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.util.ArrayList<com.p016a.cy> m1425b() {
        /*
        r10 = this;
        monitor-enter(r10);
        r6 = new java.util.ArrayList;	 Catch:{ all -> 0x005a }
        r6.<init>();	 Catch:{ all -> 0x005a }
        r0 = r10.f817b;	 Catch:{ all -> 0x005a }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x005a }
        if (r0 == 0) goto L_0x0011;
    L_0x000e:
        r0 = r6;
    L_0x000f:
        monitor-exit(r10);
        return r0;
    L_0x0011:
        r7 = r10.f817b;	 Catch:{ all -> 0x005a }
        r8 = new java.util.ArrayList;	 Catch:{ all -> 0x005a }
        r0 = r7.keySet();	 Catch:{ all -> 0x005a }
        r8.<init>(r0);	 Catch:{ all -> 0x005a }
        r9 = r8.iterator();	 Catch:{ all -> 0x005a }
    L_0x0020:
        r0 = r9.hasNext();	 Catch:{ all -> 0x005a }
        if (r0 == 0) goto L_0x005d;
    L_0x0026:
        r1 = r9.next();	 Catch:{ all -> 0x005a }
        r1 = (java.lang.String) r1;	 Catch:{ all -> 0x005a }
        r0 = r7.get(r1);	 Catch:{ Throwable -> 0x0051 }
        r0 = (org.json.JSONObject) r0;	 Catch:{ Throwable -> 0x0051 }
        r2 = "num";
        r4 = r0.getInt(r2);	 Catch:{ Throwable -> 0x0051 }
        r2 = "x";
        r5 = r0.getString(r2);	 Catch:{ Throwable -> 0x0051 }
        r2 = "time";
        r2 = r0.getLong(r2);	 Catch:{ Throwable -> 0x0051 }
        r0 = 120; // 0x78 float:1.68E-43 double:5.93E-322;
        if (r4 < r0) goto L_0x0020;
    L_0x0048:
        r0 = new com.a.cy;	 Catch:{ Throwable -> 0x0051 }
        r0.<init>(r1, r2, r4, r5);	 Catch:{ Throwable -> 0x0051 }
        r6.add(r0);	 Catch:{ Throwable -> 0x0051 }
        goto L_0x0020;
    L_0x0051:
        r0 = move-exception;
        r1 = "HeatMap";
        r2 = "hot";
        com.p016a.ev.m1777a(r0, r1, r2);	 Catch:{ all -> 0x005a }
        goto L_0x0020;
    L_0x005a:
        r0 = move-exception;
        monitor-exit(r10);
        throw r0;
    L_0x005d:
        r0 = new com.a.da;	 Catch:{ all -> 0x005a }
        r0.<init>(r10);	 Catch:{ all -> 0x005a }
        java.util.Collections.sort(r6, r0);	 Catch:{ all -> 0x005a }
        r8.clear();	 Catch:{ all -> 0x005a }
        r0 = r6;
        goto L_0x000f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.cz.b():java.util.ArrayList<com.a.cy>");
    }

    public void m1426c() {
        cz.m1420a().m1421d();
        this.f818c = false;
    }
}
