package com.fimi.soul.module.dronemanage;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import com.amap.api.maps.AMap.InfoWindowAdapter;
import com.amap.api.maps.AMap.OnCameraChangeListener;
import com.amap.api.maps.AMap.OnInfoWindowClickListener;
import com.amap.api.maps.AMap.OnMapClickListener;
import com.amap.api.maps.AMap.OnMapLoadedListener;
import com.amap.api.maps.AMap.OnMapLongClickListener;
import com.amap.api.maps.AMap.OnMapTouchListener;
import com.amap.api.maps.AMap.OnMarkerClickListener;
import com.amap.api.maps.AMap.OnMarkerDragListener;
import com.amap.api.maps.Projection;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.fimi.kernel.p084e.ab;
import com.fimi.kernel.p084e.ak;
import com.fimi.soul.C1205R;
import com.fimi.soul.biz.p090b.C1243b;
import com.fimi.soul.biz.p090b.C1244c;
import com.fimi.soul.biz.p090b.C1253k;
import com.fimi.soul.biz.p090b.C1259q;
import com.fimi.soul.biz.p092c.C1263a;
import com.fimi.soul.biz.p092c.C1266d;
import com.fimi.soul.biz.p101h.C1346a;
import com.fimi.soul.biz.p101h.C1348c;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.p107c.p108a.p109a.C1458u;
import com.fimi.soul.drone.p107c.p108a.p109a.bn;
import com.fimi.soul.drone.p116g.C1543c;
import com.fimi.soul.drone.p117h.bh;
import com.fimi.soul.drone.p117h.p118a.C1553b;
import com.fimi.soul.entity.FlyActionBean;
import com.fimi.soul.module.droneFragment.FlyActionSettingFragment;
import com.fimi.soul.module.droneui.DroneMap;
import com.fimi.soul.utils.ac;
import com.fimi.soul.utils.ad;
import com.fimi.soul.utils.aj;
import com.fimi.soul.utils.be;
import com.tencent.mm.sdk.platformtools.Util;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import it.p074a.p075a.C2799f;
import java.util.List;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

public class FlightMapFragment extends DroneMap implements InfoWindowAdapter, OnCameraChangeListener, OnInfoWindowClickListener, OnMapClickListener, OnMapLoadedListener, OnMapLongClickListener, OnMapTouchListener, OnMarkerClickListener, OnMarkerDragListener, ad {
    private double f8365A;
    private List<Circle> f8366B;
    boolean f8367a;
    Editor f8368b;
    boolean f8369c;
    Circle f8370d;
    Circle f8371e;
    private C1346a f8372o;
    private C1259q f8373p;
    private LatLng f8374q;
    private Circle f8375r;
    private boolean f8376s;
    private FragmentManager f8377t;
    private float f8378u;
    private float f8379v;
    private C1734u f8380w;
    private SharedPreferences f8381x;
    private C1253k f8382y;
    private C1244c f8383z;

    public FlightMapFragment() {
        this.f8367a = false;
        this.f8369c = true;
    }

    private void m11172a(LatLng latLng) {
        if (this.f8375r == null) {
            // #ffff4200: 红色
            this.f8375r = this.f.addCircle(new CircleOptions().center(latLng).radius(500.0d).strokeWidth(5.0f)
                    .strokeColor(getResources().getColor(C1205R.color.errortextcl)));
            return;
        }
        this.f8375r.setCenter(latLng);
        this.f8375r.setVisible(true);
    }

    private void m11173a(LatLng latLng, int i, int i2) {
        int i3 = this.f8381x.getInt(C1543c.f7244s, 1);
        if (i2 == 3) {
            if (this.f8371e != null) {
                this.f8371e.setCenter(latLng);
                if (((double) i) != this.f8371e.getRadius()) {
                    this.f8371e.setRadius((double) (i + 100));
                }
            } else {
                // 黑色
                // rgb(198, 200, 203): 浅灰色
                // rgb(101, 104, 106): 深灰色
                this.f8371e = this.f.addCircle(new CircleOptions().center(latLng).radius((double) (i + 100))
                        .strokeColor(Color.argb(Opcodes.LAND, 0, 0, 0)).strokeWidth(2.0f).fillColor(
                                1 == i3 ? Color.argb(C1458u.f6934b, Opcodes.IFNULL, C2799f.f14282t, bn.f6797b)
                                        : Color.argb(C1458u.f6934b, Opcodes.LSUB, Opcodes.IMUL, Opcodes.FMUL)));
            }
        } else if (this.f8371e != null) {
            this.f8371e.remove();
            this.f8371e = null;
        }
        if (this.f8370d == null || !this.f8366B.contains(this.f8370d)) {
            // rgb(255, 54, 0): 红色
            // rgb(255, 54, 0): 红色
            // zIndex 100
            this.f8370d = this.f.addCircle(new CircleOptions().center(latLng).radius((double) i)
                    .strokeColor(Color.argb(Opcodes.LAND, Util.MASK_8BIT, 54, 0)).strokeWidth(2.0f)
                    .fillColor(Color.argb(51, Util.MASK_8BIT, 54, 0)).zIndex(100.0f));
        } else {
            this.f8370d.setCenter(latLng);
            if (((double) i) != this.f8370d.getRadius()) {
                this.f8370d.setRadius((double) i);
            }
        }
        if (!this.f8366B.contains(this.f8370d)) {
            this.f8366B.add(this.f8370d);
        }
    }

    private void m11174a(bh bhVar) {
    }

    private void m11175c() {
        if (this.f == null) {
            this.f = getMap();
        }
        if (this.f != null) {
            this.f.setOnMapClickListener(this);
            this.f.setOnMarkerClickListener(this);
            this.f.setOnMarkerDragListener(this);
            this.f.setOnMapLongClickListener(this);
            this.f.setMapType(this.f8381x.getInt(C1543c.f7244s, 1));
            this.f.setTrafficEnabled(false);
            this.f.setOnCameraChangeListener(this);
            this.f.setOnMapLoadedListener(this);
            this.f.setOnInfoWindowClickListener(this);
            this.f.setInfoWindowAdapter(this);
            this.f.setOnMapTouchListener(this);
            UiSettings uiSettings = this.f.getUiSettings();
            uiSettings.setZoomControlsEnabled(false);
            uiSettings.setScaleControlsEnabled(false);
        }
    }

    private void m11176d() {
        if (this.f8375r != null) {
            this.f8375r.setVisible(false);
        }
    }

    public void m11177a() {
        this.f.setMapType(this.f8381x.getInt(C1543c.f7244s, 1));
    }

    public void m11178a(float f, boolean z, View... viewArr) {
        for (int i = 0; i < viewArr.length; i++) {
            View view = viewArr[i];
            if (view.getAlpha() != f) {
                view.setAlpha(f);
            }
            if (view.isEnabled() != z) {
                viewArr[i].setEnabled(z);
            }
        }
    }

    public void m11179a(Button button, int i) {
        button.setTextColor(button.getTextColors().withAlpha(i));
        if (i == Util.MASK_8BIT) {
            button.setEnabled(true);
        } else {
            button.setEnabled(false);
        }
    }

    public void m11180a(Marker marker) {
        Handler handler = new Handler();
        long uptimeMillis = SystemClock.uptimeMillis();
        Projection projection = this.f.getProjection();
        Point toScreenLocation = projection.toScreenLocation(marker.getPosition());
        toScreenLocation.offset(0, -100);
        handler.post(new C1732s(this, uptimeMillis, new BounceInterpolator(), marker, projection.fromScreenLocation(toScreenLocation)));
    }

    public void m11181a(C1553b c1553b) {
        aj a = ad.m12227a((double) c1553b.m10163j(), (double) c1553b.m10164k());
        m11173a(new LatLng(a.m12250a(), a.m12252b()), c1553b.m10160g() * 10, c1553b.m10162i());
    }

    public void m11182a(FlyActionBean flyActionBean) {
        this.f8383z.m8562b();
    }

    public void m11183a(List<bh> list) {
        this.f8380w.m11266a(list);
    }

    public void m11184b(Marker marker) {
        LatLng position = marker.getPosition();
        for (Circle contains : C1348c.m9015a().m9016b()) {
            if (contains.contains(position)) {
                this.f8376s = true;
                ak.m8083a(this.g.f6507c, (int) C1205R.string.flyzonwaypoint, 3000);
                return;
            }
        }
        this.f8376s = false;
        aj a = ad.m12227a(this.g.m9618u().m10301b(), this.g.m9618u().m10302c());
        this.f8365A = ac.m12223c(position, new LatLng(a.m12250a(), a.m12252b())).m12254a();
        if (this.f8382y.m8599a().get() == 1 || this.f8382y.m8599a().get() == 2) {
            marker.setTitle(getString(C1205R.string.distancetag) + ab.m8015a(this.f8365A, 1) + getString(C1205R.string.meter));
            marker.showInfoWindow();
        }
        if (this.f8365A >= 500.0d) {
            ak.m8083a(getActivity(), (int) C1205R.string.waypoint_distan_home_Err, (int) XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER);
            return;
        }
        this.f8374q = position;
        FlyActionBean flyActionBean = (FlyActionBean) marker.getObject();
        if (flyActionBean != null) {
            flyActionBean.setLatLng(position);
            m11182a(flyActionBean);
        }
    }

    public void m11185b(List<bh> list) {
    }

    public View getInfoContents(Marker marker) {
        return null;
    }

    public View getInfoWindow(Marker marker) {
        Object object = marker.getObject();
        View inflate;
        Button button;
        if (object instanceof FlyActionBean) {
            FlyActionBean flyActionBean = (FlyActionBean) marker.getObject();
            if (flyActionBean != null) {
                if (flyActionBean.getStyleInfo() == 2) {
                    inflate = LayoutInflater.from(getActivity()).inflate(C1205R.layout.mystyle_com_inforwindow, null);
                    ((Button) inflate.findViewById(C1205R.id.delete)).setOnClickListener(new C1727n(this));
                    button = (Button) inflate.findViewById(C1205R.id.excute_again);
                    if (flyActionBean.isCanExcute()) {
                        m11179a(button, Util.MASK_8BIT);
                    } else {
                        m11179a(button, 75);
                    }
                    button.setOnClickListener(new C1728o(this, flyActionBean));
                    be.m12359a(getActivity().getAssets(), r1, button);
                    return inflate;
                }
                View inflate2 = LayoutInflater.from(getActivity()).inflate(C1205R.layout.mystyle_inforwindow, null);
                Button button2 = (Button) inflate2.findViewById(C1205R.id.reminder);
                button2.setText(marker.getTitle());
                button2.setOnClickListener(new C1729p(this, button2));
                be.m12359a(getActivity().getAssets(), button2);
                return inflate2;
            }
        } else if (object instanceof C1243b) {
            C1243b c1243b = (C1243b) object;
            inflate = LayoutInflater.from(getActivity()).inflate(C1205R.layout.mystyle_com_inforwindow, null);
            ((Button) inflate.findViewById(C1205R.id.delete)).setOnClickListener(new C1730q(this));
            button = (Button) inflate.findViewById(C1205R.id.excute_again);
            button.setText(C1205R.string.take_photo_fly_again);
            if (c1243b.f5637a) {
                m11179a(button, 75);
            } else {
                m11179a(button, Util.MASK_8BIT);
            }
            button.setOnClickListener(new C1731r(this));
            be.m12359a(getActivity().getAssets(), r1, button);
            return inflate;
        }
        return null;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.f8380w = (C1734u) getActivity();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onCameraChange(CameraPosition cameraPosition) {
        if (this.f8382y.m8599a().get() == 3 && this.g.m9570Q()) {
            this.g.m9589a(C1584h.CHANGESTARTMARKER);
        }
    }

    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        if (this.f8382y.m8599a().get() == 3 && this.g.m9570Q()) {
            this.g.m9589a(C1584h.CHANGESTARTMARKER);
        }
        if (cameraPosition != null && cameraPosition.zoom >= 7.0f) {
            this.f8372o.m9013a();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f8382y = C1253k.m8598a(getActivity().getApplicationContext());
        this.f8366B = C1348c.m9015a().m9016b();
        this.f8377t = getActivity().getSupportFragmentManager();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.f8381x = PreferenceManager.getDefaultSharedPreferences(getActivity());
        this.f8368b = this.f8381x.edit();
        return onCreateView;
    }

    public void onDestroy() {
        super.onDestroy();
        this.f8372o.m9014b();
        for (Circle circle : this.f8366B) {
            if (circle != null) {
                circle.remove();
            }
        }
        this.f8373p.m8626a();
    }

    public void onDetach() {
        super.onDetach();
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        super.onDroneEvent(c1584h, c1433a);
        switch (C1733t.f8519a[c1584h.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                C1553b g = c1433a.m9604g();
                if (g.m10159f() != 4 && g.m10159f() != 5 && g.m10159f() != 6) {
                    m11181a(g);
                }
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                aj a = ad.m12227a(c1433a.m9618u().m10301b(), c1433a.m9618u().m10302c());
                m11172a(new LatLng(a.m12250a(), a.m12252b()));
            case Type.BYTE /*3*/:
                m11176d();
            default:
        }
    }

    public void onInfoWindowClick(Marker marker) {
    }

    public void onMapClick(LatLng latLng) {
        if (h != 6 && h != 4 && h != 5) {
            this.g.m9589a(C1584h.HIDEINFORWINDOW);
            FlyActionSettingFragment flyActionSettingFragment = (FlyActionSettingFragment) this.f8377t.findFragmentById(C1205R.id.flyaction);
            if ((flyActionSettingFragment != null && !flyActionSettingFragment.isVisible()) || !this.f8382y.m8601b()) {
                return;
            }
            if (this.f8382y.m8599a().get() == 1) {
                this.f8383z.m8560a(latLng, (int) C1205R.drawable.icon_fly_waypoint_red);
            } else if (this.f8382y.m8599a().get() == 2) {
                this.f8383z.m8560a(latLng, (int) C1205R.drawable.img_fly_flag_blue);
            } else if (this.f8382y.m8599a().get() == 3) {
                this.f8383z.m8560a(latLng, (int) C1205R.drawable.img_fly_flag_blue);
            }
        }
    }

    public void onMapLoaded() {
        if (this.f.getCameraPosition().zoom >= 7.0f) {
            this.f8372o.m9013a();
        }
    }

    public void onMapLongClick(LatLng latLng) {
    }

    public boolean onMarkerClick(Marker marker) {
        if (this.g.ah().m10172g().judgeNoAction()) {
            this.f8383z.m8561a(marker);
        }
        return true;
    }

    public void onMarkerDrag(Marker marker) {
        m11184b(marker);
    }

    public void onMarkerDragEnd(Marker marker) {
        if ((this.f8365A >= 500.0d || this.f8376s) && this.f8374q != null) {
            marker.setPosition(this.f8374q);
        }
        if (marker.isInfoWindowShown()) {
            marker.hideInfoWindow();
            marker.setTitle(C2915a.f14760f);
        }
        this.g.m9589a(C1584h.NOTIFICHANGECIECLERIDUS);
    }

    public void onMarkerDragStart(Marker marker) {
        m11180a(marker);
    }

    public void onPause() {
        super.onPause();
    }

    public void onResume() {
        super.onResume();
    }

    public void onStart() {
        super.onStart();
        m11175c();
        this.g.m9584a(this.f);
        if (this.f8372o == null) {
            this.f8372o = new C1346a(getActivity().getApplicationContext(), this.f);
        }
        if (this.f8383z == null) {
            this.f8383z = new C1244c(this.g, this.f, getActivity().getApplicationContext(), this.f8377t);
        } else {
            this.f8383z.m8564d();
        }
        if (this.f8373p == null) {
            this.f8373p = new C1259q(this.g);
        }
    }

    public void onStop() {
        super.onStop();
    }

    public void onTouch(MotionEvent motionEvent) {
        C1263a a = C1263a.m8645a();
        if (a.m8651c() == C1266d.DRONEPRESS) {
            switch (motionEvent.getAction() & Util.MASK_8BIT) {
                case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                    this.f8378u = motionEvent.getX();
                    this.f8379v = motionEvent.getY();
                    this.f8367a = true;
                    a.m8649a(false);
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    a.m8649a(true);
                    if (!this.f8367a) {
                        return;
                    }
                    if (Math.abs(motionEvent.getX() - this.f8378u) > BitmapDescriptorFactory.HUE_ORANGE || Math.abs(motionEvent.getY() - this.f8379v) > BitmapDescriptorFactory.HUE_ORANGE) {
                        a.m8648a(C1266d.DRONE);
                    }
                case Type.INT /*5*/:
                    this.f8367a = false;
                default:
            }
        }
    }
}
