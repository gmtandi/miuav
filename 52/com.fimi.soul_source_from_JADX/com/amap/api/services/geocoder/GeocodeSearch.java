package com.amap.api.services.geocoder;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.amap.api.services.core.C0471d;
import com.amap.api.services.core.C0474g;
import com.amap.api.services.core.C0480l;
import com.amap.api.services.core.C0490p;
import com.amap.api.services.core.C0490p.C0486c;
import com.amap.api.services.core.C0490p.C0489f;
import com.amap.api.services.core.C0493t;
import java.util.List;

public final class GeocodeSearch {
    public static final String AMAP = "autonavi";
    public static final String GPS = "gps";
    private Context f3232a;
    private OnGeocodeSearchListener f3233b;
    private Handler f3234c;

    /* renamed from: com.amap.api.services.geocoder.GeocodeSearch.1 */
    class C05051 implements Runnable {
        final /* synthetic */ RegeocodeQuery f3228a;
        final /* synthetic */ GeocodeSearch f3229b;

        C05051(GeocodeSearch geocodeSearch, RegeocodeQuery regeocodeQuery) {
            this.f3229b = geocodeSearch;
            this.f3228a = regeocodeQuery;
        }

        public void run() {
            Message obtainMessage = C0490p.m4850a().obtainMessage();
            try {
                obtainMessage.arg1 = 2;
                obtainMessage.what = 21;
                RegeocodeAddress fromLocation = this.f3229b.getFromLocation(this.f3228a);
                obtainMessage.arg2 = 0;
                C0489f c0489f = new C0489f();
                c0489f.f3165b = this.f3229b.f3233b;
                c0489f.f3164a = new RegeocodeResult(this.f3228a, fromLocation);
                obtainMessage.obj = c0489f;
            } catch (Throwable e) {
                C0471d.m4762a(e, "GeocodeSearch", "getFromLocationAsyn");
                obtainMessage.arg2 = e.getErrorCode();
            } finally {
                this.f3229b.f3234c.sendMessage(obtainMessage);
            }
        }
    }

    /* renamed from: com.amap.api.services.geocoder.GeocodeSearch.2 */
    class C05062 implements Runnable {
        final /* synthetic */ GeocodeQuery f3230a;
        final /* synthetic */ GeocodeSearch f3231b;

        C05062(GeocodeSearch geocodeSearch, GeocodeQuery geocodeQuery) {
            this.f3231b = geocodeSearch;
            this.f3230a = geocodeQuery;
        }

        public void run() {
            Message obtainMessage = C0490p.m4850a().obtainMessage();
            try {
                obtainMessage.what = 20;
                obtainMessage.arg1 = 2;
                List fromLocationName = this.f3231b.getFromLocationName(this.f3230a);
                obtainMessage.arg2 = 0;
                C0486c c0486c = new C0486c();
                c0486c.f3159b = this.f3231b.f3233b;
                c0486c.f3158a = new GeocodeResult(this.f3230a, fromLocationName);
                obtainMessage.obj = c0486c;
            } catch (Throwable e) {
                C0471d.m4762a(e, "GeocodeSearch", "getFromLocationNameAsyn");
                obtainMessage.arg2 = e.getErrorCode();
            } finally {
                this.f3231b.f3234c.sendMessage(obtainMessage);
            }
        }
    }

    public interface OnGeocodeSearchListener {
        void onGeocodeSearched(GeocodeResult geocodeResult, int i);

        void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i);
    }

    public GeocodeSearch(Context context) {
        this.f3232a = context.getApplicationContext();
        this.f3234c = C0490p.m4850a();
    }

    public RegeocodeAddress getFromLocation(RegeocodeQuery regeocodeQuery) {
        C0480l.m4841a(this.f3232a);
        return (RegeocodeAddress) new C0493t(this.f3232a, regeocodeQuery).m4458g();
    }

    public void getFromLocationAsyn(RegeocodeQuery regeocodeQuery) {
        new Thread(new C05051(this, regeocodeQuery)).start();
    }

    public List<GeocodeAddress> getFromLocationName(GeocodeQuery geocodeQuery) {
        C0480l.m4841a(this.f3232a);
        return (List) new C0474g(this.f3232a, geocodeQuery).m4458g();
    }

    public void getFromLocationNameAsyn(GeocodeQuery geocodeQuery) {
        new Thread(new C05062(this, geocodeQuery)).start();
    }

    public void setOnGeocodeSearchListener(OnGeocodeSearchListener onGeocodeSearchListener) {
        this.f3233b = onGeocodeSearchListener;
    }
}
