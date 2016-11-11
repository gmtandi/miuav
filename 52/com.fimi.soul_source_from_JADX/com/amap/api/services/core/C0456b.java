package com.amap.api.services.core;

import android.content.Context;
import com.amap.api.services.busline.BusLineQuery;
import com.amap.api.services.busline.BusLineQuery.SearchType;
import com.amap.api.services.busline.BusStationQuery;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

/* renamed from: com.amap.api.services.core.b */
public class C0456b<T> extends C0449r<T, ArrayList<?>> {
    private int f3049h;
    private List<String> f3050i;
    private List<SuggestionCity> f3051j;

    public C0456b(Context context, T t) {
        super(context, t);
        this.f3049h = 0;
        this.f3050i = new ArrayList();
        this.f3051j = new ArrayList();
    }

    protected ArrayList<?> m4611a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject optJSONObject = jSONObject.optJSONObject("suggestion");
            if (optJSONObject != null) {
                this.f3051j = C0477j.m4781a(optJSONObject);
                this.f3050i = C0477j.m4795b(optJSONObject);
            }
            this.f3049h = jSONObject.optInt("count");
            return this.a instanceof BusLineQuery ? C0477j.m4818i(jSONObject) : C0477j.m4809e(jSONObject);
        } catch (Throwable e) {
            C0471d.m4762a(e, "BusSearchServerHandler", "paseJSON");
            return null;
        }
    }

    protected String a_() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("output=json");
        String city;
        if (this.a instanceof BusLineQuery) {
            BusLineQuery busLineQuery = (BusLineQuery) this.a;
            stringBuilder.append("&extensions=all");
            if (busLineQuery.getCategory() == SearchType.BY_LINE_ID) {
                stringBuilder.append("&id=").append(m4455c(((BusLineQuery) this.a).getQueryString()));
            } else {
                city = busLineQuery.getCity();
                if (!C0477j.m4816h(city)) {
                    stringBuilder.append("&city=").append(m4455c(city));
                }
                stringBuilder.append("&keywords=" + m4455c(busLineQuery.getQueryString()));
                stringBuilder.append("&offset=" + busLineQuery.getPageSize());
                stringBuilder.append("&page=" + (busLineQuery.getPageNumber() + 1));
            }
        } else {
            BusStationQuery busStationQuery = (BusStationQuery) this.a;
            city = busStationQuery.getCity();
            if (!C0477j.m4816h(city)) {
                stringBuilder.append("&city=").append(m4455c(city));
            }
            stringBuilder.append("&keywords=" + m4455c(busStationQuery.getQueryString()));
            stringBuilder.append("&offset=" + busStationQuery.getPageSize());
            stringBuilder.append("&page=" + (busStationQuery.getPageNumber() + 1));
        }
        stringBuilder.append("&key=" + C0496w.m4874f(this.d));
        return stringBuilder.toString();
    }

    protected /* synthetic */ Object m4612b(String str) {
        return m4611a(str);
    }

    public String m4613b() {
        String str = this.a instanceof BusLineQuery ? ((BusLineQuery) this.a).getCategory() == SearchType.BY_LINE_ID ? "lineid" : ((BusLineQuery) this.a).getCategory() == SearchType.BY_LINE_NAME ? "linename" : C2915a.f14760f : "stopname";
        return C0470c.m4755a() + "/bus/" + str + "?";
    }

    public List<String> b_() {
        return this.f3050i;
    }

    public T m4614c() {
        return this.a;
    }

    public int m4615d() {
        return this.f3049h;
    }

    public List<SuggestionCity> m4616f() {
        return this.f3051j;
    }
}
