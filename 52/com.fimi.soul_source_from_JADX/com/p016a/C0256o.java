package com.p016a;

import android.content.Context;

/* renamed from: com.a.o */
final class C0256o implements Runnable {
    final /* synthetic */ Context f1301a;
    final /* synthetic */ gd f1302b;
    final /* synthetic */ String f1303c;

    C0256o(Context context, gd gdVar, String str) {
        this.f1301a = context;
        this.f1302b = gdVar;
        this.f1303c = str;
    }

    public void run() {
        try {
            C0255n.m2028a(this.f1301a, 1).m2011a(this.f1302b, this.f1301a, new Throwable("gpsstatistics"), this.f1303c, null, null);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
