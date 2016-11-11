package com.amap.api.services.core;

import android.content.Context;
import com.amap.api.services.district.DistrictResult;
import com.amap.api.services.district.DistrictSearchQuery;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.amap.api.services.core.e */
public class C0472e extends C0449r<DistrictSearchQuery, DistrictResult> {
    public C0472e(Context context, DistrictSearchQuery districtSearchQuery) {
        super(context, districtSearchQuery);
    }

    protected DistrictResult m4768a(String str) {
        ArrayList arrayList = new ArrayList();
        DistrictResult districtResult = new DistrictResult((DistrictSearchQuery) this.a, arrayList);
        try {
            JSONObject jSONObject = new JSONObject(str);
            districtResult.setPageCount(jSONObject.optInt("count"));
            JSONArray optJSONArray = jSONObject.optJSONArray("districts");
            if (optJSONArray == null) {
                return districtResult;
            }
            C0477j.m4789a(optJSONArray, arrayList, null);
            return districtResult;
        } catch (Throwable e) {
            C0471d.m4762a(e, "DistrictServerHandler", "paseJSONJSONException");
        } catch (Throwable e2) {
            C0471d.m4762a(e2, "DistrictServerHandler", "paseJSONException");
        }
    }

    protected String a_() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("output=json");
        stringBuffer.append("&page=").append(((DistrictSearchQuery) this.a).getPageNum() + 1);
        stringBuffer.append("&offset=").append(((DistrictSearchQuery) this.a).getPageSize());
        stringBuffer.append("&showChild=").append(((DistrictSearchQuery) this.a).isShowChild());
        if (((DistrictSearchQuery) this.a).isShowBoundary()) {
            stringBuffer.append("&extensions=all");
        } else {
            stringBuffer.append("&extensions=base");
        }
        if (((DistrictSearchQuery) this.a).checkKeyWords()) {
            stringBuffer.append("&keywords=").append(m4455c(((DistrictSearchQuery) this.a).getKeywords()));
        }
        if (((DistrictSearchQuery) this.a).checkLevels()) {
            stringBuffer.append("&level=").append(((DistrictSearchQuery) this.a).getKeywordsLevel());
        }
        stringBuffer.append("&key=" + C0496w.m4874f(this.d));
        return stringBuffer.toString();
    }

    protected /* synthetic */ Object m4769b(String str) {
        return m4768a(str);
    }

    public String m4770b() {
        return C0470c.m4755a() + "/config/district?";
    }
}
