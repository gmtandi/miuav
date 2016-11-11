package com.amap.api.mapcore;

import com.amap.api.mapcore.util.ce;

class ay implements Runnable {
    final /* synthetic */ aw f1643a;

    ay(aw awVar) {
        this.f1643a = awVar;
    }

    public void run() {
        try {
            this.f1643a.f1631a.m2318q();
        } catch (Throwable th) {
            ce.m3829a(th, "MapOverlayImageView", "redrawInfoWindow post");
            th.printStackTrace();
        }
    }
}
