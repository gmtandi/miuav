package com.fimi.soul.biz.p090b;

import com.amap.api.maps.model.LatLng;
import com.fimi.kernel.p084e.ak;
import com.fimi.soul.C1205R;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.entity.FlyActionBean;
import com.fimi.soul.module.p091a.C1664h;
import com.fimi.soul.utils.ad;
import com.fimi.soul.utils.aj;
import java.util.List;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.fimi.soul.biz.b.i */
public class C1251i implements C1250p {
    private C1433a f5681a;

    public C1251i(C1433a c1433a) {
        this.f5681a = c1433a;
    }

    public void m8595a() {
        C1664h.m10813a(this.f5681a).m10821a(0, 0.0d, 0.0d, (short) 0, (int) Opcodes.TABLESWITCH);
    }

    public void m8596a(List<FlyActionBean> list) {
        if (list == null || list.size() <= 0) {
            ak.m8082a(this.f5681a.f6507c, (int) C1205R.string.no_flytopoint);
            return;
        }
        FlyActionBean flyActionBean = (FlyActionBean) list.get(0);
        aj a = ad.m12228a(0.1d, flyActionBean.getLatLng().latitude, flyActionBean.getLatLng().longitude);
        LatLng latLng = new LatLng(a.m12250a(), a.m12252b());
        C1664h.m10813a(this.f5681a).m10821a(1, latLng.longitude, latLng.latitude, (short) flyActionBean.getHeight(), 85);
    }
}
