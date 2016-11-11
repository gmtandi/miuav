package com.tencent.map.p131b;

import android.content.Context;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import com.amap.api.services.geocoder.GeocodeSearch;
import java.util.Iterator;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.tencent.map.b.e */
public final class C2243e {
    private static LocationManager f11622b;
    private static float f11623d;
    private Context f11624a;
    private C2240a f11625c;
    private C2242c f11626e;
    private C2241b f11627f;
    private boolean f11628g;
    private byte[] f11629h;
    private int f11630i;
    private long f11631j;
    private boolean f11632k;
    private int f11633l;
    private int f11634m;

    /* renamed from: com.tencent.map.b.e.a */
    final class C2240a implements Listener, LocationListener {
        private /* synthetic */ C2243e f11618a;

        private C2240a(C2243e c2243e) {
            this.f11618a = c2243e;
        }

        public final void onGpsStatusChanged(int i) {
            switch (i) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    C2243e.m13396a(this.f11618a, 1);
                    break;
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    C2243e.m13396a(this.f11618a, 0);
                    break;
                case Type.BYTE /*3*/:
                    C2243e.m13396a(this.f11618a, 2);
                    break;
            }
            this.f11618a.m13402b();
        }

        public final void onLocationChanged(Location location) {
            Object obj = null;
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                if (latitude != 29.999998211860657d && longitude != 103.99999916553497d && Math.abs(latitude) >= 1.0E-8d && Math.abs(longitude) >= 1.0E-8d && latitude >= -90.0d && latitude <= 90.0d && longitude >= -180.0d && longitude <= 180.0d) {
                    obj = 1;
                }
                if (obj != null) {
                    this.f11618a.f11631j = System.currentTimeMillis();
                    this.f11618a.m13402b();
                    C2243e.m13396a(this.f11618a, 2);
                    this.f11618a.f11627f = new C2241b(this.f11618a, location, this.f11618a.f11633l, this.f11618a.f11634m, this.f11618a.f11630i, this.f11618a.f11631j);
                    if (this.f11618a.f11626e != null) {
                        this.f11618a.f11626e.m13395a(this.f11618a.f11627f);
                    }
                }
            }
        }

        public final void onProviderDisabled(String str) {
            if (str != null) {
                try {
                    if (str.equals(GeocodeSearch.GPS)) {
                        this.f11618a.f11633l = this.f11618a.f11634m = 0;
                        this.f11618a.f11630i = 0;
                        if (this.f11618a.f11626e != null) {
                            this.f11618a.f11626e.m13394a(this.f11618a.f11630i);
                        }
                    }
                } catch (Exception e) {
                }
            }
        }

        public final void onProviderEnabled(String str) {
            if (str != null) {
                try {
                    if (str.equals(GeocodeSearch.GPS)) {
                        this.f11618a.f11630i = 4;
                        if (this.f11618a.f11626e != null) {
                            this.f11618a.f11626e.m13394a(this.f11618a.f11630i);
                        }
                    }
                } catch (Exception e) {
                }
            }
        }

        public final void onStatusChanged(String str, int i, Bundle bundle) {
        }
    }

    /* renamed from: com.tencent.map.b.e.b */
    public final class C2241b implements Cloneable {
        private Location f11619a;
        private long f11620b;
        private int f11621c;

        public C2241b(C2243e c2243e, Location location, int i, int i2, int i3, long j) {
            this.f11619a = null;
            this.f11620b = 0;
            this.f11621c = 0;
            if (location != null) {
                this.f11619a = new Location(location);
                this.f11621c = i2;
                this.f11620b = j;
            }
        }

        public final boolean m13392a() {
            return this.f11619a == null ? false : (this.f11621c <= 0 || this.f11621c >= 3) && System.currentTimeMillis() - this.f11620b <= 30000;
        }

        public final Location m13393b() {
            return this.f11619a;
        }

        public final Object clone() {
            C2241b c2241b;
            try {
                c2241b = (C2241b) super.clone();
            } catch (Exception e) {
                c2241b = null;
            }
            if (this.f11619a != null) {
                c2241b.f11619a = new Location(this.f11619a);
            }
            return c2241b;
        }
    }

    /* renamed from: com.tencent.map.b.e.c */
    public interface C2242c {
        void m13394a(int i);

        void m13395a(C2241b c2241b);
    }

    static {
        f11622b = null;
        f11623d = 0.0f;
    }

    public C2243e() {
        this.f11624a = null;
        this.f11625c = null;
        this.f11626e = null;
        this.f11627f = null;
        this.f11628g = false;
        this.f11629h = new byte[0];
        this.f11630i = SmileConstants.MAX_SHARED_STRING_VALUES;
        this.f11631j = 0;
        this.f11632k = false;
        this.f11633l = 0;
        this.f11634m = 0;
    }

    static /* synthetic */ int m13396a(C2243e c2243e, int i) {
        int i2 = c2243e.f11630i | i;
        c2243e.f11630i = i2;
        return i2;
    }

    private void m13402b() {
        this.f11634m = 0;
        this.f11633l = 0;
        GpsStatus gpsStatus = f11622b.getGpsStatus(null);
        if (gpsStatus != null) {
            int maxSatellites = gpsStatus.getMaxSatellites();
            Iterator it = gpsStatus.getSatellites().iterator();
            if (it != null) {
                while (it.hasNext() && this.f11633l <= maxSatellites) {
                    this.f11633l++;
                    if (((GpsSatellite) it.next()).usedInFix()) {
                        this.f11634m++;
                    }
                }
            }
        }
    }

    public final void m13410a() {
        synchronized (this.f11629h) {
            if (this.f11628g) {
                if (!(f11622b == null || this.f11625c == null)) {
                    f11622b.removeGpsStatusListener(this.f11625c);
                    f11622b.removeUpdates(this.f11625c);
                }
                this.f11628g = false;
                return;
            }
        }
    }

    public final boolean m13411a(C2242c c2242c, Context context) {
        synchronized (this.f11629h) {
            if (this.f11628g) {
                return true;
            } else if (context == null || c2242c == null) {
                return false;
            } else {
                this.f11624a = context;
                this.f11626e = c2242c;
                try {
                    f11622b = (LocationManager) this.f11624a.getSystemService("location");
                    this.f11625c = new C2240a();
                    if (f11622b == null || this.f11625c == null) {
                        return false;
                    }
                    try {
                        f11622b.requestLocationUpdates(GeocodeSearch.GPS, 1000, 0.0f, this.f11625c);
                        f11622b.addGpsStatusListener(this.f11625c);
                        if (f11622b.isProviderEnabled(GeocodeSearch.GPS)) {
                            this.f11630i = 4;
                        } else {
                            this.f11630i = 0;
                        }
                        this.f11628g = true;
                        return this.f11628g;
                    } catch (Exception e) {
                        return false;
                    }
                } catch (Exception e2) {
                    return false;
                }
            }
        }
    }
}
