package com.mob.commons.appcollector;

import android.os.HandlerThread;

/* renamed from: com.mob.commons.appcollector.b */
class C2150b extends HandlerThread {
    final /* synthetic */ PackageCollector f11325a;

    C2150b(PackageCollector packageCollector, String str) {
        this.f11325a = packageCollector;
        super(str);
    }

    public void run() {
        if (this.f11325a.f11292l.m13117a().size() == 0) {
            this.f11325a.f11292l.m13119a(this.f11325a.m13123a());
        } else {
            this.f11325a.m13128b();
        }
        super.run();
    }
}
