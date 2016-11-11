package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.services.route.b */
final class C0533b implements Creator<BusRouteResult> {
    C0533b() {
    }

    public BusRouteResult m5004a(Parcel parcel) {
        return new BusRouteResult(parcel);
    }

    public BusRouteResult[] m5005a(int i) {
        return new BusRouteResult[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m5004a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m5005a(i);
    }
}
