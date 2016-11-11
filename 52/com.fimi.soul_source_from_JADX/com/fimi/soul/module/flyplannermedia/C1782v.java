package com.fimi.soul.module.flyplannermedia;

import com.fimi.soul.biz.camera.C1276b;
import com.fimi.soul.biz.camera.entity.X11FileInfo;
import com.fimi.soul.biz.camera.p094c.C1290m;
import com.fimi.soul.entity.WifiDistanceFile;
import com.fimi.soul.utils.C1969i;

/* renamed from: com.fimi.soul.module.flyplannermedia.v */
class C1782v implements C1776p<X11FileInfo> {
    final /* synthetic */ DroneOnlineFragment f8718a;

    C1782v(DroneOnlineFragment droneOnlineFragment) {
        this.f8718a = droneOnlineFragment;
    }

    public boolean m11460a(X11FileInfo x11FileInfo) {
        int fileType = WifiDistanceFile.getFileType(x11FileInfo.getName());
        this.f8718a.f8682e = this.f8718a.f8682e + 1;
        if (fileType == 1) {
            if (C1276b.m8689b(C1276b.m8681a(x11FileInfo))) {
                this.f8718a.m11429a(x11FileInfo, String.format("file://%s", new Object[]{C1969i.m12475a(C1276b.m8681a(x11FileInfo))}));
                this.f8718a.f8684g.m11455b();
            } else {
                this.f8718a.m11404m().m8874s().m8769a(x11FileInfo.getName(), x11FileInfo.getAbsolutePath(), x11FileInfo.getCreateDate(), C1290m.Thumb);
            }
        }
        if (fileType == 2) {
            this.f8718a.f8689m = true;
            this.f8718a.m11404m().m8874s().m8784g(x11FileInfo.getAbsolutePath());
        }
        return true;
    }
}
