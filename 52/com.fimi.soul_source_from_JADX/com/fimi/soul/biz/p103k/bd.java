package com.fimi.soul.biz.p103k;

import android.content.Context;
import android.location.Location;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.GeocodeSearch.OnGeocodeSearchListener;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.fimi.kernel.p084e.ah;
import com.fimi.soul.biz.p098j.C1330i;
import com.fimi.soul.biz.p102i.C1374m;
import com.fimi.soul.entity.CityInfo;
import com.fimi.soul.entity.PlaneMsg;
import java.util.HashMap;

/* renamed from: com.fimi.soul.biz.k.bd */
public class bd implements Callback, OnGeocodeSearchListener {
    private static bd f6167a;
    private static final int f6168b = 0;
    private HashMap<Integer, C1330i> f6169c;
    private C1374m f6170d;
    private Context f6171e;
    private Handler f6172f;
    private GeocodeSearch f6173g;
    private C1394s f6174h;

    static {
        f6167a = null;
    }

    public bd(Context context) {
        this.f6171e = null;
        this.f6170d = new C1374m(context);
        this.f6172f = new Handler(this);
        this.f6171e = context;
        this.f6169c = new HashMap();
        this.f6173g = new GeocodeSearch(context);
        this.f6173g.setOnGeocodeSearchListener(this);
    }

    public static bd m9284a(Context context) {
        if (f6167a == null) {
            f6167a = new bd(context);
        }
        return f6167a;
    }

    public void m9287a(Location location, C1330i c1330i) {
        this.f6173g.getFromLocationAsyn(new RegeocodeQuery(new LatLonPoint(location.getLatitude(), location.getLongitude()), 200.0f, GeocodeSearch.AMAP));
        this.f6169c.put(Integer.valueOf(0), c1330i);
    }

    public boolean handleMessage(Message message) {
        if (message.obj != null) {
            ((C1330i) this.f6169c.get(Integer.valueOf(message.what))).m8951a((PlaneMsg) message.obj);
        }
        return true;
    }

    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
    }

    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
        if (i == 0 && regeocodeResult != null && regeocodeResult.getRegeocodeAddress() != null && regeocodeResult.getRegeocodeAddress().getFormatAddress() != null) {
            String city = regeocodeResult.getRegeocodeAddress().getCity();
            String district = regeocodeResult.getRegeocodeAddress().getDistrict();
            CityInfo cityInfo = new CityInfo();
            cityInfo.setTown(city);
            cityInfo.setCity(district);
            ah.m8077b(new be(this, 0, cityInfo));
        }
    }
}
