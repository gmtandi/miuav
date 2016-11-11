package com.p016a;

import android.content.Context;

/* renamed from: com.a.p */
final class C0257p implements Runnable {
    final /* synthetic */ Context f1304a;
    final /* synthetic */ int f1305b;
    final /* synthetic */ Throwable f1306c;
    final /* synthetic */ String f1307d;
    final /* synthetic */ String f1308e;

    C0257p(Context context, int i, Throwable th, String str, String str2) {
        this.f1304a = context;
        this.f1305b = i;
        this.f1306c = th;
        this.f1307d = str;
        this.f1308e = str2;
    }

    public void run() {
        try {
            C0251r a = C0255n.m2028a(this.f1304a, this.f1305b);
            if (a != null) {
                a.m2009a(this.f1304a, this.f1306c, this.f1307d, this.f1308e);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
