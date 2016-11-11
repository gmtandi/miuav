package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.amap.api.services.route.RouteSearch.DriveRouteQuery;

/* renamed from: com.amap.api.services.route.n */
final class C0545n implements Creator<DriveRouteQuery> {
    C0545n() {
    }

    public DriveRouteQuery m5028a(Parcel parcel) {
        return new DriveRouteQuery(parcel);
    }

    public DriveRouteQuery[] m5029a(int i) {
        return new DriveRouteQuery[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m5028a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m5029a(i);
    }
}
