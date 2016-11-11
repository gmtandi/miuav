package com.fimi.soul.biz.p090b;

import com.fimi.kernel.p084e.ak;
import com.fimi.soul.C1205R;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.entity.FlyActionBean;
import com.fimi.soul.module.p091a.C1664h;
import com.fimi.soul.utils.ad;
import com.fimi.soul.utils.aj;
import java.util.List;

/* renamed from: com.fimi.soul.biz.b.o */
public class C1257o implements C1250p {
    private C1433a f5698a;

    public C1257o(C1433a c1433a) {
        this.f5698a = c1433a;
    }

    public void m8623a() {
        C1664h.m10813a(this.f5698a).m10820a(1, 0.0d, 0.0d, 0, 22, 0, 0, 0, 0, 0);
    }

    public void m8624a(List<FlyActionBean> list) {
        if (list == null || list.size() <= 0) {
            ak.m8082a(this.f5698a.f6507c, (int) C1205R.string.no_poipoint);
            return;
        }
        FlyActionBean flyActionBean = (FlyActionBean) list.get(0);
        aj a = ad.m12228a(0.1d, flyActionBean.getLatLng().latitude, flyActionBean.getLatLng().longitude);
        int start_point_agle = (flyActionBean.getPara1() == 0 || flyActionBean.getPara1() == 1) ? (short) ((int) (((double) (360 - flyActionBean.getStart_point_agle())) / 1.412d)) : (short) ((int) (((double) flyActionBean.getStart_point_agle()) / 1.412d));
        C1664h.m10813a(this.f5698a).m10820a(1, a.m12252b(), a.m12250a(), flyActionBean.getHeight() * 10, flyActionBean.getOpration_Code(), flyActionBean.getRidus() * 10, flyActionBean.getSpeek() * 10, start_point_agle, flyActionBean.getPara1(), flyActionBean.getYaw_mode());
    }
}
