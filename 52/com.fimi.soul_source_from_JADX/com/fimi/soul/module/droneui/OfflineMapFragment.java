package com.fimi.soul.module.droneui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.amap.api.maps.AMap;
import com.amap.api.maps.SupportMapFragment;

public class OfflineMapFragment extends SupportMapFragment {
    private AMap f8595a;

    private void m11357a() {
        if (this.f8595a == null) {
            this.f8595a = getMap();
        }
        if (this.f8595a != null) {
            AMap aMap = this.f8595a;
            AMap aMap2 = this.f8595a;
            aMap.setMapType(2);
            this.f8595a.getUiSettings().setZoomControlsEnabled(false);
            this.f8595a.getUiSettings().setCompassEnabled(false);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        m11357a();
        return onCreateView;
    }
}
