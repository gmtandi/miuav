package com.fimi.soul.biz.p103k;

import android.os.Message;
import com.fimi.soul.entity.UpgradeResultInfo;

/* renamed from: com.fimi.soul.biz.k.az */
class az implements Runnable {
    final /* synthetic */ ay f6123a;
    private int f6124b;
    private String f6125c;
    private UpgradeResultInfo f6126d;

    public az(ay ayVar, int i) {
        this.f6123a = ayVar;
        this.f6124b = i;
    }

    public az(ay ayVar, int i, UpgradeResultInfo upgradeResultInfo) {
        this.f6123a = ayVar;
        this.f6124b = i;
        this.f6126d = upgradeResultInfo;
    }

    public az(ay ayVar, int i, String str) {
        this.f6123a = ayVar;
        this.f6124b = i;
        this.f6125c = str;
    }

    public void run() {
        Message obtainMessage = this.f6123a.f6120g.obtainMessage();
        Object obj = null;
        if (this.f6124b == 0) {
            obj = this.f6123a.f6119f.m9102a(this.f6123a.f6122i);
        } else if (this.f6124b == 1) {
            obj = this.f6123a.f6119f.m9104a(this.f6123a.f6122i, this.f6125c);
        } else if (this.f6124b == 2) {
            obj = this.f6123a.f6119f.m9105b(this.f6123a.f6122i);
        } else if (this.f6124b == 3) {
            obj = this.f6123a.f6119f.m9103a(this.f6123a.f6122i, this.f6126d);
        }
        obtainMessage.what = this.f6124b;
        obtainMessage.obj = obj;
        this.f6123a.f6120g.sendMessage(obtainMessage);
    }
}
