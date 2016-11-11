package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.amap.api.services.route.RouteSearch.BusRouteQuery;

/* renamed from: com.amap.api.services.route.m */
final class C0544m implements Creator<BusRouteQuery> {
    C0544m() {
    }

    public BusRouteQuery m5026a(Parcel parcel) {
        return new BusRouteQuery(parcel);
    }

    public BusRouteQuery[] m5027a(int i) {
        return new BusRouteQuery[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m5026a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m5027a(i);
    }
}
