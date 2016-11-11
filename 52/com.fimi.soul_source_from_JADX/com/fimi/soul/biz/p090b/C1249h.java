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
import com.fimi.soul.entity.FlyActionBean;
import com.fimi.soul.utils.ac;
import com.fimi.soul.utils.ad;
import com.fimi.soul.utils.aj;
import java.util.List;

/* renamed from: com.fimi.soul.biz.b.h */
public class C1249h implements C1242a {
    private AMap f5675a;
    private Context f5676b;
    private C1433a f5677c;
    private C1247f f5678d;
    private Marker f5679e;
    private Polyline f5680f;

    public C1249h(AMap aMap, Context context, C1433a c1433a) {
        this.f5677c = c1433a;
        this.f5675a = aMap;
        this.f5676b = context;
        this.f5678d = C1247f.m8565k();
    }

    public void m8584a() {
        if (this.f5679e != null) {
            if (this.f5679e.isInfoWindowShown()) {
                this.f5679e.hideInfoWindow();
            }
            this.f5679e.setDraggable(false);
        }
    }

    public void m8585a(LatLng latLng) {
        List b = C1247f.m8565k().m8574b();
        if (b != null && !b.contains(latLng)) {
            b.add(latLng);
            if (b.size() < 2) {
                return;
            }
            if (this.f5680f == null) {
                this.f5680f = this.f5675a.addPolyline(C1254l.m8604a(b, 4, this.f5676b.getResources().getColor(C1205R.color.drone_inface_line)).zIndex(50.0f));
            } else {
                this.f5680f.setPoints(b);
            }
        }
    }

    public void m8586a(LatLng latLng, int i) {
        for (Circle contains : C1348c.m9015a().m9016b()) {
            if (contains.contains(latLng)) {
                ak.m8083a(this.f5677c.f6507c, (int) C1205R.string.flyzonwaypoint, 3000);
                return;
            }
        }
        aj a = ad.m12227a(this.f5677c.m9618u().m10301b(), this.f5677c.m9618u().m10302c());
        double a2 = ac.m12223c(latLng, new LatLng(a.m12250a(), a.m12252b())).m12254a();
        if (a2 <= 500.0d) {
            List f = this.f5678d.m8578f();
            if (this.f5679e == null) {
                FlyActionBean flyActionBean = new FlyActionBean();
                flyActionBean.setLatLng(latLng);
                flyActionBean.setDrawableRes(i);
                flyActionBean.setCanclick(true);
                flyActionBean.setType(2);
                flyActionBean.setModelType(2);
                flyActionBean.setStyleInfo(1);
                flyActionBean.setHeight(this.f5678d.m8579g());
                this.f5679e = this.f5675a.addMarker(C1254l.m8603a(latLng, this.f5677c.f6507c, this.f5678d.m8579g(), true, i));
                this.f5679e.setObject(flyActionBean);
                this.f5679e.setZIndex(1000.0f);
                this.f5679e.setAnchor(0.2f, 0.8f);
                this.f5678d.m8571a(flyActionBean);
                if (!f.contains(flyActionBean)) {
                    f.add(flyActionBean);
                }
                f = this.f5678d.m8577e();
                if (!(f == null || f.contains(this.f5679e))) {
                    f.add(this.f5679e);
                }
                this.f5677c.m9589a(C1584h.SHOWHEIGHTVIEW);
                return;
            }
            ((FlyActionBean) this.f5679e.getObject()).setLatLng(latLng);
            this.f5679e.setPosition(latLng);
        } else if (a2 > 500.0d) {
            ak.m8082a(this.f5676b, (int) C1205R.string.outterwaypoint);
        }
    }

    public void m8587b() {
        if (this.f5679e != null) {
            this.f5679e.remove();
            this.f5679e = null;
        }
        if (this.f5680f != null) {
            this.f5680f.remove();
            this.f5680f = null;
        }
        this.f5678d.m8578f().clear();
        this.f5677c.m9589a(C1584h.HIDEHEIGHTVALUE);
    }

    public void m8588c() {
        if (this.f5679e != null) {
            this.f5679e.setIcon(C1255m.m8605a(C1205R.drawable.img_fly_flag_red));
        }
        C1253k.m8598a(this.f5676b).m8600a(0);
    }

    public void m8589d() {
        if (this.f5679e != null) {
            FlyActionBean flyActionBean = (FlyActionBean) this.f5679e.getObject();
            if (flyActionBean != null) {
                flyActionBean.setStyleInfo(1);
            }
            this.f5679e.setTitle(this.f5677c.f6507c.getString(C1205R.string.delete_marker));
            this.f5679e.showInfoWindow();
        }
    }

    public void m8590e() {
        if (this.f5679e != null) {
            FlyActionBean flyActionBean = (FlyActionBean) this.f5679e.getObject();
            if (flyActionBean != null && flyActionBean.getModelType() == 2) {
                flyActionBean.setStyleInfo(2);
                this.f5679e.setIcon(C1255m.m8605a(C1205R.drawable.img_fly_flag_blue));
                this.f5679e.setTitle(this.f5677c.f6507c.getString(C1205R.string.delete_marker));
                this.f5679e.showInfoWindow();
            }
        }
    }

    public void m8591f() {
        if (this.f5679e != null) {
            FlyActionBean flyActionBean = (FlyActionBean) this.f5679e.getObject();
            if (flyActionBean != null) {
                flyActionBean.setType(1);
                this.f5679e.setIcon(C1255m.m8606a(this.f5677c.f6507c, C1205R.drawable.img_fly_flag_blue, flyActionBean.getHeight(), false));
            }
        }
    }

    public void m8592g() {
        if (this.f5679e != null) {
            FlyActionBean flyActionBean = (FlyActionBean) this.f5679e.getObject();
            if (flyActionBean != null && flyActionBean.getModelType() == 2 && 1 == flyActionBean.getType()) {
                flyActionBean.setStyleInfo(2);
                flyActionBean.setCanExcute(false);
                this.f5679e.setIcon(C1255m.m8605a(C1205R.drawable.img_fly_flag_blue));
                if (this.f5679e.isInfoWindowShown()) {
                    this.f5679e.hideInfoWindow();
                }
                this.f5679e.setTitle(this.f5677c.f6507c.getString(C1205R.string.delete_marker));
                this.f5679e.showInfoWindow();
            }
        }
    }

    public void m8593h() {
        if (this.f5679e != null) {
            FlyActionBean flyActionBean = (FlyActionBean) this.f5679e.getObject();
            if (flyActionBean != null && flyActionBean.getModelType() == 2 && 1 == flyActionBean.getType()) {
                flyActionBean.setStyleInfo(2);
                flyActionBean.setCanExcute(true);
                this.f5679e.setIcon(C1255m.m8605a(C1205R.drawable.img_fly_flag_blue));
                if (this.f5679e.isInfoWindowShown()) {
                    this.f5679e.hideInfoWindow();
                }
                this.f5679e.setTitle(this.f5677c.f6507c.getString(C1205R.string.delete_marker));
                this.f5679e.showInfoWindow();
            }
        }
    }
}
