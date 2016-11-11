package com.p016a;

import android.location.GpsSatellite;
import android.location.GpsStatus.Listener;
import android.location.GpsStatus.NmeaListener;
import com.amap.api.services.geocoder.GeocodeSearch;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

/* renamed from: com.a.er */
public final class er implements Listener, NmeaListener {
    private long f1116a;
    private long f1117b;
    private boolean f1118c;
    private List f1119d;
    private String f1120e;
    private String f1121f;
    private String f1122g;
    private /* synthetic */ dw f1123h;

    protected er(dw dwVar) {
        this.f1123h = dwVar;
        this.f1116a = 0;
        this.f1117b = 0;
        this.f1118c = false;
        this.f1119d = new ArrayList();
        this.f1120e = null;
        this.f1121f = null;
        this.f1122g = null;
    }

    public final void m1748a(String str) {
        if (System.currentTimeMillis() - this.f1117b > 400) {
            if (this.f1118c && this.f1119d.size() > 0) {
                try {
                    fd fdVar = new fd(this.f1119d, this.f1120e, null, this.f1122g);
                    if (fdVar.m1796a()) {
                        this.f1123h.f1013L = dw.m1586a(this.f1123h, fdVar, this.f1123h.f1010I);
                        if (this.f1123h.f1013L > 0) {
                            dw.m1593a(this.f1123h, String.format(Locale.CHINA, "&nmea=%.1f|%.1f&g_tp=%d", new Object[]{Double.valueOf(fdVar.m1798c()), Double.valueOf(fdVar.m1797b()), Integer.valueOf(this.f1123h.f1013L)}));
                        }
                    } else {
                        this.f1123h.f1013L = 0;
                    }
                } catch (Exception e) {
                    this.f1123h.f1013L = 0;
                }
                this.f1119d.clear();
                this.f1122g = null;
                this.f1121f = null;
                this.f1120e = null;
                this.f1118c = false;
            }
            if (this.f1119d != null && this.f1119d.size() > 100) {
                this.f1119d.clear();
            }
        }
        if (str.startsWith("$GPGGA")) {
            this.f1118c = true;
            this.f1120e = str.trim();
        } else if (str.startsWith("$GPGSV")) {
            this.f1119d.add(str.trim());
        } else if (str.startsWith("$GPGSA")) {
            this.f1122g = str.trim();
        }
        this.f1117b = System.currentTimeMillis();
    }

    public final void onGpsStatusChanged(int i) {
        int i2 = 0;
        try {
            if (this.f1123h.f1020q != null) {
                switch (i) {
                    case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                        this.f1123h.f1012K = 0;
                    case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                        if (dw.f992a || System.currentTimeMillis() - this.f1116a >= 10000) {
                            if (this.f1123h.f1008G == null) {
                                this.f1123h.f1008G = this.f1123h.f1020q.getGpsStatus(null);
                            } else {
                                this.f1123h.f1020q.getGpsStatus(this.f1123h.f1008G);
                            }
                            this.f1123h.f1009H = 0;
                            this.f1123h.f1010I = 0;
                            this.f1123h.f1011J = new HashMap();
                            int i3 = 0;
                            int i4 = 0;
                            for (GpsSatellite gpsSatellite : this.f1123h.f1008G.getSatellites()) {
                                i3++;
                                if (gpsSatellite.usedInFix()) {
                                    i4++;
                                }
                                if (gpsSatellite.getSnr() > 0.0f) {
                                    i2++;
                                }
                                if (gpsSatellite.getSnr() >= ((float) dw.f989U)) {
                                    this.f1123h.f1010I = this.f1123h.f1010I + 1;
                                }
                            }
                            if (this.f1123h.f1016m == -1 || ((i4 >= 4 && this.f1123h.f1016m < 4) || (i4 < 4 && this.f1123h.f1016m >= 4))) {
                                this.f1123h.f1016m = i4;
                                if (i4 < 4) {
                                    if (this.f1123h.f1021r != null) {
                                        this.f1123h.f1021r.m1744v();
                                    }
                                } else if (this.f1123h.f1021r != null) {
                                    this.f1123h.f1021r.m1743u();
                                }
                            }
                            this.f1123h.f1012K = i2;
                            this.f1123h.m1587a(this.f1123h.f1011J);
                            if (!dw.f992a) {
                                if ((i4 > 3 || i3 > 15) && this.f1123h.f1020q.getLastKnownLocation(GeocodeSearch.GPS) != null) {
                                    this.f1116a = System.currentTimeMillis();
                                }
                            }
                        }
                    default:
                }
            }
        } catch (Exception e) {
        }
    }

    public final void onNmeaReceived(long j, String str) {
        try {
            if (dw.f992a && str != null && !str.equals(C2915a.f14760f) && str.length() >= 9 && str.length() <= Opcodes.FCMPG && this.f1123h.f1003B != null) {
                this.f1123h.f1003B.sendMessage(this.f1123h.f1003B.obtainMessage(1, str));
            }
        } catch (Exception e) {
        }
    }
}
