package com.amap.api.mapcore;

import com.amap.api.mapcore.util.ce;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

/* renamed from: com.amap.api.mapcore.x */
class C0411x implements Runnable {
    final /* synthetic */ C0410w f2554a;

    C0411x(C0410w c0410w) {
        this.f2554a = c0410w;
    }

    public synchronized void run() {
        try {
            synchronized (this.f2554a) {
                Collection arrayList = new ArrayList(this.f2554a.f2550d);
                Collections.sort(arrayList, this.f2554a.f2549b);
                this.f2554a.f2550d = new CopyOnWriteArrayList(arrayList);
            }
        } catch (Throwable th) {
            ce.m3829a(th, "MapOverlayImageView", "changeOverlayIndex");
        }
    }
}
