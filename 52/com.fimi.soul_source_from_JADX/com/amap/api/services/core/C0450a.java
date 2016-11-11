package com.amap.api.services.core;

import android.content.Context;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.RouteSearch.BusRouteQuery;
import org.p122a.p123a.C2915a;

/* renamed from: com.amap.api.services.core.a */
public class C0450a extends C0449r<BusRouteQuery, BusRouteResult> {
    public C0450a(Context context, BusRouteQuery busRouteQuery) {
        super(context, busRouteQuery);
    }

    protected BusRouteResult m4460a(String str) {
        return C0477j.m4793b(str);
    }

    protected String a_() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("key=").append(C0496w.m4874f(this.d));
        stringBuffer.append("&origin=").append(C0471d.m4759a(((BusRouteQuery) this.a).getFromAndTo().getFrom()));
        stringBuffer.append("&destination=").append(C0471d.m4759a(((BusRouteQuery) this.a).getFromAndTo().getTo()));
        String city = ((BusRouteQuery) this.a).getCity();
        if (!C0477j.m4816h(city)) {
            stringBuffer.append("&city=").append(m4455c(city));
        }
        stringBuffer.append("&strategy=").append(C2915a.f14760f + ((BusRouteQuery) this.a).getMode());
        stringBuffer.append("&nightflag=").append(((BusRouteQuery) this.a).getNightFlag());
        stringBuffer.append("&output=json");
        return stringBuffer.toString();
    }

    protected /* synthetic */ Object m4461b(String str) {
        return m4460a(str);
    }

    public String m4462b() {
        return C0470c.m4755a() + "/direction/transit/integrated?";
    }
}
