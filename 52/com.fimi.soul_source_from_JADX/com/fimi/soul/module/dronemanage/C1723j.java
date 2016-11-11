package com.fimi.soul.module.dronemanage;

import com.amap.api.maps.AMap.CancelableCallback;

/* renamed from: com.fimi.soul.module.dronemanage.j */
class C1723j implements CancelableCallback {
    final /* synthetic */ C1722i f8495a;

    C1723j(C1722i c1722i) {
        this.f8495a = c1722i;
    }

    public void onCancel() {
        this.f8495a.f8483l = true;
    }

    public void onFinish() {
        if (this.f8495a.f8476e >= 2) {
            this.f8495a.f8483l = false;
            this.f8495a.f8476e = 0;
        }
    }
}
