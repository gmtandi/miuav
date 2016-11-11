package com.fimi.soul.module.droneFragment;

import com.amap.api.maps.model.Marker;
import com.fimi.soul.biz.p090b.C1247f;
import com.fimi.soul.biz.p090b.C1255m;
import com.fimi.soul.entity.FlyActionBean;
import com.fimi.soul.view.myhorizontalseebar.C1684l;
import com.fimi.soul.view.myhorizontalseebar.SeekBar;

/* renamed from: com.fimi.soul.module.droneFragment.n */
class C1700n implements C1684l {
    final /* synthetic */ C1697k f8323a;

    C1700n(C1697k c1697k) {
        this.f8323a = c1697k;
    }

    public void m11135a(SeekBar seekBar) {
        this.f8323a.f8318x = true;
    }

    public void m11136a(SeekBar seekBar, int i, boolean z) {
        if (this.f8323a.f8318x) {
            FlyActionBean j = this.f8323a.f8290E.m8582j();
            if (j != null) {
                if (i < 5) {
                    i = 5;
                }
                this.f8323a.f8295a.setText(i + "m");
                j.setHeight(i);
                for (Marker marker : C1247f.m8565k().m8577e()) {
                    if (marker.getObject().equals(j)) {
                        marker.setIcon(C1255m.m8606a(this.f8323a.f8309o, j.getDrawableRes(), j.getHeight(), true));
                        return;
                    }
                }
            }
        }
    }

    public void m11137b(SeekBar seekBar) {
        this.f8323a.f8318x = false;
    }
}
