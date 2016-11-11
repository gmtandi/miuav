package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.services.route.t */
final class C0551t implements Creator<WalkRouteResult> {
    C0551t() {
    }

    public WalkRouteResult m5040a(Parcel parcel) {
        return new WalkRouteResult(parcel);
    }

    public WalkRouteResult[] m5041a(int i) {
        return new WalkRouteResult[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m5040a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m5041a(i);
    }
}
