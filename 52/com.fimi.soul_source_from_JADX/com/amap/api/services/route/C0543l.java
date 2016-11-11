package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.services.route.l */
final class C0543l implements Creator<RouteResult> {
    C0543l() {
    }

    public RouteResult m5024a(Parcel parcel) {
        return new RouteResult(parcel);
    }

    public RouteResult[] m5025a(int i) {
        return new RouteResult[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m5024a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m5025a(i);
    }
}
