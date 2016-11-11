package com.fimi.soul.biz.p090b;

import android.content.Context;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.Polyline;
import com.fimi.kernel.p083d.C1160b;
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
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.biz.b.s */
public class C1261s implements C1242a {
    private AMap f5704a;
    private Context f5705b;
    private volatile Polyline f5706c;
    private C1247f f5707d;
    private C1433a f5708e;
    private volatile Polyline f5709f;

    public C1261s(AMap aMap, Context context, C1433a c1433a) {
        this.f5708e = c1433a;
        this.f5704a = aMap;
        this.f5705b = context;
        this.f5707d = C1247f.m8565k();
    }

    public synchronized void m8628a() {
        Marker marker;
        FlyActionBean flyActionBean;
        List h;
        List e = C1247f.m8565k().m8577e();
        if (C1247f.m8565k().m8578f().size() > 0 && e.size() == 0) {
            for (FlyActionBean flyActionBean2 : C1247f.m8565k().m8578f()) {
                Marker addMarker = this.f5704a.addMarker(C1254l.m8602a(flyActionBean2.getLatLng(), C1205R.drawable.img_fly_origin_blue));
                addMarker.setObject(flyActionBean2);
                addMarker.setAnchor(0.5f, 0.88f);
                addMarker.hideInfoWindow();
                if (!e.contains(addMarker)) {
                    e.add(addMarker);
                }
            }
        }
        if (e != null) {
            if (e.size() > 1) {
                for (int i = 0; i < e.size(); i++) {
                    marker = (Marker) e.get(i);
                    flyActionBean = (FlyActionBean) marker.getObject();
                    if (i == e.size() - 1) {
                        marker.setIcon(C1255m.m8605a(C1205R.drawable.img_fly_flag_blue));
                        flyActionBean.setStyleInfo(2);
                        flyActionBean.setCanclick(true);
                        flyActionBean.setDrawableRes(C1205R.drawable.img_fly_flag_blue);
                        marker.setAnchor(0.1f, 0.9f);
                    } else {
                        flyActionBean.setCanclick(false);
                        marker.setIcon(C1255m.m8605a(C1205R.drawable.img_fly_origin_blue));
                        flyActionBean.setDrawableRes(C1205R.drawable.img_fly_origin_blue);
                        marker.setAnchor(0.5f, 0.7f);
                    }
                    flyActionBean.setModelType(1);
                    marker.setDraggable(false);
                    if (marker.isInfoWindowShown()) {
                        marker.hideInfoWindow();
                    }
                }
                h = this.f5707d.m8580h();
                if (h != null && h.size() > 0) {
                    if (this.f5706c != null) {
                        this.f5706c = this.f5704a.addPolyline(C1254l.m8604a(h, 4, this.f5705b.getResources().getColor(C1205R.color.polyline_coclor)));
                    } else {
                        this.f5706c.setPoints(h);
                    }
                    this.f5707d.m8570a(this.f5706c);
                }
                if (this.f5709f != null) {
                    this.f5709f.remove();
                    this.f5709f = null;
                }
                if (C1247f.m8565k().m8574b() != null) {
                    C1247f.m8565k().m8574b().clear();
                }
            }
        }
        if (e.size() > 0) {
            marker = (Marker) e.get(0);
            flyActionBean = (FlyActionBean) marker.getObject();
            marker.setIcon(C1255m.m8605a(C1205R.drawable.img_fly_flag_blue));
            flyActionBean.setStyleInfo(2);
            flyActionBean.setCanclick(true);
            flyActionBean.setDrawableRes(C1205R.drawable.img_fly_flag_blue);
            marker.setAnchor(0.1f, 0.9f);
        }
        h = this.f5707d.m8580h();
        if (this.f5706c != null) {
            this.f5706c.setPoints(h);
        } else {
            this.f5706c = this.f5704a.addPolyline(C1254l.m8604a(h, 4, this.f5705b.getResources().getColor(C1205R.color.polyline_coclor)));
        }
        this.f5707d.m8570a(this.f5706c);
        if (this.f5709f != null) {
            this.f5709f.remove();
            this.f5709f = null;
        }
        if (C1247f.m8565k().m8574b() != null) {
            C1247f.m8565k().m8574b().clear();
        }
    }

    public void m8629a(int i) {
        if (this.f5708e.ah().m10170e() == 6) {
            List e = this.f5707d.m8577e();
            if (e.size() >= 1 && i <= e.size() && i >= 1) {
                Marker marker;
                if (i <= e.size() - 1) {
                    marker = (Marker) e.get(i - 1);
                    marker.setIcons(C1255m.m8607a(this.f5708e.f6507c, ((FlyActionBean) marker.getObject()).getHeight(), false, C1205R.drawable.img_fly_origin_blue_m, C1205R.drawable.img_fly_origin_flash));
                    marker.setAnchor(0.5f, 0.8f);
                } else if (i == e.size()) {
                    marker = (Marker) e.get(e.size() - 1);
                    marker.setIcon(C1255m.m8606a(this.f5708e.f6507c, C1205R.drawable.img_fly_flag_blue, ((FlyActionBean) marker.getObject()).getHeight(), false));
                    marker.setAnchor(0.15f, 0.9f);
                }
                if (i >= 2) {
                    for (int i2 = 0; i2 < i - 1; i2++) {
                        marker = (Marker) e.get(i2);
                        marker.setIcon(C1255m.m8605a(C1205R.drawable.img_fly_origin_red));
                        marker.setAnchor(0.5f, 0.7f);
                    }
                }
            }
        }
    }

    public void m8630a(LatLng latLng) {
        List b = C1247f.m8565k().m8574b();
        if (b != null && !b.contains(latLng)) {
            b.add(latLng);
            if (b.size() < 2) {
                return;
            }
            if (this.f5709f == null) {
                this.f5709f = this.f5704a.addPolyline(C1254l.m8604a(b, 4, this.f5705b.getResources().getColor(C1205R.color.drone_inface_line)).zIndex(50.0f));
            } else {
                this.f5709f.setPoints(b);
            }
        }
    }

    public void m8631a(LatLng latLng, int i) {
        List h = this.f5707d.m8580h();
        List<Marker> e = this.f5707d.m8577e();
        if (e == null || e.size() < 20) {
            for (Circle contains : C1348c.m9015a().m9016b()) {
                if (contains.contains(latLng)) {
                    ak.m8083a(this.f5708e.f6507c, (int) C1205R.string.flyzonwaypoint, 3000);
                    return;
                }
            }
            aj a = ad.m12227a(this.f5708e.m9618u().m10301b(), this.f5708e.m9618u().m10302c());
            double a2 = ac.m12223c(latLng, new LatLng(a.m12250a(), a.m12252b())).m12254a();
            if (a2 <= 500.0d) {
                FlyActionBean flyActionBean = new FlyActionBean();
                flyActionBean.setLatLng(latLng);
                flyActionBean.setDrawableRes(i);
                flyActionBean.setCanclick(true);
                flyActionBean.setType(2);
                flyActionBean.setModelType(1);
                flyActionBean.setHeight(this.f5707d.m8579g());
                Marker addMarker = this.f5704a.addMarker(C1254l.m8603a(latLng, this.f5708e.f6507c, this.f5707d.m8579g(), true, i));
                addMarker.setAnchor(0.5f, 0.88f);
                addMarker.setObject(flyActionBean);
                addMarker.setZIndex(1000.0f);
                this.f5707d.m8571a(flyActionBean);
                if (!(e == null || e.contains(addMarker))) {
                    for (Marker marker : e) {
                        marker.setIcon(C1255m.m8606a(this.f5708e.f6507c, C1205R.drawable.icon_fly_waypoint_blue, ((FlyActionBean) marker.getObject()).getHeight(), false));
                    }
                    e.add(addMarker);
                    this.f5708e.m9589a(C1584h.SHOWHEIGHTVIEW);
                }
                List f = this.f5707d.m8578f();
                if (!(f == null || f.contains(flyActionBean))) {
                    f.add(flyActionBean);
                }
                if (h != null && !h.contains(h)) {
                    h.add(latLng);
                    if (this.f5706c == null) {
                        this.f5706c = this.f5704a.addPolyline(C1254l.m8604a(h, 4, this.f5705b.getResources().getColor(C1205R.color.polyline_coclor)));
                    } else {
                        this.f5706c.setPoints(h);
                    }
                    this.f5707d.m8570a(this.f5706c);
                    return;
                }
                return;
            } else if (a2 > 500.0d) {
                ak.m8082a(this.f5705b, (int) C1205R.string.outterwaypoint);
                return;
            } else {
                return;
            }
        }
        ak.m8083a(this.f5708e.f6507c, (int) C1205R.string.waypointCountOut, 3000);
    }

    public void m8632a(FlyActionBean flyActionBean) {
        int i = 0;
        List h = this.f5707d.m8580h();
        List f = this.f5707d.m8578f();
        List<Marker> e = this.f5707d.m8577e();
        if (flyActionBean != null && f.contains(flyActionBean)) {
            i = f.indexOf(flyActionBean);
            f.remove(flyActionBean);
        }
        int i2 = i;
        if (h != null && h.contains(flyActionBean.getLatLng())) {
            h.remove(flyActionBean.getLatLng());
            if (this.f5706c == null || h.size() <= 0) {
                this.f5708e.m9589a(C1584h.HIDEHEIGHTVALUE);
            } else {
                this.f5706c.setPoints(h);
            }
        }
        if (e != null) {
            for (Marker marker : e) {
                if (marker.getObject().equals(flyActionBean)) {
                    marker.remove();
                    e.remove(marker);
                    break;
                }
            }
        }
        if (f.size() >= 1 && i2 <= f.size()) {
            this.f5707d.m8571a((FlyActionBean) f.get(i2 - 1));
        }
        if (e.size() >= 1 && i2 <= e.size()) {
            ((Marker) e.get(i2 - 1)).setIcon(C1255m.m8606a(this.f5708e.f6507c, C1205R.drawable.icon_fly_waypoint_red, flyActionBean.getHeight(), true));
        }
    }

    public void m8633b() {
        if (this.f5706c != null) {
            this.f5706c.remove();
            this.f5706c = null;
        }
        if (this.f5709f != null) {
            this.f5709f.remove();
            this.f5709f = null;
        }
    }

    public void m8634c() {
        List e = C1247f.m8565k().m8577e();
        List h = C1247f.m8565k().m8580h();
        int e2 = this.f5708e.m9618u().m10304e();
        if (e != null && e.size() > 0 && h != null && e2 >= h.size()) {
            ak.m8083a(this.f5708e.f6507c, (int) C1205R.string.excute_waypoint_com, 3000);
            C1160b.m7953b(this.f5708e.f6507c).m7959a(this.f5708e.f6507c.getString(C1205R.string.excute_waypoint_com));
            ((Marker) e.get(e.size() - 1)).setIcon(C1255m.m8605a(C1205R.drawable.img_fly_flag_red));
            C1253k.m8598a(this.f5708e.f6507c).m8600a(0);
            this.f5708e.m9589a(C1584h.SHOWINFORWINDOW);
        }
    }

    public void m8635d() {
        List h = C1247f.m8565k().m8580h();
        List<FlyActionBean> f = C1247f.m8565k().m8578f();
        if (h != null && f != null) {
            h.clear();
            for (FlyActionBean flyActionBean : f) {
                if (!h.contains(flyActionBean.getLatLng())) {
                    h.add(flyActionBean.getLatLng());
                }
            }
            this.f5706c.setPoints(h);
        }
    }

    public void m8636e() {
        List e = this.f5707d.m8577e();
        if (e != null && e.size() > 0) {
            Marker marker = (Marker) e.get(e.size() - 1);
            FlyActionBean flyActionBean = (FlyActionBean) marker.getObject();
            if (flyActionBean != null && flyActionBean.getModelType() == 1) {
                flyActionBean.setStyleInfo(2);
                flyActionBean.setCanExcute(true);
                marker.setTitle(C2915a.f14760f);
                marker.showInfoWindow();
            }
        }
    }

    public void m8637f() {
        List e = this.f5707d.m8577e();
        if (e != null && e.size() > 0) {
            Marker marker = (Marker) e.get(e.size() - 1);
            FlyActionBean flyActionBean = (FlyActionBean) marker.getObject();
            if (marker != null && flyActionBean != null && flyActionBean.getModelType() == 1) {
                if (marker.isInfoWindowShown()) {
                    marker.hideInfoWindow();
                }
                flyActionBean.setStyleInfo(2);
                flyActionBean.setCanExcute(false);
                marker.setTitle(this.f5708e.f6507c.getString(C1205R.string.delete_marker));
                marker.showInfoWindow();
            }
        }
    }

    public void m8638g() {
        List e = this.f5707d.m8577e();
        if (e != null && e.size() > 0) {
            Marker marker = (Marker) e.get(e.size() - 1);
            FlyActionBean flyActionBean = (FlyActionBean) marker.getObject();
            if (flyActionBean != null && flyActionBean.getModelType() == 1) {
                flyActionBean.setStyleInfo(2);
                flyActionBean.setCanExcute(true);
                if (marker.isInfoWindowShown()) {
                    marker.hideInfoWindow();
                    if (!marker.isInfoWindowShown()) {
                        marker.setTitle(C2915a.f14760f);
                        marker.showInfoWindow();
                    }
                }
            }
        }
    }

    public void m8639h() {
        List e = this.f5707d.m8577e();
        if (e != null && e.size() > 0) {
            Marker marker = (Marker) e.get(e.size() - 1);
            if (marker.isInfoWindowShown()) {
                marker.hideInfoWindow();
            }
        }
    }
}
