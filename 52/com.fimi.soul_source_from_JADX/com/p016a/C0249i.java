package com.p016a;

import android.content.Context;
import android.os.Looper;

/* renamed from: com.a.i */
class C0249i implements Runnable {
    final /* synthetic */ Context f1281a;
    final /* synthetic */ gd f1282b;
    final /* synthetic */ boolean f1283c;
    final /* synthetic */ C0248h f1284d;

    C0249i(C0248h c0248h, Context context, gd gdVar, boolean z) {
        this.f1284d = c0248h;
        this.f1281a = context;
        this.f1282b = gdVar;
        this.f1283c = z;
    }

    public void run() {
        try {
            synchronized (Looper.getMainLooper()) {
                new ac(this.f1281a, true).m985a(this.f1282b);
            }
            if (this.f1283c) {
                synchronized (Looper.getMainLooper()) {
                    ae aeVar = new ae(this.f1281a);
                    ag agVar = new ag();
                    agVar.m1009c(true);
                    agVar.m1005a(true);
                    agVar.m1007b(true);
                    aeVar.m998a(agVar);
                }
                C0255n.m2030a(this.f1284d.f1280d);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
