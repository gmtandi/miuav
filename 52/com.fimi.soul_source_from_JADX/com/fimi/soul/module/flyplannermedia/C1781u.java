package com.fimi.soul.module.flyplannermedia;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.fimi.soul.biz.camera.entity.X11FileInfo;
import com.fimi.soul.entity.WifiDistanceFile;

/* renamed from: com.fimi.soul.module.flyplannermedia.u */
class C1781u implements OnClickListener {
    final /* synthetic */ C1779s f8717a;

    C1781u(C1779s c1779s) {
        this.f8717a = c1779s;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.f8717a.f8715a.f8683f = 0;
        this.f8717a.f8715a.f8689m = false;
        if (this.f8717a.f8715a.f8685h.size() != 0) {
            this.f8717a.f8715a.f8686i.clear();
            for (int i2 = 0; i2 < this.f8717a.f8715a.f8685h.size(); i2++) {
                for (X11FileInfo x11FileInfo : this.f8717a.f8715a.m11404m().m8855k().getCurDirFileList()) {
                    if (((WifiDistanceFile) this.f8717a.f8715a.f8685h.get(i2)).getName().equals(x11FileInfo.getName())) {
                        this.f8717a.f8715a.f8686i.add(x11FileInfo);
                        break;
                    }
                }
            }
            this.f8717a.f8715a.m11404m().m8874s().m8777c(((WifiDistanceFile) this.f8717a.f8715a.f8685h.get(this.f8717a.f8715a.f8683f)).getAbsolutePath());
            this.f8717a.f8715a.m11399h(false);
            this.f8717a.f8715a.m11397g(true);
        }
    }
}
