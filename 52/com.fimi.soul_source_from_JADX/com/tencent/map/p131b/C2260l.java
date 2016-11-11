package com.tencent.map.p131b;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* renamed from: com.tencent.map.b.l */
public final class C2260l {
    private static C2260l f11736b;
    private Context f11737a;

    private C2260l() {
    }

    public static C2260l m13506a() {
        if (f11736b == null) {
            f11736b = new C2260l();
        }
        return f11736b;
    }

    public static Context m13507b() {
        return C2260l.m13506a().f11737a;
    }

    public static boolean m13508c() {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) C2260l.m13506a().f11737a.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.getType() == 1) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean m13509d() {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) C2260l.m13506a().f11737a.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isAvailable();
            }
        } catch (Exception e) {
        }
        return false;
    }

    public final void m13510a(Context context) {
        if (this.f11737a == null) {
            this.f11737a = context.getApplicationContext();
        }
    }
}
