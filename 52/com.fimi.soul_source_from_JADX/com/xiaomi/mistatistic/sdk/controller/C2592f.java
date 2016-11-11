package com.xiaomi.mistatistic.sdk.controller;

import android.os.Handler;
import android.os.HandlerThread;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.xiaomi.mistatistic.sdk.controller.f */
class C2592f extends HandlerThread {
    final /* synthetic */ C2589b f12962a;

    public C2592f(C2589b c2589b, String str) {
        this.f12962a = c2589b;
        super(str);
    }

    protected void onLooperPrepared() {
        this.f12962a.f12956c = new Handler();
        ArrayList arrayList = null;
        synchronized (this.f12962a.f12957d) {
            if (!this.f12962a.f12957d.isEmpty()) {
                arrayList = (ArrayList) this.f12962a.f12957d.clone();
                this.f12962a.f12957d.clear();
            }
        }
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((C2582e) it.next()).m14697a();
            }
        }
        super.onLooperPrepared();
    }
}
