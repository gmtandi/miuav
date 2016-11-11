package com.p016a;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.fimi.soul.base.C1236a;
import java.text.SimpleDateFormat;

/* renamed from: com.a.em */
final class em implements LocationListener {
    private /* synthetic */ dw f1113a;

    em(dw dwVar) {
        this.f1113a = dwVar;
    }

    private static boolean m1747a(Location location) {
        return location != null && GeocodeSearch.GPS.equalsIgnoreCase(location.getProvider()) && location.getLatitude() > -90.0d && location.getLatitude() < 90.0d && location.getLongitude() > -180.0d && location.getLongitude() < 180.0d;
    }

    public final void onLocationChanged(Location location) {
        try {
            long time = location.getTime();
            long currentTimeMillis = System.currentTimeMillis();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(C1236a.f5614l);
            simpleDateFormat.format(Long.valueOf(time));
            simpleDateFormat.format(Long.valueOf(currentTimeMillis));
            if (time > 0) {
                currentTimeMillis = time;
            }
            if (location != null && em.m1747a(location)) {
                if (location.getSpeed() > ((float) dw.f996f)) {
                    eu.m1768a(dw.f999i);
                    eu.m1769b(dw.f999i * 10);
                } else if (location.getSpeed() > ((float) dw.f995e)) {
                    eu.m1768a(dw.f998h);
                    eu.m1769b(dw.f998h * 10);
                } else {
                    eu.m1768a(dw.f997g);
                    eu.m1769b(dw.f997g * 10);
                }
                this.f1113a.f1026w.m1759a();
                em.m1747a(location);
                if (this.f1113a.f1026w.m1759a() && em.m1747a(location)) {
                    location.setTime(System.currentTimeMillis());
                    dw.m1597a(this.f1113a, location, 0, currentTimeMillis);
                }
            }
        } catch (Exception e) {
        }
    }

    public final void onProviderDisabled(String str) {
    }

    public final void onProviderEnabled(String str) {
    }

    public final void onStatusChanged(String str, int i, Bundle bundle) {
    }
}
