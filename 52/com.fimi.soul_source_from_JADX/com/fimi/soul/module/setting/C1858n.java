package com.fimi.soul.module.setting;

import com.fimi.kernel.p076b.C1153f;
import com.fimi.soul.entity.APConfig;

/* renamed from: com.fimi.soul.module.setting.n */
class C1858n implements C1153f<APConfig> {
    final /* synthetic */ C1852h f9313a;

    C1858n(C1852h c1852h) {
        this.f9313a = c1852h;
    }

    public void m11741a(APConfig aPConfig) {
        if (aPConfig != null) {
            this.f9313a.f9306h = aPConfig;
            this.f9313a.f9299a.setText(aPConfig.getSsid());
            this.f9313a.f9300b.setText(aPConfig.getApPwd());
            this.f9313a.f9302d.setText(aPConfig.getIpAddr());
            this.f9313a.f9303e.setText(aPConfig.getVersion());
            this.f9313a.f9304f.setText(aPConfig.getCountryCode());
        }
    }

    public void m11743b(APConfig aPConfig) {
    }
}
