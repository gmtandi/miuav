package com.amap.api.mapcore;

/* renamed from: com.amap.api.mapcore.f */
class C0313f implements Runnable {
    final /* synthetic */ AMapDelegateImp f1926a;

    C0313f(AMapDelegateImp aMapDelegateImp) {
        this.f1926a = aMapDelegateImp;
    }

    public void run() {
        if (this.f1926a.aj != null) {
            this.f1926a.aT = true;
            if (this.f1926a.al != null) {
                this.f1926a.al.m2171c(false);
            }
        }
    }
}
