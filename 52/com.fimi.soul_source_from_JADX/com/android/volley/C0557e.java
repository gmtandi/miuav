package com.android.volley;

/* renamed from: com.android.volley.e */
class C0557e implements Runnable {
    final /* synthetic */ C0570r f3519a;
    final /* synthetic */ C0556d f3520b;

    C0557e(C0556d c0556d, C0570r c0570r) {
        this.f3520b = c0556d;
        this.f3519a = c0570r;
    }

    public void run() {
        try {
            this.f3520b.f3515c.put(this.f3519a);
        } catch (InterruptedException e) {
        }
    }
}
