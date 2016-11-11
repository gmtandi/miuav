package com.amap.api.services.core;

import android.content.Context;
import com.amap.api.services.route.RouteSearch.WalkRouteQuery;
import com.amap.api.services.route.WalkRouteResult;

/* renamed from: com.amap.api.services.core.u */
public class C0494u extends C0449r<WalkRouteQuery, WalkRouteResult> {
    public C0494u(Context context, WalkRouteQuery walkRouteQuery) {
        super(context, walkRouteQuery);
    }

    protected WalkRouteResult m4863a(String str) {
        return C0477j.m4805d(str);
    }

    protected String a_() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("key=").append(C0496w.m4874f(this.d));
        stringBuffer.append("&origin=").append(C0471d.m4759a(((WalkRouteQuery) this.a).getFromAndTo().getFrom()));
        stringBuffer.append("&destination=").append(C0471d.m4759a(((WalkRouteQuery) this.a).getFromAndTo().getTo()));
        stringBuffer.append("&multipath=0");
        stringBuffer.append("&output=json");
        return stringBuffer.toString();
    }

    protected /* synthetic */ Object m4864b(String str) {
        return m4863a(str);
    }

    public String m4865b() {
        return C0470c.m4755a() + "/direction/walking?";
    }
}
