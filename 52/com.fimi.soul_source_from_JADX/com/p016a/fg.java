package com.p016a;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Message;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.DPoint;
import com.amap.api.services.geocoder.GeocodeSearch;

/* renamed from: com.a.fg */
class fg implements LocationListener {
    final /* synthetic */ ff f1201a;

    fg(ff ffVar) {
        this.f1201a = ffVar;
    }

    public void onLocationChanged(Location location) {
        if (location != null) {
            try {
                Bundle extras = location.getExtras();
                int i = extras != null ? extras.getInt("satellites") : 0;
                if (i > 0 || this.f1201a.f1197d.isMockEnable()) {
                    if (this.f1201a.f1194a != null) {
                        this.f1201a.f1194a.sendEmptyMessage(5);
                    }
                    if (dn.m1519b() - this.f1201a.f1199f > this.f1201a.f1198e) {
                        AMapLocation aMapLocation;
                        if (ev.m1778a(location.getLatitude(), location.getLongitude()) && this.f1201a.f1197d.isOffset()) {
                            aMapLocation = new AMapLocation(location);
                            aMapLocation.setLocationType(1);
                            DPoint a = fi.m1819a(this.f1201a.f1195b, location.getLongitude(), location.getLatitude());
                            aMapLocation.setLatitude(a.getLatitude());
                            aMapLocation.setLongitude(a.getLongitude());
                        } else {
                            aMapLocation = new AMapLocation(location);
                            aMapLocation.setLatitude(location.getLatitude());
                            aMapLocation.setLongitude(location.getLongitude());
                            aMapLocation.setLocationType(1);
                        }
                        aMapLocation.setSatellites(i);
                        Message message = new Message();
                        message.obj = aMapLocation;
                        message.what = 2;
                        if (this.f1201a.f1194a != null) {
                            this.f1201a.f1194a.sendMessage(message);
                        }
                        this.f1201a.f1199f = dn.m1519b();
                    }
                }
            } catch (Throwable th) {
                ev.m1777a(th, "GPSLocation", "onLocationChanged");
            }
        }
    }

    public void onProviderDisabled(String str) {
        try {
            if (GeocodeSearch.GPS.equals(str) && this.f1201a.f1194a != null) {
                this.f1201a.f1194a.sendEmptyMessage(3);
            }
        } catch (Throwable th) {
            ev.m1777a(th, "GPSLocation", "onProviderDisabled");
        }
    }

    public void onProviderEnabled(String str) {
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
        if (i == 0 || i == 1) {
            try {
                if (this.f1201a.f1194a != null) {
                    this.f1201a.f1194a.sendEmptyMessage(3);
                }
            } catch (Throwable th) {
                ev.m1777a(th, "GPSLocation", "onStatusChanged");
            }
        }
    }
}
