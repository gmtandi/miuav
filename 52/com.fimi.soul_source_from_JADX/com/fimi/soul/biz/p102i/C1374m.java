package com.fimi.soul.biz.p102i;

import android.content.Context;
import com.amap.api.services.district.DistrictSearchQuery;
import com.facebook.common.util.UriUtil;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.biz.p098j.C1373m;
import com.fimi.soul.entity.CityInfo;
import com.fimi.soul.entity.PlaneMsg;
import com.fimi.soul.entity.WeatherInfo;
import com.fimi.soul.utils.NetUtil;
import com.fimi.soul.utils.ai;
import com.xiaomi.market.sdk.C2537j;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONException;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.biz.i.m */
public class C1374m implements C1373m {
    public C1374m(Context context) {
    }

    private CityInfo m9170a(Context context) {
        return null;
    }

    private PlaneMsg m9171b(CityInfo cityInfo, Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "getweatherIDbyCity"));
        arrayList.add(new BasicNameValuePair(DistrictSearchQuery.KEYWORDS_CITY, cityInfo.getCity()));
        arrayList.add(new BasicNameValuePair("town", cityInfo.getTown()));
        String b = NetUtil.m12204b(C1236a.f5612j, arrayList, context);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            JSONObject jSONObject = new JSONObject(b);
            String string = jSONObject.getString("errorMessage");
            String string2 = jSONObject.getString("commandCode");
            String string3 = jSONObject.getString("errorCode");
            boolean z = jSONObject.getBoolean("success");
            if (!jSONObject.isNull(UriUtil.DATA_SCHEME)) {
                planeMsg.setData((CityInfo) ai.m12249b(CityInfo.class, jSONObject.getJSONObject(UriUtil.DATA_SCHEME).toString()));
            }
            planeMsg.setCommandCode(string2);
            planeMsg.setErrorCode(string3);
            planeMsg.setErrorMessage(string);
            planeMsg.setSuccess(z);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (JsonParseException e2) {
            e2.printStackTrace();
        } catch (JsonMappingException e3) {
            e3.printStackTrace();
        } catch (IOException e4) {
            e4.printStackTrace();
        }
        return planeMsg;
    }

    public PlaneMsg m9172a(Context context, CityInfo cityInfo) {
        return cityInfo != null ? m9173a(cityInfo, context) : null;
    }

    public PlaneMsg m9173a(CityInfo cityInfo, Context context) {
        String str = "http://weatherapi.market.xiaomi.com/wtr-v2/weather";
        PlaneMsg planeMsg = new PlaneMsg();
        PlaneMsg b = m9171b(cityInfo, context);
        if (b.isSuccess()) {
            CityInfo cityInfo2 = (CityInfo) b.getData();
            List arrayList = new ArrayList();
            arrayList.add(new BasicNameValuePair(C2537j.au, "fimi"));
            arrayList.add(new BasicNameValuePair("cityId", cityInfo2.getWeatherID()));
            String a = NetUtil.m12198a(str, arrayList, context, true);
            if (a != null) {
                try {
                    if (!C2915a.f14760f.equalsIgnoreCase(a)) {
                        JSONObject jSONObject = new JSONObject(a);
                        Object obj = null;
                        if (!jSONObject.isNull("forecast")) {
                            jSONObject = jSONObject.getJSONObject("forecast");
                            obj = new WeatherInfo();
                            obj.setCity(cityInfo.getCity());
                            obj.setWD(jSONObject.getString("fx1"));
                            obj.setWS(jSONObject.getString("fl1"));
                            obj.setWeather(jSONObject.getString("weather1"));
                            obj.setTemp(jSONObject.getString("temp1"));
                            obj.setIndex_tr(jSONObject.getString("index_ls"));
                        }
                        planeMsg.setData(obj);
                        planeMsg.setSuccess(true);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            planeMsg.setSuccess(false);
            planeMsg.setErrorMessage("\u5929\u6c14\u65e0\u6cd5\u83b7\u53d6");
        } else {
            planeMsg.setSuccess(false);
        }
        return planeMsg;
    }
}
