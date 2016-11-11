package com.p016a;

import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.services.geocoder.GeocodeSearch;
import org.p122a.p123a.C2915a;

/* renamed from: com.a.ff */
public class ff {
    Handler f1194a;
    Context f1195b;
    LocationManager f1196c;
    AMapLocationClientOption f1197d;
    long f1198e;
    long f1199f;
    LocationListener f1200g;

    public ff(Context context, C0246f c0246f) {
        this.f1198e = 1000;
        this.f1199f = 0;
        this.f1200g = new fg(this);
        this.f1195b = context;
        this.f1194a = c0246f;
        this.f1196c = (LocationManager) this.f1195b.getSystemService("location");
    }

    public void m1809a() {
        if (this.f1196c != null && this.f1200g != null) {
            try {
                this.f1196c.removeUpdates(this.f1200g);
            } catch (Throwable th) {
            }
        }
    }

    void m1810a(long j, float f) {
        try {
            Looper myLooper = Looper.myLooper();
            if (myLooper == null) {
                myLooper = this.f1195b.getMainLooper();
            }
            this.f1198e = j;
            this.f1196c.requestLocationUpdates(GeocodeSearch.GPS, 1000, f, this.f1200g, myLooper);
        } catch (Throwable e) {
            ev.m1777a(e, "GPSLocation", "requestLocationUpdates part1");
            if (this.f1197d.getLocationMode().equals(AMapLocationMode.Device_Sensors)) {
                Message obtain = Message.obtain();
                AMapLocation aMapLocation = new AMapLocation(C2915a.f14760f);
                aMapLocation.setProvider(GeocodeSearch.GPS);
                aMapLocation.setErrorCode(12);
                aMapLocation.setLocationType(1);
                obtain.what = 2;
                obtain.obj = aMapLocation;
                if (this.f1194a != null) {
                    this.f1194a.sendMessage(obtain);
                }
            }
        } catch (Throwable e2) {
            ev.m1777a(e2, "GPSLocation", "requestLocationUpdates part2");
        }
    }

    public void m1811a(AMapLocationClientOption aMapLocationClientOption) {
        this.f1197d = aMapLocationClientOption;
        m1810a(this.f1197d.getInterval(), 0.0f);
    }
}
