package com.amap.api.services.core;

import android.content.Context;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RouteSearch.DriveRouteQuery;
import org.p122a.p123a.C2915a;

/* renamed from: com.amap.api.services.core.f */
public class C0473f extends C0449r<DriveRouteQuery, DriveRouteResult> {
    public C0473f(Context context, DriveRouteQuery driveRouteQuery) {
        super(context, driveRouteQuery);
    }

    protected DriveRouteResult m4771a(String str) {
        return C0477j.m4800c(str);
    }

    protected String a_() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("key=").append(C0496w.m4874f(this.d));
        stringBuffer.append("&origin=").append(C0471d.m4759a(((DriveRouteQuery) this.a).getFromAndTo().getFrom()));
        if (!C0477j.m4816h(((DriveRouteQuery) this.a).getFromAndTo().getStartPoiID())) {
            stringBuffer.append("&originid=").append(((DriveRouteQuery) this.a).getFromAndTo().getStartPoiID());
        }
        stringBuffer.append("&destination=").append(C0471d.m4759a(((DriveRouteQuery) this.a).getFromAndTo().getTo()));
        if (!C0477j.m4816h(((DriveRouteQuery) this.a).getFromAndTo().getDestinationPoiID())) {
            stringBuffer.append("&destinationid=").append(((DriveRouteQuery) this.a).getFromAndTo().getDestinationPoiID());
        }
        stringBuffer.append("&strategy=").append(C2915a.f14760f + ((DriveRouteQuery) this.a).getMode());
        stringBuffer.append("&extensions=all");
        if (((DriveRouteQuery) this.a).hasPassPoint()) {
            stringBuffer.append("&waypoints=").append(((DriveRouteQuery) this.a).getPassedPointStr());
        }
        if (((DriveRouteQuery) this.a).hasAvoidpolygons()) {
            stringBuffer.append("&avoidpolygons=").append(((DriveRouteQuery) this.a).getAvoidpolygonsStr());
        }
        if (((DriveRouteQuery) this.a).hasAvoidRoad()) {
            stringBuffer.append("&avoidroad=").append(m4455c(((DriveRouteQuery) this.a).getAvoidRoad()));
        }
        stringBuffer.append("&output=json");
        return stringBuffer.toString();
    }

    protected /* synthetic */ Object m4772b(String str) {
        return m4771a(str);
    }

    public String m4773b() {
        return C0470c.m4755a() + "/direction/driving?";
    }
}
