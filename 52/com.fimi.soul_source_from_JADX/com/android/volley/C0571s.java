package com.android.volley;

/* renamed from: com.android.volley.s */
class C0571s implements Runnable {
    final /* synthetic */ String f3563a;
    final /* synthetic */ long f3564b;
    final /* synthetic */ C0570r f3565c;

    C0571s(C0570r c0570r, String str, long j) {
        this.f3565c = c0570r;
        this.f3563a = str;
        this.f3564b = j;
    }

    public void run() {
        this.f3565c.f3548b.m5065a(this.f3563a, this.f3564b);
        this.f3565c.f3548b.m5064a(toString());
    }
}
