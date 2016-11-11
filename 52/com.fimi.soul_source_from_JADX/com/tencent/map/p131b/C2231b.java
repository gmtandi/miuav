package com.tencent.map.p131b;

import android.location.Location;
import org.json.JSONException;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

/* renamed from: com.tencent.map.b.b */
public class C2231b {
    private static C2231b f11564b;
    public String f11565a;
    private double f11566c;
    private double f11567d;
    private double f11568e;
    private double f11569f;
    private double f11570g;
    private double f11571h;
    private C2229a f11572i;
    private C2230b f11573j;
    private boolean f11574k;

    /* renamed from: com.tencent.map.b.b.a */
    public interface C2229a {
        void m13364a(double d, double d2);
    }

    /* renamed from: com.tencent.map.b.b.b */
    public final class C2230b extends Thread {
        private /* synthetic */ C2231b f11563a;

        public C2230b(C2231b c2231b) {
            this.f11563a = c2231b;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void run() {
            /*
            r8 = this;
            r6 = 4645040803167600640; // 0x4076800000000000 float:0.0 double:360.0;
            r1 = 0;
            r0 = r8.f11563a;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.f11565a;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.getBytes();	 Catch:{ Exception -> 0x0034 }
            r0 = com.tencent.map.p131b.C2257j.m13494a(r0);	 Catch:{ Exception -> 0x0034 }
            r2 = r8.f11563a;	 Catch:{ Exception -> 0x0034 }
            r3 = 1;
            r2.f11574k = r3;	 Catch:{ Exception -> 0x0034 }
            r2 = "http://ls.map.soso.com/deflect?c=1";
            r3 = "SOSO MAP LBS SDK";
            r0 = com.tencent.map.p131b.C2231b.m13367a(r2, r3, r0);	 Catch:{ Exception -> 0x0034 }
            r2 = r8.f11563a;	 Catch:{ Exception -> 0x0034 }
            r3 = 0;
            r2.f11574k = r3;	 Catch:{ Exception -> 0x0034 }
            r2 = r0.f11740a;	 Catch:{ Exception -> 0x0034 }
            r2 = com.tencent.map.p131b.C2257j.m13495b(r2);	 Catch:{ Exception -> 0x0034 }
            r3 = r8.f11563a;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.f11741b;	 Catch:{ Exception -> 0x0034 }
            com.tencent.map.p131b.C2231b.m13368a(r3, r2, r0);	 Catch:{ Exception -> 0x0034 }
        L_0x0033:
            return;
        L_0x0034:
            r0 = move-exception;
            r0 = r1;
        L_0x0036:
            r0 = r0 + 1;
            r2 = 3;
            if (r0 > r2) goto L_0x006a;
        L_0x003b:
            r2 = 2000; // 0x7d0 float:2.803E-42 double:9.88E-321;
            com.tencent.map.p131b.C2231b.C2230b.sleep(r2);	 Catch:{ Exception -> 0x0068 }
            r2 = r8.f11563a;	 Catch:{ Exception -> 0x0068 }
            r2 = r2.f11565a;	 Catch:{ Exception -> 0x0068 }
            r2 = r2.getBytes();	 Catch:{ Exception -> 0x0068 }
            r2 = com.tencent.map.p131b.C2257j.m13494a(r2);	 Catch:{ Exception -> 0x0068 }
            r3 = "http://ls.map.soso.com/deflect?c=1";
            r4 = "SOSO MAP LBS SDK";
            r2 = com.tencent.map.p131b.C2231b.m13367a(r3, r4, r2);	 Catch:{ Exception -> 0x0068 }
            r3 = r8.f11563a;	 Catch:{ Exception -> 0x0068 }
            r4 = 0;
            r3.f11574k = r4;	 Catch:{ Exception -> 0x0068 }
            r3 = r2.f11740a;	 Catch:{ Exception -> 0x0068 }
            r3 = com.tencent.map.p131b.C2257j.m13495b(r3);	 Catch:{ Exception -> 0x0068 }
            r4 = r8.f11563a;	 Catch:{ Exception -> 0x0068 }
            r2 = r2.f11741b;	 Catch:{ Exception -> 0x0068 }
            com.tencent.map.p131b.C2231b.m13368a(r4, r3, r2);	 Catch:{ Exception -> 0x0068 }
            goto L_0x0033;
        L_0x0068:
            r2 = move-exception;
            goto L_0x0036;
        L_0x006a:
            r0 = r8.f11563a;
            r0.f11574k = r1;
            r0 = r8.f11563a;
            r0 = r0.f11572i;
            if (r0 == 0) goto L_0x0033;
        L_0x0077:
            r0 = r8.f11563a;
            r0 = r0.f11572i;
            r0.m13364a(r6, r6);
            goto L_0x0033;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.map.b.b.b.run():void");
        }
    }

    public C2231b() {
        this.f11566c = 0.0d;
        this.f11567d = 0.0d;
        this.f11568e = 0.0d;
        this.f11569f = 0.0d;
        this.f11570g = 0.0d;
        this.f11571h = 0.0d;
        this.f11573j = null;
        this.f11574k = false;
        this.f11565a = C2915a.f14760f;
    }

    public static C2231b m13366a() {
        if (f11564b == null) {
            f11564b = new C2231b();
        }
        return f11564b;
    }

    public static C2262n m13367a(String str, String str2, byte[] bArr) {
        Object obj = 1;
        if (C2260l.m13507b() == null) {
            obj = null;
        }
        if (obj == null) {
            throw new C2263o();
        }
        try {
            return C2265q.m13513a(false, str, str2, null, bArr, false, true);
        } catch (Exception e) {
            throw e;
        }
    }

    static /* synthetic */ void m13368a(C2231b c2231b, byte[] bArr, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            stringBuffer.append(new String(bArr, str));
        } catch (Exception e) {
            if (c2231b.f11572i != null) {
                c2231b.f11572i.m13364a(360.0d, 360.0d);
            }
        }
        try {
            JSONObject jSONObject = new JSONObject(stringBuffer.toString()).getJSONObject("location");
            double d = jSONObject.getDouble("latitude");
            double d2 = jSONObject.getDouble("longitude");
            c2231b.f11570g = d - c2231b.f11568e;
            c2231b.f11571h = d2 - c2231b.f11569f;
            c2231b.f11566c = c2231b.f11568e;
            c2231b.f11567d = c2231b.f11569f;
            if (c2231b.f11572i != null) {
                c2231b.f11572i.m13364a(d, d2);
            }
        } catch (JSONException e2) {
            if (c2231b.f11572i != null) {
                c2231b.f11572i.m13364a(360.0d, 360.0d);
            }
        }
    }

    public static boolean m13370a(String str) {
        return str == null || str.trim().length() == 0;
    }

    public final void m13371a(double d, double d2, C2229a c2229a) {
        this.f11572i = c2229a;
        if (!(this.f11570g == 0.0d || this.f11571h == 0.0d)) {
            float[] fArr = new float[10];
            Location.distanceBetween(d, d2, this.f11566c, this.f11567d, fArr);
            if (fArr[0] < 1500.0f) {
                this.f11572i.m13364a(this.f11570g + d, this.f11571h + d2);
                return;
            }
        }
        if (!this.f11574k) {
            this.f11565a = "{\"source\":101,\"access_token\":\"160e7bd42dec9428721034e0146fc6dd\",\"location\":{\"latitude\":" + d + ",\"longitude\":" + d2 + "}\t}";
            this.f11568e = d;
            this.f11569f = d2;
            this.f11573j = new C2230b(this);
            this.f11573j.start();
        }
    }
}
