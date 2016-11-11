package com.amap.api.mapcore;

/* renamed from: com.amap.api.mapcore.i */
class C0316i implements Runnable {
    final /* synthetic */ AMapDelegateImp f1929a;

    C0316i(AMapDelegateImp aMapDelegateImp) {
        this.f1929a = aMapDelegateImp;
    }

    public synchronized void run() {
        if (this.f1929a.bb) {
            this.f1929a.ba = true;
            this.f1929a.bb = false;
        }
    }
}
