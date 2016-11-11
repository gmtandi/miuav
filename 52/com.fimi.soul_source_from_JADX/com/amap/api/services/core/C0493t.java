package com.amap.api.services.core;

import android.content.Context;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.xiaomi.mipush.sdk.MiPushClient;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.amap.api.services.core.t */
public class C0493t extends C0449r<RegeocodeQuery, RegeocodeAddress> {
    public C0493t(Context context, RegeocodeQuery regeocodeQuery) {
        super(context, regeocodeQuery);
    }

    protected RegeocodeAddress m4860a(String str) {
        RegeocodeAddress regeocodeAddress = new RegeocodeAddress();
        try {
            JSONObject optJSONObject = new JSONObject(str).optJSONObject("regeocode");
            if (optJSONObject != null) {
                regeocodeAddress.setFormatAddress(C0477j.m4794b(optJSONObject, "formatted_address"));
                JSONObject optJSONObject2 = optJSONObject.optJSONObject("addressComponent");
                if (optJSONObject2 != null) {
                    C0477j.m4790a(optJSONObject2, regeocodeAddress);
                }
                regeocodeAddress.setPois(C0477j.m4801c(optJSONObject));
                JSONArray optJSONArray = optJSONObject.optJSONArray("roads");
                if (optJSONArray != null) {
                    C0477j.m4798b(optJSONArray, regeocodeAddress);
                }
                JSONArray optJSONArray2 = optJSONObject.optJSONArray("roadinters");
                if (optJSONArray2 != null) {
                    C0477j.m4788a(optJSONArray2, regeocodeAddress);
                }
            }
        } catch (Throwable e) {
            C0471d.m4762a(e, "ReverseGeocodingHandler", "paseJSON");
        }
        return regeocodeAddress;
    }

    protected String a_() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("output=json").append("&extensions=all").append("&location=").append(((RegeocodeQuery) this.a).getPoint().getLongitude()).append(MiPushClient.ACCEPT_TIME_SEPARATOR).append(((RegeocodeQuery) this.a).getPoint().getLatitude());
        stringBuffer.append("&radius=").append(((RegeocodeQuery) this.a).getRadius());
        stringBuffer.append("&coordsys=").append(((RegeocodeQuery) this.a).getLatLonType());
        stringBuffer.append("&key=" + C0496w.m4874f(this.d));
        stringBuffer.append("&language=").append(C0470c.m4756b());
        return stringBuffer.toString();
    }

    protected /* synthetic */ Object m4861b(String str) {
        return m4860a(str);
    }

    public String m4862b() {
        return C0470c.m4755a() + "/geocode/regeo?";
    }
}
