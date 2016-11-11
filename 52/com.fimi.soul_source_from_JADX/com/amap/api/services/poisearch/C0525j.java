package com.amap.api.services.poisearch;

import android.content.Context;
import com.amap.api.services.core.C0470c;
import com.amap.api.services.core.C0471d;
import com.amap.api.services.core.C0477j;
import com.amap.api.services.core.C0492s;
import com.amap.api.services.core.C0496w;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.poisearch.PoiSearch.Query;
import com.amap.api.services.poisearch.PoiSearch.SearchBound;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* renamed from: com.amap.api.services.poisearch.j */
class C0525j extends C0522g<C0492s, ArrayList<PoiItem>> {
    private int f3391h;
    private int f3392i;
    private int f3393j;
    private List<String> f3394k;
    private List<SuggestionCity> f3395l;

    public C0525j(Context context, C0492s c0492s) {
        super(context, c0492s);
        this.f3391h = 1;
        this.f3392i = 20;
        this.f3393j = 0;
        this.f3394k = new ArrayList();
        this.f3395l = new ArrayList();
    }

    private String m4981n() {
        return ((C0492s) this.a).f3168b.isDistanceSort() ? "distance" : "weight";
    }

    private String m4982o() {
        StringBuffer stringBuffer = new StringBuffer();
        if (((C0492s) this.a).f3167a.hasGroupBuyLimit() && ((C0492s) this.a).f3167a.hasDiscountLimit()) {
            stringBuffer.append("&filter=groupbuy:1|discount:1");
            return stringBuffer.toString();
        }
        if (((C0492s) this.a).f3167a.hasGroupBuyLimit()) {
            stringBuffer.append("&filter=");
            stringBuffer.append("groupbuy:1");
        }
        if (((C0492s) this.a).f3167a.hasDiscountLimit()) {
            stringBuffer.append("&filter=");
            stringBuffer.append("discount:1");
        }
        return stringBuffer.toString();
    }

    public void m4983a(int i) {
        this.f3391h = i + 1;
    }

    protected String a_() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("output=json");
        if (((C0492s) this.a).f3168b != null) {
            double a;
            if (((C0492s) this.a).f3168b.getShape().equals(SearchBound.BOUND_SHAPE)) {
                a = C0471d.m4757a(((C0492s) this.a).f3168b.getCenter().getLongitude());
                stringBuilder.append("&location=").append(a + MiPushClient.ACCEPT_TIME_SEPARATOR + C0471d.m4757a(((C0492s) this.a).f3168b.getCenter().getLatitude()));
                stringBuilder.append("&radius=").append(((C0492s) this.a).f3168b.getRange());
                stringBuilder.append("&sortrule=").append(m4981n());
            } else if (((C0492s) this.a).f3168b.getShape().equals(SearchBound.RECTANGLE_SHAPE)) {
                LatLonPoint lowerLeft = ((C0492s) this.a).f3168b.getLowerLeft();
                LatLonPoint upperRight = ((C0492s) this.a).f3168b.getUpperRight();
                double a2 = C0471d.m4757a(lowerLeft.getLatitude());
                a = C0471d.m4757a(lowerLeft.getLongitude());
                stringBuilder.append("&polygon=" + a + MiPushClient.ACCEPT_TIME_SEPARATOR + a2 + ";" + C0471d.m4757a(upperRight.getLongitude()) + MiPushClient.ACCEPT_TIME_SEPARATOR + C0471d.m4757a(upperRight.getLatitude()));
            } else if (((C0492s) this.a).f3168b.getShape().equals(SearchBound.POLYGON_SHAPE)) {
                List polyGonList = ((C0492s) this.a).f3168b.getPolyGonList();
                if (polyGonList != null && polyGonList.size() > 0) {
                    stringBuilder.append("&polygon=" + C0471d.m4761a(polyGonList));
                }
            }
        }
        String city = ((C0492s) this.a).f3167a.getCity();
        if (!m4973a(city)) {
            stringBuilder.append("&city=").append(m4455c(city));
        }
        if (!C0471d.m4763a(m4982o())) {
            stringBuilder.append(m4982o());
        }
        stringBuilder.append("&keywords=" + m4455c(((C0492s) this.a).f3167a.getQueryString()));
        stringBuilder.append("&language=").append(C0470c.m4756b());
        stringBuilder.append("&offset=" + this.f3392i);
        stringBuilder.append("&page=" + this.f3391h);
        stringBuilder.append("&types=" + m4455c(((C0492s) this.a).f3167a.getCategory()));
        stringBuilder.append("&extensions=all");
        stringBuilder.append("&key=" + C0496w.m4874f(this.d));
        return stringBuilder.toString();
    }

    public /* synthetic */ Object m4984b(String str) {
        return m4987e(str);
    }

    public String m4985b() {
        String str = C0470c.m4755a() + "/place";
        return ((C0492s) this.a).f3168b == null ? str + "/text?" : ((C0492s) this.a).f3168b.getShape().equals(SearchBound.BOUND_SHAPE) ? str + "/around?" : (((C0492s) this.a).f3168b.getShape().equals(SearchBound.RECTANGLE_SHAPE) || ((C0492s) this.a).f3168b.getShape().equals(SearchBound.POLYGON_SHAPE)) ? str + "/polygon?" : str;
    }

    public void m4986b(int i) {
        int i2 = 30;
        int i3 = i > 30 ? 30 : i;
        if (i3 > 0) {
            i2 = i3;
        }
        this.f3392i = i2;
    }

    public ArrayList<PoiItem> m4987e(String str) {
        ArrayList<PoiItem> arrayList = new ArrayList();
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                this.f3393j = jSONObject.optInt("count");
                arrayList = C0477j.m4801c(jSONObject);
                if (jSONObject.has("suggestion")) {
                    jSONObject = jSONObject.optJSONObject("suggestion");
                    if (jSONObject != null) {
                        this.f3395l = C0477j.m4781a(jSONObject);
                        this.f3394k = C0477j.m4795b(jSONObject);
                    }
                }
            } catch (Throwable e) {
                C0471d.m4762a(e, "PoiSearchKeywordHandler", "paseJSONJSONException");
            } catch (Throwable e2) {
                C0471d.m4762a(e2, "PoiSearchKeywordHandler", "paseJSONException");
            }
        }
        return arrayList;
    }

    public int m4988f() {
        return this.f3392i;
    }

    public int m4989i() {
        return this.f3393j;
    }

    public Query m4990j() {
        return ((C0492s) this.a).f3167a;
    }

    public SearchBound m4991k() {
        return ((C0492s) this.a).f3168b;
    }

    public List<String> m4992l() {
        return this.f3394k;
    }

    public List<SuggestionCity> m4993m() {
        return this.f3395l;
    }
}
