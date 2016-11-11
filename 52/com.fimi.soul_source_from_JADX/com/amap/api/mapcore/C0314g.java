package com.amap.api.mapcore;

/* renamed from: com.amap.api.mapcore.g */
class C0314g implements Runnable {
    final /* synthetic */ AMapDelegateImp f1927a;

    C0314g(AMapDelegateImp aMapDelegateImp) {
        this.f1927a = aMapDelegateImp;
    }

    public void run() {
        if (this.f1927a.aj != null) {
            this.f1927a.aT = false;
            this.f1927a.aj.setVisibility(8);
        }
    }
}
