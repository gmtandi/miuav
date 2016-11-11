package com.fimi.soul.biz.p090b;

import com.amap.api.maps.model.LatLng;
import com.fimi.kernel.p084e.ak;
import com.fimi.soul.C1205R;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.p117h.as;
import com.fimi.soul.drone.p117h.at;
import com.fimi.soul.entity.FlyActionBean;
import com.fimi.soul.module.p091a.C1664h;
import com.fimi.soul.utils.ad;
import com.fimi.soul.utils.aj;
import java.util.List;

/* renamed from: com.fimi.soul.biz.b.t */
public class C1262t implements C1250p {
    private List<FlyActionBean> f5710a;
    private C1433a f5711b;
    private volatile int f5712c;

    public C1262t(C1433a c1433a) {
        this.f5712c = 1;
        this.f5711b = c1433a;
    }

    public void m8640a() {
        as k = this.f5711b.m9608k();
        if (k.m10386a() < this.f5712c || this.f5712c != k.m10386a() || k.m10393g() != 0.0d || this.f5710a == null || this.f5712c > this.f5710a.size()) {
            return;
        }
        if (k.m10386a() == this.f5710a.size()) {
            C1664h.m10813a(this.f5711b).m10841q();
            return;
        }
        this.f5712c++;
        m8641a(this.f5712c);
    }

    public void m8641a(int i) {
        if (this.f5710a.size() >= 1 && i <= this.f5710a.size()) {
            FlyActionBean flyActionBean = (FlyActionBean) this.f5710a.get(i - 1);
            aj a = ad.m12228a(0.1d, flyActionBean.getLatLng().latitude, flyActionBean.getLatLng().longitude);
            LatLng latLng = new LatLng(a.m12250a(), a.m12252b());
            C1664h.m10813a(this.f5711b).m10822a(i, latLng.longitude, latLng.latitude, (short) flyActionBean.getHeight(), (short) this.f5710a.size());
        }
    }

    public void m8642a(at atVar) {
        List h = C1247f.m8565k().m8580h();
        List f = C1247f.m8565k().m8578f();
        aj a = ad.m12227a(atVar.m10398c(), atVar.m10397b());
        LatLng latLng = new LatLng(a.m12250a(), a.m12252b());
        if (!(h == null || h.contains(latLng) || atVar.m10403h() == 0)) {
            h.add(latLng);
            FlyActionBean flyActionBean = new FlyActionBean();
            flyActionBean.setLatLng(latLng);
            flyActionBean.setHeight((int) atVar.m10399d());
            flyActionBean.setType(1);
            flyActionBean.setModelType(1);
            if (!(f == null || f.contains(flyActionBean))) {
                f.add(flyActionBean);
            }
        }
        if (atVar.m10403h() <= h.size()) {
            this.f5711b.m9589a(C1584h.RESHWAYPOINT);
        } else {
            m8644b(atVar.m10395a() + 1);
        }
    }

    public void m8643a(List<FlyActionBean> list) {
        if (list == null || list.size() > 0) {
            this.f5712c = 1;
            this.f5710a = list;
            m8641a(this.f5712c);
            return;
        }
        ak.m8082a(this.f5711b.f6507c, (int) C1205R.string.no_waypoint);
    }

    public void m8644b(int i) {
        C1664h.m10813a(this.f5711b).m10819a(i);
    }
}
