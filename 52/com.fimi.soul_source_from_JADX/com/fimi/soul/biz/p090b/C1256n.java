package com.fimi.soul.biz.p090b;

import android.content.Context;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.Polyline;
import com.fimi.kernel.p084e.ak;
import com.fimi.soul.C1205R;
import com.fimi.soul.biz.p101h.C1348c;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.p117h.ao;
import com.fimi.soul.entity.FlyActionBean;
import com.fimi.soul.utils.ac;
import com.fimi.soul.utils.ad;
import com.fimi.soul.utils.aj;
import java.util.List;

/* renamed from: com.fimi.soul.biz.b.n */
public class C1256n implements C1242a {
    private AMap f5692a;
    private Context f5693b;
    private C1433a f5694c;
    private C1247f f5695d;
    private Marker f5696e;
    private Polyline f5697f;

    public C1256n(AMap aMap, Context context, C1433a c1433a) {
        this.f5694c = c1433a;
        this.f5692a = aMap;
        this.f5693b = context;
        this.f5695d = C1247f.m8565k();
    }

    public void m8610a() {
        ao t = this.f5694c.m9617t();
        m8611a((double) t.m10361e(), (double) t.m10360d());
    }

    public void m8611a(double d, double d2) {
        aj a = ad.m12227a(d, d2);
        m8613a(new LatLng(a.m12250a(), a.m12252b()), 0);
    }

    public void m8612a(LatLng latLng) {
        List b = C1247f.m8565k().m8574b();
        if (b != null && !b.contains(latLng)) {
            b.add(latLng);
            if (b.size() < 2) {
                return;
            }
            if (this.f5697f == null) {
                this.f5697f = this.f5692a.addPolyline(C1254l.m8604a(b, 4, this.f5693b.getResources().getColor(C1205R.color.drone_inface_line)).zIndex(50.0f));
            } else {
                this.f5697f.setPoints(b);
            }
        }
    }

    public void m8613a(LatLng latLng, int i) {
        for (Circle contains : C1348c.m9015a().m9016b()) {
            if (contains.contains(latLng)) {
                ak.m8083a(this.f5694c.f6507c, (int) C1205R.string.flyzonwaypoint, 3000);
                return;
            }
        }
        aj a = ad.m12227a(this.f5694c.m9618u().m10301b(), this.f5694c.m9618u().m10302c());
        double a2 = ac.m12223c(latLng, new LatLng(a.m12250a(), a.m12252b())).m12254a();
        if (a2 <= 500.0d) {
            List f = this.f5695d.m8578f();
            if (this.f5696e == null) {
                FlyActionBean flyActionBean = new FlyActionBean();
                flyActionBean.setLatLng(latLng);
                flyActionBean.setDrawableRes(C1205R.drawable.img_fly_flag_blue);
                flyActionBean.setCanclick(true);
                flyActionBean.setType(2);
                flyActionBean.setModelType(3);
                flyActionBean.setStyleInfo(1);
                flyActionBean.setHeight(this.f5695d.m8579g());
                this.f5696e = this.f5692a.addMarker(C1254l.m8603a(latLng, this.f5694c.f6507c, this.f5695d.m8579g(), true, C1205R.drawable.img_fly_flag_blue));
                this.f5696e.setObject(flyActionBean);
                this.f5696e.setZIndex(1000.0f);
                this.f5696e.setAnchor(0.2f, 0.8f);
                this.f5696e.setDraggable(false);
                this.f5695d.m8571a(flyActionBean);
                if (!f.contains(flyActionBean)) {
                    f.add(flyActionBean);
                }
                f = this.f5695d.m8577e();
                if (!(f == null || f.contains(this.f5696e))) {
                    f.add(this.f5696e);
                }
                this.f5694c.m9589a(C1584h.SHOWPOIOPERA);
                return;
            }
            ((FlyActionBean) this.f5696e.getObject()).setLatLng(latLng);
            this.f5696e.setPosition(latLng);
        } else if (a2 > 500.0d) {
            ak.m8082a(this.f5693b, (int) C1205R.string.outterwaypoint);
        }
    }

    public void m8614a(FlyActionBean flyActionBean) {
        List f = this.f5695d.m8578f();
        LatLng latLng = flyActionBean.getLatLng();
        if (this.f5696e == null) {
            this.f5696e = this.f5692a.addMarker(C1254l.m8603a(latLng, this.f5694c.f6507c, this.f5695d.m8579g(), true, C1205R.drawable.img_fly_flag_blue));
            this.f5696e.setObject(flyActionBean);
            this.f5696e.setZIndex(1000.0f);
            this.f5696e.setAnchor(0.2f, 0.8f);
            this.f5696e.setDraggable(false);
            this.f5695d.m8571a(flyActionBean);
            if (!f.contains(flyActionBean)) {
                f.add(flyActionBean);
            }
            f = this.f5695d.m8577e();
            if (!(f == null || f.contains(this.f5696e))) {
                f.add(this.f5696e);
            }
            this.f5694c.m9589a(C1584h.SHOWPOIOPERA);
        }
    }

    public void m8615b() {
        if (this.f5696e != null) {
            this.f5696e.setIcon(C1255m.m8605a(C1205R.drawable.img_fly_flag_red));
        }
        C1253k.m8598a(this.f5693b).m8600a(0);
    }

    public void m8616c() {
        if (this.f5696e != null) {
            FlyActionBean flyActionBean = (FlyActionBean) this.f5696e.getObject();
            if (flyActionBean != null) {
                flyActionBean.setStyleInfo(1);
            }
            this.f5696e.setTitle(this.f5694c.f6507c.getString(C1205R.string.delete_marker));
            this.f5696e.showInfoWindow();
        }
    }

    public void m8617d() {
        if (this.f5696e != null) {
            FlyActionBean flyActionBean = (FlyActionBean) this.f5696e.getObject();
            if (flyActionBean != null) {
                flyActionBean.setType(1);
                this.f5696e.setIcon(C1255m.m8606a(this.f5694c.f6507c, C1205R.drawable.img_fly_flag_blue, flyActionBean.getHeight(), false));
            }
        }
    }

    public void m8618e() {
        if (this.f5696e != null) {
            FlyActionBean flyActionBean = (FlyActionBean) this.f5696e.getObject();
            if (flyActionBean != null && flyActionBean.getModelType() == 3 && 1 == flyActionBean.getType()) {
                flyActionBean.setStyleInfo(2);
                flyActionBean.setCanExcute(false);
                this.f5696e.setIcon(C1255m.m8605a(C1205R.drawable.img_fly_flag_blue));
                if (this.f5696e.isInfoWindowShown()) {
                    this.f5696e.hideInfoWindow();
                }
                this.f5696e.setTitle(this.f5694c.f6507c.getString(C1205R.string.delete_marker));
                this.f5696e.showInfoWindow();
            }
        }
    }

    public void m8619f() {
        if (this.f5696e != null) {
            FlyActionBean flyActionBean = (FlyActionBean) this.f5696e.getObject();
            if (flyActionBean != null && flyActionBean.getModelType() == 3 && 1 == flyActionBean.getType()) {
                flyActionBean.setStyleInfo(2);
                flyActionBean.setCanExcute(true);
                this.f5696e.setIcon(C1255m.m8605a(C1205R.drawable.img_fly_flag_blue));
                if (this.f5696e.isInfoWindowShown()) {
                    this.f5696e.hideInfoWindow();
                }
                this.f5696e.setTitle(this.f5694c.f6507c.getString(C1205R.string.delete_marker));
                this.f5696e.showInfoWindow();
            }
        }
    }

    public void m8620g() {
        FlyActionBean j = C1247f.m8565k().m8582j();
        if (j != null) {
            j.setType(1);
        }
        if (this.f5696e != null) {
            this.f5696e.hideInfoWindow();
        }
    }

    public void m8621h() {
        if (this.f5696e != null) {
            if (this.f5696e.isInfoWindowShown()) {
                this.f5696e.hideInfoWindow();
            }
            this.f5696e.setDraggable(false);
        }
    }

    public void m8622i() {
        if (this.f5696e != null) {
            this.f5696e.remove();
            this.f5696e = null;
        }
        if (this.f5697f != null) {
            this.f5697f.remove();
            this.f5697f = null;
        }
        this.f5695d.m8578f().clear();
        this.f5694c.m9589a(C1584h.HIDEHEIGHTVALUE);
    }
}
