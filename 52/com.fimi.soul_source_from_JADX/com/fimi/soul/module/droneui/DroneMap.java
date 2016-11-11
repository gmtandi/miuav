package com.fimi.soul.module.droneui;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.amap.api.location.AMapLocation;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.SupportMapFragment;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.DroidPlannerApp;
import com.fimi.soul.biz.p103k.C1394s;
import com.fimi.soul.biz.p103k.C1396u;
import com.fimi.soul.drone.C1234i;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.utils.be;
import com.fimi.soul.view.photodraweeview.C2020f;
import org.codehaus.jackson.smile.SmileConstants;

public abstract class DroneMap extends SupportMapFragment implements C1396u, C1234i {
    public static int f8354h = 0;
    public static final byte f8355i = (byte) 80;
    public static final byte f8356j = (byte) 82;
    public static final byte f8357k = (byte) 67;
    public static int f8358l = 0;
    public static final int f8359m = 3;
    public static int f8360n;
    private boolean f8361a;
    private Marker f8362b;
    protected AMap f8363f;
    public C1433a f8364g;

    static {
        f8354h = 20;
        f8358l = 0;
        f8360n = 0;
    }

    public DroneMap() {
        this.f8361a = true;
    }

    private void m11166a() {
        if (this.f8363f == null) {
            this.f8363f = getMap();
            this.f8364g.m9584a(this.f8363f);
        }
    }

    private void m11167a(LatLng latLng) {
        this.f8362b = this.f8363f.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(C1205R.drawable.ic_wp_home)));
        this.f8362b.setAnchor(C2020f.f10933c, 0.5f);
    }

    public void m11168a(AMapLocation aMapLocation) {
        if (this.f8363f != null && aMapLocation != null && aMapLocation.getLatitude() != 0.0d && aMapLocation.getLongitude() != 0.0d) {
            this.f8364g.m9583a((Location) aMapLocation);
            LatLng latLng = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
            if (!(this.f8364g.m9570Q() || this.f8364g.am())) {
                this.f8364g.m9603f(true);
                this.f8363f.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18.0f));
            }
            if (this.f8362b == null) {
                m11167a(latLng);
                return;
            }
            if (!this.f8362b.isVisible()) {
                this.f8362b.destroy();
                m11167a(latLng);
            }
            this.f8362b.setPosition(latLng);
        }
    }

    public void m11169b() {
        super.onDestroy();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.f8364g = ((DroidPlannerApp) getActivity().getApplication()).f5570a;
        m11166a();
        C1394s.m9340a(getActivity()).m9347a((C1396u) this);
        return onCreateView;
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        switch (C1745f.f8602a[c1584h.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                c1433a.m9614q();
                f8354h = c1433a.ah().m10170e();
            default:
        }
    }

    public void onResume() {
        super.onResume();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    public void onStart() {
        super.onStart();
        this.f8364g.m9590a((C1234i) this);
        if (!be.m12370b(getActivity()) && !be.m12398p(getActivity()) && !this.f8364g.m9570Q()) {
            this.f8364g.m9603f(true);
            this.f8363f.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(32.76593d, 102.523984d), 4.0f));
        }
    }

    public void onStop() {
        super.onStop();
        this.f8364g.m9594b((C1234i) this);
    }
}
