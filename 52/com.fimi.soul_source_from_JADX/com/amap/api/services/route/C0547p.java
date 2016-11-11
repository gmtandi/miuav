package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.amap.api.services.route.RouteSearch.WalkRouteQuery;

/* renamed from: com.amap.api.services.route.p */
final class C0547p implements Creator<WalkRouteQuery> {
    C0547p() {
    }

    public WalkRouteQuery m5032a(Parcel parcel) {
        return new WalkRouteQuery(parcel);
    }

    public WalkRouteQuery[] m5033a(int i) {
        return new WalkRouteQuery[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m5032a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m5033a(i);
    }
}
