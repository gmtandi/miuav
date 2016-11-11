package com.p016a;

import android.content.Context;

/* renamed from: com.a.ar */
final class ar extends Thread {
    final /* synthetic */ Context f566a;
    final /* synthetic */ String f567b;
    final /* synthetic */ String f568c;

    ar(Context context, String str, String str2) {
        this.f566a = context;
        this.f567b = str;
        this.f568c = str2;
    }

    public void run() {
        try {
            aq.f558c.m1065a(this.f566a, this.f567b, this.f568c);
        } catch (Throwable th) {
            C0247g.m1917a(th, "DynamicClassLoader", "getInstance()");
        }
    }
}
