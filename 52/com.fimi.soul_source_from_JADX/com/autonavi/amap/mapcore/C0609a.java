package com.autonavi.amap.mapcore;

/* renamed from: com.autonavi.amap.mapcore.a */
class C0609a implements Runnable {
    public BaseMapLoader f3716a;

    public C0609a(BaseMapLoader baseMapLoader) {
        this.f3716a = null;
        this.f3716a = baseMapLoader;
    }

    public void run() {
        try {
            this.f3716a.doRequest();
        } catch (Throwable th) {
        }
    }
}
