package com.fimi.soul.biz.update;

import com.fimi.kernel.p076b.C1153f;
import com.fimi.soul.entity.APConfig;
import com.fimi.soul.entity.UpdateVersonBean;

/* renamed from: com.fimi.soul.biz.update.d */
class C1407d implements C1153f<APConfig> {
    final /* synthetic */ aj f6351a;
    final /* synthetic */ C1404a f6352b;

    C1407d(C1404a c1404a, aj ajVar) {
        this.f6352b = c1404a;
        this.f6351a = ajVar;
    }

    public void m9445a(APConfig aPConfig) {
        UpdateVersonBean updateVersonBean = new UpdateVersonBean();
        if (!(aPConfig == null || aPConfig.getVersion() == null)) {
            String[] split = aPConfig.getVersion().split("_");
            updateVersonBean.setNewVersion(split[split.length - 1]);
            this.f6352b.m9383a(updateVersonBean);
        }
        this.f6351a.m9421a(updateVersonBean);
    }

    public void m9447b(APConfig aPConfig) {
        this.f6351a.m9421a(new UpdateVersonBean());
    }
}
