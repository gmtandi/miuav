package com.fimi.soul.module.dronemanage;

import android.os.SystemClock;
import android.view.animation.Interpolator;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.fimi.soul.view.photodraweeview.C2020f;

/* renamed from: com.fimi.soul.module.dronemanage.s */
class C1732s implements Runnable {
    final /* synthetic */ long f8514a;
    final /* synthetic */ Interpolator f8515b;
    final /* synthetic */ Marker f8516c;
    final /* synthetic */ LatLng f8517d;
    final /* synthetic */ FlightMapFragment f8518e;

    C1732s(FlightMapFragment flightMapFragment, long j, Interpolator interpolator, Marker marker, LatLng latLng) {
        this.f8518e = flightMapFragment;
        this.f8514a = j;
        this.f8515b = interpolator;
        this.f8516c = marker;
        this.f8517d = latLng;
    }

    public void run() {
        float interpolation = this.f8515b.getInterpolation(((float) (SystemClock.uptimeMillis() - this.f8514a)) / 1500.0f);
        double d = (((double) interpolation) * this.f8516c.getPosition().longitude) + (((double) (C2020f.f10933c - interpolation)) * this.f8517d.longitude);
        this.f8516c.setPosition(new LatLng((((double) (C2020f.f10933c - interpolation)) * this.f8517d.latitude) + (((double) interpolation) * this.f8516c.getPosition().latitude), d));
    }
}
