package com.amap.api.services.poisearch;

import android.content.Context;
import com.amap.api.services.core.C0470c;
import com.amap.api.services.core.C0471d;
import com.amap.api.services.core.C0477j;
import com.amap.api.services.core.C0496w;
import com.amap.api.services.core.ServiceSettings;
import com.tencent.mm.sdk.platformtools.Util;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.amap.api.services.poisearch.i */
class C0524i extends C0522g<String, PoiItemDetail> {
    private String f3390h;

    public C0524i(Context context, String str, String str2) {
        super(context, str);
        this.f3390h = PoiSearch.CHINESE;
        if (Util.ENGLISH.equals(str2)) {
            this.f3390h = str2;
        }
    }

    private PoiItemDetail m4976a(JSONObject jSONObject) {
        PoiItemDetail poiItemDetail = null;
        if (jSONObject != null) {
            JSONArray optJSONArray = jSONObject.optJSONArray("pois");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(0);
                if (optJSONObject != null) {
                    poiItemDetail = C0477j.m4804d(optJSONObject);
                    JSONObject optJSONObject2 = optJSONObject.optJSONObject("rich_content");
                    if (optJSONObject2 != null) {
                        C0477j.m4785a(poiItemDetail, optJSONObject2);
                    }
                    optJSONObject2 = optJSONObject.optJSONObject("deep_info");
                    if (optJSONObject2 != null) {
                        C0477j.m4810e(poiItemDetail, optJSONObject2, optJSONObject);
                    }
                }
            }
        }
        return poiItemDetail;
    }

    private String m4977f() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id=").append((String) this.a);
        stringBuilder.append("&output=json");
        stringBuilder.append("&extensions=all");
        stringBuilder.append("&language=").append(ServiceSettings.getInstance().getLanguage());
        stringBuilder.append("&key=" + C0496w.m4874f(this.d));
        return stringBuilder.toString();
    }

    protected String a_() {
        return m4977f();
    }

    public /* synthetic */ Object m4978b(String str) {
        return m4980e(str);
    }

    public String m4979b() {
        return C0470c.m4755a() + "/place/detail?";
    }

    public PoiItemDetail m4980e(String str) {
        PoiItemDetail poiItemDetail = null;
        try {
            poiItemDetail = m4976a(new JSONObject(str));
        } catch (Throwable e) {
            C0471d.m4762a(e, "PoiSearchIdHandler", "paseJSONJSONException");
        } catch (Throwable e2) {
            C0471d.m4762a(e2, "PoiSearchIdHandler", "paseJSONException");
        }
        return poiItemDetail;
    }
}
