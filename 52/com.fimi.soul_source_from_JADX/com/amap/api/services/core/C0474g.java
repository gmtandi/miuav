package com.amap.api.services.core;

import android.content.Context;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import java.util.ArrayList;
import org.json.JSONObject;

/* renamed from: com.amap.api.services.core.g */
public class C0474g extends C0449r<GeocodeQuery, ArrayList<GeocodeAddress>> {
    public C0474g(Context context, GeocodeQuery geocodeQuery) {
        super(context, geocodeQuery);
    }

    protected ArrayList<GeocodeAddress> m4774a(String str) {
        ArrayList<GeocodeAddress> arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("count") && jSONObject.getInt("count") > 0) {
                arrayList = C0477j.m4826n(jSONObject);
            }
        } catch (Throwable e) {
            C0471d.m4762a(e, "GeocodingHandler", "paseJSONJSONException");
        } catch (Throwable e2) {
            C0471d.m4762a(e2, "GeocodingHandler", "paseJSONException");
        }
        return arrayList;
    }

    protected String a_() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("output=json").append("&address=").append(m4455c(((GeocodeQuery) this.a).getLocationName()));
        String city = ((GeocodeQuery) this.a).getCity();
        if (!C0477j.m4816h(city)) {
            stringBuffer.append("&city=").append(m4455c(city));
        }
        stringBuffer.append("&key=" + C0496w.m4874f(this.d));
        stringBuffer.append("&language=").append(C0470c.m4756b());
        return stringBuffer.toString();
    }

    protected /* synthetic */ Object m4775b(String str) {
        return m4774a(str);
    }

    public String m4776b() {
        return C0470c.m4755a() + "/geocode/geo?";
    }
}
